package edu.orangecoastcollege.cs273.gabyers.occparentportal;

/**
 * Created by gabye on 12/3/2016.
 */

public class ParentFeedItem {
    private String mTitle;
    private String mDescription;
    private String mLink;
    private String mPubDate;
    private String mThumbnailUrl;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public void setPubDate(String pubDate) {
        mPubDate = pubDate;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        mThumbnailUrl = thumbnailUrl;
    }
}
