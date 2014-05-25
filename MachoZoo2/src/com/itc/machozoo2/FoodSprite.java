package com.itc.machozoo2;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class FoodSprite extends Figura{
	GameView gameView;
	private int life=50;
	private float x;
    private float y;
    private boolean activar=false;
	 private List<FoodSprite> temps;
	
	 public FoodSprite(GameView gameView, Bitmap bmp,List<FoodSprite> f, int id) {
		    this.id=id;
		    this.tipo=7;
			this.gameView=gameView;
			this.bmp = bmp;
			this.width=bmp.getWidth();
			this.height=bmp.getHeight();
			temps=f;
			dst = new Rect(0, 0, 45, 30);
			//this.x=220;
			//this.y=y;
			
			/*this.x = Math.min(Math.max(x - bmp.getWidth() / 2, 0),
                    gameView.getWidth() - bmp.getWidth());
            this.y = Math.min(Math.max(y - bmp.getHeight() / 2, 0),
                    gameView.getHeight() - bmp.getHeight());*/
            Log.i("zoo", "("+x+","+y+")");
			
	}
	 public void onDraw(Canvas canvas) {
         update();
         src = new Rect(0, 0, width, height);
         canvas.drawBitmap(bmp, src, dst, null);
   }
	 private void update() {
		 if (life < 1) {
                temps.remove(this);
         }
        
   }
	public void mordida(){
		 life--;
	 }
}
