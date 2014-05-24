package com.itc.machozoo2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Visitante extends Sprite{
	//GameView GV;
	int salud=1;
	public Visitante(GameView GV){
		this.gameView=GV;
		this.bmp=gameView.bmp[16];
		GV.Figuras.add(this);
		this.BMP_ROWS=2;
		this.BMP_COLUMNS=6;
		this.width = (bmp.getWidth() / BMP_COLUMNS);// -67;
		this.height = bmp.getHeight() / BMP_ROWS;
		
		this.x=850;
		this.y=100;
		
		xSpeed = rnd.nextInt(10);
		ySpeed = rnd.nextInt(10) - 5;
		
		GV.gold.cantidad+=20;
		
		VisitanteCaminando Vis = new VisitanteCaminando(this);
	}
		
		
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawBitmap(bmp, src, dst, null);
	}
	
	public boolean comiendo(){
		return false;
	}
	
}
