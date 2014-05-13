package com.itc.machozoo2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Paint.Style;

public class Boton extends Figura{
	
	public Boton(GameView gameView, Bitmap bmp, int id,int tipo){
		this.id=id;
		this.bmp = bmp;
		this.tipo = tipo;
        width=bmp.getWidth();
        height=bmp.getHeight();
		dst = new Rect(0, 250, 250, 500);
		src = new Rect(0, 0, width, height);
	}
	
	public void onDraw(Canvas canvas) {
		canvas.drawBitmap(bmp, src, dst, null);
	}
}
