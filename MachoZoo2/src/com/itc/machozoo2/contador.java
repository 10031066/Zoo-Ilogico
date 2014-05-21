package com.itc.machozoo2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/*
 * ¿Como usar esta clase?
 * Si quieres un contador debes crear en gameview un objeto de esta clase
 * agregar al arreglo de mapas de bits del gameview un bmp de lo que representa el contador
 * inicializar el objeto en el surfaceCreated del gameview y pasarle los parametros.
 * inc es el numero de unidades que se incrementara cada cierto tiempo.
 * cant es la cantidad o total que hay de lo que estes contando
 * t es el intervalo de tiempo que tiene que pasar para que se sume el incremento
 * 
 * hay una funcion llamada restar que recive un entero como parametro, esta funcion resta ese valor 
 * a cantidad
 * 
 * si quieres cambiar su posicion debes cambiar los valores numericos que estan en las lineas
 * 
 * canvas.drawBitmap(bmp, 0, 10, p);
 * canvas.drawText("="+cantidad, bmp.getWidth()+10, 60, p);
 * que estan en el onDraw de esta clase 
 * 
 */
public class contador {
	 
	Bitmap bmp;
	int incremento;
	int cantidad=0;

	Paint p=new Paint();
	//GameView GV;
	
	public contador(GameView GV, Bitmap bmp){
		this.bmp=bmp;
		//cosas de la letra
		Typeface tf = Typeface.createFromAsset(GV.ct.getAssets(), "fonts/ZookyZooky.ttf");
		p.setTypeface(tf);
		p.setTextSize(60);
		p.setARGB(255,0,0,0);
		
	    
	}
	
	/**public void update(){
		if(cronometro>=tiempo){
			cantidad+=incremento;
			cronometro=0;
		}else{
			cronometro++;
		}
	}*/
	
	public void onDraw(Canvas canvas){
		//update();
		canvas.drawBitmap(bmp, 0, 10, p);
		canvas.drawText("="+cantidad, bmp.getWidth()+10, 60, p);
	}
	
	public void restar(int r){
		cantidad-=r;
	}

}
