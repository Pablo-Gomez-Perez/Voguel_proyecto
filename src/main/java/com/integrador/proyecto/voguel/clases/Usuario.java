package com.integrador.proyecto.voguel.clases;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5368541681691532071L;
	/**
	 * 
	 * 
	 */
	private int id;
	private String pswd;
	private String nombre;
	
	public Usuario(int id, String pswd, String nombre) {
		this.setId(id);
		this.setPswd(pswd);
		this.setNombre(nombre);
	}
	public Usuario(String pswd, String nombre) {
		this.setPswd(pswd);
		this.setNombre(nombre);
	}
	public Usuario(int id, String nombre) {
		this.setId(id);
		this.setNombre(nombre);
	}
	public Usuario() {
		
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return this.id;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getPswd() {
		return this.pswd;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return this.nombre;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, pswd);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		return id == other.id && Objects.equals(nombre, other.nombre) && Objects.equals(pswd, other.pswd);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Usuario = { id: ").append(this.getId());
		sb.append(", pswd: ").append(this.getPswd());
		sb.append(", nombre: ").append(this.getNombre());
		return sb.toString();
	}
	
	
}
