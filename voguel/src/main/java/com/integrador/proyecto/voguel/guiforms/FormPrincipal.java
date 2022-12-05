package com.integrador.proyecto.voguel.guiforms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5669015254473744930L;
	/**
	 * 
	 * 
	 */
	private JPanel panelPrincipal;
	private JMenu menuDatos;


	/**
	 * Create the frame.
	 */
	public FormPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormPrincipal.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 419);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 204, 204));
		panelPrincipal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBarraMenu = new JPanel();
		panelBarraMenu.setBackground(new Color(0, 204, 153));
		panelBarraMenu.setBorder(null);
		panelPrincipal.add(panelBarraMenu, BorderLayout.NORTH);
		FlowLayout fl_panelBarraMenu = new FlowLayout(FlowLayout.LEFT, 0, 0);
		fl_panelBarraMenu.setAlignOnBaseline(true);
		panelBarraMenu.setLayout(fl_panelBarraMenu);
		
		JMenuBar barraDeMenu = new JMenuBar();
		//barraDeMenu.setPreferredSize(panelBarraMenu.getPreferredSize());
		panelBarraMenu.add(barraDeMenu);
		
		JMenu menuAlmacen = new JMenu("Almacen");
		barraDeMenu.add(menuAlmacen);
		
		JMenuItem opcionEditarAlmacen = new JMenuItem("Editar registros");
		opcionEditarAlmacen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menuAlmacen.add(opcionEditarAlmacen);
		
		JMenuItem opcionConsultarAlmacen = new JMenuItem("Consultar");
		menuAlmacen.add(opcionConsultarAlmacen);
		
		JMenu menuDestinos = new JMenu("Destino");
		barraDeMenu.add(menuDestinos);
		
		JMenuItem opcionEditarDestinos = new JMenuItem("Editar Registros");
		menuDestinos.add(opcionEditarDestinos);
		
		JMenuItem opcionConsultarDestinos = new JMenuItem("Consultar");
		menuDestinos.add(opcionConsultarDestinos);
		
		JMenu menuGerentes = new JMenu("Gerentes");
		barraDeMenu.add(menuGerentes);
		
		JMenuItem opcionEditarGerentes = new JMenuItem("Editar Registros");
		opcionEditarGerentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormRegistroGerentes();
			}
		});
		menuGerentes.add(opcionEditarGerentes);
		
		JMenuItem opcionConsultarGerentes = new JMenuItem("Consultar");
		menuGerentes.add(opcionConsultarGerentes);
		
		JMenu menuUsuarios = new JMenu("Usuarios");
		barraDeMenu.add(menuUsuarios);
		
		JMenuItem opcionEditarUsuarios = new JMenuItem("Editar Registros");
		opcionEditarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormRegistroUsuarios();
			}
		});
		menuUsuarios.add(opcionEditarUsuarios);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Consultar");
		menuUsuarios.add(mntmNewMenuItem);
		
		menuDatos = new JMenu("Datos");
		barraDeMenu.add(menuDatos);
		
		JMenu menuImportar = new JMenu("Importar");
		menuDatos.add(menuImportar);
		
		JMenu menuReespaldar = new JMenu("Respaldar");
		menuDatos.add(menuReespaldar);
		
		JMenuItem opcionRespaldarBD = new JMenuItem("Base de datos");
		menuReespaldar.add(opcionRespaldarBD);
		
		JMenu menuRespaldarTabla = new JMenu("Tablas");
		menuReespaldar.add(menuRespaldarTabla);
		
		JMenuItem opcionResapldarAlmacen = new JMenuItem("Almacen");
		menuRespaldarTabla.add(opcionResapldarAlmacen);
		
		JMenuItem opcionRespaldarDestinos = new JMenuItem("Destinos");
		menuRespaldarTabla.add(opcionRespaldarDestinos);
		
		JMenuItem opcionRespaldarGerentes = new JMenuItem("Gerentes");
		menuRespaldarTabla.add(opcionRespaldarGerentes);
		
		JMenu menuOpciones = new JMenu("Opciones");
		barraDeMenu.add(menuOpciones);
		
		JMenuItem opcionReajustarVentana = new JMenuItem("Reajustar ventana");
		menuOpciones.add(opcionReajustarVentana);
		
		JMenuItem opcionCerrarSesion = new JMenuItem("Cerrar Sesion");
		menuOpciones.add(opcionCerrarSesion);
		
		JMenuItem opcionCerrar = new JMenuItem("Salir");
		menuOpciones.add(opcionCerrar);
		
		JPanel panelCentralComponentes = new JPanel();
		panelCentralComponentes.setBackground(new Color(0, 204, 204));
		panelCentralComponentes.setBorder(new EmptyBorder(8, 8, 8, 8));
		panelPrincipal.add(panelCentralComponentes, BorderLayout.CENTER);
		panelCentralComponentes.setLayout(new BoxLayout(panelCentralComponentes, BoxLayout.Y_AXIS));
		
		JPanel panelBotonesDeAccion = new JPanel();
		panelBotonesDeAccion.setBackground(new Color(0, 204, 102));
		panelPrincipal.add(panelBotonesDeAccion, BorderLayout.SOUTH);
	}
	
	/**
	 * se abre el formulario para el registro edicion y actuaización de datos de los gerentes de
	 * los almacenes
	 */
	private void abrirFormRegistroGerentes() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					com.integrador.proyecto.voguel.guiforms.FormGerentes formGerentes = new FormGerentes();
					formGerentes.setVisible(true);
					formGerentes.setLocationRelativeTo(null);
					formGerentes.pack();
					formGerentes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception er) {
					er.printStackTrace();
					JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
	}
	
	private void abrirFormRegistroUsuarios() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					com.integrador.proyecto.voguel.guiforms.FormUsuarios formUsuarios = new FormUsuarios();
					formUsuarios.setVisible(true);
					formUsuarios.setLocationRelativeTo(null);
					formUsuarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}catch(Exception er) {
					er.printStackTrace();
					JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
