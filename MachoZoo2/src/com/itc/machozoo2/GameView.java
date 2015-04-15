package com.itc.machozoo2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	private Bitmap bmp, bmp2, bmp3, bmp4,bmp5,bmp6,bmp7,bmp8,bmp9;
	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;
	private Mapa map;
	private List<Figura> Figuras;
	private List<Jaula> Jaulas = new ArrayList<Jaula>();
	private List<FoodSprite> food = new ArrayList<FoodSprite>();
	private ArrayList<RejaSprite> Rejastmp;
	private ArrayList<RejaSprite> Rejas;
	int id = 0;
	int id_jaulas = 0;
	int id_carne=0;
	int activa;
	private Rect[][] Celdas;
	private GameView GV = this;
	boolean flag = false, flagF=false;
	Canvas canvas;

	public GameView(Context context) {
		super(context);

		gameLoopThread = new GameLoopThread(this);
		bmp5 = BitmapFactory.decodeResource(getResources(), R.drawable.poste);
		bmp6 = BitmapFactory.decodeResource(getResources(), R.drawable.puerta);
		bmp7 = BitmapFactory.decodeResource(getResources(), R.drawable.piso);
		bmp8=BitmapFactory.decodeResource(getResources(), R.drawable.bcarne);
		bmp9=BitmapFactory.decodeResource(getResources(), R.drawable.carne);
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.tigres);
		bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.rejasmall);
		bmp3 = BitmapFactory.decodeResource(getResources(), R.drawable.bjaula);
		bmp4 = BitmapFactory.decodeResource(getResources(), R.drawable.checkbutton);
		map = new Mapa(this,bmp5,bmp6,bmp7);
		Celdas = map.Get_Celdas();
		holder = getHolder();
		holder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				gameLoopThread.setRunning(false);
				while (retry) {
					try {
						gameLoopThread.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				gameLoopThread.setRunning(true);
				gameLoopThread.start();
				// Botones overhere!!
				
				Figuras = new CopyOnWriteArrayList<Figura>();
				// Animales tipo:1, rejas tipo:2, botones tipo:3
				
				//Figuras.add(new RejaSprite(GV, bmp2, id++, 2));
				Figuras.add(new Sprite(GV, bmp, Figuras, id++, 1,food));//tigre
				Figuras.add(new Sprite(GV, bmp, Figuras, id++, 1,food));
				Figuras.add(new Boton(GV, bmp3, id++, 3));
				Figuras.add(new Boton(GV, bmp4, id++, 3));
				Figuras.get(id - 1).get_dst().set(250, 250, 350, 350);
				Figuras.add(new Boton(GV, bmp8, id++, 3));
				Figuras.get(id - 1).get_dst().set(0, 500, 250, 750);
				//si quieres otro tigre descomenta la linea de abajo, todo lo que quieras agregar de figuras 
				//ponlo abajo de este comentario por que si no joderas los botones.
				//Figuras.add(new Sprite(GV, bmp, Figuras, id++, 1,food));
				

				// botones overhere!!
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			}
		});

	}

	@SuppressLint("WrongCall")
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		map.onDraw(canvas);
    
		for (Iterator<Figura> f = Figuras.iterator(); f.hasNext();) {
			Figura aux = f.next();
			aux.onDraw(canvas);
		}
	    if(!food.isEmpty()){
			for (int i = food.size() - 1; i >= 0; i--) {
	            food.get(i).onDraw(canvas);
	     }
	        }
	}

	@SuppressLint("WrongCall")
	@Override
	public boolean onTouchEvent(MotionEvent event) {//Aqui se controlas las acciones TOUCH!
		int x = (int) event.getX();
		int y = (int) event.getY();
		

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN://Dedo sobre la pantalla
			for (Iterator<Figura> f = Figuras.iterator(); f.hasNext();) {
				Figura aux = f.next();
				
				switch (aux.tipo) {
				case 2:
					if (aux.get_dst().contains(x, y)) {
						Log.i("zoo", "toque");
						flag = true;
						activa = aux.get_id();
					
						break;
					} 
					break;

				case 3:// accion de los botones
					if (aux.get_dst().contains(x, y) && aux.get_id() == 1) {// boton
																			// para
																			// a�adir
																			// rejas

						Figuras.add(new RejaSprite(GV, bmp2, id++, 2));
                      
						Figuras.get(id - 1).get_dst().set(x - 15, y - 125, x + 15, y + 125);
						activa = id-1;
						flag = true;
						flagF=false;
						onDraw(canvas);
						break;
					} else {
						if (aux.get_dst().contains(x, y) && aux.get_id() == 2) {// check
							Rejas = new ArrayList<RejaSprite>(); // button
							Rejastmp = new ArrayList<RejaSprite>();int i=0;
							for (Iterator<Figura> t = Figuras.iterator(); t.hasNext();) {// crea arrelgo de rejas
																							
								Figura aux1 = t.next();

								if (aux1.get_tipo() == 2) {
									if(CheckId(aux1.get_id())){
									Rejas.add((RejaSprite) aux1);
									Log.i("zoo", "reja a�adida");
									}else{
										Log.i("zoo","no se a�adio reja");
									}
									// Jaulas.add(new Jaula(id_jaulas++));
								}
							}
							continuidad();
							imprimirjaulas();
							if(estacompleta()){
								Jaulas.add(new Jaula(id_jaulas++,Rejastmp));
								Log.i("zoo", "la jaula  terminada");
							}else{
								Log.i("zoo", "la jaula no esta terminada");
							}

						}else{
							if (aux.get_dst().contains(x, y) && aux.get_id() == 3) {//a�adir carne
								Log.i("zoo", "presionaste carne");
								
								id_carne=food.size();
								
								food.add(new FoodSprite(GV, bmp9, food, id_carne++, 4));
								if(id_carne!=0){
								food.get(id_carne - 1).get_dst().set(x - 22, y - 15, x + 22, y + 15);
								}else{
									food.get(id_carne).get_dst().set(x - 22, y - 15, x + 22, y + 15);
								}
							}
						}
					}

				}
			}
			for(Iterator<FoodSprite> f = food.iterator(); f.hasNext();){
				FoodSprite aux=f.next();
				if (aux.get_dst().contains(x, y) && aux.get_tipo()==4) {
					Log.i("zoo", "toque en la carne");
					flagF = true;flag=false;
					activa = aux.get_id();
					// Log.i("zoo", "old= " + activa);
					break;
				}else
				{
					flagF=false;
				}
			}

			break;

		case MotionEvent.ACTION_MOVE://moviendo el dedo
			// Log.i("zoo", "" + flag);
			if(flagF){
					//Bug conocido: cuando el tigre se come la carne 1 antes que la 2 
					
					food.get(activa).get_dst().set(x - 22, y - 15, x + 22, y + 15);
					
					break;
				
			}
			if (flag) {
				
				if (activa == 0) {
					Log.i("zoo", "move");
					Figuras.get(activa).get_dst().set(x - 15, y - 125, x + 15, y + 125);
					break;
				} else {
					Log.i("zoo", "Activa= " + activa);
					
					for (int i = 0; i < Celdas.length; i++) {// recorre la
																// rejilla celda
																// por celda
						for (int j = 0; j < Celdas[0].length; j++) {
							if (Celdas[i][j].contains(Figuras.get(activa).get_dst().centerX(),
									Figuras.get(activa).get_dst().centerY())) {// checa si la reja esta en la celda[i][j]
								if(!((RejaSprite)Figuras.get(activa)).get_fija()){
							
								if (Figuras.get(activa).get_dst().centerY() < Celdas[i][j]
										.centerY() - 45
										|| Figuras.get(activa).get_dst().centerY() > Celdas[i][j]
												.centerY() + 45) {
									Figuras.get(activa).set_btm(
											BitmapFactory.decodeResource(getResources(),
													R.drawable.hrejasmall));
									Figuras.get(activa).get_dst()
											.set(x - 125, y - 15, x + 125, y + 15);
									Figuras.get(activa).set_heigth(30);
									Figuras.get(activa).set_width(250);

									break;
								} else {
									Figuras.get(activa).set_btm(
											BitmapFactory.decodeResource(getResources(),
													R.drawable.rejasmall));
									Figuras.get(activa).get_dst()
											.set(x - 15, y - 125, x + 15, y + 125);
									Figuras.get(activa).set_heigth(250);
									Figuras.get(activa).set_width(30);
									break;
								}
							}
							}
						}
					}
					break;
				}

				// Log.i("zoo",
				// ""+Reja1.SpriteRect().left+","+Reja1.SpriteRect().top+","+Reja1.SpriteRect().top+","+Reja1.SpriteRect().bottom);
			
			}
			break;

		case MotionEvent.ACTION_UP://levantando el dedo
			
			if(Figuras.get(activa).get_tipo()==2){
			for (int i = 0; i < Celdas.length; i++) {// recorre la
				// rejilla celda
				// por celda
				for (int j = 0; j < Celdas[0].length; j++) {
					
					RejaSprite aux = (RejaSprite) Figuras.get(activa);
					if (Celdas[i][j].contains(Figuras.get(activa).get_dst().centerX(),
							Figuras.get(activa).get_dst().centerY())) {
						if (Figuras.get(activa).get_dst().centerY() < Celdas[i][j].centerY() - 45) {// top
							Figuras.get(activa).get_dst().set(Celdas[i][j].left, Celdas[i][j].top,
							Celdas[i][j].left + 250, Celdas[i][j].top + 30);
							aux.set_position(2);

						}
						if (Figuras.get(activa).get_dst().centerY() > Celdas[i][j].centerY() + 45) {// bot
							Figuras.get(activa).get_dst().set(Celdas[i][j].left, Celdas[i][j].bottom - 30,
							Celdas[i][j].left + 250, Celdas[i][j].bottom);
							aux.set_position(4);
						}
						if (Figuras.get(activa).get_dst().centerX() > Celdas[i][j].centerX() + 45) {
							Figuras.get(activa)
									.get_dst()
									.set(Celdas[i][j].right - 30, Celdas[i][j].top,
											Celdas[i][j].right, Celdas[i][j].top + 250);
							aux.set_position(3);
						}
						if (Figuras.get(activa).get_dst().centerX() < Celdas[i][j].centerX() - 45) {
							Figuras.get(activa)
									.get_dst()
									.set(Celdas[i][j].left, Celdas[i][j].top,
											Celdas[i][j].left + 30, Celdas[i][j].top + 250);
							aux.set_position(1);
						}

					}
				}
			}}
			flag = false;
			break;
		}

		return true;

	}

	public void set_canvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public void continuidad(){
		for (int i = 0; i < Rejas.size(); i++) {//checa la continuidad de las rejas para formar la jaula
			int tmp=0;
			for (int j = 0; j < Rejas.size(); j++) {

				// Log.i("zoo", "Hola");
				/* horizontal a la torch */
				if(i!=j){
				if ((Rejas.get(j).get_dst().left == Rejas
						.get(i).get_dst().right || Rejas.get(i).get_dst().left == Rejas
						.get(j).get_dst().right)
						&& Rejas.get(i).get_dst().top == Rejas.get(j).get_dst().top
						&& Rejas.get(i).get_dst().bottom == Rejas.get(j)
								.get_dst().bottom ) {
					// Codigo aqui
					
					if(tmp==0){
					if(Rejas.get(i).get_anterior()==null){
						Rejas.get(i).set_anterior(Rejas.get(j));
					}tmp=1;
					}else{
						if(Rejas.get(i).get_siguiente()==null){
					
						Rejas.get(i).set_siguiente(Rejas.get(j));
					
						}
					}
					
					if(!yaexiste(Rejas.get(i).get_id())){
						Rejastmp.add(Rejas.get(i));
					}

					Log.i("zoo", "" + Rejas.get(i).get_id()
							+ " es continua H con " + Rejas.get(j).get_id());
				}

				/* vertical */if (Rejas.get(i).get_dst().top == Rejas.get(j)
						.get_dst().top && (Rejas.get(i).get_dst().right == Rejas.get(j)
								.get_dst().right)) {
					// codigo aqui
					
					if(tmp==0){
						if(Rejas.get(i).get_anterior()==null){
							Rejas.get(i).set_anterior(Rejas.get(j));
						}tmp=1;
						}else{
							if(Rejas.get(i).get_siguiente()==null){
						
							Rejas.get(i).set_siguiente(Rejas.get(j));
						
							}
						}
					
					
						if(!yaexiste(Rejas.get(i).get_id())){
							Rejastmp.add(Rejas.get(i));
						}
					
					Log.i("zoo", "" + Rejas.get(i).get_id()
							+ " es continua V con " + Rejas.get(j).get_id());
				}
				if(Rejas.get(i).get_dst().bottom==Rejas.get(j).get_dst().top&&
						Rejas.get(i).get_dst().left==Rejas.get(j).get_dst().left){
					if(tmp==0){
						if(Rejas.get(i).get_anterior()==null){
							Rejas.get(i).set_anterior(Rejas.get(j));
						}tmp=1;
						}else{
							if(Rejas.get(i).get_siguiente()==null){
						
							Rejas.get(i).set_siguiente(Rejas.get(j));
						
							}
						}
					if(!yaexiste(Rejas.get(i).get_id())){
						Rejastmp.add(Rejas.get(i));
					}
				}
					
				if(Rejas.get(i).get_dst().top == Rejas.get(j)
						.get_dst().top && Rejas.get(i).get_dst().left == Rejas.get(j).get_dst().left){
					
					if(tmp==0){
						if(Rejas.get(i).get_anterior()==null){
							Rejas.get(i).set_anterior(Rejas.get(j));
						}tmp=1;
						}else{
							if(Rejas.get(i).get_siguiente()==null){
						
							Rejas.get(i).set_siguiente(Rejas.get(j));
						
							}
						}
					if(!yaexiste(Rejas.get(i).get_id())){
						Rejastmp.add(Rejas.get(i));
					}
						Log.i("zoo", "" + Rejas.get(i).get_id()
								+ " es continua VA con " + Rejas.get(j).get_id());
				}
				if (Rejas.get(i).get_dst().bottom == Rejas.get(j).get_dst().bottom
						&& (Rejas.get(i).get_dst().right == Rejas.get(j)
								.get_dst().right || Rejas.get(i).get_dst().left == Rejas
								.get(j).get_dst().left)) {
					// codigo aqui
					
					if(tmp==0){
						if(Rejas.get(i).get_anterior()==null){
							Rejas.get(i).set_anterior(Rejas.get(j));
						}tmp=1;
						}else{
							if(Rejas.get(i).get_siguiente()==null){
						
							Rejas.get(i).set_siguiente(Rejas.get(j));
						
							}
						}
					
						if(!yaexiste(Rejas.get(i).get_id())){
							Rejastmp.add(Rejas.get(i));
						}
					
					Log.i("zoo", "" + Rejas.get(i).get_id()
							+ " es continua V2 con " + Rejas.get(j).get_id());
				}

				}
			}
			
		}//checa la continuidad de las rejas para formar la jaula
	}

	public boolean CheckId(int id) {// devuelve falso si el id de una reja ya
									// existe en una jaula
		for (Iterator<Jaula> J = Jaulas.iterator(); J.hasNext();) {
			Jaula tmp = J.next();
			for (Iterator<RejaSprite> I = tmp.Get_Rejas().iterator(); I.hasNext();) {
				RejaSprite Itmp = I.next();
				if (id == Itmp.get_id()) {
					return false;
				}
			}

		}
		return true;
	}

	public int Distanciaentredospuntos(int x1, int x2, int y1, int y2) {
		return (int) Math.hypot(x2 - x1, y2 - y1);
	}

	public boolean yaexiste(int id) {
		for (int i = 0; i < Rejastmp.size(); i++) {
			if (id == Rejastmp.get(i).get_id()) {
				return true;
			}
		}
		return false;
	}

	public boolean estacompleta() {
		boolean flag=false;
		for (int i = 0; i < Rejastmp.size(); i++) {
			if(Rejastmp.get(i).get_siguiente()!=null && Rejastmp.get(i).get_anterior()!=null){
				flag=true;
			}else{
				flag=false;
			}
		}
		return flag;

	}

	public void imprimirjaulas() {
		for (int i = 0; i < Rejastmp.size(); i++) {
			Log.i("zoo", "ID: " + Rejastmp.get(i).get_id());
		}

	}
}