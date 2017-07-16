package controlador;

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

import gestor.GestorDeDatos;
import ventana.Vcuenta;
import ventana.Vedición;
import ventana.Ventana;
import ventana.Vlogin;

public class Controlador implements ActionListener, KeyListener, MouseListener, FocusListener, ComponentListener{

	Ventana ventana;
	GestorDeDatos gestorDeDatos;
	Vlogin vlogin;
	Vedición vedicion;
	Vcuenta vcuenta;

	public Controlador(Ventana ventana, GestorDeDatos gestorDatos, Vlogin vlogin, Vedición vedicion, Vcuenta vcuenta) {
		this.ventana =  ventana;
		this.gestorDeDatos = gestorDatos;
		this.vlogin = vlogin;
		this.vedicion = vedicion;
		this.vcuenta = vcuenta;

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
		vedicion.btn_vista_buscar.addActionListener(this);
		vedicion.btn_login.addActionListener(this);
		vedicion.btn_salir.addActionListener(this);
		vedicion.tfl_titulo.addFocusListener(this);
		vedicion.tfl_autor.addFocusListener(this);
		vedicion.tfl_tutor.addFocusListener(this);
		vedicion.tfl_año.addFocusListener(this);
		vedicion.btn_buscar.addActionListener(this);
		vedicion.tfl_nro.addFocusListener(this);
		vedicion.cmbx_carrera.addActionListener(this);
		vedicion.cmbx_linea.addActionListener(this);
		vedicion.addComponentListener(this);
		vedicion.tfl_tema.addKeyListener(this);
		vedicion.carreras.addKeyListener(this);
		Vedición.linea.addKeyListener(this);
		vedicion.sld_años.addKeyListener(this);

		vcuenta.tfl_usuario.addFocusListener(this);
		vcuenta.psw_nueva_contraseña.addFocusListener(this);
		vcuenta.psw_repita_contraseña.addFocusListener(this);
		vcuenta.psw_nueva_contraseña.addKeyListener(this);
		vcuenta.psw_repita_contraseña.addKeyListener(this);
		vcuenta.btn_ModUsuario.addActionListener(this);
		vcuenta.btn_ModContraseña.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==vcuenta.btn_ModContraseña) {gestorDeDatos.modificarContraseña(vcuenta);} //Modifica Contraseña
		
		if (e.getSource()==vcuenta.btn_ModUsuario) {gestorDeDatos.modificarUsuario(vcuenta);} //Modifia Nombre de usuario
		
		if (e.getSource()==vedicion.btn_login) { gestorDeDatos.mostrar_vcuenta(vcuenta);} //Muestra la ventana de cuenta
		
		if (e.getSource()==vedicion.btn_vista_buscar){ gestorDeDatos.m_o_busqueda(vedicion);} // Muestra / Oculta el Panel de busqueda en la ventana de edición
		
		if (e.getSource()==vedicion.btn_agregar){ gestorDeDatos.agregar_fila(vedicion);} //Agregar fila Edición

		if (e.getSource()==vedicion.btn_aplicar){ gestorDeDatos.modificar_fila(vedicion);} //Aplicar edicion

		if (e.getSource()==vedicion.btn_eliminar){ gestorDeDatos.eliminar_fila(vedicion);} //Eliminar edicion

		if (e.getSource()==ventana.btn_editar){ gestorDeDatos.mostrar_login(ventana, vlogin);} //Editar

		if (e.getSource()==vlogin.btn_aceptar){ gestorDeDatos.aceptar_login(vlogin, vedicion);} // Aceptar del Login

		if (e.getSource()==vlogin.btn_cancelar){ gestorDeDatos.cancelar_login(ventana, vlogin);} // cancelar Login

		if (e.getSource()==ventana.carreras){ gestorDeDatos.agregarLineas_principal((String)ventana.carreras.getSelectedItem());} //Contenido comboBox Ventana principal

		if (e.getSource()==vedicion.cmbx_carrera){ gestorDeDatos.agregarLineas_editar((String) vedicion.cmbx_carrera.getSelectedItem(), vedicion);} //Contenido comboBox Ventana edicion

		if (e.getSource()==vedicion.btn_buscar) {gestorDeDatos.buscar(vedicion);} // Buscar en Edición
		
		if (e.getSource()==ventana.btn_buscar) { gestorDeDatos.buscar(ventana);}  //Buscar 

		if (e.getSource()==ventana.btn_salir){ System.exit(0);} // Salir principal
		
		if (e.getSource()==vedicion.btn_salir){	gestorDeDatos.salir_edicion(vedicion);} //Salir Edición
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getSource()==ventana.tabla){ gestorDeDatos.mostrar_seleccion(ventana);} //Muestra elemento de la tabla seleccionado Ventana principal
		
		if (e.getSource()==vedicion.tabla){ gestorDeDatos.mostrar_seleccion_Veditar(vedicion);} //Muestra elemento de la tabla seleccionado Ventana edición
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getSource()==ventana.tabla){ gestorDeDatos.mostrar_seleccion(ventana);}
		
		if (e.getSource()==vedicion.tabla){ gestorDeDatos.mostrar_seleccion_Veditar(vedicion);}
		
		gestorDeDatos.atajos_de_teclado(e,vlogin,ventana,vedicion, vcuenta);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void focusGained(FocusEvent e) {	gestorDeDatos.focus_textField(vlogin, vedicion, ventana, e, vcuenta);}

	@Override
	public void focusLost(FocusEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		
		if (e.getSource()==ventana){ gestorDeDatos.ajustar_tamaño_ventana(ventana);}
	
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
