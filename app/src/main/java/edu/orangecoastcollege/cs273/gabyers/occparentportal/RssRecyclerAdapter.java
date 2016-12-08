package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * @author Grant Byers
 * This RecyclerView class inflates a list view style widget that is used to recycle inflated view.
 * It will build a list of News articles (the rss feed) by using data structerd with ParentFeedItem and inflating it with
 * rss_feed_item.xml. The layout including this Recycler class is rss_feed_recycle_layout.xml
 */

public class RssRecyclerAdapter extends RecyclerView.Adapter<RssRecyclerAdapter.MyViewHolder> {
    private ArrayList<ParentFeedItem> mFeedList;
    private Context mContext;

    //MyAdapter constructor passing Context and ArrayList variables.
    public RssRecyclerAdapter(Context context, ArrayList<ParentFeedItem> feedList)
    {
        mContext = context;
        mFeedList = feedList;
    }

    /**
     * Method creates a ViewHolder with the View that inflates the rss_feed_item layout.
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RssRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rss_feed_item, parent, false);
        //Needs a ViewHolder object to hold that view
        MyViewHolder viewHolder = new MyViewHolder(view);
        //return holder
        return viewHolder;
    }

    /**
     *Inner Class extends RecyclerView.ViewHolder. This class Gets a reference to all the feed_item widgets
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView rssCardView;
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView dateTextView;
        private ImageView thumbnail;

        private MyViewHolder(View itemView) {
            super(itemView);
            //wire up widgets(remember to use itemView as needed static reference
            rssCardView = (CardView)itemView.findViewById(R.id.rss_cardView);
            titleTextView = (TextView) itemView.findViewById(R.id.title_textView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description_textView);
            dateTextView = (TextView) itemView.findViewById(R.id.date_textView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail_imageView);
        }
    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }

    //now bind the view that was just wired up in this method(with each provided position)
    @Override
    public void onBindViewHolder(RssRecyclerAdapter.MyViewHolder holder, int position) {
        final ParentFeedItem currentItem = mFeedList.get(position);

        //can access all views using holder (holder is the ViewHolder)
        holder.titleTextView.setText(currentItem.getTitle());
        holder.descriptionTextView.setText(currentItem.getDescription());
        holder.dateTextView.setText(currentItem.getPubDate());
        //follow the code syntax that the nice folks at picasso provided
        Picasso.with(mContext).load(currentItem.getThumbnailUrl()).into(holder.thumbnail);
        //Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);

//        Just like play Yo-yo.
//
//                YoYo.with(Techniques.Tada)
//                .duration(700)
//                .playOn(findViewById(R.id.edit_area));
        //remember to access with holder
        YoYo.with(Techniques.RubberBand).duration(800).playOn(holder.rssCardView);

        //when the card view is clicked send the ink to NewsDetailsActivity
        holder.rssCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent rssDetailGlue = new Intent(mContext, NewsDetailsActivity.class);

                rssDetailGlue.putExtra("Link", currentItem.getLink());

                //start activity but because inside of inner class yous context
                mContext.startActivity(rssDetailGlue);
            }
        });

    }
}
