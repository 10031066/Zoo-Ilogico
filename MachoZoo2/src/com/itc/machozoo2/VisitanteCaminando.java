package com.itc.machozoo2;

import android.graphics.Rect;

public class VisitanteCaminando extends Thread{
	Visitante Vis;
	public VisitanteCaminando(Visitante Vis){
		this.Vis= Vis;
		
		start();
	}
	
	@Override
	public void run(){
		while(true){
			
			int srcX = Vis.currentFrame * Vis.width;
			int srcY = Vis.getAnimationRow() * Vis.height;
			
			
			Vis.src = new Rect(srcX, srcY, srcX + Vis.width, srcY + Vis.height);
			Vis.dst = new Rect(Vis.x, Vis.y, Vis.x + Vis.width, Vis.y + Vis.height);
			//System.out.println(Vis.x+" "+ Vis.y+" "+ (Vis.x + Vis.width)+" "+ (Vis.y + Vis.height));
			
			update();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//agregar el src y el dst del visitante y su sprite
			
			
		}
	}
	
	void update() {
			if (Vis.x > Vis.gameView.getWidth() - 133 ) {
				Vis.xSpeed = -10;
			}
			if (Vis.x + Vis.xSpeed < 250 ) {
				Vis.xSpeed = 10;
			}
			if(!Vis.flag){ //Log.i("zoo", "hola");
				if(Vis.v){
					Vis.ySpeed=Vis.ySpeed*(-1);
				}if(Vis.h){
					Vis.xSpeed=Vis.xSpeed*(-1);
				}
			
			}
			Vis.x = Vis.x + Vis.xSpeed;
			if (Vis.y > Vis.gameView.getHeight() - Vis.height - Vis.ySpeed || Vis.y + Vis.ySpeed < 0) {
				Vis.ySpeed = -Vis.ySpeed;
			}
			Vis.y = Vis.y + Vis.ySpeed;
			Vis.currentFrame = ++Vis.currentFrame % Vis.BMP_COLUMNS;
		
	}
	
}
