package controlador;

import gestor.GestorDeDatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ventana.Vedición;
import ventana.Ventana;
import ventana.Vlogin;

public class Controlador implements ActionListener, KeyListener, MouseListener, FocusListener, ComponentListener{

	Ventana ventana;
	GestorDeDatos gestorDeDatos;
	Vlogin vlogin;
	Vedición vedicion;

	public Controlador(Ventana ventana, GestorDeDatos gestorDatos, Vlogin vlogin, Vedición vedicion) {
		this.ventana =  ventana;
		this.gestorDeDatos = gestorDatos;
		this.vlogin = vlogin;
		this.vedicion = vedicion;

		ventana.btn_buscar.addActionListener(this);
		ventana.btn_editar.addActionListener(this);
		ventana.tabla.addMouseListener(this);
		ventana.tabla.addKeyListener(this);
		ventana.btn_salir.addActionListener(this);
		ventana.carreras.addActionListener(this);
		ventana.carreras.addKeyListener(this);
		Ventana.linea.addKeyListener(this);
		ventana.sld_años.addKeyListener(this);
		ventana.addComponentListener(this);
		ventana.tfl_tema.addKeyListener(this);
		ventana.tfl_tema.addFocusListener(this);

		vlogin.btn_cancelar.addActionListener(this);
		vlogin.btn_aceptar.addActionListener(this);
		vlogin.tfl_usuario.addFocusListener(this);
		vlogin.tfl_usuario.addKeyListener(this);
		vlogin.pwd_contraseña.addFocusListener(this);
		vlogin.pwd_contraseña.addKeyListener(this);

		vedicion.tabla.addMouseListener(this);
		vedicion.tabla.addKeyListener(this);
		vedicion.btn_aplicar.addActionListener(this);
		vedicion.btn_agregar.addActionListener(this);
		vedicion.btn_eliminar.addActionListener(this);
		vedicion.btn_salir.addActionListener(this);
		vedicion.tfl_titulo.addFocusListener(this);
		vedicion.tfl_autor.addFocusListener(this);
		vedicion.tfl_Tutor.addFocusListener(this);
		vedicion.tfl_Año.addFocusListener(this);
		vedicion.tfl_numero.addFocusListener(this);
		vedicion.cmbx_carrera.addActionListener(this);
		vedicion.cmbx_linea.addActionListener(this);
		vedicion.addComponentListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==vedicion.btn_agregar){//Agregar fila Edición
			gestorDeDatos.agregar_fila(vedicion);
		}

		if (e.getSource()==vedicion.btn_aplicar){//Aplicar edicion
			gestorDeDatos.modificar_fila(vedicion);
		}

		if (e.getSource()==vedicion.btn_eliminar){//Eliminar edicion
			gestorDeDatos.eliminar_fila(vedicion);
		}

		if (e.getSource()==ventana.btn_editar){//Editar

			gestorDeDatos.mostrar_login(ventana, vlogin);

		}

		if (e.getSource()==vlogin.btn_aceptar){// Aceptar del Login

			gestorDeDatos.aceptar_login(vlogin, vedicion);
		}

		if (e.getSource()==vlogin.btn_cancelar){ // cancelar Login
			gestorDeDatos.cancelar_login(ventana, vlogin);
		}

		if (e.getSource()==ventana.carreras){ //Contenido comboBox

			gestorDeDatos.agregarLineas_principal((String)ventana.carreras.getSelectedItem());
		}

		if (e.getSource()==vedicion.cmbx_carrera){
			gestorDeDatos.agregarLineas_editar((String) vedicion.cmbx_carrera.getSelectedItem(), vedicion);
		}

		if (e.getSource()==ventana.btn_buscar) { //Buscar 

			gestorDeDatos.buscar(ventana);

		}

		if (e.getSource()==ventana.btn_salir){// Salir principal
			System.exit(0);
		}
		if (e.getSource()==vedicion.btn_salir){	//Salir Edición
			gestorDeDatos.salir_edicion(vedicion);
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getSource()==ventana.tabla){
			gestorDeDatos.mostrar_seleccion(ventana);
		}
		if (e.getSource()==vedicion.tabla){
			gestorDeDatos.mostrar_seleccion_Veditar(vedicion);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getSource()==ventana.tabla){
			gestorDeDatos.mostrar_seleccion(ventana);
		}
		if (e.getSource()==vedicion.tabla){
			gestorDeDatos.mostrar_seleccion_Veditar(vedicion);
		}
		gestorDeDatos.atajos_de_teclado(e,vlogin,ventana);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void focusGained(FocusEvent e) {

		gestorDeDatos.focus_textField(vlogin, vedicion, ventana, e);

	}

	@Override
	public void focusLost(FocusEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		if (e.getSource()==ventana){
			gestorDeDatos.ajustar_tamaño_ventana(ventana);
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}
}
