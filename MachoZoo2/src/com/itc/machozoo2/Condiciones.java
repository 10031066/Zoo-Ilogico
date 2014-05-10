package com.itc.machozoo2;

import java.util.Iterator;

public class Condiciones extends Thread{
	public GameView GV;
	int tipo[]; //tipo de la condicion 
		/**
		 * 0: Tamaño de la Jaula llega a cierto numero
		 * @param tipo
		 */
	int numero[];
	boolean CondCumplidas[];
	
	public Condiciones(GameView GV,int tipo[],int numero[]){
		this.tipo=tipo;
		this.numero = numero;
		CondCumplidas = new boolean[tipo.length];
		this.GV=GV;
		
		start();
	}
	
	@Override
	public void run(){
		try {
			boolean terminar;
			do{
				for(int i=0;i<CondCumplidas.length;i++){//
					if(!CondCumplidas[i]){
						switch(tipo[i]){
							case 0:
								for(Iterator<Jaula> j =GV.Jaulas.iterator();j.hasNext(); ){//Busca en todas las jaulas
									Jaula temp = j.next();
									if(temp.getSize()>=numero[i]){
										CondCumplidas[i]=true;
									}
								}
								break;
							case 1:
								break;
						}
					}
				}
				
				terminar=true;
				
				for(int i=0;i<CondCumplidas.length;i++){
					if(!CondCumplidas[i]){// si alguna condicion no esta cumplida no termina
						terminar= false;
					}
				}
				
				
				Thread.sleep(1000);
			}while(!terminar);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.err.println("Las condiciones se han cumplido");
	}
}
