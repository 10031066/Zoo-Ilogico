package com.example.cursoandroid;

public class Persona {

	private int edad,estatura;
	private String nombre;
	private boolean genero;
	
	public Persona(String pNombre,boolean pGenero){
		setNombre(pNombre);
		setEdad(0);
		setEstatura(30);
		setGenero(pGenero);
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getEstatura() {
		return estatura;
	}

	public void setEstatura(int estatura) {
		this.estatura = estatura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isGenero() {
		return genero;
	}

	public void setGenero(boolean genero) {
		this.genero = genero;
	}
	
}
