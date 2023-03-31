package com.integrador.proyecto.voguel.app;

//import com.integrador.proyecto.voguel.guiforms.FormLogIn;
import com.integrador.proyecto.voguel.guiforms.FormPrincipal;

import javax.swing.JOptionPane;

public class App {

	public static void main(String[] args) throws Exception{

		
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					
					FormPrincipal form = new FormPrincipal();
					form.setVisible(true);
					form.setLocationRelativeTo(null);
					form.pack();
					
				}catch(Exception er) {
					er.printStackTrace(System.out);
					JOptionPane.showMessageDialog(null, er.getMessage(), "Error", 
					JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}

}
