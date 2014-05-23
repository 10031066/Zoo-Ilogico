package com.itc.machozoo2;

import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.Log;

public class Sprite  extends Figura {
	public static int BMP_ROWS = 2;
	public static int BMP_COLUMNS = 6;
	boolean atacar=false;
	protected int xSpeed = 10;
	protected int ySpeed;
	int currentFrame = 0;
	private int salud;
	protected List<FoodSprite>food;
	List<Figura> Figuras;
	Random rnd = new Random();
	
	GameView gameView;
	
	boolean flag = true;
	boolean v=false;
	boolean h=false;
	int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
	
	//Cambios de Edgar
		private int indice;//para definir el animal 1 a 14
		String nombre; 
		private boolean salvaje; //si o no
		protected int MaxSalud;
		private int precio; //costo del animal
	    private boolean heno; //come heno?
	    private boolean fruta;
	    private boolean pescado;
	    private boolean carne;
	    //si en un animal salvaje, baja a cero empezara a atacar
        //si es un animal docil baja a cero muere
        //si baja a un 10% del total bota una alarma o cambia de color la linea para avisar
	    protected int ataque;
	    protected Cuadro zona;
	//
		
	public void crear(int indice,String nombre ,boolean salvaje, int salud, int ataque, int precio, boolean heno, boolean fruta, boolean pescado, boolean carne,Bitmap bmp) {
		this.indice = indice;
	    this.nombre = nombre;
	    this.salvaje = salvaje;
	    this.MaxSalud = salud;
	    this.ataque = ataque;
	    this.precio = precio;
	    this.heno = heno;
	    this.fruta = fruta;
	    this.pescado = pescado;
	    this.carne = carne;
	    this.salud=salud;
	    this.bmp= bmp;
	    this.width = (bmp.getWidth() / BMP_COLUMNS);// -67;
		this.height = bmp.getHeight() / BMP_ROWS;
		dst = new Rect(x,y,x+width,y+height);
		this.Figuras=gameView.Figuras;
	}    
	
	
    @Override
	public void onDraw(Canvas canvas) {

		/**Paint p1= new Paint();
		p1.setARGB(255, 255, 0, 0);
		p1.setStyle(Style.STROKE);
		canvas.drawRect(dst, p1);
		*/
		canvas.drawBitmap(bmp, src, dst, null);

	}

	int getAnimationRow() {
		if (xSpeed > 0) {
			return 0;
		} else {
			return 1;
		}
	}
	
	
    public int get_salud(){
    	return salud;
    }
    public void set_salud(int s){
    	salud=s;
    }
    
    public boolean comiendo(){//busca colision con comida
    	for (FoodSprite f : food){
    	 if(Rect.intersects(dst, f.get_dst())){
    		 f.mordida();
    		 salud++;
    		 return true;
    	 }
    	 
    	}
    	return false;
    }


	public void buscaRejaAtacar() {//este metodo solo se usa en animales salvajes
		// TODO Auto-generated method stub
		
	}

}
