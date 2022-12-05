package com.integrador.proyecto.voguel.clases;

public class Gerente extends Usuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8454804272673696115L;
	/**
	 * 
	 * 
	 */
	
	private String rfc;
	private String telefono;
	private String direccion;
	
	public Gerente(int id_gerente, String nombre, String rfc, String telefono, String direccion) {
		super(id_gerente,nombre);
		this.setRfc(rfc);
		this.setTelefono(telefono);
		this.setDireccion(direccion);
	}
	
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
