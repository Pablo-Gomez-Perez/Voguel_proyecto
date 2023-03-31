package com.integrador.proyecto.voguel.guiforms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.integrador.proyecto.voguel.clases.Conexion;
import com.integrador.proyecto.voguel.clases.Gerente;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class FormGerentes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8422435581446468494L;
	/**
	 * 
	 * 
	 * 
	 */
	private JPanel panelPrincipal;;
	private JTextField txf_NombreGerente = new JTextField();
	private JTextField txf_Telefono = new JTextField();
	private JTextField txf_Direccion = new JTextField();
	private JComboBox<String> jcmb_rfcGerente = new JComboBox<String>();
	private static com.integrador.proyecto.voguel.clases.Conexion conexion = new Conexion();
	private static Connection cn;
	private static Statement stm;

	/**
	 * Create the frame.
	 */
	public FormGerentes() {
		setTitle("Gerentes de Almacen");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FormGerentes.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 228);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		JPanel panelBarraDeMenu = new JPanel();
		panelBarraDeMenu.setBorder(null);
		panelBarraDeMenu.setBackground(new Color(81, 207, 249));
		FlowLayout flowLayout = (FlowLayout) panelBarraDeMenu.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelPrincipal.add(panelBarraDeMenu, BorderLayout.NORTH);

		JMenuBar barraDeMenu = new JMenuBar();
		barraDeMenu.setBackground(new Color(255, 255, 255));
		barraDeMenu.setBorder(null);
		barraDeMenu.setBorderPainted(false);
		barraDeMenu.setMinimumSize(panelBarraDeMenu.getPreferredSize());
		panelBarraDeMenu.add(barraDeMenu);

		JMenu menuArchivo = new JMenu("Archivo");
		barraDeMenu.add(menuArchivo);

		JMenuItem opcionVerRegistro = new JMenuItem("Ver Registros");
		opcionVerRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				abrirFormRegistroGerentes();

			}
		});
		menuArchivo.add(opcionVerRegistro);

		JMenuItem opcionExportar = new JMenuItem("Exportar registro");
		menuArchivo.add(opcionExportar);

		JMenuItem opcionCerrarForm = new JMenuItem("Salir");
		opcionCerrarForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		menuArchivo.add(opcionCerrarForm);

		JMenu menuVista = new JMenu("Vista");
		barraDeMenu.add(menuVista);

		JMenuItem opcionReajustarVentana = new JMenuItem("ajustar ventana");
		opcionReajustarVentana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reajustarVentana();
			}
		});
		menuVista.add(opcionReajustarVentana);

		Component horizontalStrut_4 = Box.createHorizontalStrut(310);
		barraDeMenu.add(horizontalStrut_4);

		JPanel panelComponentes = new JPanel();
		panelComponentes.setBorder(new EmptyBorder(12, 5, 12, 5));
		panelPrincipal.add(panelComponentes, BorderLayout.CENTER);
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));

		Box bx_Nombre = Box.createHorizontalBox();
		panelComponentes.add(bx_Nombre);

		JLabel lblNewLabel = new JLabel("RFC");
		bx_Nombre.add(lblNewLabel);

		Component horizontalStrut = Box.createHorizontalStrut(35);
		bx_Nombre.add(horizontalStrut);
		
		this.cargarListaDeRfcCombobox();
		jcmb_rfcGerente.setEditable(true);
		bx_Nombre.add(jcmb_rfcGerente);

		Component verticalStrut = Box.createVerticalStrut(5);
		panelComponentes.add(verticalStrut);

		Box bx_nombreGerente = Box.createHorizontalBox();
		panelComponentes.add(bx_nombreGerente);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		bx_nombreGerente.add(lblNewLabel_1);

		Component horizontalStrut_1 = Box.createHorizontalStrut(18);
		bx_nombreGerente.add(horizontalStrut_1);

		bx_nombreGerente.add(txf_NombreGerente);
		txf_NombreGerente.setColumns(10);

		Component verticalStrut_1 = Box.createVerticalStrut(5);
		panelComponentes.add(verticalStrut_1);

		Box horizontalBox = Box.createHorizontalBox();
		panelComponentes.add(horizontalBox);

		JLabel lblNewLabel_2 = new JLabel("Telefono");
		horizontalBox.add(lblNewLabel_2);

		Component horizontalStrut_2 = Box.createHorizontalStrut(13);
		horizontalBox.add(horizontalStrut_2);

		horizontalBox.add(txf_Telefono);
		txf_Telefono.setColumns(10);

		Component verticalStrut_2 = Box.createVerticalStrut(5);
		panelComponentes.add(verticalStrut_2);

		Box horizontalBox_1 = Box.createHorizontalBox();
		panelComponentes.add(horizontalBox_1);

		JLabel lblNewLabel_3 = new JLabel("Direccion");
		horizontalBox_1.add(lblNewLabel_3);

		Component horizontalStrut_3 = Box.createHorizontalStrut(12);
		horizontalBox_1.add(horizontalStrut_3);

		horizontalBox_1.add(txf_Direccion);
		txf_Direccion.setColumns(10);

		JPanel panelBotonesAccion = new JPanel();
		panelBotonesAccion.setBorder(null);
		FlowLayout flowLayout_1 = (FlowLayout) panelBotonesAccion.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelPrincipal.add(panelBotonesAccion, BorderLayout.SOUTH);

		JButton btn_LimpiarForm = new JButton("Limpiar Campos");
		btn_LimpiarForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarForm();
			}
		});
		panelBotonesAccion.add(btn_LimpiarForm);

		JButton btn_Consultar = new JButton("Consultar");
		btn_Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(jcmb_rfcGerente.getSelectedItem().toString().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingrese el rfc a consultar", "Error", JOptionPane.WARNING_MESSAGE);
				}else {
					consultarGerente();
				}

			}
		});
		panelBotonesAccion.add(btn_Consultar);

		JButton btn_Actualizar = new JButton("Actualizar");
		btn_Actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validarCamposVacios() == true) {
					Gerente gerente = getGerente();
					actualizarRegistroDeGerentes(gerente);
					limpiarForm();
				}else {
					JOptionPane.showMessageDialog(null, "No deje campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panelBotonesAccion.add(btn_Actualizar);

		JButton btn_Agregar = new JButton("Agregar");
		btn_Agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCamposVacios() == true) {
					Gerente gerente = getGerente();
					guardarDatosEnBd(gerente);
					limpiarForm();
				} else {
					JOptionPane.showMessageDialog(null, "No deje campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		panelBotonesAccion.add(btn_Agregar);
	}

	/**
	 * se inicializa un objeto de tipo gerente para obtener los datos ingresados por
	 * el usuario
	 * 
	 * @return {@code com.integrador.proyecto.voguel.clases.Gerente gerente}
	 */
	private com.integrador.proyecto.voguel.clases.Gerente getGerente() {
		com.integrador.proyecto.voguel.clases.Gerente gerente = new Gerente();

		gerente.setRfc(this.jcmb_rfcGerente.getSelectedItem().toString());
		gerente.setNombre(this.txf_NombreGerente.getText());
		gerente.setTelefono(this.txf_Telefono.getText());
		gerente.setDireccion(this.txf_Direccion.getText());
		
		gerente.toString();

		return gerente;
	}
	
	/**
	 * 
	 * Busca el rfc de un gerente y devuelve los demas datos en los campos de texto correspondientes
	 * 
	 */
	private void consultarGerente() {
		
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery(
					"SELECT nombre,telefono,direccion FROM gerente "
					+ "WHERE rfc = '" + this.jcmb_rfcGerente.getSelectedItem().toString() + "';"
					);
			
			if(rset.next()) {
				this.txf_NombreGerente.setText(rset.getString(1));
				this.txf_Telefono.setText(rset.getString(2));
				this.txf_Direccion.setText(rset.getString(3));
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
	 * envía los datos del objeto {@code Gerente} a su respectiva tabla
	 * 
	 * @param gerente
	 */
	private void guardarDatosEnBd(com.integrador.proyecto.voguel.clases.Gerente gerente) {


		try {

			cn = conexion.conection();
			stm = cn.createStatement();
			stm.execute("INSERT INTO gerente (rfc,nombre,telefono,direccion) VALUES(" 
					+ "'" + gerente.getRfc() + "',"
					+ "'" + gerente.getNombre() + "'," 
					+ "'" + gerente.getTelefono() + "'," 
					+ "'" + gerente.getDireccion() + "');");
					

			JOptionPane.showMessageDialog(this, "Datos Almacenados", "Voguel", JOptionPane.INFORMATION_MESSAGE);
			
			this.jcmb_rfcGerente.addItem(gerente.getRfc());

		}catch(SQLTimeoutException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		} finally {
			try {

				
				if (stm != null) {
					stm.close();
				}
				if (cn != null) {
					cn.close();
				}

			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}
	
	/**
	 * 
	 * actualiza los datos de un registro en específico en la tabla
	 * 
	 */
	private void actualizarRegistroDeGerentes(com.integrador.proyecto.voguel.clases.Gerente gerente){
		
		try {
			cn = conexion.conection();
			stm = cn.createStatement();
			stm.execute(
					"UPDATE gerente SET "
					+ "nombre = '" + gerente.getNombre() + "',"
					+ "telefono = '" + gerente.getTelefono() + "',"
					+ "direccion = '" + gerente.getDireccion() + "'"
					+ "WHERE rfc = '" + gerente.getRfc() + "';"
					);
			
			JOptionPane.showMessageDialog(this, "El Gerente " + gerente.getRfc() + " ha sido actualizado", "Atencion", JOptionPane.INFORMATION_MESSAGE);
			
			System.out.println("Establecido: " + Conexion.getControlador());
			System.out.println("Enviado: " + Conexion.getUrl());
			
		}catch(SQLTimeoutException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}catch(SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	/**
	 * limpia todos los campos del formulario
	 */
	private void limpiarForm() {

		this.txf_Direccion.setText("");
		this.txf_NombreGerente.setText("");
		this.jcmb_rfcGerente.setSelectedItem("");
		this.txf_Telefono.setText("");

	}

	/**
	 * valida que los campos del formulario no estén vacios la momento de guardar
	 * datos
	 * 
	 * @return {@code true} si hay campos vacios {@code false} si todos los campos
	 *         son != de vacio
	 * 
	 */
	private boolean validarCamposVacios() {
		if (this.txf_Direccion.getText().isEmpty() || this.txf_NombreGerente.getText().isEmpty()
				|| this.jcmb_rfcGerente.getSelectedItem().toString().isEmpty() || this.txf_Telefono.getText().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 
	 * reajusta el tamaño de la ventana al tamaño de los componentes del form
	 * 
	 */
	private void reajustarVentana() {
		this.pack();
	}
	
	/**
	 * 
	 * cierra el form sin terminar el programa
	 * 
	 */
	private void cerrarForm() {
		this.dispose();
	}
	
	/**
	 * carga la lista completa de RFC's existentes en el combobox
	 */
	private void cargarListaDeRfcCombobox() {
		
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			rset = stm.executeQuery("SELECT rfc FROM gerente");
			
			while(rset.next()) {
				this.jcmb_rfcGerente.addItem(rset.getString(1));
			}
			
		}catch(SQLTimeoutException er) {
			er.printStackTrace();
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
		}
		
	}
	
	private void abrirFormRegistroGerentes() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					com.integrador.proyecto.voguel.guiforms.ConsultaGerentes form = new ConsultaGerentes();
					form.setVisible(true);
					form.setLocationRelativeTo(null);
					form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}catch(Exception er) {
					er.printStackTrace();
				}
				
			}
		});
	}
}
