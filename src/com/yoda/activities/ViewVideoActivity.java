package com.yoda.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.yoda.rec.R;

public class ViewVideoActivity extends Activity {
    private String filename;
    @Override
    public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          System.gc();
          Intent i = getIntent();
          Bundle extras = i.getExtras();
          filename = extras.getString("videofilename");
          
          setContentView(R.layout.video_view);
          //VideoView vv = new VideoView(getApplicationContext());
          VideoView vv = (VideoView) findViewById(R.id.video);
          
          vv.setVideoPath(filename);
          vv.setMediaController(new MediaController(this));
          vv.requestFocus();
          vv.start();
    }
}
