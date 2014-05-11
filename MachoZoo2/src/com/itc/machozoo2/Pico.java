package com.itc.machozoo2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Pico extends Figura{
//estas dos variables representan la ubicacion de la punta del pico, cuando esta punta tenga con alguna reja
//se deberia borrar la reja
public int puntaX;
public int puntaY;

	GameView GV;

	public Pico (GameView gameView, Bitmap bmp,int tipo){
		this.tipo=tipo;
		this.bmp=bmp;
		this.width=bmp.getWidth();
		this.height=bmp.getHeight();
		dst = new Rect(0, 0, 45, 30);
		puntaX=dst.top+50;
		puntaY=dst.left;
		this.GV=gameView;
	}
	
	public void onDraw(Canvas canvas) {
      
        src = new Rect(0, 0, width, height);
        canvas.drawBitmap(bmp, src, dst, null);
	}
	
	public Jaula EliminaReja(reja Elimi,Jaula No1,Jaula No2){	// V 0.01 Agregando la funcionalidad del pico
		No1.rejas.remove(Elimi);
		No2.rejas.remove(Elimi);
		
		No1.rejas.addAll(No2.rejas);
		GV.Jaulas.remove(No2);
		return No1;
	}
}
