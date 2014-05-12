package com.itc.machozoo2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
	
	public void EliminaReja(){	// V 0.01 Agregando la funcionalidad del pico

		List<Jaula> No=new CopyOnWriteArrayList<Jaula>();
		System.out.println("Eliminando Reja");
		for(int i=0;i<GV.map.rows;i++){
			for(int j=0;j<GV.map.columns;j++){
				if(GV.map.Area[i][j].rejas[0]==picada || GV.map.Area[i][j].rejas[1]==picada || GV.map.Area[i][j].rejas[2]==picada || GV.map.Area[i][j].rejas[3]==picada){
					No.add(GV.map.Area[i][j].pertenece);
				}
			}
		}
		//en teoria solo deberia haber una o dos jaulas
		switch (No.size()){
		case 0:
			//si no hay ninguna jaula no hace nada
			break;
		case 1:
			//
			break;
		case 2:
			No.get(0).rejas.remove(picada);
			No.get(1).rejas.remove(picada);
			No.get(0).aumentaSize(No.get(1).getSize());
			
			No.get(0).rejas.addAll(No.get(1).rejas); //todas las rejas que restan de la jaula 1
			GV.Jaulas.remove(No.get(1));
			break;
		
		}
	}
}
