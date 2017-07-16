package gestor;

import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import ventana.Vcuenta;
import ventana.Vedición;
import ventana.Ventana;
import ventana.Vlogin;

public class GestorDeDatos {

	private Connection ct;
	private Statement st;
	private static ServerSocket server_socket;
	private int control;

	public GestorDeDatos(){

		crear_coneccion();

	}//Fin del constructor

	private int obtener_año(){
		Calendar tiempo = Calendar.getInstance();
		return tiempo.get(Calendar.YEAR);
	}

	private Boolean validar(Vlogin vlogin){   

		try {
			ResultSet val = getSt().executeQuery("SELECT * FROM LOGIN WHERE " +
					"Usuario='"+vlogin.tfl_usuario.getText()+"' AND " +
					"Contraseña='"+String.valueOf(vlogin.pwd_contraseña.getPassword())+"'");
			if (val.next()){
				return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	private void llenar_tabla(Vedición vedicion){

		try {
			vedicion.dtm.setRowCount(0);
			vedicion.dtm.setColumnCount(0);
			vedicion.dtm.setColumnIdentifiers(vedicion.titulos);
			vedicion.ids.clear();

			ResultSet aux = getSt().executeQuery("SELECT * FROM TESIS ORDER BY Número ASC");

			while(aux.next()){
				Object[] fila = {aux.getObject(4), aux.getObject(5), aux.getObject(6), aux.getObject(2),aux.getObject(3),aux.getObject(7),aux.getObject(8)};
				vedicion.ids.add((Integer)aux.getObject(1));
				vedicion.dtm.addRow(fila);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private Boolean comprobar_clones_de_filas(Ventana ventana, ResultSet aux) throws SQLException{
		for (Object n: ventana.ids){
			if (n.equals(aux.getObject(1))){
				return false;
			}
		}
		return true;
	}

	private Boolean comprobar_clones_de_filas(Vedición vedicion, ResultSet aux) throws SQLException{
		for (Object n: vedicion.ids){
			if (n.equals(aux.getObject(1))){
				return false;
			}
		}
		return true;
	}

	private JTextField[] getAll_Array_tfl(Vedición vedicion, Ventana ventana, Vlogin vlogin, Vcuenta vcuenta){

		JTextField[] tfl_array = new JTextField[] {ventana.tfl_tema, vedicion.tfl_titulo, vedicion.tfl_autor, vedicion.tfl_tutor,
				vedicion.tfl_año, vedicion.tfl_nro, vlogin.tfl_usuario, vlogin.pwd_contraseña,
				vcuenta.tfl_usuario, vcuenta.psw_nueva_contraseña,
				vcuenta.psw_repita_contraseña};
		return tfl_array;
	}

	private JTextField[] getArray_tfl(Vedición vedicion){

		JTextField[] tfl_array = new JTextField[] {vedicion.tfl_titulo, vedicion.tfl_autor, vedicion.tfl_tutor, vedicion.tfl_año, vedicion.tfl_nro};
		return tfl_array;
	}

	private void limpiar_campos_edicion(Vedición vedicion){

		for (JTextField i : getArray_tfl(vedicion)) {

			i.setText("");
		}
	}

	private void modificar(Vedición vedicion){

		String título_tabla[]= new String[] {"Titulo", "Autor", "Tutor", "Carrera", "Linea", "Año", "Número"};

		JTextField array_tfl[] = getArray_tfl(vedicion);

		try{
			int temp =0;
			Object id = (Integer)vedicion.ids.get(vedicion.tabla.getSelectedRow());
			for (int i=0; i<título_tabla.length;i++){
				if (i==3){
					getSt().executeUpdate("UPDATE TESIS SET "+
							título_tabla[i]+"='"+vedicion.cmbx_carrera.getSelectedItem()+
							"' WHERE id="+id);
				}else if(i==4){
					getSt().executeUpdate("UPDATE TESIS SET "+
							título_tabla[i]+"='"+vedicion.cmbx_linea.getSelectedItem()+
							"' WHERE id="+id);
				}else if (i>2 && 6<i){
					getSt().executeUpdate("UPDATE TESIS SET "+
							título_tabla[i]+"='"+((JTextComponent) array_tfl[i+2]).getText()+
							"' WHERE id="+id);
				}else if(i==0){
					getSt().executeUpdate("UPDATE TESIS SET "+
							título_tabla[i]+"='"+((JTextComponent) array_tfl[temp]).getText().toUpperCase()+
							"' WHERE id="+id);
					temp++;
				}else {
					getSt().executeUpdate("UPDATE TESIS SET "+
							título_tabla[i]+"='"+((JTextComponent) array_tfl[temp]).getText()+
							"' WHERE id="+id);
					temp++;
				}
			}
			llenar_tabla(vedicion);
			JOptionPane.showMessageDialog(vedicion, "Se ha modificado la Fila!");
			limpiar_campos_edicion(vedicion);
			vedicion.tfl_titulo.requestFocus();
		}catch(Exception e){
			e.getStackTrace();
		}
	}

	private Boolean evitar_tfl_blancos(Vedición vedicion){
		Boolean resultado = true;
		for (JTextField i: getArray_tfl(vedicion)){
			if (i.getText().equals("null") || i.getText().equals("")){
				resultado = false;
				break;
			}
		}
		return resultado;
	}

	private void crear_coneccion(){
		try {
			Class.forName("org.sqlite.JDBC");
			ct = DriverManager.getConnection("jdbc:sqlite:/home/canaima/git/RegitLoc/Programa de Registro/src/base2.sqlite");
			st = ct.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String eliminar_acentos(String cadena){

		cadena = cadena.toLowerCase().replace('á','a').replace('é','e').replace('í','i').replace('ó','o').replace('ú','u');
		return cadena;
	}

	private String limpiar_pronombres(String strm_temas){

		String[] pronombres = {" implementacion "," basado "," bajo "," como "," cuales "," cuyo ","donde"," de ","estado",
				" esta "," el "," en "," cual "," cuya ",
				" las "," les "," los "," la "," le "," lo "," que "," se "," si "," tal ",
				" una "," un "," unas "," uno "," unos "," y "," para "};
		for (String palabra: pronombres ){
			strm_temas = strm_temas.replace(palabra, " ");
		}

		return strm_temas;
	}

	private Boolean comprobarNumero(String contenido) {
		try{
			Integer.parseInt(contenido);
			return true;
		}catch(Exception e) {
			e.getStackTrace();
			return false;
		}
	}

	public Statement getSt(){
		return st;
	}

	@SuppressWarnings("unchecked")
	public void agregarLineas_editar(String carrS, Vedición vedicion){

		if (carrS == "Ing. Industrial"){

			vedicion.cmbx_linea.removeAllItems();

			String[] lineaBox = {"Gerencia",
					"Operaciones",
					"Procesos Industriales",
					"Mejoramiento continuo",
			"Agroindustria"};

			for (int i=0; i< lineaBox.length; i++){
				vedicion.cmbx_linea.addItem(lineaBox[i]);
			}
		}

		if (carrS == "Ing. Mtto. Mecánico"){
			vedicion.cmbx_linea.removeAllItems();

			String[] lineaBox = {"Mec 1",
					"Mec 2",
					"Mec 3",
			"Mec 4"};

			for (int i=0; i< lineaBox.length; i++){
				vedicion.cmbx_linea.addItem(lineaBox[i]);
			}
		}

		if (carrS == "Ing. Civil"){

			vedicion.cmbx_linea.removeAllItems();

			String[] lineaBox = {"Civil 1",
					"Civil 2",
					"Civil 3",
			"Civil 4"};

			for (int i=0; i< lineaBox.length; i++){
				vedicion.cmbx_linea.addItem(lineaBox[i]);
			}
		}

		if (carrS == "Ing. Eléctrica"){

			vedicion.cmbx_linea.removeAllItems();

			String[] lineaBox = {"Eléctrica 1",
					"Eléctrica 2",
					"Eléctrica 3",
			"Eléctrica 4"};

			for (int i=0; i< lineaBox.length; i++){
				vedicion.cmbx_linea.addItem(lineaBox[i]);
			}
		}

		if (carrS == "Ing. Electrónica"){

			vedicion.cmbx_linea.removeAllItems();

			String[] lineaBox = {"Electrónica 1",
					"Electrónica 2",
					"Electrónica 3",
			"Electrónica 4"};

			for (int i=0; i< lineaBox.length; i++){
				vedicion.cmbx_linea.addItem(lineaBox[i]);
			}
		}

		if (carrS == "Ing. en Sistemas"){

			vedicion.cmbx_linea.removeAllItems();

			String[] lineaBox = {"Sistemas 1",
					"Sistemas 2",
					"Sistemas 3",
			"Sistemas 4"};

			for (int i=0; i< lineaBox.length; i++){
				vedicion.cmbx_linea.addItem(lineaBox[i]);
			}
		}

		if (carrS == "Arquitectura"){

			vedicion.cmbx_linea.removeAllItems();

			String[] lineaBox = {"Arquitectura 1",
					"Arquitectura 2",
					"Arquitectura 3",
			"Arquitectura 4"};

			for (int i=0; i< lineaBox.length; i++){
				vedicion.cmbx_linea.addItem(lineaBox[i]);
			}
		}
	}

	@SuppressWarnings({ "unchecked" })
	public void agregarLineas_principal(String carrS){

		if (carrS == "Ing. Industrial"){

			Ventana.linea.removeAllItems();

			String[] lineaBox = {"--Seleccione Línea--",
					"Gerencia",
					"Operaciones",
					"Procesos Industriales",
					"Mejoramiento continuo",
			"Agroindustria"};

			for (int i=0; i< lineaBox.length; i++){
				Ventana.linea.addItem(lineaBox[i]);
			}
		}

		if (carrS == "Ing. Mtto. Mecánico"){
			Ventana.linea.removeAllItems();

			String[] lineaBox = {"--Seleccione Línea--",
					"Mec 1",
					"Mec 2",
					"Mec 3",
			"Mec 4"};

			for (int i=0; i< lineaBox.length; i++){
				Ventana.linea.addItem(lineaBox[i]);
			}
		}

		if (carrS == "Ing. CiVentana.lineail"){

			Ventana.linea.removeAllItems();

			String[] lineaBox = {"--Seleccione Línea--",
					"Civil 1",
					"Civil 2",
					"Civil 3",
			"Civil 4"};

			for (int i=0; i< lineaBox.length; i++){
				Ventana.linea.addItem(lineaBox[i]);
			}
		}

		if (carrS == "Ing. Eléctrica"){

			Ventana.linea.removeAllItems();

			String[] lineaBox = {"--Seleccione Línea--",
					"Eléctrica 1",
					"Eléctrica 2",
					"Eléctrica 3",
			"Eléctrica 4"};

			for (int i=0; i< lineaBox.length; i++){
				Ventana.linea.addItem(lineaBox[i]);
			}
		}

		if (carrS == "Ing. Electrónica"){

			Ventana.linea.removeAllItems();

			String[] lineaBox = {"--Seleccione Línea--",
					"Electrónica 1",
					"Electrónica 2",
					"Electrónica 3",
			"Electrónica 4"};

			for (int i=0; i< lineaBox.length; i++){
				Ventana.linea.addItem(lineaBox[i]);
			}
		}

		if (carrS == "Ing. en Sistemas"){

			Ventana.linea.removeAllItems();

			String[] lineaBox = {"--Seleccione Línea--",
					"Sistemas 1",
					"Sistemas 2",
					"Sistemas 3",
			"Sistemas 4"};

			for (int i=0; i< lineaBox.length; i++){
				Ventana.linea.addItem(lineaBox[i]);
			}
		}

		if (carrS == "Arquitectura"){

			Ventana.linea.removeAllItems();

			String[] lineaBox = {"--Seleccione Línea--",
					"Arquitectura 1",
					"Arquitectura 2",
					"Arquitectura 3",
			"Arquitectura 4"};

			for (int i=0; i< lineaBox.length; i++){
				Ventana.linea.addItem(lineaBox[i]);
			}
		}
	}

	public void mostrar_login(Ventana ventana, Vlogin vlogin){

		vlogin.lbl_mensaje.setText("");
		vlogin.tfl_usuario.setText("");
		vlogin.pwd_contraseña.setText("");
		vlogin.tfl_usuario.requestFocus();
		vlogin.setVisible(true);
	}

	public void salir_edicion(Vedición vedicion){

		vedicion.dispose();
	}

	public void aceptar_login(Vlogin vlogin, Vedición vedicion){

		if (validar(vlogin)==true){
			vlogin.dispose();
			llenar_tabla(vedicion);
			limpiar_campos_edicion(vedicion);
			vedicion.setVisible(true);
		}else{
			vlogin.lbl_mensaje.setText("Usuario y/o Contraseña Incorrecta");
		}
	}

	public void cancelar_login(Ventana ventana, Vlogin vlogin){

		ventana.setVisible(true);
		vlogin.dispose();
	}

	public void buscar(Ventana ventana){


		String[] strn_temas = limpiar_pronombres(ventana.tfl_tema.getText()).split(" ");
		ventana.strn_carreraS = ((String)ventana.carreras.getSelectedItem());
		ventana.strn_lineaS = ((String)Ventana.linea.getSelectedItem());

		try {
			ventana.dtm.setRowCount(0);
			ventana.dtm.setColumnCount(0);
			ventana.dtm.setColumnIdentifiers(ventana.titulos);
			ventana.ids.clear();

			if (ventana.strn_lineaS!="--Seleccione Línea--"){
				for (String p: strn_temas){
					ResultSet aux = getSt().executeQuery("SELECT * FROM TESIS WHERE " +
							"REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(Titulo," +
							"'á','a'), 'é','e'),'í','i'),'ó','o'),'ú','u')," +
							"'Á','A'), 'É','E'),'Í','I'),'Ó','O'),'Ú','U') LIKE '%"+eliminar_acentos(p)+"%' AND " +
							"Carrera = '"+ventana.strn_carreraS+"' AND " +
							"Linea = '"+ventana.strn_lineaS+"' ORDER BY Año DESC");

					while(aux.next()){
						Object[] fila = {aux.getObject(4), aux.getObject(5), aux.getObject(6)};
						if((Integer)aux.getObject(7)>=obtener_año() - ventana.sld_años.getValue() && comprobar_clones_de_filas(ventana, aux)){
							ventana.ids.add((Integer)aux.getObject(1));
							ventana.dtm.addRow(fila);
						}
					}
				}
			}else{
				if (comprobarNumero(strn_temas[0])) { 

					ResultSet aux = getSt().executeQuery("SELECT * FROM TESIS WHERE Número="+strn_temas[0]);
					while(aux.next()){
						Object[] fila2 = {aux.getObject(4), aux.getObject(5), aux.getObject(6)};
						if((Integer)aux.getObject(7)>=obtener_año() - ventana.sld_años.getValue() && comprobar_clones_de_filas(ventana, aux)){
							ventana.ids.add((Integer)aux.getObject(1));
							ventana.dtm.addRow(fila2);
						}
					}
				}else {
					for (String p: strn_temas){
						ResultSet aux = getSt().executeQuery("SELECT * FROM TESIS WHERE " +
								"REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(Titulo," +
								"'á','a'), 'é','e'),'í','i'),'ó','o'),'ú','u')," +
								"'Á','A'), 'É','E'),'Í','I'),'Ó','O'),'Ú','U') LIKE '%"+eliminar_acentos(p)+"%' AND " +
								"Carrera = '"+ventana.strn_carreraS+"'ORDER BY Año DESC");

						while(aux.next()){
							Object[] fila = {aux.getObject(4), aux.getObject(5), aux.getObject(6)};
							if((Integer)aux.getObject(7)>=obtener_año() - ventana.sld_años.getValue() && comprobar_clones_de_filas(ventana, aux)){
								ventana.ids.add((Integer)aux.getObject(1));
								ventana.dtm.addRow(fila);
							}
						}
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void buscar(Vedición vedicion){


		String[] strn_temas = limpiar_pronombres(vedicion.tfl_tema.getText()).split(" ");
		String strn_carreraS = ((String)vedicion.carreras.getSelectedItem());
		String strn_lineaS = ((String)Ventana.linea.getSelectedItem());

		try {
			vedicion.dtm.setRowCount(0);
			vedicion.dtm.setColumnCount(0);
			vedicion.dtm.setColumnIdentifiers(vedicion.titulos);
			vedicion.ids.clear();

			if (strn_lineaS!="--Seleccione Línea--"){
				for (String p: strn_temas){
					ResultSet aux = getSt().executeQuery("SELECT * FROM TESIS WHERE " +
							"REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(Titulo," +
							"'á','a'), 'é','e'),'í','i'),'ó','o'),'ú','u')," +
							"'Á','A'), 'É','E'),'Í','I'),'Ó','O'),'Ú','U') LIKE '%"+eliminar_acentos(p)+"%' AND " +
							"Carrera = '"+strn_carreraS+"' AND " +
							"Linea = '"+strn_lineaS+"' ORDER BY Año DESC");

					while(aux.next()){
						Object[] fila = {aux.getObject(4), aux.getObject(5), aux.getObject(6), aux.getObject(2),aux.getObject(3),aux.getObject(7),aux.getObject(8)};
						if((Integer)aux.getObject(7)>=obtener_año() - vedicion.sld_años.getValue() && comprobar_clones_de_filas(vedicion, aux)){
							vedicion.ids.add((Integer)aux.getObject(1));
							vedicion.dtm.addRow(fila);
						}
					}
				}
			}else{
				if (comprobarNumero(strn_temas[0])) { 

					ResultSet auxi = getSt().executeQuery("SELECT * FROM TESIS WHERE Número="+strn_temas[0]);
					while(auxi.next()){
						Object[] fila2 = {auxi.getObject(4), auxi.getObject(5), auxi.getObject(6), auxi.getObject(2),auxi.getObject(3),auxi.getObject(7),auxi.getObject(8)};
						if((Integer)auxi.getObject(7)>=obtener_año() - vedicion.sld_años.getValue() && comprobar_clones_de_filas(vedicion, auxi)){
							vedicion.ids.add((Integer)auxi.getObject(1));
							vedicion.dtm.addRow(fila2);
						}
					}
				}else {
					for (String p: strn_temas){
						ResultSet aux = getSt().executeQuery("SELECT * FROM TESIS WHERE " +
								"REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(Titulo," +
								"'á','a'), 'é','e'),'í','i'),'ó','o'),'ú','u')," +
								"'Á','A'), 'É','E'),'Í','I'),'Ó','O'),'Ú','U') LIKE '%"+eliminar_acentos(p)+"%' AND " +
								"Carrera = '"+strn_carreraS+"'ORDER BY Año DESC");

						while(aux.next()){
							Object[] fila = {aux.getObject(4), aux.getObject(5), aux.getObject(6), aux.getObject(2),aux.getObject(3),aux.getObject(7),aux.getObject(8)};
							if((Integer)aux.getObject(7)>=obtener_año() - vedicion.sld_años.getValue() && comprobar_clones_de_filas(vedicion, aux)){
								vedicion.ids.add((Integer)aux.getObject(1));
								vedicion.dtm.addRow(fila);
							}
						}
					}
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void mostrar_seleccion(Ventana ventana){

		try {
			if (ventana.tabla.getSelectedRow()!=-1){
				ResultSet seleccion = getSt().executeQuery("SELECT * FROM TESIS WHERE id="+ventana.ids.get(ventana.tabla.getSelectedRow()));
				ventana.lbl_CarreraPD.setText(""+seleccion.getObject(2));
				ventana.lbl_lineaPD.setText(""+seleccion.getObject(3));
				ventana.txtArea_Titulo.setText(""+seleccion.getObject(4));
				ventana.lbl_Autor.setText(""+seleccion.getObject(5));
				ventana.lbl_tutor.setText(""+seleccion.getObject(6));
				ventana.lbl_año.setText(""+seleccion.getObject(7));
				ventana.lbl_Nro_E.setText(""+seleccion.getObject(8));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void mostrar_seleccion_Veditar(Vedición vedicion){

		try {
			if (vedicion.tabla.getSelectedRow()!=-1){
				ResultSet seleccion = getSt().executeQuery("SELECT * FROM TESIS WHERE id="+vedicion.ids.get(vedicion.tabla.getSelectedRow()));
				vedicion.cmbx_carrera.setSelectedItem(seleccion.getObject(2));
				vedicion.cmbx_linea.setSelectedItem(seleccion.getObject(3));
				vedicion.tfl_titulo.setText(""+seleccion.getObject(4));
				vedicion.tfl_autor.setText(""+seleccion.getObject(5));
				vedicion.tfl_tutor.setText(""+seleccion.getObject(6));
				vedicion.tfl_año.setText(""+seleccion.getObject(7));
				vedicion.tfl_nro.setText(""+seleccion.getObject(8));
				control = seleccion.getInt(8);				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void focus_textField (Vlogin vlogin, Vedición vedicion, Ventana ventana, FocusEvent e, Vcuenta vcuenta){

		for (JTextField i : getAll_Array_tfl(vedicion,ventana, vlogin, vcuenta)) {
			if (e.getSource()==i){
				((JTextComponent) i).selectAll();
			}
		}

	}

	public void eliminar_fila(Vedición vedicion){

		try {
			if (JOptionPane.showConfirmDialog(vedicion, "¿Desea eliminar la fila?", "Eliminar", JOptionPane.ERROR_MESSAGE)==0){
				getSt().executeUpdate("DELETE FROM TESIS WHERE id="+vedicion.ids.get(vedicion.tabla.getSelectedRow()));
				llenar_tabla(vedicion);
				limpiar_campos_edicion(vedicion);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void agregar_fila(Vedición vedicion){
		if (evitar_tfl_blancos(vedicion)){
			try {

				ResultSet comprobar = getSt().executeQuery("SELECT * FROM TESIS WHERE Número='"+vedicion.tfl_nro.getText()+"'");
				if(comprobar.next()){
					JOptionPane.showMessageDialog(vedicion, "El número ya está asignado, Por favor introduzca un número diferente");
				}else{
					getSt().executeUpdate("INSERT INTO TESIS (Carrera, Linea, Titulo, Autor, Tutor, Año, Número) VALUES ('"+
							vedicion.cmbx_carrera.getSelectedItem()+"','"+
							vedicion.cmbx_linea.getSelectedItem()+"','"+
							vedicion.tfl_titulo.getText().toUpperCase()+"','"+
							vedicion.tfl_autor.getText()+"','"+
							vedicion.tfl_tutor.getText()+"',"+
							Integer.parseInt(vedicion.tfl_año.getText())+","+
							Integer.parseInt(vedicion.tfl_nro.getText())+")");
					JOptionPane.showMessageDialog(vedicion, "Operación Exitosa");
					limpiar_campos_edicion(vedicion);

				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(vedicion, "Formato inválido, Ingrese un número");

			} catch (SQLException e) {
				e.printStackTrace();
			}
			llenar_tabla(vedicion);
		}else{
			JOptionPane.showMessageDialog(vedicion, "Debe llenar todos los campos");
		}
	}

	public void modificar_fila(Vedición vedicion){

		if (evitar_tfl_blancos(vedicion)){
			try {
				if (control == Integer.parseInt(vedicion.tfl_nro.getText())){

					modificar(vedicion);

				}else{
					ResultSet comprobar = getSt().executeQuery("SELECT * FROM TESIS WHERE Número='"+vedicion.tfl_nro.getText()+"'");
					if(comprobar.next()){
						JOptionPane.showMessageDialog(vedicion, "El número ya está asignado, Por favor introduzca un número diferente");
					}else{
						modificar(vedicion);

					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(vedicion, "Formato inválido, Ingrese un número");
			}
		}else{
			JOptionPane.showMessageDialog(vedicion, "Debe llenar todos los campos");
		}
	}

	public void atajos_de_teclado(KeyEvent e, Vlogin vlogin, Ventana ventana, Vedición vedicion, Vcuenta vcuenta) {

		if(e.getKeyCode()==KeyEvent.VK_ENTER){

			if (e.getSource()==vlogin.tfl_usuario){	vlogin.pwd_contraseña.requestFocus();}

			if (e.getSource()==vlogin.pwd_contraseña){ vlogin.btn_aceptar.doClick();}

			if (e.getSource()==ventana.carreras || e.getSource()==Ventana.linea || e.getSource()==ventana.sld_años || e.getSource()==ventana.tfl_tema){ventana.btn_buscar.doClick();}

			if (e.getSource()==vedicion.carreras || e.getSource()==Vedición.linea || e.getSource()==vedicion.sld_años || e.getSource()==vedicion.tfl_tema){vedicion.btn_buscar.doClick();}

			if (e.getSource()==vcuenta.psw_nueva_contraseña) {vcuenta.psw_repita_contraseña.requestFocus();}
		}
	}

	public void single(){
		try{
			setServer_socket(new ServerSocket(1334));
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Ya se está ejecutando una instancia de la aplicación", "Mensaje",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}

	public void ajustar_tamaño_ventana (Ventana ventana){

		ventana.imagen_fondo.setSize(ventana.getContentPane().getSize());
		ventana.panel_base.setSize(ventana.getContentPane().getSize());
		ventana.panel_base.updateUI();

	}

	public static ServerSocket getServer_socket() {
		return server_socket;
	}

	public static void setServer_socket(ServerSocket server_socket) {
		GestorDeDatos.server_socket = server_socket;
	}

	public void m_o_busqueda (Vedición vedicion) {
		if (vedicion.panelIzquierda!=null && vedicion.panelIzquierda.isVisible()) {
			vedicion.panelIzquierda.setVisible(false);
			vedicion.btn_vista_buscar.setIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/buscarOsP.png")));
			vedicion.btn_vista_buscar.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/buscarOsG.png")));
		}else if(vedicion.panelIzquierda!=null) {
			vedicion.panelIzquierda.setVisible(true);
			vedicion.btn_vista_buscar.setIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/searchmP.png")));
			vedicion.btn_vista_buscar.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/searchmG.png")));
		}
	}

	public void mostrar_vcuenta(Vcuenta vcuenta) {

		JTextField tfl_vcuenta[] = new JTextField[] {vcuenta.tfl_usuario, vcuenta.psw_nueva_contraseña, vcuenta.psw_repita_contraseña}; 

		for(JTextField t:tfl_vcuenta) {
			t.setText("");
		}
		try {
			vcuenta.tfl_usuario.setText(""+String.valueOf(getSt().executeQuery("SELECT Usuario FROM LOGIN WHERE id='1'").getObject(1)));
		}catch (Exception e) {
			e.getStackTrace();
		}
		vcuenta.setVisible(true);


	}

	public void modificarContraseña(Vcuenta vcuenta) {

		if (String.valueOf(vcuenta.psw_nueva_contraseña.getPassword()).equals(String.valueOf(vcuenta.psw_repita_contraseña.getPassword()))) {
			try {
				getSt().executeUpdate("UPDATE LOGIN SET Contraseña='"+String.valueOf(vcuenta.psw_nueva_contraseña.getPassword())+"' WHERE id=1"); //FIXME Encriptar contraseña
				JOptionPane.showMessageDialog(vcuenta, "Operación exitosa");
				vcuenta.psw_nueva_contraseña.setText("");
				vcuenta.psw_repita_contraseña.setText("");
			}catch (Exception e) {
				e.getStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(vcuenta, "Las contraseñas no coinsiden");
		}
	}

	public void modificarUsuario(Vcuenta vcuenta) {

		try {
			getSt().executeUpdate("UPDATE LOGIN SET Usuario='"+vcuenta.tfl_usuario.getText()+"' WHERE id=1");
			JOptionPane.showMessageDialog(vcuenta, "Operación exitosa");
		}catch (Exception e) {
			e.getStackTrace();
		}
	}

}//Fin de la clase gestor
