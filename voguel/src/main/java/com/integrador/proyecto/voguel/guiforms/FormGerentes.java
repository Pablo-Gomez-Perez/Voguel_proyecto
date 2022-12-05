package com.integrador.proyecto.voguel.guiforms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.Dimension;

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
	private JPanel panelPrincipal;
	private JTextField txf_rfcGerente;
	private JTextField txf_NombreGerente;
	private JTextField textField;
	private JTextField textField_1;

	

	/**
	 * Create the frame.
	 */
	public FormGerentes() {
		setTitle("Gerentes de Almacen");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormGerentes.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 283);
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
		menuArchivo.add(opcionVerRegistro);
		
		JMenuItem opcionExportar = new JMenuItem("Exportar registro");
		menuArchivo.add(opcionExportar);
		
		JMenuItem opcionCerrarForm = new JMenuItem("Salir");
		menuArchivo.add(opcionCerrarForm);
		
		JMenu menuVista = new JMenu("Vista");
		barraDeMenu.add(menuVista);
		
		JMenuItem opcionReajustarVentana = new JMenuItem("ajustar ventana");
		menuVista.add(opcionReajustarVentana);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(290);
		horizontalStrut_4.setPreferredSize(new Dimension(244, 12));
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
		
		txf_rfcGerente = new JTextField();
		bx_Nombre.add(txf_rfcGerente);
		txf_rfcGerente.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(5);
		panelComponentes.add(verticalStrut);
		
		Box bx_nombreGerente = Box.createHorizontalBox();
		panelComponentes.add(bx_nombreGerente);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		bx_nombreGerente.add(lblNewLabel_1);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(18);
		bx_nombreGerente.add(horizontalStrut_1);
		
		txf_NombreGerente = new JTextField();
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
		
		textField = new JTextField();
		horizontalBox.add(textField);
		textField.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(5);
		panelComponentes.add(verticalStrut_2);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		panelComponentes.add(horizontalBox_1);
		
		JLabel lblNewLabel_3 = new JLabel("Direccion");
		horizontalBox_1.add(lblNewLabel_3);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(12);
		horizontalBox_1.add(horizontalStrut_3);
		
		textField_1 = new JTextField();
		horizontalBox_1.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panelBotonesAccion = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelBotonesAccion.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelPrincipal.add(panelBotonesAccion, BorderLayout.SOUTH);
		
		JButton btn_Consultar = new JButton("Consultar");
		panelBotonesAccion.add(btn_Consultar);
		
		JButton btn_Actualizar = new JButton("Actualizar");
		panelBotonesAccion.add(btn_Actualizar);
		
		JButton btn_Agregar = new JButton("Agregar");
		panelBotonesAccion.add(btn_Agregar);
	}

}
