import java.lang.Math;
public class MatrixLite {

	private double [][] matrix;
	private int rows;
	private int columns;
	
	/**
	 * An accessor for rows.
	 */
	public int getRows()
	{
		return rows;
	}//end of getRows()
	
	/**
	 * An accessor for columns.
	 */
	public int getColumns()
	{
		return columns;
	}//end of getColumns()
	
	/**
	 * An accessor for matrix[][].
	 */
	public double[][] getMatrix()
	{
		return matrix;
	}
	
	/**
	 * Modifier for matrix[][].
	 * Inserts value at matrix[i][j].
	 */
	public void setElement(int i, int j, double value)
	{
		matrix[i][j]=value;
	}
	
	/**
	 * Constructor.
	 */
	public MatrixLite(double[][] m)
	{
		matrix=new double [m.length][m[0].length];
		this.rows=m.length;
		this.columns=m[0].length;
		for(int i=0; i<rows; i++)
			for(int j=0; j<columns; j++)
				matrix[i][j]=m[i][j];
	}//end of constructor
	
	
	/**
	 * Comparison of 2 matrices
	 * Returns 0 if matrices are equal, -1 otherwise.
	 */
	public int matrixComparison(MatrixLite toCompare)
	{
		double [][] toCompareM=toCompare.getMatrix();
		if(rows != toCompare.getRows())
			return -1;
		if(columns != toCompare.getColumns())
			return -1;
		for(int i=0; i<rows; i++)
			for(int j=0; j<columns; j++)
				if(matrix[i][j] != toCompareM[i][j])
					return -1;
		return 0;
	} 
	
	
	
	/**
	 * Displays a matrix on the screen.
	 */
	public void display()
	{
		if(rows>0 && columns>0)
		for(int i=0; i<rows; i++)
		   {
		   	for(int j=0; j<columns; j++)
		       System.out.print(matrix[i][j]+" ");
		    System.out.println();
		    }   
	}//end of display()
	
	
	
	/**
	 * Multiplies a matrix by a scalar.
	 */
	public void xscalar(double scalar)
	{
		for(int i=0; i<rows; i++)
		    for(int j=0; j<rows; j++)
		        matrix[i][j]*=scalar;
	}//end of xscalar()
	
			
	
	/**
	 * This method return the biggest in absolute value
	 * element of a matrix m.
	 */
	public double findAbsGreatest()
	{
		double greatest=Math.abs(matrix[0][0]);
		
		for(int i=0; i<rows; i++)
		    for(int j=0; j<columns; j++)
		        {
		        	if((Math.abs(matrix[i][j]))>greatest)
		        	   greatest=Math.abs(matrix[i][j]);
		        }
		return greatest;
	}//end of findGreatest()
	
	
	/**
	 * This method returns true if a matrix is diagonally
	 * dominant, false otherwise.
	 * Diagonally dominant matrix is a matrix where
	 * |a[i][i]| > Sum of |a[i][j]| where j!=i
	 */
	public boolean isDiagonallyDominant()
	{
		boolean retval=true;
		double sum=0;
		double ii;
		for(int i=0; i<rows; i++)
		{
			ii=Math.abs(matrix[i][i]);
			sum=0;
			for(int j=0; j<columns; j++)
			    if(i!=j)
			       sum+=Math.abs(matrix[i][j]);
			if(ii<=sum)
			{
				retval=false;
				break;
			}//if
		}//for
		
		return retval;    
		
	}//end of isDiagonallyDominant()
	
	
	/**
	 * Transposes a matrix.
	 */
	public void transpose()
	{
		double [][] temp;
		int tempint;
		temp=new double[columns][rows];
		for(int i=0; i<rows; i++)
		    for(int j=0; j<columns; j++)
		        temp[j][i]=matrix[i][j];
		matrix=temp;
		tempint=rows;
		rows=columns;
		columns=tempint;
	}//end of transpose()
	
	
	
	/**
	 * The main method.
	 * Used to test this class.
	 */
	public static void main(String[] args)
	{
		
	}
	
	
	
	
	
}//end of class

