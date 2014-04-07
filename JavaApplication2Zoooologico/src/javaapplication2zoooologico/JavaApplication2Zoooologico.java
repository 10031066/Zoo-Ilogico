package javaapplication2zoooologico;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JavaApplication2Zoooologico {
    static inicio obj;
    public static void main(String[] args) {
        obj= new inicio("Zoo-Ilogico");
    }
    
    static class inicio extends JFrame{
        ListaAnimales listaDociles;
        ListaAnimales listaSalvajes;
        Mapa Zona;
        public inicio(String title) throws HeadlessException {
            super(title);
            setLayout(new GridLayout(1,1));
                        
            Zona= new Mapa(7, 7);
            Zona.repaint();
            
            
            add(Zona);
            
            setSize(800, 600);
            setResizable(false);
            setDefaultCloseOperation(inicio.EXIT_ON_CLOSE);
            setVisible(true);
        }
    }

    
}
