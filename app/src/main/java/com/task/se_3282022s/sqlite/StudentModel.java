package com.task.se_3282022s.sqlite;

public class StudentModel
{
    private  String studentId, studentName,surName ,fatherName ,nationalID,dateOfBirth, gender;

    public StudentModel() {
    }

    public StudentModel(String studentId, String studentName, String surName, String fatherName, String nationalID, String dateOfBirth, String gender) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.surName = surName;
        this.fatherName = fatherName;
        this.nationalID = nationalID;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSurName() {
        return surName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getNationalID() {
        return nationalID;
    }


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }
}
