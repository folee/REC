package com.yoda.activities;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.yoda.adapters.VideoListAdapter;
import com.yoda.rec.R;
import com.yoda.utility.FileNameExtensionFilter;

public class VideosTabActivity extends Activity {
	
	ListView videoListView;
	File[] videoList;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.video_list_view);
        
        System.gc();
    	
        /*sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
    	String[] proj = {
        		MediaStore.Video.Media._ID,
        		MediaStore.Video.Media.DATA,
        		MediaStore.Video.Media.DISPLAY_NAME,
        		MediaStore.Video.Media.SIZE,
        		MediaStore.Video.Media.DURATION };
        System.out.println("Absolute Path: " + this.getExternalFilesDir(null).getAbsolutePath());
        Uri uriToAppDir = Uri.parse(this.getExternalFilesDir(null).getAbsolutePath());
        videocursor = managedQuery(uriToAppDir, proj, null, null, null);
        
        // System.out.println(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        // content://media/external/video/media
        
        File parentDirectory = new File("/mnt/sdcard/DreamIN");
        parentDirectory.mkdirs();*/
        
        refreshList();
        
        
        
        
        /*videocursor = managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);
        count = videocursor.getCount();*/
        
        // videolist.setAdapter(new VideoAdapter(getApplicationContext()));
        
    }
    
    private void refreshList()
    {
    	System.out.println(getApplicationContext().getExternalFilesDir(null).toString());
    	File parentDirectory = new File(getApplicationContext().getExternalFilesDir(null).toString());
        FileNameExtensionFilter extFilter = new FileNameExtensionFilter("mp4");
        videoList = parentDirectory.listFiles(extFilter);
        videoListView = (ListView) findViewById(R.id.list); 
        videoListView.setAdapter(new VideoListAdapter(getApplicationContext(), videoList));
        videoListView.setOnItemClickListener(videogridlistener);
    }
    
    private OnItemClickListener videogridlistener = new OnItemClickListener() {
    	@SuppressWarnings("rawtypes")
		public void onItemClick(AdapterView parent, View v, int position, long id) {
              System.gc();
              
              String filename = videoList[position].getAbsolutePath();
              Intent intent = new Intent(VideosTabActivity.this, ViewVideoActivity.class);
              intent.putExtra("videofilename", filename);
              startActivity(intent);
        }
    };
    
    public void onResume()
    {
    	super.onResume();
    	refreshList();
    }
    
}