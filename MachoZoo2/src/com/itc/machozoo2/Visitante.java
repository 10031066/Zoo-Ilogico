package com.itc.machozoo2;

import android.graphics.Canvas;

public class Visitante extends Sprite{
	GameView GV;
	
	public Visitante(GameView GV){
		this.GV=GV;
		GV.Figuras.add(this);
		this.x=850;
		this.y=100;
		
		xSpeed = rnd.nextInt(10);
		ySpeed = rnd.nextInt(10) - 5;
		
		GV.gold.cantidad+=10;
		
		crear(0,"Visitante",false,1,0,0,false,false,false,false,gameView.bmp[1]);//hay que cambiar el bmp al del visitante
		
		ActulizandoAnimales Act = new ActulizandoAnimales(this);
		start();
	}
	@Override
	public void run(){
		while(true){
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//agregar el src y el dst del visitante y su sprite
			
			
		}
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawBitmap(bmp, src, dst, null);
	}
}
