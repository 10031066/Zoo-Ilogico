package com.itc.machozoo2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Jaula {
 List<reja> rejas;
 private int ancho=0,alto=0;//numero de rejas que forman el ancho y el alto 
 //como guardo los indices?
 List<Sprite> inquilinos;
	public boolean jaulaRota=false;
 //
 protected int possX; //posicion incial de la jaula
 protected int possY; //posicion incial de la jaula
private int size;

 
 //
 
 public Jaula(int ancho, int largo, int iniX, int iniY, reja[] lista) {
	 this.possX=iniX;
     this.possY=iniY;
     this.ancho = ancho;
     this.alto = largo;
     this.size =ancho*largo;
     rejas = new CopyOnWriteArrayList<reja>();
     inquilinos = new CopyOnWriteArrayList<Sprite>();
     for(int i=0;i<lista.length;i++)
    	 if(lista[i]!=null){
    		 rejas.add(lista[i]);
    	 }
     
}
 
 public int get_ancho(){
	 return ancho;
 }
 public int get_largo(){
	 return alto;
 }
 //Agregado 

public int getDiametro() {
	return 2*ancho+alto;
	
}

public int getSize() {
	// TODO Auto-generated method stub
	return ancho*alto;
}

void agregarAnimal(Salvajes ani){
    ani.origen=this;//se le define a que jaula pertenece el animal
    inquilinos.add(ani); //referencia a que cuadro de la jaula pertenece el animal
}

public int getHabitantes() {
	return inquilinos.size();
}

public List<reja> Get_Rejas() {
	// TODO Auto-generated method stub
	return rejas;
}
	
}
