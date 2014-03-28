package com.example.cursoandroid2;

import android.util.Log;

public class Frances extends Persona implements IAccionesHumanas{
	
	private String identificacion;

	public Frances(String pNombre, boolean pGenero, String pIdentificacion) {
		super(pNombre, pGenero);
		
		
		this.identificacion = pIdentificacion;
		//no lloran
		Log.i("curso","el bebe no lloro");
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	@Override
	public void hablar() {
		// TODO Auto-generated method stub
		Log.i("curso","Je ne parle francois pas");
		
	}

	@Override
	public void comer() {
		// TODO Auto-generated method stub
		Log.i("curso","Comiendo con la boca abierta");
	}
	
}
