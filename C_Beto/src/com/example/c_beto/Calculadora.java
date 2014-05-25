package com.example.c_beto;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculadora extends Activity implements OnClickListener {
	double operando1,operando2;
	Button btn1,btn2,btn3, btn4, btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17; //importar librerías Ctrl + shift + O
	EditText resultado;
	int operando=0;
	double solve=0;
	int cont=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculadora);
		
		resultado = (EditText ) findViewById(R.id.editResultado);
		btn1 = (Button) findViewById(R.id.C);
		btn1.setOnClickListener(this);
		btn2 = (Button) findViewById(R.id.F);
		btn2.setOnClickListener(this);
		btn3 = (Button) findViewById(R.id.button3);
		btn3.setOnClickListener(this);
		btn4 = (Button) findViewById(R.id.button4);
		btn4.setOnClickListener(this);
		btn5 = (Button) findViewById(R.id.button5);
		btn5.setOnClickListener(this);
		btn6 = (Button) findViewById(R.id.button6);
		btn6.setOnClickListener(this);
		btn7 = (Button) findViewById(R.id.button7);
		btn7.setOnClickListener(this);
		btn8 = (Button) findViewById(R.id.button8);
		btn8.setOnClickListener(this);
		btn9 = (Button) findViewById(R.id.button9);
		btn9.setOnClickListener(this);
		btn10 = (Button) findViewById(R.id.button0); //Hola, es el numero cero
		btn10.setOnClickListener(this);
		btn11 = (Button) findViewById(R.id.buttondiv);
		btn11.setOnClickListener(this);
		btn12 = (Button) findViewById(R.id.buttonmul);
		btn12.setOnClickListener(this);
		btn13 = (Button) findViewById(R.id.buttonmen);
		btn13.setOnClickListener(this);
		btn14 = (Button) findViewById(R.id.buttonplus);
		btn14.setOnClickListener(this);
		btn15 = (Button) findViewById(R.id.buttondot);
		btn15.setOnClickListener(this);
		btn16 = (Button) findViewById(R.id.buttonCL);
		btn16.setOnClickListener(this);
		btn17 = (Button) findViewById(R.id.buttonresult);
		btn17.setOnClickListener(this);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculadora, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		String punto=".";
		
		switch(v.getId())
		{
		case R.id.button7: 
			resultado.append("7");
		break;
		case R.id.button8: 
			resultado.append("8");
		break;
		case R.id.button9: 
			resultado.append("9");
		break;
		case R.id.button4: 
			resultado.append("4");
		break;
		case R.id.button5: 
			resultado.append("5");
		break;
		case R.id.button6: 
			resultado.append("6");
		break;
		case R.id.C: 
			resultado.append("1");
		break;
		case R.id.F: 
			resultado.append("2");
		break;
		case R.id.button3: 
			resultado.append("3");
		break;
		case R.id.button0: 
			resultado.append("0");
		break;
		
		case R.id.buttondiv: operando=1;
		cont=0;
        //resultado = (EditText ) findViewById(R.id.editResultado);
		Log.i("C_beto","clic en /");
		operando1= Double.parseDouble(resultado.getText().toString());
		Log.i("C_beto","operador1"+operando1);
		Log.i("C_beto","operacion es"+operando);
		resultado.setText(""); break;
		
		case R.id.buttonmul: operando=2;
		cont=0;
       // resultado = (EditText ) findViewById(R.id.editResultado);
		operando1= Double.parseDouble(resultado.getText().toString());
		resultado.setText(""); break;
		
		case R.id.buttonmen: operando=3; 
		cont=0;
       //resultado = (EditText ) findViewById(R.id.editResultado);
		operando1= Double.parseDouble(resultado.getText().toString());
		resultado.setText(""); break;
		
		case R.id.buttonplus: 
			cont=0;
			operando=4;  
	        //resultado = (EditText ) findViewById(R.id.editResultado);
			operando1= Double.parseDouble(resultado.getText().toString());
			resultado.setText(""); break;
			
		case R.id.buttondot:   
			if(cont==0)
			{resultado.setText(resultado.getText()+punto);
			cont=1;
			}
			
			break;
			
		case R.id.buttonCL: resultado.setText(""); break;
		
		case R.id.buttonresult:
			cont=0;
			//operando1 =Double.parseDouble.(resultado.getText().toString);
			operando2=Double.parseDouble(resultado.getText().toString());
			resultado.setText("");
			Log.i("C_beto","operacion"+operando);
			switch(operando)
			{
			case 1: 
				Log.i("C_beto","entrando a la división");
				solve=(operando1/operando2); 
			break;
			case 2: solve=operando1*operando2; break;
			case 3: solve=operando1-operando2; break;
			case 4: solve=operando1+operando2; break;
			}
			resultado.setText(""+solve);
		}
		
	}

}
