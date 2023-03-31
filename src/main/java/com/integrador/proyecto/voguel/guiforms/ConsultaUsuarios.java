package com.integrador.proyecto.voguel.guiforms;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.integrador.proyecto.voguel.clases.Conexion;

/**
 * 
 * @author PABLO
 *
 */
public class ConsultaUsuarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7412596220578878213L;
	/**
	 * 
	 * 
	 * 
	 */
	private JPanel panelPrincipal;
	private JTable tablaUsuario;
	private DefaultTableModel model = new DefaultTableModel();
	private static com.integrador.proyecto.voguel.clases.Conexion conexion = new Conexion();
	private static Connection cn;
	private static Statement stm;

	

	/**
	 * Create the frame.
	 */
	public ConsultaUsuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConsultaUsuarios.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setTitle("Usuarios Registrados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneContenedorTabla = new JScrollPane();
		panelPrincipal.add(scrollPaneContenedorTabla, BorderLayout.CENTER);
		
		tablaUsuario = new JTable();
		tablaUsuario.setModel(model);
		
		
		model.addColumn("id Usuario");
		model.addColumn("Nombre");
		
		llenarTabla();
		
		scrollPaneContenedorTabla.setViewportView(tablaUsuario);
	}
	
	
	private void llenarTabla() {
		
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery(
					"SELECT id,nombre FROM usuario;"
					);
			
			while(rset.next()) {
				Object[] filaDatos = new Object[2];
				filaDatos[0] = rset.getString(1);
				filaDatos[1] = rset.getString(2);
				
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
