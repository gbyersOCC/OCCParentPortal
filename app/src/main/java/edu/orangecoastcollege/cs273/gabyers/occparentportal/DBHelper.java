package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gabye on 11/17/2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    private Context mContext;
    // DEFINE THE DATABASE VERSION, NAME, AND TABLE NAMES
    static final String DATABASE_NAME = "OCCParentPortal";
    private static final String DATABASE_TABLE_TEACHER = "Teacher";
    private static final String DATABASE_TABLE_PARENT = "Parent";
    private static final String DATABASE_TABLE_CHILD = "Child";
    private static final String DATABASE_TABLE_MAP = "Map";
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

    // DEFINE THE FIELDS (COLUMN NAMES) FOR THE MAP TABLE
    private static final String LOCATIONS_TABLE = "Locations";
    private static final String LOCATIONS_KEY_FIELD_ID = "_id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_ADDRESS = "address";
    private static final String FIELD_CITY = "city";
    private static final String FIELD_STATE = "state";
    private static final String FIELD_ZIP_CODE = "zip_code";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_LATITUDE = "latitude";
    private static final String FIELD_LONGITUDE = "longitude";

    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTeacherQuery = "CREATE TABLE " + DATABASE_TABLE_TEACHER + "("
                + TEACHER_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TEACHER_FIRST_NAME + " TEXT, "
                + TEACHER_LAST_NAME + " TEXT, "
                + TEACHER_EMAIL_ADDRESS + " TEXT, "
                + TEACHER_IMAGE_PATH + " TEXT, "
                + TEACHER_LESSON + " TEXT, "
                + TEACHER_PASSWORD + " TEXT, "
                + TEACHER_LAST_LOGIN + " TEXT"+ ")";
        db.execSQL (createTeacherQuery);

        String createParentQuery = "CREATE TABLE " + DATABASE_TABLE_PARENT + "("
                + PARENT_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PARENT_USERNAME + " TEXT, "
                + PARENT_FIRST_NAME + " TEXT, "
                + PARENT_LAST_NAME + " TEXT, "
                + PARENT_EMAIL_ADDRESS + " TEXT, "
                + PARENT_PHONE_NUMBER + " INTEGER, "
                + PARENT_IMAGE_PATH + " TEXT, "
                + PARENT_LAST_LOGIN + " INTEGER" + ")";
        db.execSQL(createParentQuery);

        String createChildQuery =  "CREATE TABLE " + DATABASE_TABLE_CHILD + "("
                + CHILD_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CHILD_AGE + " INTEGER, "
                + CHILD_FIRST_NAME + " TEXT, "
                + CHILD_LAST_NAME + " TEXT, "
                + CHILD_IMAGE_PATH + " TEXT, "
                + CHILD_PARTICIPATION_RATING + " REAL, "
                + CHILD_ATTENTIVENESS_RATING + " REAL, "
                + CHILD_CARING_RATING + " REAL, "
                + CHILD_STUDIOUS_RATING + " REAL" + ")";
        db.execSQL(createChildQuery);

        String createMapQuery = "CREATE TABLE " + LOCATIONS_TABLE + "("
                + LOCATIONS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_NAME + " TEXT, "
                + FIELD_ADDRESS + " TEXT, "
                + FIELD_CITY + " TEXT,"
                + FIELD_STATE + " TEXT,"
                + FIELD_ZIP_CODE + " TEXT,"
                + FIELD_PHONE + " TEXT,"
                + FIELD_LATITUDE + " REAL,"
                + FIELD_LONGITUDE + " REAL" + ")";
        db.execSQL(createMapQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_TEACHER);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PARENT);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_CHILD);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_MAP);
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
        Cursor cursor = database.query(DATABASE_TABLE_TEACHER, new String[]{TEACHER_KEY_FIELD_ID,
                TEACHER_FIRST_NAME, TEACHER_LAST_NAME, TEACHER_EMAIL_ADDRESS, TEACHER_IMAGE_PATH,
                TEACHER_LESSON, TEACHER_PASSWORD, TEACHER_LAST_LOGIN},
                null, null, null, null, null, null);

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
                new String[]{CHILD_KEY_FIELD_ID, CHILD_AGE, CHILD_FIRST_NAME, CHILD_LAST_NAME,
                        PARENT_IMAGE_PATH, CHILD_PARTICIPATION_RATING, CHILD_ATTENTIVENESS_RATING,
                        CHILD_CARING_RATING, CHILD_STUDIOUS_RATING},
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

    ///////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////MAP TABLE METHODS/////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void addLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, location.getName());
        values.put(FIELD_ADDRESS, location.getAddress());
        values.put(FIELD_CITY, location.getCity());
        values.put(FIELD_STATE, location.getState());
        values.put(FIELD_ZIP_CODE, location.getZipCode());
        values.put(FIELD_PHONE, location.getPhone());
        values.put(FIELD_LATITUDE, location.getLatitude());
        values.put(FIELD_LONGITUDE, location.getLongitude());

        db.insert(LOCATIONS_TABLE, null, values);

        db.close();
    }

    public ArrayList<Location> getAllLocations() {
        ArrayList<Location> locationsList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                LOCATIONS_TABLE,
                new String[]{LOCATIONS_KEY_FIELD_ID, FIELD_NAME, FIELD_ADDRESS, FIELD_CITY,
                        FIELD_STATE, FIELD_ZIP_CODE, FIELD_PHONE, FIELD_LATITUDE, FIELD_LONGITUDE},
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Location location =
                        new Location(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getString(6),
                                cursor.getDouble(7),
                                cursor.getDouble(8));
                locationsList.add(location);
            } while (cursor.moveToNext());
        }
        return locationsList;
    }

    public void deleteLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(LOCATIONS_TABLE, LOCATIONS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(location.getId())});
        db.close();
    }

    public void deleteAllLocations() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LOCATIONS_TABLE, null, null);
        db.close();
    }

    public Location getLocation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                LOCATIONS_TABLE,
                new String[]{LOCATIONS_KEY_FIELD_ID, FIELD_NAME, FIELD_ADDRESS, FIELD_CITY, FIELD_STATE, FIELD_ZIP_CODE, FIELD_PHONE, FIELD_LATITUDE, FIELD_LONGITUDE},
                LOCATIONS_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Location location =
                new Location(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getDouble(7),
                        cursor.getDouble(8));

        db.close();
        return location;
    }

    public boolean importLocationsFromCSV(String csvFileName) {
        AssetManager manager = mContext.getAssets();
        InputStream inStream;
        try {
            inStream = manager.open(csvFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length != 9) {
                    Log.d("Locations", "Skipping Bad CSV Row: " + Arrays.toString(fields));
                    continue;
                }
                int id = Integer.parseInt(fields[0].trim());
                String name = fields[1].trim();
                String address = fields[2].trim();
                String city = fields[3].trim();
                String state = fields[4].trim();
                String zipCode = fields[5].trim();
                String phone = fields[6].trim();
                double latitude = Double.parseDouble(fields[7].trim());
                double longitude = Double.parseDouble(fields[8].trim());
                addLocation(new Location(id, name, address, city, state, zipCode, phone, latitude, longitude));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
