package gestion_de_stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import gestion_de_stock.connexion_data;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class log_in extends JFrame {

	private JPanel contentPane;
	static JTextField case_user;
	private JPasswordField case_passwd;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					log_in frame = new log_in();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public log_in() {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 20, 902, 718);
	contentPane = new JPanel();
	contentPane.setBackground(Color.LIGHT_GRAY);
	contentPane.setBorder(null);
	setContentPane(contentPane);
	contentPane.setLayout(null);
	

	//boutton pour le login 
	JButton btn_log = new JButton("Connexion");
	btn_log.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			Connection b=connexion_data.connecterDB();
			if(b != null) {
			if((case_user.getText().equals("Nom utilisateur") && case_passwd.getText().isEmpty() || case_user.getText().isEmpty()) && case_passwd.getText().isEmpty() ) {
				
				//pour la case user
				case_user.setFont(new Font("Tahoma", Font.BOLD, 20));
				case_user.setForeground(Color.BLACK);
				case_user.setHorizontalAlignment(SwingConstants.CENTER);
				case_user.setText("Nom utilisateur");
				case_user.setBackground(Color.RED);
				
				// pour la case passwd
				
				case_passwd.setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "veuillez remplir le Nom d'utilisateur et le mot de passe ","ERREUR",JOptionPane.ERROR_MESSAGE);
				
			
			}else if(case_user.getText().equals("Nom utilisateur") || case_user.getText().isEmpty()) {
				
				case_user.setFont(new Font("Tahoma", Font.BOLD, 20));
				case_user.setForeground(Color.BLACK);
				case_user.setHorizontalAlignment(SwingConstants.CENTER);
				case_user.setText("Nom utilisateur");
				case_user.setBackground(Color.RED);
				
				JOptionPane.showMessageDialog(null, "veuillez remplir le Nom d'utilisateur","ERREUR",JOptionPane.ERROR_MESSAGE);
			
			}else if (case_passwd.getText().isEmpty()) {
				
				case_passwd.setBackground(Color.RED);
				JOptionPane.showMessageDialog(null, "veuillez remplir le mot de passe","ERREUR",JOptionPane.ERROR_MESSAGE);
					
			}else if (!(case_passwd.getText().isEmpty()) && !(case_user.getText().equals("Nom utilisateur")) ) {
				
				
				try {
					String sql ="SELECT * from log_in where User='"+case_user.getText()+"' and EXISTS (SELECT Password from log_in where Password='"+case_passwd.getText()+"')";
			
					Statement st =b.createStatement();
					ResultSet rs_1=st.executeQuery(sql);
					
					table.setModel(DbUtils.resultSetToTableModel(rs_1));
				
					int reff=table.getModel().getRowCount();
						
					if(reff !=1) {
						String[] btn_options = {"ok"};
					    JOptionPane.showOptionDialog(null,"Nom d'utilisateur ou le mot de passe incorrect ","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);
					    
					
					}else {
						
						Acceuil home=new Acceuil();
						home.setVisible(true);
						log_in.this.dispose();
						}
					

				}catch(Exception e1) {
					e1.printStackTrace();
					
				}
				
			}
		}
	}
});
	
	//la phrase pour changer le mot de passe 
	JLabel label_change_passwd = new JLabel("changer le mot de passe ");
	label_change_passwd.setHorizontalAlignment(SwingConstants.CENTER);
	label_change_passwd.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			Connection b = connexion_data.connecterDB();
			if(b!= null) {
			if(case_user.getText().isEmpty() || case_user.getText().equals("Nom utilisateur")) {
				
				JOptionPane.showMessageDialog(null, "veuillez remplir le nom d'utilisateur SVP","Information",JOptionPane.INFORMATION_MESSAGE);
				
			}else {
					
				try {
				
					String sql="SELECT * from log_in where User='"+case_user.getText()+"'";
					
				Statement st = b.createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				table.setModel(DbUtils.resultSetToTableModel(rs));
				int reff=table.getModel().getRowCount();
				
				if(reff == 1) {
					change_psswd mdp=new change_psswd();
					mdp.setVisible(true);
					log_in.this.dispose();
				
				}else {
					
					String[] btn_options = {"ok"};
				    JOptionPane.showOptionDialog(null,"nom d'utilisateur n'existe pas","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);
				}
				
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "nom d'utilisateur n'existe pas");
				}
			}
		}
	}
});
	
	//la phrase pour changer le nom utilisateur
	JLabel label_change_passwd_1 = new JLabel("changer le nom utilisateur");
	label_change_passwd_1.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			Connection b = connexion_data.connecterDB();
			if(b != null) {
			if(case_user.getText().isEmpty() || case_user.getText().equals("Nom utilisateur")) {
				
				JOptionPane.showMessageDialog(null, "veuillez remplir le nom d'utilisateur SVP","Information",JOptionPane.INFORMATION_MESSAGE);
				
			}else {
				try {
				
					String sql="SELECT * from log_in where User='"+case_user.getText()+"'";
					
				Statement st = b.createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				table.setModel(DbUtils.resultSetToTableModel(rs));
				int reff=table.getModel().getRowCount();
				
				if(reff == 1) {
					change_user user=new change_user();
					user.setVisible(true);
					log_in.this.dispose();
				
				}else {
					
					String[] btn_options = {"ok"};
				    JOptionPane.showOptionDialog(null,"nom d'utilisateur n'existe pas","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);
				}
				
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
});
	
	JLabel icon_passwd = new JLabel("");
	icon_passwd.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_show_i.jpg"));
	icon_passwd.setBounds(784, 451, 35, 48);
	contentPane.add(icon_passwd);
	
	
	
	
	//icon de afficher le mot de passe
	icon_passwd.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(case_passwd.echoCharIsSet()) {
				case_passwd.setEchoChar((char)0);
				icon_passwd.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_hidee_i.jpg"));
			
			}else {
				case_passwd.setEchoChar('●');
				icon_passwd.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_show_i.jpg"));
			}
		}
	});
	label_change_passwd_1.setHorizontalAlignment(SwingConstants.CENTER);
	label_change_passwd_1.setForeground(Color.BLUE);
	label_change_passwd_1.setBounds(627, 533, 172, 14);
	contentPane.add(label_change_passwd_1);
	label_change_passwd.setForeground(Color.BLUE);
	label_change_passwd.setBounds(627, 511, 172, 14);
	contentPane.add(label_change_passwd);
	
	table = new JTable();
	table.setBounds(572, 43, 1, 1);
	contentPane.add(table);
	
	JLabel lblNewLabel_1 = new JLabel("New label");
	lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_pssd_22.jpg"));
	lblNewLabel_1.setBounds(575, 407, 45, 48);
	contentPane.add(lblNewLabel_1);
	
	case_passwd = new JPasswordField();
	case_passwd.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			case_passwd.setBackground(Color.LIGHT_GRAY);
			
			if(case_user.getText().isEmpty()) {
				
				case_user.setFont(new Font("Tahoma", Font.BOLD, 20));
				case_user.setForeground(Color.GRAY);
				case_user.setHorizontalAlignment(SwingConstants.CENTER);
				case_user.setText("Nom utilisateur");
				case_user.setBackground(Color.LIGHT_GRAY);
				
			}
		}
	});
	case_passwd.setToolTipText("");
	case_passwd.setFont(new Font("Tahoma", Font.BOLD, 20));
	case_passwd.setBackground(Color.LIGHT_GRAY);
	case_passwd.setBounds(627, 401, 172, 53);
	contentPane.add(case_passwd);
	btn_log.setFont(new Font("Tahoma", Font.BOLD, 23));
	btn_log.setBounds(595, 558, 241, 53);
	contentPane.add(btn_log);
	
	JLabel lblNewLabel_2 = new JLabel("New label");
	lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\user_icon_2.png"));
	lblNewLabel_2.setBounds(655, 209, 95, 72);
	contentPane.add(lblNewLabel_2);
	
	JLabel lblNewLabel = new JLabel("New label");
	lblNewLabel.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\RS_login.PNG"));
	lblNewLabel.setBounds(0, 0, 492, 749);
	contentPane.add(lblNewLabel);
	
	case_user = new JTextField();
	case_user.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(case_user.getText().equals("Nom utilisateur")) {
				
				case_user.setText("");
				case_user.setForeground(Color.BLACK);
				case_user.setFont(new Font("Tahoma", Font.BOLD, 20));
				case_user.setHorizontalAlignment(SwingConstants.CENTER);
				case_user.setBackground(Color.LIGHT_GRAY);
			
			}
			
			
		}
	});
	case_user.setFont(new Font("Tahoma", Font.BOLD, 20));
	case_user.setForeground(Color.GRAY);
	case_user.setHorizontalAlignment(SwingConstants.CENTER);
	case_user.setText("Nom utilisateur");
	case_user.setBackground(Color.LIGHT_GRAY);
	case_user.setBounds(627, 311, 172, 53);
	contentPane.add(case_user);
	case_user.setColumns(10);
	
	//sur l'interface blanc qui contient les cases users et mot de passe 
	JLabel back_log = new JLabel("New label");
	back_log.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(case_user.getText().equals("")) {
				case_user.setText("Nom utilisateur");
				case_user.setForeground(Color.GRAY);
				case_user.setHorizontalAlignment(SwingConstants.CENTER);
				
			}
		}
	});
	back_log.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\Background_white.jpg"));
	back_log.setBounds(530, 198, 346, 446);
	contentPane.add(back_log);
	
	}
}
