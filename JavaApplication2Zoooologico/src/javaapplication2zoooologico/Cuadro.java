package javaapplication2zoooologico;
public class Cuadro {
    boolean poss[]={false,false,false,false};//poss 1.- Arriba 2.-Derecha 3.-Abajo 4.-Izquierda
        boolean Jaula=false;//es parte de una jaula?
        int NoJaula; //Numero del indice de la Jaula a la que pertecene
        Jaula pertenece;
        //valor falso significa que no hay reja
        reja rejas[]={null,null,null,null};//hay reja??
        
        void NuevaNorte(reja ne){
            rejas[0]=ne;
            poss[0]=true;
        }
        void NuevaSur(reja ne){
            rejas[2]=ne;
            poss[2]=true;
        }
        void NuevaDerecha(reja ne){
            rejas[1]=ne;
            poss[1]=true;
        }
        void NuevaIzquierda(reja ne){
            rejas[3]=ne;
            poss[3]=true;
        }
        
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
            return poss[0];
        }
        
        boolean getDerecha(){
            return poss[1];
        }
        
        boolean getAbajo(){
            return poss[2];
        }
        boolean getIzquierda(){
            return poss[3];
        }
        
}
