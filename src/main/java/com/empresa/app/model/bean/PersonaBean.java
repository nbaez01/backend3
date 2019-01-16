package com.empresa.app.model.bean;

import java.io.Serializable;

public class PersonaBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6253470380726385526L;
	/**
	 * 
	 */

	private int id;
	private String nombre;
	private String apepaterno;
	private String apematerno;
	private String dni;
	private int iddireccion;

	public PersonaBean() {
		// TODO Auto-generated constructor stub
	}

	public PersonaBean(int id, String nombre, String apepaterno, String apematerno, String dni, int iddireccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apepaterno = apepaterno;
		this.apematerno = apematerno;
		this.dni = dni;
		this.iddireccion = iddireccion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApepaterno() {
		return apepaterno;
	}

	public void setApepaterno(String apepaterno) {
		this.apepaterno = apepaterno;
	}

	public String getApematerno() {
		return apematerno;
	}

	public void setApematerno(String apematerno) {
		this.apematerno = apematerno;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getIddireccion() {
		return iddireccion;
	}

	public void setIddireccion(int iddireccion) {
		this.iddireccion = iddireccion;
	}

}
