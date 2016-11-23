package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

/**
 * Created by gbyers on 11/22/2016.
 */
@RunWith(AndroidJUnit4.class)
public class TeacherTestInstrumented {
//
   @Test
    public void writeToParcel(Parcel dest, int flags) {
  Teacher teachObj = new Teacher();
       Parcel parcel = Parcel.obtain();
       teachObj.writeToParcel(parcel, 0);

       parcel.setDataPosition(0);

       Teacher otherTeach = Teacher.CREATOR.createFromParcel(parcel);

       assertEquals("Error in Parcel", teachObj, otherTeach);

    }
}
