package com.NGPz44.camera_0z44;

import android.hardware.Camera;
import android.util.Log;

public class Camara {
	
	private  final String TAG="NGPz44";
	
 
	Camara(){
		
	}
	
	public  Camera InstanciarCamara(){
		Camera c= null;
		try{
			c=Camera.open();
		
		}
		catch(Exception camE){
			Log.i(TAG,"Sin acceso a la camara");
		}
		return c;
	}
	
	
}
