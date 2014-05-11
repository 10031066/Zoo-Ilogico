package com.itc.machozoo2;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Pico extends Figura{
//estas dos variables representan la ubicacion de la punta del pico, cuando esta punta tenga con alguna reja
//se deberia borrar la reja
public int puntaX;
public int puntaY;
GameView GV;

public int id_picada;
public reja picada;
private List<reja> R;
	public Pico (GameView gameView, Bitmap bmp,int tipo, List<reja>R){

		this.tipo=tipo;
		this.bmp=bmp;
		this.width=bmp.getWidth();
		this.height=bmp.getHeight();
		this.R=R;
		dst = new Rect(0, 0, 45, 30);

		puntaX=dst.top+50;
		puntaY=dst.left;
		this.GV=gameView;

		

	}
	
	private void update(){
		
		for(int i=0; i<R.size();i++){
			//Log.i("Zoo","top"+ puntaX);
			if(R.get(i).get_dst().contains(puntaX, puntaY)){
				id_picada=R.get(i).get_id();
				picada=R.get(i);
				Log.i("Zoo", "Id reja picada= "+ R.get(i).get_id());
			}else{
				id_picada=0;
				picada=null;
			}
		}
	}
	public void onDraw(Canvas canvas) {
        update();
        puntaX=dst.top+50;
		puntaY=dst.left;
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
