package registro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class Ventana extends JFrame implements ActionListener{

	private final String[] titulos = {"Título","Autor","Tutor"};

	private JMenuBar barraMenu;
	private JMenu menu;
	private JMenuItem accion;
	private JButton btn_buscar,btn_login,btn_salir;
	private JScrollPane scroll;
	private JComboBox carreras,linea;
	private JLabel lbl_carrera,lbl_linea;
	private JToolBar barraHerramientas;
	private JPanel panelIzquierda,panelDerecha;
	private String strn_carreraS,strn_lineaS;
	private GestorDeDatos gdd = new GestorDeDatos();
	private DefaultTableModel dtm = new DefaultTableModel(){
		public boolean isCellEditable(int fila, int columna){
			return false;
		}};
		
		
		
		private JTable tabla = new JTable(dtm);

		public Ventana() {


			super("Registro");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(850,500);
			setLocationRelativeTo(null);
			agregarEl();
			setVisible(true);

		}

		private void agregarEl() {

			dtm.setColumnIdentifiers(titulos);
			tabla.setRowHeight(25);

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

			//procedemos con los elementos
			lbl_carrera = new JLabel("Carrera: ");
			lbl_linea = new JLabel("Linea: ");

			carreras = new JComboBox(carrerasBox);
			linea = new JComboBox(lineaBox);
			
			btn_buscar = new JButton(new ImageIcon("Iconos/searchm.png"));
			btn_buscar.setToolTipText("Buscar");
			
			btn_login = new JButton(new ImageIcon("Iconos/engineerm.png"));
			btn_login.setToolTipText("Ingresar como Administrador");

			btn_salir = new JButton(new ImageIcon("Iconos/exit-1.png"));
			btn_salir.setToolTipText("Salir");

			
			panelIzquierda = new JPanel(new GridBagLayout());
			panelIzquierda.setBorder(new TitledBorder(new EtchedBorder(), "Filtro de Datos"));		
			GridBagConstraints gbc = new GridBagConstraints();

			//Restricciones de GridBagLayout

			//Label de carreras
			gbc.gridx = 0;                         //columna en que se ubicará
			gbc.gridy = 0;						   //fila en que se ubicará	
			gbc.gridwidth = 1;					   //Columnas que ocupará
			gbc.gridheight= 1;					   //fila que ocupará
			gbc.weightx = 1.0;					   //lo que crecerá en ancho
			gbc.weighty = 0.0;					   //lo que crecerá en alto
			gbc.fill = GridBagConstraints.HORIZONTAL;    //como se expanderá (horizontal, vertical, no lo hará)
			gbc.anchor = GridBagConstraints.WEST;  //Determina la posición del elemento en su espacio
			gbc.insets = new Insets(30, 0, 0, 0);  //determina el espacion entre los elementos
			panelIzquierda.add(lbl_carrera,gbc);   //se agrega al panel el elemento junto con el gbc

			//ComboBox de carreras
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.gridwidth = 2;
			gbc.gridheight= 1;
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(30, 0, 0, 0);
			panelIzquierda.add(carreras,gbc);

			//Label de linea
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.gridheight= 1;
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(30, 0, 0, 0);
			panelIzquierda.add(lbl_linea,gbc);

			//ComboBox de linea
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.gridwidth = 2;
			gbc.gridheight= 1;
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(30, 0, 0, 0);

			panelIzquierda.add(linea,gbc);

			//Boton Buscar		
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 3;
			gbc.gridheight= 1;
			gbc.weightx = 0.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.NONE;
			gbc.insets = new Insets(50, 0, 0, 0);
			gbc.anchor = GridBagConstraints.CENTER;
			btn_buscar.setMargin(new Insets(1, 1, 1, 1));
			btn_buscar.setContentAreaFilled(false);
			btn_buscar.setForeground(Color.WHITE);
			btn_buscar.setFocusPainted(false);
			btn_buscar.setBorderPainted(false);
			panelIzquierda.add(btn_buscar,gbc);

			//Segundo panel
			scroll = new JScrollPane(tabla);
			//scroll.setBounds(0, 0, 600, 400);

			panelDerecha = new JPanel();
			panelDerecha.setLayout(new GridLayout());
			panelDerecha.add(scroll);

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

			btn_buscar.addActionListener(this);
			btn_login.addActionListener(this);



		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource()==btn_buscar) {
				
				strn_carreraS = ((String)carreras.getSelectedItem());
				strn_lineaS = ((String)linea.getSelectedItem());
				
				try {
					dtm.setRowCount(0);
					dtm.setColumnCount(0);
					dtm.setColumnIdentifiers(titulos);
					
					ResultSet aux = gdd.getSt().executeQuery("SELECT Titulo, Autor, Tutor FROM TESIS WHERE" +
															 " Carrera = '"+strn_carreraS+"' AND " +
															 "Linea = '"+strn_lineaS+"'");
					while(aux.next()){	
					Object[] fila = {aux.getObject(1), aux.getObject(2), aux.getObject(3)};
					dtm.addRow(fila);
					
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			} 
			
		}

}
