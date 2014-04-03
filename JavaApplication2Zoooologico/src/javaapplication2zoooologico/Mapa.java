package javaapplication2zoooologico;

import java.awt.Canvas;
import java.awt.Graphics;

public class Mapa extends Canvas{
    int largo;
    int ancho;
    cuadro Area[][];
    Jaula ListaJaulas[];
    int indJaulas=0;
    
    @Override
    public void paint(Graphics g) {
        /**for(int i=0;i<largo;i++){
            for(int j=0;j<ancho;j++){
                                
            }
        }*/
        dibujaJaulas(g);
    }
    
    private void dibujaJaulas(Graphics g) {
        for(int i=0;i<indJaulas;i++){
            g.drawRect(ListaJaulas[i].possX*50, ListaJaulas[i].possY*50, ListaJaulas[i].ancho*50, ListaJaulas[i].largo*50);
        }
    }
    Jaula esJaula(int i,int j){//metodo que se usara antes de crear una jaula, para ver si en el menu se desplegara la opcion de crear jaula
        //los indices son de la posicion del cuadro no de los pixeles
        reja ListaDeRejas[] = null;
        int indice=0;
        int tamI;
        int tamJ;
        //empezara a bsucar el borde superior de la jaula, osea, una reja
        
        while(!Area[i][j].getArriba()){
            i--;
            if(i<0)
                return null;
        }
        ListaDeRejas[indice]=Area[i][j].norte();
        
        while(!Area[i][j].getDerecha()){//agrega el resto de rejas de arriba de la jaula
            j++;
            if(j>=largo)//si el indice j sale del area del mapa sale
               return null;
            if(!Area[i][j].getArriba()){ //si el cuadro de la derecha, tiene norte continua recorriendose
                ListaDeRejas[indice]=Area[i][j].norte(); 
                indice++;
            }else{
                return null;
            }
        }
        ListaDeRejas[indice]=Area[i][j].oeste();
        
        while(!Area[i][j].getAbajo()){
            i++;
            if(i>ancho)
                return null;
            if(!Area[i][j].getDerecha()){
                ListaDeRejas[indice]=Area[i][j].oeste();
                indice++;
            }else{
                return null;
            }
        }
        ListaDeRejas[indice]=Area[i][j].sur();
        tamI=i;
        
        while(!Area[i][j].getIzquierda()){
            j--;
            if(j<largo ||j)
                return null;
            if(!Area[i][j].getAbajo()){
                ListaDeRejas[indice]=Area[i][j].sur();
                indice++;
            }else{
                return null;
            }
        }
        
        
        
        
        return true;
    }
    
    
    void crearJaula(int iniX,int iniY, int ancho,int largo,reja lista[]){//suponiendo que la funcion ya resive las rejas 
        ListaJaulas[indJaulas] = new Jaula(ancho,largo,iniX,iniY,lista);
        indJaulas++;
    }
     
     
    public Mapa(int largo, int ancho) {
        this.largo = largo;
        this.ancho = ancho;
        Area = new cuadro[largo][ancho];
        
        for(int i=0;i<largo;i++){
            for(int j=0;j<ancho;j++){
                Area[i][j]=new cuadro();
                if(i==0){
                    Area[i][j].poss[0]=false;
                }
                if(i==largo-1){
                    Area[i][j].poss[2]=false;
                }
                if(j==0){
                    Area[i][j].poss[3]=false;
                }
                if(j==ancho-1){
                    Area[i][j].poss[1]=false;
                }
            }
        }
    }
    
    void Modifica(int i,int j,int poss){//poss 1.- Arriba 2.-Derecha 3.-Abajo 4.-Izquierda
        Area[i][j].poss[poss]=!Area[i][j].poss[poss];
        
        switch (poss){
            case 1:
                Area[i-1][j].poss[poss]=!Area[i-1][j].poss[poss];
                break;
            case 2:
                Area[i][j+1].poss[poss]=!Area[i][j+1].poss[poss];
                break;
            case 3:
                Area[i+1][j].poss[poss]=!Area[i+1][j].poss[poss];
                break;
            case 4:
                Area[i][j-1].poss[poss]=!Area[i][j-1].poss[poss];
                break;
        }
        
    }
   
    class cuadro{
        boolean poss[]={true,true,true,true};//poss 1.- Arriba 2.-Derecha 3.-Abajo 4.-Izquierda
        //un valor verdadera significa que puede caminar por ahi, osea no hay reja
        reja rejas[]={null,null,null,null};//hay reja??
        
        reja norte(){
            return rejas[0];
        }
        reja sur(){
            return rejas[2];
        }
        reja oeste(){
            return rejas[1];
        }
        reja este(){
            return rejas[3];
        }
        
        boolean getArriba(){
            if(rejas[0]==null){
                return false;
            }
            return true;
        }
        
        boolean getDerecha(){
            if(rejas[1]==null){
                return false;
            }
            return true;
        }
        
        boolean getAbajo(){
            if(rejas[2]==null){
                return false;
            }
            return true;
        }
        boolean getIzquierda(){
            if(rejas[3]==null){
                return false;
            }
            return true;
        }
        
    }
}
