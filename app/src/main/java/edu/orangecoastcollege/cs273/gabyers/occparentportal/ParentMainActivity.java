package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ParentMainActivity extends AppCompatActivity{

    Button viewClassStreamButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_main);

        viewClassStreamButton = (Button) findViewById(R.id.stream_class_button);
    }
    public void viewProfileDetails(View view)
    {
        //receive parent(in on create from log in activity) and pass to a details Activity with intent
        //view Details Button
    }
    public void viewParentRSSFeed(View view){
        Intent RSSFeedGlue = new Intent(this, RSSActivity.class);
        startActivity(RSSFeedGlue);
    }
    public void viewClassStream(View view)
    {
        Intent streamVideoIntent = new Intent(ParentMainActivity.this, StreamClassVideo.class);

        startActivity(streamVideoIntent);
    }
}
