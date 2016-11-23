package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dnova_000 on 11/17/2016.
 */

public class Child implements Parcelable {

    private int mId;
    private String mNameFirst;
    private String mNameLast;
    private Uri mImagePath;



    public Child(int id, String nameFirst, String nameLast, Uri imagePath){
        mId = id;
        mNameFirst = nameFirst;
        mNameLast = nameLast;
        mImagePath = imagePath;
    }

    public  Child(){
        this(-1,"","",null);
    }

    public Child(String nameFirst, String nameLast, Uri imagePath){
        this(-1 , nameFirst, nameLast, imagePath);
    }


    private Child(Parcel source) {
        mId = source.readInt();
        mNameFirst = source.readString();
        mNameLast = source.readString();
        mImagePath = Uri.parse(source.readString());
    }

    public int getId(){
        return mId;
    }

    public void setId(int id){
        mId = id;
    }

    public String getmNameFirst(){
        return mNameFirst;
    }

    public void setNameFirst(String nameFirst){
        mNameFirst = nameFirst;
    }

    public String getmNameLast(){
        return mNameLast;
    }

    public void setNameLast(String nameLast){
        mNameLast = nameLast;
    }

    public Uri getImagePath() {
        return mImagePath;
    }

    public void setImagePath(Uri imagePath) {
        mImagePath = imagePath;
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
        dest.writeString(mImagePath.toString());
    }
    public static final Parcelable.Creator<Child> CREATOR = new Parcelable.Creator<Child>(){

        @Override
        public Child createFromParcel(Parcel source) {
            return new Child(source);
        }

        @Override
        public Child[] newArray(int size) {
            return new Child[size];
        }
    };

}
