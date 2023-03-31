package com.integrador.proyecto.voguel.guiforms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.integrador.proyecto.voguel.clases.Conexion;
import com.integrador.proyecto.voguel.clases.Destino;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JMenuItem;

public class FormDestino extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5795794565247986637L;
	/**
	 * 
	 * 
	 */
	private JPanel panelPrincipal;
	private JTextField txf_IdDestino;
	private JTextField txf_descripcion;
	private JTextField txf_direccion;
	private JTextField txf_telefono;
	private static com.integrador.proyecto.voguel.clases.Conexion conexion = new Conexion();
	private static Connection cn;
	private static Statement stm;
	

	/**
	 * Create the frame.
	 */
	public FormDestino() {
		setTitle("Destinos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormDestino.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 236);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperiorBarraMenu = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSuperiorBarraMenu.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelPrincipal.add(panelSuperiorBarraMenu, BorderLayout.NORTH);
		
		JMenuBar barraDeMenuPrincipal = new JMenuBar();
		panelSuperiorBarraMenu.add(barraDeMenuPrincipal);
		
		JMenu menuArchivo = new JMenu("Archivo");
		barraDeMenuPrincipal.add(menuArchivo);
		
		JMenuItem opcionConsultaRegistros = new JMenuItem("Ver registros");
		opcionConsultaRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				abrirConsultaDestinos();
				
				
			}
		});
		menuArchivo.add(opcionConsultaRegistros);
		
		JMenuItem opcionExportarRegistros = new JMenuItem("Exportar registros");
		menuArchivo.add(opcionExportarRegistros);
		
		JMenuItem opcionSalir = new JMenuItem("Salir");
		menuArchivo.add(opcionSalir);
		
		JMenu menuVista = new JMenu("Vista");
		barraDeMenuPrincipal.add(menuVista);
		
		JMenuItem opcionReajustarVentana = new JMenuItem("Reajustar Ventana");
		menuVista.add(opcionReajustarVentana);
		
		Component horizontalStrut = Box.createHorizontalStrut(334);
		barraDeMenuPrincipal.add(horizontalStrut);
		
		JPanel panelCentralComponentes = new JPanel();
		panelCentralComponentes.setBorder(new EmptyBorder(12, 8, 12, 8));
		panelPrincipal.add(panelCentralComponentes, BorderLayout.CENTER);
		panelCentralComponentes.setLayout(new BoxLayout(panelCentralComponentes, BoxLayout.Y_AXIS));
		
		Box horizontalBox = Box.createHorizontalBox();
		panelCentralComponentes.add(horizontalBox);
		
		JLabel lblNewLabel = new JLabel("id destino");
		lblNewLabel.setToolTipText("");
		horizontalBox.add(lblNewLabel);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_1);
		
		txf_IdDestino = new JTextField();
		txf_IdDestino.setToolTipText("Escribir unicamente para consulta o actualizacion");
		horizontalBox.add(txf_IdDestino);
		txf_IdDestino.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(5);
		panelCentralComponentes.add(verticalStrut);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		panelCentralComponentes.add(horizontalBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		horizontalBox_1.add(lblNewLabel_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_2);
		
		txf_descripcion = new JTextField();
		horizontalBox_1.add(txf_descripcion);
		txf_descripcion.setColumns(10);
		
		Component verticalStrut_1 = Box.createVerticalStrut(5);
		panelCentralComponentes.add(verticalStrut_1);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		panelCentralComponentes.add(horizontalBox_2);
		
		JLabel lblNewLabel_2 = new JLabel("Direccion");
		horizontalBox_2.add(lblNewLabel_2);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_3);
		
		txf_direccion = new JTextField();
		horizontalBox_2.add(txf_direccion);
		txf_direccion.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(5);
		panelCentralComponentes.add(verticalStrut_2);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		panelCentralComponentes.add(horizontalBox_3);
		
		JLabel lblNewLabel_3 = new JLabel("Telefono");
		horizontalBox_3.add(lblNewLabel_3);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_4);
		
		txf_telefono = new JTextField();
		horizontalBox_3.add(txf_telefono);
		txf_telefono.setColumns(10);
		
		JPanel panelInferiorBotones = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		panelPrincipal.add(panelInferiorBotones, BorderLayout.SOUTH);
		
		JButton btn_limpiarForm = new JButton("Limpiar");
		btn_limpiarForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				limpiarForm();
				
			}
		});
		panelInferiorBotones.add(btn_limpiarForm);
		
		JButton btn_Consultar = new JButton("Consultar");
		btn_Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txf_IdDestino.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingrese el id a consultar", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					consultarDatosBd();
				}
			}
		});
		
		JButton btn_eliminarDestino = new JButton("Eliminar");
		btn_eliminarDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txf_IdDestino.getText().isEmpty() == true) {
					JOptionPane.showMessageDialog(null, "Ingrese el id del almacen", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					eliminarDestino();
					JOptionPane.showMessageDialog(null, "Registro Eliminado", "Atencion", JOptionPane.INFORMATION_MESSAGE);
				}
										
			}
		});
		panelInferiorBotones.add(btn_eliminarDestino);
		panelInferiorBotones.add(btn_Consultar);
		
		JButton btn_Actualizar = new JButton("Actualizar");
		btn_Actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Destino destino = new Destino();
				destino = getDestino();
				
				if((validarCamposVacios() && txf_IdDestino.getText().isEmpty()) == false) {
					actualizarDestino(destino);
				}else {
					JOptionPane.showMessageDialog(null, "No deje campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
		panelInferiorBotones.add(btn_Actualizar);
		
		JButton btn_Agregar = new JButton("Agregar");
		btn_Agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(validarCamposVacios() == true) {
					JOptionPane.showMessageDialog(null, "No deje campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					Destino destino = new Destino();
					destino = getDestino();
					guardarEnBd(destino);
					limpiarForm();
				}
				
			}
		});
		panelInferiorBotones.add(btn_Agregar);
	}
	
	/**
	 * 
	 * @return un objeto de tipo {@code Destino} de los datos asignados por el usuario en los
	 * respectivos campos del form... los datos de este objeto serán almacenados o actualizados en una bd
	 * 
	 */
	private com.integrador.proyecto.voguel.clases.Destino getDestino() {
		Destino destino = new Destino();
		
		try {
			
			if(!(this.txf_IdDestino.getText().isEmpty())) {
				destino.setId_destino(Integer.parseInt(this.txf_IdDestino.getText()));
			}
			destino.setDescripcion(this.txf_descripcion.getText());
			destino.setDireccion(this.txf_direccion.getText());
			destino.setTelefono(this.txf_telefono.getText());
			
		}catch(Exception er) {
			er.printStackTrace();
		}
		
		System.out.println(destino.toString());
		
		return destino;
	}
	
	/**
	 * guarda los datos de un objeto de tipo {@code Destino} dentro de la bd
	 * @param destino
	 */
	private void guardarEnBd(com.integrador.proyecto.voguel.clases.Destino destino) {
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			stm.execute(
					"INSERT INTO destino (descripcion,direccion,telefono) VALUES ("
					+ "'" + destino.getDescripcion() +"',"
					+ "'" + destino.getDireccion() + "',"
					+ "'" + destino.getTelefono() + "');"
					);
			
			JOptionPane.showMessageDialog(this, "Datos Almacenados", "Voguel", JOptionPane.INFORMATION_MESSAGE);
			
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
					cn.close();
				}
				
			}catch(SQLException er) {
				er.printStackTrace();
			}catch(Exception er) {
				er.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Actualiza los registros de un destino específico en la base de datos
	 * @param destino
	 */
	private void actualizarDestino(com.integrador.proyecto.voguel.clases.Destino destino) {
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			stm.execute(
					"UPDATE destino SET "
					+ "descripcion = '" + destino.getDescripcion() + "',"
					+ "direccion = '" + destino.getDireccion() + "',"
					+ "telefono = '" + destino.getTelefono() + "'"
					+ "WHERE id_destino = " + destino.getId_destino() + ";"
					);
			
			JOptionPane.showMessageDialog(this, "El destino " + destino.getId_destino() + "ha sido actualiado", "Atencion", 
					JOptionPane.INFORMATION_MESSAGE);
			
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
					cn.close();
				}
				
			}catch(SQLException er) {
				er.printStackTrace();
			}catch(Exception er) {
				er.printStackTrace();
			}
		}
	}
	
	private void eliminarDestino() {
		
		try {
			
			eliminarCostosAsociado();
			
			cn = conexion.conection();
			stm = cn.createStatement();
			
			stm.execute(
					"DELETE FROM destino WHERE id_destino = " + this.txf_IdDestino.getText() + ";"
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
				"DELETE FROM costos WHERE id_destino = " + this.txf_IdDestino.getText()
				);
		
	}
	
	
	/**
	 * consulta los datos almacenados en la bd y regresa en los campos respectivos los datos ligados al ID ingresado
	 * para la busqueda
	 */
	private void consultarDatosBd() {
		ResultSet rset = null;
		
		//se limpian los datos obtenidos de antiguas busquedas antes de realizar la nueva busqueda
		this.txf_descripcion.setText("");
		this.txf_direccion.setText("");
		this.txf_telefono.setText("");
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery(
					"SELECT descripcion,direccion,telefono FROM destino "
					+ "WHERE id_destino = " + this.txf_IdDestino.getText() + ";"
					);
			
			if(rset.next()) {
				this.txf_descripcion.setText(rset.getString(1));
				this.txf_direccion.setText(rset.getString(2));
				this.txf_telefono.setText(rset.getString(3));
			}
			
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
	
	private void limpiarForm() {
		this.txf_IdDestino.setText("");
		this.txf_descripcion.setText("");
		this.txf_direccion.setText("");
		this.txf_telefono.setText("");
	}
	
	/**
	 * valida que los campos del formulario no estén vacios al momento de guardar informacion
	 * en la bd, para no pasar datos tipo {@code NULL} a las tablas
	 * @return {@code true} si uno o todos los campos están vacios
	 */
	private boolean validarCamposVacios() {
		if(this.txf_descripcion.getText().isEmpty()||this.txf_direccion.getText().isEmpty()
				||this.txf_telefono.getText().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	private void abrirConsultaDestinos() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				com.integrador.proyecto.voguel.guiforms.ConsultaDestinos form = new ConsultaDestinos();
				form.setVisible(true);
				form.setLocationRelativeTo(null);
				form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
	
		
	}
	
}
 