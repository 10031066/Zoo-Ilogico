package com.itc.machozoo2;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

<<<<<<< HEAD
public class Salvajes extends Sprite {
	
	Jaula origen; 
    reja victima=null;
=======
public class Salvajes extends Animales {
	//Jaula origen;
>>>>>>> c9794eb462bb4810dd32cad2013fe1be9e122cc3
    ActulizandoAnimales Act;
    
    
    public Salvajes(int indice/*, Cuadro zona*/,int x,int y,Jaula origen,GameView gameView, int id, List<FoodSprite> F) {
	    this.id=id;
	    this.tipo=5;
		this.gameView=gameView;
		
		gameView.Figuras.add(this);
		gameView.Animales.add(this);
		gameView.salvaje.add(this);
		food=F;
		
		xSpeed = rnd.nextInt(10);
		ySpeed = rnd.nextInt(10) - 5;

		//this.zona=zona;
        this.x=x;
        this.y=y;
        this.origen= origen;
        if(origen!=null){
        	origen.agregarAnimal(this); //se agrega el animal a la lista de su jaula
        }else{
        	System.out.println("El animal no tiene jaula");
        }
        switch (indice){
            case 8: //tigre
                crear(indice,"Tigre",true,10,20,10,false,false,true,true,gameView.bmp[1]);
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
        Act = new ActulizandoAnimales(this);//Movimiento
        digestion = new Hambre(this); //hambre
        
	}
}
