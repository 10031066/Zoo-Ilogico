package com.itc.machozoo2;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class FoodSprite extends Figura{
	GameView gameView;
	private int life=100;
	private float x;
    private float y;
	 private List<FoodSprite> temps;
	
	 public FoodSprite(GameView gameView, Bitmap bmp,List<FoodSprite> f, int id,int tipo,float x,
             float y) {
		    this.id=id;
		    this.tipo=tipo;
			this.gameView=gameView;
			this.bmp = bmp;
			temps=f;
			//this.x=220;
			//this.y=y;
			
			this.x = Math.min(Math.max(x - bmp.getWidth() / 2, 0),
                    gameView.getWidth() - bmp.getWidth());
            this.y = Math.min(Math.max(y - bmp.getHeight() / 2, 0),
                    gameView.getHeight() - bmp.getHeight());
            Log.i("zoo", "("+x+","+y+")");
			
	}
	 public void onDraw(Canvas canvas) {
         update();
         canvas.drawBitmap(bmp, x, y, null);
   }
	 private void update() {
         if (--life < 1) {
                temps.remove(this);
         }
   }
}
