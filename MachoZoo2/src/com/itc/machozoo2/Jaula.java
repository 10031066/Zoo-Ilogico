package com.itc.machozoo2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Jaula {
 public List<reja> rejas;
 private int ancho=0,alto=0;//numero de rejas que forman el ancho y el alto 
 //como guardo los indices?
 List<Sprite> inquilinos;
	public boolean jaulaRota=false;
 //
 protected int possX; //posicion incial de la jaula
 protected int possY; //posicion incial de la jaula
 int size;

 
 //
 
 public Jaula(int ancho, int largo, int iniI, int iniJ, List<reja> nuevaJaula) {
	 this.possX=iniJ;
     this.possY=iniI;
     this.ancho = ancho;
     this.alto = largo;
     this.size =ancho*largo;
     rejas = new CopyOnWriteArrayList<reja>();
     inquilinos = new CopyOnWriteArrayList<Sprite>();
     for(int i=0;i<nuevaJaula.size();i++){
    	 if(nuevaJaula.get(i)!=null){
    		 rejas.add(nuevaJaula.get(i));
    	 }
     }
     System.out.println("Jaula Creada");
}

 //Agregado 


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

public void aumentaSize(int size2) {
	this.size+=size2;
	
}

public void remuevePorID(int id) {
	for(int i=0;i<rejas.size();i++){
		if(rejas.get(i).id==id){
			rejas.set(i, null);
			rejas.remove(rejas.get(i));
			System.out.println("reja eliminada");
		}
	}
	
}
	
}
