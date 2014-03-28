package com.itc.machoszoo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
 
public class Sprite {
       private static final int BMP_ROWS = 2;
       private static final int BMP_COLUMNS = 6;
       private int x = 0;
       private int y = 0;
       private int xSpeed = 10;
       private GameView gameView;
       private Bitmap bmp;
       private int currentFrame = 0;
       private int width;
       private int height;
       int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
       public Sprite(GameView gameView, Bitmap bmp) {
    	   this.gameView = gameView;
           this.bmp = bmp;
           this.width = (bmp.getWidth() / BMP_COLUMNS);//-67;
           this.height = bmp.getHeight() / BMP_ROWS;
             Log.i("zoo",""+this.width);
       }
 
       private void update() {
             if (x > gameView.getWidth() - 133) {
                    xSpeed = -10;
             }
             if (x + xSpeed< 0) {
                    xSpeed = 10;
             }
             x = x + xSpeed;
             currentFrame = ++currentFrame % BMP_COLUMNS;
       }
      
       public void onDraw(Canvas canvas) {
    	   update();
    	   int srcX = currentFrame * width;
           int srcY = getAnimationRow()* height;
           Log.i("zoo", ""+getAnimationRow());
           //Rect src = new Rect(srcX,0, srcX + width, srcY + 60);
           //Rect dst = new Rect(x, y+100, x + width, y + 200);
           Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
           Rect dst = new Rect(x, y, x + width, y + height);
           canvas.drawBitmap(bmp, src, dst, null);
       }
       
       private int getAnimationRow() {
          if(xSpeed>0){
        	  return 0;
          }
          else{ return 1;
          }
       }
          
   
}  