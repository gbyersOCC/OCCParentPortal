package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * @author Grant Byers
 * Class catches intent provided by the RssRecyclerAdapter (which is the link of the RSS item being chosen)
 * Class inflate a WebView and loads the link that it received into that WebView
 */
public class NewsDetailsActivity extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details_layout);

        //the layout for this is a FrameLayout that holds a WebView only
        mWebView = (WebView) findViewById(R.id.news_webView);

        //receive the link from RSS activity with getIntent() using the link as what to set(load) the WebView with
        String sentLink = getIntent().getExtras().getString("Link");

        mWebView.loadUrl(sentLink);
    }
}
