package com.example.cursoandroid;

import android.util.Log;

public class Frances extends Persona implements IAccionesHumanas{
	private String Ident;

	public Frances(String pNombre, boolean pGenero, String pIdent){
		super(pNombre,pGenero);
		Ident= pIdent;
		Log.i("curso", "el bebe no lloro");
	}

	
	public String getIdent() {
		return Ident;
	}

	public void setIdent(String ident) {
		Ident = ident;
	}


	@Override
	public void hablar() {
		Log.i("curso", "Je ne parle francois pas");
		
	}


	@Override
	public void comer() {
		Log.i("curso", "Comiendo con la boca abierta");
		
	}
	
	
}
