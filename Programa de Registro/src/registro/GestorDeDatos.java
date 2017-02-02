package registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class GestorDeDatos {

	private Connection ct;
	private Statement st;
	
	public GestorDeDatos(){
		
		try {
			Class.forName("org.sqlite.JDBC");
			ct = DriverManager.getConnection("jdbc:sqlite:/home/canaima/workspace/Programa de Registro/bin/registro/base.db");
			st = ct.createStatement();
			System.out.println("Conexión exitosa");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}//Fin del constructor
	
	
	public Statement getSt(){
		return st;

	}
	

}//Fin de la clase gestor
