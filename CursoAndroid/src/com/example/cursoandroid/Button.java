package com.example.cursoandroid;

import android.util.Log;

public class Button extends View implements onClickListener{
	private String text;
	private boolean isCheckbox;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isCheckbox() {
		return isCheckbox;
	}
	public void setCheckbox(boolean isCheckbox) {
		this.isCheckbox = isCheckbox;
	}
	@Override
	public void onclick() {
		Log.i("curso","click!!!");
		
	}

	
}
