package com.itc.machozoo2;

import android.graphics.Bitmap;

public class EntradasVisitantes extends Thread{
	//esta clase se encarga de manejar la entrada de visitantes y crearlos
	GameView GV;
	public EntradasVisitantes(GameView GV){
		this.GV=GV;
		start();
	}
	
	@Override
	public void run(){
		while(true){
			try {
				Visitante temp=new Visitante(GV);
				GV.ListaVisitantes.add(temp);
				GV.Figuras.add(temp);
				
				sleep(30000-GV.Animales.size()*2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
