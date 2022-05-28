package com.task.se_3282022s.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.task.se_3282022s.Helper.StudentsAdapter;
import com.task.se_3282022s.R;
import com.task.se_3282022s.sqlite.SqLiteDatabase;

import java.util.ArrayList;


public class SQLActivity extends AppCompatActivity {

    SqLiteDatabase sqLiteDatabase;
    private RecyclerView recyclerView;
    private StudentsAdapter studentsAdapter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlactivity);
        getSupportActionBar().setTitle("SQL");
        sqLiteDatabase = new SqLiteDatabase(this, null, 1);
        initProgressBar();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        studentsAdapter = new StudentsAdapter(getApplicationContext(), new ArrayList<>());
        recyclerView.setAdapter(studentsAdapter);
        studentsAdapter.setAdapterData(sqLiteDatabase.getStudents());

    }

    private void initProgressBar() {
        progressDialog = new ProgressDialog(SQLActivity.this);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle(R.string.app_name);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();

    }


}