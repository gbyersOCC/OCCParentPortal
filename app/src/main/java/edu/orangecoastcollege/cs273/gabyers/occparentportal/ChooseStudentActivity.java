package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.AnyRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Controller class that allows user to pick students from a List
 */
public class ChooseStudentActivity extends ListActivity{

    //user interface widgets
    private ListView mChooseStudentListView;

    //childList will need xhanged to hold Child object <child>
    private List<Parent> mChildList;
    private Context mContext = ChooseStudentActivity.this;
    private DBHelper mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get the DBHelper object
        mDb = new DBHelper(this);

        mDb.addParent(new Parent("userName.66","Billy", "Vaugn","","",getUriToResource(this, R.drawable.doomflh),""));
        mDb.addParent(new Parent("userName.66","Billy", "Vaugn","","",getUriToResource(this, R.drawable.doomflh),""));
        mDb.addParent(new Parent("userName.66","Billy", "Vaugn","","",getUriToResource(this, R.drawable.doomflh),""));
        mDb.addParent(new Parent("userName.66","Billy", "Vaugn","","",getUriToResource(this, R.drawable.doomflh),""));
        mDb.addParent(new Parent("userName.66","Billy", "Vaugn","","",getUriToResource(this, R.drawable.doomflh),""));
 

        mChooseStudentListView = (ListView)findViewById(R.id.choose_student_listView);

        mChildList = new ArrayList<>();

        mChildList = mDb.getAllParents();

        setListAdapter(new StudentListAdapter(mContext, R.layout.student_list_item_layout, mChildList));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //upon child object clicked, send that to AddDRDPActivity

        //this will need switched back to child
        Parent selectedChild =  mChildList.get(position);

        Intent addPerformaceGlue = new Intent(ChooseStudentActivity.this, AddPerformanceRatings.class);

        addPerformaceGlue.putExtra("ChildOBJ", selectedChild);

        startActivity(addPerformaceGlue);
    }

    public static Uri getUriToResource(@NonNull Context context, @AnyRes int resId)throws Resources.NotFoundException{
        Resources res = context.getResources();
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+
                "://" + res.getResourcePackageName(resId)+
                '/'+res.getResourceTypeName(resId)+
                '/'+res.getResourceEntryName(resId));
    }
}
