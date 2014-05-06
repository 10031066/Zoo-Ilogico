package com.itc.machozoo2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;

public class RejaSprite extends Figura {
	/*
	 Position es el lugar donde la reja esta ubicada dentro de la celda del mapa
	 1=left  3= right 
	 2=Top   4=bottom
	 0=nada
	 */
	
	private int x = 500;
	private int position=0;
	private Paint p1 = new Paint();
	private RejaSprite anterior=null, siguiente=null;
    private boolean fija = false;
    private int Salud=1000;
    
	public RejaSprite(GameView gameView, Bitmap bmp, int id,int tipo) {
		this.id=id;
		this.bmp = bmp;
		this.tipo = tipo;
        width=bmp.getWidth()/2;
        height=bmp.getHeight();
		dst = new Rect(470, 0, 500, 250);
	}

	private void update() {

	}

	public void onDraw(Canvas canvas) {
		update();

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
	
	public void set_siguiente(RejaSprite s){
		siguiente=s;
	}
	public RejaSprite get_siguiente(){
		return siguiente;	
	}
	
	public void set_anterior(RejaSprite a){
		anterior=a;
	}
	public RejaSprite get_anterior(){
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
