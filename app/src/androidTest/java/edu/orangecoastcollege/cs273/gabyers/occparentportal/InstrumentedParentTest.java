package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.net.Uri;
import android.os.Parcel;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by dnova_000 on 11/22/2016.
 */
public class InstrumentedParentTest {

    @Test
    public void writeToParcel() throws Exception {

        // Create a new Parent and write it to the parcel
        Parent parent = new Parent();
        parent.setImagePath(Uri.parse(""));
        parent.addChild(new Child(-1, "", "", Uri.parse("")));
        Parcel parcel = Parcel.obtain();
        parent.writeToParcel(parcel, 0);

        // Reset the parcel for reading
        parcel.setDataPosition(0);

        // Create a new Parent from the parent
        Parent parcelParent = Parent.CREATOR.createFromParcel(parcel);

        // Test that both objects are the same
        assertEquals(parent, parcelParent);
    }

}