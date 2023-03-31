package com.integrador.proyecto.voguel.clases;

import java.util.Objects;

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
	public Gerente() {
		
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(direccion, rfc, telefono);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Gerente)) {
			return false;
		}
		Gerente other = (Gerente) obj;
		return Objects.equals(direccion, other.direccion) && Objects.equals(rfc, other.rfc)
				&& Objects.equals(telefono, other.telefono);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Gerente = { rfc: ").append(this.getRfc());
		sb.append(", telefono: ").append(this.getTelefono());
		sb.append(", direccion: ").append(this.getDireccion());
		sb.append(super.toString());
		return sb.toString();
	}

	
	
	
	
}
