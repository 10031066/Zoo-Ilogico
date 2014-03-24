package javaapplication2zoooologico;
public class Animales {
    int nombre;//para definir el animal 1 a 14
    boolean salvaje; //si o no
    int salud; // funciona como indicador de hambre
        //si en un animal salvaje, baja a cero empezara a atacar
        //si es un animal docil baja a cero muere
        //si baja a un 10% del total bota una alarma o cambia de color la linea para avisar
    int ataque;
        //daño que los animales salvajes
    int precio; //costo del animal
    boolean heno; //come heno?
    boolean fruta;
    boolean pescado;
    boolean carne;
    
    private void crear(int nombre, boolean salvaje, int salud, int ataque, int precio, boolean heno, boolean fruta, boolean pescado, boolean carne) {
        this.nombre = nombre;
        this.salvaje = salvaje;
        this.salud = salud;
        this.ataque = ataque;
        this.precio = precio;
        this.heno = heno;
        this.fruta = fruta;
        this.pescado = pescado;
        this.carne = carne;
    }
    
    public Animales(int nombre){
        switch (nombre){
            case 1: //chango
                crear(nombre,false,60,0,250,false,true,false,true);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                break;
            case 14:
                break;
            default:
                
            
            
        }
        
    }
    
}
