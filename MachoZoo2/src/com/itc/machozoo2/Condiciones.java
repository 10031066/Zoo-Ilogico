package com.itc.machozoo2;

import java.util.Iterator;

import android.content.Intent;
import android.util.Log;

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
			boolean repetir;
			int cumplidas=0;
			
			do{
				for(int i=0;i<CondCumplidas.length;i++){//
					if(!CondCumplidas[i]){
						switch(tipo[i]){
							case 0:
								for(Iterator<Jaula> j =GV.Jaulas.iterator();j.hasNext(); ){//Busca en todas las jaulas
									Jaula temp = j.next();
									if(temp.size>=numero[i]){
										CondCumplidas[i]=true;
										cumplidas++;
										
										System.out.println("Se cumplio una condicion");
									}
								}
								break;
							case 1:
								break;
						}
					}
				}
				
				repetir=true;
				//System.out.println("condicions cumplidas "+cumplidas+"/"+CondCumplidas.length);
				if(cumplidas==CondCumplidas.length){
					repetir=false;
				}
				
				
				Thread.sleep(1000);
			}while(repetir);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.err.println("Las condiciones se han cumplido");
		Log.i("Zoo","lanzando letrero");
		Intent in=new Intent(GV.ct,WinActivity.class); 
		GV.ct.startActivity(in);
	}
}
