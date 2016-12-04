package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class AddPerformanceRatings extends AppCompatActivity {

    private Child mSelectedChild;
    private DBHelper mDb;

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

        mDb = new DBHelper(AddPerformanceRatings.this);
        mPartRating = (RatingBar) findViewById(R.id.participation_ratingBar);
        mAttentRating = (RatingBar) findViewById(R.id.attentiveness_ratingBar);
        mCareRating = (RatingBar) findViewById(R.id.caring_ratingBar);
        mStudioRating = (RatingBar) findViewById(R.id.studious_ratingBar);
        mNameTextView = (TextView) findViewById(R.id.student_name_textView);
        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mSaveResultsButton = (Button) findViewById(R.id.save_results_button);


        //receive the child which right now is parent and needs changed
        mSelectedChild = (Child) getIntent().getExtras().getParcelable("ChildOBJ");

        //set the name at beginning of layout
        if(mSelectedChild instanceof Child && mSelectedChild != null);
        mNameTextView.setText(mSelectedChild.getNameFull() );

    }
    public void cancelOnClick(View view){
        mPartRating.setRating(0.0f);
        mAttentRating.setRating(0.0f);
        mCareRating.setRating(0.0f);
        mStudioRating.setRating(0.0f);
        finish();
    }
    public void saveResultsOnClick(View view){

        //if any of the results categories are not set make Toast saying so
        if(mPartRating.getRating()== 0.0f||mAttentRating.getRating()==0.0f
                ||mCareRating.getRating()==0.0f || mStudioRating.getRating()==0.0f)
            //make toast notifying of incomplete result added
            Toast.makeText(this, "Update all result categories to continue",Toast.LENGTH_LONG );
        else{
            //save ratings for that student and reset the ratingBars
            mSelectedChild.setPartRating(mPartRating.getRating());
            mSelectedChild.setAttentRating(mAttentRating.getRating());
            mSelectedChild.setCareRating(mCareRating.getRating());
            mSelectedChild.setStudioRating(mStudioRating.getRating());

            //update the Database with the new results for that child
            mDb.updateChild(mSelectedChild);

            //clear all ratingBars for next time used
            mPartRating.setRating(0.0f);
            mAttentRating.setRating(0.0f);
            mCareRating.setRating(0.0f);
            mStudioRating.setRating(0.0f);

            //take user back to Main Activity
            this.finish();
        }
    }
}

