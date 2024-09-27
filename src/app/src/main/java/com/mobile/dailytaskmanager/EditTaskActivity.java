package com.mobile.dailytaskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;

public class EditTaskActivity extends AppCompatActivity {

    private EditText editTextTaskName;
    private TimePicker timePicker;
    private int taskPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        editTextTaskName = findViewById(R.id.editTextTaskName);
        timePicker = findViewById(R.id.timePicker);
        Button buttonSaveTask = findViewById(R.id.buttonSaveTask);

        Intent intent = getIntent();
        String taskName = intent.getStringExtra("TASK_NAME");
        int hour = intent.getIntExtra("TASK_HOUR", -1);
        int minute = intent.getIntExtra("TASK_MINUTE", -1);
        taskPosition = intent.getIntExtra("TASK_POSITION", -1);

        editTextTaskName.setText(taskName);
        timePicker.setHour(hour);
        timePicker.setMinute(minute);

        buttonSaveTask.setOnClickListener(v -> {
            String updatedTaskName = editTextTaskName.getText().toString();
            int updatedHour = timePicker.getHour();
            int updatedMinute = timePicker.getMinute();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("TASK_NAME", updatedTaskName);
            resultIntent.putExtra("TASK_HOUR", updatedHour);
            resultIntent.putExtra("TASK_MINUTE", updatedMinute);
            resultIntent.putExtra("TASK_POSITION", taskPosition);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
