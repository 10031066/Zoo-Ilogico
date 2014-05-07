package com.itc.machozoo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Sprite  extends Figura{
	private static final int BMP_ROWS = 2;
	private static final int BMP_COLUMNS = 6;

	private int xSpeed = 10;
	private int ySpeed;
	private int currentFrame = 0;
	private int salud;
	private List<FoodSprite>food;
	private Rect dst;
	List<Figura> Figuras;
	Random rnd = new Random();
	
	GameView gameView;
	
	private boolean flag = true;
	private boolean v=false,h=false;
	int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };

	public Sprite(GameView gameView, Bitmap bmp,List<Figura> arg, int id,int tipo, List<FoodSprite> F) {
	    x=0;
	    y=0;
	    Figuras=arg;
	    this.id=id;
	    this.tipo=tipo;
		this.gameView=gameView;
		this.bmp = bmp;
		this.width = (bmp.getWidth() / BMP_COLUMNS);// -67;
		this.height = bmp.getHeight() / BMP_ROWS;
		food=F;
		
		xSpeed = rnd.nextInt(10);
		ySpeed = rnd.nextInt(10) - 5;

		
		// Log.i("zoo",""+this.width);

	}

	private void update() {
		if(!comiendo()){
		if (x > gameView.getWidth() - 133 ) {
			xSpeed = -10;
		}
		if (x + xSpeed < 250 ) {
			xSpeed = 10;
		}
		if(!flag){ //Log.i("zoo", "hola");
			if(v){
				ySpeed=ySpeed*(-1);
			}if(h){
				xSpeed=xSpeed*(-1);
			}
			
		}
		x = x + xSpeed;
		if (y > gameView.getHeight() - height - ySpeed || y + ySpeed < 0) {
			ySpeed = -ySpeed;
		}
		y = y + ySpeed;
		currentFrame = ++currentFrame % BMP_COLUMNS;
	}
	}
    @Override
	public void onDraw(Canvas canvas) {

		int srcX = currentFrame * width;
		int srcY = getAnimationRow() * height;
	
		src = new Rect(srcX, srcY, srcX + width, srcY + height);
		dst = new Rect(x, y, x + width, y + height);

		for (Figura f : Figuras) {
			if( this.id != f.get_id() && this.get_tipo() != f.get_tipo()){
			if (Rect.intersects(dst, f.get_dst()) ) {
				
				flag = false;
				if(((RejaSprite)f).get_position()==2||((RejaSprite)f).get_position()==4){
				 v=true;
				}else{
					v=false;
				}
				if(((RejaSprite)f).get_position()==1||((RejaSprite)f).get_position()==3){
					 h=true;
					}else{
						h=false;
					}
			
				break;
			} else {
				flag = true;
			}
			}
		}
		
		canvas.drawBitmap(bmp, src, dst, null);
		update();
	}

	private int getAnimationRow() {
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
    		 return true;
    	 }
    	}
    	return false;
    }
}
