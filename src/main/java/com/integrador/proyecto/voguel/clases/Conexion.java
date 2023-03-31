package com.integrador.proyecto.voguel.clases;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * 
 * @author PABLO
 *
 */
public class Conexion {

	private static final String controlador = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/voguel_db";
	private static final String user = "root";
	
	static {
		try {
			Class.forName(controlador);
		}catch(ClassNotFoundException er) {
			
			er.printStackTrace();
			System.out.println(er.getLocalizedMessage());
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error de controlador 01", JOptionPane.ERROR_MESSAGE);
			
		}catch(Exception er) {
			
			er.printStackTrace();
			System.out.println(er.getLocalizedMessage());
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error 'GENERAL'", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	/**
	 * metodo para conectar la app a la base de datos
	 */
	public Connection conection() {
		
		Connection conexion = null;

		try {
			
			//en caso de requerir contraseña en Mysql root colocarla en el parametro "" (vacio)
			conexion = DriverManager.getConnection(url, user, "");
			//JOptionPane.showMessageDialog(null, "Conexion establecida " + url, "MySQL",
				//	JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Conexion ok ||" + controlador);

		}catch (SQLException er) {

			er.printStackTrace(System.out);
			System.out.println(er.getLocalizedMessage());
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error de conexion 02", JOptionPane.ERROR_MESSAGE);
			
		} catch (Exception er) {

			er.printStackTrace(System.out);
			System.out.println(er.getLocalizedMessage());
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error de conexion 'GENERAL'",
					JOptionPane.ERROR_MESSAGE);
			
		}

		return conexion;
	}
	
	public static void testConexion() {
		Conexion conexion = new Conexion();
		Connection cn = null; 
		Statement stm = null;
		ResultSet rst = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rst = stm.executeQuery("SELECT * FROM usuario");
			
			while(rst.next()) {
				int id_usuario = rst.getInt(1);
				String nombre = rst.getString(2);
				String pswd = rst.getString(3);
				
				System.out.println(id_usuario + "-" + " | " +  nombre + " | " + pswd);
			}
			
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
		}
		
	}

	/**
	 * @return el controlador para establecer la coneccion a la bd local
	 */
	public static String getControlador() {
		return controlador;
	}

	/**
	 * @return la dirección de la bd local a la cual se establece la conexion
	 */
	public static String getUrl() {
		return url;
	}

	/**
	 * @return el usuario que accesa a la bd para ejecutar las respectivas sentencias de
	 * consulta actualizacion o agregar
	 */
	public static String getUser() {
		return user;
	}
	
	
}
