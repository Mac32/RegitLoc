package ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({ "serial","rawtypes" })
public class Vedición extends JDialog{

	public final String[] titulos = {"Título","Autor","Tutor","Carrera","Línea","Año","Número"};
	public JScrollPane scroll;
	private JPanel pnl_edicion, panel_tabla;
	public JPanel pnl_base;
	public JTextField tfl_Año,tfl_numero,tfl_autor,tfl_Tutor, tfl_titulo;
	public JLabel imagen_fondo;
	private JLabel lbl_Nro, lbl_año,lbl_linea,lbl_Carrera,lbl_Autor,lbl_titulo,lbl_Tutor;
	private JToolBar tb_barra;
	public JButton btn_aplicar, btn_agregar,btn_eliminar,btn_salir;
	public JComboBox cmbx_carrera, cmbx_linea;
	public DefaultTableModel dtm = new DefaultTableModel(){

		public boolean isCellEditable(int fila, int columna){
			return false;
		}};
		public JTable tabla;
		public List<Object> ids = new ArrayList<Object>();
		private Font mi_font = new Font("Dialog",Font.BOLD,12);

		public Vedición(Ventana padre, String nombre, ImageIcon imagen_fondo){

			super(padre,nombre);
			setModal(true);
			setSize(1200,760);
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.imagen_fondo = new JLabel(imagen_fondo);
			agregarElementos();
		}

		@SuppressWarnings("unchecked")
		private void agregarElementos() {
			
			//Contenedor
			Container c = getContentPane();
			c.setLayout(null);
			
			//Panel Base
			pnl_base = new JPanel(new BorderLayout());
			pnl_base.setOpaque(false);
			pnl_base.setBounds(0, 0, 1200, 700);

			//Tabla
			tabla = new JTable(dtm);
			dtm.setColumnIdentifiers(titulos);
			tabla.setRowHeight(25);
			tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			//		Se añande la tabla al scroll
			scroll = new JScrollPane(tabla);
			scroll.setBackground(Color.CYAN);
			scroll.setOpaque(false);

			//		Se añade el scroll al panel
			panel_tabla = new JPanel();
			panel_tabla.setLayout(new GridLayout(1,1));
			panel_tabla.add(scroll);

			//Barra de Herramientas
			tb_barra = new JToolBar();
			tb_barra.setBorderPainted(false);
			tb_barra.setOpaque(false);
			tb_barra.setFloatable(false);
			tb_barra.setEnabled(false);

			//		Boton Aplicar cambios
			btn_aplicar = new JButton("Aplicar",new ImageIcon(Ventana.class.getResource("/Imagenes/AplicarP.png")));
			btn_aplicar.setForeground(Color.WHITE);
			btn_aplicar.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/AplicarG.png")));
			btn_aplicar.setFont(mi_font);
			btn_aplicar.setFocusable(false);
			btn_aplicar.setBorderPainted(false);
			btn_aplicar.setContentAreaFilled(false);
			btn_aplicar.setToolTipText("Aplicar Cambios (Alt+Enter)");
			btn_aplicar.setMnemonic(KeyEvent.VK_ENTER);

			//		Boton Agregar
			btn_agregar = new JButton("Agregar",new ImageIcon(Ventana.class.getResource("/Imagenes/AgregarP.png")));
			btn_agregar.setForeground(Color.WHITE);
			btn_agregar.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/AgregarG.png")));
			btn_agregar.setFont(mi_font);
			btn_agregar.setFocusable(false);
			btn_agregar.setBorderPainted(false);
			btn_agregar.setContentAreaFilled(false);
			btn_agregar.setToolTipText("Agregar (Alt+a)");
			btn_agregar.setMnemonic(KeyEvent.VK_A);

			//		Boton Eliminar
			btn_eliminar = new JButton("Eliminar",new ImageIcon(Ventana.class.getResource("/Imagenes/EliminarP.png")));
			btn_eliminar.setForeground(Color.WHITE);
			btn_eliminar.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/EliminarG.png")));
			btn_eliminar.setFont(mi_font);
			btn_eliminar.setFocusable(false);
			btn_eliminar.setBorderPainted(false);
			btn_eliminar.setContentAreaFilled(false);
			btn_eliminar.setToolTipText("Eliminar (Alt+Borrar)");
			btn_eliminar.setMnemonic(KeyEvent.VK_DELETE);

			//		Boton salir
			btn_salir = new JButton("Salir",new ImageIcon(Ventana.class.getResource("/Imagenes/exit-1.png")));
			btn_salir.setForeground(Color.WHITE);
			btn_salir.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/exit-1G.png")));
			btn_salir.setFont(mi_font);
			btn_salir.setFocusable(false);
			btn_salir.setBorderPainted(false);
			btn_salir.setContentAreaFilled(false);
			btn_salir.setToolTipText("Salir");

			//		Se agregan los elementos a la barra
			tb_barra.add(btn_aplicar);
			tb_barra.addSeparator();
			tb_barra.add(btn_agregar);
			tb_barra.addSeparator();
			tb_barra.add(btn_eliminar);
			tb_barra.addSeparator();
			tb_barra.add(btn_salir);


			//Panel de Edición
			pnl_edicion = new JPanel();
			pnl_edicion.setForeground(Color.WHITE);
			pnl_edicion.setOpaque(false);
			pnl_edicion.setLayout(null);

			//		Label Título
			lbl_titulo = new JLabel("Título: ");
			lbl_titulo.setForeground(Color.WHITE);
			lbl_titulo.setBounds(14, 12, 55, 17);
			lbl_titulo.setFont(new Font("Dialog", Font.BOLD, 12));
			pnl_edicion.add(lbl_titulo);

			//		TextField Título
			tfl_titulo = new JTextField();
			tfl_titulo.setBounds(81, 5, 1100, 30);
			tfl_titulo.setColumns(70);
			pnl_edicion.add(tfl_titulo);

			//		Label Autor
			lbl_Autor = new JLabel("Autor: ");
			lbl_Autor.setForeground(Color.WHITE);
			lbl_Autor.setBounds(14, 47, 55, 17);
			lbl_Autor.setFont(new Font("Dialog", Font.BOLD, 12));
			pnl_edicion.add(lbl_Autor);

			//		TextField Autor
			tfl_autor = new JTextField();
			tfl_autor.setBounds(81, 40, 500, 30);
			tfl_autor.setColumns(40);
			pnl_edicion.add(tfl_autor);

			//		Label Tutor
			lbl_Tutor = new JLabel("Tutor: ");
			lbl_Tutor.setForeground(Color.WHITE);
			lbl_Tutor.setBounds(625, 47, 55, 17);
			lbl_Tutor.setFont(new Font("Dialog", Font.BOLD, 12));
			pnl_edicion.add(lbl_Tutor);

			//		TextField Tutor
			tfl_Tutor = new JTextField();
			tfl_Tutor.setBounds(681, 40, 500, 30);
			tfl_Tutor.setColumns(40);
			pnl_edicion.add(tfl_Tutor);

			//		Label Carrera
			lbl_Carrera = new JLabel("Carrera: ");
			lbl_Carrera.setForeground(Color.WHITE);
			lbl_Carrera.setBounds(14, 82, 71, 17);
			lbl_Carrera.setFont(new Font("Dialog", Font.BOLD, 12));
			pnl_edicion.add(lbl_Carrera);

			//		TextField Carrera
			String[] carrerasBox = {"Ing. Industrial",
					"Ing. Mtto. Mecánico",
					"Ing. Civil",
					"Ing. Eléctrica",
					"Ing. Electrónica",
					"Ing. en Sistemas",
			"Arquitectura"};
			cmbx_carrera = new JComboBox(carrerasBox);
			cmbx_carrera.setBounds(81, 75, 500, 30);
			pnl_edicion.add(cmbx_carrera);

			//		Label Linea
			lbl_linea = new JLabel("Linea: ");
			lbl_linea.setForeground(Color.WHITE);
			lbl_linea.setBounds(625, 82, 53, 17);
			lbl_linea.setFont(new Font("Dialog", Font.BOLD, 12));
			pnl_edicion.add(lbl_linea);

			//		TextField Linea
			String[] lineaBox = {"Gerencia",
					"Operaciones",
					"Procesos Industriales",
					"Mejoramiento continuo",
			"Agroindustria"};
			cmbx_linea = new JComboBox(lineaBox);
			cmbx_linea.setBounds(681, 75, 500, 30);
			pnl_edicion.add(cmbx_linea);

			//		Label Año
			lbl_año = new JLabel("Año:");
			lbl_año.setForeground(Color.WHITE);
			lbl_año.setBounds(14, 118, 37, 17);
			lbl_año.setFont(new Font("Dialog", Font.BOLD, 12));
			pnl_edicion.add(lbl_año);

			//		TextField Año
			tfl_Año = new JTextField();
			tfl_Año.setBounds(81, 111, 500, 30);
			tfl_Año.setColumns(40);
			pnl_edicion.add(tfl_Año);

			//		Label Número
			lbl_Nro = new JLabel("Nro:");
			lbl_Nro.setForeground(Color.WHITE);
			lbl_Nro.setBounds(625, 118, 34, 17);
			lbl_Nro.setFont(new Font("Dialog", Font.BOLD, 12));
			pnl_edicion.add(lbl_Nro);

			//		TextField Número
			tfl_numero = new JTextField();
			tfl_numero.setBounds(681, 111, 500, 30);
			tfl_numero.setColumns(40);
			pnl_edicion.add(tfl_numero);

			//Se añaden los elementos al contenedor
			pnl_base.add(tb_barra, BorderLayout.NORTH);
			pnl_base.add(pnl_edicion, BorderLayout.CENTER);
			pnl_base.add(panel_tabla, BorderLayout.SOUTH);
			
			imagen_fondo.setBounds(0, 0, 1200, 700);
			
			c.add(pnl_base);
			c.add(imagen_fondo);
			
		}
}
