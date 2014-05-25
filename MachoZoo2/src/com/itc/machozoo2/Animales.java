package com.itc.machozoo2;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Animales extends Sprite{
	public int getMaxSalud() {
        return MaxSalud;
    }
	public Hambre digestion;
    protected int posX,posY;//Posiciones del animal
    private int indice;//para definir el animal 1 a 14
    public String nombre; 
    private boolean salvaje; //si o no
    public int salud; // funciona como indicador de hambre
    protected int MaxSalud;

    //protected Cuadro zona; //un animal pertenece a un cuadro
    public int getSalud() {
        return salud;
    }
        //si en un animal salvaje, baja a cero empezara a atacar
        //si es un animal docil baja a cero muere
        //si baja a un 10% del total bota una alarma o cambia de color la linea para avisar
    public int ataque;

        //daño que los animales salvajes
    private int precio; //costo del animal
    private boolean heno; //come heno?
    private boolean fruta;
    private boolean pescado;
    private boolean carne;
    reja rejaVictima;
    Jaula origen;
    Animales AnimalVictima;
    
    public void crear(int indice,String nombre ,boolean salvaje, int salud, int ataque, int precio, boolean heno, boolean fruta, boolean pescado, boolean carne, Bitmap bmp) {
        this.indice = indice;
        this.nombre = nombre;
        this.salvaje = salvaje;
        this.MaxSalud = salud;
        this.ataque = ataque;
        this.precio = precio;
        this.heno = heno;
        this.fruta = fruta;
        this.pescado = pescado;
        this.carne = carne;
        this.salud=salud;
        this.bmp= bmp;
        this.width = (bmp.getWidth() / BMP_COLUMNS);// -67;
		this.height = bmp.getHeight() / BMP_ROWS;
		dst = new Rect(x,y,x+width,y+height);
		this.Figuras=gameView.Figuras;
    }
	
}
