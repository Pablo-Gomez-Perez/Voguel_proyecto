package com.integrador.proyecto.voguel.guiforms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.integrador.proyecto.voguel.clases.Conexion;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormAsignarCostos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3514460852562452386L;
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private JPanel panelPrincipal;
	private JTextField txf_Costo;
	JComboBox<String> jcmb_idAlmacen = new JComboBox<String>();
	JComboBox<String> jcmb_idDestino = new JComboBox<String>();
	private static com.integrador.proyecto.voguel.clases.Conexion conexion = new Conexion();
	private static Connection cn;
	private static Statement stm;
	

	/**
	 * Create the frame.
	 */
	public FormAsignarCostos() {
		setTitle("Costos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormAsignarCostos.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 337, 166);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		llenarListaAlmacen();
		llenarListaDestino();
		
		JPanel panelCentralCampos = new JPanel();
		panelPrincipal.add(panelCentralCampos, BorderLayout.CENTER);
		panelCentralCampos.setLayout(new BoxLayout(panelCentralCampos, BoxLayout.Y_AXIS));
		
		Box horizontalBox = Box.createHorizontalBox();
		panelCentralCampos.add(horizontalBox);
		
		JLabel lblNewLabel = new JLabel("Almacen Origen");
		horizontalBox.add(lblNewLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		jcmb_idAlmacen.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				txf_Costo.setText("" + consultarCostoAsociado());
			}
		});
		
		
		horizontalBox.add(jcmb_idAlmacen);
		
		Component verticalStrut = Box.createVerticalStrut(8);
		panelCentralCampos.add(verticalStrut);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		panelCentralCampos.add(horizontalBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("Destino");
		horizontalBox_1.add(lblNewLabel_1);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_1);
		jcmb_idDestino.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				txf_Costo.setText("" + consultarCostoAsociado());				
			}
		});
		
		
		
		horizontalBox_1.add(jcmb_idDestino);
		
		Component verticalStrut_1 = Box.createVerticalStrut(8);
		panelCentralCampos.add(verticalStrut_1);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		panelCentralCampos.add(horizontalBox_2);
		
		JLabel lblNewLabel_2 = new JLabel("Costo asociado");
		horizontalBox_2.add(lblNewLabel_2);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_2);
		
		txf_Costo = new JTextField();
		horizontalBox_2.add(txf_Costo);
		txf_Costo.setColumns(10);
		
		JPanel panelBotonesAccion = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotonesAccion.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelPrincipal.add(panelBotonesAccion, BorderLayout.SOUTH);
		
		JButton btn_Asignar = new JButton("Asignar");
		btn_Asignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(consultarCostoAsociado() == 0) {
					ingresarNuevoCosto();
				}else {
					actualizarCostoAsociado();
				}
				
			}
		});
		
		JButton btn_Consultar = new JButton("Consultar");
		btn_Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txf_Costo.setText("" + consultarCostoAsociado());
				
			}
		});
		panelBotonesAccion.add(btn_Consultar);
		panelBotonesAccion.add(btn_Asignar);
	}
	
	/**
	 * 
	 * consulta en la BD la informacion del costo asociado a los dos indices asignados en 
	 * {@code jcmb_idDestino} y {@code jcmb_idAlmacen} y el resultado lo coloca en {@code txf_Costo}
	 * si la consulta es {@code Null} el texto en el campo será {@code Null}
	 * 
	 * @author PABLO
	 * 
	 * @throws SQLException en errores de conección o consulta
	 */
	private int consultarCostoAsociado() {
		
		ResultSet rset = null;
		int costoAsociado = 0;
		
		//this.txf_Costo.setText("");
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery(
					"SELECT costo FROM costos "
					+ "WHERE id_almacen = " + this.jcmb_idAlmacen.getSelectedItem().toString() + " AND "
					+ "id_destino = " + this.jcmb_idDestino.getSelectedItem().toString() + ";"
					);
			
			if(rset.next()) {
				costoAsociado = rset.getInt(1);
			}					
			
			System.out.println("costo: " + costoAsociado);
			
		}catch(SQLTimeoutException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}catch(SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return costoAsociado;
		
	}
	
	private void llenarListaAlmacen() {
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery("SELECT id_almacen FROM almacen");
			
			while(rset.next()) {
				this.jcmb_idAlmacen.addItem(rset.getString(1));
			}
			
		}catch(SQLTimeoutException er) {
			er.printStackTrace();
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
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
	
	private void llenarListaDestino() {
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery("SELECT id_destino FROM destino");
			
			while(rset.next()) {
				this.jcmb_idDestino.addItem(rset.getString(1));
			}
			
		}catch(SQLTimeoutException er) {
			er.printStackTrace();
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
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
	
	private void ingresarNuevoCosto() {
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			stm.execute(
					"INSERT INTO costos (id_almacen, id_destino, costo) VALUES("
					+ " " + this.jcmb_idAlmacen.getSelectedItem().toString() + ","
					+ " " + this.jcmb_idDestino.getSelectedItem().toString() + ","
					+ " " + this.txf_Costo.getText() + ");"
					);
			
		}catch(SQLTimeoutException er) {
			er.printStackTrace();
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
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
	
	private void actualizarCostoAsociado() {
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			stm.execute(
					"UPDATE costos SET "
					+ "costo = " + this.txf_Costo.getText() + " "
					+ "WHERE "
					+ "id_almacen = " + this.jcmb_idAlmacen.getSelectedItem().toString() + " "
					+ "AND id_destino = " + this.jcmb_idDestino.getSelectedItem().toString() + ";"
					);
			
		}catch(SQLTimeoutException er) {
			er.printStackTrace();
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
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
