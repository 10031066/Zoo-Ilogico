import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class Simpson38 {
    private double h;
    private double puntos[];
    
    double aux=0;
    ArrayList<Float> valores= new ArrayList<Float>();
    
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    
	Simpson38(String dir){
		 try {
	       
	      
	         archivo = new File (dir);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	         //estas lineas leen los datos desde el archivo de texo 
	        
	         String linea []=br.readLine().split(",");
	         puntos= new double[linea.length];
	         for(int l=0; l<linea.length;l++){
	         puntos[l]=Double.parseDouble(linea[l]);
	       
	         }
	        	 
	        	 
	         
	         
	        	
	        
	      } catch(Exception e){
	          e.printStackTrace();
	      }
		
	}
	
	public double volumen (int n){
		double volumen=0;
		double x[]=new double [n+1];
		double fx[] = new double [n+1];
		double sumatoria=0;


		
		if(puntos.length!=0){
			
			setH(((puntos[puntos.length-1])-puntos[0])/n);
			
		
			for(int i = 0; i<x.length;i++){
								
				if(i==0){
					x[i]=puntos[0];
				}
				else{
					x[i]=x[i-1]+h;
				}
			}
			
			for(int j=0; j<x.length;j++){
				if(j==0||j==x.length-1){
					fx[j]=evaluar(x[j]);//primer y ultimo valor por 1
				}
				else{
					if((j%3)==0){
						fx[j]=2*evaluar(x[j]);//multiplos de 3 por 2
					}
					else{
						
							fx[j]=3*evaluar(x[j]); //No multiplos de 3 
							                       //se multiplican por 3
					   
					}
				}
				
				sumatoria=sumatoria+fx[j];
				System.out.println(fx[j]);
			}
			
			volumen=(((3*h)/8)*sumatoria)*Math.PI; //se hace (3*h)/pi del metodo 3/8 y se multiplica 
			                                      //por pi por ser un solido de revolucion
			
			
			
		}
		
		return volumen;
	}

	public double evaluar(double m){
		//y = -0.0018x4 + 0.0533x3 - 0.5654x2 + 2.5454x + 1.458
		double x;
		x=Math.pow(-0.0018*Math.pow(m,4)+.0533*Math.pow(m, 3)-0.5654*Math.pow(m,2)+2.5454*m+1.458,2);
		
		return x;
	}
	
	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}

	
	
}
