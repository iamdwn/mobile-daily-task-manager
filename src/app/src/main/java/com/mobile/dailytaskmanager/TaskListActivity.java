package com.mobile.dailytaskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mobile.dailytaskmanager.Models.Task;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    public static final int EDIT_TASK_REQUEST = 2;
    private static final int ADD_TASK_REQUEST = 1;
    private ArrayList<Task> taskList;
    private TaskAdapter taskAdapter;

    private final ActivityResultLauncher<Intent> addTaskLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String taskName = result.getData().getStringExtra("TASK_NAME");
                    int taskHour = result.getData().getIntExtra("TASK_HOUR", -1);
                    int taskMinute = result.getData().getIntExtra("TASK_MINUTE", -1);

                    if (taskName != null && taskHour != -1 && taskMinute != -1) {
                        Task newTask = new Task(taskName, taskHour, taskMinute);
                        taskList.add(newTask);
                        taskAdapter.notifyDataSetChanged(); // Refresh the list
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        taskList = new ArrayList<>();
        taskList.add(new Task("Lam slide", 1, 2));
        taskList.add(new Task("Lam assignment", 1, 2));

        taskAdapter = new TaskAdapter(taskList, this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTaskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);

        findViewById(R.id.buttonAddTask).setOnClickListener(v -> {
            Intent intent = new Intent(TaskListActivity.this, AddTaskActivity.class);
            addTaskLauncher.launch(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_TASK_REQUEST && resultCode == RESULT_OK && data != null) {
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
            } else if (requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK && data != null) {
                String taskName = data.getStringExtra("TASK_NAME");
                int taskHour = data.getIntExtra("TASK_HOUR", -1);
                int taskMinute = data.getIntExtra("TASK_MINUTE", -1);

                if (taskName != null && taskHour != -1 && taskMinute != -1) {
                    Task newTask = new Task(taskName, taskHour, taskMinute);
                    taskList.add(newTask);
                    taskAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
