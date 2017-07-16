package ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({ "serial","rawtypes" })
public class Vedición extends JDialog{

	public final String[] titulos = {"Título","Autor","Tutor","Carrera","Línea","Año","Número"};
	private JToolBar tb_barra;
	private JPanel pnl_edicion, panel_tabla,pnl_derecha;
	private JLabel lbl_lineaPI,lbl_nro, lbl_año,lbl_linea,lbl_carrera,lbl_autor,lbl_titulo,lbl_tutor,lblBuscar,lbl_carreraPI,lbl_Ran_años;
	public JScrollPane scroll;
	public JPanel pnl_base, panelIzquierda;
	public JTextField tfl_año,tfl_nro,tfl_autor,tfl_tutor, tfl_titulo,tfl_tema;
	public JLabel imagen_fondo;
	public JButton btn_aplicar, btn_agregar,btn_eliminar,btn_salir,btn_buscar,btn_login,btn_vista_buscar;
	public JComboBox cmbx_carrera, cmbx_linea,carreras;
	public static JComboBox linea;
	public JSlider sld_años;
	public DefaultTableModel dtm = new DefaultTableModel(){

		public boolean isCellEditable(int fila, int columna){
			return false;
		}};
		public JTable tabla = new JTable(dtm);
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
			btn_aplicar.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/AplicarG.png")));
			btn_aplicar.setFont(mi_font);
			btn_aplicar.setFocusable(false);
			btn_aplicar.setBorderPainted(false);
			btn_aplicar.setContentAreaFilled(false);
			btn_aplicar.setToolTipText("Aplicar Cambios (Alt+Enter)");
			btn_aplicar.setMnemonic(KeyEvent.VK_ENTER);

			//		Boton Agregar
			btn_agregar = new JButton("Agregar",new ImageIcon(Ventana.class.getResource("/Imagenes/AgregarP.png")));
			btn_agregar.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/AgregarG.png")));
			btn_agregar.setFont(mi_font);
			btn_agregar.setFocusable(false);
			btn_agregar.setBorderPainted(false);
			btn_agregar.setContentAreaFilled(false);
			btn_agregar.setToolTipText("Agregar (Alt+a)");
			btn_agregar.setMnemonic(KeyEvent.VK_A);

			//		Boton Eliminar
			btn_eliminar = new JButton("Eliminar",new ImageIcon(Ventana.class.getResource("/Imagenes/EliminarP.png")));
			btn_eliminar.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/EliminarG.png")));
			btn_eliminar.setFont(mi_font);
			btn_eliminar.setFocusable(false);
			btn_eliminar.setBorderPainted(false);
			btn_eliminar.setContentAreaFilled(false);
			btn_eliminar.setToolTipText("Eliminar (Alt+Borrar)");
			btn_eliminar.setMnemonic(KeyEvent.VK_DELETE);

			//		Boton Mostrar Vista Buscar
			btn_vista_buscar = new JButton("Buscar", new ImageIcon(Ventana.class.getResource("/Imagenes/buscarOsP.png")));
			btn_vista_buscar.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/buscarOsG.png")));
			btn_vista_buscar.setFont(mi_font);
			btn_vista_buscar.setFocusable(false);
			btn_vista_buscar.setBorderPainted(false);
			btn_vista_buscar.setContentAreaFilled(false);
			btn_vista_buscar.setToolTipText("Mostrar Panel de Búsqueda");

			//		Boton Registrar Usuario
			btn_login = new JButton("Cuenta de Usuario", new ImageIcon(Ventana.class.getResource("/Imagenes/UserP.png")));
			btn_login.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/UserG.png")));
			btn_login.setFont(mi_font);
			btn_login.setFocusable(false);
			btn_login.setBorderPainted(false);
			btn_login.setContentAreaFilled(false);
			btn_login.setToolTipText("Muestra Ventana de Cuenta de Usuarios");

			//		Boton salir
			btn_salir = new JButton("Salir",new ImageIcon(Ventana.class.getResource("/Imagenes/exit-1.png")));
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
			tb_barra.add(btn_vista_buscar);
			tb_barra.addSeparator();
			tb_barra.add(btn_login);
			tb_barra.addSeparator();
			tb_barra.add(btn_salir);

			//Panel de Edición
			pnl_edicion = new JPanel();
			pnl_edicion.setOpaque(false);
			pnl_edicion.setBorder(new TitledBorder(null, "Edición", TitledBorder.LEADING, TitledBorder.TOP, null));
			GridBagLayout gbl_pnl_edición = new GridBagLayout();
			gbl_pnl_edición.columnWidths = new int[]{0, 0, 0, 0};
			gbl_pnl_edición.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0};
			gbl_pnl_edición.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
			pnl_edicion.setLayout(gbl_pnl_edición);

			//		Label Título
			lbl_titulo = new JLabel("Título: ");
			lbl_titulo.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_titulo = new GridBagConstraints();
			gbc_lbl_titulo.gridx = 0;
			gbc_lbl_titulo.gridy = 0;
			gbc_lbl_titulo.gridwidth = 1;
			gbc_lbl_titulo.anchor = GridBagConstraints.WEST;
			gbc_lbl_titulo.fill = GridBagConstraints.HORIZONTAL;
			gbc_lbl_titulo.insets = new Insets(10, 5, 10, 5);
			pnl_edicion.add(lbl_titulo, gbc_lbl_titulo);

			//		TextField Título
			tfl_titulo = new JTextField();
			tfl_titulo.setColumns(70);
			GridBagConstraints gbc_tfl_titulo = new GridBagConstraints();
			gbc_tfl_titulo.gridx = 1;
			gbc_tfl_titulo.gridy = 0;
			gbc_tfl_titulo.gridwidth = 3;
			gbc_tfl_titulo.anchor = GridBagConstraints.WEST;
			gbc_tfl_titulo.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfl_titulo.insets = new Insets(10, 0, 10, 20);
			pnl_edicion.add(tfl_titulo, gbc_tfl_titulo);

			//		Label Autor
			lbl_autor = new JLabel("Autor: ");
			lbl_autor.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_autor = new GridBagConstraints();
			gbc_lbl_autor.gridx = 0;
			gbc_lbl_autor.gridy = 1;
			gbc_lbl_autor.gridheight = 1;
			gbc_lbl_autor.gridwidth = 1;
			gbc_lbl_autor.anchor = GridBagConstraints.WEST;
			gbc_lbl_autor.fill = GridBagConstraints.HORIZONTAL;
			gbc_lbl_autor.insets = new Insets(10, 5, 10, 5);
			pnl_edicion.add(lbl_autor, gbc_lbl_autor);

			//		TextField Autor
			tfl_autor = new JTextField();
			tfl_autor.setColumns(40);
			GridBagConstraints gbc_tfl_autor = new GridBagConstraints();
			gbc_tfl_autor.gridx = 1;
			gbc_tfl_autor.gridy = 1;
			gbc_tfl_autor.gridheight = 1;
			gbc_tfl_autor.gridwidth = 1;
			gbc_tfl_autor.anchor = GridBagConstraints.WEST;
			gbc_tfl_autor.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfl_autor.insets = new Insets(10, 0, 10, 20);
			pnl_edicion.add(tfl_autor, gbc_tfl_autor);

			//		Label Tutor
			lbl_tutor = new JLabel("Tutor: ");
			lbl_tutor.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_tutor = new GridBagConstraints();
			gbc_lbl_tutor.gridx = 2;
			gbc_lbl_tutor.gridy = 1;
			gbc_lbl_tutor.gridheight = 1;
			gbc_lbl_tutor.gridwidth = 1;
			gbc_lbl_tutor.anchor = GridBagConstraints.WEST;
			gbc_lbl_tutor.fill = GridBagConstraints.HORIZONTAL;
			gbc_lbl_tutor.insets = new Insets(10, 5, 10, 5);
			pnl_edicion.add(lbl_tutor, gbc_lbl_tutor);

			//		TextField Tutor
			tfl_tutor = new JTextField();
			tfl_tutor.setColumns(40);
			GridBagConstraints gbc_tfl_tutor = new GridBagConstraints();
			gbc_tfl_tutor.gridx = 3;
			gbc_tfl_tutor.gridy = 1;
			gbc_tfl_tutor.gridheight = 1;
			gbc_tfl_tutor.gridwidth = 1;
			gbc_tfl_tutor.anchor = GridBagConstraints.WEST;
			gbc_tfl_tutor.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfl_tutor.insets = new Insets(10, 0, 10, 20);
			pnl_edicion.add(tfl_tutor, gbc_tfl_tutor);

			//		Label Carrera
			lbl_carrera = new JLabel("Carrera: ");
			lbl_carrera.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_carrera = new GridBagConstraints();
			gbc_lbl_carrera.gridx = 0;
			gbc_lbl_carrera.gridy = 2;
			gbc_lbl_carrera.gridheight = 1;
			gbc_lbl_carrera.gridwidth = 1;
			gbc_lbl_carrera.anchor = GridBagConstraints.WEST;
			gbc_lbl_carrera.fill = GridBagConstraints.HORIZONTAL;
			gbc_lbl_carrera.insets = new Insets(10, 5, 10, 5);
			pnl_edicion.add(lbl_carrera, gbc_lbl_carrera);

			//		ComboBox Carrera
			String[] carrerasBox = {"Ing. Industrial",
					"Ing. Mtto. Mecánico",
					"Ing. Civil",
					"Ing. Eléctrica",
					"Ing. Electrónica",
					"Ing. en Sistemas",
			"Arquitectura"};

			cmbx_carrera = new JComboBox(carrerasBox);
			GridBagConstraints gbc_cmbx_carrera = new GridBagConstraints();
			gbc_cmbx_carrera.gridx = 1;
			gbc_cmbx_carrera.gridy = 2;
			gbc_cmbx_carrera.gridheight = 1;
			gbc_cmbx_carrera.gridwidth = 1;
			gbc_cmbx_carrera.anchor = GridBagConstraints.WEST;
			gbc_cmbx_carrera.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbx_carrera.insets = new Insets(10, 0, 10, 20);
			pnl_edicion.add(cmbx_carrera, gbc_cmbx_carrera);

			//		Label Linea
			lbl_linea = new JLabel("Linea: ");
			lbl_linea.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_linea = new GridBagConstraints();
			gbc_lbl_linea.gridx = 2;
			gbc_lbl_linea.gridy = 2;
			gbc_lbl_linea.gridheight = 1;
			gbc_lbl_linea.gridwidth = 1;
			gbc_lbl_linea.anchor = GridBagConstraints.WEST;
			gbc_lbl_linea.fill = GridBagConstraints.HORIZONTAL;
			gbc_lbl_linea.insets = new Insets(10, 5, 10, 5);
			pnl_edicion.add(lbl_linea, gbc_lbl_linea);

			//		ComboBox Linea
			String[] lineaBox = {"Gerencia",
					"Operaciones",
					"Procesos Industriales",
					"Mejoramiento continuo",
			"Agroindustria"};

			cmbx_linea = new JComboBox(lineaBox);
			GridBagConstraints gbc_cmbx_linea = new GridBagConstraints();
			gbc_cmbx_linea.gridx = 3;
			gbc_cmbx_linea.gridy = 2;
			gbc_cmbx_linea.gridheight = 1;
			gbc_cmbx_linea.gridwidth = 1;
			gbc_cmbx_linea.anchor = GridBagConstraints.WEST;
			gbc_cmbx_linea.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbx_linea.insets = new Insets(10, 0, 10, 20);
			pnl_edicion.add(cmbx_linea, gbc_cmbx_linea);

			//		Label Año
			lbl_año = new JLabel("Año:");
			lbl_año.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_año = new GridBagConstraints();
			gbc_lbl_año.gridx = 0;
			gbc_lbl_año.gridy = 3;
			gbc_lbl_año.gridheight = 1;
			gbc_lbl_año.gridwidth = 1;
			gbc_lbl_año.anchor = GridBagConstraints.WEST;
			gbc_lbl_año.fill = GridBagConstraints.HORIZONTAL;
			gbc_lbl_año.insets = new Insets(10, 5, 10, 5);
			pnl_edicion.add(lbl_año, gbc_lbl_año);

			//		TextField Año
			tfl_año = new JTextField();
			tfl_año.setColumns(40);
			GridBagConstraints gbc_tfl_linea = new GridBagConstraints();
			gbc_tfl_linea.gridx = 1;
			gbc_tfl_linea.gridy = 3;
			gbc_tfl_linea.gridheight = 1;
			gbc_tfl_linea.gridwidth = 1;
			gbc_tfl_linea.anchor = GridBagConstraints.WEST;
			gbc_tfl_linea.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfl_linea.insets = new Insets(10, 0, 10, 20);
			pnl_edicion.add(tfl_año, gbc_tfl_linea);

			//		Label Número
			lbl_nro = new JLabel("Nro:");
			lbl_nro.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_nro = new GridBagConstraints();
			gbc_lbl_nro.gridx = 2;
			gbc_lbl_nro.gridy = 3;
			gbc_lbl_nro.gridheight = 1;
			gbc_lbl_nro.gridwidth = 1;
			gbc_lbl_nro.anchor = GridBagConstraints.WEST;
			gbc_lbl_nro.fill = GridBagConstraints.HORIZONTAL;
			gbc_lbl_nro.insets = new Insets(10, 5, 10, 5);
			pnl_edicion.add(lbl_nro, gbc_lbl_nro);

			//			TextField Número
			tfl_nro = new JTextField();
			tfl_nro.setColumns(40);
			GridBagConstraints gbc_tfl_nro = new GridBagConstraints();
			gbc_tfl_nro.gridx = 3;
			gbc_tfl_nro.gridy = 3;
			gbc_tfl_nro.gridheight = 1;
			gbc_tfl_nro.gridwidth = 1;
			gbc_tfl_nro.anchor = GridBagConstraints.WEST;
			gbc_tfl_nro.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfl_nro.insets = new Insets(10, 0, 10, 20);
			pnl_edicion.add(tfl_nro, gbc_tfl_nro);

			// Panel Izquierda
			panelIzquierda = new JPanel();
			panelIzquierda.setOpaque(false);
			panelIzquierda.setBorder(new TitledBorder(null, "Filtro de Datos", TitledBorder.LEADING, TitledBorder.TOP, null));
			GridBagLayout gbl_panelIzquierda = new GridBagLayout();
			gbl_panelIzquierda.rowHeights = new int[]{0, 37, 24, 24, 0, 176, 80, 0};
			gbl_panelIzquierda.columnWeights = new double[]{0.0, 1.0};
			panelIzquierda.setLayout(gbl_panelIzquierda);
			panelIzquierda.setVisible(false);

			lblBuscar = new JLabel("Buscar:");
			GridBagConstraints gbc_lblBuscar = new GridBagConstraints();
			gbc_lblBuscar.anchor = GridBagConstraints.WEST;
			gbc_lblBuscar.insets = new Insets(5, 5, 5, 5);
			gbc_lblBuscar.gridx = 0;
			gbc_lblBuscar.gridy = 0;
			panelIzquierda.add(lblBuscar, gbc_lblBuscar);

			tfl_tema = new JTextField();
			GridBagConstraints gbc_tfl_tema = new GridBagConstraints();
			gbc_tfl_tema.insets = new Insets(5, 5, 5, 0);
			gbc_tfl_tema.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfl_tema.gridx = 1;
			gbc_tfl_tema.gridy = 0;
			panelIzquierda.add(tfl_tema, gbc_tfl_tema);
			tfl_tema.setColumns(10);

			//		Label de carreras
			lbl_carreraPI = new JLabel("Carrera: ");
			lbl_carreraPI.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_carreraPI = new GridBagConstraints();
			gbc_lbl_carreraPI.anchor = GridBagConstraints.WEST;
			gbc_lbl_carreraPI.insets = new Insets(5, 5, 5, 5);
			gbc_lbl_carreraPI.gridx = 0;
			gbc_lbl_carreraPI.gridy = 1;
			panelIzquierda.add(lbl_carreraPI, gbc_lbl_carreraPI);

			//		ComboBox de carreras
			String[] carrerasBox1 = {"Ing. Industrial",
					"Ing. Mtto. Mecánico",
					"Ing. Civil",
					"Ing. Eléctrica",
					"Ing. Electrónica",
					"Ing. en Sistemas",
			"Arquitectura"};

			carreras = new JComboBox(carrerasBox1);
			carreras.setOpaque(false);
			carreras.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_carreras = new GridBagConstraints();
			gbc_carreras.fill = GridBagConstraints.HORIZONTAL;
			gbc_carreras.insets = new Insets(5, 5, 5, 0);
			gbc_carreras.gridx = 1;
			gbc_carreras.gridy = 1;
			panelIzquierda.add(carreras, gbc_carreras);

			//		Label de linea
			lbl_lineaPI = new JLabel("Linea: ");
			lbl_lineaPI.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_lineaPI = new GridBagConstraints();
			gbc_lbl_lineaPI.anchor = GridBagConstraints.WEST;
			gbc_lbl_lineaPI.insets = new Insets(5, 5, 5, 5);
			gbc_lbl_lineaPI.gridx = 0;
			gbc_lbl_lineaPI.gridy = 2;
			panelIzquierda.add(lbl_lineaPI, gbc_lbl_lineaPI);

			//		ComboBox de linea
			String[] lineaBox1 = {"--Seleccione Línea--",
					"Gerencia",
					"Operaciones",
					"Procesos Industriales",
					"Mejoramiento continuo",
			"Agroindustria"};

			linea = new JComboBox(lineaBox1);
			linea.setOpaque(false);
			linea.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_linea = new GridBagConstraints();
			gbc_linea.fill = GridBagConstraints.HORIZONTAL;
			gbc_linea.insets = new Insets(5, 5, 5, 0);
			gbc_linea.gridx = 1;
			gbc_linea.gridy = 2;
			panelIzquierda.add(linea, gbc_linea);

			//		Label de rango de años
			lbl_Ran_años = new JLabel("Rango de años: ");
			lbl_Ran_años.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_Ran_años = new GridBagConstraints();
			gbc_lbl_Ran_años.anchor = GridBagConstraints.WEST;
			gbc_lbl_Ran_años.insets = new Insets(5, 5, 5, 5);
			gbc_lbl_Ran_años.gridx = 0;
			gbc_lbl_Ran_años.gridy = 3;
			panelIzquierda.add(lbl_Ran_años, gbc_lbl_Ran_años);

			//		Slider de Rango de años
			sld_años = new JSlider(1, 10, 5);
			sld_años.setOpaque(false);
			sld_años.setMajorTickSpacing(1);
			sld_años.setPaintTicks(true);
			sld_años.setPaintLabels(true);
			sld_años.setToolTipText("");
			GridBagConstraints gbc_sld_años = new GridBagConstraints();
			gbc_sld_años.insets = new Insets(0, 0, 5, 0);
			gbc_sld_años.gridx = 1;
			gbc_sld_años.gridy = 3;
			panelIzquierda.add(sld_años, gbc_sld_años);

			//		Boton Buscar
			btn_buscar = new JButton(new ImageIcon(Ventana.class.getResource("/Imagenes/searchmP.png")));
			btn_buscar.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/searchmG.png")));
			btn_buscar.setContentAreaFilled(false);
			btn_buscar.setToolTipText("Buscar");
			btn_buscar.setMargin(new Insets(5, 5, 5, 5));
			btn_buscar.setFocusPainted(false);
			btn_buscar.setBorderPainted(false);
			GridBagConstraints gbc_btn_buscar = new GridBagConstraints();
			gbc_btn_buscar.gridwidth = 2;
			gbc_btn_buscar.insets = new Insets(5, 5, 5, 0);
			gbc_btn_buscar.gridx = 0;
			gbc_btn_buscar.gridy = 5;
			panelIzquierda.add(btn_buscar, gbc_btn_buscar);

			//Panel Derecha
			pnl_derecha = new JPanel();
			pnl_derecha.setOpaque(false);
			pnl_derecha.setLayout(new GridLayout(0,1));
			pnl_derecha.add(pnl_edicion);
			pnl_derecha.add(panel_tabla);

			//Se añaden los elementos al contenedor
			pnl_base.add(tb_barra, BorderLayout.NORTH);
			pnl_base.add(panelIzquierda,BorderLayout.WEST);
			pnl_base.add(pnl_derecha, BorderLayout.CENTER);

			imagen_fondo.setBounds(0, 0, 1200, 700);

			c.add(pnl_base);
			c.add(imagen_fondo);

		}
}
