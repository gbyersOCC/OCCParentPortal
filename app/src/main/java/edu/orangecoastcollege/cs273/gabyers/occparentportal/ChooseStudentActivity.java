package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;


/**
 * Controller class that allows user to pick students from a List
 */
public class ChooseStudentActivity extends ListActivity{

    //user interface widgets
    private ListView mChooseStudentListView;

    private List<Child> mChildList;
    private Context mContext = ChooseStudentActivity.this;
    private DBHelper mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get the DBHelper object
        mDb = new DBHelper(this);

        //for testing: this can be removed if you want database to keep Pet records
 

        mChooseStudentListView = (ListView)findViewById(R.id.choose_student_listView);


        setListAdapter(new StudentListAdapter(mContext, R.layout.student_list_item_layout, mChildList));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //implement method
    }


}
