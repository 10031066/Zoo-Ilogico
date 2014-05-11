package com.itc.machozoo2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;

public class reja extends Figura{
	int tipo;
    int Resistencia;
    int ResistenciaMax;
    int iniI,iniJ;
    int finI,finJ;
    int estado;
    double unico;
    rejaSprite imagen;
    private GameView GV;
    Bitmap tipo1,tipo2;
    //Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.rejasmall);
    boolean Vertical;
    		
    public reja(int tipo, Rect dst,GameView gameView,boolean Vertical) {
    	this.id=(int)(Math.random()*32000);
    	this.Vertical=Vertical;
    	this.GV=gameView;
        this.tipo = tipo;
        tipo1=BitmapFactory.decodeResource(GV.getResources(),R.drawable.hrejasmall);
        tipo2=BitmapFactory.decodeResource(GV.getResources(),R.drawable.rejasmall);
        fija = false;
        anterior=null;
        siguiente =null;
        p1= new Paint();
        x=500;
        position=0;
        if(tipo==1){
            Resistencia =1000;
            ResistenciaMax=1000;
        }else{
            //establece la resisistenciaMax del tipo 2
        }
        width=tipo1.getWidth()/2;
        height=tipo1.getHeight();
        
        unico = id;
        this.dst = dst;
    }
	private int position=0;
	private Paint p1 = new Paint();
	private reja anterior=null, siguiente=null;
    private boolean fija = false;
    private int Salud=1000;
 
    public void onDraw(Canvas canvas) {
		
		p1.setARGB(255, 255, 0, 0);
		p1.setStyle(Style.STROKE);
		//System.out.println(dst.left +" "+dst.top+" "+dst.right+" "+dst.bottom);
		
		canvas.drawRect(dst, p1);
		if(Vertical){
			//src = new Rect(0, 0, tipo2.getWidth(), tipo2.getHeight());
			canvas.drawBitmap(tipo2, null, dst, null);
		}else{
			//src = new Rect(0, 0, tipo1.getWidth(),tipo1.getHeight() );
			canvas.drawBitmap(tipo1, null, dst, null);
		}
		
	}
	
	public void set_position(int p){
		position=p;
	}
	public int get_position (){
		return position;	
	}
	
	public void set_siguiente(reja s){
		siguiente=s;
	}
	public reja get_siguiente(){
		return siguiente;	
	}
	
	public void set_anterior(reja a){
		anterior=a;
	}
	public reja get_anterior(){
		return anterior;
	}
	
	public void fijar(){
		fija=true;
	}
    public boolean get_fija(){
    	return fija;
    }
    public int get_salud(){
    	return Salud;
    }
    public void set_salud(int s){
    	Salud=s;
    }
    
}
