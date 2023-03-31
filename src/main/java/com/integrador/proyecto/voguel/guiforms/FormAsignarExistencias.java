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
import java.awt.Panel;
import java.awt.Button;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormAsignarExistencias extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -15794155927357091L;
	/**
	 * 
	 * 
	 * 
	 */
	private JPanel panelPrincipal;
	private JTable tablaExistenciasAlmacen;
	private DefaultTableModel model = new DefaultTableModel();
	private static com.integrador.proyecto.voguel.clases.Conexion conexion = new Conexion();
	private static Connection cn;
	private static Statement stm;

	

	/**
	 * Create the frame.
	 */
	public FormAsignarExistencias() {
		setType(Type.UTILITY);
		setTitle("Asignar exitencias");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormAsignarExistencias.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		Panel panelInferiorBotones = new Panel();
		FlowLayout fl_panelInferiorBotones = (FlowLayout) panelInferiorBotones.getLayout();
		fl_panelInferiorBotones.setAlignment(FlowLayout.RIGHT);
		panelPrincipal.add(panelInferiorBotones, BorderLayout.SOUTH);
		
		Button btn_Actualizar = new Button("Actualizar");
		btn_Actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				almacenarExistenciasEnBd();
				
			}
		});
		panelInferiorBotones.add(btn_Actualizar);
		
		Panel PanelCentralTabla = new Panel();
		panelPrincipal.add(PanelCentralTabla, BorderLayout.CENTER);
		PanelCentralTabla.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneTablaExistencias = new JScrollPane();
		PanelCentralTabla.add(scrollPaneTablaExistencias, BorderLayout.CENTER);
		
		tablaExistenciasAlmacen = new JTable();
		tablaExistenciasAlmacen.setModel(model);
		
		model.addColumn("id Almacen");
		model.addColumn("Descripcion");
		model.addColumn("Existencias");
		
		llenarTablaExistencias();
		
		scrollPaneTablaExistencias.setViewportView(tablaExistenciasAlmacen);
	}
	
	private void llenarTablaExistencias() {
		
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery(
					"SELECT "
					+ "almacen.id_almacen,"
					+ "almacen.descripcion,"
					+ "almacen.existencia "
					+ "FROM almacen;"
					);
			
			while(rset.next()) {
				Object[] filadatos = {
					rset.getInt(1),
					rset.getString(2),
					rset.getInt(3)
				};
				
				model.addRow(filadatos);
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
	
	private void almacenarExistenciasEnBd() {
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			
			
			for(int i = 0; i<this.tablaExistenciasAlmacen.getRowCount(); i++) {
				
				stm.execute(
						"UPDATE almacen SET "
						+ "existencia = " + tablaExistenciasAlmacen.getValueAt(i, 2) + " "
						+ "WHERE id_almacen = " + tablaExistenciasAlmacen.getValueAt(i, 0) + ";"
						);
				
			}
			
			JOptionPane.showMessageDialog(null, "Registro Actualizado", "Atencion", JOptionPane.INFORMATION_MESSAGE);
			
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
