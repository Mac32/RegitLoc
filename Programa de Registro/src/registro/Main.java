package registro;

import gestor.GestorDeDatos;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import ventana.Vcuenta;
import ventana.Vedición;
import ventana.Ventana;
import ventana.Vlogin;
import controlador.Controlador;

public class Main {

	private static ImageIcon imagen_fondo = new ImageIcon(Ventana.class.getResource("/Imagenes/naranja4.jpg"));

	public static void main(String[] args) {
		
//		try {
//		
//		UIManager.setLookAndFeel(new com.jtattoo.plaf.acryl.AcrylLookAndFeel());
//	} catch (UnsupportedLookAndFeelException e) {
//		e.printStackTrace();
//	}

//		try {
//			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		try {
//			UIManager.setLookAndFeel(new de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel());
//		} catch (UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		} catch (java.text.ParseException e) {
//			e.printStackTrace();
//		}
		GestorDeDatos gestorDeDatos = new GestorDeDatos();
		gestorDeDatos.single();
		UIManager.put("Label.foreground", Color.white);
		UIManager.put("Button.background", Color.orange);
		UIManager.put("Button.foreground", Color.white);
		UIManager.put("TextField.caretForeground",Color.orange);
		UIManager.put("TableHeader.background", Color.orange);
		UIManager.put("Table.selectionBackground", new Color(255, 162, 51));
		UIManager.put("Table.background", Color.orange);
		UIManager.put("Table.gridColor", Color.white);
		UIManager.put("TextArea.foreground", Color.white);
		UIManager.put("Table.font", new Font("Dialog", Font.PLAIN, 12));
		UIManager.put("TitledBorder.titleColor", Color.white);
		UIManager.put("TitledBorder.border", new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255)));
		
		Ventana ventana = new Ventana(getImagen_fondo());
		Vlogin vlogin = new Vlogin(ventana, "Login",getImagen_fondo());
		Vedición vedicion = new Vedición(ventana, "Edición", getImagen_fondo());
		Vcuenta vcuenta = new Vcuenta(vedicion, "Cuenta de Usuario", getImagen_fondo());
		new Controlador(ventana, gestorDeDatos, vlogin, vedicion, vcuenta);

	}

	public static ImageIcon getImagen_fondo() {
		return imagen_fondo;
	}

	public static void setImagen_fondo(ImageIcon imagen_fondo) {
		Main.imagen_fondo = imagen_fondo;
	}
	

}
