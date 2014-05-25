package com.example.cursoandroid;



import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	int primerdigito;
	int segundodigito;
	boolean flag;
	int operacion;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        Log.i("curso", "se ejecuto onCreate");
        
        //--------------------------------------------------------------------------//
       /* Persona Nelther = new Persona("Nelther",true);
        
        Frances jacques = new Frances("Jacques",true,"JAQ32");
        Colombiano juana = new Colombiano("Juana",false,"1234");
        
        jacques.comer();
        jacques.hablar();
        juana.comer();
        juana.hablar();
        */
        //super.onPause();
       // super.onRestart();
      //  super.onResume();
     //   super.onDestroy()
    //---------------------------------------------------------------------------//    
        
     final EditText editResultado = (EditText) this.findViewById(R.id.editResultado);
     Button b0 = (Button) findViewById(R.id.button0);
     Button b1 = (Button) findViewById(R.id.bsend);
     Button b2 = (Button) findViewById(R.id.button2);
     Button b3 = (Button) findViewById(R.id.button3);
     Button b4 = (Button) findViewById(R.id.button4);
     Button b5 = (Button) findViewById(R.id.button5);
     Button b6 = (Button) findViewById(R.id.button6);
     Button b7 = (Button) findViewById(R.id.button7);
     Button b8 = (Button) findViewById(R.id.button8);
     Button b9 = (Button) findViewById(R.id.button9);
     Button bdv = (Button) findViewById(R.id.buttondiv);//boton division
     Button bml = (Button) findViewById(R.id.buttonmul);//boton multiplicacion
     Button bsum = (Button) findViewById(R.id.buttonplus);//boton suma
     Button bmin = (Button)findViewById(R.id.buttonmen);//boton resta
     Button big = (Button)findViewById(R.id.buttonig);//boton igual
     Button bC= (Button)findViewById(R.id.buttonCL);//boton limpiar
     
		bC.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				editResultado.setText("");
			}
			
		});
     
     bsum.setOnClickListener(new OnClickListener(){

 		@Override
 		public void onClick(View v) {
 			// TODO Auto-generated method stub
 		operacion=1;
 		primerdigito = Integer.parseInt(editResultado.getText().toString());
		
		editResultado.setText("");
 		}
     	 
      });
     

     bmin.setOnClickListener(new OnClickListener(){
    	
    	 public void onClick(View V){
    	 operacion = 2;
			
			primerdigito = Integer.parseInt(editResultado.getText().toString());
			
			editResultado.setText("");
    	 }
    	 
     });
     
     bml.setOnClickListener(new OnClickListener(){
     	
    	 public void onClick(View V){
    	 operacion = 3;
			
			primerdigito = Integer.parseInt(editResultado.getText().toString());
			
			editResultado.setText("");
    	 }
    	 
     });
     
     bdv.setOnClickListener(new OnClickListener(){
     	
    	 public void onClick(View V){
    	 operacion = 4;
			
			primerdigito = Integer.parseInt(editResultado.getText().toString());
			
			editResultado.setText("");
    	 }
    	 
     });
  
     
     big.setOnClickListener(new OnClickListener(){

 		@Override
 		public void onClick(View v) {
 			// obtenemos el contenido del campo de texto 
			segundodigito = Integer.parseInt(editResultado.getText().toString());
			int resultado = 0;
			
			//checamos que operacion se registro y ejecutamos la operacion matematica
			switch(operacion){
			case 1:
				
				resultado = primerdigito + segundodigito;
				
				//mandamos el resultado al campo de texto 
				editResultado.setText(String.valueOf(resultado));
				
				break;
				
			case 2:
				
				resultado = primerdigito - segundodigito;
				
				//mandamos el resultado al campo de texto 
				editResultado.setText(String.valueOf(resultado));
				
				break;
				
			case 3:
				
				resultado = primerdigito * segundodigito;
				
				//mandamos el resultado al campo de texto 
				editResultado.setText(String.valueOf(resultado));
				
				break;
				
			case 4:
				
				resultado = primerdigito / segundodigito;
				
				//mandamos el resultado al campo de texto 
				editResultado.setText(String.valueOf(resultado));
				
				break;
			}
 		}
     	 
      });
     
     
     //Botones numericos//
        b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				editResultado.append("1");
				

			}});
		
		b2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				editResultado.append("2");
				
			
			}});
		
		b3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				editResultado.append("3");
			
			}});
		
		b4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				editResultado.append("4");
			
			}});
		
		b5.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				editResultado.append("5");
				
			
			}});
		
		b6.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				editResultado.append("6");
				

			}});
		
		b7.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				editResultado.append("7");
				
			}});
		
		b8.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				editResultado.append("8");

			}});
		
		b9.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				editResultado.append("9");

			}});
		
		b0.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				editResultado.append("0");
	
			}});
		
        //botones numericos//
    }
 
    @Override
    protected void onPause(){
    	super.onPause();
    	Log.i("cuerso", "se ejecuto onPause");
    }
    @Override
    protected void onRestart(){
    	super.onRestart();
    	Log.i("cuerso", "se ejecuto onRestart");
    }
    @Override
    protected void onResume(){
    	super.onResume();
    	Log.i("cuerso", "se ejecuto onResume");
    }
    @Override
    protected void onDestroy(){
    	super.onDestroy();
    	Log.i("cuerso", "se ejecuto Destroy");
    }
    
}
