package com.itc.machozoo2;

import android.graphics.Rect;
import android.util.Log;

public class ActulizandoAnimales extends Thread{
	Animales Animal;
	private boolean atacando;
	ActulizandoAnimales(Animales Animal){
		this.Animal= Animal;
		
		
		start();
	}
	
	@Override
	public void run(){
	  while(true){
		
		int srcX = Animal.currentFrame * Animal.width;
		int srcY = Animal.getAnimationRow() * Animal.height;
	
		Animal.src = new Rect(srcX, srcY, srcX + Animal.width, srcY + Animal.height);
		Animal.dst = new Rect(Animal.x, Animal.y, Animal.x + Animal.width, Animal.y + Animal.height);
		//System.out.println(Figuras.size());
		for (Figura f : Animal.Figuras) {
			
			if( Animal.id != f.get_id()){// && this.get_tipo() != f.get_tipo()){
				//System.out.println(f.id);
				if (Rect.intersects(Animal.dst, f.dst) ) {//Se intersectan??
					
					//busca a quien atacar reja, docil o visitante
					
						if(Animal.atacar){//Si tiene hambre
							Animal.flag = true;
							switch(f.tipo){//es reja? animal docil? animal salvaje? visitante?
								case 4://Docil
									//Que empieze la batalla!!!
									atacando=true;
									Animal.AnimalVictima =BuscaDocilAtacar();
									if(Animal.AnimalVictima==null){
										System.out.println("Cosa rara esta pasando");
									}else{
										atacandoDocil((Dociles)Animal.AnimalVictima);
									}
									
									
									break;
								case 5://Salvaje
									
									break;
								case 6://rejas
									atacando=true;
									Animal.rejaVictima = buscaRejaAtacar();
									if(Animal.rejaVictima==null){//si no encontro jaula
										System.out.println("Cosa rara esta pasando");
									}else{
										try {
											atacandoJaula();
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									break;
								case 7://carne
									
									break;
								
								case 8://Visitante
									//El visitantante muere inmediatamente asi que solo necesita ser eliminado
									VictimaVisitante();
									
									break;
							}
										
						}else{//no tiene hambre o no es un animal salvaje
							
							Animal.flag = false;
							
							Animal.v=!Animal.v;
							Animal.h=!Animal.h;
							break;
						} 
						
					}
				}
			}
		
		update();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  }
	}
	
	private void atacandoDocil(Dociles Vict){//Funcion del Animal salvaje 
		while(atacando){
			Vict.salud-=Animal.ataque;
			if(Vict.salud<=0){//Ha muerto la victima
				Animal.gameView.dociles.remove(Vict);
				Animal.gameView.Animales.remove(Vict);
				Vict=null;
				atacando=false;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.i("ActualizandoAnimales", "Ha muerto un docil");
	}
	
	private void atacandoSalvaje(Salvajes Vict){
		while(atacando){
			Vict.salud+=Animal.ataque; // los dociles aumentan la salud/desestrezan al animal
			if(Vict.salud>=Vict.MaxSalud*.40){
				Vict.atacar=false; //Dejara de buscar pelea
				Vict.Act.atacando=false; //dejara de pelear
				Vict=null;
				atacando=false;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
 	private Dociles BuscaDocilAtacar() {
		for(int i=0; i<Animal.gameView.dociles.size(); i++){
			if(Rect.intersects(Animal.get_dst(),Animal.gameView.dociles.get(i).get_dst())){
				Log.i("ActualizandoAnimales", "Rival encontrado");
	      		return Animal.gameView.dociles.get(i);
	      	}
		}
	    return null;
		
	}

	private void VictimaVisitante() {
		for(int i=0; i<Animal.gameView.ListaVisitantes.size(); i++){
			if(Rect.intersects(Animal.get_dst(),Animal.gameView.ListaVisitantes.get(i).get_dst())){
				Animal.gameView.ListaVisitantes.remove(i);
	      		Log.i("ActualizandoAnimales", "el visitante fue devorado");
	      		Animal.gameView.VisitantesComidos++;
	      	}
	    }
	}

	public void atacandoJaula() throws InterruptedException{
        //La funcion de encarga de atacar la jaula
    	
        	while(Animal.get_salud()<Animal.MaxSalud*0.40){ //mientras su salud mantenga el margen de atacante
                //atacando la reja
        		Thread.sleep(1000);
        		System.out.println("El tigre esta atacando "+Animal.rejaVictima.Resistencia);
        		if(Animal.rejaVictima!=null){// Log.i("Zoo", "rejaVictima no es null");
        			Animal.rejaVictima.Resistencia-=Animal.ataque;
        			
        			if(Animal.rejaVictima.Resistencia<Animal.rejaVictima.ResistenciaMax/2){
        				if(Animal.rejaVictima.Vertical){
        					Animal.rejaVictima.usar=Animal.rejaVictima.broken2;
        				}else{
        					Animal.rejaVictima.usar=Animal.rejaVictima.broken1;
        				}
        			}
        			
        			if(Animal.rejaVictima.Resistencia<=0){ //
        				Animal.origen.jaulaRota=true;
        				if(Animal.origen.rejas.remove(Animal.rejaVictima)){
        					System.out.println("se removio reja de la jaula");
        				}
        				if(Animal.gameView.Rejas.remove(Animal.rejaVictima)){
        					System.out.println("se removio reja de la lista");
        				}
        				if(Animal.gameView.Figuras.remove(Animal.rejaVictima)){
        					System.out.println("se removio reja de las figuras");
        				}
        				
        				int indice=0;
        				int k[]=new int[2];
        				int l[]=new int[2];
        				
        				for(int i=0;i<Animal.gameView.map.rows;i++){
        					for(int j=0;j<Animal.gameView.map.columns;j++){
        						if(Rect.intersects(Animal.gameView.map.Celdas[i][j], Animal.rejaVictima.dst)){
        							k[indice]=i;
        							l[indice]=j;
        							indice++;
        						}
        					}
        				}
        				Animal.gameView.map.Area[k[0]][l[0]].EliminaporID(Animal.rejaVictima.get_id());
        				Animal.gameView.map.Area[k[1]][l[1]].EliminaporID(Animal.rejaVictima.get_id());
        				
        						
        				Animal.rejaVictima=null;
        				
        				System.out.println("la jaula ha caido!!!");
        				atacando=false;
        				return;
        			}
        			
        		}
        		
            }
    }
	
	public reja buscaRejaAtacar(){
    	for(int i=0; i<Animal.gameView.Rejas.size(); i++){
      	   if(Rect.intersects(Animal.get_dst(),Animal.gameView.Rejas.get(i).get_dst())){
      		   Log.i("Zoo", "reja encontrada para atacar");
      		   return Animal.gameView.Rejas.get(i);
      	   }
    	}
    	return null;
    }
	
	void update() {
		if(!Animal.comiendo()&&!atacando){
			if (Animal.x > Animal.gameView.getWidth() - 133 ) {
				Animal.xSpeed = -10;
			}
			if (Animal.x + Animal.xSpeed < 250 ) {
				Animal.xSpeed = 10;
			}
			if(!Animal.flag){ //Log.i("zoo", "hola");
				if(Animal.v){
					Animal.ySpeed=Animal.ySpeed*(-1);
				}if(Animal.h){
					Animal.xSpeed=Animal.xSpeed*(-1);
				}
			
			}
			Animal.x = Animal.x + Animal.xSpeed;
			if (Animal.y > Animal.gameView.getHeight() - Animal.height - Animal.ySpeed || Animal.y + Animal.ySpeed < 0) {
				Animal.ySpeed = -Animal.ySpeed;
			}
			Animal.y = Animal.y + Animal.ySpeed;
			Animal.currentFrame = ++Animal.currentFrame % Animal.BMP_COLUMNS;
		}else{
			//System.out.println("El tigre no deberia moverse");
		}
	}
}
