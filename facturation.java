package gestion_de_stock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import gestion_de_stock.connexion_data;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.awt.print.*;
import javafx.print.Printer;
public class facturation extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JTextField input_ligne;
	private JTextField input_designation;
	private JTextField input_tva;
	private JTextField input_cin;
	private JTextField input_client;
	private JTextField input_encaissement;
	private JTextField input_date;
	private JTable table_1;
	private JTextField input_recherche;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					facturation frame = new facturation();
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
	public facturation() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1112, 740);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(null);
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JButton btn_retour_acceuil = new JButton("<--");
	btn_retour_acceuil.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			vente vt = new vente();
			vt.setVisible(true);
			facturation.this.dispose();
		}
		
	});
	
	JButton btn_csv = new JButton("New button");
	btn_csv.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\imprimante.png"));
	btn_csv.setBounds(1001, 632, 109, 58);
	contentPane.add(btn_csv);
	
	JButton btn_recherche = new JButton("Recherche");
	btn_recherche.setFont(new Font("Dialog", Font.BOLD, 16));
	btn_recherche.setBounds(766, 419, 122, 47);
	contentPane.add(btn_recherche);
	
	input_recherche = new JTextField();
	input_recherche.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			connection_display();
		}
	});
	input_recherche.setToolTipText("");
	input_recherche.setColumns(10);
	input_recherche.setBounds(910, 426, 176, 38);
	contentPane.add(input_recherche);
	
	JButton btn_enregi_modif = new JButton("Modification");
	btn_enregi_modif.setFont(new Font("Dialog", Font.BOLD, 16));
	btn_enregi_modif.setBounds(806, 569, 137, 47);
	contentPane.add(btn_enregi_modif);
	btn_enregi_modif.hide();
	
	JButton btn_enregistrer = new JButton("Enregistrer");
	btn_enregistrer.setFont(new Font("Dialog", Font.BOLD, 16));
	btn_enregistrer.setBounds(806, 554, 122, 47);
	contentPane.add(btn_enregistrer);
	btn_enregistrer.hide();
	
	JButton btn_ajout = new JButton("Ajouter");
	btn_ajout.setBounds(174, 419, 89, 47);
	contentPane.add(btn_ajout);
	btn_ajout.setFont(new Font("Dialog", Font.BOLD, 16));
	
	
	
	
	
	
	JButton btn_modif = new JButton("Modifier");
	btn_modif.setBounds(349, 419, 122, 47);
	contentPane.add(btn_modif);
	btn_modif.setFont(new Font("Dialog", Font.BOLD, 16));
	
	JButton btn_supp = new JButton("Supprimer");
	btn_supp.setBounds(556, 419, 122, 47);
	contentPane.add(btn_supp);
	btn_supp.setFont(new Font("Dialog", Font.BOLD, 16));
	
	JPanel my_panel = new JPanel();
	my_panel.setLayout(null);
	my_panel.setForeground(Color.BLACK);
	my_panel.setBounds(137, 481, 646, 269);
	contentPane.add(my_panel);
	
	my_panel.hide();
	
	//boutton pour exit
	JButton btn_exit = new JButton("X");
	btn_exit.setForeground(Color.DARK_GRAY);
	btn_exit.setFont(new Font("Dialog", Font.BOLD, 12));
	btn_exit.setBackground(Color.RED);
	btn_exit.setBounds(589, 9, 47, 31);
	my_panel.add(btn_exit);
	
	JLabel lbl_ligne = new JLabel("Veuillez saisir le numéro de la ligne de vente :");
	lbl_ligne.setFont(new Font("Tahoma", Font.BOLD, 14));
	lbl_ligne.setBounds(27, 61, 359, 19);
	my_panel.add(lbl_ligne);
	
	input_ligne = new JTextField();
	input_ligne.setBounds(358, 60, 86, 20);
	my_panel.add(input_ligne);
	input_ligne.setColumns(10);
	
	JButton btn_ligne = new JButton("OK");
	
	btn_ligne.setBounds(465, 59, 89, 23);
	my_panel.add(btn_ligne);
	
	JLabel lbl_designation = new JLabel("Désignation");
	lbl_designation.setHorizontalAlignment(SwingConstants.CENTER);
	lbl_designation.setFont(new Font("Tahoma", Font.BOLD, 14));
	lbl_designation.setBounds(37, 91, 97, 19);
	my_panel.add(lbl_designation);
	
	input_designation = new JTextField();
	input_designation.setBounds(135, 92, 137, 20);
	my_panel.add(input_designation);
	input_designation.setColumns(10);
	
	JLabel lbl_tva = new JLabel("TVA");
	lbl_tva.setHorizontalAlignment(SwingConstants.CENTER);
	lbl_tva.setFont(new Font("Tahoma", Font.BOLD, 14));
	lbl_tva.setBounds(37, 153, 97, 19);
	my_panel.add(lbl_tva);
	
	input_tva = new JTextField();
	input_tva.setColumns(10);
	input_tva.setBounds(135, 153, 137, 21);
	my_panel.add(input_tva);
	
	JLabel lbl_cin = new JLabel("CIN");
	lbl_cin.setHorizontalAlignment(SwingConstants.CENTER);
	lbl_cin.setFont(new Font("Tahoma", Font.BOLD, 14));
	lbl_cin.setBounds(383, 89, 97, 19);
	my_panel.add(lbl_cin);
	
	input_cin = new JTextField();
	input_cin.setColumns(10);
	input_cin.setBounds(481, 90, 137, 20);
	my_panel.add(input_cin);
	
	JLabel lbl_client = new JLabel("NOM Client");
	lbl_client.setHorizontalAlignment(SwingConstants.CENTER);
	lbl_client.setFont(new Font("Tahoma", Font.BOLD, 14));
	lbl_client.setBounds(383, 151, 97, 19);
	my_panel.add(lbl_client);
	
	input_client = new JTextField();
	input_client.setColumns(10);
	input_client.setBounds(481, 152, 137, 20);
	my_panel.add(input_client);
	
	JLabel lbl_encaissement = new JLabel(" Encaissement");
	lbl_encaissement.setHorizontalAlignment(SwingConstants.CENTER);
	lbl_encaissement.setFont(new Font("Tahoma", Font.BOLD, 14));
	lbl_encaissement.setBounds(10, 209, 124, 19);
	my_panel.add(lbl_encaissement);
	
	input_encaissement = new JTextField();
	input_encaissement.setColumns(10);
	input_encaissement.setBounds(135, 210, 137, 20);
	my_panel.add(input_encaissement);
	
	JLabel lbl_date = new JLabel("Date");
	lbl_date.setHorizontalAlignment(SwingConstants.CENTER);
	lbl_date.setFont(new Font("Tahoma", Font.BOLD, 14));
	lbl_date.setBounds(383, 207, 97, 19);
	my_panel.add(lbl_date);
	
	input_date = new JTextField();
	input_date.setColumns(10);
	input_date.setBounds(481, 208, 137, 20);
	my_panel.add(input_date);
	
	JLabel lbl_pourcentage = new JLabel("%");
	lbl_pourcentage.setHorizontalAlignment(SwingConstants.LEFT);
	lbl_pourcentage.setFont(new Font("Tahoma", Font.BOLD, 14));
	lbl_pourcentage.setBounds(276, 153, 21, 19);
	my_panel.add(lbl_pourcentage);
	
	JCheckBox chb_designation = new JCheckBox("");
	chb_designation.setBounds(278, 91, 21, 23);
	my_panel.add(chb_designation);
	
	JCheckBox chb_tva = new JCheckBox("");
	chb_tva.setBounds(300, 153, 21, 23);
	my_panel.add(chb_tva);
	
	JCheckBox chb_encaissement = new JCheckBox("");
	chb_encaissement.setBounds(278, 209, 21, 23);
	my_panel.add(chb_encaissement);
	
	JCheckBox chb_cin = new JCheckBox("");
	chb_cin.setBounds(358, 87, 21, 23);
	my_panel.add(chb_cin);
	
	JCheckBox chb_client = new JCheckBox("");
	chb_client.setBounds(358, 153, 21, 23);
	my_panel.add(chb_client);
	
	JCheckBox chb_date = new JCheckBox("");
	chb_date.setBounds(358, 209, 21, 23);
	my_panel.add(chb_date);
	
	//action pour boutton exit
	btn_exit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			my_panel.hide();
			my_panel.setBounds(137, 481, 646, 269);
			btn_ajout.show();
			btn_modif.show();
			btn_supp.show();
			btn_csv.show();
			btn_enregistrer.hide();
			btn_enregi_modif.hide();
			btn_recherche.show();
			input_recherche.show();
			
			
			//enable les champs
			lbl_designation.setEnabled(true);
			input_designation.setEnabled(true);
			
			lbl_tva.setEnabled(true);
			input_tva.setEnabled(true);
			lbl_pourcentage.setEnabled(true);
			
			lbl_encaissement.setEnabled(true);
			input_encaissement.setEnabled(true);
			
			lbl_cin.setEnabled(true);
			input_cin.setEnabled(true);
			
			lbl_client.setEnabled(true);
			input_client.setEnabled(true);
			
			lbl_date.setEnabled(true);
			input_date.setEnabled(true);
			
			
		}
	});
	
	JLabel lblNewLabel_1 = new JLabel("New label");
	lblNewLabel_1.setBounds(921, 0, 175, 106); 
	lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\RS_blanc_2.jpg"));
	contentPane.add(lblNewLabel_1);
	
	JLabel lblSuiviDeFacturation = new JLabel("SUIVI DE FACTURATION");
	lblSuiviDeFacturation.setHorizontalAlignment(SwingConstants.CENTER);
	lblSuiviDeFacturation.setForeground(Color.WHITE);
	lblSuiviDeFacturation.setFont(new Font("Dialog", Font.BOLD, 23));
	lblSuiviDeFacturation.setBounds(338, 70, 324, 65);
	contentPane.add(lblSuiviDeFacturation);
	btn_retour_acceuil.setFont(new Font("Dialog", Font.BOLD, 16));
	btn_retour_acceuil.setBounds(10, 11, 56, 38);
	contentPane.add(btn_retour_acceuil);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(0, 146, 1096, 262);
	contentPane.add(scrollPane);
	
	table = new JTable();
	scrollPane.setViewportView(table);
	
	JLabel lblNewLabel = new JLabel("New label");
	lblNewLabel.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			connection_display();
		}
	});
	lblNewLabel.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\background_same_2_facturation.jpg"));
	lblNewLabel.setBounds(0, 0, 1096, 750);
	contentPane.add(lblNewLabel);
	
	table_1 = new JTable();
	table_1.setBounds(249, 26, 1, 1);
	contentPane.add(table_1);
	
	//action pour boutton ajouter 
		btn_ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				my_panel.show();
				my_panel.setBounds(150, 419, 646, 269);
				btn_ajout.hide();
				btn_modif.hide();
				btn_supp.hide();
				btn_csv.hide();
				btn_enregi_modif.hide();
				btn_recherche.hide();
				input_recherche.hide();
				
				//afficher le champ input pour la ligne
				lbl_ligne.show();
				input_ligne.show();
				btn_ligne.show();
				input_ligne.setText("");
				
				//cacher les champs
				
				lbl_designation.hide();
				input_designation.hide();
				
				lbl_tva.hide();
				input_tva.setText("");
				input_tva.hide();
				lbl_pourcentage.hide();
				
				lbl_encaissement.hide();
				input_encaissement.setText("");
				input_encaissement.hide();
				
				lbl_cin.hide();
				input_cin.setText("");
				input_cin.hide();
				
				lbl_client.hide();
				input_client.setText("");
				input_client.hide();
				
				lbl_date.hide();
				input_date.setText("");
				input_date.hide();
				
				//cacher les checkboxes
				
				lbl_ligne.show();
				input_ligne.show();
				btn_ligne.show();
				input_ligne.setText("");
				
				//cacher les champs
				
				chb_designation.hide();
				
				chb_tva.hide();
	
				chb_encaissement.hide();
				
				chb_cin.hide();
				
				chb_client.hide();
				
				chb_date.hide();
				
				lbl_designation.setEnabled(true);
				input_designation.setEnabled(true);
				
				lbl_tva.setEnabled(true);
				input_tva.setEnabled(true);
				lbl_pourcentage.setEnabled(true);
				
				lbl_encaissement.setEnabled(true);
				input_encaissement.setEnabled(true);
				
				lbl_cin.setEnabled(true);
				input_cin.setEnabled(true);
				
				lbl_client.setEnabled(true);
				input_client.setEnabled(true);
				
				lbl_date.setEnabled(true);
				input_date.setEnabled(true);
			
			}
		});
		
		
		
		//boutton OK pour la ligne 
		btn_ligne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection b = connexion_data.connecterDB();
				if(!(input_ligne.getText().isEmpty()) ) {
					try {
					String sql="SELECT * from vente where ligne="+input_ligne.getText();
					Statement st=b.createStatement();
					ResultSet rs=st.executeQuery(sql);
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					
					int row =table_1.getModel().getRowCount();
					if(row == 1) {
						
						btn_enregistrer.show();
						//on cache les bouttons
						lbl_ligne.hide();
						input_ligne.hide();
						btn_ligne.hide();
						
						//on affiche les champs
						
						lbl_designation.show();
						input_designation.show();
						input_designation.setText("");
						
						lbl_tva.show();
						input_tva.show();
						input_tva.setText("");
						lbl_pourcentage.show();
						
						lbl_encaissement.show();
						input_encaissement.show();
						input_encaissement.setText("");
						
						lbl_cin.show();
						input_cin.show();
						input_cin.setText("");
						
						lbl_client.show();
						input_client.show();
						input_client.setText("");
						
						lbl_date.show();
						input_date.show();
						input_date.setText("");
						
						
					
					}else {
						JOptionPane.showMessageDialog(null, "n'existe pas cette ligne","ERREUR",JOptionPane.ERROR_MESSAGE);
					}
				
					
				
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, "vous avez saisi une lettre","Attention",JOptionPane.WARNING_MESSAGE);
					}
				
				}else {
					JOptionPane.showMessageDialog(null, "Veuillez remplir le champ de la ligne","Attention",JOptionPane.WARNING_MESSAGE);
					
				}
			
				}
			
		});
		
		
		btn_enregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection b = connexion_data.connecterDB();
				
				try {
					
				String sql_ref="SELECT @ref :=  Reference from vente where ligne="+input_ligne.getText()+";";
				Statement st_ref=b.createStatement();
				st_ref.executeQuery(sql_ref);
				
				String sql_des="SET @des := '"+input_designation.getText()+"';";
				Statement st_des=b.createStatement();
				st_des.executeUpdate(sql_des);
				
				String sql_qt="SELECT @qt := Quantite from vente where ligne="+input_ligne.getText()+";";
				Statement st_qt=b.createStatement();
				st_qt.executeQuery(sql_qt);
				
				String sql_pv="SELECT @pv :=  Prix_vente from vente where ligne="+input_ligne.getText()+";";
				Statement st_pv=b.createStatement();
				st_pv.executeQuery(sql_pv);
				
				String sql_ht="SET @ht := (@pv * @qt) ;";
				Statement st_ht=b.createStatement();
				st_ht.executeUpdate(sql_ht);
				
				String sql_tva="SET @tva := ("+input_tva.getText()+" / 100) ;";
				Statement st_tva=b.createStatement();
				st_tva.executeUpdate(sql_tva);
				
				String sql_cin="SET @cin := '"+input_cin.getText()+"';";
				Statement st_cin=b.createStatement();
				st_cin.executeUpdate(sql_cin);
				
				String sql_client="SET @client := '"+input_client.getText()+"';";
				Statement st_client=b.createStatement();
				st_client.executeUpdate(sql_client);
				
				String sql_ttc="SET @ttc := (@ht + (@ht * @tva)) ;";
				Statement st_ttc=b.createStatement();
				st_ttc.executeUpdate(sql_ttc);
				
				String sql_mt="SET @mt := @ttc ;";
				Statement st_mt=b.createStatement();
				st_mt.executeUpdate(sql_mt);
				
				String sql_en="SET @en := '"+input_encaissement.getText()+"';";
				Statement st_en=b.createStatement();
				st_en.executeUpdate(sql_en);
							
				String sql_date="SET @date := '"+input_date.getText()+"';";
				Statement st_date=b.createStatement();
				st_date.executeUpdate(sql_date);
				
				
				String sql="INSERT INTO facturation(Reference,Designation,Quantite,HT,TVA,CIN,Nom_Client,Montant,Encaissement,Date) VALUES(@ref,@des,@qt,@ht,@tva,@cin,@client,@ttc,@en,@date)";
				Statement st=b.createStatement();
				st.executeUpdate(sql);
				
				ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
				JOptionPane.showMessageDialog(null, "Ajout bien établi","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
				
				lbl_ligne.show();
				input_ligne.setText("");
				input_ligne.show();
				btn_ligne.show();
				
				//on affiche les champs
				
				lbl_designation.hide();
				input_designation.hide();
				
				lbl_tva.hide();
				input_tva.hide();
				lbl_pourcentage.hide();
				
				lbl_encaissement.hide();
				input_encaissement.hide();
				
				lbl_cin.hide();
				input_cin.hide();
				
				lbl_client.hide();
				input_client.hide();
				
				lbl_date.hide();
				input_date.hide();
				btn_enregistrer.hide();
				connection_display();
				
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "date ou TVA incorrecte ","Attention",JOptionPane.WARNING_MESSAGE);
					
				}
			}
		});
		
		//action pour boutton modifier 
		btn_modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection b=connexion_data.connecterDB();
				
				if (table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "veuillez sélectionner une seule ligne de la table ");
				}else if(b != null) {
					
				my_panel.show();
				my_panel.setBounds(150, 419, 646, 269);
				btn_ajout.hide();
				btn_modif.hide();
				btn_supp.hide();
				btn_csv.hide();
				btn_enregi_modif.show();
				btn_recherche.hide();
				input_recherche.hide();
				
				
				
				lbl_ligne.hide();
				input_ligne.hide();
				btn_ligne.hide();
				input_ligne.setText("");
				
				//cacher les champs
				
				lbl_designation.show();
				lbl_designation.setEnabled(false);
				input_designation.setText("");
				input_designation.setEnabled(false);
				input_designation.show();
				
				lbl_tva.show();
				lbl_tva.setEnabled(false);
				input_tva.setText("");
				input_tva.setEnabled(false);
				input_tva.show();
				lbl_pourcentage.show();
				lbl_pourcentage.setEnabled(false);
				
				lbl_encaissement.show();
				lbl_encaissement.setEnabled(false);
				input_encaissement.setText("");
				input_encaissement.setEnabled(false);
				input_encaissement.show();
				
				lbl_cin.show();
				lbl_cin.setEnabled(false);
				input_cin.setText("");
				input_cin.setEnabled(false);
				input_cin.show();
				
				lbl_client.show();
				lbl_client.setEnabled(false);
				input_client.setText("");
				input_client.setEnabled(false);
				input_client.show();
				
				lbl_date.show();
				lbl_date.setEnabled(false);
				input_date.setText("");
				input_date.setEnabled(false);
				input_date.show();
				
				//afficher les checkboxes
				
				chb_designation.show();
				chb_designation.setSelected(false);
				chb_designation.setEnabled(true);
				
				chb_tva.show();
				chb_tva.setSelected(false);
				chb_tva.setEnabled(true);
				
				chb_encaissement.show();
				chb_encaissement.setSelected(false);
				chb_encaissement.setEnabled(true);
				
				chb_cin.show();
				chb_cin.setSelected(false);
				chb_cin.setEnabled(true);
				
				chb_client.show();
				chb_client.setSelected(false);
				chb_client.setEnabled(true);
				
				chb_date.show();
				chb_date.setSelected(false);
				chb_date.setEnabled(true);
				
				}
			}
		});
		
		//action checkbox designation
		chb_designation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(chb_designation.isSelected()) {
					
					//enable designation
					lbl_designation.show();
					lbl_designation.setEnabled(true);
					input_designation.setText("");
					input_designation.setEnabled(true);
					input_designation.show();
					
					//disable tous les champs
					lbl_tva.show();
					lbl_tva.setEnabled(false);
					input_tva.setText("");
					input_tva.setEnabled(false);
					input_tva.show();
					lbl_pourcentage.show();
					lbl_pourcentage.setEnabled(false);
					
					lbl_encaissement.show();
					lbl_encaissement.setEnabled(false);
					input_encaissement.setText("");
					input_encaissement.setEnabled(false);
					input_encaissement.show();
					
					lbl_cin.show();
					lbl_cin.setEnabled(false);
					input_cin.setText("");
					input_cin.setEnabled(false);
					input_cin.show();
					
					lbl_client.show();
					lbl_client.setEnabled(false);
					input_client.setText("");
					input_client.setEnabled(false);
					input_client.show();
					
					lbl_date.show();
					lbl_date.setEnabled(false);
					input_date.setText("");
					input_date.setEnabled(false);
					input_date.show();
					
					//cacher tous les chackbox
					
					chb_tva.hide();
					
					chb_encaissement.hide();
					
					chb_cin.hide();
					
					chb_client.hide();
					
					chb_date.hide();
					
				}else {
					
					//disable tous les champs
					lbl_designation.show();
					lbl_designation.setEnabled(false);
					input_designation.setText("");
					input_designation.setEnabled(false);
					input_designation.show();
					
					lbl_tva.show();
					lbl_tva.setEnabled(false);
					input_tva.setText("");
					input_tva.setEnabled(false);
					input_tva.show();
					lbl_pourcentage.show();
					lbl_pourcentage.setEnabled(false);
					
					lbl_encaissement.show();
					lbl_encaissement.setEnabled(false);
					input_encaissement.setText("");
					input_encaissement.setEnabled(false);
					input_encaissement.show();
					
					lbl_cin.show();
					lbl_cin.setEnabled(false);
					input_cin.setText("");
					input_cin.setEnabled(false);
					input_cin.show();
					
					lbl_client.show();
					lbl_client.setEnabled(false);
					input_client.setText("");
					input_client.setEnabled(false);
					input_client.show();
					
					lbl_date.show();
					lbl_date.setEnabled(false);
					input_date.setText("");
					input_date.setEnabled(false);
					input_date.show();
					
					//afficher les checkboxes
					
					chb_designation.show();
					chb_designation.setSelected(false);
					chb_designation.setEnabled(true);
					
					chb_tva.show();
					chb_tva.setSelected(false);
					chb_tva.setEnabled(true);
					
					chb_encaissement.show();
					chb_encaissement.setSelected(false);
					chb_encaissement.setEnabled(true);
					
					chb_cin.show();
					chb_cin.setSelected(false);
					chb_cin.setEnabled(true);
					
					chb_client.show();
					chb_client.setSelected(false);
					chb_client.setEnabled(true);
					
					chb_date.show();
					chb_date.setSelected(false);
					chb_date.setEnabled(true);
					
				}
			}
		});
		
		//action checkbox tva
				chb_tva.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if(chb_tva.isSelected()) {
							
							//enable tva
							lbl_tva.show();
							lbl_tva.setEnabled(true);
							input_tva.setText("");
							input_tva.setEnabled(true);
							input_tva.show();
							lbl_pourcentage.show();
							lbl_pourcentage.setEnabled(true);
							
							
							
							//disable tous les champs
						
							lbl_designation.show();
							lbl_designation.setEnabled(false);
							input_designation.setText("");
							input_designation.setEnabled(false);
							input_designation.show();
							
							lbl_encaissement.show();
							lbl_encaissement.setEnabled(false);
							input_encaissement.setText("");
							input_encaissement.setEnabled(false);
							input_encaissement.show();
							
							lbl_cin.show();
							lbl_cin.setEnabled(false);
							input_cin.setText("");
							input_cin.setEnabled(false);
							input_cin.show();
							
							lbl_client.show();
							lbl_client.setEnabled(false);
							input_client.setText("");
							input_client.setEnabled(false);
							input_client.show();
							
							lbl_date.show();
							lbl_date.setEnabled(false);
							input_date.setText("");
							input_date.setEnabled(false);
							input_date.show();
							
							//cacher tous les chackbox
							
							chb_designation.hide();
							
							chb_encaissement.hide();
							
							chb_cin.hide();
							
							chb_client.hide();
							
							chb_date.hide();
							
						}else {
							
							//disable tous les champs
							lbl_designation.show();
							lbl_designation.setEnabled(false);
							input_designation.setText("");
							input_designation.setEnabled(false);
							input_designation.show();
							
							lbl_tva.show();
							lbl_tva.setEnabled(false);
							input_tva.setText("");
							input_tva.setEnabled(false);
							input_tva.show();
							lbl_pourcentage.show();
							lbl_pourcentage.setEnabled(false);
							
							lbl_encaissement.show();
							lbl_encaissement.setEnabled(false);
							input_encaissement.setText("");
							input_encaissement.setEnabled(false);
							input_encaissement.show();
							
							lbl_cin.show();
							lbl_cin.setEnabled(false);
							input_cin.setText("");
							input_cin.setEnabled(false);
							input_cin.show();
							
							lbl_client.show();
							lbl_client.setEnabled(false);
							input_client.setText("");
							input_client.setEnabled(false);
							input_client.show();
							
							lbl_date.show();
							lbl_date.setEnabled(false);
							input_date.setText("");
							input_date.setEnabled(false);
							input_date.show();
							
							//afficher les checkboxes
							
							chb_designation.show();
							chb_designation.setSelected(false);
							chb_designation.setEnabled(true);
							
							chb_tva.show();
							chb_tva.setSelected(false);
							chb_tva.setEnabled(true);
							
							chb_encaissement.show();
							chb_encaissement.setSelected(false);
							chb_encaissement.setEnabled(true);
							
							chb_cin.show();
							chb_cin.setSelected(false);
							chb_cin.setEnabled(true);
							
							chb_client.show();
							chb_client.setSelected(false);
							chb_client.setEnabled(true);
							
							chb_date.show();
							chb_date.setSelected(false);
							chb_date.setEnabled(true);
							
						}
					}
				});
				
				//action checkbox encaissement
				chb_encaissement.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if(chb_encaissement.isSelected()) {
							
							//enable encaissement
							
							lbl_encaissement.show();
							lbl_encaissement.setEnabled(true);
							input_encaissement.setText("");
							input_encaissement.setEnabled(true);
							input_encaissement.show();
							
							
							//disable tous les champs
						
							lbl_designation.show();
							lbl_designation.setEnabled(false);
							input_designation.setText("");
							input_designation.setEnabled(false);
							input_designation.show();
							
							lbl_tva.show();
							lbl_tva.setEnabled(false);
							input_tva.setText("");
							input_tva.setEnabled(false);
							input_tva.show();
							lbl_pourcentage.show();
							lbl_pourcentage.setEnabled(false);
							
							lbl_cin.show();
							lbl_cin.setEnabled(false);
							input_cin.setText("");
							input_cin.setEnabled(false);
							input_cin.show();
							
							lbl_client.show();
							lbl_client.setEnabled(false);
							input_client.setText("");
							input_client.setEnabled(false);
							input_client.show();
							
							lbl_date.show();
							lbl_date.setEnabled(false);
							input_date.setText("");
							input_date.setEnabled(false);
							input_date.show();
							
							//cacher tous les chackbox
							
							chb_designation.hide();
							
							chb_tva.hide();
							
							chb_cin.hide();
							
							chb_client.hide();
							
							chb_date.hide();
							
						}else {
							
							//disable tous les champs
							lbl_designation.show();
							lbl_designation.setEnabled(false);
							input_designation.setText("");
							input_designation.setEnabled(false);
							input_designation.show();
							
							lbl_tva.show();
							lbl_tva.setEnabled(false);
							input_tva.setText("");
							input_tva.setEnabled(false);
							input_tva.show();
							lbl_pourcentage.show();
							lbl_pourcentage.setEnabled(false);
							
							lbl_encaissement.show();
							lbl_encaissement.setEnabled(false);
							input_encaissement.setText("");
							input_encaissement.setEnabled(false);
							input_encaissement.show();
							
							lbl_cin.show();
							lbl_cin.setEnabled(false);
							input_cin.setText("");
							input_cin.setEnabled(false);
							input_cin.show();
							
							lbl_client.show();
							lbl_client.setEnabled(false);
							input_client.setText("");
							input_client.setEnabled(false);
							input_client.show();
							
							lbl_date.show();
							lbl_date.setEnabled(false);
							input_date.setText("");
							input_date.setEnabled(false);
							input_date.show();
							
							//afficher les checkboxes
							
							chb_designation.show();
							chb_designation.setSelected(false);
							chb_designation.setEnabled(true);
							
							chb_tva.show();
							chb_tva.setSelected(false);
							chb_tva.setEnabled(true);
							
							chb_encaissement.show();
							chb_encaissement.setSelected(false);
							chb_encaissement.setEnabled(true);
							
							chb_cin.show();
							chb_cin.setSelected(false);
							chb_cin.setEnabled(true);
							
							chb_client.show();
							chb_client.setSelected(false);
							chb_client.setEnabled(true);
							
							chb_date.show();
							chb_date.setSelected(false);
							chb_date.setEnabled(true);
							
						}
					}
				});
				
				
				//action checkbox cin
				chb_cin.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if(chb_cin.isSelected()) {
							
							//enable cin
							
							lbl_cin.show();
							lbl_cin.setEnabled(true);
							input_cin.setText("");
							input_cin.setEnabled(true);
							input_cin.show();
							
							//disable tous les champs
						
							lbl_designation.show();
							lbl_designation.setEnabled(false);
							input_designation.setText("");
							input_designation.setEnabled(false);
							input_designation.show();
							
							lbl_tva.show();
							lbl_tva.setEnabled(false);
							input_tva.setText("");
							input_tva.setEnabled(false);
							input_tva.show();
							lbl_pourcentage.show();
							lbl_pourcentage.setEnabled(false);
							
							lbl_encaissement.show();
							lbl_encaissement.setEnabled(false);
							input_encaissement.setText("");
							input_encaissement.setEnabled(false);
							input_encaissement.show();
							
							lbl_client.show();
							lbl_client.setEnabled(false);
							input_client.setText("");
							input_client.setEnabled(false);
							input_client.show();
							
							lbl_date.show();
							lbl_date.setEnabled(false);
							input_date.setText("");
							input_date.setEnabled(false);
							input_date.show();
							
							//cacher tous les chackbox
							
							chb_designation.hide();
							
							chb_tva.hide();
							
							chb_encaissement.hide();
							
							chb_client.hide();
							
							chb_date.hide();
							
						}else {
							
							//disable tous les champs
							lbl_designation.show();
							lbl_designation.setEnabled(false);
							input_designation.setText("");
							input_designation.setEnabled(false);
							input_designation.show();
							
							lbl_tva.show();
							lbl_tva.setEnabled(false);
							input_tva.setText("");
							input_tva.setEnabled(false);
							input_tva.show();
							lbl_pourcentage.show();
							lbl_pourcentage.setEnabled(false);
							
							lbl_encaissement.show();
							lbl_encaissement.setEnabled(false);
							input_encaissement.setText("");
							input_encaissement.setEnabled(false);
							input_encaissement.show();
							
							lbl_cin.show();
							lbl_cin.setEnabled(false);
							input_cin.setText("");
							input_cin.setEnabled(false);
							input_cin.show();
							
							lbl_client.show();
							lbl_client.setEnabled(false);
							input_client.setText("");
							input_client.setEnabled(false);
							input_client.show();
							
							lbl_date.show();
							lbl_date.setEnabled(false);
							input_date.setText("");
							input_date.setEnabled(false);
							input_date.show();
							
							//afficher les checkboxes
							
							chb_designation.show();
							chb_designation.setSelected(false);
							chb_designation.setEnabled(true);
							
							chb_tva.show();
							chb_tva.setSelected(false);
							chb_tva.setEnabled(true);
							
							chb_encaissement.show();
							chb_encaissement.setSelected(false);
							chb_encaissement.setEnabled(true);
							
							chb_cin.show();
							chb_cin.setSelected(false);
							chb_cin.setEnabled(true);
							
							chb_client.show();
							chb_client.setSelected(false);
							chb_client.setEnabled(true);
							
							chb_date.show();
							chb_date.setSelected(false);
							chb_date.setEnabled(true);
							
						}
					}
				});
				
				//action checkbox client
				chb_client.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if(chb_client.isSelected()) {
							
							//enable client
							
							lbl_client.show();
							lbl_client.setEnabled(true);
							input_client.setText("");
							input_client.setEnabled(true);
							input_client.show();
							
							//disable tous les champs
						
							lbl_designation.show();
							lbl_designation.setEnabled(false);
							input_designation.setText("");
							input_designation.setEnabled(false);
							input_designation.show();
							
							lbl_tva.show();
							lbl_tva.setEnabled(false);
							input_tva.setText("");
							input_tva.setEnabled(false);
							input_tva.show();
							lbl_pourcentage.show();
							lbl_pourcentage.setEnabled(false);
							
							lbl_encaissement.show();
							lbl_encaissement.setEnabled(false);
							input_encaissement.setText("");
							input_encaissement.setEnabled(false);
							input_encaissement.show();
							

							lbl_cin.show();
							lbl_cin.setEnabled(false);
							input_cin.setText("");
							input_cin.setEnabled(false);
							input_cin.show();
							
							
							lbl_date.show();
							lbl_date.setEnabled(false);
							input_date.setText("");
							input_date.setEnabled(false);
							input_date.show();
							
							//cacher tous les chackbox
							
							chb_designation.hide();
							
							chb_tva.hide();
							
							chb_encaissement.hide();
							
							chb_cin.hide();
							
							chb_date.hide();
							
						}else {
							
							//disable tous les champs
							lbl_designation.show();
							lbl_designation.setEnabled(false);
							input_designation.setText("");
							input_designation.setEnabled(false);
							input_designation.show();
							
							lbl_tva.show();
							lbl_tva.setEnabled(false);
							input_tva.setText("");
							input_tva.setEnabled(false);
							input_tva.show();
							lbl_pourcentage.show();
							lbl_pourcentage.setEnabled(false);
							
							lbl_encaissement.show();
							lbl_encaissement.setEnabled(false);
							input_encaissement.setText("");
							input_encaissement.setEnabled(false);
							input_encaissement.show();
							
							lbl_cin.show();
							lbl_cin.setEnabled(false);
							input_cin.setText("");
							input_cin.setEnabled(false);
							input_cin.show();
							
							lbl_client.show();
							lbl_client.setEnabled(false);
							input_client.setText("");
							input_client.setEnabled(false);
							input_client.show();
							
							lbl_date.show();
							lbl_date.setEnabled(false);
							input_date.setText("");
							input_date.setEnabled(false);
							input_date.show();
							
							//afficher les checkboxes
							
							chb_designation.show();
							chb_designation.setSelected(false);
							chb_designation.setEnabled(true);
							
							chb_tva.show();
							chb_tva.setSelected(false);
							chb_tva.setEnabled(true);
							
							chb_encaissement.show();
							chb_encaissement.setSelected(false);
							chb_encaissement.setEnabled(true);
							
							chb_cin.show();
							chb_cin.setSelected(false);
							chb_cin.setEnabled(true);
							
							chb_client.show();
							chb_client.setSelected(false);
							chb_client.setEnabled(true);
							
							chb_date.show();
							chb_date.setSelected(false);
							chb_date.setEnabled(true);
							
						}
					}
				});
				
				
				//action checkbox date
				chb_date.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if(chb_date.isSelected()) {
							
							//enable date
							
							lbl_date.show();
							lbl_date.setEnabled(true);
							input_date.setText("");
							input_date.setEnabled(true);
							input_date.show();
							
							
							
							//disable tous les champs
						
							lbl_designation.show();
							lbl_designation.setEnabled(false);
							input_designation.setText("");
							input_designation.setEnabled(false);
							input_designation.show();
							
							lbl_tva.show();
							lbl_tva.setEnabled(false);
							input_tva.setText("");
							input_tva.setEnabled(false);
							input_tva.show();
							lbl_pourcentage.show();
							lbl_pourcentage.setEnabled(false);
							
							lbl_encaissement.show();
							lbl_encaissement.setEnabled(false);
							input_encaissement.setText("");
							input_encaissement.setEnabled(false);
							input_encaissement.show();
							

							lbl_cin.show();
							lbl_cin.setEnabled(false);
							input_cin.setText("");
							input_cin.setEnabled(false);
							input_cin.show();
							
							lbl_client.show();
							lbl_client.setEnabled(false);
							input_client.setText("");
							input_client.setEnabled(false);
							input_client.show();
						
							
							//cacher tous les chackbox
							
							chb_designation.hide();
							
							chb_tva.hide();
							
							chb_encaissement.hide();
							
							chb_cin.hide();
							
							chb_client.hide();
							
						}else {
							
							//disable tous les champs
							lbl_designation.show();
							lbl_designation.setEnabled(false);
							input_designation.setText("");
							input_designation.setEnabled(false);
							input_designation.show();
							
							lbl_tva.show();
							lbl_tva.setEnabled(false);
							input_tva.setText("");
							input_tva.setEnabled(false);
							input_tva.show();
							lbl_pourcentage.show();
							lbl_pourcentage.setEnabled(false);
							
							lbl_encaissement.show();
							lbl_encaissement.setEnabled(false);
							input_encaissement.setText("");
							input_encaissement.setEnabled(false);
							input_encaissement.show();
							
							lbl_cin.show();
							lbl_cin.setEnabled(false);
							input_cin.setText("");
							input_cin.setEnabled(false);
							input_cin.show();
							
							lbl_client.show();
							lbl_client.setEnabled(false);
							input_client.setText("");
							input_client.setEnabled(false);
							input_client.show();
							
							lbl_date.show();
							lbl_date.setEnabled(false);
							input_date.setText("");
							input_date.setEnabled(false);
							input_date.show();
							
							//afficher les checkboxes
							
							chb_designation.show();
							chb_designation.setSelected(false);
							chb_designation.setEnabled(true);
							
							chb_tva.show();
							chb_tva.setSelected(false);
							chb_tva.setEnabled(true);
							
							chb_encaissement.show();
							chb_encaissement.setSelected(false);
							chb_encaissement.setEnabled(true);
							
							chb_cin.show();
							chb_cin.setSelected(false);
							chb_cin.setEnabled(true);
							
							chb_client.show();
							chb_client.setSelected(false);
							chb_client.setEnabled(true);
							
							chb_date.show();
							chb_date.setSelected(false);
							chb_date.setEnabled(true);
							
						}
					}
				});
				
				//action de l'enregistrement de modification
				btn_enregi_modif.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							Connection b = connexion_data.connecterDB();
							if(chb_designation.isSelected() || chb_tva.isSelected() ||chb_encaissement.isSelected() ||chb_cin.isSelected() ||chb_client.isSelected() ||chb_date.isSelected()  ) {
								if (table.getSelectionModel().isSelectionEmpty()) {
									JOptionPane.showMessageDialog(null, "veuillez sélectionner une seule ligne de la table ");
								}else if(b != null) {
								int ligne = table.getSelectedRow();
								
								try {
								//designation
									if(chb_designation.isSelected() && !(input_designation.getText().isEmpty()) ){
										
										String sql_designation = "update facturation set Designation='"+input_designation.getText()+"' where Ligne="+(ligne+1);
										Statement st_designation=b.createStatement();
										st_designation.executeUpdate(sql_designation);

										ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
										JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
										
										my_panel.hide();
										btn_enregi_modif.hide();
										btn_ajout.show();
										btn_modif.show();
										btn_supp.show();
										
										connection_display();
										
									}else if(chb_designation.isSelected() && input_designation.getText().isEmpty()) {
										
										JOptionPane.showMessageDialog(null, "veuillez remplir le champ de désignation","Attention",JOptionPane.WARNING_MESSAGE);
									
										
									}
									
									//tva
									
									if(chb_tva.isSelected() && !(input_tva.getText().isEmpty()) ){
										
										String sql_tva = "update facturation set TVA=("+input_tva.getText()+"/100) where Ligne="+(ligne+1);
										Statement st_tva=b.createStatement();
										st_tva.executeUpdate(sql_tva);

										ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
										JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
										
										my_panel.hide();
										btn_enregi_modif.hide();
										btn_ajout.show();
										btn_modif.show();
										btn_supp.show();
										
										connection_display();
										
									}else if(chb_tva.isSelected() && input_tva.getText().isEmpty()) {
										
										JOptionPane.showMessageDialog(null, "veuillez remplir le champ de TVA","Attention",JOptionPane.WARNING_MESSAGE);
										
									}
									
									//encaissement
									
									if(chb_encaissement.isSelected() && !(input_encaissement.getText().isEmpty()) ){
										
										String sql_en = "update facturation set encaissement='"+input_encaissement.getText()+"' where Ligne="+(ligne+1);
										Statement st_en=b.createStatement();
										st_en.executeUpdate(sql_en);

										ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
										JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
										
										my_panel.hide();
										btn_enregi_modif.hide();
										btn_ajout.show();
										btn_modif.show();
										btn_supp.show();
										
										connection_display();
										
									}else if(chb_encaissement.isSelected() && input_encaissement.getText().isEmpty()) {
										
										JOptionPane.showMessageDialog(null, "veuillez remplir le champ d'encaissement","Attention",JOptionPane.WARNING_MESSAGE);
										
									}
									
									
									//cin
									
									if(chb_cin.isSelected() && !(input_cin.getText().isEmpty()) ){
										
										String sql_cin = "update facturation set CIN='"+input_cin.getText()+"' where Ligne="+(ligne+1);
										Statement st_cin=b.createStatement();
										st_cin.executeUpdate(sql_cin);

										ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
										JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
										
										my_panel.hide();
										btn_enregi_modif.hide();
										btn_ajout.show();
										btn_modif.show();
										btn_supp.show();
										
										connection_display();
										
									}else if(chb_cin.isSelected() && input_cin.getText().isEmpty()) {
										
										JOptionPane.showMessageDialog(null, "veuillez remplir le champ de CIN","Attention",JOptionPane.WARNING_MESSAGE);
										
									}
									
									//client
									
									if(chb_client.isSelected() && !(input_client.getText().isEmpty()) ){
										
										String sql_client = "update facturation set Nom_Client='"+input_client.getText()+"' where Ligne="+(ligne+1);
										Statement st_client=b.createStatement();
										st_client.executeUpdate(sql_client);

										ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
										JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
										
										my_panel.hide();
										btn_enregi_modif.hide();
										btn_ajout.show();
										btn_modif.show();
										btn_supp.show();
										
										connection_display();
										
									}else if(chb_client.isSelected() && input_client.getText().isEmpty()) {
										
										JOptionPane.showMessageDialog(null, "veuillez remplir le champ du Nom de Client","Attention",JOptionPane.WARNING_MESSAGE);
										
									}
									
									//date
									
									if(chb_date.isSelected() && !(input_date.getText().isEmpty()) ){
										
										String sql_date = "update facturation set Date='"+input_date.getText()+"' where Ligne="+(ligne+1);
										Statement st_date=b.createStatement();
										st_date.executeUpdate(sql_date);

										ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
										JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
										
										my_panel.hide();
										btn_enregi_modif.hide();
										btn_ajout.show();
										btn_modif.show();
										btn_supp.show();
										
										connection_display();
										
									}else if(chb_date.isSelected() && input_date.getText().isEmpty()) {
										
										JOptionPane.showMessageDialog(null, "veuillez remplir le champ de la Date","Attention",JOptionPane.WARNING_MESSAGE);
										
									}
							
								}catch(Exception e1) {
									e1.printStackTrace();
								}
							
							}
							
							}else {
								
								JOptionPane.showMessageDialog(null, "veuillez cocher une seule case","Attention",JOptionPane.WARNING_MESSAGE);
							}
						
					}
				});
		
				
			//boutton pour suppresion
				btn_supp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Connection b=connexion_data.connecterDB();
						
						if (table.getSelectionModel().isSelectionEmpty()) {
							JOptionPane.showMessageDialog(null, "veuillez sélectionner une seule ligne de la table ");
						}else if(b != null) {
							
							int ligne = table.getSelectedRow();
							try {
								
								if(JOptionPane.showConfirmDialog(null, " vous voulez supprimer ?")==0) {
									String sql = "delete from facturation where facturation.Ligne="+(ligne+1);
								
									Statement st =b.createStatement();
									st.executeUpdate(sql);
									
									String sql_ligne="SET @num := 0";
									Statement stmt=b.createStatement();
									stmt.executeUpdate(sql_ligne);
									
									String sql_ligne_3="UPDATE facturation SET Ligne=@num := (@num+1);";
									Statement stmt_3=b.createStatement();
									stmt.executeUpdate(sql_ligne_3);
									
									String sql_ligne_2="ALTER TABLE facturation AUTO_INCREMENT = 1";
									Statement stmt_2=b.createStatement();
									stmt.executeUpdate(sql_ligne_2);
									
									
							ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
							JOptionPane.showMessageDialog(null, " suppression bien établie ","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
							
							connection_display();
							}
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
				
				//action pour boutton rechrehce
				btn_recherche.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Connection b = connexion_data.connecterDB();
						if(!(input_recherche.getText().isEmpty()) ) {
							try {
								String sql="SELECT * FROM facturation where Reference='"+input_recherche.getText()+"'";
								Statement st=b.createStatement();
								ResultSet rs = st.executeQuery(sql);
								
								table.setModel(DbUtils.resultSetToTableModel(rs));
								int row = table.getModel().getRowCount();
								
								if(row >= 1) {
								}else {
									
									JOptionPane.showMessageDialog(null, "cette référence : "+input_recherche.getText()+"n'existe pas","ERREUR",JOptionPane.ERROR_MESSAGE);
									connection_display();

								}
							}catch(Exception e1) {
								e1.printStackTrace();
							}
						}else {
							JOptionPane.showMessageDialog(null, "veuillez remplir le champ de recherche","Attention",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				
				//btn for excel 
				btn_csv.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Connection b=connexion_data.connecterDB();

						MessageFormat header = new MessageFormat("RS NETWORK : FACTURE");
							
						MessageFormat footer = new MessageFormat("Page{0,number,integer}");
						
						try {
	
						table.print(JTable.PrintMode.FIT_WIDTH,header,footer);
						
					/*	String sql="SELECT * FROM facturation into outfile \"c:/Users/seddik/Desktop/facture.csv\" fields terminated by ';' lines terminated by '\n'  ";
						

						Statement st=b.createStatement();
						ResultSet rs=st.executeQuery(sql);
						PrintWriter writer = new PrintWriter("c:/Users/seddik/Desktop/facture.csv","UTF-8");
							writer.append(" Reference;\"Designation\";\"Quantite\";\"HT\";\"TVA\";\"CIN\";\"Nom_Client\";\"Montant\";\"Encaissement\";\"Date\";\"Ligne\" \n");
							
						/*FileWriter file = new FileWriter("c:/Users/seddik/Desktop/facture.csv",true);
							BufferedWriter bu = new BufferedWriter(file);
							bu.write(" Reference;\"Prix_achat\";\"Stock\";\"Nom_Fournisseur\" \n");
							bu.newLine();
							bu.write(rs);
							bu.close();
							file.close();
							
						
						
						//writer.close();
						 */
						}catch(java.awt.print.PrinterException e1) {
							System.err.format("ERREUR impression", e1.getMessage());
						}
						//select * from facturation into outfile "c:\\DESKTOP\\NOM FICHIER" fields terminated by ',' lines terminated by '\n' ;
					}
				});
		
	connection_display();
	
	
	}
	
	public static void connection_display(){
		Connection b=connexion_data.connecterDB();
		if(b != null) {
			try {
			String sql = "select * from facturation";
			Statement st =b.createStatement();
			ResultSet rs=st.executeQuery(sql);
			//sur import faut faire static pour tabel
			table.setModel(DbUtils.resultSetToTableModel(rs));

			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
