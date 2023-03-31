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
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormAsignarDemandas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5192739541738815865L;
	/**
	 * 
	 * 
	 */
	private JPanel panelPrincipal;
	private JTable tableAsignarDemandas;
	private DefaultTableModel model = new DefaultTableModel();
	private static com.integrador.proyecto.voguel.clases.Conexion conexion = new Conexion();
	private static Connection cn;
	private static Statement stm;
	private JPanel panelInferiorBotones;
	private JButton btn_Actualizar;
	
	

	/**
	 * Create the frame.
	 */
	public FormAsignarDemandas() {
		setType(Type.UTILITY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormAsignarDemandas.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentralTabla = new JPanel();
		panelPrincipal.add(panelCentralTabla, BorderLayout.CENTER);
		panelCentralTabla.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneTablaDemandas = new JScrollPane();
		panelCentralTabla.add(scrollPaneTablaDemandas, BorderLayout.CENTER);
		
		
		tableAsignarDemandas = new JTable();
		tableAsignarDemandas.setModel(model);
		model.addColumn("id Destino");
		model.addColumn("Descripcion");
		model.addColumn("Demanda");
		llenarTablaDemandas();
		scrollPaneTablaDemandas.setViewportView(tableAsignarDemandas);
		
		panelInferiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelPrincipal.add(panelInferiorBotones, BorderLayout.SOUTH);
		
		btn_Actualizar = new JButton("Actualizar");
		btn_Actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDemandasEnBd();
			}
		});
		panelInferiorBotones.add(btn_Actualizar);
	}
	
	private void llenarTablaDemandas() {
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery(
					"SELECT "
					+ "id_destino,"
					+ "descripcion,"
					+ "demanda "
					+ "FROM destino;"
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
	
	private void actualizarDemandasEnBd() {
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			
			
			for(int i = 0; i<this.tableAsignarDemandas.getRowCount(); i++) {
				
				stm.execute(
						"UPDATE destino SET "
						+ "demanda = " + tableAsignarDemandas.getValueAt(i, 2) + " "
						+ "WHERE id_destino = " + tableAsignarDemandas.getValueAt(i, 0) + ";"
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
