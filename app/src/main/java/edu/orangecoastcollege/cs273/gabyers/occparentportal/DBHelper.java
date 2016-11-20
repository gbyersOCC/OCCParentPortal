package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by gabye on 11/17/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    //TASK 1: DEFINE THE DATABASE VERSION, NAME AND TABLE NAME
    static final String DATABASE_NAME = "OCCParentPortal";
    private static final String DATABASE_TABLE_TEACHER = "Teacher";
    private static final String DATABASE_TABLE_PARENT = "Parent";
    private static final int DATABASE_VERSION = 1;


    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String KEY_FIELD_ID = "id";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private static final String EMAIL_ADDRESS = "Email";
    private static final String IMAGE_PATH = "ImagePath";
    private static final String LESSON = "Lesson";
    private static final String PASSWORD = "Password";
    private static final String LAST_LOGIN = "LastLogin";

    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE " + DATABASE_TABLE_TEACHER + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME + " TEXT, "
                + LAST_NAME + " TEXT, "
                + EMAIL_ADDRESS + " TEXT, "
                + IMAGE_PATH + " TEXT, "
                + LESSON + " TEXT, "
                + PASSWORD + " TEXT, "
                + LAST_LOGIN + " TEXT"+ ")";
        db.execSQL (table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_TEACHER);
        onCreate(db);
    }

    public void addTeacher(Teacher teacher){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME, teacher.getNameFirst());
        values.put(LAST_NAME, teacher.getNameLast());
        values.put(EMAIL_ADDRESS, teacher.getEmailAddress());
        values.put(IMAGE_PATH, teacher.getImagePath().toString());
        values.put(LESSON, teacher.getLesson());
        values.put(PASSWORD, teacher.getPassword());
        values.put(LAST_LOGIN, teacher.getLastLogin());

        database.insert(DATABASE_TABLE_TEACHER, null, values);

        database.close();
    }

    public ArrayList<Teacher> getAllTeachers(){
        ArrayList<Teacher> teacherList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DATABASE_TABLE_TEACHER, new String[]{KEY_FIELD_ID, FIRST_NAME, LAST_NAME, EMAIL_ADDRESS, IMAGE_PATH, LESSON, PASSWORD, LAST_LOGIN},null, null, null, null, null, null);

        if(cursor.moveToFirst()){

            do{
                int id = cursor.getInt(0);
                String first = cursor.getString(1);
                String last = cursor.getString(2);
                String email = cursor.getString(3);
                Uri imagePath = Uri.parse(cursor.getString(4));
                String lesson = cursor.getString(5);
                String password = cursor.getString(6);
                String lastLogin = cursor.getString(7);

                Teacher teacher = new Teacher(id, first, last, email, imagePath,  lesson, password, lastLogin);

                teacherList.add(teacher);

            }while(cursor.moveToNext());
        }
        return teacherList;
    }

    public void updateTeacher(Teacher teacher){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIRST_NAME, teacher.getNameFirst());
        values.put(LAST_NAME, teacher.getNameLast());
        values.put(EMAIL_ADDRESS, teacher.getEmailAddress());
        values.put(IMAGE_PATH, teacher.getImagePath().toString());
        values.put(LESSON, teacher.getLesson());
        values.put(PASSWORD, teacher.getPassword());
        values.put(LAST_LOGIN, teacher.getLastLogin());

        database.update(DATABASE_TABLE_TEACHER, values, KEY_FIELD_ID+ " = ?", new String[]{String.valueOf(teacher.getId())});

        database.close();
    }

    public void deleteTeacher(Teacher teacher){
SQLiteDatabase db = this.getWritableDatabase();

        db.delete(DATABASE_TABLE_TEACHER, KEY_FIELD_ID + " = ?", new String[]{String.valueOf(teacher.getId())});

        db.close();
    }

    public void deleteAllTeachers(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE_TEACHER, null, null);
        db.close();

    }
}
