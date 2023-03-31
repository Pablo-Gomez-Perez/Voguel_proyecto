package com.integrador.proyecto.voguel.guiforms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.integrador.proyecto.voguel.clases.AproximacionDeVoguel;

public class FormMatrizCostos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2390040046649316258L;
	/**
	 * 
	 * 
	 * 
	 */
	private JPanel panelPrincipal;
	private JTable tablaCostos;
	private DefaultTableModel model = new DefaultTableModel();
	private DefaultTableModel modelSolucion = new DefaultTableModel();
	private int cantidadFilas;
	private int cantidadColumnas;
	private static com.integrador.proyecto.voguel.clases.Conexion conexion = new com.integrador.proyecto.voguel.clases.Conexion();
	private static Connection cn;
	private static Statement stm;
	private JTextField txf_ofertaTotal;
	private JTextField txf_demandaTotal;
	private JTextField txf_diferencias;
	private JTable tablaSolucion;
	JScrollPane scrollPaneTablaSolucion = new JScrollPane();
	private ArrayList<String> ListProcesos = new ArrayList<String>();
    private ArrayList<Integer> ListMultiplicaciones = new ArrayList<Integer>();
	

	/**
	 * Create the frame.
	 */
	public FormMatrizCostos() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormMatrizCostos.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setTitle("Matriz de costos");
		setBackground(new Color(0, 102, 51));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 478);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(51, 102, 51));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentralColor = new JPanel();
		panelPrincipal.add(panelCentralColor, BorderLayout.CENTER);
		panelCentralColor.setLayout(new BorderLayout(0, 0));
		
		JPanel PanelContenedorTabla = new JPanel();
		PanelContenedorTabla.setBorder(new EmptyBorder(15, 8, 15, 8));
		PanelContenedorTabla.setBackground(new Color(51, 153, 51));
		panelCentralColor.add(PanelContenedorTabla, BorderLayout.CENTER);
		PanelContenedorTabla.setLayout(new BoxLayout(PanelContenedorTabla, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPaneContenedorTabla = new JScrollPane();
		PanelContenedorTabla.add(scrollPaneContenedorTabla);
					
		tablaCostos = new JTable();
		tablaCostos.setModel(model);
		
		//se agrega la primer columna del JTable principal con los origenes(Almacenes)
		model.addColumn("ORIGEN / DESTINO",consultarOrigenes());
		
		//se guarda la lista de las demandas asociadas a cada almacen dentro del
		//siguiente vector
		Vector<String> dmdas = consultarDemandas();
		
		//se agregan las siguientes columnas a la tabla principal las cuales contienen
		//como encabezado el indice del destino y el valor de cada celda de la columna es
		//el costo asociado a cada origen
		consultarDestinos(model);
		
		//se agrega la fila con las demandas de cada destino, estos valores fueron almacenados en el Vector<String> dmdas
		model.addRow(dmdas);
		
		// se agrega la última columna a la tabla principal con el valor de las ofertas(Existencias) de cada almacen
		model.addColumn("Oferta",consultarOfertas());
		
		scrollPaneContenedorTabla.setViewportView(tablaCostos);
		
		
		tablaSolucion = new JTable();
		tablaSolucion.setModel(modelSolucion);
		
		scrollPaneTablaSolucion.setViewportView(tablaSolucion);
		
		
		PanelContenedorTabla.add(scrollPaneTablaSolucion);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelPrincipal.add(panel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("Oferta");
		panel.add(lblNewLabel);
		
		txf_ofertaTotal = new JTextField();
		panel.add(txf_ofertaTotal);
		txf_ofertaTotal.setColumns(10);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);
		
		JLabel lblNewLabel_1 = new JLabel("Demanda");
		panel.add(lblNewLabel_1);
		
		txf_demandaTotal = new JTextField();
		panel.add(txf_demandaTotal);
		txf_demandaTotal.setColumns(10);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1);
		
		JLabel lblNewLabel_2 = new JLabel("Diferencias");
		panel.add(lblNewLabel_2);
		
		txf_diferencias = new JTextField();
		panel.add(txf_diferencias);
		txf_diferencias.setColumns(10);
		
		this.txf_ofertaTotal.setText("" + sumarOfertas());
		
		this.txf_demandaTotal.setText("" + sumarDemandas());
		
		this.txf_diferencias.setText("" + calcularDiferencias());
		
		JButton btnCargarMatriz = new JButton("Cargar Matriz");
		btnCargarMatriz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proceder();
			}
		});
		panel.add(btnCargarMatriz);
		
		JButton btn_Solucionar = new JButton("Solucionar");
		btn_Solucionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int[] demandaArray = demandasArray();
				int[] ofertaArray = ofertasArray();
				int[][] costos = obtenerMatriz();
				
				try {
					
					AproximacionDeVoguel voguel = new AproximacionDeVoguel(demandaArray, ofertaArray, costos);
					voguel.solucion();
					
				}catch(Exception er) {
					
					er.printStackTrace();
				}
				
			}
		});
		panel.add(btn_Solucionar);
	}
	
	/**
	 * consulta el indice de los almacenes registrados en la bd y los almacena dentro de 
	 * un {@code Vector<String>} para ser desplegado como una columna dentro del {@code JTable}
	 * @return
	 */
	private Vector<String> consultarOrigenes(){
		Vector<String> origenes = new Vector<String>();
		ResultSet rset = null;
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			
			rset = stm.executeQuery(
					"SELECT id_almacen FROM almacen;"
					);
			
			this.cantidadFilas = 0;
			
			while(rset.next()) {
				origenes.add(rset.getString(1));
				cantidadFilas += 1;
			}
			
			
		}catch(SQLException er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace(System.out);
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
		
		return origenes;
	}
	
	/**
	 * consulta las ofertas de cada almacen dentro de la bd y las almacena en un {@code Vector<String>} para ser colocados como
	 * una fila dentro de un {@code JTable}
	 * @return
	 */
	private Vector<String> consultarOfertas(){
		Vector<String> ofertas = new Vector<String>();
		
		ResultSet rset = null;
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			
			rset = stm.executeQuery(
					"SELECT existencia FROM almacen;"
					);
			
			
			while(rset.next()) {
				ofertas.add(rset.getString(1));
			}
			
			
			
		}catch(SQLException er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}
		
		return ofertas;
	}
	
	
	/**
	 * Consulta las demandas de cada uno de los destinos almacenados en la bd y los almacena en un
	 * {@code Vector<String>} para ser colocados en una columna en un {@code JTable}
	 * @return 
	 */
	private Vector<String> consultarDemandas(){
		Vector<String> demandas = new Vector<String>();
		this.cantidadColumnas = 0;
		ResultSet rset = null;
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			
			rset = stm.executeQuery(
					"SELECT demanda FROM destino;"
					);
			
			demandas.add("Demandas");
			
			while(rset.next()) {
				demandas.add(rset.getString(1));
				cantidadColumnas +=1;
		}
			
			
			
		}catch(SQLException er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}
		
		return demandas;
	}
	
	/**
	 * Coloca tantas columnas en la tabla de acuerdo a la cantidad de destino que hay mediante el método
	 * <code>addColumn(String name, Vector<> dataColumn) </code>, el nombre (Indice) de cada columna se obtiene
	 * consultando el indice de los destinos mediante una consulta SQL a la respectiva tabla, y el {@code Vector<t>}
	 * con los costos se obtiene mediante el método {@code consultarCostos(String nombre)} 
	 * @param model
	 */
	private void consultarDestinos(DefaultTableModel model) {
		
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			
			rset = stm.executeQuery(
					"SELECT id_destino FROM destino;"
					);
			
			//mientras se rset tenga registros
			while(rset.next()) {
				model.addColumn(rset.getString(1),consultarCostos(rset.getString(1)));
			}
			
			
			
		}catch(SQLException er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	/**
	 * 
	 * esta función sirve para llenar las columnas con los costos de envío de cada origen aosicado dentro de la tabla,
	 * es llamada desde el método {@linkplain consultarDestinos(DefaultTableModel model)} contenido en esta misma clase.
	 * 
	 * @param nombre 
	 * 
	 * @return un {@code Vector<Integer>} con los costos de envío desde lo origenes en la tabla, hasta el destino de la columna
	 * que se está colocando, dicho destino se obtiene mediante el método <code> getColumnName(int index)</code> correspondiente a la
	 * clase <code>JTable</code>, el valor del ID del destino se usa para ejecutar la consulta SQL y obtener los costos.
	 * 
	 */
	private Vector<Integer> consultarCostos(String nombre){
		Vector<Integer> costos = new Vector<Integer>();
		ResultSet rset = null;
		
		try {
			
			cn = conexion.conection();
			stm = cn.createStatement();
			
			for(int i = 0; i < cantidadFilas; i++) {
				rset = stm.executeQuery(
						"SELECT costo FROM costos "
						+ "WHERE id_destino = " + nombre + " AND "
						+ "id_almacen = " + tablaCostos.getValueAt( i , 0) + ";"
						);
				
				if(rset.next()) {
					costos.add(rset.getInt(1));
				}
			}
			
		}catch(SQLException er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace(System.out);
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
		}
		return costos;
	}
	
	/**
	 * calcula el valor de la oferta total del sistema, es decir, las existencias en los alamacenes
	 * @return
	 */
	private int sumarOfertas() {
		int suma = 0;
		
		for(int i = 0; i < tablaCostos.getRowCount() - 1; i++) {
			suma = suma + Integer.parseInt(tablaCostos.getValueAt(i, tablaCostos.getColumnCount() - 1).toString());
		}
		
		return suma;
	}
	
	/**
	 * calcula el valor de la demanda total del sistema, es decir, lo que cada destino requiere
	 * @return 
	 */
	private int sumarDemandas() {
		int suma = 0;
		
		for(int i = 1; i < tablaCostos.getColumnCount() -1; i++) {
			suma = suma + Integer.parseInt(tablaCostos.getValueAt(tablaCostos.getRowCount() -1, i).toString());
		}
		
		return suma;
	}
	
	/**
	 * calcula las diferencias entre la oferta y la demanda
	 * @return
	 */
	private int calcularDiferencias() {
		int valor = 0;
		
		valor = Integer.parseInt(this.txf_demandaTotal.getText()) - Integer.parseInt(this.txf_ofertaTotal.getText());
		
		return valor;
	}
	
	/**
	 * se crea un arreglo con las demandas, solo que esta vez se cargan de la tabla solución y no de la tabla principal
	 * @return
	 */
	private int[] demandasArray() {
		int numDemandas = tablaSolucion.getColumnCount() - 2;
		//se obtiene el indice de la última fila de la tabla, que es en donde están contenidas las demandas
		int ultimaFila = tablaSolucion.getRowCount()-1;
		int[] arrayDemandas = new int[numDemandas];
		
		for(int i = 0; i<numDemandas; i++) {
			arrayDemandas[i] = Integer.parseInt(tablaSolucion.getValueAt(ultimaFila, i + 1).toString());
		}
		
		return arrayDemandas;
	}
	
	/**
	 * se crea un arreglo con las ofertas de cada almacen, este array obtiene los datos de la tabla solución y no de la principal
	 * debido a que si la tabla está desbalanceada se pueden agregar una fila mas o una columna mas dependiendo de si la oferta
	 * o la demanda es mayor
	 * @return
	 */
	private int[] ofertasArray() {
		int numOfetas = tablaSolucion.getRowCount() - 1;
		int ultimaColumna = tablaSolucion.getColumnCount() -1;
		int[] arrayOfertas = new int[numOfetas];
		
		for(int i = 0; i < numOfetas; i++) {
			arrayOfertas[i] = Integer.parseInt(tablaSolucion.getValueAt(i, ultimaColumna).toString());
		}
		
		return arrayOfertas;
	}
	
	
	/**
	 * Método para obtener la matriz de costos de envío del JTable principal, únicamente rescata los costos
	 * de envío de cada origen en el eje Y del JTable a cada detino en el eje X del JTable 
	 * @return una matriz detipo {@code int[][]}
	 * @author PABLO
	 */
	private int[][] obtenerMatriz(){
		
		int columnas = this.tablaSolucion.getColumnCount() - 2;
		int filas = this.tablaSolucion.getRowCount() - 1;
		int[][] matriz = new int[filas][columnas];
		
		for(int i = 0; i < filas; i++) {
			for(int j = 0; j < columnas; j++) {
				matriz[i][j] = Integer.parseInt(tablaSolucion.getValueAt(i, j + 1).toString());
			}
		}
		
		return matriz;
	}
    
	
	private Vector<String> agregarColumna(){
		Vector<String> datosColuma = new Vector<String>();
		for(int i  = 0; i < cantidadFilas;i++) {
			datosColuma.add("0");
		}
		return datosColuma;
	}
	
	private Vector<String> agregarFila(){
		Vector<String> datosFila = new Vector<String>();
		datosFila.add("0000");
		for(int i = 0; i < cantidadColumnas + 1; i++) {
			datosFila.add("0");
		}
		return datosFila;
	}
	
	private void llenarTablaSolución() {
		
		
		modelSolucion.addColumn("Origen/Destino", consultarOrigenes());
		
		consultarDestinos(modelSolucion);
		
		if(sumarDemandas() > sumarOfertas()) {
			
			modelSolucion.addRow(agregarFila());
			modelSolucion.addRow(consultarDemandas());
			modelSolucion.addColumn("Ofertas", consultarOfertas());
			tablaSolucion.setValueAt(sumarDemandas() - sumarOfertas() , tablaSolucion.getRowCount()-2, tablaSolucion.getColumnCount()-1);
			
			JOptionPane.showMessageDialog(null, "Se ha agregado un almacen ficticio");
			
		}else if(sumarOfertas() > sumarDemandas()) {
			
			modelSolucion.addColumn("0000", agregarColumna());
			modelSolucion.addRow(consultarDemandas());
			modelSolucion.addColumn("Ofertas", consultarOfertas());
			tablaSolucion.setValueAt(sumarOfertas()-sumarDemandas() , tablaSolucion.getRowCount()-1, tablaSolucion.getColumnCount()-2);
			
			JOptionPane.showMessageDialog(null, "Se ha agregado un destino ficticio");
			
		}else {
			modelSolucion.addColumn("Ofertas", consultarOfertas());
			modelSolucion.addRow(consultarDemandas());
		}
		
		
	}
	
	
	private void proceder() {
		
		try {
			
			ListProcesos.clear();
			ListMultiplicaciones.clear();
			llenarTablaSolución();
			
			//matrizSolucion();
			
		}catch(Exception er) {
			er.printStackTrace();
		}
		
	}

}
