package com.itc.machozoo2;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class Mapa{

	private int width;
	private int height;
	int SIZE = 250;
	int rows;
	int columns;
	List<Figura> Figuras;
	boolean flag = false, flagF=false;
	Paint paint = new Paint();
	private GameView gameView;
	private List<FoodSprite> food;
	int activa;
	int id;
	//
		public Cuadro Area[][];
		public Rect Celdas[][];
		List<Jaula> Jaulas;
		List<Sprite> ListaAnimales;
		
		private Bitmap[] Bmp;
	//

	public Mapa(GameView gameview, Bitmap Bmp[],List<Figura> figuras,int id, List<FoodSprite> food) {
		this.gameView = gameview;
		this.Figuras=figuras;
		Jaulas = gameview.Jaulas;
		width = 2500;
		Log.i("zoo", "Ancho: " + width);
		height = 1600;
		Log.i("zoo", "Alto: " + height);
		rows = height / SIZE;
		columns = width / SIZE;
		Area = new Cuadro[rows][columns];
		Celdas = new Rect[rows][columns];
		this.Bmp=Bmp;
		Log.i("zoo", "Tamaño: " + columns);
        this.id=id;
        this.food=food;
        		
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
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Area[i][j]=new Cuadro(Celdas[i][j],gameview);
			}
		}
		
	}
	


	@SuppressLint("WrongCall")
	public void onDraw(Canvas canvas) {
		//Rect src = new Rect(0, 0, 250, 250);
		// Log.i("zoo", ""+Celdas.length);
		for (int i = 0; i < Celdas.length; i++) {
			for (int j = 0; j < Celdas[0].length; j++) {
				/**paint.setARGB(255, 255, 0, 0);
				paint.setStyle(Style.STROKE);
				canvas.drawRect(Celdas[i][j], paint);
				*/
				Area[i][j].onDraw(canvas);
			}
		}
	}
	
	
	Jaula esJaula(int i,int j){//Si es Jaula la crea y agrega a la lista
		//Esta funcion se usa solamente si no s sabe si es o no una jaula
        //los indices son de la posicion del cuadro no de los pixeles
        System.out.println("Area "+i+","+j);
        List<reja> ListaDeRejas = new CopyOnWriteArrayList<reja>();;
        int tamX=1;
        int tamY=1;
        int jaulaYtam;
	    int jaulaXtam;
        //empezara a bsucar el borde superior de la jaula, osea, una reja
        
        while(Area[i][j].getArriba()==null){
            i--;
            if(i<0)
                return null;
            
        }
        System.out.println("arriba en "+i);
        ListaDeRejas.add(Area[i][j].getArriba());
        
        while(Area[i][j].getDerecha()==null){//agrega el resto de rejas de arriba de la jaula
            j++;
            if(j>=columns){//si el indice j sale del area del mapa sale
               return null;
            }
            
            if(Area[i][j].getArriba()!=null){ //si el cuadro de la derecha, tiene norte continua recorriendose
                ListaDeRejas.add(Area[i][j].getArriba()); 
            }else{
                return null;
            }
            
        }
        System.out.println("largo "+j);
        ListaDeRejas.add(Area[i][j].getDerecha());
        
        //System.out.println(2);
        
        while(Area[i][j].getAbajo()==null){//agrega las rejas de la Derecha de ña jaula
            i++;
            if(i>=rows)
                return null;
            if(Area[i][j].getDerecha()!=null){
                ListaDeRejas.add(Area[i][j].getDerecha());
                
            }else{
                return null;
            }
            tamY++;
        }
        jaulaYtam=tamY;
        System.out.println("Tamaño en Y " + jaulaYtam);
        ListaDeRejas.add(Area[i][j].getAbajo());
        //tamX=i;
        //System.out.println(3);
        
        while(Area[i][j].getIzquierda()==null){ //agrega las rejas Abajo de la jaula
            j--;
            if(j<0)
                return null;
            //System.out.println(Area[i][j].getAbajo() + " "+i+ " "+j);
            if(Area[i][j].getAbajo()!=null){
                ListaDeRejas.add(Area[i][j].getAbajo());
                
            }else{
                return null;
            }
            tamX++;
        }
        jaulaXtam=tamX;
        System.out.println("Tamaño en X " + jaulaXtam);
        ListaDeRejas.add(Area[i][j].getIzquierda());
        
        //System.out.println(5);
        
        while(Area[i][j].getArriba()==null){//Ahora sube buscando las rejas de laa izquierda
            i--;
            if(i<0){
                return null;
            }
            if(Area[i][j].getIzquierda()!=null){
                ListaDeRejas.add(Area[i][j].getIzquierda());
                
            }else{
                return null;
            }
        }
        int i2=i,j2=j;//La posicion inicial de la jaula
        //System.out.println(6);
        
        try{
        while(Area[i][j].getArriba().id!=ListaDeRejas.get(0).id ){
            ListaDeRejas.add(Area[i][j].getArriba());
            j++;
        }}catch(Exception e){
        	return null;
        }
        //System.out.println(6);
        crearJaula(j2,i2,jaulaXtam,jaulaYtam,ListaDeRejas);
        return Jaulas.get(Jaulas.size()-1);
    }

	void crearJaula(int iniJ,int iniI, int lonX,int lonY,List<reja> nuevaJaula){//suponiendo que la funcion ya resive las rejas
		System.out.println("Creando Jaula con "+nuevaJaula.size()+" rejas");
		for(int i=0;i<nuevaJaula.size();i++){
			System.out.println(nuevaJaula.get(i).id);
		}
		
        Jaulas.add(new Jaula(lonY,lonX,iniI,iniJ,nuevaJaula));
        //indicar que cuadros pertenecer a esta jaula
        for(int i=iniI;i<iniI+lonY;i++){
            for(int j=iniJ;j<iniJ+lonX;j++){
                //System.out.println(i+" "+j);
                Area[i][j].Jaula=true;
                Area[i][j].NoJaula=Jaulas.size()-1;
                Area[i][j].pertenece=Jaulas.get(Jaulas.size()-1);
            }
        }
        
    }
	
	void crearReja(int x,int y,int a){//x y y son las posiciones del cuadro
		//System.out.println("Creando reja "+x+","+y+","+a);
		reja nueva;
		//Rect temp1= new Rect(k,l,m,n);
		if(Area[y][x].Jaula){ //si el cuadro ya es una jaula, no se le pueden agregar mas rejas adentro
			return;
		}
		if(Area[y][x].rejas[a]!=null){
			System.out.println("ya existia una reja ahi");
			return;
		}
		
		switch (a){
        case 0:
            nueva = new reja(new Rect(x*250,y*250-15,x*250+250,y*250+15),gameView,false);
            Area[y][x].setArriba(nueva);

            if(y-1>=0){
                Area[y-1][x].setAbajo(nueva); // Se igualan las rejas de "afuera" y adentro
            }
            
            break;
        case 1:
            nueva = new reja(new Rect(x*250+235,y*250,x*250+265,y*250+250),gameView,true);
            Area[y][x].setDerecha(nueva);
            if(x+1<columns){
                Area[y][x+1].setIzquierda(nueva);
            }
            break;
        case 2:
            nueva = new reja(new Rect(x*250,y*250+235,x*250+250,y*250+265),gameView,false);
            Area[y][x].setAbajo(nueva);
            if(y+1<rows){
                Area[y+1][x].setArriba(nueva);
            }
            break;
        case 3:
            nueva = new reja(new Rect(x*250-15,y*250,x*250+15,y*250+250),gameView,true);
            Area[y][x].setIzquierda(nueva);
            if(x-1>=0){
                Area[y][x-1].setDerecha(nueva);
            }
            break;
        default:
        	System.out.println("Este mensaje no deberia nunca aparecer");
        	return;
        	/**Se usaba en el java, ya no se necesita
            List<reja> nuevaJaula = esJaula(x, y);
            if(nuevaJaula!=null){
                crearJaula(x, y, jaulaXtam, jaulaYtam, nuevaJaula);
                
                Log.i("zoo", "old= " + "Nueva Jaula Creada");
                //System.out.println("nueva jaula creada " + ListaJaulas[indJaulas-1].ancho +" " +ListaJaulas[indJaulas-1].largo);
            }*/
		}
		Figuras.add(nueva);
		gameView.Rejas.add(nueva);
		System.out.println(nueva.id);
	}

	public Rect[][] Get_Celdas() {
		return Celdas;
	}
}
