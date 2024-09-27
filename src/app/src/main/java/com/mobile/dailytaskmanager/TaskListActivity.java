package com.mobile.dailytaskmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class TaskListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewTasks;
    private EditText editTextTaskName;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        editTextTaskName = findViewById(R.id.editTextTaskName);
        timePicker = findViewById(R.id.timePicker);
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        Button buttonEditTask = findViewById(R.id.buttonEditTask);
        Button buttonDeleteTask = findViewById(R.id.buttonDeleteTask);

        buttonEditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // Thiết lập RecyclerView và adapter

    }

}

