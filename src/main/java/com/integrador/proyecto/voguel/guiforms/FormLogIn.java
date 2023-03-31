package com.integrador.proyecto.voguel.guiforms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.integrador.proyecto.voguel.clases.Conexion;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author PABLO
 *
 */
public class FormLogIn extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5501592389916634728L;
	private JPanel contentPane;
	private JPasswordField pswf_usuario = new JPasswordField();
	private JComboBox<String> cmb_usuarios = new JComboBox<String>();
	private java.util.ArrayList<String> usuarios = getNombreUsuarios();
	private static Conexion conexion = new Conexion();

	/**
	 * Create the frame.
	 */
	public FormLogIn() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormLogIn.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setTitle("Voguel LogIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 134);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentroComponentes = new JPanel();
		contentPane.add(panelCentroComponentes, BorderLayout.CENTER);
		panelCentroComponentes.setLayout(new BoxLayout(panelCentroComponentes, BoxLayout.Y_AXIS));
		
		Box horizontalBox = Box.createHorizontalBox();
		panelCentroComponentes.add(horizontalBox);
		
		JLabel lblNewLabel = new JLabel("usuario");
		horizontalBox.add(lblNewLabel);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		
		llenarCmb_Usuarios();
		horizontalBox.add(cmb_usuarios);
		
		Component verticalStrut = Box.createVerticalStrut(5);
		panelCentroComponentes.add(verticalStrut);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		panelCentroComponentes.add(horizontalBox_1);
		
		JLabel lblNewLabel_1 = new JLabel("contraseña");
		horizontalBox_1.add(lblNewLabel_1);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_1);
		
		//passwordField.setDropMode(DropMode.ON);
		pswf_usuario.setColumns(12);
		horizontalBox_1.add(pswf_usuario);
		
		JPanel panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		JButton btn_salir = new JButton("Salir");
		btn_salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panelBotones.add(btn_salir);
		
		JButton btn_ingresar = new JButton("Ingresar");
		btn_ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarIngreso() == true) {
					java.awt.EventQueue.invokeLater(new Runnable() {
						@Override
						public void run() {
							
							com.integrador.proyecto.voguel.guiforms.FormPrincipal form = new FormPrincipal();
							form.setVisible(true);
							form.setLocationRelativeTo(null);
							form.pack();
						}
					});
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Error de acceso", "Error", 
							JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		panelBotones.add(btn_ingresar);
		
		usuarios.clear();
	}
	
	/**
	 * funcion para obtener los nombres de los usuarios de su respectiva tabla dentro de la 
	 * bd "voguel_db" estos datos pasan a un array list que posteriormente son agregados al
	 * JComboBox donde serán desplegados
	 * @return {@code usuarios<String>} 
	 * 
	 */
	public static java.util.ArrayList<String> getNombreUsuarios() {
		
		java.util.ArrayList<String> usuarios = new ArrayList<String>();
		
		Connection cn = null;
		Statement stm = null;
		ResultSet rstset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rstset = stm.executeQuery("SELECT nombre FROM usuario");
			
			while(rstset.next()) {
				usuarios.add(rstset.getString(1));
			}
			
		}catch(SQLException er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}finally {
			try {
				if(rstset != null) {
					rstset.close();
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
		
		return usuarios;
		
	}
	
	/**
	 * se cargan todos los elementos obtenidos de la función {@code getNombreUsuarios()}
	 * al combobox del formulario
	 */
	public void llenarCmb_Usuarios() {
		for(String st:usuarios) {
			this.cmb_usuarios.addItem(st);
		}
	}
	
	public String getPswd() {
		
		String pswd = null;
		String nombreUsuario = cmb_usuarios.getSelectedItem().toString();
		
		Connection cn = null;
		Statement stm = null;
		ResultSet rstset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rstset = stm.executeQuery("SELECT pswd FROM usuario WHERE nombre = " +"'"+nombreUsuario+"'");
			
			if(rstset.next()) {
				pswd = rstset.getString(1);	
			}
			
			
		}catch(SQLException er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error 'GENERAL'", JOptionPane.ERROR_MESSAGE);
		}finally {
			
			try {
				
				if(rstset != null) {
					rstset.close();
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
		
		return pswd;
	}
	
	public boolean validarIngreso() {
		StringBuilder sb = new StringBuilder();
		char[] pswdCaracteres;
		boolean ingreso = false;
		
		try {
			pswdCaracteres = this.pswf_usuario.getPassword();
			
			for(char ch: pswdCaracteres) {
				sb.append(ch);
			}
			
			System.out.println("texto en campo = " + sb.toString());
			System.out.println("texto en tabla = " + getPswd());
			if(sb.toString().equals(getPswd())) {
				System.out.println("ingreso correcto");
				ingreso = true;
			}else {
				System.out.println("Ingreso incorrecto");
				ingreso = false;
			}
		}catch(NullPointerException er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error 'GENERAL'", JOptionPane.ERROR_MESSAGE);
		}catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		return ingreso;
	}
	
	public JPasswordField getPasswordField() {
		return pswf_usuario;
	}
	public JComboBox<String> getComboBox() {
		return cmb_usuarios;
	}
}
