package edu.orangecoastcollege.cs273.gabyers.occparentportal;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TeacherMainActivity extends AppCompatActivity {
//hello
    private DBHelper mDatabase;
    private List<Teacher> teacherList;
    private List<Teacher> teacherListB;

    //interface widgets
    private ImageView picImageView;
    private TextView nameTextView;
    private Button profileDetailsButton;
    private Button addDRDPButton;

    //next two buttons have no name set in interface
    private Button noNameButton;
    private Button noNameButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        //obtain reference to all user widgets
        picImageView = (ImageView) findViewById(R.id.imageView);
        nameTextView = (TextView) findViewById(R.id.name_text_view);
        profileDetailsButton = (Button) findViewById(R.id.profile_details_button);
        addDRDPButton = (Button) findViewById(R.id.add_drdp_button);

        //(Temp)create a list of Teachers for the testing of DBHelper methods
        teacherList = new ArrayList<>();

        Teacher teacher = new Teacher();
        Teacher teacherb = new Teacher();
        Teacher teacherc = new Teacher();
        Teacher teacherd = new Teacher();

        teacher.setNameFirst("James");
        teacherb.setNameLast("Timmons");
        teacherb.setNameFirst("Sharon");
        teacherb.setNameLast("Tate");
        teacherc.setNameFirst("Jimmy");
        teacherc.setNameLast("Johnson");
        teacherd.setNameFirst("Charlie");
        teacherd.setNameLast("Swanson-Johnson");

        Uri tempUri = getUriToResource(this, R.drawable.doomflh);
        teacher.setImagePath(tempUri);
        teacherb.setImagePath(tempUri);
        teacherc.setImagePath(tempUri);
        teacherd.setImagePath(tempUri);

        teacherList.add(teacher);
        teacherList.add(teacherb);
        teacherList.add(teacherc);
        teacherList.add(teacherd);

        //now add them to a fresh database
        this.deleteDatabase(DBHelper.DATABASE_NAME);

        mDatabase = new DBHelper(this);

        mDatabase.addTeacher(teacherList.get(0));
        mDatabase.addTeacher(teacherList.get(1));
        mDatabase.addTeacher(teacherList.get(2));
        mDatabase.addTeacher(teacherList.get(3));

        teacherListB = new ArrayList<>();

        teacherListB = mDatabase.getAllTeachers();


    }
    public void pickStudentClick(View view){

        //load activity that inflates a list view and allows user to pick a student from it
        Intent pickStudentIntent = new Intent(this, ChooseStudentActivity.class);
        startActivity(pickStudentIntent);
    }
    public void viewParentRSSFeed(View view){
        Intent RSSFeedGlue = new Intent(this, RSSActivity.class);
        startActivity(RSSFeedGlue);
    }
    /**
     * Method builds a correct Uri type image path to picture
     * @param context
     * @param resId
     * @return
     * @throws Resources.NotFoundException
     */
    public static Uri getUriToResource(@NonNull Context context, @AnyRes int resId)throws Resources.NotFoundException{
        Resources res = context.getResources();
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+
                "://" + res.getResourcePackageName(resId)+
                '/'+res.getResourceTypeName(resId)+
                '/'+res.getResourceEntryName(resId));
    }
}
