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
	public Bitmap bmp[]= {null,BitmapFactory.decodeResource(getResources(), R.drawable.tigres),BitmapFactory.decodeResource(getResources(), R.drawable.rejasmall),BitmapFactory.decodeResource(getResources(), R.drawable.bjaula),
			BitmapFactory.decodeResource(getResources(), R.drawable.checkbutton),BitmapFactory.decodeResource(getResources(), R.drawable.poste),BitmapFactory.decodeResource(getResources(), R.drawable.puerta),
			BitmapFactory.decodeResource(getResources(), R.drawable.piso),BitmapFactory.decodeResource(getResources(), R.drawable.bcarne),
			BitmapFactory.decodeResource(getResources(), R.drawable.carne),BitmapFactory.decodeResource(getResources(), R.drawable.bpico),BitmapFactory.decodeResource(getResources(), R.drawable.pico)};
			
	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;
	public List<Figura> Figuras; //Aqui se guardan todas las figuras que pueden chocar entre si
	public List<Figura> Nochocan;
	public List<Sprite> ListaSalvajes;
	public List<Jaula> Jaulas = new ArrayList<Jaula>();
	private List<FoodSprite> food = new ArrayList<FoodSprite>();
	private ArrayList<reja> Rejastmp;
	private ArrayList<reja> Rejas;
	private List<reja> Rejas2= new ArrayList<reja>();//lo ocupo para la colision del pico, 
	                                            //es una lista de todas las rejas, se llena cuando lepicas al pico
	boolean flag = false, flagF=false,FlagP=false;
	int id=4;
	int id_jaulas = 0;
	int id_carne=0;
	Canvas canvas;
	int activa;
	private Rect[][] Celdas;
	private GameView GV = this;
	int indice=0;
	public Mapa map;
	private Pico p;
	Condiciones condicion;

	public GameView(Context context) {
		super(context);

		gameLoopThread = new GameLoopThread(this);
		Figuras = new CopyOnWriteArrayList<Figura>();
		Nochocan = new CopyOnWriteArrayList<Figura>();
		Rejas2=new CopyOnWriteArrayList<reja>();
		ListaSalvajes = new CopyOnWriteArrayList<Sprite>();
		
		map = new Mapa(this,bmp,Figuras,id,food);
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
				
				
				// Animales tipo:1, rejas tipo:2, botones tipo:3, pico tipo: 4
				
				Nochocan.add(new Boton(GV, bmp[3], 1, 3));
				Nochocan.add(new Boton(GV, bmp[4], 2, 3));
				Nochocan.get(1).get_dst().set(250, 250, 350, 350);
				Nochocan.add(new Boton(GV, bmp[8], 3, 3));
				Nochocan.get(2).get_dst().set(0, 500, 250, 750);
				Nochocan.add(new Boton(GV, bmp[10], 4, 3));
				Nochocan.get(3).get_dst().set(0, 750, 250, 1000);
				//si quieres otro tigre descomenta la linea de abajo, todo lo que quieras agregar de figuras 
				//ponlo abajo de este comentario por que si no joderas los botones.
				//Figuras.add(new Sprite(GV, bmp, Figuras, id++, 1,food));
				
				// botones overhere!!
				escenario1();
				//Log.i("llega", "ahhh");
			}
			
			public void escenario1(){
				map.crearReja(2, 2, 0);
				map.crearReja(2, 2, 1);
				map.crearReja(2, 2, 2);
				map.crearReja(2, 2, 3);
				
				Jaula Temp = map.esJaula(2,2);

				Salvajes temp1 = new Salvajes(8, 520,520, map.Jaulas.get(0), GV, 4, 0, food);
				Salvajes temp2 = new Salvajes(8, 520,620, map.Jaulas.get(0), GV, 4, 0, food);
				
				int var1[]={0};
				int var2[]={2};
				condicion = new Condiciones(GV,var1,var2);
				
				

			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			}
		});

	}

	@SuppressLint("WrongCall")
	protected
	void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
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
	    for (Iterator<Figura> f = Nochocan.iterator(); f.hasNext();) {
			Figura aux = f.next();
			aux.onDraw(canvas);
		}
	    if(p!=null){
        	p.onDraw(canvas);
        }
	}

	@SuppressLint("WrongCall")
	@Override
	public boolean onTouchEvent(MotionEvent event) {//Aqui se controlas las acciones TOUCH!
		int x = (int) event.getX();
		int y = (int) event.getY();
		int puntoX=x/map.SIZE;
		int puntoY=y/map.SIZE;
		
		//System.out.println(x+","+y);
		System.out.println("punto "+puntoX+","+puntoY);
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN://Dedo sobre la pantalla
			
			if(!map.Area[puntoY][puntoX].Jaula){			//Agregado en 0.00 : al presionar un cuadro que podria ser jaula lo combierte en una
				if(map.esJaula(puntoY, puntoX)!=null){
					System.out.println("Se Creo la Jaula");
				}else
					System.out.println("No se creo la jaula");
			}
			
			for (Iterator<Figura> f = Nochocan.iterator(); f.hasNext();) {
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
																			// añadir
																			// rejas

						Nochocan.add(new rejaSprite(GV, bmp[2], id++, 2));
                      
						Nochocan.get(id - 1).get_dst().set(x - 15, y - 125, x + 15, y + 125);
						activa = id-1;
						flag = true;
						flagF=false;
						onDraw(canvas);
						break;
					} else {
						if (aux.get_dst().contains(x, y) && aux.get_id() == 2) {// check
							
							//Rejas = new ArrayList<RejaSprite>(); // button
							//Rejastmp = new ArrayList<RejaSprite>();int i=0;
							for (Iterator<Figura> t = Figuras.iterator(); t.hasNext();) {// crea arrelgo de rejas

								Figura aux1 = t.next();

								if (aux1.get_tipo() == 2) {
									if(CheckId(aux1.get_id())){
									//Rejas.add((rejaSprite) aux1);
									Log.i("zoo", "reja añadida");
									}else{
										Log.i("zoo","no se añadio reja");
									}
									// Jaulas.add(new Jaula(id_jaulas++));
								}
							}
							continuidad();
							imprimirjaulas();
							if(estacompleta()){
								//Jaulas.add(new Jaula(id_jaulas++,Rejastmp));
								Log.i("zoo", "la jaula  terminada");
							}else{
								Log.i("zoo", "la jaula no esta terminada");
							}
							
						}else{
							if (aux.get_dst().contains(x, y) && aux.get_id() == 3) {//añadir carne
								Log.i("zoo", "presionaste carne");

								id_carne=food.size();

								food.add(new FoodSprite(GV, bmp[9], food, id_carne++, 4));
								if(id_carne!=0){
								food.get(id_carne - 1).get_dst().set(x - 22, y - 15, x + 22, y + 15);
								}else{
									food.get(id_carne).get_dst().set(x - 22, y - 15, x + 22, y + 15);
								}
							}
							else{//pico
								if(aux.get_dst().contains(x, y) && aux.get_id() == 4){
									for(int i=0; i<map.Area.length;i++){
										for(int j=0; j<map.Area[0].length;j++){
											for(int k=0; k<4;k++){boolean flag1=false;
												if(map.Area[i][j].rejas[k]!=null){
													//Log.i("Zoo", "Testing");
													if(Rejas2.isEmpty()){
													Rejas2.add(map.Area[i][j].rejas[k]);
													Log.i("Zoo", "se agrego1= "+map.Area[i][j].rejas[k].get_id());
													}else{
														if(!yaexiste2(map.Area[i][j].rejas[k].get_id())){
														Rejas2.add(map.Area[i][j].rejas[k]);
														Log.i("Zoo", "se agrego1= "+map.Area[i][j].rejas[k].get_id());
														}
													}
												
												}
											}
											
										}
									}
									p= new Pico(GV, bmp[11], 4,Rejas2);
									p.get_dst().set(x - 75, y , x + 75, y );
									FlagP=true;
									Log.i("tag", "PICO");
								}else{
									FlagP=false;
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
			if(FlagP){//pico
				p.get_dst().set(x - 75, y-150, x + 75, y );
			}
			if (flag) {

				if (activa == 0) {
					//Log.i("zoo", "move");
					Nochocan.get(activa).get_dst().set(x - 15, y - 125, x + 15, y + 125);
					break;
				} else {
					//Log.i("zoo", "Activa= " + activa);

					for (int i = 0; i < Celdas.length; i++) {// recorre la
																// rejilla celda
																// por celda
						for (int j = 0; j < Celdas[0].length; j++) {
							if (Celdas[i][j].contains
									(Nochocan.get(activa).get_dst().centerX(),
									Nochocan.get(activa).get_dst().centerY())) {// checa si la reja esta en la celda[i][j]
								if(!((rejaSprite)Nochocan.get(activa)).get_fija()){

								if (Nochocan.get(activa).get_dst().centerY() < Celdas[i][j]
										.centerY() - 45
										|| Nochocan.get(activa).get_dst().centerY() > Celdas[i][j]
												.centerY() + 45) {
									Nochocan.get(activa).set_btm(
											BitmapFactory.decodeResource(getResources(),
													R.drawable.hrejasmall));
									Nochocan.get(activa).get_dst()
											.set(x - 125, y - 15, x + 125, y + 15);
									Nochocan.get(activa).set_heigth(30);
									Nochocan.get(activa).set_width(250);
									
									break;
								} else {
									Nochocan.get(activa).set_btm(
											BitmapFactory.decodeResource(getResources(),
													R.drawable.rejasmall));
									Nochocan.get(activa).get_dst()
											.set(x - 15, y - 125, x + 15, y + 125);
									Nochocan.get(activa).set_heigth(250);
									Nochocan.get(activa).set_width(30);
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
			
			if(FlagP){
				if(p.picada!=null){
					p.EliminaReja();
					FlagP=false;
					
				}
			}
			p=null;
			
			if(Nochocan.get(activa).get_tipo()==2){
			for (int i = 0; i < Celdas.length; i++) {// recorre la
				// rejilla celda
				// por celda
				for (int j = 0; j < Celdas[0].length; j++) {

					rejaSprite aux = (rejaSprite) Nochocan.get(activa);
					if (Celdas[i][j].contains(Nochocan.get(activa).get_dst().centerX(),
							Nochocan.get(activa).get_dst().centerY())) {
						if (Nochocan.get(activa).get_dst().centerY() < Celdas[i][j].centerY() - 45) {// top
							Nochocan.get(activa).get_dst().set(Celdas[i][j].left, Celdas[i][j].top,
							Celdas[i][j].left + 250, Celdas[i][j].top + 30);
							aux.set_position(2);
							map.crearReja(j, i, 0);//Crea la reja logica para despues dibujarla
							Nochocan.remove(activa);  //Remueve la Reja visual
							id--;
							flag=false;
							activa--;
							return true;
						}
						if (Nochocan.get(activa).get_dst().centerY() > Celdas[i][j].centerY() + 45) {// bot
							Nochocan.get(activa).get_dst().set(Celdas[i][j].left, Celdas[i][j].bottom - 30,
							Celdas[i][j].left + 250, Celdas[i][j].bottom);
							aux.set_position(4);
							map.crearReja(j, i, 2);
							Nochocan.remove(activa);
							id--;
							flag=false;
							activa--;
							return true;
						}
						if (Nochocan.get(activa).get_dst().centerX() > Celdas[i][j].centerX() + 45) {
							Nochocan.get(activa)
									.get_dst()
									.set(Celdas[i][j].right - 30, Celdas[i][j].top,
											Celdas[i][j].right, Celdas[i][j].top + 250);
							aux.set_position(3);
							map.crearReja(j, i, 1);
							Nochocan.remove(activa);
							id--;
							flag=false;
							activa--;
							return true;
						}
						if (Nochocan.get(activa).get_dst().centerX() < Celdas[i][j].centerX() - 45) {
							Nochocan.get(activa)
									.get_dst()
									.set(Celdas[i][j].left, Celdas[i][j].top,
											Celdas[i][j].left + 30, Celdas[i][j].top + 250);
							aux.set_position(1);
							map.crearReja(j, i, 3);
							Nochocan.remove(activa);
							id--;
							flag=false;
							activa--;
							return true;
						}

					}
				}
			}}
			flag = false;
			break;
		}

		return true;

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
			for (Iterator<reja> I = tmp.Get_Rejas().iterator(); I.hasNext();) {
				reja Itmp = (reja) I.next();
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
	public boolean yaexiste2(int id) {// este lo ocupo en la linea 251 y es para lo del pico vs reja
		for (int i = 0; i < Rejas2.size(); i++) {
			if (id == Rejas2.get(i).get_id()) {
				return true;
			}
		}
		return false;
	}
public boolean estaencuadro(int id){// este metodo busca si el id pertenece a una reja de algun cuadro
	for(int i=0; i<map.Area.length;i++){
		for(int j=0; j<map.Area[0].length;j++){
			for(int k=0; k<4;k++){
				if(map.Area[i][j].rejas[k].get_id()==id){
					return true;
				}
			}
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

	public void set_canvas(Canvas canvas) {
		this.canvas = canvas;
	}

}