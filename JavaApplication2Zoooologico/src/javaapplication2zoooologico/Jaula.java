package javaapplication2zoooologico;
public class Jaula {

    
    private int size;//igual a ancho por largo
    private int ancho;
    private int largo;
    private int possI;
    private int possJ;
    private int habitantes=0;
    private int indice=0;
    private Animales inquilinos[];//lista de animales en esa jaula
    
    public Jaula(int ancho, int largo, int possI, int possJ) {
        this.ancho = ancho;
        this.largo = largo;
        this.possI = possI;
        this.possJ = possJ;
        this.size =ancho*largo;
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
