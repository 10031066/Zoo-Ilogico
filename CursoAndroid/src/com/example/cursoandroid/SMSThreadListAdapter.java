package com.example.cursoandroid;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SMSThreadListAdapter extends ArrayAdapter<Message> {

	private ArrayList <Message> messages;
	private Context ctx;
	public SMSThreadListAdapter(Context context,int textViewResourceId, ArrayList <Message> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		messages=objects;
		ctx = context;
	}

	@Override
	public View getView  ( int position, View convertView, ViewGroup parent){
		View v = convertView;
		if(v==null){
			LayoutInflater vi = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v=vi.inflate(R.layout.item_list_sms, null);
		}
		Message temp = messages.get(position);
		TextView tvnumero = (TextView) v.findViewById(R.id.tvNumber);
		TextView tvFecha = (TextView)v.findViewById(R.id.tvfecha);
		TextView tvTexto = (TextView)v.findViewById(R.id.tvmsg);
		
		tvnumero.setText(temp.getNumber_source());
		tvTexto.setText(temp.getText());
		tvFecha.setText(temp.getDate());
		
		return v;
	}
}
