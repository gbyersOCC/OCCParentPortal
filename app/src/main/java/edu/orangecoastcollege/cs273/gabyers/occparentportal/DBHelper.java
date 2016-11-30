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
    // DEFINE THE DATABASE VERSION, NAME, AND TABLE NAMES
    static final String DATABASE_NAME = "OCCParentPortal";
    private static final String DATABASE_TABLE_TEACHER = "Teacher";
    private static final String DATABASE_TABLE_PARENT = "Parent";
    private static final String DATABASE_TABLE_CHILD = "Child";
    private static final int DATABASE_VERSION = 1;


    // DEFINE THE FIELDS (COLUMN NAMES) FOR THE TEACHER TABLE
    private static final String TEACHER_KEY_FIELD_ID = "id";
    private static final String TEACHER_FIRST_NAME = "FirstName";
    private static final String TEACHER_LAST_NAME = "LastName";
    private static final String TEACHER_EMAIL_ADDRESS = "Email";
    private static final String TEACHER_IMAGE_PATH = "ImagePath";
    private static final String TEACHER_LESSON = "Lesson";
    private static final String TEACHER_PASSWORD = "Password";
    private static final String TEACHER_LAST_LOGIN = "LastLogin";

    // DEFINE THE FIELDS (COLUMN NAMES) FOR THE PARENT TABLE
    private static final String PARENT_KEY_FIELD_ID = "id";
    private static final String PARENT_USERNAME = "username";
    private static final String PARENT_FIRST_NAME = "firstname";
    private static final String PARENT_LAST_NAME = "lastname";
    private static final String PARENT_EMAIL_ADDRESS = "email";
    private static final String PARENT_PHONE_NUMBER = "phonenumber";
    private static final String PARENT_IMAGE_PATH = "imagepath";
    private static final String PARENT_LAST_LOGIN = "lastlogin";

    // DEFINE THE FIELDS (COLUMN NAMES) FOR THE CHILD TABLE
    private static final String CHILD_KEY_FIELD_ID = "id";
    private static final String CHILD_AGE = "age";
    private static final String CHILD_FIRST_NAME = "firstname";
    private static final String CHILD_LAST_NAME = "lastname";
    private static final String CHILD_IMAGE_PATH = "imagepath";
    private static final String CHILD_PARTICIPATION_RATING = "childParticipationRating";
    private static final String CHILD_ATTENTIVENESS_RATING = "childAttentRating";
    private static final String CHILD_CARING_RATING = "childCaringRating";
    private static final String CHILD_STUDIOUS_RATING = "childStudiousRating";

    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String teacherTable = "CREATE TABLE " + DATABASE_TABLE_TEACHER + "("
                + TEACHER_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TEACHER_FIRST_NAME + " TEXT, "
                + TEACHER_LAST_NAME + " TEXT, "
                + TEACHER_EMAIL_ADDRESS + " TEXT, "
                + TEACHER_IMAGE_PATH + " TEXT, "
                + TEACHER_LESSON + " TEXT, "
                + TEACHER_PASSWORD + " TEXT, "
                + TEACHER_LAST_LOGIN + " TEXT"+ ")";
        db.execSQL (teacherTable);

        String parentTable = "CREATE TABLE " + DATABASE_TABLE_PARENT + "("
                + PARENT_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PARENT_USERNAME + " TEXT, "
                + PARENT_FIRST_NAME + " TEXT, "
                + PARENT_LAST_NAME + " TEXT, "
                + PARENT_EMAIL_ADDRESS + " TEXT, "
                + PARENT_PHONE_NUMBER + " INTEGER, "
                + PARENT_IMAGE_PATH + " TEXT, "
                + PARENT_LAST_LOGIN + " INTEGER" + ")";
        db.execSQL(parentTable);

        String childTable =  "CREATE TABLE " + DATABASE_TABLE_CHILD + "("
                + CHILD_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CHILD_AGE + " INTEGER, "
                + CHILD_FIRST_NAME + " TEXT, "
                + CHILD_LAST_NAME + " TEXT, "
                + CHILD_IMAGE_PATH + " TEXT, "
                + CHILD_PARTICIPATION_RATING + " REAL, "
                + CHILD_ATTENTIVENESS_RATING + " REAL, "
                + CHILD_CARING_RATING + " REAL, "
                + CHILD_STUDIOUS_RATING + " REAL)";
        db.execSQL(childTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_TEACHER);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PARENT);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CHILD);
        onCreate(db);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////TEACHER TABLE METHODS/////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void addTeacher(Teacher teacher){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEACHER_FIRST_NAME, teacher.getNameFirst());
        values.put(TEACHER_LAST_NAME, teacher.getNameLast());
        values.put(TEACHER_EMAIL_ADDRESS, teacher.getEmailAddress());
        values.put(TEACHER_IMAGE_PATH, teacher.getImagePath().toString());
        values.put(TEACHER_LESSON, teacher.getLesson());
        values.put(TEACHER_PASSWORD, teacher.getPassword());
        values.put(TEACHER_LAST_LOGIN, teacher.getLastLogin());

        database.insert(DATABASE_TABLE_TEACHER, null, values);

        database.close();
    }

    public ArrayList<Teacher> getAllTeachers(){
        ArrayList<Teacher> teacherList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DATABASE_TABLE_TEACHER, new String[]{TEACHER_KEY_FIELD_ID, TEACHER_FIRST_NAME, TEACHER_LAST_NAME, TEACHER_EMAIL_ADDRESS, TEACHER_IMAGE_PATH, TEACHER_LESSON, TEACHER_PASSWORD, TEACHER_LAST_LOGIN},null, null, null, null, null, null);

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

        values.put(TEACHER_FIRST_NAME, teacher.getNameFirst());
        values.put(TEACHER_LAST_NAME, teacher.getNameLast());
        values.put(TEACHER_EMAIL_ADDRESS, teacher.getEmailAddress());
        values.put(TEACHER_IMAGE_PATH, teacher.getImagePath().toString());
        values.put(TEACHER_LESSON, teacher.getLesson());
        values.put(TEACHER_PASSWORD, teacher.getPassword());
        values.put(TEACHER_LAST_LOGIN, teacher.getLastLogin());

        database.update(DATABASE_TABLE_TEACHER, values, TEACHER_KEY_FIELD_ID + " = ?", new String[]{String.valueOf(teacher.getId())});

        database.close();
    }

    public void deleteTeacher(Teacher teacher){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(DATABASE_TABLE_TEACHER, TEACHER_KEY_FIELD_ID + " = ?", new String[]{String.valueOf(teacher.getId())});

        db.close();
    }

    public void deleteAllTeachers(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE_TEACHER, null, null);
        db.close();

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////PARENT TABLE METHODS/////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method adds a parent to the local databse.
     * @param parent the parent to be added.
     */
    public void addParent(Parent parent){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PARENT_USERNAME, parent.getUsername());
        values.put(PARENT_FIRST_NAME, parent.getNameFirst());
        values.put(PARENT_LAST_NAME, parent.getNameLast());
        values.put(PARENT_EMAIL_ADDRESS, parent.getEmailAddress());
        values.put(PARENT_PHONE_NUMBER, parent.getPhoneNumber());
        values.put(PARENT_IMAGE_PATH, parent.getImagePath().toString());
        values.put(PARENT_LAST_LOGIN, parent.getLastLogin());

        database.insert(DATABASE_TABLE_PARENT, null, values);

        database.close();
    }

    /**
     * This method retrieves all parent from the local database
     * @return an ArrayList<> of parents.
     */
    public ArrayList<Parent> getAllParents(){
        ArrayList<Parent> parentList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DATABASE_TABLE_PARENT, new String[]{PARENT_KEY_FIELD_ID,
                PARENT_USERNAME, PARENT_FIRST_NAME, PARENT_LAST_NAME, PARENT_EMAIL_ADDRESS,
                PARENT_PHONE_NUMBER, PARENT_IMAGE_PATH, PARENT_LAST_LOGIN},null, null, null,
                null, null, null);

        if(cursor.moveToFirst()){

            do{
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                String first = cursor.getString(2);
                String last = cursor.getString(3);
                String email = cursor.getString(4);
                String phoneNumber = cursor.getString(5);
                Uri imagePath = Uri.parse(cursor.getString(6));
                String lastLogin = cursor.getString(7);

                Parent parent = new Parent(id, username, first, last, email, phoneNumber, imagePath, lastLogin);

                parentList.add(parent);

            }while(cursor.moveToNext());
        }
        return parentList;
    }

    /**
     * This method takes a parent as a parameter at updates the database with the new parent values.
     * @param parent the parent to update in the databse
     */
    public void updateParent(Parent parent){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PARENT_USERNAME, parent.getUsername());
        values.put(PARENT_FIRST_NAME, parent.getNameFirst());
        values.put(PARENT_LAST_NAME, parent.getNameLast());
        values.put(PARENT_EMAIL_ADDRESS, parent.getEmailAddress());
        values.put(PARENT_PHONE_NUMBER, parent.getPhoneNumber());
        values.put(PARENT_IMAGE_PATH, parent.getImagePath().toString());
        values.put(PARENT_LAST_LOGIN, parent.getLastLogin());

        database.update(DATABASE_TABLE_PARENT, values, PARENT_KEY_FIELD_ID + " = ?", new String[]{String.valueOf(parent.getId())});

        database.close();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////CHILD TABLE METHODS/////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void addChild(Child child){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CHILD_AGE, child.getAge());
        values.put(CHILD_FIRST_NAME, child.getNameFirst());
        values.put(CHILD_LAST_NAME, child.getNameLast());
        values.put(CHILD_IMAGE_PATH, child.getImagePath().toString());
        values.put(CHILD_PARTICIPATION_RATING, child.getPartRating());
        values.put(CHILD_ATTENTIVENESS_RATING, child.getAttentRating());
        values.put(CHILD_CARING_RATING, child.getCareRating());
        values.put(CHILD_STUDIOUS_RATING, child.getStudioRating());

        database.insert(DATABASE_TABLE_CHILD, null, values);

        database.close();
    }

    public ArrayList<Child> getAllChildren(){
        ArrayList<Child> childrenList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DATABASE_TABLE_CHILD,
                new String[]{CHILD_KEY_FIELD_ID, CHILD_AGE, CHILD_FIRST_NAME, CHILD_LAST_NAME, PARENT_IMAGE_PATH, CHILD_PARTICIPATION_RATING,
                CHILD_ATTENTIVENESS_RATING, CHILD_CARING_RATING, CHILD_STUDIOUS_RATING},
                null, null, null, null, null, null);

        if(cursor.moveToFirst()){

            do{
                int id = cursor.getInt(0);
                int age = cursor.getInt(1);
                String first = cursor.getString(2);
                String last = cursor.getString(3);
                Uri imagePath = Uri.parse(cursor.getString(4));
                float partRating = cursor.getFloat(5), attentRating = cursor.getFloat(6),
                        careRating = cursor.getFloat(7), studiousRating = cursor.getFloat(8);

                Child child = new Child(id, age, first, last, imagePath, partRating, attentRating, careRating, studiousRating);

                childrenList.add(child);

            }while(cursor.moveToNext());
        }
        return childrenList;
    }

    public void updateChild(Child child){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CHILD_AGE, child.getAge());
        values.put(CHILD_FIRST_NAME, child.getNameFirst());
        values.put(CHILD_LAST_NAME, child.getNameLast());
        values.put(CHILD_IMAGE_PATH, child.getImagePath().toString());
        values.put(CHILD_PARTICIPATION_RATING, child.getPartRating());
        values.put(CHILD_ATTENTIVENESS_RATING, child.getAttentRating());
        values.put(CHILD_CARING_RATING, child.getCareRating());
        values.put(CHILD_STUDIOUS_RATING, child.getStudioRating());

        database.update(DATABASE_TABLE_CHILD, values, CHILD_KEY_FIELD_ID + " = ?", new String[]{String.valueOf(child.getId())});

        database.close();
    }
}
