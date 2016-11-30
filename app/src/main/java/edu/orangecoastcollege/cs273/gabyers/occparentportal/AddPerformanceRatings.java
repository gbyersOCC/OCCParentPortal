package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class AddPerformanceRatings extends AppCompatActivity {

    private TextView mNameTextView;
    private RatingBar mPartRating;
    private RatingBar mAttentRating;
    private RatingBar mCareRating;
    private RatingBar mStudioRating;
    private Button mCancelButton;
    private Button mSaveResultsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_performance_ratings);

        mPartRating = (RatingBar) findViewById(R.id.participation_ratingBar);
        mAttentRating = (RatingBar) findViewById(R.id.attentiveness_ratingBar);
        mCareRating = (RatingBar) findViewById(R.id.caring_ratingBar);
        mStudioRating = (RatingBar) findViewById(R.id.studious_ratingBar);
        mNameTextView = (TextView) findViewById(R.id.name_text_view);
        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mSaveResultsButton = (Button) findViewById(R.id.save_results_button);

        /**
         * TODO change Parent obj into Child obj.
         */

        //receive the child which right now is parent and needs changed
        Parent receivedChild = (Parent) getIntent().getExtras().getParcelable("ChildOBJ");

        //set the name at beginning of layout
        if(receivedChild instanceof Parent)
            mNameTextView.setText(receivedChild.getNameFirst() + " " + receivedChild.getNameLast());

        if(mPartRating.getRating()== 0.0f||mAttentRating.getRating()==0.0f
                ||mCareRating.getRating()==0.0f || mStudioRating.getRating()==0.0f)
            //make toast notifying of incomplete result added
            Toast.makeText(this, "Update all result sets",Toast.LENGTH_LONG );

    }
    public void cancelOnClick(View view){
        //start Teacher Main activity
    }
    public void saveResultsOnClick(View view){

    }
}
