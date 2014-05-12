package com.itc.machozoo2;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;

@SuppressLint("DrawAllocation")
public class Cuadro{
	boolean poss[]={false,false,false,false};//poss 1.- Arriba 2.-Derecha 3.-Abajo 4.-Izquierda
    boolean Jaula=false;//es parte de una jaula?
    int NoJaula; //Numero del indice de la Jaula a la que pertecene
    Jaula pertenece;
    //valor falso significa que no hay reja
    reja rejas[]={null,null,null,null};//hay reja??
    Rect desplazamiento;
	GameView GV;
	Bitmap tipo1,tipo2,usar;
    
	public Cuadro(Rect rect, GameView gameview) {
		this.GV=gameview;
		desplazamiento= rect;
		tipo1=BitmapFactory.decodeResource(GV.getResources(),R.drawable.poste);
		tipo2=usar=BitmapFactory.decodeResource(GV.getResources(),R.drawable.piso);
	}
	
    void NuevaNorte(reja ne){
        rejas[0]=ne;
        poss[0]=true;
    }
    void NuevaSur(reja ne){
        rejas[2]=ne;
        poss[2]=true;
    }
    void NuevaDerecha(reja ne){
        rejas[1]=ne;
        poss[1]=true;
    }
    void NuevaIzquierda(reja ne){
        rejas[3]=ne;
        poss[3]=true;
    }
    
    reja norte(){
        return rejas[0];
    }
    reja sur(){
        return rejas[2];
    }
    reja oeste(){
        return rejas[1];
    }
    reja este(){
        return rejas[3];
    }
    
    boolean getArriba(){
        return poss[0];
    }
    
    boolean getDerecha(){
        return poss[1];
    }
    
    boolean getAbajo(){
        return poss[2];
    }
    boolean getIzquierda(){
        return poss[3];
    }

	@SuppressLint("WrongCall")
	public void onDraw(Canvas canvas) {
		/**Rect src = new Rect(0,0,250,250);
		Paint p1=new Paint();
		p1.setARGB(255, 0, 255, 0);
		p1.setStyle(Style.STROKE);
		canvas.drawRect(desplazamiento, p1);
		 */
		if(Jaula){
			usar=tipo1;
		}else{
			usar=tipo2;
		}
		canvas.drawBitmap(usar, null, desplazamiento, null);
		//System.out.println(desplazamiento.toString());
		/**if(poss[0]) no se necesita por que se dibujan cuando se dibujan las figuras chocables
			rejas[0].onDraw(canvas);
		if(poss[1])
			rejas[1].onDraw(canvas);
		if(poss[2])
			rejas[2].onDraw(canvas);
		if(poss[3])
			rejas[3].onDraw(canvas);
		*/
	}
}
