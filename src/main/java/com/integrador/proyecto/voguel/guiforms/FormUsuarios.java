package com.integrador.proyecto.voguel.guiforms;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.integrador.proyecto.voguel.clases.Conexion;
import com.integrador.proyecto.voguel.clases.Usuario;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;

import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

/**
 * 
 * @author PABLO
 *
 */
public class FormUsuarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3406795452267808949L;
	/**
	 * 
	 * 
	 * 
	 */
	
	private JPanel panelPrincipal;
	private JTextField txf_IdUsuario;
	private JTextField txf_nombreUsuario;
	private JPasswordField pswd_Contrasenia;
	private JPasswordField pswd_confirmaContrasenia;
	private JButton btn_agregar;
	private static com.integrador.proyecto.voguel.clases.Conexion conexion = new Conexion();
	private static Connection cn;
	private static Statement stm;
	

	/**
	 * Create the frame.
	 */
	public FormUsuarios() {
		setTitle("Usuarios");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormUsuarios.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 238);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperior = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSuperior.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
		panelSuperior.add(menuBar);
		
		JMenu menuArchivo = new JMenu("Archivo");
		menuBar.add(menuArchivo);
		
		JMenuItem opcionVerRegistro = new JMenuItem("Consultar registros");
		opcionVerRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				abrirTablaRegistros();
				
			}
		});
		menuArchivo.add(opcionVerRegistro);
		
		JMenuItem opcionExportarRegistro = new JMenuItem("Exportar Registros");
		menuArchivo.add(opcionExportarRegistro);
		
		JMenuItem opcionSalir = new JMenuItem("Salir");
		opcionSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		menuArchivo.add(opcionSalir);
		
		JMenu menuVista = new JMenu("vista");
		menuBar.add(menuVista);
		
		JMenuItem opcionAjustarVentana = new JMenuItem("Reajustar ventana");
		opcionAjustarVentana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reajustarVentana();
			}
		});
		menuVista.add(opcionAjustarVentana);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(278);
		menuBar.add(horizontalStrut_4);
		
		JPanel panelBotonesAccion = new JPanel();
		panelBotonesAccion.setBorder(null);
		panelPrincipal.add(panelBotonesAccion, BorderLayout.SOUTH);
		panelBotonesAccion.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		
		JButton btn_Limpiar = new JButton("Limpiar");
		btn_Limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				limpiarCamposFormulario();
			}
		});
		panelBotonesAccion.add(btn_Limpiar);
		
		JButton btn_consultar = new JButton("Consultar");
		btn_consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txf_IdUsuario.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Ingrese el id a consultar", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					consultarUsuario();
				}
				
			}
		});
		panelBotonesAccion.add(btn_consultar);
		
		JButton btn_Actualizar = new JButton("Actualizar");
		btn_Actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				if((txf_IdUsuario.getText().isEmpty() && validarCamposVacios()) == false) {
					Usuario usuario = getUsuario();
					actualizarUsuario(usuario);
				}else {
					JOptionPane.showMessageDialog(null, "No deje campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
				}
												
			}
		});
		panelBotonesAccion.add(btn_Actualizar);
		
		btn_agregar = new JButton("Agregar");
		btn_agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(validarCamposVacios() == true) {
					JOptionPane.showMessageDialog(null, "No deje campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(validarContraseniaIngresada() == false) {
					JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					Usuario usuario = getUsuario();
					guardarDatosEnBd(usuario);
				}
				
				
				
			}
		});
		panelBotonesAccion.add(btn_agregar);
		
		JPanel panelCentralComponentes = new JPanel();
		panelCentralComponentes.setBorder(new EmptyBorder(12, 5, 12, 5));
		panelPrincipal.add(panelCentralComponentes, BorderLayout.CENTER);
		panelCentralComponentes.setLayout(new BoxLayout(panelCentralComponentes, BoxLayout.Y_AXIS));
		
		Box horizontalBox = Box.createHorizontalBox();
		panelCentralComponentes.add(horizontalBox);
		
		JLabel lblNewLabel = new JLabel("id Usuario");
		horizontalBox.add(lblNewLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		
		txf_IdUsuario = new JTextField();
		txf_IdUsuario.setToolTipText("Escribir aquí solo para actualizar un registro");
		horizontalBox.add(txf_IdUsuario);
		txf_IdUsuario.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(5);
		panelCentralComponentes.add(verticalStrut);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		panelCentralComponentes.add(horizontalBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		horizontalBox_1.add(lblNewLabel_1);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_1);
		
		txf_nombreUsuario = new JTextField();
		horizontalBox_1.add(txf_nombreUsuario);
		txf_nombreUsuario.setColumns(10);
		
		Component verticalStrut_1 = Box.createVerticalStrut(5);
		panelCentralComponentes.add(verticalStrut_1);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		panelCentralComponentes.add(horizontalBox_2);
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña");
		horizontalBox_2.add(lblNewLabel_2);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_2);
		
		pswd_Contrasenia = new JPasswordField();
		horizontalBox_2.add(pswd_Contrasenia);
		
		Component verticalStrut_2 = Box.createVerticalStrut(5);
		panelCentralComponentes.add(verticalStrut_2);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		panelCentralComponentes.add(horizontalBox_3);
		
		JLabel lblNewLabel_3 = new JLabel("Confirmar");
		horizontalBox_3.add(lblNewLabel_3);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_3);
		
		pswd_confirmaContrasenia = new JPasswordField();
		horizontalBox_3.add(pswd_confirmaContrasenia);
	}
	
	
	
	/**
	 * se crea un objeto de tipo {@code Usuario} con los datos asignados por el usuario en el form
	 * @return
	 */
	private com.integrador.proyecto.voguel.clases.Usuario getUsuario(){
		Usuario usuario = new Usuario();
		char[] caracteres = this.pswd_Contrasenia.getPassword();
		StringBuilder sb = new StringBuilder();
		
		for(char c: caracteres) {
			sb.append(c);
		}
		
		try {
			if(validarContraseniaIngresada() == true) {
				
				if(!(this.txf_IdUsuario.getText().isEmpty())) {
					usuario.setId(Integer.parseInt(this.txf_IdUsuario.getText()));
				}
				
				usuario.setNombre(this.txf_nombreUsuario.getText());
				usuario.setPswd(sb.toString());
				
				System.out.println(usuario.toString());
				
			}else {
				JOptionPane.showMessageDialog(this, "No coinciden las contraseñas", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}catch(Exception er) {
			er.printStackTrace();
		}
		
		return usuario;
	}
	
	/**
	 * actualiza los datos de un usuario en la tabla
	 */
	private void actualizarUsuario(com.integrador.proyecto.voguel.clases.Usuario usuario) {
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			stm.execute("UPDATE usuario SET "
					+ "nombre = '" + usuario.getNombre() + "'," //se acualiza el nombre
					+ "pswd = '" + usuario.getPswd() + "'"		//se actualiza la contraseña
					+ "WHERE id = " + usuario.getId() + ";");	//se busca el ide del usuario a buscar
			
			JOptionPane.showConfirmDialog(this, "El usuario " + usuario.getId() + " ha sido actualizado", "Atencion", JOptionPane.INFORMATION_MESSAGE);
			
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
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 
	 * Envía los datos del objeto {@code Usuario} a su respectiva tabla en la bd
	 * @throws SQLException en caso de errores de conecion o si el límite de tiempo de ejecución del comando
	 * es excedido
	 * 
	 */
	private void guardarDatosEnBd(com.integrador.proyecto.voguel.clases.Usuario usuario) {
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			stm.execute("INSERT INTO usuario (nombre,pswd) VALUES("
					+ "'" + usuario.getNombre() + "',"
					+ "'" + usuario.getPswd() + "');");
			
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
	
	/**
	 * 
	 * realiza un consulta a la base de datos buscando el id del usuario y regresando los datos en
	 * el campo de texto correspondiente
	 * 
	 */
	private void consultarUsuario() {
		
		ResultSet rset = null;
		
		try {
			
			this.txf_nombreUsuario.setText("");
			this.pswd_confirmaContrasenia.setText("");
			this.pswd_Contrasenia.setText("");
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery(
					"SELECT nombre FROM usuario "
					+ "WHERE id = " + this.txf_IdUsuario.getText() + ";"
					);
			
			if(rset.next()) {
				this.txf_nombreUsuario.setText(rset.getString(1));
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
	
	/**
	 * 
	 * se cierra el form sin salir del sistema
	 * 
	 * 
	 */
	private void cerrarForm() {
		this.dispose();
	}
	
	/**
	 * 
	 * reajusta el tamaño de la ventana al tamaño de los componentes en caso de que 
	 * el tamaño haya sido modificado manualmente por el usuario
	 * 
	 */
	private void reajustarVentana() {
		this.pack();
	}
	
	/**
	 * 
	 * se elimina el texto de todos los campos de texto contenidos en el 
	 * formulario
	 * 
	 */
	private void limpiarCamposFormulario() {
		
		this.txf_IdUsuario.setText("");
		this.txf_nombreUsuario.setText("");
		this.pswd_confirmaContrasenia.setText("");
		this.pswd_Contrasenia.setText("");
		
	}
	
	private boolean validarCamposVacios() {
		
		if(this.txf_nombreUsuario.getText().isEmpty() || this.pswd_Contrasenia.getPassword().length == 0 || 
				this.pswd_confirmaContrasenia.getPassword().length == 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * 
	 * valida que la contraseña ingresada por el usuario al primer {@code JPasswordField}
	 * sea la misma que la que se ingreso en el segundo campo
	 * @return {@code true} si el contenido de los campos es igual
	 * 
	 */
	private boolean validarContraseniaIngresada() {

		String pswd1 = null;
		String pswd2 = null;
		StringBuilder sb = new StringBuilder();
		char[] pswdIngresada = this.pswd_Contrasenia.getPassword();
		char[] pswdrectificar = this.pswd_confirmaContrasenia.getPassword();
		
		for(char c: pswdIngresada) {
			sb.append(c);
		}
		
		pswd1 = sb.toString();
		sb.delete(0, sb.length());
		
		for(char c: pswdrectificar) {
			sb.append(c);
		}
		
		pswd2 = sb.toString();
		sb.delete(0, sb.length());
		
		return pswd1.equals(pswd2);
	}
	
	private void abrirTablaRegistros() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					com.integrador.proyecto.voguel.guiforms.ConsultaUsuarios form = new ConsultaUsuarios();
					form.setVisible(true);
					form.setLocationRelativeTo(null);
					form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}catch(Exception er) {
					er.printStackTrace();
				}
				
			}
		});
	}
	
	public JButton getBtn_agregar() {
		return btn_agregar;
	}
}








