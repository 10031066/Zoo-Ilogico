package com.example.cursoandroid2;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {
	
	private int primerDigito;
	private int segundoDigito;
	private boolean flag = false;
	private int operacion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.i("curso", "se ejecuto onCreate");
		
		/*Persona rene = new Persona("Rene", true);
		
		Frances jacques = new Frances("Jacques", true, "JAQ3243536");
		Colombiano juana = new Colombiano("Juana",false, "DFSADF232423");
		
		jacques.comer();
		jacques.hablar();
		juana.comer();
		juana.hablar();
		*/
		
		final EditText editResultado = (EditText) this.findViewById(R.id.editResultado);
		Button b1 = (Button) this.findViewById(R.id.boton1);
		Button b2 = (Button) this.findViewById(R.id.boton2);
		Button b3 = (Button) this.findViewById(R.id.boton3);
		Button b4 = (Button) this.findViewById(R.id.boton4);
		Button b5 = (Button) this.findViewById(R.id.boton5);
		Button b6 = (Button) this.findViewById(R.id.boton6);
		Button b7 = (Button) this.findViewById(R.id.boton7);
		Button b8 = (Button) this.findViewById(R.id.boton8);
		Button b9 = (Button) this.findViewById(R.id.boton9);
		Button b0 = (Button) this.findViewById(R.id.boton0);
		Button bPlus = (Button) this.findViewById(R.id.botonPlus);
		Button bMinus = (Button) this.findViewById(R.id.botonMinus);
		Button bMultiply = (Button) this.findViewById(R.id.botonMultiply);
		Button bDivide = (Button) this.findViewById(R.id.botonDivide);
		Button bResult = (Button) this.findViewById(R.id.botonResult);
		Button bClear = (Button) this.findViewById(R.id.botonClear);
		
		
		bClear.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				editResultado.setText("");
			}
			
		});
		
		/**
		 *  Los listeners de los botones de operaciones simplemente fijan mediante un valor 
		 *  entero el tipo de operacion: 1 para suma, 2 para resta, 3 para multiplicacion y 4 para division.
		 *  Se obtiene el contenido del campo de texto y se asigna a la variable "primerDigito".
		 *  Finalmente se limpia el valor del campo de texto con la funcion setText con una cadena vacia.
		 */
		bPlus.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				operacion = 1;
				
				primerDigito = Integer.parseInt(editResultado.getText().toString());
				
				editResultado.setText("");
			}
			
		});
		
		bMinus.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				operacion = 2;
				
				primerDigito = Integer.parseInt(editResultado.getText().toString());
				
				editResultado.setText("");
			}
			
		});
		
		
		bMultiply.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				operacion = 3;
				
				primerDigito = Integer.parseInt(editResultado.getText().toString());
				
				editResultado.setText("");
				
			}
			
		});
		
		bDivide.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				operacion = 4;
				
				primerDigito = Integer.parseInt(editResultado.getText().toString());
				
				editResultado.setText("");
				
			}
			
		});
		
		
		/**
		 *  Definimos el onClick del boton "="
		 *  Usamos un switch para obtener que operacion se esta realizando,
		 *  se efectua la operacion matematica y se asigna al campo de texto
		 *  mediante la funcion setText
		 */
		bResult.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				// obtenemos el contenido del campo de texto 
				segundoDigito = Integer.parseInt(editResultado.getText().toString());
				int resultado = 0;
				
				//checamos que operacion se registro y ejecutamos la operacion matematica
				switch(operacion){
				case 1:
					
					resultado = primerDigito + segundoDigito;
					
					//mandamos el resultado al campo de texto 
					editResultado.setText(String.valueOf(resultado));
					
					break;
					
				case 2:
					
					resultado = primerDigito - segundoDigito;
					
					//mandamos el resultado al campo de texto 
					editResultado.setText(String.valueOf(resultado));
					
					break;
					
				case 3:
					
					resultado = primerDigito * segundoDigito;
					
					//mandamos el resultado al campo de texto 
					editResultado.setText(String.valueOf(resultado));
					
					break;
					
				case 4:
					
					resultado = primerDigito / segundoDigito;
					
					//mandamos el resultado al campo de texto 
					editResultado.setText(String.valueOf(resultado));
					
					break;
				}
			}
			
		});
		
		
		/**
		 *  Implementamos el click en los 10 numeros
		 *  simplemente agregamos el digito al contenido que tenga
		 *  el campo de texto con la funcion append
		 */
		
		
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
		
		b9.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				editResultado.append("0");
	
			}});
		
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		
		Log.i("curso", "se ejecuto onPause");
	}
	
	@Override
	protected void onRestart(){
		super.onRestart();
		
		Log.i("curso", "se ejecuto onRestart");
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		Log.i("curso", "se ejecuto onResume");
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		Log.i("curso", "se ejecuto onDestroy");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	

}
