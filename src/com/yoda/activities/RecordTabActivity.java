package com.yoda.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.yoda.rec.R;

public class RecordTabActivity extends Activity {
	String title;
	float duration;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.record_options_view);
        
        final EditText videoTitle = (EditText) findViewById(R.id.videoTitle);
        final EditText videoDuration = (EditText)	findViewById(R.id.videoDuration);
        
        
        Button recordButton = (Button) findViewById(R.id.recordButton);
        recordButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				System.out.println("Title: " + (String) videoTitle.getText().toString());
		        System.out.println("Duration: " + (String) videoDuration.getText().toString());
		        if (!videoTitle.getText().toString().equals("") && !videoTitle.getText().toString().equals(null))
		        {
		        	title = (String) videoTitle.getText().toString() + ".mp4";
		        }
		        else
		        {
		        	title = "default" + System.currentTimeMillis() + ".mp4";
		        }
		        
		        if (!videoDuration.getText().toString().equals("") && !videoDuration.getText().toString().equals(null))
		        {
		        	duration = Float.parseFloat(videoDuration.getText().toString());
		        }
		        else
		        {
		        	duration = 2;
		        }
		        
		        Intent intent = new Intent(RecordTabActivity.this, CameraActivity.class);
				intent.putExtra("videoTitle", title);
				intent.putExtra("videoDuration", duration);
				videoTitle.setText("");
				videoDuration.setText("");
				videoTitle.requestFocus();
	            startActivity(intent);
			}
		});
	}
}
