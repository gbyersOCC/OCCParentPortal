package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by gabye on 12/3/2016.
 */
public class RSSReader extends AsyncTask<Void,Void,Void> {
    private Context mContext;
    private ProgressDialog mDialog;
    private ArrayList<ParentFeedItem> mAllFeedItemsList;

    private static final String TAG = "RSSReader.java";
    //string URL to website containing the ReallySimpleSyndication elements
    private static final String  RSS_URL = " http://www.si.com/rss/si_topstories.rss";
    //"http://www.parentingwithdignity.com/rss/parenting_advice.xml";

    private RecyclerView mRecycler;
    private URL mUrl;


    public RSSReader(Context context, RecyclerView recycler) {
        mRecycler = recycler;
        mContext = context;
        mDialog = new ProgressDialog(mContext);
        mDialog.setMessage("Loading Parenting News RSS Feed...");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mDialog.dismiss();
        //instantiate RecyclerAdapter class
        RssRecyclerAdapter recyclerAdapter = new RssRecyclerAdapter(mContext, mAllFeedItemsList);
        //must set LayoutManager attribute
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        //now hook up recyclerView to adapter
        mRecycler.setAdapter(recyclerAdapter);

    }

    @Override
    protected Void doInBackground(Void... params) {

        extractXMLDocument(this.getDocumentFromUrl(RSS_URL));


        return null;
    }
    private void extractXMLDocument(Document document){
        if(document != null)
        {

            //array list ofr the feed items
             mAllFeedItemsList = new ArrayList<ParentFeedItem>();

            Element rootElement = document.getDocumentElement();
            Node channel = rootElement.getChildNodes().item(1);
            NodeList AllElements = channel.getChildNodes();

            for(int i= 0; i< AllElements.getLength(); i++){
                Node singleElement = AllElements.item(i);
                //now loop thru single element
                if(singleElement.getNodeName().equalsIgnoreCase("ITEM")){
                    ParentFeedItem feedItem = new ParentFeedItem();

                    NodeList itemChilds = singleElement.getChildNodes();

                    for(int j =0; j < itemChilds.getLength(); j++)
                    {
                        Node currentChild = itemChilds.item(j);
                        if(currentChild.getNodeName().equalsIgnoreCase("title")){
                            feedItem.setTitle(currentChild.getTextContent());
                        }else if(currentChild.getNodeName().equalsIgnoreCase("link"))
                            feedItem.setLink(currentChild.getTextContent());
                        else if(currentChild.getNodeName().equalsIgnoreCase("pubDate"))
                            feedItem.setPubDate(currentChild.getTextContent());
                        else if(currentChild.getNodeName().equalsIgnoreCase("description"))
                            feedItem.setDescription(currentChild.getTextContent());
                        else if(currentChild.getNodeName().equalsIgnoreCase("media:thumbnail")){
                            String url = currentChild.getAttributes().item(0).getTextContent();
                            feedItem.setThumbnailUrl(url);
                        }


                        //Log.d(TAG, currentChild.getTextContent());
                    }
                    mAllFeedItemsList.add(feedItem);
                    Log.d("item title = ", feedItem.getTitle());
                    Log.d("item Description = ", feedItem.getDescription());
                    Log.d("item Publishing date = ", feedItem.getPubDate());
                    Log.d("item Link = ", feedItem.getLink());
                    Log.d("Item image URL: ", feedItem.getThumbnailUrl());
                }
            }
        }
        //Log.d(TAG + "RoooT",document.getDocumentElement().getNodeName() );
    }
    private Document getDocumentFromUrl(String url){
        //pass the urlString into the URL object
        try{
            mUrl = new URL(url);

            //create new HTTPConnection object out of the URL String
            HttpURLConnection httpConnect = (HttpURLConnection) mUrl.openConnection();
            httpConnect.setRequestMethod("GET");
            //make inputstream out of connection
            InputStream inStream = httpConnect.getInputStream();

            //create doc builder factory Obj
            DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder socBuilder = docBuildFactory.newDocumentBuilder();
            Document urlDocument = socBuilder.parse(inStream);
            return urlDocument;

        }catch(MalformedURLException exception){
            Log.e(TAG, "Error Creating URL obj from URl String");
            return null;
        }catch(IOException ex){
            Log.e(TAG,"Error creating HttpUrlConnection");
            return null;
        }catch(ParserConfigurationException exception){
            Log.e(TAG, "Problem with DOcument Builder or its Factory Class");
            return null;
        }catch(SAXException ex){
            Log.e(TAG, "SAX error");
            return null;
        }
    }
}

