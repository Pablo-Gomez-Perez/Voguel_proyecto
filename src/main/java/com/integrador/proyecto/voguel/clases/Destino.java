package com.integrador.proyecto.voguel.clases;

import java.util.Objects;

public class Destino implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1640225838092539781L;
	/**
	 * 
	 * 
	 * 
	 */
	
	private int id_destino;
	private String descripcion;
	private String direccion;
	private String telefono;
	
	public Destino(int id_destino, String descripcion, String direccion, String telefono) {
		this.setId_destino(id_destino);
		this.setDescripcion(descripcion);
		this.setDireccion(direccion);
		this.setTelefono(telefono);
	}
	public Destino(String descripcion, String direccion, String telefono) {
		this.setDescripcion(descripcion);
		this.setDireccion(direccion);
		this.setTelefono(telefono);
	}
	/**
	 * constructor vacio para ser usado como javaBean
	 */
	public Destino() {
		
	}
	
	/**
	 * @return the id_destino
	 */
	public int getId_destino() {
		return id_destino;
	}
	/**
	 * @param id_destino the id_destino to set
	 */
	public void setId_destino(int id_destino) {
		this.id_destino = id_destino;
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Destino = { id_destino: ").append(this.getId_destino());
		sb.append(", descripcion: ").append(this.getDescripcion());
		sb.append(", direccion: ").append(this.getDireccion());
		sb.append(", telefono: ").append(this.getTelefono()).append(" }");
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, direccion, id_destino, telefono);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Destino)) {
			return false;
		}
		Destino other = (Destino) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(direccion, other.direccion)
				&& id_destino == other.id_destino && Objects.equals(telefono, other.telefono);
	}
	
	
}
