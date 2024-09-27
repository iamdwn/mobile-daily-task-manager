package com.mobile.dailytaskmanager;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.dailytaskmanager.Models.Task;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    public static final int EDIT_TASK_REQUEST = 1;
    private ArrayList<Task> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        taskList = new ArrayList<>();
        taskList.add(new Task("Lam slide",1,2));
        taskList.add(new Task("Lam assignment",2,3));

        taskAdapter = new TaskAdapter(taskList, this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTaskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_TASK_REQUEST && resultCode == RESULT_OK && data != null) {
            // Get updated task data from EditTaskActivity
            String updatedTaskName = data.getStringExtra("TASK_NAME");
            int updatedHour = data.getIntExtra("TASK_HOUR", -1);
            int updatedMinute = data.getIntExtra("TASK_MINUTE", -1);
            int taskPosition = data.getIntExtra("TASK_POSITION", -1);

            if (taskPosition != -1) {
                Task updatedTask = taskList.get(taskPosition);
                updatedTask.setName(updatedTaskName);
                updatedTask.setHour(updatedHour);
                updatedTask.setMinute(updatedMinute);
                taskAdapter.notifyItemChanged(taskPosition);
            }
        }
    }
}
