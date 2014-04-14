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
    public Cuadro Area[][];
    Jaula ListaJaulas[]={null,null,null,null,null,null};
    int indJaulas=0;
    private int jaulaYtam;
    private int jaulaXtam;
    
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        for(int i=0;i<largo;i++){
            for(int j=0;j<ancho;j++){
                g.drawRect(i*50, j*50, 50, 50);
            }
        }
        //Dibuja las jaulas
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
        //Dibuja los animales
        if(ListaJaulas!=null)
        for(int i=0;i<ListaJaulas.length;i++){
            if(ListaJaulas[i]!=null)
            for(int j=0;j<ListaJaulas[i].inquilinos.length;j++){
                if(ListaJaulas[i].inquilinos[j]!=null)
                    ListaJaulas[i].inquilinos[j].Dibujate();
            }
        }
    }
    
    reja[] esJaula(int i,int j){//metodo que se usara antes de crear una jaula, para ver si en el menu se desplegara la opcion de crear jaula
        //los indices son de la posicion del cuadro no de los pixeles
        reja ListaDeRejas[]= new reja[28];
        int indice=0;
        int tamX=1;
        int tamY=1;
        //empezara a bsucar el borde superior de la jaula, osea, una reja
        
        while(!Area[i][j].getArriba()){
            i--;
            if(i<0)
                return null;
            
        }
        ListaDeRejas[indice]=Area[i][j].norte();
        indice++;
        
        while(!Area[i][j].getDerecha()){//agrega el resto de rejas de arriba de la jaula
            i++;
            if(j>=ancho){//si el indice j sale del area del mapa sale
               return null;
            }
            
            if(Area[i][j].getArriba()){ //si el cuadro de la derecha, tiene norte continua recorriendose
                ListaDeRejas[indice]=Area[i][j].norte(); 
                indice++;
            }else{
                return null;
            }
            
        }
        ListaDeRejas[indice]=Area[i][j].oeste();
        indice++;
        //System.out.println(2);
        
        while(!Area[i][j].getAbajo()){//agrega las rejas de la Derecha de ña jaula
            j++;
            if(i>largo)
                return null;
            if(Area[i][j].getDerecha()){
                ListaDeRejas[indice]=Area[i][j].oeste();
                indice++;
                
            }else{
                return null;
            }
            tamY++;
        }
        jaulaYtam=tamY;
        System.out.println("Tamaño en Y " + jaulaYtam);
        ListaDeRejas[indice]=Area[i][j].sur();
        //tamX=i;
        indice++;
        //System.out.println(3);
        
        while(!Area[i][j].getIzquierda()){ //agrega las rejas Abajo de la jaula
            i--;
            if(j>ancho)
                return null;
            System.out.println(Area[i][j].getAbajo() + " "+i+ " "+j);
            if(Area[i][j].getAbajo()){
                ListaDeRejas[indice]=Area[i][j].sur();
                indice++;
            }else{
                return null;
            }
            tamX++;
        }
        jaulaXtam=tamX;
        System.out.println("Tamaño en X " + jaulaXtam);
        ListaDeRejas[indice]=Area[i][j].este();
        indice++;
        
        //System.out.println(5);
        
        while(!Area[i][j].getArriba()){
            j--;
            if(i<0){
                return null;
            }
            if(Area[i][j].getIzquierda()){
                ListaDeRejas[indice]=Area[i][j].este();
                indice++;
            }else{
                return null;
            }
        }
        
        //System.out.println(6);
        while(Area[i][j].getArriba() && Area[i][j].norte().unico!=ListaDeRejas[0].unico ){
            ListaDeRejas[indice]=Area[i][j].este();
            indice++;
            i++;
        }
        //System.out.println(6);
        return ListaDeRejas;
    }
    
    
    void crearJaula(int iniX,int iniY, int ancho,int largo,reja lista[]){//suponiendo que la funcion ya resive las rejas 
        Jaula temp =new Jaula(ancho,largo,iniX,iniY,lista,Area,indJaulas);
        ListaJaulas[indJaulas] = temp;
        //indicar que cuadros pertenecer a esta jaula
        for(int i=ListaJaulas[indJaulas].possX;i<ListaJaulas[indJaulas].possX+ListaJaulas[indJaulas].ancho;i++){
            for(int j=ListaJaulas[indJaulas].possY;j<ListaJaulas[indJaulas].possY+ListaJaulas[indJaulas].largo;j++){
                System.out.println(i+" "+j);
                Area[i][j].Jaula=true;
                Area[i][j].NoJaula=indJaulas;
                Area[i][j].pertenece=ListaJaulas[indJaulas];
            }
        }
        indJaulas++;
    }
     
     
    public Mapa(int largo, int ancho) {
        this.largo = largo;
        this.ancho = ancho;
        Area = new Cuadro[largo][ancho];
        addMouseListener(new clickArea(this));
        
        for(int i=0;i<largo;i++){
            for(int j=0;j<ancho;j++){
                Area[i][j]=new Cuadro();
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
    private class clickArea extends MouseAdapter implements Runnable{
        Canvas espacio;
        private MouseEvent e;
        public clickArea(Canvas espacio) {
            this.espacio=espacio;
        }
        
        @Override
        public void run() {
            //System.out.println(e.getX()+"  "+e.getY());
            int x= e.getX()/50;
            int y= e.getY()/50;
            if(Area[x][y].Jaula){ //si ya es uan jaula mostrara un menu en el que mostrara para agregar animales
                int a= Integer.parseInt(JOptionPane.showInputDialog("Agregar Animal?\n8.-Tigre\n9.-Oso\n10.-Leon\n11.-Avestruz\n12.-Panda\n13.-Cocodrilo\n14.-Rinoceronte"));
                Salvaje nuevoAnimal= new Salvaje(a,Area[x][y] , e.getX(), e.getY(),Area[x][y].pertenece,Mapa.this);
                nuevoAnimal.start();
                Area[x][y].pertenece.agregarAnimal(nuevoAnimal);
                repaint();
            }else{
            
            int a= Integer.parseInt(JOptionPane.showInputDialog("Agregar reja?\n0.-Norte\n1.-Oeste\n2.-Sur\n3.-Este"));
            
            
            int k=x,l=y,m=x,n=y;
            reja nueva;
            
            switch (a){
                case 0:
                    m=x+1;
                    n=y;
                    nueva = new reja(1,k,l,m,n);
                    Area[x][y].NuevaNorte(nueva);
                    if(y-1>=0){
                        Area[x][y-1].NuevaSur(nueva); // Se igualan las rejas de "afuera" y adentro
                    }
                    break;
                case 1:
                    k+=1;
                    m+=1;
                    n+=1;
                    nueva = new reja(1,k,l,m,n);
                    Area[x][y].NuevaDerecha(nueva);
                    if(x+1<Area.length){
                        Area[x+1][y].NuevaIzquierda(nueva);
                    }
                    break;
                case 2:
                    l+=1;
                    m+=1;
                    n+=1;
                    nueva = new reja(1,k,l,m,n);
                    Area[x][y].NuevaSur(nueva);
                    if(y+1<Area[1].length){
                        Area[x][y+1].NuevaNorte(nueva);
                    }
                    
                    break;
                case 3:
                    n+=1;
                    nueva = new reja(1,k,l,m,n);
                    Area[x][y].NuevaIzquierda(nueva);
                    if(x-1>=0){
                        Area[x-1][y].NuevaDerecha(nueva);
                    }
                    break;
                default:
                    reja nuevaJaula[] = esJaula(x, y);
                    if(nuevaJaula!=null){
                        crearJaula(x, y, jaulaXtam, jaulaYtam, nuevaJaula);
                        
                        System.out.println("nueva jaula creada " + ListaJaulas[indJaulas-1].ancho +" " +ListaJaulas[indJaulas-1].largo);
                    }
                    break;
            }
            
            espacio.repaint();
            }
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            this.e=e;
            run();
        }

        
    }
}
