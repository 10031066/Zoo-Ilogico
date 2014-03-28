import javax.swing.JOptionPane;


public class Main  {
	
public static void main(String[] args) {
	int n = Integer.parseInt(JOptionPane.showInputDialog("Tamaño"));
	double matriz [][]= new double [n][n];
	MatrixLite mat = new MatrixLite(matriz);
	do{
	
	for(int i=0; i<n; i++){
		for(int j=0; j<n; j++){
			matriz[i][j]=Double.parseDouble((JOptionPane.showInputDialog("Valor"+i+","+j)));}}
	
	for(int i=0; i<n; i++){
		for(int j=0; j<n; j++){
			System.out.print(matriz[i][j]);
			}
		System.out.print("\n");
		}
	
	System.out.println(mat.isDiagonallyDominant());
	if(mat.isDiagonallyDominant()){
		JOptionPane.showMessageDialog(null, "Dominante");
	}
	
	}while(!mat.isDiagonallyDominant());
		
	
	
	
	
}
}
 
