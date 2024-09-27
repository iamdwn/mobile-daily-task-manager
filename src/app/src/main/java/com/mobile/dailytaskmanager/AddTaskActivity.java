package com.mobile.dailytaskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTaskName;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTaskName = findViewById(R.id.editTextTaskName);
        timePicker = findViewById(R.id.timePicker);
        Button buttonAddTask = findViewById(R.id.buttonAddTask);

        buttonAddTask.setOnClickListener(v -> {
            String taskName = editTextTaskName.getText().toString();

            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("TASK_NAME", taskName);
            resultIntent.putExtra("TASK_HOUR", hour);
            resultIntent.putExtra("TASK_MINUTE", minute);

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
