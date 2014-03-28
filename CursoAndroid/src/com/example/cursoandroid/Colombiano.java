package com.example.cursoandroid;

import android.util.Log;

public class Colombiano extends Persona implements IAccionesHumanas {
	
	
		private String curp;

		public Colombiano(String pNombre, boolean pGenero, String curp){
			super(pNombre,pGenero);
			this.setCurp(curp);
			Log.i("curso", "el bebe no lloro");
		}
		

	@Override
	public void hablar() {
		Log.i("curso", "Ola k asen");
		
	}

	@Override
	public void comer() {
		Log.i("curso", "mastica 100 veces");
		
	}


	public String getCurp() {
		return curp;
	}


	public void setCurp(String curp) {
		this.curp = curp;
	}

}
