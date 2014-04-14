package javaapplication2zoooologico;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Salvaje extends Animales{
    Jaula origen;
    boolean atacar=false;    
    
    @Override
    public void run() {
        //
        try {
            while(true){
                g.repaint();
                System.out.println(nombre+" corriendo");
                Thread.sleep(5000);
                setSalud(getSalud()-((origen.getHabitantes()*origen.getHabitantes())/origen.getSize()));
                if(getSalud()<=0){
                    setSalud(0);
                    atacar=true;
                    while(getSalud()<getMaxSalud()*40){//empieza a atacar!!!
                       if(origen.jaulaRota){//si la jaula esta rota
                           //es libre!!! ahora busca comida...
                           //debe buscar al comestible mas cercano e ir tras el
                           int indiceComida;
                           double distanciaMenor=999;
                           double distancia;
                           int comidaX,comidaY;
                           
                           /**for(int i=0;i<)
                           
                           
                           while(getSalud()<getMaxSalud()*40){ //mientras su salud mantenga el margen de atacante
                               ASasasdasd 
                               
                           }*/
                       }else{//a dañar la jaula
                           //busca la reja mas cercana
                           int indiceReja=0;
                           double distanciaMenor=999;
                           double distancia;
                           int jaulaX,jaulaY;//posiciones de la jaula
                           for(int i=0;i<origen.getDiametro();i++){
                               jaulaX=origen.rejas[i].iniI;
                               jaulaY=origen.rejas[i].iniJ;
                               distancia = Math.sqrt(Math.pow(posX-jaulaX,2)+Math.pow(posY-jaulaY,2));
                               if(distancia<distanciaMenor){
                                   distanciaMenor=distancia;
                                   indiceReja=i;
                               }
                           }
                           //ya teniendo la reja mas cercana, se debera mover hacia ella y empezar a atacar
                           
                           
                           while(getSalud()<getMaxSalud()*40){ //mientras su salud mantenga el margen de atacante
                               //atacando la reja
                               origen.rejas[indiceReja].Resistencia-=ataque;
                               Thread.sleep(1000);
                           }
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
    
    public Salvaje(int indice, Cuadro zona,int x,int y,Jaula origen,Canvas g) {
        this.zona=zona;
        this.posX=x;
        this.posY=y;
        this.origen= origen;
        
        switch (indice){
            case 8: //tigre
                crear(indice,"Tigre",true,300,20,1000,false,false,true,true,g);
                break;
            case 9: //Oso
                crear(indice,"Oso",true,250,15,900,false,false,true,true,g);
                break;
            case 10: //leon
                crear(indice,"Leon",true,250,20,900,false,false,true,true,g);
                break;
            case 11: //Avestruz
                crear(indice,"Avestruz",true,250,10,750,true,true,false,false,g);
                break;
            case 12: //Panda
                crear(indice,"Panda",true,210,15,800,false,true,false,true,g);
                break;
            case 13: //Cocodrilo
                crear(indice,"Cocodrilo",true,230,20,850,false,false,true,true,g);
                break;
            case 14: //Rinoceronte
                crear(indice,"Rinoceronte",true,350,30,1100,true,true,false,false,g);
                break;   
        }   
    }

}

