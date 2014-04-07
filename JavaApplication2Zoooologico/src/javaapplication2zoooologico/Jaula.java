package javaapplication2zoooologico;
public class Jaula {
    private int size;//igual a ancho por largo
    protected int ancho;
    protected int largo;
    protected int possX; //posicion incial de la jaula
    protected int possY; //posicion incial de la jaula
    private int habitantes=0;
    boolean jaulaRota=false;
    private int indice=0;
    private Animales inquilinos[];//lista de animales en esa jaula
    public reja rejas[];
         
    public int getDiametro(){
        return 2*ancho*largo;
    }
    
    public int getSize() {
        return size;
    }
    
    public int getHabitantes() {
        return habitantes;
    }
    
    public Jaula(int ancho, int largo,int X,int Y, reja muros[]) {
        this.possX=X;
        this.possY=Y;
        this.ancho = ancho;
        this.largo = largo;
        this.size =ancho*largo;
        this.rejas = muros;
        
    }
    //las jaulas solo pueden ser rectangulares
    //las possioniones es donde empieza la jaula
    
    void agregarAnimal(Salvaje ani){
        ani.origen=this;//se le define a que jaula pertenece el animal
        habitantes++;
        inquilinos[indice]=ani; //referencia a que cuadro de la jaula pertenece el animal
        indice++;
    }
    
}
