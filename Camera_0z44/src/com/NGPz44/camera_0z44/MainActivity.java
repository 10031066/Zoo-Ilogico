package com.NGPz44.camera_0z44;

import java.io.IOError;
import java.io.IOException;

import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	private Camara cam;
	private boolean flag=false;
	boolean bandera=false;
	private Preview mPreview;
	private Camera mCamara;
	private MediaRecorder mMediarecorder;
	private boolean grabando=false;
	FrameLayout VP;
	View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

	        setContentView(R.layout.activity_main);
		Log.i("Camera_0z44","En el onCreate");
		if(bandera){
	    	Log.i("Camera_0z44","PTM");
	    }
		cam=new Camara();
		mCamara=cam.InstanciarCamara();
		
		mPreview=new Preview(this,mCamara);
	    
		 VP = (FrameLayout) findViewById(R.id.vista);
		 view = getLayoutInflater().inflate(R.layout.panel_layout, null,false);
			
		VP.addView(mPreview);
		
		VP.addView(view);
		
		final ImageButton capture = (ImageButton) findViewById(R.id.imageButton1);
		capture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(grabando){
					Log.i("Camera_0z44","Cerrando grabacion"+grabando);
					mMediarecorder.stop();
					releaseMediaRecorder();
					mCamara.lock();
					
					capture.setImageResource(R.drawable.obturador);
					grabando=false;					
				}
				else{
					if(preparandograbacion()){
						Log.i("Camera_0z44","empezando a grabar");
						mMediarecorder.start();
						Log.i("Camera_0z44","mMedia.start()");
						capture.setImageResource(R.drawable.pressop);
						Log.i("Camera_0z44","cambio de boton");
						grabando=true;							
						
					}
					else{
						releaseMediaRecorder();
					}
				}
				
			}
		});
		
		
		
	}
	
	public boolean preparandograbacion(){
		
		//mCamara=cam.InstanciarCamara();
		mMediarecorder= new MediaRecorder();
		 GuardarArchivo outputfile =new GuardarArchivo(2);
		mCamara.unlock();
		
		mMediarecorder.setCamera(mCamara);
		
		mMediarecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
		Log.i("Camera_0z44","Se definio el audiosourse");
		mMediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		Log.i("Camera_0z44","Se definio el videosourse");
		
		mMediarecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
		Log.i("Camera_0z44","Se definio el profile");
		
	    mMediarecorder.setOutputFile(outputfile.getOutputMediaFile(2).getAbsolutePath());
	    Log.i("Camera_0z44","Se definio el outputfile");
	    Log.i("Camera_0z44",outputfile.getOutputMediaFile(2).getAbsolutePath());
	   // mPreview.mCamara.stopPreview();
	    Log.i("Camera_0z44","se paro el preview de la camara");
	    mMediarecorder.setPreviewDisplay(mPreview.getHolder().getSurface());
	    Log.i("Camera_0z44","Se inicia preview en el mediarecorder");
	    
	    try {
	    	
	        mMediarecorder.prepare();
	        Log.i("Camera_0z44","Se ejecuto prepare()");
	    } catch (IllegalStateException e) {
	        Log.d("Camera_0z44", "IllegalStateException preparing MediaRecorder: " + e.getMessage());
	        
	        releaseMediaRecorder();
	        return false;
	    } catch (IOException e) {
	        Log.d("Camera_0z44", "IOException preparing MediaRecorder: " + e.getMessage());
	        releaseMediaRecorder();
	        return false;
	    }
	    
	    return true;
	
		
	}
	
	private void releaseMediaRecorder(){
        if (mMediarecorder != null) {
            mMediarecorder.reset();   
            mMediarecorder.release(); 
            mMediarecorder = null;
            mCamara.lock();           
        }
    }
	private void releaseCamera(){
        if (mCamara != null){
        	
        	mCamara.stopPreview(); 
	        mCamara.setPreviewCallback(null);
	        mPreview.getHolder().removeCallback(mPreview);

    		mCamara.release();
            mCamara = null;
        }
    }
	
	@Override
	 protected void onPause() {
	        super.onPause();
	       
	        Log.d("Camera_0z44", "Entro en pausa");
	        flag=true;
	        
	        releaseMediaRecorder();    
	       releaseCamera();             
	}
	
	@Override
	public void onResume(){
		super.onResume();
		 Log.d("Camera_0z44", "onResume");
	     if(flag){
	    	 Log.d("Camera_0z44", "en el if");
	    	
	     cam=null;
	     cam= new Camara();
	     mCamara=cam.InstanciarCamara();
	    
		 mPreview= null;
		 mPreview= new Preview(this, mCamara);
		 VP.removeAllViews();
		 VP.addView(mPreview);
			
	     VP.addView(view);
		 
		 
	     }
	     Log.d("Camera_0z44", "salio del if del resume"); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
