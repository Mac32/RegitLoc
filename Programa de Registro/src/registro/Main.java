package registro;

import gestor.GestorDeDatos;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ventana.Ventana;
import controlador.Controlador;

public class Main {


	public static void main(String[] args) {
	
		try {
		
		UIManager.setLookAndFeel(new com.jtattoo.plaf.graphite.GraphiteLookAndFeel());
	} catch (UnsupportedLookAndFeelException e) {
		e.printStackTrace();
	}
		
//		try {
//		
//		UIManager.setLookAndFeel(new com.jtattoo.plaf.noire.NoireLookAndFeel());
//	} catch (UnsupportedLookAndFeelException e) {
//		e.printStackTrace();
//	}
//		try {
//			UIManager.setLookAndFeel(new de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel());
//		} catch (UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}


		
		Ventana ventana = new Ventana();
		GestorDeDatos gestorDeDatos = new GestorDeDatos();
		new Controlador(ventana, gestorDeDatos);

	}
	

}
