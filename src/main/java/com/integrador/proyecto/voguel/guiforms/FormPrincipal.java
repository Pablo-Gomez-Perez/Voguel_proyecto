package com.integrador.proyecto.voguel.guiforms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;

/**
 * 
 * @author PABLO
 *
 */
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
		setBounds(100, 100, 702, 500);
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
				
				
				abrirFormRegistroAlmacen();
				
				
				
			}
		});
		menuAlmacen.add(opcionEditarAlmacen);
		
		JMenuItem opcionConsultarAlmacen = new JMenuItem("Consultar");
		menuAlmacen.add(opcionConsultarAlmacen);
		
		JMenuItem opcionExistencias = new JMenuItem("Existencias");
		opcionExistencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormAsignarExistencia();
			}
		});
		menuAlmacen.add(opcionExistencias);
		
		JMenu menuDestinos = new JMenu("Destino");
		barraDeMenu.add(menuDestinos);
		
		JMenuItem opcionEditarDestinos = new JMenuItem("Editar Registros");
		opcionEditarDestinos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				abrirFormRegistroDestinos();
				
			}
		});
		menuDestinos.add(opcionEditarDestinos);
		
		JMenuItem opcionConsultarDestinos = new JMenuItem("Consultar");
		menuDestinos.add(opcionConsultarDestinos);
		
		JMenuItem opcionAsignarDemandas = new JMenuItem("Demandas");
		opcionAsignarDemandas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				abrirFormAsignarDemandas();
				
			}
		});
		menuDestinos.add(opcionAsignarDemandas);
		
		JMenu menuGerentes = new JMenu("Gerentes");
		barraDeMenu.add(menuGerentes);
		
		JMenuItem opcionEditarGerentes = new JMenuItem("Editar Registros");
		//se abre un formulario para dar de alta o actualizar registros de gerentes
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
		//se abre un formulario para actualizar o dar de alta nuevos usuarios
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
		//evento reajustar ventana
		opcionReajustarVentana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reajustarVentana();
			}
		});
		menuOpciones.add(opcionReajustarVentana);
		
		JMenuItem opcionCerrarSesion = new JMenuItem("Cerrar Sesion");
		opcionCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarSesion();
			}
		});
		menuOpciones.add(opcionCerrarSesion);
		
		JMenuItem opcionCerrar = new JMenuItem("Salir");
		opcionCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salirDelSistema();
			}
		});
		menuOpciones.add(opcionCerrar);
		
		JMenu menuEnvios = new JMenu("Envios");
		barraDeMenu.add(menuEnvios);
		
		JMenuItem opcionEstablecerCosto = new JMenuItem("Establecer Costos");
		opcionEstablecerCosto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormEstablecerCostos();
			}
		});
		menuEnvios.add(opcionEstablecerCosto);
		
		JMenuItem opcionMatrizCostos = new JMenuItem("Matriz de Costos");
		opcionMatrizCostos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormMatrizDeCostos();
			}
		});
		menuEnvios.add(opcionMatrizCostos);
		
		Component horizontalStrut = Box.createHorizontalStrut(320);
		barraDeMenu.add(horizontalStrut);
		
		JPanel panelCentralComponentes = new JPanel();
		panelCentralComponentes.setBackground(new Color(0, 204, 204));
		panelCentralComponentes.setBorder(new EmptyBorder(8, 8, 8, 8));
		panelPrincipal.add(panelCentralComponentes, BorderLayout.CENTER);
		panelCentralComponentes.setLayout(new BorderLayout(0, 0));
		
		JPanel panelImagen = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			/**
			 * 
			 * 
			 */
			private Image imagen;
			@Override
			public void paint(Graphics g) {
				imagen = new ImageIcon(getClass().getResource("/com/integrador/proyecto/voguel/imagenes/operaciones logisticas funciones.jpg")).getImage();
				g.drawImage(imagen, 0, 0, getWidth(), getHeight(),this);
				setOpaque(false);
				super.paint(g);
			}
			
		};
		panelImagen.setBackground(new Color(204, 255, 204));
		panelImagen.setForeground(new Color(102, 153, 51));
		panelCentralComponentes.add(panelImagen, BorderLayout.CENTER);
		panelImagen.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBotonesDeAccion = new JPanel();
		panelBotonesDeAccion.setBackground(new Color(0, 204, 102));
		panelPrincipal.add(panelBotonesDeAccion, BorderLayout.SOUTH);
		
		JPanel panelIzquierdo = new JPanel();
		panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
		panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(430);
		panelIzquierdo.add(verticalStrut);
		
		JPanel panelDerecho = new JPanel();
		panelPrincipal.add(panelDerecho, BorderLayout.EAST);
		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
	}
	
	/**
	 * se abre el formulario para el registro edicion y actuaización de datos de los gerentes de
	 * los almacenes
	 * 
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
	
	/**
	 * 
	 * se abre el formulario para dar de alta o actualizar registros de los usuarios
	 * 
	 */
	private void abrirFormRegistroUsuarios() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					com.integrador.proyecto.voguel.guiforms.FormUsuarios formUsuarios = new FormUsuarios();
					formUsuarios.setVisible(true);
					formUsuarios.setLocationRelativeTo(null);
					formUsuarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					formUsuarios.pack();
				}catch(Exception er) {
					er.printStackTrace();
					JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private void abrirFormRegistroDestinos() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					com.integrador.proyecto.voguel.guiforms.FormDestino form = new FormDestino();
					form.setVisible(true);
					form.setLocationRelativeTo(null);
					form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					form.pack();
				}catch(Exception er) {
					er.printStackTrace();
					JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private void abrirFormEstablecerCostos() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					com.integrador.proyecto.voguel.guiforms.FormAsignarCostos form = new com.integrador.proyecto.voguel.guiforms.FormAsignarCostos();
					form.setVisible(true);
					form.setLocationRelativeTo(null);
					form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					form.pack();
				}catch(Exception er) {
					er.printStackTrace();
				}
			}
		});
	}
	
	private void abrirFormAsignarExistencia() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					
					com.integrador.proyecto.voguel.guiforms.FormAsignarExistencias form = new com.integrador.proyecto.voguel.guiforms.FormAsignarExistencias();
					form.setVisible(true);
					form.setLocationRelativeTo(null);
					form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
				}catch(Exception er) {
					er.printStackTrace();
				}
				
			}
		});
	}
	
	private void abrirFormMatrizDeCostos() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					
					com.integrador.proyecto.voguel.guiforms.FormMatrizCostos form = new com.integrador.proyecto.voguel.guiforms.FormMatrizCostos(); 
					form.setVisible(true);
					form.setLocationRelativeTo(null);
					form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					//form.pack();
					
				}catch(Exception er) {
					er.printStackTrace();
				}
			}
		});
	}
	
	private void abrirFormRegistroAlmacen() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					
					com.integrador.proyecto.voguel.guiforms.FormAlmacen form = new FormAlmacen();
					form.setVisible(true);
					form.setLocationRelativeTo(null);
					form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					form.pack();
					
				}catch(Exception er) {
					er.printStackTrace();
				}
			}
		});
	}
	
	private void abrirFormAsignarDemandas() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					
					com.integrador.proyecto.voguel.guiforms.FormAsignarDemandas form = new com.integrador.proyecto.voguel.guiforms.FormAsignarDemandas();
					form.setVisible(true);
					form.setLocationRelativeTo(null);
					form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
				}catch(Exception er) {
					er.printStackTrace();
				}
				
			}
		});
	}
	
	/**
	 * 
	 * reajusta el tamaño de la ventana si esta se ha expandido
	 * 
	 */
	private void reajustarVentana() {
		this.pack();
	}
	
	/**
	 * se cierra el formulario principal y se abre la ventana de login 
	 */
	private void cerrarSesion() {
		java.awt.EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				com.integrador.proyecto.voguel.guiforms.FormLogIn form = new FormLogIn();
				form.setVisible(true);
				form.setLocationRelativeTo(null);
				form.pack();
				
			}
			
		});
		
		this.dispose();
	}
	
	private void salirDelSistema() {
		System.exit(DO_NOTHING_ON_CLOSE);
	}
}
