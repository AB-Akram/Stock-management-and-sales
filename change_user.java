package gestion_de_stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gestion_de_stock.connexion_data;
import gestion_de_stock.log_in;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class change_user extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField case_user_2;
	private JTextField case_user_3;
	private JTextField case_user_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					change_user frame = new change_user();
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
	public change_user() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 20, 902, 757);
	contentPane = new JPanel();
	contentPane.setBackground(Color.RED);
	contentPane.setBorder(null);
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	//boutton de retour au login
	JButton btn_retour = new JButton("BACK");
	btn_retour.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			log_in log=new log_in();
			log.setVisible(true);
			change_user.this.dispose();
		}
	});
	;
	
	case_user_4 = new JTextField();
	case_user_4.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			
			if(case_user_4.getText().equals("Nom utilisateur")) {
				
				case_user_4.setText("");
				case_user_4.setForeground(Color.BLACK);
				case_user_4.setFont(new Font("Tahoma", Font.BOLD, 20));
				case_user_4.setHorizontalAlignment(SwingConstants.CENTER);
				case_user_4.setBackground(Color.LIGHT_GRAY);
			
			}
		}
	});
	case_user_4.setText("Nom utilisateur");
	case_user_4.setHorizontalAlignment(SwingConstants.CENTER);
	case_user_4.setForeground(Color.GRAY);
	case_user_4.setFont(new Font("Tahoma", Font.BOLD, 20));
	case_user_4.setColumns(10);
	case_user_4.setBackground(Color.LIGHT_GRAY);
	case_user_4.setBounds(629, 528, 172, 53);
	contentPane.add(case_user_4);
	
	case_user_3 = new JTextField();
	case_user_3.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			
			if(case_user_3.getText().equals("Nom utilisateur")) {
				
				case_user_3.setText("");
				case_user_3.setForeground(Color.BLACK);
				case_user_3.setFont(new Font("Tahoma", Font.BOLD, 20));
				case_user_3.setHorizontalAlignment(SwingConstants.CENTER);
				case_user_3.setBackground(Color.LIGHT_GRAY);
			
			}
		}
	});
	case_user_3.setText("Nom utilisateur");
	case_user_3.setHorizontalAlignment(SwingConstants.CENTER);
	case_user_3.setForeground(Color.GRAY);
	case_user_3.setFont(new Font("Tahoma", Font.BOLD, 20));
	case_user_3.setColumns(10);
	case_user_3.setBackground(Color.LIGHT_GRAY);
	case_user_3.setBounds(629, 416, 172, 53);
	contentPane.add(case_user_3);
	
	//la case de nom utilisateur premier 
	case_user_2 = new JTextField();
	case_user_2.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(case_user_2.getText().equals("Nom utilisateur")) {
				
				case_user_2.setText("");
				case_user_2.setForeground(Color.BLACK);
				case_user_2.setFont(new Font("Tahoma", Font.BOLD, 20));
				case_user_2.setHorizontalAlignment(SwingConstants.CENTER);
				case_user_2.setBackground(Color.LIGHT_GRAY);
			
			}
		}
	});
	case_user_2.setText("Nom utilisateur");
	case_user_2.setHorizontalAlignment(SwingConstants.CENTER);
	case_user_2.setForeground(Color.GRAY);
	case_user_2.setFont(new Font("Tahoma", Font.BOLD, 20));
	case_user_2.setColumns(10);
	case_user_2.setBackground(Color.LIGHT_GRAY);
	case_user_2.setBounds(629, 290, 172, 53);
	contentPane.add(case_user_2);
	
	JLabel lblConfirmerVotreNouveau = new JLabel("confirmer votre nouveau nom d'utilisateur ");
	lblConfirmerVotreNouveau.setHorizontalAlignment(SwingConstants.CENTER);
	lblConfirmerVotreNouveau.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblConfirmerVotreNouveau.setBounds(598, 491, 255, 26);
	contentPane.add(lblConfirmerVotreNouveau);
	
	JLabel lblTapezVotreNouveau = new JLabel("tapez votre nouveau nom d'utilisateur");
	lblTapezVotreNouveau.setHorizontalAlignment(SwingConstants.CENTER);
	lblTapezVotreNouveau.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblTapezVotreNouveau.setBounds(598, 379, 241, 26);
	contentPane.add(lblTapezVotreNouveau);
	
	JLabel lblTapezVotreAncien = new JLabel("tapez votre ancien nom d'utilisateur ");
	lblTapezVotreAncien.setFont(new Font("Tahoma", Font.BOLD, 12));
	lblTapezVotreAncien.setHorizontalAlignment(SwingConstants.CENTER);
	lblTapezVotreAncien.setBounds(598, 243, 241, 26);
	contentPane.add(lblTapezVotreAncien);
	
	JButton btn_modif = new JButton("Modifier");
	btn_modif.setFont(new Font("Tahoma", Font.BOLD, 23));
	btn_modif.setBounds(584, 621, 241, 53);
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
	back_log.setBounds(530, 198, 346, 493);
	contentPane.add(back_log);
	
	table = new JTable();
	table.setBounds(779, 46, 1, 1);
	contentPane.add(table);
	
	
	
	
	//boutton pour enregistrer modification 
	btn_modif.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Connection b=connexion_data.connecterDB();
			
			//pour la case de tapez votre ancien nom utilisateur
			if(!(case_user_2.getText().isEmpty()) && !case_user_2.getText().equals("Nom utilisateur")) {
				try {
					String sql="SELECT User from log_in where User='"+case_user_2.getText()+"' and EXISTS(SELECT User from log_in where User='"+log_in.case_user.getText()+"')";
					
					java.sql.Statement st =b.createStatement();
					ResultSet rs_1=st.executeQuery(sql);
					
					table.setModel(DbUtils.resultSetToTableModel(rs_1));
				
					int reff=table.getModel().getRowCount();
					
					if(reff !=1) {
						String[] btn_options = {"ok"};
					    JOptionPane.showOptionDialog(null,"erreur sur le nom d'utilisateur ancien ","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);

					
						//pour la case de tapez votre nouveau nom utilisateur
					}else if(!(case_user_3.getText().isEmpty()) && !case_user_3.getText().equals("Nom utilisateur")) {
						
								//pour la case de tapez confirm mot de passe
								if(!(case_user_4.getText().isEmpty()) && !case_user_4.getText().equals("Nom utilisateur")) {
									
									if(case_user_4.getText().equals(case_user_3.getText()) ) {
										
										if(JOptionPane.showConfirmDialog(null, "êtes-vous sur de modifier le nom d'utilisateur ")==0) {
											
										String sql_2="Update log_in SET User='"+case_user_4.getText()+"' where User ='"+log_in.case_user.getText()+"'";
										java.sql.Statement pst=b.createStatement();
										pst.executeUpdate(sql_2);
										
										ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
										JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
										
										log_in log=new log_in();
										log.setVisible(true);
										change_user.this.dispose();
										}
								
									}else {
										String[] btn_options = {"ok"};
									    JOptionPane.showOptionDialog(null,"erreur sur la confirmation de nom d'utilisateur","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);

									}
									
									
								}else {
									JOptionPane.showMessageDialog(null, "le champ de la confirmation de nom d'utilisateur est  vide ","Attention",JOptionPane.WARNING_MESSAGE);
								}
							
						}else {
							JOptionPane.showMessageDialog(null, "le champ de nouveau nom d'utilisateur est vide  ","Attention",JOptionPane.WARNING_MESSAGE);
							
						}
					
					
				}catch(Exception e1){
					e1.printStackTrace();
					
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "le champ d'ancien nom d'utilisateur est vide  ","Attention",JOptionPane.WARNING_MESSAGE);

				
			}
			
		}
	});
	
	}
}
