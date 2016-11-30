package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dnova_000 on 11/17/2016.
 */

public class Child implements Parcelable {

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

    protected Child(Parcel in) {
        mId = in.readInt();
        mAge = in.readInt();
        mNameFirst = in.readString();
        mNameLast = in.readString();
        mImagePath = in.readParcelable(Uri.class.getClassLoader());
    }

    public int getId() {
        return mId;
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
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mAge);
        dest.writeString(mNameFirst);
        dest.writeString(mNameLast);
        dest.writeParcelable(mImagePath, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Child> CREATOR = new Creator<Child>() {
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
