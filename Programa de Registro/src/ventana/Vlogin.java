package ventana;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Vlogin extends JDialog{

	public JButton btn_aceptar, btn_cancelar;
	public JTextField tfl_usuario;
	public JPasswordField pwd_contraseña;
	public JLabel lbl_mensaje, imagen_fondo;
	private JLabel lbl_Usuario, lbl_Contraseña, logo;
	private JPanel panel1,panel2,panel3,panel4;
	public JPanel pnl_base;

	public Vlogin(Ventana padre, String nombre, ImageIcon imagen_fondo) {
		super(padre,nombre);
		setModal(true);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(Vlogin.DISPOSE_ON_CLOSE);
		this.imagen_fondo = new JLabel(imagen_fondo);
		setResizable(false);
		agregarElementos();

	}

	public void agregarElementos(){
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		pnl_base = new JPanel(new BorderLayout());
		pnl_base.setOpaque(false);
		pnl_base.setBounds(0, 0, 400, 250);


		//Panel 1
		panel1 = new JPanel();
		panel1.setOpaque(false);
		panel1.setLayout(null);
		
			//Label Usuario
		lbl_Usuario = new JLabel("Usuario:");
		lbl_Usuario.setBounds(6, 50, 67, 21);
		panel1.add(lbl_Usuario);
		
			//TextField Usuario
		tfl_usuario = new JTextField(20);
		tfl_usuario.setBounds(102, 47, 142, 27);
		panel1.add(tfl_usuario);

			//Label Contraseña
		lbl_Contraseña = new JLabel("Contraseña:");
		lbl_Contraseña.setBounds(0, 112, 90, 21);
		panel1.add(lbl_Contraseña);
			
			//Pasword
		pwd_contraseña = new JPasswordField(20);
		pwd_contraseña.setBounds(102, 109, 142, 27);
		panel1.add(pwd_contraseña);

		//Panel 2
		panel2 = new JPanel();
		panel2.setOpaque(false);
		
		//		Boton Aceptar
		btn_aceptar = new JButton("Aceptar");
		panel2.add(btn_aceptar);
		
		//		Boton Cancelar
		btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setOpaque(true);
		panel2.add(btn_cancelar);

		//Panel 3
		panel3 = new JPanel();
		panel3.setOpaque(false);
		
			//Label mensaje
		lbl_mensaje = new JLabel("");
		panel3.add(lbl_mensaje);

		//Panel 4
		panel4 = new JPanel();
		panel4.setOpaque(false);
		panel4.setLayout(new GridLayout(0, 1, 0, 0));
		
			//Label Logo
		logo = new JLabel(new ImageIcon(Ventana.class.getResource("/Imagenes/logo_login.png")));
		panel4.add(logo);

		//Agregando paneles al Contenedor
		pnl_base.add(panel1, BorderLayout.CENTER);
		pnl_base.add(panel2, BorderLayout.SOUTH);
		pnl_base.add(panel3, BorderLayout.NORTH);		
		pnl_base.add(panel4, BorderLayout.WEST);

		imagen_fondo.setBounds(0, 0, 400, 300);

		c.add(pnl_base);
		c.add(imagen_fondo);
		
	}
}
