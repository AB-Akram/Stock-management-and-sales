package gestion_de_stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.xdevapi.Statement;

import gestion_de_stock.connexion_data;
import gestion_de_stock.log_in;
import net.proteanit.sql.DbUtils;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class change_psswd extends JFrame {

	private JPanel contentPane;
	private JPasswordField mdp_ancien;
	private JPasswordField mdp_new;
	private JPasswordField mdp_confirm;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					change_psswd frame = new change_psswd();
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
	public change_psswd() {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 20, 902, 758);
	contentPane = new JPanel();
	contentPane.setBackground(Color.RED);
	contentPane.setBorder(null);
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JButton btn_retour = new JButton("BACK");
	btn_retour.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			log_in log=new log_in();
			log.setVisible(true);
			change_psswd.this.dispose();
		}
	});
	;
	
	//icon pour le confirm password
	JLabel icon_passwd_4 = new JLabel("New label");
	icon_passwd_4.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_show_i.jpg"));
	icon_passwd_4.setBounds(790, 547, 35, 48);
	contentPane.add(icon_passwd_4);
	
	//icon pour le nouveau password
	JLabel icon_passwd_3 = new JLabel("New label");
	icon_passwd_3.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_show_i.jpg"));
	icon_passwd_3.setBounds(790, 396, 35, 48);
	contentPane.add(icon_passwd_3);
	
	//icon pour le ancien password
	JLabel icon_passwd_2 = new JLabel("New label");
	icon_passwd_2.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_show_i.jpg"));
	icon_passwd_2.setBounds(790, 250, 35, 48);
	contentPane.add(icon_passwd_2);
	
	JLabel lblConfirmerVotreNouveau = new JLabel("confirmer votre nouveau mot de passe ");
	lblConfirmerVotreNouveau.setHorizontalAlignment(SwingConstants.CENTER);
	lblConfirmerVotreNouveau.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblConfirmerVotreNouveau.setBounds(598, 455, 255, 26);
	contentPane.add(lblConfirmerVotreNouveau);
	
	JLabel lblTapezVotreNouveau = new JLabel("tapez votre nouveau mot de passe");
	lblTapezVotreNouveau.setHorizontalAlignment(SwingConstants.CENTER);
	lblTapezVotreNouveau.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblTapezVotreNouveau.setBounds(598, 309, 227, 26);
	contentPane.add(lblTapezVotreNouveau);
	
	JLabel lblTapezVotreAncien = new JLabel("tapez votre ancien mot de passe ");
	lblTapezVotreAncien.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblTapezVotreAncien.setHorizontalAlignment(SwingConstants.CENTER);
	lblTapezVotreAncien.setBounds(598, 161, 210, 26);
	contentPane.add(lblTapezVotreAncien);
	
	mdp_confirm = new JPasswordField();
	mdp_confirm.setToolTipText("");
	mdp_confirm.setFont(new Font("Tahoma", Font.BOLD, 20));
	mdp_confirm.setBackground(Color.LIGHT_GRAY);
	mdp_confirm.setBounds(613, 492, 172, 53);
	contentPane.add(mdp_confirm);
	
	mdp_new = new JPasswordField();
	mdp_new.setToolTipText("");
	mdp_new.setFont(new Font("Tahoma", Font.BOLD, 20));
	mdp_new.setBackground(Color.LIGHT_GRAY);
	mdp_new.setBounds(613, 342, 172, 53);
	contentPane.add(mdp_new);
	
	mdp_ancien = new JPasswordField();
	mdp_ancien.setToolTipText("");
	mdp_ancien.setFont(new Font("Tahoma", Font.BOLD, 20));
	mdp_ancien.setBackground(Color.LIGHT_GRAY);
	mdp_ancien.setBounds(613, 198, 172, 53);
	contentPane.add(mdp_ancien);
	
	JButton btn_modif = new JButton("Modifier");
	btn_modif.setFont(new Font("Tahoma", Font.BOLD, 23));
	btn_modif.setBounds(584, 606, 241, 53);
	contentPane.add(btn_modif);
	btn_retour.setFont(new Font("Dialog", Font.BOLD, 16));
	btn_retour.setBounds(493, 0, 89, 47);
	contentPane.add(btn_retour);
	
	JLabel lblNewLabel = new JLabel("New label");
	lblNewLabel.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\RS_logo_mdp.jpg"));
	lblNewLabel.setBounds(0, 0, 492, 749);
	contentPane.add(lblNewLabel);
	
	
	JLabel back_log = new JLabel("New label");
	back_log.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\Background_white.jpg"));
	back_log.setBounds(528, 114, 348, 570);
	contentPane.add(back_log);
	
	table = new JTable();
	table.setBounds(572, 43, 1, 1);
	contentPane.add(table);
	
	
	//boutton pour la modification 
	btn_modif.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Connection b=connexion_data.connecterDB();
			
			//pour la case de tapez votre ancien mot de passe
			if(!(mdp_ancien.getText().isEmpty())) {
				try {
					String sql="SELECT Password from log_in where Password='"+mdp_ancien.getText()+"' and EXISTS(SELECT User from log_in where User='"+log_in.case_user.getText()+"')";
					
					java.sql.Statement st =b.createStatement();
					ResultSet rs_1=st.executeQuery(sql);
					
					table.setModel(DbUtils.resultSetToTableModel(rs_1));
				
					int reff=table.getModel().getRowCount();
					
					if(reff !=1) {
						String[] btn_options = {"ok"};
					    JOptionPane.showOptionDialog(null,"erreur sur le mot de passe ancien ","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);
					
						//pour la case de tapez votre nouveau mot de passe
					}else if(!(mdp_new.getText().isEmpty())) {
						
								//pour la case de tapez confirm mot de passe
								if(!(mdp_confirm.getText().isEmpty())) {
									
									if(mdp_confirm.getText().equals(mdp_new.getText()) ) {
										
										if(JOptionPane.showConfirmDialog(null, "êtes-vous sur de modifier le mot de passe ? ")==0) {
										String sql_2="Update log_in SET Password='"+mdp_confirm.getText()+"' where User ='"+log_in.case_user.getText()+"'";
										java.sql.Statement pst=b.createStatement();
										pst.executeUpdate(sql_2);
										
										ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
										JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
										
										log_in log=new log_in();
										log.setVisible(true);
										change_psswd.this.dispose();
										}
								
									}else {
										
										String[] btn_options = {"ok"};
									    JOptionPane.showOptionDialog(null,"erreur sur la confirmation du mot de passe","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);
									}
									
									
								}else {
									
									JOptionPane.showMessageDialog(null, "le champ de la confirmation du mot de passe est vide ","Attention",JOptionPane.WARNING_MESSAGE);
								}
							
						}else {
							JOptionPane.showMessageDialog(null, "le champ de nouveau mot de passe est vide  ","Attention",JOptionPane.WARNING_MESSAGE);
							
						}
					
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "le champ d'ancien mot de passe  est vide  ","Attention",JOptionPane.WARNING_MESSAGE);

			}
			
		}
	});
	
	
	//action pour l'icon show passwword pour ancien mot de passe
	icon_passwd_2.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(mdp_ancien.echoCharIsSet()) {
				mdp_ancien.setEchoChar((char)0);
				icon_passwd_2.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_hidee_i.jpg"));
			
			}else {
				mdp_ancien.setEchoChar('●');
				icon_passwd_2.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_show_i.jpg"));
			}
		}
	});
	
	
	//action pour l'icon show passwword pour nouveau mot de passe
	icon_passwd_3.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(mdp_new.echoCharIsSet()) {
				mdp_new.setEchoChar((char)0);
				icon_passwd_3.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_hidee_i.jpg"));
			
			}else {
				mdp_new.setEchoChar('●');
				icon_passwd_3.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_show_i.jpg"));
			}
		}
	});
	
	
	//action pour l'icon show passwword pour confirm mot de passe
		icon_passwd_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(mdp_confirm.echoCharIsSet()) {
					mdp_confirm.setEchoChar((char)0);
					icon_passwd_4.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_hidee_i.jpg"));
				
				}else {
					mdp_confirm.setEchoChar('●');
					icon_passwd_4.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\icon_show_i.jpg"));
				}
			}
		});
	
	
	
	
	
	
	}
}
