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
    private float mPartRating;
    private float mAttentRating;
    private float mCareRating;
    private float mStudioRating;

    public Child(int id, int age, String nameFirst, String nameLast, Uri imagePath, float partRating, float attentRating, float careRating, float studioRating) {
        mId = id;
        mAge = age;
        mNameFirst = nameFirst;
        mNameLast = nameLast;
        mImagePath = imagePath;
        mPartRating = partRating;
        mAttentRating = attentRating;
        mCareRating = careRating;
        mStudioRating = studioRating;
    }

    public Child(int age, String nameFirst, String nameLast, Uri imagePath,  float partRating, float attentRating, float careRating, float studioRating) {
      this(-1,age, nameFirst, nameLast, imagePath, 0.0f, 0.0f, 0.0f, 0.0f);
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

    public float getPartRating() {
        return mPartRating;
    }

    public void setPartRating(float partRating) {
        mPartRating = partRating;
    }

    public float getAttentRating() {
        return mAttentRating;
    }

    public void setAttentRating(float attentRating) {
        mAttentRating = attentRating;
    }

    public float getCareRating() {
        return mCareRating;
    }

    public void setCareRating(float careRating) {
        mCareRating = careRating;
    }

    public float getStudioRating() {
        return mStudioRating;
    }

    public void setStudioRating(float studioRating) {
        mStudioRating = studioRating;
    }
}
