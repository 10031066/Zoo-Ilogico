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
	private int SIZE = 250;
	private int rows;
	private int columns;
	List<Figura> Figuras;
	boolean flag = false, flagF=false;
	Paint paint = new Paint();
	private GameView gameView;
	private List<FoodSprite> food;
	int activa;
	int id;
	//
		int largo;
		int ancho;
		public Cuadro Area[][];
		public Rect Celdas[][];
		List<Jaula> Jaulas;
		List<Sprite> ListaAnimales;
		private int jaulaYtam;
	    private int jaulaXtam;
		private Bitmap[] Bmp;
	//

	public Mapa(GameView gameview, Bitmap Bmp[],List<Figura> figuras,int id, List<FoodSprite> food) {
		this.gameView = gameview;
		this.Figuras=figuras;
		Jaulas = new CopyOnWriteArrayList<Jaula>();
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
				
				Area[i][j]=new Cuadro(i*250,j*250,i*250+250,j*250+250,gameview);
                if(i==0){
                    Area[i][j].poss[0]=false;
                }
                if(i==largo-1){
                    Area[i][j].poss[2]=false;
                }
                if(j==0){
                    Area[i][j].poss[3]=false;
                }
                if(j==ancho-1){
                    Area[i][j].poss[1]=false;
                }

			}
		}
		
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
	
	void Modifica(int i,int j,int poss){//poss 1.- Arriba 2.-Derecha 3.-Abajo 4.-Izquierda
        Area[i][j].poss[poss]=!Area[i][j].poss[poss];
        
        switch (poss){
            case 1:
                Area[i-1][j].poss[poss]=!Area[i-1][j].poss[poss];
                break;
            case 2:
                Area[i][j+1].poss[poss]=!Area[i][j+1].poss[poss];
                break;
            case 3:
                Area[i+1][j].poss[poss]=!Area[i+1][j].poss[poss];
                break;
            case 4:
                Area[i][j-1].poss[poss]=!Area[i][j-1].poss[poss];
                break;
        }
        
    }

	@SuppressLint("WrongCall")
	public void onDraw(Canvas canvas) {
		//Rect src = new Rect(0, 0, 250, 250);
		// Log.i("zoo", ""+Celdas.length);
		for (int i = 0; i < Celdas.length; i++) {
			for (int j = 0; j < Celdas[0].length; j++) {
				paint.setARGB(255, 255, 0, 0);
				paint.setStyle(Style.STROKE);
				canvas.drawRect(Celdas[i][j], paint);
				
				Area[i][j].onDraw(canvas);
				
				
			}
		}
	}
	
	
	reja[] esJaula(int i,int j){//metodo que se usara antes de crear una jaula, para ver si en el menu se desplegara la opcion de crear jaula
        //los indices son de la posicion del cuadro no de los pixeles
        reja ListaDeRejas[]= new reja[28];
        int indice=0;
        int tamX=1;
        int tamY=1;
        //empezara a bsucar el borde superior de la jaula, osea, una reja
        
        while(!Area[i][j].getArriba()){
            i--;
            if(i<0)
                return null;
            
        }
        ListaDeRejas[indice]=Area[i][j].norte();
        indice++;
        
        while(!Area[i][j].getDerecha()){//agrega el resto de rejas de arriba de la jaula
            i++;
            if(j>=ancho){//si el indice j sale del area del mapa sale
               return null;
            }
            
            if(Area[i][j].getArriba()){ //si el cuadro de la derecha, tiene norte continua recorriendose
                ListaDeRejas[indice]=Area[i][j].norte(); 
                indice++;
            }else{
                return null;
            }
            
        }
        ListaDeRejas[indice]=Area[i][j].oeste();
        indice++;
        //System.out.println(2);
        
        while(!Area[i][j].getAbajo()){//agrega las rejas de la Derecha de ña jaula
            j++;
            if(i>largo)
                return null;
            if(Area[i][j].getDerecha()){
                ListaDeRejas[indice]=Area[i][j].oeste();
                indice++;
                
            }else{
                return null;
            }
            tamY++;
        }
        jaulaYtam=tamY;
        System.out.println("Tamaño en Y " + jaulaYtam);
        ListaDeRejas[indice]=Area[i][j].sur();
        //tamX=i;
        indice++;
        //System.out.println(3);
        
        while(!Area[i][j].getIzquierda()){ //agrega las rejas Abajo de la jaula
            i--;
            if(j>ancho)
                return null;
            System.out.println(Area[i][j].getAbajo() + " "+i+ " "+j);
            if(Area[i][j].getAbajo()){
                ListaDeRejas[indice]=Area[i][j].sur();
                indice++;
            }else{
                return null;
            }
            tamX++;
        }
        jaulaXtam=tamX;
        System.out.println("Tamaño en X " + jaulaXtam);
        ListaDeRejas[indice]=Area[i][j].este();
        indice++;
        
        //System.out.println(5);
        
        while(!Area[i][j].getArriba()){
            j--;
            if(i<0){
                return null;
            }
            if(Area[i][j].getIzquierda()){
                ListaDeRejas[indice]=Area[i][j].este();
                indice++;
            }else{
                return null;
            }
        }
        
        //System.out.println(6);
        while(Area[i][j].getArriba() && Area[i][j].norte().unico!=ListaDeRejas[0].unico ){
            ListaDeRejas[indice]=Area[i][j].este();
            indice++;
            i++;
        }
        //System.out.println(6);
        return ListaDeRejas;
    }

	void crearJaula(int iniX,int iniY, int ancho,int largo,reja lista[]){//suponiendo que la funcion ya resive las rejas
		
        Jaulas.add(new Jaula(ancho,largo,iniX,iniY,lista));
        //indicar que cuadros pertenecer a esta jaula
        for(int i=iniX;i<iniX+ancho;i++){
            for(int j=iniY;j<iniX+largo;j++){
                //System.out.println(i+" "+j);
                Area[i][j].EsJaula(true);
                Area[i][j].NoJaula=Jaulas.size()-1;
                Area[i][j].pertenece=Jaulas.get(Jaulas.size()-1);
            }
        }
       
    }
	
	void crearReja(int x,int y,int a){//x y y son las posiciones del cuadro
		
		reja nueva;
		//Rect temp1= new Rect(k,l,m,n);
		
		switch (a){
        case 0:
            nueva = new reja(1,new Rect(x*250,y*250-15,x*250+250,y*250+15),gameView,false,gameView.indice++);
            Figuras.add(nueva);
            System.out.println("Se creo una reja arriba");
            Area[x][y].NuevaNorte(nueva);
            if(y-1>=0){
                Area[x][y-1].NuevaSur(nueva); // Se igualan las rejas de "afuera" y adentro
            }
            
            break;
        case 1:
            nueva = new reja(1,new Rect(x*250+235,y*250,x*250+265,y*250+250),gameView,true,gameView.indice++);
            Area[x][y].NuevaDerecha(nueva);
            if(x+1<Area.length){
                Area[x+1][y].NuevaIzquierda(nueva);
            }
            Figuras.add(nueva);
            break;
        case 2:
            nueva = new reja(1,new Rect(x*250,y*250+235,x*250+250,y*250+265),gameView,false,gameView.indice++);
            Area[x][y].NuevaSur(nueva);
            if(y+1<Area[1].length){
                Area[x][y+1].NuevaNorte(nueva);
            }
            Figuras.add(nueva);
            break;
        case 3:
            nueva = new reja(1,new Rect(x*250-15,y*250,x*250+15,y*250+250),gameView,true,gameView.indice++);
            Area[x][y].NuevaIzquierda(nueva);
            if(x-1>=0){
                Area[x-1][y].NuevaDerecha(nueva);
            }
            Figuras.add(nueva);
            break;
        default:
            reja nuevaJaula[] = esJaula(x, y);
            if(nuevaJaula!=null){
                crearJaula(x, y, jaulaXtam, jaulaYtam, nuevaJaula);
                
                Log.i("zoo", "old= " + "Nueva Jaula Creada");
                //System.out.println("nueva jaula creada " + ListaJaulas[indJaulas-1].ancho +" " +ListaJaulas[indJaulas-1].largo);
            }
            break;
		}
		
	}

	public Rect[][] Get_Celdas() {
		return Celdas;
	}
}
