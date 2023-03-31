package com.integrador.proyecto.voguel.guiforms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.border.TitledBorder;

import com.integrador.proyecto.voguel.clases.Almacen;
import com.integrador.proyecto.voguel.clases.Conexion;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author PABLO
 *
 */
public class FormAlmacen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8295313782942543373L;
	/**
	 * 
	 * 
	 * 
	 */
	private JPanel panelPrincipal;
	private JTextField txf_IdAlmacen;
	private JTextField txf_NombreGerente;
	private JTextField txf_Descripcion;
	private JTextField txf_Direccion;
	private JTextField txf_Telefono;
	private JComboBox<String> jcmb_RfcGerente = new JComboBox<String>();
	private static com.integrador.proyecto.voguel.clases.Conexion conexion = new Conexion();
	private static Connection cn;
	private static Statement stm;
	

	/**
	 * Create the frame.
	 */
	public FormAlmacen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormAlmacen.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setTitle("Almacen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperiorBarradeMenu = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelSuperiorBarradeMenu.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelPrincipal.add(panelSuperiorBarradeMenu, BorderLayout.NORTH);
		
		JMenuBar menuPrincipal = new JMenuBar();
		panelSuperiorBarradeMenu.add(menuPrincipal);
		
		JMenu menuArchivo = new JMenu("Archivo");
		menuPrincipal.add(menuArchivo);
		
		JMenuItem opcionConsultarRegistros = new JMenuItem("Ver Registros");
		opcionConsultarRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				abrirFormConsultaAlmacen();
				
			}
		});
		menuArchivo.add(opcionConsultarRegistros);
		
		JMenuItem opcionExportarRegistros = new JMenuItem("Exportar Registros");
		menuArchivo.add(opcionExportarRegistros);
		
		JMenuItem opcionSalir = new JMenuItem("Salir");
		menuArchivo.add(opcionSalir);
		
		JMenu menuVista = new JMenu("Vista");
		menuPrincipal.add(menuVista);
		
		JMenuItem opcionReajustarVentana = new JMenuItem("Reajustar ventana");
		menuVista.add(opcionReajustarVentana);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(339);
		menuPrincipal.add(horizontalStrut_4);
		
		JPanel panelCentralComponentes = new JPanel();
		panelCentralComponentes.setBorder(new EmptyBorder(12, 8, 16, 8));
		panelPrincipal.add(panelCentralComponentes, BorderLayout.CENTER);
		panelCentralComponentes.setLayout(new BoxLayout(panelCentralComponentes, BoxLayout.Y_AXIS));
		
		Box bx_cajaDatosBasicos = Box.createVerticalBox();
		bx_cajaDatosBasicos.setBorder(new TitledBorder(null, "Identificacion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentralComponentes.add(bx_cajaDatosBasicos);
		
		Box horizontalBox = Box.createHorizontalBox();
		bx_cajaDatosBasicos.add(horizontalBox);
		
		JLabel lblNewLabel = new JLabel("id Almacen");
		horizontalBox.add(lblNewLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		
		txf_IdAlmacen = new JTextField();
		horizontalBox.add(txf_IdAlmacen);
		txf_IdAlmacen.setColumns(10);
		
		Component verticalStrut_1 = Box.createVerticalStrut(5);
		bx_cajaDatosBasicos.add(verticalStrut_1);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		bx_cajaDatosBasicos.add(horizontalBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("RFC Gerente");
		horizontalBox_1.add(lblNewLabel_1);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_1);
		
		cargarListboxRFC();
		jcmb_RfcGerente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				obtenerNombreGerente();
				
			}
		});
		horizontalBox_1.add(jcmb_RfcGerente);
		
		Component verticalStrut_2 = Box.createVerticalStrut(5);
		bx_cajaDatosBasicos.add(verticalStrut_2);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		bx_cajaDatosBasicos.add(horizontalBox_2);
		
		JLabel lblNewLabel_2 = new JLabel("Gerente");
		horizontalBox_2.add(lblNewLabel_2);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_2);
		
		txf_NombreGerente = new JTextField();
		horizontalBox_2.add(txf_NombreGerente);
		txf_NombreGerente.setColumns(10);
		
		Component verticalStrut_3 = Box.createVerticalStrut(5);
		bx_cajaDatosBasicos.add(verticalStrut_3);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		bx_cajaDatosBasicos.add(horizontalBox_3);
		
		JLabel lblNewLabel_3 = new JLabel("Descripcion");
		horizontalBox_3.add(lblNewLabel_3);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_3);
		
		txf_Descripcion = new JTextField();
		horizontalBox_3.add(txf_Descripcion);
		txf_Descripcion.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(8);
		panelCentralComponentes.add(verticalStrut);
		
		Box bx_cajaDatosContacto = Box.createVerticalBox();
		bx_cajaDatosContacto.setBorder(new TitledBorder(null, "Datos de contacto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentralComponentes.add(bx_cajaDatosContacto);
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		bx_cajaDatosContacto.add(horizontalBox_4);
		
		JLabel lblNewLabel_4 = new JLabel("Direccion");
		horizontalBox_4.add(lblNewLabel_4);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_5);
		
		txf_Direccion = new JTextField();
		horizontalBox_4.add(txf_Direccion);
		txf_Direccion.setColumns(10);
		
		Component verticalStrut_4 = Box.createVerticalStrut(5);
		bx_cajaDatosContacto.add(verticalStrut_4);
		
		Box horizontalBox_5 = Box.createHorizontalBox();
		bx_cajaDatosContacto.add(horizontalBox_5);
		
		JLabel lblNewLabel_5 = new JLabel("Telefono");
		horizontalBox_5.add(lblNewLabel_5);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_5.add(horizontalStrut_6);
		
		txf_Telefono = new JTextField();
		horizontalBox_5.add(txf_Telefono);
		txf_Telefono.setColumns(10);
		
		JPanel panelInferiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panelPrincipal.add(panelInferiorBotones, BorderLayout.SOUTH);
		
		JButton btn_limpiarForm = new JButton("Limpiar");
		btn_limpiarForm.addActionListener(new ActionListener() {
			/**
			 * 
			 * se limpian los campos del formulario
			 */
			public void actionPerformed(ActionEvent e) {
				
				limpiarCamposFormulario();
				
			}
		});
		panelInferiorBotones.add(btn_limpiarForm);
		
		JButton btn_Consultar = new JButton("consultar");
		btn_Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
											
				if(txf_IdAlmacen.getText().isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Ingrese el id a consultar", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					limpiarCamposFormulario();
					consultarAlmacen();
				}

			}
		});
		
		JButton btn_EliminarAlmacen = new JButton("Eliminar");
		btn_EliminarAlmacen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txf_IdAlmacen.getText().isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Ingrese el id del almacen", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					eliminarAlmacen();
					JOptionPane.showMessageDialog(null, "Registro Eliminado", "Atencion", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		panelInferiorBotones.add(btn_EliminarAlmacen);
		panelInferiorBotones.add(btn_Consultar);
		
		JButton btn_Actualizar = new JButton("Actualizar");
		btn_Actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarCamposVacios() == true) {
					JOptionPane.showMessageDialog(null, "No deje campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					Almacen almacen = obtenerAlmacen();
					actualizarAlmacen(almacen);
					JOptionPane.showMessageDialog(null, "Registro actualizado", "Atencion", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		panelInferiorBotones.add(btn_Actualizar);
		
		JButton btn_Agregar = new JButton("Agregar");
		btn_Agregar.addActionListener(new ActionListener() {
			/**
			 * se almacenan los datos en la bd
			 */
			public void actionPerformed(ActionEvent e) {
				
				
				if(validarCamposVacios() == true) {
					JOptionPane.showMessageDialog(null, "No deje campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					guardarDatosEnbd(obtenerAlmacen());
				}
				
			}
		});
		panelInferiorBotones.add(btn_Agregar);
	}
	
	
	
	private void cargarListboxRFC() {
		
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery(					
					"SELECT rfc FROM gerente;"					
					);
			
			while(rset.next()) {
				this.jcmb_RfcGerente.addItem(rset.getString(1));
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
	
	private void obtenerNombreGerente() {
		System.out.println("-- event charged: //");
		
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery(					
					"SELECT nombre FROM gerente WHERE rfc = '" + jcmb_RfcGerente.getSelectedItem().toString() + "';"					
					);
			
			while(rset.next()) {
				System.out.println("gerente Name: " + rset.getString(1));
				this.txf_NombreGerente.setText(rset.getString(1));
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
	
	
	private com.integrador.proyecto.voguel.clases.Almacen obtenerAlmacen(){
		com.integrador.proyecto.voguel.clases.Almacen almacen = new Almacen();
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery(					
					"SELECT id_gerente FROM gerente WHERE rfc = '" + jcmb_RfcGerente.getSelectedItem().toString() + "';"					
					);
			while(rset.next()) {
				almacen.setId_gerente(rset.getInt(1));
			}
			
			almacen.setDescripcion(this.txf_Descripcion.getText());
			almacen.setDireccion(this.txf_Direccion.getText());
			almacen.setTelefono(this.txf_Telefono.getText());
					
			System.out.println(almacen.toString());
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
		
		return almacen;
	}
	
	private void guardarDatosEnbd(com.integrador.proyecto.voguel.clases.Almacen almacen) {
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			stm.execute(
					"INSERT INTO almacen (id_gerente,descripcion,direccion,telefono) VALUES( "
					+ "'" + almacen.getId_gerente() + "',"
					+ "'" + almacen.getDescripcion() + "',"
					+ "'" + almacen.getDireccion() + "',"
					+ "'" + almacen.getTelefono() + "');"
					);
			
			JOptionPane.showMessageDialog(this, "Datos Almacenados", "Voguel", JOptionPane.INFORMATION_MESSAGE);
			
			this.limpiarCamposFormulario();
			
			System.out.println("Establecido: " + Conexion.getControlador());
			System.out.println("Enviado: " + Conexion.getUrl());
			
		}catch(SQLTimeoutException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error 'GENERAL'", JOptionPane.ERROR_MESSAGE);
		}finally {
			//se liberan recurso al cerrar la conexion
			try {
				if(stm != null){
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
	
	private void limpiarCamposFormulario() {
		this.txf_NombreGerente.setText("");
		this.txf_Descripcion.setText("");
		this.txf_Direccion.setText("");
		this.txf_Telefono.setText("");
	}
	
	private boolean validarCamposVacios() {
		
		if(this.jcmb_RfcGerente.getSelectedItem().toString().isEmpty() || this.txf_Descripcion.getText().isEmpty() || this.txf_Direccion.getText().isEmpty()
				|| this.txf_Telefono.getText().isEmpty()) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	private void abrirFormConsultaAlmacen() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					
					com.integrador.proyecto.voguel.guiforms.ConsultaAlmacen form = new com.integrador.proyecto.voguel.guiforms.ConsultaAlmacen();
					form.setVisible(true);
					form.setLocationRelativeTo(null);
					form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
				}catch(Exception er) {
					er.printStackTrace();
				}
				
			}
		});
	}
	
	private void eliminarAlmacen() {
		
		try {
			
			eliminarCostosAsociado();
			
			stm = cn.createStatement();
			cn = conexion.conection();
			
			stm.execute(
					"DELETE FROM almacen WHERE id_almacen = " + this.txf_IdAlmacen.getText()
					);
			
			
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
	
	private void eliminarCostosAsociado() throws Exception{
		
		cn = conexion.conection();
		stm = cn.createStatement();
		
		stm.execute(
				"DELETE FROM costos WHERE id_almacen = " + this.txf_IdAlmacen.getText()
				);
		
	}
	
	
	private void actualizarAlmacen(com.integrador.proyecto.voguel.clases.Almacen almacen) {
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			stm.execute(
					"UPDATE almacen SET "
					+ "id_gerente = " + almacen.getId_gerente() + ", "
					+ "descripcion = '" + almacen.getDescripcion() + "', "
					+ "direccion = '" + almacen.getDireccion() + "', "
					+ "telefono = '" + almacen.getTelefono() + "'"
					+ "WHERE id_almacen = " + this.txf_IdAlmacen.getText() + ";"
					);
			
		}catch(SQLTimeoutException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}catch(SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}finally {
			
			try {
				if(stm != null) {
					stm.close();
				}
				if(cn != null) {
					stm.close();
				}
			}catch(SQLException er) {
				er.printStackTrace();
				JOptionPane.showMessageDialog(null, er.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}catch(Exception er) {
				er.printStackTrace();
				JOptionPane.showMessageDialog(null, er.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	
	private void consultarAlmacen() {
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
					+ " FROM almacen INNER JOIN gerente ON almacen.id_gerente = gerente.id_gerente "
					+ "WHERE almacen.id_almacen = " + this.txf_IdAlmacen.getText() + ";"
					);
			
			if(rset.next()) {
				this.jcmb_RfcGerente.setSelectedItem(rset.getString(2));
				this.txf_NombreGerente.setText(rset.getString(3));
				this.txf_Descripcion.setText(rset.getString(4));
				this.txf_Direccion.setText(rset.getString(5));
				this.txf_Telefono.setText(rset.getString(6));
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
