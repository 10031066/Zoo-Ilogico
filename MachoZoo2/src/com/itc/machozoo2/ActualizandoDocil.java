package com.itc.machozoo2;

import android.graphics.Rect;

public class ActualizandoDocil extends Thread {
	Dociles d;
	int row;
	int srcX,srcY;
	public ActualizandoDocil(Dociles d) {
		this.d=d;
		d.dst = new Rect(d.x, d.y, d.x + d.width, d.y + d.height);
	start();
	}
	
	public void run(){
		while(true){
			d.currentFrame = ++d.currentFrame % d.BMP_COLUMNS;
			if(row<7){
			srcX = d.currentFrame * d.width;
			 srcY = row* d.height;
			row++;
			}else{
				row=0;
			}
			
			
			
			d.src = new Rect(srcX, srcY, srcX + d.width, srcY + d.height);
			
			//System.out.println(Vis.x+" "+ Vis.y+" "+ (Vis.x + Vis.width)+" "+ (Vis.y + Vis.height));
			
			
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//agregar el src y el dst del visitante y su sprite
			
			
		}
	}

}
