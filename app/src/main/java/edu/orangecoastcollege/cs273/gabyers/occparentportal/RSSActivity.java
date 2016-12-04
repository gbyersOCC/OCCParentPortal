package edu.orangecoastcollege.cs273.gabyers.occparentportal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

    public class RSSActivity extends AppCompatActivity {
    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_feed_recycle_layout);

        mRecycler = (RecyclerView) findViewById(R.id.rss_recycler);

        RSSReader reader = new RSSReader(this, mRecycler);

        reader.execute();
    }
}
