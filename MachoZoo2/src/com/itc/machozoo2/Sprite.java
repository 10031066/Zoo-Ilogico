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
	public int BMP_ROWS = 2;
	public int BMP_COLUMNS = 6;
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
	
	public void atacandoJaula() throws InterruptedException{//este metodo solo se usa en animales salvajes
		
	}

}
