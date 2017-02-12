package ventana;

import java.awt.BorderLayout;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings({ "serial", "rawtypes" })
public class Ventana extends JFrame{

	public final String[] titulos = {"Título","Autor","Tutor"};

	private JMenuBar barraMenu;
	private JMenu menu;
	private JMenuItem accion;
	public JButton btn_buscar,btn_login,btn_salir;
	private JScrollPane scroll;
	public JComboBox carreras,linea;
	public JLabel lbl_carrera,lbl_linea,lbl_titulo,lbl_tutor,lbl_Autor,lbl_año,lbl_lineaPD,lbl_CarreraPD;
	private JToolBar barraHerramientas;
	private JPanel panelIzquierda,panelDerecha,pnl_detalles;
	public String strn_carreraS,strn_lineaS;
	public DefaultTableModel dtm = new DefaultTableModel(){

		public boolean isCellEditable(int fila, int columna){
			return false;
		}};
		public JTable tabla = new JTable(dtm);
		public List<Object> ids = new ArrayList<Object>();
		private JLabel lblAo, lblLinea, lblAutor, lblTutor, lblTtulo, lblCarrera;

		public Ventana() {
			setTitle("Registro");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(950,600);
			setLocationRelativeTo(null);
			agregarElementos();
			setVisible(true);
		}

		@SuppressWarnings({ "unchecked" })
		private void agregarElementos() {


			dtm.setColumnIdentifiers(titulos);
			tabla.setRowHeight(25);
			tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Hace que solo se pueda seleccionar una fila de la tabla

			String[] carrerasBox = {"Ing. Industrial",
					"Ing. Mtto. Mecánico",
					"Ing. Civil",
					"Ing. Eléctrica",
					"Ing. Electrónica",
					"Ing. en Sistemas",
			"Arquitectura"};

			String[] lineaBox = {"Gerencia",
					"Operaciones",
					"Procesos Industriales",
			"Agroindustria"};

			Container c = getContentPane();
			c.setLayout(new BorderLayout());

			//Creacion de menú
			menu = new JMenu("Archivo");
			accion = new JMenuItem("Accion");
			menu.add(accion);
			barraMenu = new JMenuBar();
			barraMenu.add(menu);
			this.setJMenuBar(barraMenu);

			btn_login = new JButton(new ImageIcon("src/Imagenes/engineerm.png"));
			btn_login.setBorderPainted(false);
			btn_login.setContentAreaFilled(false);
			btn_login.setFocusPainted(false);
			btn_login.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/engineermG.png")));
			btn_login.setToolTipText("Ingresar como Administrador");

			btn_salir = new JButton(new ImageIcon("src/Imagenes/exit-1.png"));
			btn_salir.setBorderPainted(false);
			btn_salir.setFocusable(false);
			btn_salir.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/exit-1G.png")));
			btn_salir.setContentAreaFilled(false);
			btn_salir.setToolTipText("Salir");
			panelIzquierda = new JPanel();
			panelIzquierda.setBorder(new TitledBorder(new EtchedBorder(), "Filtro de Datos"));

			//Segundo panel
			scroll = new JScrollPane(tabla);

			//Creacion de panel detalles
			pnl_detalles = new JPanel();
			pnl_detalles.setBorder(new TitledBorder(new EtchedBorder(),"Detalles"));
			GridBagLayout gbl_pnl_detalles = new GridBagLayout();
			gbl_pnl_detalles.columnWidths = new int[]{114, 78, 65, 47, 63, 48, 36, 0};
			gbl_pnl_detalles.rowHeights = new int[]{64, 0, 15, 15, 15, 0};
			gbl_pnl_detalles.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_pnl_detalles.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			pnl_detalles.setLayout(gbl_pnl_detalles);

			lblTtulo = new JLabel("Título: ");
			lblTtulo.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
			gbc_lblTtulo.anchor = GridBagConstraints.WEST;
			gbc_lblTtulo.insets = new Insets(10, 5, 5, 5);
			gbc_lblTtulo.gridx = 0;
			gbc_lblTtulo.gridy = 0;
			pnl_detalles.add(lblTtulo, gbc_lblTtulo);
			lbl_titulo = new JLabel("...");
			lbl_titulo.setFont(new Font("Dialog", Font.PLAIN, 12));
			GridBagConstraints gbc_lbl_titulo = new GridBagConstraints();
			gbc_lbl_titulo.anchor = GridBagConstraints.WEST;
			gbc_lbl_titulo.gridwidth = 6;
			gbc_lbl_titulo.insets = new Insets(5, 5, 5, 0);
			gbc_lbl_titulo.gridx = 1;
			gbc_lbl_titulo.gridy = 0;
			pnl_detalles.add(lbl_titulo, gbc_lbl_titulo);

			//panel derecho
			panelDerecha = new JPanel();
			panelDerecha.setLayout(new GridLayout(0,1));
			panelDerecha.add(scroll);
			panelDerecha.add(pnl_detalles);
			panelDerecha.add(pnl_detalles);

			lblAutor = new JLabel("Autor: ");
			lblAutor.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblAutor = new GridBagConstraints();
			gbc_lblAutor.anchor = GridBagConstraints.WEST;
			gbc_lblAutor.insets = new Insets(5, 5, 5, 5);
			gbc_lblAutor.gridx = 0;
			gbc_lblAutor.gridy = 2;
			pnl_detalles.add(lblAutor, gbc_lblAutor);
			lbl_Autor = new JLabel("...");
			lbl_Autor.setFont(new Font("Dialog", Font.PLAIN, 12));
			GridBagConstraints gbc_lbl_Autor = new GridBagConstraints();
			gbc_lbl_Autor.ipady = 5;
			gbc_lbl_Autor.anchor = GridBagConstraints.WEST;
			gbc_lbl_Autor.insets = new Insets(5, 5, 5, 5);
			gbc_lbl_Autor.gridx = 1;
			gbc_lbl_Autor.gridy = 2;
			pnl_detalles.add(lbl_Autor, gbc_lbl_Autor);

			lblTutor = new JLabel("Tutor: ");
			lblTutor.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblTutor = new GridBagConstraints();
			gbc_lblTutor.anchor = GridBagConstraints.WEST;
			gbc_lblTutor.insets = new Insets(0, 0, 5, 5);
			gbc_lblTutor.gridx = 2;
			gbc_lblTutor.gridy = 2;
			pnl_detalles.add(lblTutor, gbc_lblTutor);
			lbl_tutor = new JLabel("...");
			lbl_tutor.setFont(new Font("Dialog", Font.PLAIN, 12));
			GridBagConstraints gbc_lbl_tutor = new GridBagConstraints();
			gbc_lbl_tutor.anchor = GridBagConstraints.WEST;
			gbc_lbl_tutor.insets = new Insets(5, 5, 5, 5);
			gbc_lbl_tutor.gridx = 3;
			gbc_lbl_tutor.gridy = 2;
			pnl_detalles.add(lbl_tutor, gbc_lbl_tutor);

			lblCarrera = new JLabel("Carrera: ");
			lblCarrera.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblCarrera = new GridBagConstraints();
			gbc_lblCarrera.anchor = GridBagConstraints.WEST;
			gbc_lblCarrera.insets = new Insets(0, 0, 5, 5);
			gbc_lblCarrera.gridx = 4;
			gbc_lblCarrera.gridy = 2;
			pnl_detalles.add(lblCarrera, gbc_lblCarrera);
			lbl_CarreraPD = new JLabel("...");
			lbl_CarreraPD.setFont(new Font("Dialog", Font.PLAIN, 12));
			GridBagConstraints gbc_lbl_CarreraPD = new GridBagConstraints();
			gbc_lbl_CarreraPD.gridwidth = 2;
			gbc_lbl_CarreraPD.anchor = GridBagConstraints.WEST;
			gbc_lbl_CarreraPD.insets = new Insets(5, 5, 5, 0);
			gbc_lbl_CarreraPD.gridx = 5;
			gbc_lbl_CarreraPD.gridy = 2;
			pnl_detalles.add(lbl_CarreraPD, gbc_lbl_CarreraPD);

			lblLinea = new JLabel("Linea: ");
			lblLinea.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblLinea = new GridBagConstraints();
			gbc_lblLinea.anchor = GridBagConstraints.WEST;
			gbc_lblLinea.insets = new Insets(5, 5, 0, 5);
			gbc_lblLinea.gridx = 0;
			gbc_lblLinea.gridy = 4;
			pnl_detalles.add(lblLinea, gbc_lblLinea);
			lbl_lineaPD = new JLabel("...");
			lbl_lineaPD.setFont(new Font("Dialog", Font.PLAIN, 12));
			GridBagConstraints gbc_lbl_lineaPD = new GridBagConstraints();
			gbc_lbl_lineaPD.anchor = GridBagConstraints.WEST;
			gbc_lbl_lineaPD.insets = new Insets(5, 5, 0, 5);
			gbc_lbl_lineaPD.gridx = 1;
			gbc_lbl_lineaPD.gridy = 4;
			pnl_detalles.add(lbl_lineaPD, gbc_lbl_lineaPD);

			lblAo = new JLabel("Año: ");
			lblAo.setFont(new Font("Dialog", Font.BOLD, 14));
			GridBagConstraints gbc_lblAo = new GridBagConstraints();
			gbc_lblAo.anchor = GridBagConstraints.WEST;
			gbc_lblAo.insets = new Insets(5, 5, 0, 5);
			gbc_lblAo.gridx = 2;
			gbc_lblAo.gridy = 4;
			pnl_detalles.add(lblAo, gbc_lblAo);
			lbl_año = new JLabel("...");
			lbl_año.setFont(new Font("Dialog", Font.PLAIN, 12));
			GridBagConstraints gbc_lbl_año = new GridBagConstraints();
			gbc_lbl_año.anchor = GridBagConstraints.WEST;
			gbc_lbl_año.insets = new Insets(5, 5, 0, 5);
			gbc_lbl_año.gridx = 3;
			gbc_lbl_año.gridy = 4;
			pnl_detalles.add(lbl_año, gbc_lbl_año);

			//se crea la barra de herramientas

			barraHerramientas = new JToolBar();
			btn_login.setMargin(new Insets(5, 5, 5, 5));
			barraHerramientas.add(btn_login);
			barraHerramientas.addSeparator();
			btn_salir.setMargin(new Insets(5, 5, 5, 5));
			barraHerramientas.add(btn_salir);
			barraHerramientas.setEnabled(false);

			c.add(barraHerramientas,BorderLayout.NORTH);
			c.add(panelDerecha,BorderLayout.CENTER);
			c.add(panelIzquierda,BorderLayout.WEST);
			GridBagLayout gbl_panelIzquierda = new GridBagLayout();
			gbl_panelIzquierda.rowHeights = new int[]{37, 24, 24, 176, 80, 0};
			gbl_panelIzquierda.columnWeights = new double[]{0.0, 0.0};
			panelIzquierda.setLayout(gbl_panelIzquierda);


			//Restricciones de GridBagLayout

			//Label de carreras
			lbl_carrera = new JLabel("Carrera: ");
			lbl_carrera.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_carrera = new GridBagConstraints();
			gbc_lbl_carrera.anchor = GridBagConstraints.EAST;
			gbc_lbl_carrera.insets = new Insets(5, 5, 5, 5);
			gbc_lbl_carrera.gridx = 0;
			gbc_lbl_carrera.gridy = 0;
			panelIzquierda.add(lbl_carrera, gbc_lbl_carrera);


			//ComboBox de carreras
			carreras = new JComboBox(carrerasBox);
			carreras.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_carreras = new GridBagConstraints();
			gbc_carreras.fill = GridBagConstraints.HORIZONTAL;
			gbc_carreras.insets = new Insets(5, 5, 5, 5);
			gbc_carreras.gridx = 1;
			gbc_carreras.gridy = 0;
			panelIzquierda.add(carreras, gbc_carreras);

			//Label de linea
			lbl_linea = new JLabel("Linea: ");
			lbl_linea.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_lbl_linea = new GridBagConstraints();
			gbc_lbl_linea.anchor = GridBagConstraints.WEST;
			gbc_lbl_linea.insets = new Insets(5, 5, 5, 5);
			gbc_lbl_linea.gridx = 0;
			gbc_lbl_linea.gridy = 1;
			panelIzquierda.add(lbl_linea, gbc_lbl_linea);

			//ComboBox de linea
			linea = new JComboBox(lineaBox);
			linea.setFont(new Font("Dialog", Font.BOLD, 12));
			GridBagConstraints gbc_linea = new GridBagConstraints();
			gbc_linea.fill = GridBagConstraints.HORIZONTAL;
			gbc_linea.insets = new Insets(5, 5, 5, 5);
			gbc_linea.gridx = 1;
			gbc_linea.gridy = 1;
			panelIzquierda.add(linea, gbc_linea);

			btn_buscar = new JButton(new ImageIcon("src/Imagenes/searchm.png"));
			btn_buscar.setRolloverIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/searchmG.png")));
			btn_buscar.setContentAreaFilled(false);
			btn_buscar.setToolTipText("Buscar");
			btn_buscar.setMargin(new Insets(5, 5, 5, 5));
			btn_buscar.setFocusPainted(false);
			btn_buscar.setBorderPainted(false);
			GridBagConstraints gbc_btn_buscar = new GridBagConstraints();
			gbc_btn_buscar.gridwidth = 2;
			gbc_btn_buscar.insets = new Insets(5, 5, 5, 5);
			gbc_btn_buscar.gridx = 0;
			gbc_btn_buscar.gridy = 3;
			panelIzquierda.add(btn_buscar, gbc_btn_buscar);


		}


}
