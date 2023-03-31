package com.integrador.proyecto.voguel.clases;

import java.util.Objects;

public class Almacen implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3128140276372109270L;
	/**
	 * 
	 * 
	 */
	private int id_almacen;
	private int id_gerente;
	private String descripcion;
	private String direccion;
	private String telefono;
	private int existencia;
	
	public Almacen(int id_almacen,int id_gerente, String descripcion, String direccion,
			String telefono) {
		
		this.setId_almacen(id_almacen);
		this.setId_gerente(id_gerente);
		this.setDescripcion(descripcion);
		this.setDireccion(direccion);
		this.setTelefono(telefono);
		
	}
	
	public Almacen(int id_gerente, String descripcion, String direccion, String telefono) {
		
		this.setId_gerente(id_gerente);
		this.setDescripcion(descripcion);
		this.setDireccion(direccion);
		this.setTelefono(telefono);
		
	}
	
	/**
	 * constructor vacio para usarse como JavaBean
	 */
	public Almacen() {
		
	}
	
	/**
	 * @return the id_almacen
	 */
	public int getId_almacen() {
		return id_almacen;
	}
	/**
	 * @param id_almacen the id_almacen to set
	 */
	public void setId_almacen(int id_almacen) {
		this.id_almacen = id_almacen;
	}
	/**
	 * @return the id_gerente
	 */
	public int getId_gerente() {
		return id_gerente;
	}
	/**
	 * @param id_gerente the id_gerente to set
	 */
	public void setId_gerente(int id_gerente) {
		this.id_gerente = id_gerente;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * @return the existencia
	 */
	public int getExistencia() {
		return existencia;
	}

	/**
	 * @param existencia the existencia to set
	 */
	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, direccion, existencia, id_almacen, id_gerente, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Almacen)) {
			return false;
		}
		Almacen other = (Almacen) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(direccion, other.direccion)
				&& existencia == other.existencia && id_almacen == other.id_almacen && id_gerente == other.id_gerente
				&& Objects.equals(telefono, other.telefono);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Almacen = { id_almacen: ").append(this.getId_almacen());
		sb.append(", id_gerente: ").append(this.getId_gerente());
		sb.append(", descripcion: ").append(this.getDescripcion());
		sb.append(", direccion:").append(this.getDireccion());
		sb.append(", telefono: ").append(this.getTelefono());
		sb.append(", existencia: ").append(this.getExistencia()).append(" }");
		return sb.toString();
	}

	
	
}
