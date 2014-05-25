package com.example.cursoandroid;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.ProgressBar;

public class PostalesRequestHelper extends AsyncTask{

	private String url="http://api.geonames.org/postalCodeSearchJSON?username=cursoandroiddicis&country=MX";
	private String mCP;
	private Context mctx;
	public PostalesRequestHelper(String CP, Context ctx){
		this.mCP=CP;
		this.mctx=ctx;
	}
	
	private ArrayList<String> parseJSONString(String jsonstring) throws JSONException{
		
		ArrayList <String> listaColonias = new ArrayList<String>();
		
		JSONObject rootObject = new JSONObject(jsonstring);
		JSONArray postalCodeArray = rootObject.getJSONArray("postalCodes");
		
		for(int i=0; i<postalCodeArray.length();i++){
			listaColonias.add(postalCodeArray.getJSONObject(i).getString("placeName"));
		}
		
		return listaColonias;
	}
	@Override
	protected Object doInBackground(Object... arg0) {
		String result="";
		try {
			Log.e("curso",url+"&postalcode="+this.mCP);
			result=NetUtils.RequestURL(url+"&postalcode="+this.mCP);
		} catch (MalformedURLException e) {
			 Log.e("curso",e.getMessage());
		} catch (IOException e) {
			 Log.e("curso",e.getMessage());
		}
		
		return result;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		
		String res = (String) result;
		ArrayList<String> lista=null;
		
		try {
			 lista = this.parseJSONString(res);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("curso", e.getMessage());
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String> (mctx,android.R.layout.simple_list_item_1,lista);
		Activity postalActivity = (Activity) this.mctx;
		ListView lvPostal = (ListView) postalActivity.findViewById(R.id.LVPostal);
		lvPostal.setAdapter(adapter);
		
		ProgressBar bar = (ProgressBar) postalActivity.findViewById(R.id.progressBarPostal);
		bar.setVisibility(View.GONE);
		
		
		
		
		
	}
	

}
