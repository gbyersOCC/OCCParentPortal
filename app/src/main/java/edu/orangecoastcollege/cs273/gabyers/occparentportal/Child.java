package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.net.Uri;

/**
 * Created by dnova_000 on 11/17/2016.
 */

public class Child {

    private int mId;
    private int mAge;
    private String mNameFirst;
    private String mNameLast;
    private Uri mImagePath;

    public Child(int id, int age, String nameFirst, String nameLast, Uri imagePath) {
        mId = id;
        mAge = age;
        mNameFirst = nameFirst;
        mNameLast = nameLast;
        mImagePath = imagePath;
    }

    public Child(int age, String nameFirst, String nameLast, Uri imagePath) {
      this(-1,age, nameFirst, nameLast, imagePath);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    public String getNameFirst() {
        return mNameFirst;
    }

    public void setNameFirst(String nameFirst) {
        mNameFirst = nameFirst;
    }

    public String getNameLast() {
        return mNameLast;
    }

    public void setNameLast(String nameLast) {
        mNameLast = nameLast;
    }

    public Uri getImagePath() {
        return mImagePath;
    }

    public void setImagePath(Uri imagePath) {
        mImagePath = imagePath;
    }
}
