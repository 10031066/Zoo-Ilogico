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
				//id_picada=0;
				//picada=null;
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
		System.out.println("Buscando ID: " + id_picada);
		List<Jaula> No=new ArrayList<Jaula>();
		System.out.println("Eliminando Reja");
		
		//Buscar las dos Jaulas donde esta la reja picada
		
		System.out.println("Jaulas: "+GV.Jaulas.size());
		for(int i=0;i<GV.Jaulas.size();i++){//la idea es que consiga las dos Jaulas que compartan la msima reja
			for(int j=0;j<GV.Jaulas.get(i).rejas.size();j++){
				if(GV.Jaulas.get(i).rejas.get(j).id==picada.id){
					//No.add(GV.Jaulas.get(i));
					System.out.println("SE econtro la reja");
				}
			}
		}
		System.out.println(No.size());
		
		//en teoria solo deberia haber una o dos jaulas
		switch (No.size()){
		case 0:
			//si no hay ninguna jaula no hace nada
			break;
		case 1:
			//
			break;
		case 2:
			System.out.println(No.get(0));
			System.out.println(No.get(1));
			
			No.get(0).remuevePorID(picada.id);
			//No.get(1).remuevePorID(picada.id);
			System.out.println(No.get(1).size);
			No.get(0).aumentaSize(No.get(1).size);
			
			No.get(0).rejas.addAll(No.get(1).rejas); //todas las rejas que restan de la jaula 1
			GV.Jaulas.remove(No.get(1));
			System.out.println("Reja eliminada");
			break;
		
		}
	}
}
