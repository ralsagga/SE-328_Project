package com.task.se_3282022s.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class SqLiteDatabase extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="StudentsApp.db";
    private static final String TABLE_NAME="Table_Students";

    private static final String ID ="ID";
    private static final String NAME ="Name";
    private static final String SurName ="SurName";
    private static final String FatherName ="FatherName";
    private static final String NationalID ="nationalID";
    private static final String dateOfBirth ="dateOfBirth";
    private static final String Gender ="gender";


    public SqLiteDatabase(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String create_table = "CREATE TABLE " +TABLE_NAME +"(" +
                ID + " TEXT, " + NAME +" TEXT, "+
                SurName +" TEXT, "+
                FatherName +" TEXT, "+
                NationalID +" TEXT, "+
                dateOfBirth +" TEXT, "+
                Gender +" TEXT)";
        sqLiteDatabase.execSQL(create_table);
        System.out.println("create table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
       /* sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
        System.out.println("table dropped");*/

    }

    public boolean isIdStudent(String id){

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {ID};
        String[] args = {String.valueOf(id)};

        Cursor cursor = db.query(TABLE_NAME, columns, ID + " = ? ", args, null, null, null);

        boolean isExist = cursor.moveToFirst() && cursor.getCount() >= 1;

        cursor.close();

        return isExist;

    }
    public boolean insertStudent(StudentModel studentModel)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, studentModel.getStudentId());
        contentValues.put(NAME, studentModel.getStudentName());
        contentValues.put(SurName, studentModel.getSurName());
        contentValues.put(FatherName, studentModel.getFatherName());
        contentValues.put(NationalID, studentModel.getNationalID());
        contentValues.put(dateOfBirth, studentModel.getDateOfBirth());
        contentValues.put(Gender, studentModel.getGender());

        long result =  database.insert(TABLE_NAME , null,contentValues);
        if (result==-1)
            return false;
        else
            return true;

    }

  public boolean deleteStudent(StudentModel studentModel)
  {
      SQLiteDatabase database = this.getWritableDatabase();
      long result = database.delete(TABLE_NAME , ID + "=?",new String[]{String.valueOf(studentModel.getStudentId())});
      if (result==-1)
          return false;
      else
          return true;

  }


    public List<StudentModel> getStudents()
    {

        List<StudentModel> arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {

                String id=cursor.getString(0);
                String name=cursor.getString(1);
                String surName=cursor.getString(2);
                String fatherName=cursor.getString(3);
                String nationalID=cursor.getString(4);
                String dateOfBirth=cursor.getString(5);
                String gender=cursor.getString(6);

                arrayList.add(new StudentModel(id,name,fatherName,surName,nationalID,dateOfBirth,gender));


            }while (cursor.moveToNext());

        }
        return arrayList;
    }


}
