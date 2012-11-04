package com.yoda.activities;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import com.yoda.rec.R;

public class CameraActivity extends Activity implements OnClickListener,
		SurfaceHolder.Callback {
	Camera camera;
	MediaRecorder recorder;
	SurfaceHolder holder;
	boolean recording = false;
	String filename;
	float duration;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();

		filename = getApplicationContext().getExternalFilesDir(null).toString()
				+ "/" + extras.getString("videoTitle");
		duration = extras.getFloat("videoDuration");

		
		setContentView(R.layout.camera_view);

		SurfaceView cameraView = (SurfaceView) findViewById(R.id.camera);
		holder = cameraView.getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		cameraView.setClickable(true);
		cameraView.setOnClickListener(this);

	}

	private void prepareRecorder() {
		System.out.println("Number of cameras: " + Camera.getNumberOfCameras());
		
		recorder = new MediaRecorder();
		camera = Camera.open(CameraInfo.CAMERA_FACING_BACK);
				
		/*// Step 0: Secondary camera stuff
		Camera.Parameters parameters = camera.getParameters();
		parameters.set("camera-id", 2);
		parameters.setPreviewSize(640, 480); // or (800,480)
		camera.setParameters(parameters);*/
		
		// Step 1: Unlock and set camera to MediaRecorder
		camera.unlock();
		recorder.setCamera(camera);
		
		// Step 2: Set sources
		recorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
		recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		
		// Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
		recorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
		
		// Step 4: Set output file
		recorder.setOutputFile(filename);
		recorder.setMaxDuration((int) (duration * 1000)); // 50 seconds
		// recorder.setMaxFileSize(5000000); // Approximately 5 megabytes

		// Step 5: Set the preview output
		recorder.setPreviewDisplay(holder.getSurface());

		// Step 6: Prepare configured MediaRecorder
		try {
			recorder.prepare();
			recording = true;
			recorder.start();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			releaseMediaRecorder();
			finish();
		} catch (IOException e) {
			e.printStackTrace();
			releaseMediaRecorder();
			finish();
		}
	}

	public void onClick(View v) {
		if (recording) {
			recorder.stop();
			recording = false;
			releaseMediaRecorder();
			finish();
		}
	}
	
	private void releaseMediaRecorder(){
        if (recorder != null) {
        	recorder.reset();   // clear recorder configuration
        	recorder.release(); // release the recorder object
        	recorder = null;
            camera.lock();           // lock camera for later use
        }
    }

	public void surfaceCreated(SurfaceHolder holder) {
		prepareRecorder();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		if (recording) {
			recorder.stop();
			recording = false;
		}
		releaseMediaRecorder();
		finish();
	}
}
