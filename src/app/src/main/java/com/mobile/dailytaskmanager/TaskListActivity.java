package com.mobile.dailytaskmanager;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class TaskListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        Button buttonEditTask = findViewById(R.id.buttonEditTask);
        Button buttonDeleteTask = findViewById(R.id.buttonDeleteTask);

        // Thiết lập RecyclerView và adapter


    }

}

