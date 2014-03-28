package com.example.cursoandroid;



import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream; 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream; 
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL; 
import java.net.URLConnection; 
import java.net.URLEncoder;

public class NetUtils {

	
	public static String RequestURL(String url) throws IOException, MalformedURLException 
	{
		/* Will be filled and displayed later. */ 
        String response = null; 
	     /* Define the URL we want to load data from. */ 
	     URL myURL = new URL(url); 
	     /* Open a connection to that URL. */ 
	     URLConnection ucon = myURL.openConnection(); 
	
	     /* Define InputStreams to read 
	      * from the URLConnection. */ 
	     InputStream is = ucon.getInputStream(); 
	     BufferedInputStream bis = new BufferedInputStream(is); 
	      
	     /* Read bytes to the Buffer until 
	      * there is nothing more to read(-1). */ 
	     ByteArrayBuffer baf = new ByteArrayBuffer(50); 
	     int current = 0; 
	     while((current = bis.read()) != -1){ 
	          baf.append((byte)current); 
	     } 
	
	     /* Convert the Bytes read to a String. */ 
	     response = new String(baf.toByteArray()); 
       
	     return response;
	}
	
	
}
