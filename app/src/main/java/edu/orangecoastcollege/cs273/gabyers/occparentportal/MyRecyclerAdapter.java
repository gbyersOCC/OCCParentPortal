package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by gabye on 12/3/2016.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private ArrayList<ParentFeedItem> mFeedList;
    private Context mContext;

    //dont forget MyAdapter constructor
    public MyRecyclerAdapter(Context context, ArrayList<ParentFeedItem> feedList)
    {
        mContext = context;
        mFeedList = feedList;
    }
    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rss_feed_item, parent, false);
        //Needs a ViewHolder object to hold that view
        MyViewHolder viewHolder = new MyViewHolder(view);
        //return holder
        return viewHolder;
    }



    @Override
    public int getItemCount() {
        return mFeedList.size();
    }
//this is method that actually wires up the item widgets on rss_feed_item to the Adapter which will
// be slapped onto a RecyclerView
    public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView titleTextView;
    TextView descriptionTextView;
    TextView dateTextView;
    ImageView thumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
            //wire up widgets(remember to use itemView as needed static reference
            titleTextView = (TextView) itemView.findViewById(R.id.title_textView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description_textView);
            dateTextView = (TextView) itemView.findViewById(R.id.date_textView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail_imageView);
        }
    }
    //now bind the view that was just wired up in this method(with each provided posistion)
    @Override
    public void onBindViewHolder(MyRecyclerAdapter.MyViewHolder holder, int position) {
ParentFeedItem currentItem = mFeedList.get(position);
    //can access all views from constructor by using holder
        holder.titleTextView.setText(currentItem.getTitle());
        holder.descriptionTextView.setText(currentItem.getDescription());
        holder.dateTextView.setText(currentItem.getPubDate());
        //follow the code syntax that the nice folks at picasso provided
        Picasso.with(mContext).load(currentItem.getThumbnailUrl()).into(holder.thumbnail);
        //Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
    }
}
