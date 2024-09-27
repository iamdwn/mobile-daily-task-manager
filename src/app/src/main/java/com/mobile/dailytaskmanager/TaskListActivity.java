package com.mobile.dailytaskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.dailytaskmanager.Models.Task;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTasks;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList = new ArrayList<>();
    public static final int ADD_TASK_REQUEST = 1;
    public static final int EDIT_TASK_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        recyclerViewTasks = findViewById(R.id.recyclerViewTaskList);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));

        taskAdapter = new TaskAdapter(taskList, this);
        recyclerViewTasks.setAdapter(taskAdapter);

        Button buttonAddTask = findViewById(R.id.buttonAddTask);
        buttonAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(TaskListActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, ADD_TASK_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK) {
            String taskName = data.getStringExtra("TASK_NAME");
            int hour = data.getIntExtra("TASK_HOUR", -1);
            int minute = data.getIntExtra("TASK_MINUTE", -1);
            if (taskName != null) {
                Task newTask = new Task(taskName, hour, minute);
                taskList.add(newTask);
                taskAdapter.notifyItemInserted(taskList.size() - 1);
            }
        } else if (requestCode == EDIT_TASK_REQUEST && resultCode == RESULT_OK) {
            String taskName = data.getStringExtra("TASK_NAME");
            int hour = data.getIntExtra("TASK_HOUR", -1);
            int minute = data.getIntExtra("TASK_MINUTE", -1);
            int position = data.getIntExtra("TASK_POSITION", -1);
            if (position != -1 && taskName != null) {
                Task updatedTask = new Task(taskName, hour, minute);
                taskList.set(position, updatedTask);
                taskAdapter.notifyItemChanged(position);
            }
        }
    }
}
