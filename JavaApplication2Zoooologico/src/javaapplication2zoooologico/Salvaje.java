package javaapplication2zoooologico;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Salvaje extends Animales{
    Jaula origen;
    boolean atacar=false;
    @Override
    public void run() {
        try {
            while(true){
                Thread.sleep(5000);
                setSalud(getSalud()-((origen.getHabitantes()*origen.getHabitantes())/origen.getSize()));
                if(getSalud()<=0){
                    setSalud(0);
                    atacar=true;
                    while(getSalud()<getMaxSalud()*40){
                        if(origen.getResistencia()>0){
                            origen.setResistencia(origen.getResistencia()-getAtaque());
                        }else{
                            atacando();
                        }
                        
                    }
                }
               
            }
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Salvaje.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void atacando(){
        //buscara al ser docil o humano mas cercando y atacara hasta que muera, o el estres(salud) mejore
        boolean seguir=true;
        while(seguir){
            
        }
        
    }
    
    public Salvaje(int indice) {
        switch (indice){
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
        }   
    }

}

