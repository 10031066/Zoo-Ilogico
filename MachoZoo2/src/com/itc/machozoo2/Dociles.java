package com.itc.machozoo2;

import android.graphics.Bitmap;

public class Dociles extends Animales{
	ActualizandoDocil actd;
	
	Dociles(int indice,Bitmap bmp,GameView gv,int id){
		
		this.id=id;
		this.gameView=gv;
		this.BMP_ROWS=7;
		this.BMP_COLUMNS=6;
		gameView.Animales.add(this);
		crear(1,"elefante",false,10,20,10,false,false,true,true,bmp);
		actd=new ActualizandoDocil(this);
	}
}
