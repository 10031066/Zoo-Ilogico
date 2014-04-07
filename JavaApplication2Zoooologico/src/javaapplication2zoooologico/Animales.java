package javaapplication2zoooologico;
abstract public class Animales extends Thread{

    public int getMaxSalud() {
        return MaxSalud;
    }
    protected int posX,posY;//Posiciones del animal
    private int indice;//para definir el animal 1 a 14
    private String nombre; 
    private boolean salvaje; //si o no
    private int salud; // funciona como indicador de hambre
    private int MaxSalud;
    public void setSalud(int salud) {
        this.salud = salud;
    }
    protected Mapa zona;
    public int getSalud() {
        return salud;
    }
        //si en un animal salvaje, baja a cero empezara a atacar
        //si es un animal docil baja a cero muere
        //si baja a un 10% del total bota una alarma o cambia de color la linea para avisar
    protected int ataque;

    public int getAtaque() {
        return ataque;
    }
        //daño que los animales salvajes
    private int precio; //costo del animal
    private boolean heno; //come heno?
    private boolean fruta;
    private boolean pescado;
    private boolean carne;
    
    public void crear(int indice,String nombre ,boolean salvaje, int salud, int ataque, int precio, boolean heno, boolean fruta, boolean pescado, boolean carne) {
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
    }
}