package com.integrador.proyecto.voguel.guiforms;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class FormVentanaResultados extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2106692536287866100L;
	/**
	 * 
	 * 
	 */
	private JPanel contentPane;
	//private ArrayList<String> listaProcesos = new ArrayList<String>();
	private int[][] matriz;
	//private int resultado;
	private JTable tablaResultados;
	private JPanel panel;
	private JLabel lblNewLabel;
	private Component horizontalStrut;
	private JTextField txfResultado;
	
	//private 

	/**
	 * Create the frame.
	 */
	public FormVentanaResultados(int[][] matriz, int resultado) {
		setTitle("Soluciones");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormVentanaResultados.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		
		this.matriz = matriz;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tablaResultados = new JTable(matriz.length,matriz[0].length);
		
		llenarJTable();
		
		scrollPane.setViewportView(tablaResultados);
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		lblNewLabel = new JLabel("Resultado");
		panel.add(lblNewLabel);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);
		
		txfResultado = new JTextField();
		
		this.txfResultado.setText("$"+ resultado + ".00");
		
		panel.add(txfResultado);
		txfResultado.setColumns(10);
		
		
	}
	
	private void llenarJTable() {
		for(int i = 0; i<matriz.length;i++) {
			for(int j = 0; j<matriz[i].length;j++) {
				tablaResultados.setValueAt(matriz[i][j],i, j);
			}
		}
		
		
	}

}
