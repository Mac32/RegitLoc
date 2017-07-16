package ventana;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.events.KeyEvent;


@SuppressWarnings({ "serial", "rawtypes" })
public class Ventana extends JFrame{

	public final String[] titulos = {"Título","Autor","Tutor"};
	public JButton btn_buscar,btn_login,btn_salir, btn_editar;
	public JScrollPane scroll;
	public JComboBox carreras;
	public static JComboBox linea;
	public JLabel lbl_carrera,lbl_linea,lbl_tutor,lbl_Autor,lbl_año,lbl_lineaPD,lbl_CarreraPD, imagen_fondo;
	private JToolBar barraHerramientas;
	public JPanel panelIzquierda, panel_base;
	private JPanel panelDerecha, pnl_detalles;
	public String strn_carreraS, strn_lineaS;
	public DefaultTableModel dtm = new DefaultTableModel(){

		public boolean isCellEditable(int fila, int columna){
			return false;
		}};
		public JTable tabla = new JTable(dtm);
		public List<Object> ids = new ArrayList<Object>();
		private JLabel lbl_Ran_años, lblNro, lblAo, lblLinea, lblAutor, lblTutor, lblTtulo, lblCarrera;
		public JLabel lbl_Nro_E;
		public JSlider sld_años;
		public int años;
		public int año_base;
		private Font mi_font = new Font("Dialog", Font.BOLD, 12);
		private JLabel lblBuscar;
		public JTextField tfl_tema;
		public JTextArea txtArea_Titulo;

		public Ventana(ImageIcon imagen_fondo) {
			setTitle("Registro");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(1150,800);
			//setResizable(false);
			//setExtendedState(MAXIMIZED_BOTH);
			setLocationRelativeTo(null);
			setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logo.png")).getImage());
			getContentPane().setLayout(null);
			this.imagen_fondo = new JLabel(imagen_fondo);
			agregarElementos();
			setVisible(true);
		}


		@SuppressWarnings({ "unchecked" })
		public void agregarElementos() {

			//Contenedor
			Container c = getContentPane();
			c.setLayout(null);
			panel_base = new JPanel(new BorderLayout());
			panel_base.setBounds(0, 0, 1200, 700);
			panel_base.setOpaque(false);

			//se crea la barra de herramientas
			barraHerramientas = new JToolBar();
			barraHerramientas.setOpaque(false);
			barraHerramientas.setBorderPainted(false);
			barraHerramientas.setEnabled(false);

			//		Boton editar
			btn_editar = new JButton("Editar",new ImageIcon(Ventana.class.getResource("/Imagenes/EditarP.png")));
			btn_editar.setFont(mi_font);
			btn_editar.setRolloverEnabled(false);
			btn_editar.setVerifyInputWhenFocusTarget(false);
			btn_editar.setRequestFocusEnabled(false);
			btn_editar.setFocusable(false);
			btn_editar.setFocusTraversalKeysEnabled(false);
			btn_editar.setBorderPainted(false);
			btn_editar.setContentAreaFilled(false);
			btn_editar.setFocusPainted(false);
			btn_editar.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/EditarG.png")));
			btn_editar.setToolTipText("Editar Tabla (Alt+e)");
			btn_editar.setMargin(new Insets(5, 5, 5, 5));
			btn_editar.setMnemonic(KeyEvent.VK_E);

			//		Boton salir
			btn_salir = new JButton("Salir",new ImageIcon(Ventana.class.getResource("/Imagenes/exit-1.png")));
			btn_salir.setFont(mi_font);
			btn_salir.setBorderPainted(false);
			btn_salir.setFocusable(false);
			btn_salir.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/exit-1G.png")));
			btn_salir.setContentAreaFilled(false);
			btn_salir.setToolTipText("Salir");

			//		Se Agregan los elementos a la barra
			barraHerramientas.add(btn_editar);
			barraHerramientas.addSeparator();
			barraHerramientas.add(btn_salir);

			//Tabla
			dtm.setColumnIdentifiers(titulos);
			tabla.setOpaque(false);
			tabla.setRowHeight(25);
			tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Hace que solo se pueda seleccionar una fila de la tabla


			//Creación de Panel Izquierdo
			panelIzquierda = new JPanel();
			panelIzquierda.setOpaque(false);
			panelIzquierda.setBorder(new TitledBorder(null, "Filtro de Datos", TitledBorder.LEADING, TitledBorder.TOP, null));
			btn_salir.setMargin(new Insets(5, 5, 5, 5));
			GridBagLayout gbl_panelIzquierda = new GridBagLayout();
			gbl_panelIzquierda.rowHeights = new int[]{0, 37, 24, 24, 0, 176, 80, 0};
			gbl_panelIzquierda.columnWeights = new double[]{0.0, 1.0};
			panelIzquierda.setLayout(gbl_panelIzquierda);

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
			lbl_carrera = new JLabel("Carrera: ");
			lbl_carrera.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_carrera = new GridBagConstraints();
			gbc_lbl_carrera.anchor = GridBagConstraints.WEST;
			gbc_lbl_carrera.insets = new Insets(5, 5, 5, 5);
			gbc_lbl_carrera.gridx = 0;
			gbc_lbl_carrera.gridy = 1;
			panelIzquierda.add(lbl_carrera, gbc_lbl_carrera);

			//		ComboBox de carreras
			String[] carrerasBox = {"Ing. Industrial",
					"Ing. Mtto. Mecánico",
					"Ing. Civil",
					"Ing. Eléctrica",
					"Ing. Electrónica",
					"Ing. en Sistemas",
			"Arquitectura"};

			carreras = new JComboBox(carrerasBox);
			carreras.setOpaque(false);
			carreras.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_carreras = new GridBagConstraints();
			gbc_carreras.fill = GridBagConstraints.HORIZONTAL;
			gbc_carreras.insets = new Insets(5, 5, 5, 0);
			gbc_carreras.gridx = 1;
			gbc_carreras.gridy = 1;
			panelIzquierda.add(carreras, gbc_carreras);

			//		Label de linea
			lbl_linea = new JLabel("Linea: ");
			lbl_linea.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_linea = new GridBagConstraints();
			gbc_lbl_linea.anchor = GridBagConstraints.WEST;
			gbc_lbl_linea.insets = new Insets(5, 5, 5, 5);
			gbc_lbl_linea.gridx = 0;
			gbc_lbl_linea.gridy = 2;
			panelIzquierda.add(lbl_linea, gbc_lbl_linea);

			//		ComboBox de linea
			String[] lineaBox = {"--Seleccione Línea--",
					"Gerencia",
					"Operaciones",
					"Procesos Industriales",
					"Mejoramiento continuo",
			"Agroindustria"};

			linea = new JComboBox(lineaBox);
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

			//Creacion de panel detalles
			pnl_detalles = new JPanel();
			pnl_detalles.setOpaque(false);
			pnl_detalles.setBorder(new TitledBorder(null, "Detalles", TitledBorder.LEADING, TitledBorder.TOP, null));
			GridBagLayout gbl_pnl_detalles = new GridBagLayout();
			gbl_pnl_detalles.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0};
			gbl_pnl_detalles.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
			pnl_detalles.setLayout(gbl_pnl_detalles);
			
			//		Label Título
			lblTtulo = new JLabel("Título: ");
			lblTtulo.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
			gbc_lblTtulo.anchor = GridBagConstraints.WEST;
			gbc_lblTtulo.insets = new Insets(0, 10, 5, 5);
			gbc_lblTtulo.gridx = 1;
			gbc_lblTtulo.gridy = 1;
			pnl_detalles.add(lblTtulo, gbc_lblTtulo);
			
			txtArea_Titulo = new JTextArea(2, 80);
			txtArea_Titulo.setOpaque(false);
			txtArea_Titulo.setWrapStyleWord(true);
			txtArea_Titulo.setLineWrap(true);
			txtArea_Titulo.setFocusable(false);
			txtArea_Titulo.setEditable(false);
			GridBagConstraints gbc_textArea_Titulo = new GridBagConstraints();
			gbc_textArea_Titulo.fill = GridBagConstraints.HORIZONTAL;
			gbc_textArea_Titulo.gridwidth = 13;
			gbc_textArea_Titulo.insets = new Insets(0, 0, 5, 0);
			gbc_textArea_Titulo.gridx = 2;
			gbc_textArea_Titulo.gridy = 1;
			pnl_detalles.add(txtArea_Titulo, gbc_textArea_Titulo);
			
			//		Label Autor
			lblAutor = new JLabel("Autor: ");
			lblAutor.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblAutor = new GridBagConstraints();
			gbc_lblAutor.anchor = GridBagConstraints.WEST;
			gbc_lblAutor.insets = new Insets(0, 10, 5, 5);
			gbc_lblAutor.gridx = 1;
			gbc_lblAutor.gridy = 2;
			pnl_detalles.add(lblAutor, gbc_lblAutor);
			
			lbl_Autor = new JLabel("...");
			lbl_Autor.setFont(new Font("Dialog", Font.PLAIN, 12));
			GridBagConstraints gbc_lbl_Autor = new GridBagConstraints();
			gbc_lbl_Autor.gridwidth = 3;
			gbc_lbl_Autor.anchor = GridBagConstraints.WEST;
			gbc_lbl_Autor.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_Autor.gridx = 2;
			gbc_lbl_Autor.gridy = 2;
			pnl_detalles.add(lbl_Autor, gbc_lbl_Autor);
			
			//		Label Tutor
			lblTutor = new JLabel("Tutor: ");
			lblTutor.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblTutor = new GridBagConstraints();
			gbc_lblTutor.anchor = GridBagConstraints.WEST;
			gbc_lblTutor.insets = new Insets(0, 0, 5, 5);
			gbc_lblTutor.gridx = 5;
			gbc_lblTutor.gridy = 2;
			pnl_detalles.add(lblTutor, gbc_lblTutor);
			
			lbl_tutor = new JLabel("...");
			lbl_tutor.setFont(new Font("Dialog", Font.PLAIN, 12));
			GridBagConstraints gbc_lbl_tutor = new GridBagConstraints();
			gbc_lbl_tutor.gridwidth = 2;
			gbc_lbl_tutor.anchor = GridBagConstraints.WEST;
			gbc_lbl_tutor.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_tutor.gridx = 6;
			gbc_lbl_tutor.gridy = 2;
			pnl_detalles.add(lbl_tutor, gbc_lbl_tutor);
			
			//		Label Año
			lblAo = new JLabel("Año: ");
			lblAo.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblAo = new GridBagConstraints();
			gbc_lblAo.anchor = GridBagConstraints.WEST;
			gbc_lblAo.insets = new Insets(0, 0, 5, 5);
			gbc_lblAo.gridx = 9;
			gbc_lblAo.gridy = 2;
			pnl_detalles.add(lblAo, gbc_lblAo);
			
			lbl_año = new JLabel("...");
			lbl_año.setFont(new Font("Dialog", Font.PLAIN, 12));
			GridBagConstraints gbc_lbl_año = new GridBagConstraints();
			gbc_lbl_año.gridwidth = 3;
			gbc_lbl_año.anchor = GridBagConstraints.WEST;
			gbc_lbl_año.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_año.gridx = 10;
			gbc_lbl_año.gridy = 2;
			pnl_detalles.add(lbl_año, gbc_lbl_año);
			
			//		Label Carrera
			lblCarrera = new JLabel("Carrera: ");
			lblCarrera.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblCarrera = new GridBagConstraints();
			gbc_lblCarrera.anchor = GridBagConstraints.WEST;
			gbc_lblCarrera.insets = new Insets(0, 10, 5, 5);
			gbc_lblCarrera.gridx = 1;
			gbc_lblCarrera.gridy = 3;
			pnl_detalles.add(lblCarrera, gbc_lblCarrera);
			
			lbl_CarreraPD = new JLabel("...");
			lbl_CarreraPD.setFont(new Font("Dialog", Font.PLAIN, 12));
			GridBagConstraints gbc_lbl_CarreraPD = new GridBagConstraints();
			gbc_lbl_CarreraPD.gridwidth = 3;
			gbc_lbl_CarreraPD.anchor = GridBagConstraints.WEST;
			gbc_lbl_CarreraPD.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_CarreraPD.gridx = 2;
			gbc_lbl_CarreraPD.gridy = 3;
			pnl_detalles.add(lbl_CarreraPD, gbc_lbl_CarreraPD);
			
			//		Label Linea
			lblLinea = new JLabel("Linea: ");
			lblLinea.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblLinea = new GridBagConstraints();
			gbc_lblLinea.anchor = GridBagConstraints.WEST;
			gbc_lblLinea.insets = new Insets(0, 0, 5, 5);
			gbc_lblLinea.gridx = 5;
			gbc_lblLinea.gridy = 3;
			pnl_detalles.add(lblLinea, gbc_lblLinea);
			
			lbl_lineaPD = new JLabel("...");
			lbl_lineaPD.setFont(new Font("Dialog", Font.PLAIN, 12));
			GridBagConstraints gbc_lbl_lineaPD = new GridBagConstraints();
			gbc_lbl_lineaPD.gridwidth = 2;
			gbc_lbl_lineaPD.anchor = GridBagConstraints.WEST;
			gbc_lbl_lineaPD.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_lineaPD.gridx = 6;
			gbc_lbl_lineaPD.gridy = 3;
			pnl_detalles.add(lbl_lineaPD, gbc_lbl_lineaPD);
			
			//		Label Número
			lblNro = new JLabel("Nro:");
			lblNro.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblNro = new GridBagConstraints();
			gbc_lblNro.anchor = GridBagConstraints.WEST;
			gbc_lblNro.insets = new Insets(0, 0, 5, 5);
			gbc_lblNro.gridx = 9;
			gbc_lblNro.gridy = 3;
			pnl_detalles.add(lblNro, gbc_lblNro);
			
			lbl_Nro_E = new JLabel("...");
			GridBagConstraints gbc_lbl_Nro_E = new GridBagConstraints();
			gbc_lbl_Nro_E.gridwidth = 3;
			gbc_lbl_Nro_E.insets = new Insets(0, 0, 5, 5);
			gbc_lbl_Nro_E.anchor = GridBagConstraints.WEST;
			gbc_lbl_Nro_E.gridx = 10;
			gbc_lbl_Nro_E.gridy = 3;
			pnl_detalles.add(lbl_Nro_E, gbc_lbl_Nro_E);
			panel_base.add(panelIzquierda,BorderLayout.WEST);
			
			//		Se añade la tabla al scroll
			scroll = new JScrollPane(tabla);
			scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setBackground(Color.CYAN);
			scroll.setOpaque(false);
			
			//		Se añade el scroll y el panel detalles al panel
			panelDerecha = new JPanel();
			panelDerecha.setOpaque(false);
			panelDerecha.setLayout(new GridLayout(0,1));
			panelDerecha.add(scroll);
			panelDerecha.add(pnl_detalles);

			//Se agregan los elementos a la ventana
			panel_base.add(barraHerramientas,BorderLayout.NORTH);
			panel_base.add(panelDerecha,BorderLayout.CENTER);


			imagen_fondo.setBounds(0, 0, 1200, 700);

			c.add(panel_base);
			c.add(imagen_fondo);

		}//Fin del método
}//Fin de la clase
