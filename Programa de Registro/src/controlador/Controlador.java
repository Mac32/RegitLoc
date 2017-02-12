package controlador;

import gestor.GestorDeDatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import ventana.Ventana;

public class Controlador implements ActionListener, KeyListener, MouseListener{

	Ventana ventana;
	GestorDeDatos gestorDeDatos;

	public Controlador(Ventana ventana, GestorDeDatos gestorDatos) {
		this.ventana =  ventana;
		this.gestorDeDatos = gestorDatos;

		ventana.btn_buscar.addActionListener(this);
		ventana.btn_login.addActionListener(this);
		ventana.tabla.addMouseListener(this);
		ventana.tabla.addKeyListener(this);
		ventana.btn_salir.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==ventana.btn_buscar) {

			ventana.strn_carreraS = ((String)ventana.carreras.getSelectedItem());
			ventana.strn_lineaS = ((String)ventana.linea.getSelectedItem());

			try {
				ventana.dtm.setRowCount(0);
				ventana.dtm.setColumnCount(0);
				ventana.dtm.setColumnIdentifiers(ventana.titulos);
				ventana.ids.clear();
				ResultSet aux = gestorDeDatos.getSt().executeQuery("SELECT * FROM TESIS WHERE" +
						" Carrera = '"+ventana.strn_carreraS+"' AND " +
						"Linea = '"+ventana.strn_lineaS+"'");
				while(aux.next()){	
					Object[] fila = {aux.getObject(4), aux.getObject(5), aux.getObject(6)};
					ventana.ids.add((Integer) aux.getObject(1));
					ventana.dtm.addRow(fila);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getSource()==ventana.btn_salir){
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			ResultSet seleccion = gestorDeDatos.getSt().executeQuery("SELECT * FROM TESIS WHERE id="+ventana.ids.get(ventana.tabla.getSelectedRow()));
			ventana.lbl_CarreraPD.setText(""+seleccion.getObject(2));
			ventana.lbl_lineaPD.setText(""+seleccion.getObject(3));
			ventana.lbl_titulo.setText(""+seleccion.getObject(4));
			ventana.lbl_Autor.setText(""+seleccion.getObject(5));
			ventana.lbl_tutor.setText(""+seleccion.getObject(6));
			ventana.lbl_año.setText(""+seleccion.getObject(7));

		} catch (SQLException e1) {
			e1.printStackTrace();
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
		mousePressed(null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
