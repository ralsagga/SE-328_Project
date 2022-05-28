package com.task.se_3282022s.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.task.se_3282022s.Helper.StudentsAdapter;
import com.task.se_3282022s.R;
import com.task.se_3282022s.sqlite.StudentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class GetAllActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private StudentsAdapter studentsAdapter;
    private ArrayList<StudentModel> students;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all);
        initProgressBar();
        getSupportActionBar().setTitle("Get Students");
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        studentsAdapter = new StudentsAdapter(getApplicationContext(), new ArrayList<>());
        recyclerView.setAdapter(studentsAdapter);
        students = new ArrayList<>();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference studentEndPoint = firebaseDatabase.getReference("students_table");
        studentEndPoint.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    StudentModel studentModel = s.getValue(StudentModel.class);
                    students.add(studentModel);
                }
                studentsAdapter.setAdapterData(students);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }

    private void initProgressBar() {
        progressDialog = new ProgressDialog(GetAllActivity.this);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle(R.string.app_name);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

    }
}