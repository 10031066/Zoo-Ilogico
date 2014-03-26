package javaapplication2zoooologico;
public class Jaula {
    private int size;
    private int habitantes=0;
    
    void agregarAnimal(Salvaje ani){
        ani.origen=this;
        habitantes++;
    }
    
}
