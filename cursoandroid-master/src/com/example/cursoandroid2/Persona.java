package com.example.cursoandroid2;

public class Persona {

	private int edad;
	private String nombre;
	private int estatura;
	private boolean genero;
	
	public Persona(String pNombre, boolean pGenero){
		
		this.setNombre(pNombre);
		this.setGenero(pGenero);
		this.setEdad(0);
		this.setEstatura(30);
		
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		
		if(edad < 0) this.edad = 0;
		else this.edad = edad;
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstatura() {
		return estatura;
	}

	public void setEstatura(int estatura) {
		this.estatura = estatura;
	}

	public boolean isGenero() {
		return genero;
	}

	public void setGenero(boolean genero) {
		this.genero = genero;
	}
	
}
