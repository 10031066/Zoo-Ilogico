package com.itc.machozoo2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public abstract class Figura{
	Canvas canvas;
	Bitmap bmp;
	int width;
	int height;
	int x;
	int tipo;//El tipo 3 son los botones
			 //El tipo 4 con los Animales Dociles
			 //El tipo 5 son los Animales Salvajes
			 //El tipo 6 son las rejas
			 //El tipo 7 es la carne
			 //El tipo 8 son los Visitantes
	int y;
	int id;
	Rect dst, src;
	
	public Bitmap get_btm (){
		return bmp;
	}
	public void set_btm(Bitmap b){
		bmp=b;
	}
	
	public int get_width(){
		return width;
	}
	public void set_width(int w){
		width=w;
	}
	
	public int get_height(){
		return height;
	}
	public void set_heigth(int h){
		height=h;
	}
	
	public int get_id(){
	    return id;
	}
	
	public int get_x(){
		return x;
	}
	public void set_x(int x){
		this.x=x;
	}
	
	public int get_y(){
		return y;
	}
	public void set_y(int y){
		this.y=y;
	}
	
	public Rect get_dst(){
		return dst;
	}
	public int get_tipo(){
		return tipo;
	}
	
	
	public void onDraw(Canvas canvas){
		
	}
	
}
