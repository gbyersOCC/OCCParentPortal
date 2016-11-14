package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gabye on 11/14/2016.
 */

public class Parent implements Parcelable {
    private Parent(Parcel source){

    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

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
