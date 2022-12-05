package com.integrador.proyecto.voguel.guiforms;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class FormUsuarios extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public FormUsuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormUsuarios.class.getResource("/com/integrador/proyecto/voguel/imagenes/icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
