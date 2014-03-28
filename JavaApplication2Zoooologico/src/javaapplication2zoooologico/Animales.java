package javaapplication2zoooologico;
public class Animales {
    int indice;//para definir el animal 1 a 14
    String nombre; 
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
    
    private void crear(int indice,String nombre ,boolean salvaje, int salud, int ataque, int precio, boolean heno, boolean fruta, boolean pescado, boolean carne) {
        this.indice = indice;
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
    
    public Animales(int indice){
        switch (indice){
            case 1: //chango
                crear(indice,"Chango",false,60,0,250,false,true,false,true);
                break;
            case 2://elefante
                crear(indice,"Elefante",false,250,0,750,true,true,false,false);
                break;
            case 3://jirafa
                crear(indice,"Jirafa",false,200,0,650,true,true,false,false);
                break;
            case 4: // pingüino
                crear(indice,"Pingüino",false,100,0,400,false,true,true,false);
                break;
            case 5: //cebra
                crear(indice,"Cebra",false,130,0,500,true,true,false,false);
                break;
            case 6: //camello
                crear(indice,"Camello",false,180,0,600,true,true,false,false);
                break;
            case 7: //Foca
                crear(indice,"Foca",false,110,0,450,false,true,true,false);
                break;
            case 8: //tigre
                crear(indice,"Tigre",true,300,20,1000,false,false,true,true);
                break;
            case 9: //Oso
                crear(indice,"Oso",true,250,15,900,false,false,true,true);
                break;
            case 10: //leon
                crear(indice,"Leon",true,250,20,900,false,false,true,true);
                break;
            case 11: //Avestruz
                crear(indice,"Avestruz",true,250,10,750,true,true,false,false);
                break;
            case 12: //Panda
                crear(indice,"Panda",true,210,15,800,false,true,false,true);
                break;
            case 13: //Cocodrilo
                crear(indice,"Cocodrilo",true,230,20,850,false,false,true,true);
                break;
            case 14: //Rinoceronte
                crear(indice,"Rinoceronte",true,350,30,1100,true,true,false,false);
                break;
            default:
                
            
            
        }
        
    }
    
}
