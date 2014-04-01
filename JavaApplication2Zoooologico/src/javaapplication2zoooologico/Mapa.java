package javaapplication2zoooologico;
public class Mapa {
    int largo;
    int ancho;
    cuadro Area[][];
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
    
    }
}
