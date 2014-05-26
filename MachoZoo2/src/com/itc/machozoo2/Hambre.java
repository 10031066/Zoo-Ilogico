package com.itc.machozoo2;

public class Hambre extends Thread{//esta clase se encargara de bajar la salud por hambre
	Salvajes Animal;
	Dociles Animal2;
	boolean Salvaje;
	public Hambre(Salvajes ani){
		this.Animal =ani;
		Salvaje=true;
		start();
	}
	
	public Hambre(Dociles ani){
		this.Animal2 =ani;
		Salvaje=false;
		start();
	}
	
	@Override
	public void run(){
		while(true){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(Salvaje){
				//System.out.println(Animal.origen);
				Animal.salud-=((Animal.origen.getHabitantes()*Animal.origen.getHabitantes())/Animal.origen.size);
				if(Animal.salud<=0){
					Animal.salud=0;
					Animal.atacar=true;
				}else{
					if(Animal.salud>=Animal.MaxSalud*.40){
						Animal.atacar=false;
					}
				}
			}else{
				Animal2.salud-=5;
				if(Animal2.salud<=0){
					Animal2.gameView.Animales.remove(Animal2);
					Animal2.gameView.dociles.remove(Animal2);
					
				}
			}
			
		}
	}
	
}
