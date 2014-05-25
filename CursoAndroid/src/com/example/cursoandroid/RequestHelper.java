package com.example.cursoandroid;
//NetUtils.RequestURL("http://api.geoname.org/findNearbyPlaceNameJSON?username=cursoandroiddicis&lat="+loc.getLatitude()+"&lng="+loc.getLongitude());
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;

public class RequestHelper extends AsyncTask{
	
	private String murl, mresult;
	private Context mctx;
	public RequestHelper(String URL, Context ctx){
		this.murl=URL;
		this.mctx=ctx;
	}

	@Override
	protected Object doInBackground(Object... arg0) {
		String result1="";
		try {
			result1=NetUtils.RequestURL(murl);
		} catch (MalformedURLException e) {
			result1="";
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			result1="";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return result1;
	}
	
	private String parseJSONString (String JSONString) throws JSONException{
		String parsedJson="";
		
		JSONObject geonames = new JSONObject(JSONString);
		JSONArray geonamesArray = geonames.getJSONArray("geonames");
		JSONObject geonames0= geonamesArray.getJSONObject(0);
		
		parsedJson = geonames0.getString("name")+","+geonames0.getString("adminName1")+","+geonames0.getString("countryName");
		
		return parsedJson;
	}
	
	
	@Override
	protected void onPostExecute(Object result){
		this.mresult=(String) result;
        String geoReverseString= "";
        
        try {
			geoReverseString= this.parseJSONString(mresult);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("curso",e.getMessage());
		}
	    Log.i("curso",getMresult());
	    
	    Activity menuActivity = (Activity) this.mctx;
		TextView tvGeo = (TextView) menuActivity.findViewById(R.id.tvgeoReverse);
		tvGeo.setText(geoReverseString);
		
		ProgressBar bar = (ProgressBar) menuActivity.findViewById(R.id.progressBar);
		bar.setVisibility(View.GONE);
	}
	

	public String getMresult() {
		return mresult;
	}

	

}
