package com.NGPz44.camera_0z44;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class GuardarArchivo {
public static final int VIDEO=2;
public static final int IMAGEN=1;

GuardarArchivo(int tipo){
	if(tipo==1){
		getOutputMediaFile(IMAGEN);
	}else{
		if(tipo==2){
			getOutputMediaFile(VIDEO);
		}
	}
}

private  Uri getOutputMediaFileUri(int type){
    return Uri.fromFile(getOutputMediaFile(type));
}

public  File getOutputMediaFile(int type){
	File mediaFile=null;
	if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
		if (!Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())){
			Log.i("Camera_0z44", "hay permiso de escribir");
		}
		File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES), "Camera_0z44");
	
	if (! mediaStorageDir.exists()){
        if (! mediaStorageDir.mkdirs()){
            Log.i("camera_0z44", "failed to create directory");
            return null;
        }
    }
	
	
	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    
    if (type == IMAGEN){
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
        "IMG_"+ timeStamp + ".jpg");
    } else if(type == VIDEO) {
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
        "VID_"+ timeStamp + ".mp4");
       
    } else {
        return null;
    }

   
	}else{
		 Log.i("camera_0z44", "Memoria externa no montada");
		 return null;
	}
	 return mediaFile;
	}
	
}



