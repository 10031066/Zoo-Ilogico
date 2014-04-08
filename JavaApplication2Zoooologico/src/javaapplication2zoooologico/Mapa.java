package javaapplication2zoooologico;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class Mapa extends Canvas{
    int largo;
    int ancho;
    cuadro Area[][];
    Jaula ListaJaulas[];
    int indJaulas=0;
    private int jaulaYtam;
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        for(int i=0;i<largo;i++){
            for(int j=0;j<ancho;j++){
                g.drawRect(i*50, j*50, 50, 50);
            }
        }
        g.setColor(Color.red);
        for(int i=0;i<largo;i++){
            for(int j=0;j<ancho;j++){
                if(Area[i][j].getArriba()){
                    g.drawLine(Area[i][j].rejas[0].iniI*50,Area[i][j].rejas[0].iniJ*50,Area[i][j].rejas[0].finI*50,Area[i][j].rejas[0].finJ*50);
                }
                if(Area[i][j].getDerecha()){
                    g.drawLine(Area[i][j].rejas[1].iniI*50,Area[i][j].rejas[1].iniJ*50,Area[i][j].rejas[1].finI*50,Area[i][j].rejas[1].finJ*50);
                }
                if(Area[i][j].getAbajo()){
                    g.drawLine(Area[i][j].rejas[2].iniI*50,Area[i][j].rejas[2].iniJ*50,Area[i][j].rejas[2].finI*50,Area[i][j].rejas[2].finJ*50);
                }
                if(Area[i][j].getIzquierda()){
                    g.drawLine(Area[i][j].rejas[3].iniI*50,Area[i][j].rejas[3].iniJ*50,Area[i][j].rejas[3].finI*50,Area[i][j].rejas[3].finJ*50);
                }
            }
        }
        
    }
    
    reja[] esJaula(int i,int j){//metodo que se usara antes de crear una jaula, para ver si en el menu se desplegara la opcion de crear jaula
        //los indices son de la posicion del cuadro no de los pixeles
        reja ListaDeRejas[] = null;
        int indice=0;
        int tamX=1;
        int tamY=1;
        //empezara a bsucar el borde superior de la jaula, osea, una reja
        
        while(!Area[i][j].getArriba()){
            i--;
            if(i<0)
                return null;
            
        }
        System.out.println(i+ "  "+j);
        ListaDeRejas[indice]=Area[i][j].norte();
        indice++;
        
        while(!Area[i][j].getDerecha()){//agrega el resto de rejas de arriba de la jaula
            j++;
            if(j>=largo)//si el indice j sale del area del mapa sale
               return null;
            if(!Area[i][j].getArriba()){ //si el cuadro de la derecha, tiene norte continua recorriendose
                tamX++;
                ListaDeRejas[indice]=Area[i][j].norte(); 
                indice++;
            }else{
                return null;
            }
        }
        ListaDeRejas[indice]=Area[i][j].oeste();
        
        while(!Area[i][j].getAbajo()){//agrega las rejas de la Derecha de ña jaula
            i++;
            if(i>ancho)
                return null;
            if(!Area[i][j].getDerecha()){
                tamY++;
                ListaDeRejas[indice]=Area[i][j].oeste();
                indice++;
            }else{
                return null;
            }
        }
        jaulaYtam=tamY;
        ListaDeRejas[indice]=Area[i][j].sur();
        tamX=i;
        
        while(!Area[i][j].getIzquierda()){ //agrega las rejas Abajo de la jaula
            j--;
            if(j<largo)
                return null;
            if(!Area[i][j].getAbajo()){
                
                ListaDeRejas[indice]=Area[i][j].sur();
                indice++;
            }else{
                return null;
            }
        }
        ListaDeRejas[indice]=Area[i][j].este();
        
        if(j<tamX)//asi sabremos si minimo recorrio lo que recorrio al principio
            return null;
        
        for(int k=0;k<tamY;k++){//Subira hasta donde inicio la jaula
            if(!Area[i][j].getIzquierda()){
                ListaDeRejas[indice]=Area[i][j].este();
                indice++;
                i--;
            }else{
                return null;
            }
        }
        
        while(!Area[i][j].getArriba() && Area[i][j].norte().unico!=ListaDeRejas[0].unico ){
            ListaDeRejas[indice]=Area[i][j].este();
            indice++;
            j++;
        }
        return ListaDeRejas;
    }
    
    
    void crearJaula(int iniX,int iniY, int ancho,int largo,reja lista[]){//suponiendo que la funcion ya resive las rejas 
        ListaJaulas[indJaulas] = new Jaula(ancho,largo,iniX,iniY,lista);
        indJaulas++;
    }
     
     
    public Mapa(int largo, int ancho) {
        this.largo = largo;
        this.ancho = ancho;
        Area = new cuadro[largo][ancho];
        addMouseListener(new clickArea(this));
        
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
    
    private class clickArea extends MouseAdapter {
        Canvas espacio;
        public clickArea(Canvas espacio) {
            this.espacio=espacio;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println(e.getX()+"  "+e.getY());
            int a= Integer.parseInt(JOptionPane.showInputDialog("Agregar reja?\n0.-Norte\n1.-Oeste\n2.-Sur\n3.-Este"));
            int x= e.getX()/50;
            int y= e.getY()/50;
            
            int k=x,l=y,m=x,n=y;
            reja nueva;
            
            switch (a){
                case 0:
                    m=x+1;
                    n=y;
                    nueva = new reja(1,k,l,m,n);
                    System.out.println(x+" "+y);
                    Area[x][y].NuevaNorte(nueva);
                    Area[x][y-1].NuevaSur(nueva); // Se igualan las rejas de "afuera" y adentro
                    break;
                case 1:
                    k+=1;
                    m+=1;
                    n+=1;
                    nueva = new reja(1,k,l,m,n);
                    Area[x][y].NuevaDerecha(nueva);
                    Area[x+1][y].NuevaIzquierda(nueva);
                    break;
                case 2:
                    l+=1;
                    m+=1;
                    n+=1;
                    nueva = new reja(1,k,l,m,n);
                    Area[x][y].NuevaSur(nueva);
                    Area[x][y+1].NuevaNorte(nueva);
                    break;
                case 3:
                    n+=1;
                    nueva = new reja(1,k,l,m,n);
                    Area[x][y].NuevaIzquierda(nueva);
                    Area[x-1][y].NuevaDerecha(nueva);
                    break;
                default:
                    reja nuevaJaula[] = esJaula(x, y);
                    if(nuevaJaula!=null){
                        crearJaula(x, y, nuevaJaula.length/jaulaYtam, jaulaYtam, nuevaJaula);
                        System.out.println("nueva jaula creada");
                    }
                    break;
            }
            
            espacio.repaint();
            
        }
    }
}
