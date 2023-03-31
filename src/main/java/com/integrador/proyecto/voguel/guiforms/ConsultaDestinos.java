package com.integrador.proyecto.voguel.guiforms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.integrador.proyecto.voguel.clases.Conexion;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;

public class ConsultaDestinos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 920578488924031585L;
	/**
	 * 
	 * 
	 * 
	 */
	private JPanel panelPrincipal;
	private JTable tablaDestinos;
	private DefaultTableModel model = new DefaultTableModel();
	private static com.integrador.proyecto.voguel.clases.Conexion conexion = new Conexion();
	private static Connection cn;
	private static Statement stm;


	/**
	 * Create the frame.
	 */
	public ConsultaDestinos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConsultaDestinos.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setTitle("Destinos Registrados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneContenedorTabla = new JScrollPane();
		panelPrincipal.add(scrollPaneContenedorTabla, BorderLayout.CENTER);
		
		tablaDestinos = new JTable();
		tablaDestinos.setModel(model);
		
		model.addColumn("id Destino");
		model.addColumn("Descripcion");
		model.addColumn("Direccion");
		model.addColumn("telefono");
		
		llenarTabla();
		
		scrollPaneContenedorTabla.setViewportView(tablaDestinos);
	}
	
	private void llenarTabla() {
		
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery(
					"SELECT * FROM destino"
					);
			
			while(rset.next()) {
				
				Object[] filaDatos = {
						rset.getString(1),
						rset.getString(2),
						rset.getString(3),
						rset.getString(4)
				};
				
				model.addRow(filaDatos);
				
			}
			
			
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
