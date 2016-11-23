package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by gabye on 11/14/2016.
 */

public class Parent implements Parcelable {

    private int mId;
    private String mUsername;
    private String mNameFirst;
    private String mNameLast;
    private String mEmailAddress;
    private long mPhoneNumber;
    private Uri mImagePath;
    private long mLastLogin;
    private ArrayList<Child> children;

    public Parent() {
        mId = -1;
        mUsername = "";
        mNameFirst = "";
        mNameLast = "";
        mEmailAddress = "";
        mPhoneNumber = 0l;
        children = new ArrayList<>();
    }

    public Parent(int id, String username, String firstName,
                  String lastName, String emailAddress,
                  long phoneNumber, Uri imagePath, long lastLogin) {

        mId = id;
        mUsername = username;
        mNameFirst = firstName;
        mNameLast = lastName;
        mEmailAddress = emailAddress;
        mPhoneNumber = phoneNumber;
        mImagePath = imagePath;
        mLastLogin = lastLogin;
        children = new ArrayList<>();
    }

    public Parent(String username, String firstName, String lastName,
                  String emailAddress, long phoneNumber, Uri imagePath,
                  long lastLogin) {

        this(-1, username, firstName, lastName, emailAddress, phoneNumber,
                imagePath, lastLogin);
    }

    private Parent(Parcel source){

        mId = source.readInt();
        mUsername = source.readString();
        mNameFirst = source.readString();
        mNameLast = source.readString();
        mEmailAddress = source.readString();
        mPhoneNumber = source.readLong();
        mImagePath = Uri.parse(source.readString());
        mLastLogin = source.readLong();
        children = new ArrayList<>();
        source.readTypedList(children, Child.CREATOR);
    }

    public int getId() {
        return mId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
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

    public long getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public Uri getImagePath() {
        return mImagePath;
    }

    public void setImagePath(Uri imagePath) {
        mImagePath = imagePath;
    }

    public long getLastLogin() {
        return mLastLogin;
    }

    public void setLastLogin(long lastLogin) {
        mLastLogin = lastLogin;
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void addChild(Child child){
        children.add(child);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Parent)
                || ((Parent) obj).getChildren().size() != this.getChildren().size()){
            return false;
        }
        else
            return mId == ((Parent) obj).getId()
            && mUsername.equals(((Parent) obj).getUsername())
            && mNameFirst.equals(((Parent) obj).getNameFirst())
            && mNameLast.equals(((Parent) obj).getNameLast())
            && mEmailAddress.equals(((Parent) obj).getEmailAddress())
            && mPhoneNumber == ((Parent) obj).getPhoneNumber()
            && mImagePath.toString().equals(((Parent) obj).getImagePath().toString())
            && mLastLogin == ((Parent) obj).getLastLogin()
            && children.equals(((Parent) obj).getChildren());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(mId);
        dest.writeString(mUsername);
        dest.writeString(mNameFirst);
        dest.writeString(mNameLast);
        dest.writeString(mEmailAddress);
        dest.writeLong(mPhoneNumber);
        dest.writeString(mImagePath.toString());
        dest.writeLong(mLastLogin);
        dest.writeTypedList(children);
    }
    public static final Parcelable.Creator<Parent> CREATOR = new Parcelable.Creator<Parent>(){
        @Override
        public Parent createFromParcel(Parcel source) {
            return new Parent(source) ;
        }

        @Override
        public Parent[] newArray(int size) {
            return new Parent[size];
        }
    };
}
