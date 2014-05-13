package com.itc.machozoo2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Pico extends Figura{
//estas dos variables representan la ubicacion de la punta del pico, cuando esta punta tenga con alguna reja
//se deberia borrar la reja
public int puntaX;
public int puntaY;
GameView GV;

public Mapa m;
public int id_picada;
public reja picada;
 int i[]= new int[2];
 int j[]= new int[2];
 int indice=0;

private List<reja> R;
	public Pico (GameView gameView, Bitmap bmp,int tipo, List<reja>R){

		this.tipo=tipo;
		this.bmp=bmp;
		this.width=bmp.getWidth();
		this.height=bmp.getHeight();
		this.R=R;
		dst = new Rect(0, 0, 45, 30);

		puntaX=dst.top+50;
		puntaY=dst.left;
		this.GV=gameView;

	}
	
	void update(){		
		
		indice=0;
		for(int i=0; i<R.size();i++){
			//Log.i("Zoo","top"+ puntaX);
			if(Rect.intersects(dst, R.get(i).get_dst())){
				id_picada=R.get(i).get_id();
				picada=R.get(i);
				Log.i("Zoo", "Id reja picada= "+ R.get(i).get_id());
			}else{
				/**id_picada=0;
				picada=null;*/
			}
		}
	}
	public void onDraw(Canvas canvas) {
        //update();
        puntaX=dst.top+50;
		puntaY=dst.left;
        src = new Rect(0, 0, width, height);
        canvas.drawBitmap(bmp, src, dst, null);
        
	}
	
	public void EliminaReja(){	// V 0.01 Agregando la funcionalidad del pico
		if(picada==null){
			return;
		}
		System.out.println("Buscando ID: " + id_picada);
		List<Jaula> No=new ArrayList<Jaula>();
		System.out.println("Eliminando Reja");
		
		//Buscar las dos Jaulas donde esta la reja picada
		
		System.out.println("Jaulas: "+GV.Jaulas.size());
		for(int i=0;i<GV.Jaulas.size();i++){//la idea es que consiga las dos Jaulas que compartan la msima reja
			for(int j=0;j<GV.Jaulas.get(i).rejas.size();j++){
				if(GV.Jaulas.get(i).rejas.get(j).id==picada.id){
					No.add(GV.Jaulas.get(i));
					System.out.println("SE econtro la reja");
					
				}
			}
		}
		
		
		System.out.println(No.size());
		
		//en teoria solo deberia haber una o dos jaulas
		switch (No.size()){
		case 0:
			//si no hay ninguna jaula no hace nada
			break;
		case 1://En el caso de que se toque la reja de una sola Jaula
			if(this.i==this.j){//Para el caso en que se seleccione una reja que estra en la misma jaula
				No.get(0).remuevePorID(id_picada);
				
				for(int i=0;i<4;i++){
					if(GV.map.Area[this.i[0]][this.j[0]].rejas[i].id==id_picada ){
						GV.map.Area[this.i[0]][this.j[0]].rejas[i]=null;
						
						
						GV.Figuras.remove(picada);
						System.out.println("Se elimino la reja intermedia");
					}
				}
				
			}else{
			
			System.out.println("No se puede Derribar, se escaparian los Animales");
			
			}
			break;
		case 2://En caso de que se elimine una reja que es parte de dos jaulas
			System.out.println(No.get(0));
			System.out.println(No.get(1));
			
			for(int i=0;i<4;i++){
				if(GV.map.Area[this.i[0]][this.j[0]].rejas[i]!=null && GV.map.Area[this.i[0]][this.j[0]].rejas[i].id==id_picada ){
					GV.map.Area[this.i[0]][this.j[0]].rejas[i]=null;
					
					GV.Figuras.remove(picada);
					System.out.println("Se eliminaron rejas");
				}
			}
			
			
			
			No.get(0).remuevePorID(picada.id);
			//No.get(1).remuevePorID(picada.id);
			System.out.println(No.get(1).size);
			No.get(0).aumentaSize(No.get(1).size);
			
			No.get(0).rejas.addAll(No.get(1).rejas); //todas las rejas que restan de la jaula 1
			GV.Jaulas.remove(No.get(1));
			
			//Eliminar rejas iguales en las jaulas
			
			for (int i =0; i<No.size(); i++) { 
				for(int j=i+1; j<No.size(); j++){
					if(No.get(0).rejas.get(i)==No.get(0).rejas.get(j)){
						No.get(0).rejas.remove(j);
					}
				}
			}
			
			
			//System.out.println("Reja eliminada");
			break;
		
		}
	}
}
