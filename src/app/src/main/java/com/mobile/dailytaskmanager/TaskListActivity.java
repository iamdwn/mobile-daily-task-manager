package com.mobile.dailytaskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.dailytaskmanager.AddTaskActivity;
import com.mobile.dailytaskmanager.Models.Task;
import com.mobile.dailytaskmanager.R;
import com.mobile.dailytaskmanager.TaskAdapter;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTasks;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList = new ArrayList<>();  // Hold tasks in this list
    private static final int ADD_TASK_REQUEST = 1;  // Request code to identify AddTaskActivity result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        // Initialize the RecyclerView and adapter
        recyclerViewTasks = findViewById(R.id.recyclerViewTaskList);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(taskList);
        recyclerViewTasks.setAdapter(taskAdapter);

        // Sample tasks for initial display
        taskList.add(new Task("Sample Task 1", 9, 30));
        taskList.add(new Task("Sample Task 2", 11, 45));

        // Button to launch AddTaskActivity
        Button buttonAddTask = findViewById(R.id.buttonAddTask);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch AddTaskActivity to create a new task
                Intent intent = new Intent(TaskListActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, ADD_TASK_REQUEST);
            }
        });
    }

    // This method handles the result from AddTaskActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TASK_REQUEST && resultCode == RESULT_OK) {
            // Get the task data from the Intent
            String taskName = data.getStringExtra("TASK_NAME");
            int hour = data.getIntExtra("TASK_HOUR", -1);
            int minute = data.getIntExtra("TASK_MINUTE", -1);

            // Make sure we got a valid task name and time
            if (taskName != null && hour != -1 && minute != -1) {
                // Create a new task and add it to the list
                Task newTask = new Task(taskName, hour, minute);
                taskList.add(newTask);
                taskAdapter.notifyItemInserted(taskList.size() - 1);  // Notify the adapter to refresh the list
            }
        }
    }
}
