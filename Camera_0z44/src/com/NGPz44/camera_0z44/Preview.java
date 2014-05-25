package com.NGPz44.camera_0z44;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Preview extends SurfaceView implements SurfaceHolder.Callback{
	Camera mCamara;
	private SurfaceHolder mHolder;
	private String TAG = "NGPz44";
	Preview(Context contx, Camera Camara){
		super(contx);
		
		
		mCamara=Camara;
		
		mHolder = getHolder();
        mHolder.addCallback(this);
        
        
	}


	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.i("Camera_0z44","surfaceChanged");
		// TODO Auto-generated method stub
		
	}


	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try{
		
		mCamara.setPreviewDisplay(holder);
		mCamara.startPreview();
	
		}
		catch(IOException e){
			Log.i(TAG, "Error setting camera preview: " + e.getMessage());
		}
		
	}


	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.i("Camera_0z44","surfaceDestroyed");
		
		//mCamara.stopPreview();
		mCamara.release();
		// TODO Auto-generated method stub
		
	}

}
