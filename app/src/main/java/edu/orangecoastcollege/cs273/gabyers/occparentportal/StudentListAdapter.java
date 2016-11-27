package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by gabye on 11/26/2016.
 */

public class StudentListAdapter extends ArrayAdapter<Child> {

    private Context mContext;
    private int mResourceId;
    private List<Child> mAllChildren;

    //List Item user interface widgets
    private ImageView listItemImageView;
    private TextView listItemNameTextView;
    private TextView listItemAgeTextView;

    public StudentListAdapter(Context context, int resourceId, List<Child> childList) {
        super(context, resourceId, childList);
        this.mResourceId = resourceId;
        this.mContext = context;
        this.mAllChildren = childList;
    }
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        Child chosenOne = mAllChildren.get(pos);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        listItemImageView = (ImageView) view.findViewById(R.id.list_profile_image_view);
        listItemNameTextView = (TextView) view.findViewById(R.id.list_item_name_textView);
        listItemAgeTextView = (TextView) view.findViewById(R.id.list_item_age_textView);

        listItemImageView.setImageURI(chosenOne.getImagePath());
        listItemNameTextView.setText(chosenOne.getNameFirst()+ " " + chosenOne.getNameLast());
        listItemAgeTextView.setText(chosenOne.getAge());


        return view;
    }
}
