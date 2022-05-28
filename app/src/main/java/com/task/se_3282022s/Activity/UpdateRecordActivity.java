package com.task.se_3282022s.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.task.se_3282022s.Firebase.FirebaseDatabaseHelperClass;
import com.task.se_3282022s.Helper.HelperClass;
import com.task.se_3282022s.R;
import com.task.se_3282022s.sqlite.SqLiteDatabase;
import com.task.se_3282022s.sqlite.StudentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class UpdateRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText studentIdEditText;
    private EditText nameEditText;
    private EditText surNameEditText;
    private EditText fatherNameEditText;
    private EditText nationalIdEditText;
    private EditText dobEditText;
    private EditText genderEditText;
    private Button updateButton;
    private EditText searchIdEditText;
    private Button searchButton;
    private StudentModel student;
    private SqLiteDatabase database;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);
        getSupportActionBar().setTitle("Update");
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
        searchIdEditText = findViewById(R.id.et_search_student_id);
        searchButton = findViewById(R.id.btn_search);
        searchButton.setOnClickListener(this);
        updateButton = findViewById(R.id.btn_update_record);
        updateButton.setOnClickListener(this);

    }

    private void initValues() {
        studentIdEditText.setText(student.getStudentId());
        nameEditText.setText(student.getStudentName());
        surNameEditText.setText(student.getSurName());
        fatherNameEditText.setText(student.getFatherName());
        nationalIdEditText.setText(student.getNationalID());
        dobEditText.setText(student.getDateOfBirth());
        genderEditText.setText(student.getGender());
        nameEditText.setEnabled(true);
        surNameEditText.setEnabled(true);
        fatherNameEditText.setEnabled(true);
        nationalIdEditText.setEnabled(true);
        dobEditText.setEnabled(true);
        genderEditText.setEnabled(true);
        updateButton.setEnabled(true);
        progressDialog.dismiss();
    }


    private void initProgressBar() {
        progressDialog = new ProgressDialog(UpdateRecordActivity.this);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle(R.string.app_name);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(true);

    }
    @Override
    public void onClick(View view) {
        if (view == searchButton) {
            try {

                String sID = searchIdEditText.getText().toString().trim();
                if (sID.isEmpty()) {
                    HelperClass.warning(getApplicationContext(), "Id cannot be empty");
                    return;
                }
                progressDialog.show();

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference studentEndPoint = firebaseDatabase.getReference("students_table");
                studentEndPoint.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot s : dataSnapshot.getChildren()) {
                            StudentModel studentModel = s.getValue(StudentModel.class);
                            if (sID.equalsIgnoreCase(studentModel.getStudentId())) {
                                student = studentModel;
                                initValues();

                                return;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                HelperClass.error(getApplicationContext(), "Error occurred");
            }
        }

        if (view == updateButton) {
            try {
                progressDialog.show();
                StudentModel student = new StudentModel(studentIdEditText.getText().toString().trim(),
                        nameEditText.getText().toString().trim(), surNameEditText.getText().toString().trim(),
                        fatherNameEditText.getText().toString().trim(), nationalIdEditText.getText().toString().trim(),
                        dobEditText.getText().toString().trim(), genderEditText.getText().toString().trim());

                FirebaseDatabaseHelperClass.updateStudent(student);
                HelperClass.success(getApplicationContext(), "Record updated successfully");
                finish();
                progressDialog.dismiss();
            } catch (Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                HelperClass.error(getApplicationContext(), "Error occurred");
            }
        }
    }
}