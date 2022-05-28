package com.task.se_3282022s.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.task.se_3282022s.sqlite.StudentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FirebaseDatabaseHelperClass {

    private static final String TAG = "FirebaseDatabaseHelper";
    public static DatabaseReference studentEndPoint= FirebaseDatabase.getInstance().getReference("students_table");;


    public FirebaseDatabaseHelperClass() {

    }
    /* *********************************** STUDENT CRUD ********************************** */
    public static void createStudent(final StudentModel studentModel) {

      Log.e("getApplicationContext()", "=  "+studentModel.getStudentId());
        studentEndPoint.child(studentModel.getStudentId()).
                setValue(studentModel).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        Log.e(TAG, "Database entry created");
                    }
                });
    }

    public static void updateStudent(final StudentModel student) {
        studentEndPoint.child(student.getStudentId()).
                setValue(student).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.e(TAG, "Database entry created");
                    } else {
                        Log.e(TAG, task.getException().toString());
                    }
                });
    }

    public static void deleteStudent(final StudentModel student) {
        studentEndPoint.child(student.getStudentId())
                .removeValue().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.e(TAG, "Child deleted Successfully");
                    } else {
                        Log.e(TAG, "Error Deleting child");
                    }
                });
    }


}
