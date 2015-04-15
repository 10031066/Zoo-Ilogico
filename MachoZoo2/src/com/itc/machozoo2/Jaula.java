package com.itc.machozoo2;

import java.util.ArrayList;

public class Jaula {
 private int id_jaula;
 public ArrayList <RejaSprite> rejas;
 private int ancho=0,alto=0;//numero de rejas que forman el ancho y el alto 
 //como guardo los indices?
 public Jaula (int id,ArrayList<RejaSprite> R){
	id_jaula=id;
	rejas=R;
	
	for(int i=0; i<rejas.size();i++){
		rejas.get(i).fijar();
	}
 }
 
 public void addReja (RejaSprite i){
	rejas.add(i);
 }
 public ArrayList<RejaSprite> Get_Rejas(){
	 return rejas;
 }
 
 public int get_ancho(){
	 for(int i=0; i<rejas.size();i++){
			if(rejas.get(i).get_position()==1||rejas.get(i).get_position()==4){
				ancho++;
			}
		}
	 return ancho/2;
 }
 public int get_largo(){
	 for(int i=0; i<rejas.size();i++){
			if(rejas.get(i).get_position()==2||rejas.get(i).get_position()==3){
				alto++;
			}
		}
	 return alto/2;
 }
}
