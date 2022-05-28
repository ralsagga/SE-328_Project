package com.task.se_3282022s.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.task.se_3282022s.Firebase.FirebaseDatabaseHelperClass;
import com.task.se_3282022s.Helper.HelperClass;
import com.task.se_3282022s.R;
import com.task.se_3282022s.sqlite.SqLiteDatabase;
import com.task.se_3282022s.sqlite.StudentModel;


public class InsertRecordActivity extends AppCompatActivity {

    private EditText studentIdEditText;
    private EditText nameEditText;
    private EditText surNameEditText;
    private EditText fatherNameEditText;
    private EditText nationalIdEditText;
    private EditText dobEditText;
    private EditText genderEditText;
    private SqLiteDatabase database;
    private Button insertButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_record);
        getSupportActionBar().setTitle("InsertRecord");

        database = new SqLiteDatabase(this, null, 1);

        initWidgets();
        initProgressBar();
    }

    private void initWidgets() {

        studentIdEditText = findViewById(R.id.et_student_id);
        nameEditText = findViewById(R.id.et_name);
        surNameEditText = findViewById(R.id.et_sur_name);
        fatherNameEditText = findViewById(R.id.et_father_name);
        nationalIdEditText = findViewById(R.id.et_national_id);
        dobEditText = findViewById(R.id.et_dob);
        genderEditText = findViewById(R.id.et_gender);

        insertButton = findViewById(R.id.btn_insert_record);
        insertButton.setOnClickListener(v -> {

            try {
                progressDialog.show();

                StudentModel student = new StudentModel(
                        studentIdEditText.getText().toString().trim(),
                        nameEditText.getText().toString().trim(),
                        surNameEditText.getText().toString().trim(),
                        fatherNameEditText.getText().toString().trim(),
                        nationalIdEditText.getText().toString().trim(),
                        dobEditText.getText().toString().trim(),
                        genderEditText.getText().toString().trim());
                if (database.isIdStudent(student.getStudentId()))
                {
                    HelperClass.error(getApplicationContext(), "Failed,ID already exists");
                }
                else {
                    FirebaseDatabaseHelperClass.createStudent(student);
                    database.insertStudent(student);
                    HelperClass.success(getApplicationContext(), "Record added successfully");
                    finish();

                }
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
                HelperClass.error(getApplicationContext(), "Error occurred: "+e.getMessage());
                progressDialog.dismiss();
            }
        });

    }

    private void initProgressBar() {
        progressDialog = new ProgressDialog(InsertRecordActivity.this);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle(R.string.app_name);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

    }




}