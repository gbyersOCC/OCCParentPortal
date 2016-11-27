package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class ChooseStudentActivity extends ListActivity{

    //user interface widgets
    private ListView mChooseStudentListView;

    private List<Child> mChildList;
    private Context mContext = ChooseStudentActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mChooseStudentListView = (ListView)findViewById(R.id.choose_student_listView);

        setListAdapter(new StudentListAdapter(mContext, R.layout.student_list_item_layout, mChildList));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //implement method
    }


}
