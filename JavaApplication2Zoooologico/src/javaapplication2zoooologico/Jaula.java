package javaapplication2zoooologico;
public class Jaula {
    private int size;
    private int resistenciaMax;
    private int resistencia;

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getSize() {
        return size;
    }
    private int habitantes=0;

    public int getHabitantes() {
        return habitantes;
    }
    private int ancho;
    private int largo;
    
    void agregarAnimal(Salvaje ani){
        ani.origen=this;
        habitantes++;
    }
    
}
