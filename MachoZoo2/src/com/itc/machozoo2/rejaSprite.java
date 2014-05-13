package com.itc.machozoo2;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

public class rejaSprite extends Figura{
	private int x = 500;
	private int position=0;
	private Paint p1 = new Paint();
	private rejaSprite anterior=null, siguiente=null;
    private boolean fija = false;
    private int Salud=1000;
    
	public rejaSprite(GameView gameView, Bitmap bmp, int id,int tipo) {
		this.id=id;
		this.bmp = bmp;
		this.tipo = tipo;
        width=bmp.getWidth()/2;
        height=bmp.getHeight();
		dst = new Rect(470, 0, 500, 250);
	}


	public void onDraw(Canvas canvas) {

		src = new Rect(0, 0, width, height);

		p1.setARGB(255, 255, 0, 0);
		p1.setStyle(Style.STROKE);
		canvas.drawRect(dst, p1);
		canvas.drawBitmap(bmp, src, dst, null);
	}

	public void set_position(int p){
		position=p;
	}
	public int get_position (){
		return position;	
	}

	public void set_siguiente(rejaSprite s){
		siguiente=s;
	}
	public rejaSprite get_siguiente(){
		return siguiente;	
	}

	public void set_anterior(rejaSprite a){
		anterior=a;
	}
	public rejaSprite get_anterior(){
		return anterior;
	}

	public void fijar(){
		fija=true;
	}
    public boolean get_fija(){
    	return fija;
    }
    public int get_salud(){
    	return Salud;
    }
    public void set_salud(int s){
    	Salud=s;
    }
}
