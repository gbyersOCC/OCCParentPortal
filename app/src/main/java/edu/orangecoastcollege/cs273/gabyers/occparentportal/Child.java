package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dnova_000 on 11/17/2016.
 * and @Grant Byers on 11/30/2016
 * Edited by wlee
 */

public class Child implements Parcelable{

    private int mId;
    private int mAge;
    private String mNameFirst;
    private String mNameLast;
    private Uri mImagePath;
    private float mPartRating;
    private float mAttentRating;
    private float mCareRating;
    private float mStudioRating;

    public Child(int id, int age, String nameFirst, String nameLast, Uri imagePath,
                 float partRating, float attentRating, float careRating, float studioRating) {
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

    public Child(int age, String nameFirst, String nameLast, Uri imagePath,
                 float partRating, float attentRating, float careRating, float studioRating) {
      this(-1,age, nameFirst, nameLast, imagePath, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    protected Child(Parcel source) {
        mId = source.readInt();
        mAge = source.readInt();
        mNameFirst = source.readString();
        mNameLast = source.readString();
        mImagePath = Uri.parse(source.readString());
        mPartRating = source.readFloat();
        mAttentRating = source.readFloat();
        mCareRating = source.readFloat();
        mStudioRating = source.readFloat();
    }



    public String getNameFull(){
        return (getNameFirst()+" "+ getNameLast());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mAge);
        dest.writeString(mNameFirst);
        dest.writeString(mNameLast);
        dest.writeString(mImagePath.toString());
        dest.writeFloat(mPartRating);
        dest.writeFloat(mAttentRating);
        dest.writeFloat(mCareRating);
        dest.writeFloat(mStudioRating);
    }

    public static final Parcelable.Creator<Child> CREATOR = new Parcelable.Creator<Child>() {
        @Override
        public Child createFromParcel(Parcel in) {
            return new Child(in);
        }

        @Override
        public Child[] newArray(int size) {
            return new Child[size];
        }
    };
}
