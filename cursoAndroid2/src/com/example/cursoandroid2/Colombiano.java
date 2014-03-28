package com.example.cursoandroid2;

import android.util.Log;

public class Colombiano extends Persona implements IAccionesHumanas {

	private String curp;
	
	public Colombiano(String pNombre, boolean pGenero, String pCurp) {
		super(pNombre, pGenero);
		
		this.curp = pCurp;
		
		Log.i("curso", "El bebe lloro");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void hablar() {
		// TODO Auto-generated method stub
		Log.i("curso", "hola que hacen");
	}

	@Override
	public void comer() {
		// TODO Auto-generated method stub
		Log.i("curso","Mastica 100 la comida");
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

}
