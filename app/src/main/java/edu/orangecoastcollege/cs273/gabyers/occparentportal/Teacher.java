package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gabye on 11/14/2016.
 */

public class Teacher implements Parcelable {

    private int mId;
    private String mNameFirst;
    private String mNameLast;
    private String mEmailAddress;
    private Uri mImagePath;
    private String mPassword;
    private String mLastLogin;
    private String mLesson;

    public Teacher(int id, String nameFirst, String nameLast, String emailAddress, Uri imagePath, String password, String lastLogin, String lesson) {
        mId = id;
        mNameFirst = nameFirst;
        mNameLast = nameLast;
        mEmailAddress = emailAddress;
        mImagePath = imagePath;
        mPassword = password;
        mLastLogin = lastLogin;
        mLesson = lesson;
    }
    public Teacher(){
        this(-1, "", "","",null,"","","");
    }
    public Teacher(String nameFirst, String nameLast, String emailAddress, Uri imagePath, String password, String lastLogin, String lesson){
        this(-1, nameFirst, nameLast, emailAddress, imagePath, password, lastLogin,lesson);
    }
    private Teacher(Parcel source){
        mId = source.readInt();
        mNameFirst = source.readString();
        mNameLast = source.readString();
        mEmailAddress = source.readString();
        mImagePath = Uri.parse(source.readString());
        mPassword = source.readString();
        mLastLogin = source.readString();
        mLesson = source.readString();
    }
    public int getId(){
    return mId;
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

    public String getEmailAddress() {
        return mEmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        mEmailAddress = emailAddress;
    }

    public Uri getImagePath() {
        return mImagePath;
    }

    public void setImagePath(Uri imagePath) {
        mImagePath = imagePath;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getLastLogin() {
        return mLastLogin;
    }

    public void setLastLogin(String lastLogin) {
        mLastLogin = lastLogin;
    }

    public String getLesson() {
        return mLesson;
    }

    public void setLesson(String lesson) {
        mLesson = lesson;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mNameFirst);
        dest.writeString(mNameLast);
        dest.writeString(mEmailAddress);
        dest.writeString(mImagePath.toString());
        dest.writeString(mPassword);
        dest.writeString(mLastLogin);
        dest.writeString(mLesson);

    }
    public static final Parcelable.Creator<Teacher> CREATOR = new Parcelable.Creator<Teacher>(){

        @Override
        public Teacher createFromParcel(Parcel source) {
            return new Teacher(source);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };
}
