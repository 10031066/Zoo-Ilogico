package com.example.lolmachine;
public class JavaApplication46Ruleta {
    public static void main(String[] args) {
        int premio=0;
        giro jugar= new giro();
        for(int k=0;k<10;k++){
            int caja=5000;
        for(int i=0;i<100;i++){
            for(int j=0;j<1000;j++){
                caja++;
                premio=jugar.juego();
                caja-=premio;
                jugar.premios[1]=(int)(caja*.85);
                
            }
        }
        //System.out.println(caja);
        }
        System.out.println("Contadores");
        for(int i=0;i<14;i++){	
            System.out.println(i+".- "+jugar.contadores[i]);
        }
        
    }
}

class giro{
    int contadores[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    int caja=5000;
    int a;
    int b;
    int c;
    int premios[]={0,
        2000,//Jackpot
        125,//Seven
        125,//Diamond
        50,//Bar
        40,//$$$
        9,//Orange
        5,//Cherry
        80,//Diamond diamond Bar || Seven Seven Bar
        15,// ORange ORange Bar
        2,//Cherry Cherry Any
        1//Cherry Any Any
    };
    int juego(){
         a=Ruleta();
         b=Ruleta();
         c=Ruleta();
        if(a==b&&b==c){
            /**if(a==1){
                System.err.println("Jackpot");
            }*/
            contadores[a]++;
            return premios[a];
        }
        if(a==b ||b==c ||a==c){
            if(a==b){
                if(a==3 && c==4){//Diamond/Diamond/Bar
                    contadores[8]++;
                    return premios[8];
                }
                if(a==2 && c==4){//Seven Seven Bar
                    contadores[8]++;
                    return premios[8];
                }
                if(a==6 && c==4){//ORange Orange Bar
                    contadores[9]++;
                    return premios[9];
                }
                if(a==7){
                    contadores[10]++;
                    return premios[10];
                }
            }            
            if(a==c){
                if(a==3 && b==4){//Diamond/Diamond/Bar
                    contadores[8]++;
                    return premios[8];
                }
                if(a==2 && b==4){//Seven Seven Bar
                    contadores[8]++;
                    return premios[8];
                }
                if(a==6 && b==4){//ORange Orange Bar
                    contadores[9]++;
                    return premios[9];
                }
                if(a==7){
                    contadores[10]++;
                    return premios[10];
                }
            }else{//b==c
                if(b==3 && a==4){//Diamond/Diamond/Bar
                    contadores[8]++;
                    return premios[8];
                }
                if(b==2 && a==4){//Seven Seven Bar
                    contadores[8]++;
                    return premios[8];
                }
                if(b==6 && a==4){//ORange Orange Bar
                    contadores[9]++;
                    return premios[9];
                }
                if(b==7){
                    contadores[10]++;
                    return premios[10];
                }
                
            }
            if(a==7 ||b==7 ||c==7){
                contadores[11]++;
                return 1;
            }
        }
        contadores[0]++;
        return 0;
    }
    int Ruleta(){
        int x = (int)(Math.random()*44);
        if(x==0){
            return 1;//Jackpot
        }
        if(x==1 ||x==2){
            return 2;//Seven
        }
        if(x==3 || x==4){
            return 3;//Diamond
        }
        if(x>=5 && x<=8){
            return 4;//BAR
        }
        if(x>=9 && x<=12){
            return 5;//$$$
        }
        if(x>=13 && x<=19){
            return 6;//Orange
        }
        if(x>=20 && x<=31){
            return 7;//Cherry
        }
        return 8;//Defeat
    }  
}