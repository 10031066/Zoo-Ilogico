package javaapplication2zoooologico;
public class ListaAnimales {
    public Animales lista[];
    private int i=0;
    
    void agregar(Animales nuevo){
        lista[i]=nuevo;
        i++;
    }
    
}
