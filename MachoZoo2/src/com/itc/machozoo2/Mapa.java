package com.itc.machozoo2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;

public class Mapa {

	private int width;
	private int height;
	private int SIZE = 250;
	private int rows;
	private int columns;
	private Rect Celdas[][];
	private Bitmap poste,puerta,piso;
	Paint paint = new Paint();
	private GameView gameView;

	public Mapa(GameView gameview, Bitmap poste, Bitmap puerta, Bitmap piso) {
		this.gameView = gameview;
		width = 2500;
		Log.i("zoo", "Ancho: " + width);
		height = 1600;
		Log.i("zoo", "Alto: " + height);
		rows = height / SIZE;
		columns = width / SIZE;
		Celdas = new Rect[rows][columns];
		this.poste=poste;
		this.puerta=puerta;
		this.piso=piso;
		Log.i("zoo", "Tamaño: " + columns);
        
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (i == 0 && j == 0) {
					Celdas[i][j] = new Rect(0, 0, 250, 250);
				} else {
					if (i == 0 && j != 0) {
						Celdas[i][j] = new Rect(Celdas[i][j - 1].left + 250, 0,
								Celdas[i][j - 1].right + 250, 250);
					} else {
						if (j == 0 && i != 0) {
							Celdas[i][j] = new Rect(0,
									Celdas[i - 1][j].top + 250, 250,
									Celdas[i - 1][j].bottom + 250);
						} else {
							Celdas[i][j] = new Rect(
									Celdas[i][j - 1].left + 250,
									Celdas[i][j - 1].top,
									Celdas[i][j - 1].right + 250,
									Celdas[i][j - 1].bottom);
						}
					}
				}

			}
		}
		
	}

	public void onDraw(Canvas canvas) {
           Rect src = new Rect(0, 0, 250, 250);
		// Log.i("zoo", ""+Celdas.length);
		for (int i = 0; i < Celdas.length; i++) {
			for (int j = 0; j < Celdas[0].length; j++) {
				paint.setARGB(255, 255, 0, 0);
				paint.setStyle(Style.STROKE);
				canvas.drawRect(Celdas[i][j], paint);
				/*if((j==4||j==6)&&i==0){
					
					canvas.drawBitmap(poste, src, Celdas[i][j], null);
				    
					}else{
				if(j==5&&i==0){
					canvas.drawBitmap(puerta, src, Celdas[i][j], null);
				}
				
				else{
					if(j!=0)
					canvas.drawBitmap(piso, src, Celdas[i][j], null);
				}
			}*/
		}
		
		}
	}
	
	public Rect [][] Get_Celdas (){
		return Celdas;
	}
	
	

}
