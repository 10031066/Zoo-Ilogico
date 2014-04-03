package javaapplication2zoooologico;
public class reja {

    int tipo;
    int Resistencia;
    int ResistenciaMax;
    int iniI,iniJ;
    int finI,finJ;
    int estado;
    double unico;
    
    public reja(int tipo, int iniI, int iniJ, int finI, int finJ) {
        this.tipo = tipo;
        this.iniI = iniI;
        this.iniJ = iniJ;
        this.finI = finI;
        this.finJ = finJ;
        int res=0;
        if(tipo==1){
            //establece la resisistenciaMax del tipo 1
        }else{
            //establece la resisistenciaMax del tipo 2
        }
        this.Resistencia= res;
        this.ResistenciaMax=res;
        unico = Math.random();
    }
}
