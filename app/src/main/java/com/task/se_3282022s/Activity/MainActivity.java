package com.task.se_3282022s.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.task.se_3282022s.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText, studentIdEditText;
    private Button insertRecordButton, updateRecordButton, deleteRecordButton,
            getAllButton, weatherReportButton, sqlListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        nameEditText = findViewById(R.id.et_name);
        studentIdEditText = findViewById(R.id.et_student_id);

        insertRecordButton = findViewById(R.id.btn_insert_record);
        updateRecordButton = findViewById(R.id.btn_update_record);
        deleteRecordButton = findViewById(R.id.btn_delete_record);
        getAllButton = findViewById(R.id.btn_get_all);
        weatherReportButton = findViewById(R.id.btn_weather_report);
        sqlListButton = findViewById(R.id.btn_sql_list);

        insertRecordButton.setOnClickListener(this);
        updateRecordButton.setOnClickListener(this);
        deleteRecordButton.setOnClickListener(this);
        getAllButton.setOnClickListener(this);
        weatherReportButton.setOnClickListener(this);
        sqlListButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == insertRecordButton) {
            startActivity(new Intent(getApplicationContext(), InsertRecordActivity.class));

        } else if (view == updateRecordButton) {
            startActivity(new Intent(getApplicationContext(), UpdateRecordActivity.class));

        } else if (view == deleteRecordButton) {
            startActivity(new Intent(getApplicationContext(), DeleteRecordActivity.class));

        } else if (view == getAllButton) {
            startActivity(new Intent(getApplicationContext(), GetAllActivity.class));

        } else if (view == weatherReportButton) {
            startActivity(new Intent(getApplicationContext(), WeatherReportActivity.class));

        } else if (view == sqlListButton) {
            startActivity(new Intent(getApplicationContext(), SQLActivity.class));
        }
    }
}