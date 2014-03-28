package com.example.cursoandroid2;

import android.util.Log;

public class Button extends View implements OnClickListener{

	private String text;
	private boolean isCheckBox;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isCheckBox() {
		return isCheckBox;
	}
	public void setCheckBox(boolean isCheckBox) {
		this.isCheckBox = isCheckBox;
	}
	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		Log.i("curso", "click!!!!");
		
	}
	
}
