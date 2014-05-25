import javax.swing.JOptionPane;


public class MainP {

	public static void main(String [ ] args){
		int n=0;
		JOptionPane.showMessageDialog(null,"Antes de continuar cree un archivo .txt \n " +
				"que contenga los valores a utilizar separados por \n" +
				"una coma (,) sin espacios y en una sola linea ");
		do{
		 n=Integer.parseInt(JOptionPane.showInputDialog("Indique el valor de N multiplo de 3"));
		}
		while(n%3!=0);
		String dir=JOptionPane.showInputDialog("Proporcione la direccion del archivo con los valores \n" +
				"ejemplo D:\\Documentos\\Nueva carpeta\\archivo.txt \n"+"Usar doble diagonal invertida");
		
		Simpson38 piña = new Simpson38(dir);
		System.out.println(piña.volumen(n));
		JOptionPane.showMessageDialog(null,"El valor del volumen es"+piña.volumen(n));
	}
}
