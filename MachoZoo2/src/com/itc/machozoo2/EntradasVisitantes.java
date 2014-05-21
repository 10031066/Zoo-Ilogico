package com.itc.machozoo2;

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
				sleep(60000-GV.ListaSalvajes.size()*2000);
				GV.ListaVisitantes.add(new Visitante(GV));
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
