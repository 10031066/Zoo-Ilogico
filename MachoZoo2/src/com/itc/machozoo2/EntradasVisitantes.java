package com.itc.machozoo2;

import android.graphics.Bitmap;

public class EntradasVisitantes extends Thread{
	//esta clase se encarga de manejar la entrada de visitantes y crearlos
	GameView GV;
	Bitmap bmp;
	public EntradasVisitantes(GameView GV,Bitmap bmp){
		this.GV=GV;
		this.bmp=bmp;
		start();
	}
	
	@Override
	public void run(){
		while(true){
			try {
				
				GV.ListaVisitantes.add(new Visitante(GV,bmp));
				
				sleep(60000-GV.ListaSalvajes.size()*2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
