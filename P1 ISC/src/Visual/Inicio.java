package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JRadioButton;

public class Inicio extends JFrame {

	private JPanel PanelPrincipal;
	private JPanel PanelBotones;
	private JButton BTNiniciarSesion;
	private JButton BTNUser;
	private Dimension Tam;
	private JPanel PanelLogin;
	private JPanel PanelUser;
	private JPanel PanelEmpresa;
	private JTextField textUsuario;
	private JPasswordField passwordContra;
	private JPanel panel_1;
	private JLabel labelNombre;
	private JTextField textNombre;
	private JLabel labelCodigo;
	private JTextField textCodigo;
	private JLabel label_4;
	private JTextField txtUsuario;
	private JLabel labelContra;
	private JPasswordField passwordContrase�a;
	private JLabel labelConfContra;
	private JPasswordField passwordConfContra;
	private JPanel panel_2;
	private JRadioButton radioButtonUni;
	private JRadioButton radioTec;
	private JRadioButton radioObrero;
	private JPanel panel_3;
	private JRadioButton rdbtnEmpleado;
	private JRadioButton rdbtnDesempleado;

 public Inicio() {
 	addWindowListener(new WindowAdapter() {
 		@Override
 		public void windowOpened(WindowEvent e) {
 			PanelEmpresa.setVisible(false);
 			PanelLogin.setVisible(false);
 			PanelUser.setVisible(false);
 		}
 	});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PanelPrincipal = new JPanel();
		PanelPrincipal.setBackground(Color.WHITE);
		PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelPrincipal);
		PanelPrincipal.setLayout(null);
		
		BTNiniciarSesion = new JButton("Iniciar Sesi\u00F3n \u25BA");
		BTNiniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelEmpresa.setVisible(false);
	 			PanelLogin.setVisible(true);
	 			PanelUser.setVisible(false);
			}
		});
		BTNiniciarSesion.setBounds(-6, 46, 156, 58);
		BTNiniciarSesion.setHorizontalAlignment(SwingConstants.RIGHT);
		
		BTNUser = new JButton("Usuario \u25BA");
		BTNUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelEmpresa.setVisible(false);
	 			PanelLogin.setVisible(false);
	 			PanelUser.setVisible(true);
			}
		});
		BTNUser.setBounds(0, 199, 156, 58);
		BTNUser.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton BTNEmpresa = new JButton("Empresa \u25BA");
		BTNEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelEmpresa.setVisible(true);
	 			PanelLogin.setVisible(false);
	 			PanelUser.setVisible(false);
			}
		});
		BTNEmpresa.setBounds(0, 287, 156, 58);
		BTNEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblNuevo = new JLabel("Nuevo");
		lblNuevo.setBounds(0, 128, 150, 49);
		lblNuevo.setBackground(Color.BLUE);
		lblNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		Tam = this.getToolkit().getScreenSize();
		PanelBotones = new JPanel();
		PanelBotones.setBounds(0, 0, 160, (int)Tam.height);
		PanelBotones.setBackground(Color.LIGHT_GRAY);
		
		this.setBounds(100, 100, (int)Tam.getWidth(),(int)Tam.height);
		this.setLocationRelativeTo(null);
		PanelPrincipal.add(PanelBotones);
		PanelBotones.setLayout(null);
		PanelBotones.add(lblNuevo);
		PanelBotones.add(BTNiniciarSesion);
		PanelBotones.add(BTNUser);
		PanelBotones.add(BTNEmpresa);
		
	    PanelLogin = new JPanel();
		PanelLogin.setBounds(170, 28, 648, 78);
		PanelPrincipal.add(PanelLogin);
		PanelLogin.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 648, 451);
		panel.setLayout(null);
		PanelLogin.add(panel);
		
		JLabel labelUsuario = new JLabel("Usuario:");
		labelUsuario.setBounds(20, 21, 147, 14);
		panel.add(labelUsuario);
		
		JLabel labelContrase�a = new JLabel("Contrase\u00F1a:");
		labelContrase�a.setBounds(274, 21, 105, 14);
		panel.add(labelContrase�a);
		
		textUsuario = new JTextField();
		textUsuario.setColumns(10);
		textUsuario.setBounds(68, 40, 191, 20);
		panel.add(textUsuario);
		
		JButton buttonLogin = new JButton("Login");
		buttonLogin.setBounds(549, 39, 89, 23);
		panel.add(buttonLogin);
		
		passwordContra = new JPasswordField();
		passwordContra.setBounds(348, 40, 191, 20);
		panel.add(passwordContra);
		
	
		
	    PanelUser = new JPanel();
		PanelUser.setBounds(170, 107, 648, 372);
		PanelPrincipal.add(PanelUser);
		PanelUser.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos Usuario", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(82, 11, 466, 193);
		PanelUser.add(panel_1);
		
		labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(10, 67, 97, 14);
		panel_1.add(labelNombre);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(10, 92, 209, 20);
		panel_1.add(textNombre);
		
		labelCodigo = new JLabel("Codigo:");
		labelCodigo.setBounds(10, 11, 46, 14);
		panel_1.add(labelCodigo);
		
		textCodigo = new JTextField();
		textCodigo.setText("1");
		textCodigo.setEditable(false);
		textCodigo.setColumns(10);
		textCodigo.setBounds(10, 36, 209, 20);
		panel_1.add(textCodigo);
		
		label_4 = new JLabel("Usuario:");
		label_4.setBounds(10, 123, 83, 14);
		panel_1.add(label_4);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(10, 148, 209, 20);
		panel_1.add(txtUsuario);
		
		labelContra = new JLabel("Contrase\u00F1a:");
		labelContra.setBounds(256, 67, 125, 14);
		panel_1.add(labelContra);
		
		passwordContrase�a = new JPasswordField();
		passwordContrase�a.setBounds(255, 92, 198, 20);
		panel_1.add(passwordContrase�a);
		
		labelConfContra = new JLabel("Confirmar Contrase\u00F1a:");
		labelConfContra.setBounds(256, 123, 186, 14);
		panel_1.add(labelConfContra);
		
		passwordConfContra = new JPasswordField();
		passwordConfContra.setBounds(255, 148, 198, 20);
		panel_1.add(passwordConfContra);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(82, 261, 466, 47);
		PanelUser.add(panel_2);
		
		radioButtonUni = new JRadioButton("Universitario");
		radioButtonUni.setSelected(true);
		radioButtonUni.setBounds(6, 17, 109, 23);
		panel_2.add(radioButtonUni);
		
		radioTec = new JRadioButton("Tecnico");
		radioTec.setSelected(false);
		radioTec.setBounds(171, 17, 109, 23);
		panel_2.add(radioTec);
		
		radioObrero = new JRadioButton("Obrero");
		radioObrero.setSelected(false);
		radioObrero.setBounds(333, 17, 109, 23);
		panel_2.add(radioObrero);
		
		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Estado", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(82, 203, 466, 47);
		PanelUser.add(panel_3);
		
		rdbtnEmpleado = new JRadioButton("Empleado");
		rdbtnEmpleado.setSelected(true);
		rdbtnEmpleado.setBounds(34, 17, 109, 23);
		panel_3.add(rdbtnEmpleado);
		
		rdbtnDesempleado = new JRadioButton("Desempleado");
		rdbtnDesempleado.setSelected(false);
		rdbtnDesempleado.setBounds(333, 17, 109, 23);
		panel_3.add(rdbtnDesempleado);
		PanelEmpresa = new JPanel();
		PanelEmpresa.setBounds(170, 190, 648, 379);
		PanelPrincipal.add(PanelEmpresa);
		
		
	}
}
