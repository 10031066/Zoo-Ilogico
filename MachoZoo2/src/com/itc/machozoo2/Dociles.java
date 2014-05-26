package com.itc.machozoo2;

import android.graphics.Bitmap;

public class Dociles extends Animales{
	ActualizandoDocil actd;
	
	Dociles(int indice,Bitmap bmp,GameView gv,int id){
		
		this.id=id;
		this.gameView=gv;
		this.BMP_ROWS=7;
		this.BMP_COLUMNS=6;
		this.tipo =4;
		
		switch (indice){
        case 1: //elefante
        	crear(1,"elefante",false,250,20,10,false,false,true,true,bmp);
            break;
        case 2: //Oso
            crear(indice,"Oso",true,250,15,900,false,false,true,true,gameView.bmp[1]);
            break;
        case 3: //leon
            crear(indice,"Leon",true,250,20,900,false,false,true,true,gameView.bmp[1]);
            break;
        case 4: //Avestruz
            crear(indice,"Avestruz",true,250,10,750,true,true,false,false,gameView.bmp[1]);
            break;
        case 5: //Panda
            crear(indice,"Panda",true,210,15,800,false,true,false,true,gameView.bmp[1]);
            break;
        case 6: //Cocodrilo
            crear(indice,"Cocodrilo",true,230,20,850,false,false,true,true,gameView.bmp[1]);
            break;
        case 7: //Rinoceronte
            crear(indice,"Rinoceronte",true,350,30,1100,true,true,false,false,gameView.bmp[1]);
            break;   
		}
		
		gameView.Animales.add(this);
		gameView.dociles.add(this);
		gameView.Figuras.add(this);
		actd=new ActualizandoDocil(this);
		digestion = new Hambre(this); //hambre
	}
}
