package com.itc.machozoo2;

import java.util.List;

import android.graphics.Bitmap;

public class Salvajes extends Sprite {
	Jaula origen;
    boolean atacar=false;    
    
    public Salvajes(int indice/*, Cuadro zona*/,int x,int y,Jaula origen,GameView gameView, int id,int tipo, List<FoodSprite> F) {
	    this.id=id;
	    this.tipo=tipo;
		this.gameView=gameView;
		
		gameView.Figuras.add(this);
		gameView.ListaSalvajes.add(this);
		food=F;
		
		xSpeed = rnd.nextInt(10);
		ySpeed = rnd.nextInt(10) - 5;

		//this.zona=zona;
        this.x=x;
        this.y=y;
        this.origen= origen;
        origen.agregarAnimal(this); //se agrega el animal a la lista de su jaula
        switch (indice){
            case 8: //tigre
                crear(indice,"Tigre",true,100,20,1000,false,false,true,true,gameView.bmp[1]);
                break;
            case 9: //Oso
                crear(indice,"Oso",true,250,15,900,false,false,true,true,gameView.bmp[1]);
                break;
            case 10: //leon
                crear(indice,"Leon",true,250,20,900,false,false,true,true,gameView.bmp[1]);
                break;
            case 11: //Avestruz
                crear(indice,"Avestruz",true,250,10,750,true,true,false,false,gameView.bmp[1]);
                break;
            case 12: //Panda
                crear(indice,"Panda",true,210,15,800,false,true,false,true,gameView.bmp[1]);
                break;
            case 13: //Cocodrilo
                crear(indice,"Cocodrilo",true,230,20,850,false,false,true,true,gameView.bmp[1]);
                break;
            case 14: //Rinoceronte
                crear(indice,"Rinoceronte",true,350,30,1100,true,true,false,false,gameView.bmp[1]);
                break;   
        } 
		
		
		// Log.i("zoo",""+this.width);
        ActulizandoAnimales Act = new ActulizandoAnimales(this);
        start();
	}
    
    public void run() {
        //
    	
        try {
            while(true){
                //System.out.println(nombre+" corriendo "+get_salud() );
                Thread.sleep(5000);
                set_salud(get_salud()-((origen.getHabitantes()*origen.getHabitantes())/origen.size));
                if(get_salud()<=0){
                    set_salud(0);
                    atacar=true;
                    while(get_salud()<MaxSalud*40){//empieza a atacar!!!
                       Thread.sleep(5000);
                       System.out.println("El tigre tiene hambre!!!");
                       
                       if(origen.jaulaRota){//si la jaula esta rota
                           //es libre!!! ahora busca comida...
                           //debe buscar al comestible mas cercano e ir tras el
                           int indiceComida;
                           double distanciaMenor=999;
                           double distancia;
                           int comidaX,comidaY;
                           
                           //for(int i=0;i<)
                           //while(getSalud()<getMaxSalud()*40){ //mientras su salud mantenga el margen de atacante
                               
                               
                           //}
                       }else{//a dañar la jaula
                           //busca la reja mas cercana
                           int indiceReja=0;
                           double distanciaMenor=999;
                           double distancia;
                           int jaulaX,jaulaY;//posiciones de la jaula
                           
                           //atacara la primera reja con la que se tope
                           reja victima= ;
                           //
                           
                           while(get_salud()<MaxSalud*40){ //mientras su salud mantenga el margen de atacante
                               //atacando la reja
                               victima.Resistencia-=ataque;
                               if(victima.Resistencia<victima.ResistenciaMax/2){
                            	   if(victima.Vertical){
                            		   victima.usar=victima.broken2;
                            	   }else{
                            		   victima.usar=victima.broken1;
                            	   }
                               }
                               Thread.sleep(1000);
                           }
                       }
                    }
                }
            }
        } catch (InterruptedException ex) {
            
        }
    }

    void atacando(){
        //buscara al ser docil o humano mas cercando y atacara hasta que muera, o el estres(salud) mejore
        boolean seguir=true;
        while(seguir){
            
        }
        
    }
}
