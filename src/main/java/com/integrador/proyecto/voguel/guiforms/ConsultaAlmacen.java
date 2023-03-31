package com.integrador.proyecto.voguel.guiforms;



import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.integrador.proyecto.voguel.clases.Conexion;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * 
 * @author PABLO
 *
 */
public class ConsultaAlmacen extends JFrame {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = -4255434178588885171L;
	/**
	 * 
	 * 
	 * 
	 */
	private JPanel contentPane;
	private JTable tablaAlmacenes;
	private DefaultTableModel model = new DefaultTableModel();
	private static com.integrador.proyecto.voguel.clases.Conexion conexion = new Conexion();
	private static Connection cn;
	private static Statement stm;
	
	
	/**
	 * Create the frame.
	 */
	public ConsultaAlmacen() {
		setTitle("Almacenes registrados");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConsultaAlmacen.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneContenedorTabla = new JScrollPane();
		contentPane.add(scrollPaneContenedorTabla, BorderLayout.CENTER);
		
		tablaAlmacenes = new JTable();
		tablaAlmacenes.setModel(model);
		
		model.addColumn("id Almacen");
		model.addColumn("RFC gerente");
		model.addColumn("Nombre Gerente");
		model.addColumn("Descripcion");
		model.addColumn("Direccion");
		model.addColumn("Telefono");
		
		this.llenarTabla();
		
		scrollPaneContenedorTabla.setViewportView(tablaAlmacenes);
	}
	
	private void llenarTabla() {
		
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery(
					"SELECT "
					+ "almacen.id_almacen,"
					+ "gerente.rfc,"
					+ "gerente.nombre,"
					+ "almacen.descripcion,"
					+ "almacen.direccion,"
					+ "almacen.telefono"
					+ " FROM almacen INNER JOIN gerente ON almacen.id_gerente = gerente.id_gerente;"
					);
			
			
			int contadorRegistros = 0;
			
			while(rset.next()) {
				Object[] filaDatos = {
					rset.getInt(1),		//id_almacen
					rset.getString(2),	//rfc_gerente
					rset.getString(3),	//nombre gerente
					rset.getString(4),	//descripcion del almacen
					rset.getString(5),	//direccion del almacen
					rset.getString(6)	//telefono del almacen
				};
				
				model.addRow(filaDatos);
				
				contadorRegistros += 1;
			}
			
			System.out.println(contadorRegistros);
			
		}catch(SQLTimeoutException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}catch(SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}finally {
			try {
				
				if(rset != null) {
					rset.close();
				}
				if(stm != null) {
					stm.close();
				}
				if(cn != null) {
					cn.close();
				}
				
			}catch(SQLException er) {
				er.printStackTrace();
			}catch(Exception er) {
				er.printStackTrace();
			}
		}
	}

}
