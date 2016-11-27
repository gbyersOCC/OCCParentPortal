package edu.orangecoastcollege.cs273.gabyers.occparentportal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by gabye on 11/26/2016.
 */

public class StreamClassVideo extends Activity{

    VideoView videoView;
    ProgressDialog progressDlog;

    String URL = "http://androidbegin.com/tutorial/AndroidCommercial.3gp";
    private static final String TAG = "StreamClassVideo.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stream_class_video_view);

        //get reference for Video View and ProgressDialog
        videoView = (VideoView) findViewById(R.id.videoView);
        progressDlog = new ProgressDialog(StreamClassVideo.this);

        progressDlog.setTitle("Live Class Stream");

        progressDlog.setMessage("Buffering...");

        progressDlog.setIndeterminate(false);

        progressDlog.setCancelable(false);

        progressDlog.show();

        try{
            MediaController controller = new MediaController(this);

            controller.setAnchorView(videoView);

            Uri video = Uri.parse(URL);

            videoView.setMediaController(controller);

            videoView.setVideoURI(video);

        }catch(Exception ex){
            Log.e(TAG, "Error creating MediaPlayer");
        }

        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDlog.dismiss();
                videoView.start();
            }
        });
    }
}
