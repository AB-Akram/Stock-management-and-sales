package gestion_de_stock;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import gestion_de_stock.connexion_data;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
public class Produits extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JButton btnNewButton;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton btnNewButton_1;
	private JLabel Lreference;
	private JLabel Lprix_achat;
	private JLabel Lstock;
	private JLabel Lnom_fournisseur;
	private JTextField input_ref;
	private JTextField input_pa;
	private JTextField input_stock;
	private JTextField input_f;
	private JButton btnEnregistrer;
	private JButton btnEnregistrer_1;
	private JLabel lblVeuillezRemplirCe;
	private JLabel label_pa;
	private JLabel label_stock;
	private JLabel lblVeuillezRemplirCe_1;
	private JTextField input_recherche;
	private JButton btn_modif;
	private JLabel label_case;
	private JLabel lblRfrence;
	private JLabel label_prix_achat;
	private JLabel label_stock_bch;
	private JLabel label_nom_fournisseur;
	private JButton btn_recherche_supp;
	private JTextField input_recherche_supp;
	private JButton btn_retour;
	private JButton btn_retour_acceuil;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produits frame = new Produits();
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
	public Produits() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 20, 902, 743);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//setUndecorated(true);
		
		btn_retour_acceuil = new JButton("<--");
		btn_retour_acceuil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Acceuil ac=new Acceuil();
				ac.setVisible(true);
				Produits.this.dispose();
				connexion_data.closeconnexion();
			}
		});
		btn_retour_acceuil.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_retour_acceuil.setBounds(10, 20, 56, 38);
		contentPane.add(btn_retour_acceuil);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 87, 753, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		//declaration des trois bouttons 
		btnSupprimer = new JButton("Supprimer");
		btnNewButton = new JButton("Ajouter");
		btnModifier = new JButton("Modifier");
	
		
		btnModifier.setFont(new Font("Dialog", Font.BOLD, 16));
		btnModifier.setBounds(202, 356, 104, 47);
		contentPane.add(btnModifier);
		
		
		btnSupprimer.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSupprimer.setBounds(316, 356, 122, 47);
		contentPane.add(btnSupprimer);
		
		JPanel my_panel = new JPanel();
		my_panel.setForeground(new Color(0, 0, 0));
		my_panel.setBounds(50, 414, 646, 269);
		contentPane.add(my_panel);
		my_panel.setLayout(null);
		
		lblVeuillezRemplirCe = new JLabel("Veuillez remplir ce champ");
		lblVeuillezRemplirCe.setForeground(Color.RED);
		lblVeuillezRemplirCe.setBounds(87, 79, 230, 14);
		my_panel.add(lblVeuillezRemplirCe);
		
		
		
		lblVeuillezRemplirCe_1 = new JLabel("Veuillez remplir ce champ");
		lblVeuillezRemplirCe_1.setForeground(Color.RED);
		lblVeuillezRemplirCe_1.setBounds(400, 148, 236, 14);
		my_panel.add(lblVeuillezRemplirCe_1);
		
		JLabel lblVeuillezRemplirCe_2 = new JLabel("Veuillez remplir ce champ");
		lblVeuillezRemplirCe_2.setForeground(Color.RED);
		lblVeuillezRemplirCe_2.setBounds(406, 79, 230, 14);
		my_panel.add(lblVeuillezRemplirCe_2);
		lblVeuillezRemplirCe_2.hide();
		
		label_case = new JLabel("veuillez cocher une seule case :\r\n");
		label_case.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_case.setForeground(Color.RED);
		label_case.setBounds(183, 11, 308, 14);
		my_panel.add(label_case);
		
		lblRfrence = new JLabel("Référence");
		lblRfrence.setBounds(60, 53, 67, 14);
		my_panel.add(lblRfrence);
		
		label_prix_achat = new JLabel("  Prix achat");
		label_prix_achat.setBounds(183, 53, 67, 14);
		my_panel.add(label_prix_achat);
		
		label_stock_bch = new JLabel("  Stock");
		label_stock_bch.setBounds(280, 53, 47, 14);
		my_panel.add(label_stock_bch);
		
		label_nom_fournisseur = new JLabel("      Nom Fournisseur");
		label_nom_fournisseur.setBounds(360, 49, 117, 23);
		my_panel.add(label_nom_fournisseur);
		
		Lreference = new JLabel("Référence :");
		Lreference.setFont(new Font("Tahoma", Font.BOLD, 14));
		Lreference.setHorizontalAlignment(SwingConstants.CENTER);
		Lreference.setBounds(22, 97, 105, 31);
		my_panel.add(Lreference);
		
		Lprix_achat = new JLabel("Prix Achat :");
		Lprix_achat.setHorizontalAlignment(SwingConstants.CENTER);
		Lprix_achat.setFont(new Font("Tahoma", Font.BOLD, 14));
		Lprix_achat.setBounds(22, 166, 93, 31);
		my_panel.add(Lprix_achat);
		
		Lstock = new JLabel("Stock :");
		Lstock.setHorizontalAlignment(SwingConstants.CENTER);
		Lstock.setFont(new Font("Tahoma", Font.BOLD, 14));
		Lstock.setBounds(349, 97, 93, 31);
		my_panel.add(Lstock);
		
		Lnom_fournisseur = new JLabel("Nom Fournisseur :");
		Lnom_fournisseur.setHorizontalAlignment(SwingConstants.CENTER);
		Lnom_fournisseur.setFont(new Font("Tahoma", Font.BOLD, 14));
		Lnom_fournisseur.setBounds(349, 166, 128, 31);
		my_panel.add(Lnom_fournisseur);
		
		input_ref = new JTextField();
		input_ref.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblVeuillezRemplirCe.hide();
			}
		});
		//boutton pour enregistrer la modification
		btn_modif = new JButton("Modification");
		btn_modif.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_modif.setBounds(248, 222, 146, 47);
		my_panel.add(btn_modif);
		
		input_ref.setBounds(137, 104, 86, 20);
		my_panel.add(input_ref);
		input_ref.setColumns(10);
		
		input_pa = new JTextField();
		input_pa.setText("0");
		input_pa.setColumns(10);
		input_pa.setBounds(137, 173, 86, 20);
		my_panel.add(input_pa);
		
		input_stock = new JTextField();
		input_stock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblVeuillezRemplirCe_2.hide();
				lblVeuillezRemplirCe_2.setText("Veuillez remplir ce champ");
			}
		});
		input_stock.setColumns(10);
		input_stock.setBounds(476, 104, 105, 20);
		my_panel.add(input_stock);
		
		input_f = new JTextField();
		input_f.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblVeuillezRemplirCe_1.hide();
			}
		});
		
		input_f.setColumns(10);
		input_f.setBounds(505, 173, 106, 20);
		my_panel.add(input_f);
		
		
		//boutton pour la recherche apres modif
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.setFont(new Font("Dialog", Font.BOLD, 16));
		btnRecherche.setBounds(556, 356, 122, 47);
		contentPane.add(btnRecherche);
		
		//jtext pour la recherche a cote deboutton recherche
		input_recherche = new JTextField();
		input_recherche.setToolTipText("");
		input_recherche.setBounds(700, 363, 176, 38);
		contentPane.add(input_recherche);
		input_recherche.setColumns(10);
		
		JCheckBox chb_pa = new JCheckBox("");
		chb_pa.setBounds(163, 49, 21, 23);
		my_panel.add(chb_pa);
		
		JCheckBox chb_stock = new JCheckBox("");
		chb_stock.setBounds(262, 49, 21, 23);
		my_panel.add(chb_stock);
		
		JCheckBox chb_f = new JCheckBox("");
		chb_f.setBounds(349, 49, 21, 23);
		my_panel.add(chb_f);
		
		//les quatre chekboxe apres bouton modifier
		JCheckBox chb_ref = new JCheckBox("");
		chb_ref.setBounds(33, 49, 21, 23);
		my_panel.add(chb_ref);
		
		label_case.hide();
		chb_ref.hide();
		lblRfrence.hide();
		chb_pa.hide();
		label_prix_achat.hide();
		chb_stock.hide();
		label_stock_bch.hide();
		chb_f.hide();
		label_nom_fournisseur.hide();
		
		//boutton pour exit X
		btnNewButton_1 = new JButton("X");
		btnNewButton_1.setBounds(599, 11, 47, 31);
		my_panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//apres la sortie on affiche les trois boutton ajout et modif et suppr
				my_panel.hide();
				btnSupprimer.show();
				btnModifier.show();
				btnNewButton.show();
				
				//et on vide toute les case qui se trouve sur le deuxieme panel apres on cliquant sur le boutton ajouter
				input_ref.setText("");
				input_pa.setText("0");
				input_stock.setText("");
				input_f.setText("");
				
				//pour la sortie o cache les bouton qui appartient au modif comme btn recherche...
				btn_modif.hide();
				btnRecherche.hide();
				input_recherche.hide();
				label_case.hide();
				chb_ref.hide();
				lblRfrence.hide();
				chb_pa.hide();
				label_prix_achat.hide();
				chb_stock.hide();
				label_stock_bch.hide();
				chb_f.hide();
				label_nom_fournisseur.hide();
				input_recherche.setText("");
	
				chb_ref.setEnabled(true);
				chb_pa.setEnabled(true);
				chb_stock.setEnabled(true);
				chb_f.setEnabled(true);
				btnRecherche.setEnabled(true);
				input_recherche.setEnabled(true);
				
				chb_ref.setSelected(false);
				chb_pa.setSelected(false);
				chb_stock.setSelected(false);
				chb_f.setSelected(false);
				
				connection_display();
				
			}
		});
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 12));
		
	
	
		//boutton pour enregister apres boutton ajouter
		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection b=connexion_data.connecterDB();
				String Reference = input_ref.getText();
				String Prix_achat = input_pa.getText();
				
				String Nom_Fournisseur = input_f.getText();
				
				
				
				if(b != null) {
					try {
						//String sql ="insert into produits (Reference,Prix achat,Stock,Nom Fournisseur)values('"+input_ref.getText()+"',"+input_pa.getText()+","+input_stock.getText()+",'"+input_f.getText()+"')";
						
						if(input_ref.getText().isEmpty() && input_f.getText().isEmpty() && input_stock.getText().isEmpty() ) {
							
							lblVeuillezRemplirCe.show();
							lblVeuillezRemplirCe_1.show();
							lblVeuillezRemplirCe_2.show();
							
						}else if(input_ref.getText().isEmpty() && input_f.getText().isEmpty() && input_stock.getText().equals("0")) {
							
							lblVeuillezRemplirCe.show();
							lblVeuillezRemplirCe_1.show();
							lblVeuillezRemplirCe_2.show();
							lblVeuillezRemplirCe_2.setText("Donner un nombre supérieur à 0");
						
						}else if(input_f.getText().isEmpty() && input_stock.getText().equals("0")) {
							
							lblVeuillezRemplirCe_1.show();
							lblVeuillezRemplirCe_2.show();
							lblVeuillezRemplirCe_2.setText("Donner un nombre supérieur à 0");
							
						}else if( input_f.getText().isEmpty() && input_stock.getText().isEmpty()) {
							
							lblVeuillezRemplirCe_1.show();
							lblVeuillezRemplirCe_2.show();
							
						}else if(input_ref.getText().isEmpty() && input_stock.getText().equals("0")) {
							
							lblVeuillezRemplirCe.show();
							lblVeuillezRemplirCe_2.show();
							lblVeuillezRemplirCe_2.setText("Donner un nombre supérieur à 0");
							
						}else if( input_ref.getText().isEmpty() && input_stock.getText().isEmpty()) {
							
							lblVeuillezRemplirCe.show();
							lblVeuillezRemplirCe_2.show();
							
						}else if(input_f.getText().isEmpty() && input_ref.getText().isEmpty()) {
							
							lblVeuillezRemplirCe_1.show();
							lblVeuillezRemplirCe_2.show();
							lblVeuillezRemplirCe_2.setText("Donner un nombre supérieur à 0");
						
							
						}else if(input_ref.getText().isEmpty()) {
							
							lblVeuillezRemplirCe.show();
							
						}else if(input_f.getText().isEmpty()) {
							
							lblVeuillezRemplirCe_1.show();
							
						}else if(input_stock.getText().isEmpty() ) {
							
							lblVeuillezRemplirCe_2.show();
							
						}else if(input_stock.getText().equals("0")) {
							
							lblVeuillezRemplirCe_2.show();
							lblVeuillezRemplirCe_2.setText("Donner un nombre supérieur à 0");
							
						}else {
							
							String Stock = input_stock.getText();
							int Stk = Integer.parseInt(Stock);
							Double pa = Double.parseDouble(Prix_achat);	
							
							lblVeuillezRemplirCe.hide();
							lblVeuillezRemplirCe_1.hide();
							lblVeuillezRemplirCe_2.hide();
						String sql ="insert into produits values('"+Reference+"',"+pa+","+Stk+",'"+Nom_Fournisseur+"')";
						Statement st =b.createStatement();
						st.executeUpdate(sql);
						
						ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
						JOptionPane.showMessageDialog(null, "Ajout bien établi","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
						
						
						
						String sql_2 = "select * from produits";
						Statement pst =b.createStatement();
						ResultSet rs=pst.executeQuery(sql_2);
						table.setModel(DbUtils.resultSetToTableModel(rs));
						input_ref.setText("");
						input_pa.setText("0");
						input_stock.setText("");
						input_f.setText(""); 
					
				}
						
					}catch(Exception e1) {
						String[] btn_options = {"ok"};
					    JOptionPane.showOptionDialog(null,"vous avez donner le prix d'achat ou le stock un lettre","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);

					}
					
				}
			}
				});
		btnEnregistrer.setFont(new Font("Dialog", Font.BOLD, 16));
		btnEnregistrer.setBounds(241, 222, 120, 47);
		my_panel.add(btnEnregistrer);
		
		
		my_panel.hide();
		
		//boutton pour ajouter
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//affichage de platform et cacher les boutton
				my_panel.show();
				btnSupprimer.hide();
				btnModifier.hide();
				lblVeuillezRemplirCe.hide();
				lblVeuillezRemplirCe_1.hide();
				lblVeuillezRemplirCe_2.hide();
				label_case.hide();
				btn_modif.hide();
				btnEnregistrer.show();
				
				//afficher les input
				Lreference.show();
				input_ref.show();
				Lprix_achat.show();
				input_pa.show();
				Lstock.show();
				input_stock.show();
				Lnom_fournisseur.show();
				input_f.show();
				
				//enable les input
				Lreference.setEnabled(true);
				input_ref.setEnabled(true);
				input_ref.setText("");
				
				Lprix_achat.setEnabled(true);
				input_pa.setEnabled(true);
				input_pa.setText("0");
				
				Lstock.setEnabled(true);
				input_stock.setEnabled(true);
				input_stock.setText("");
				
				Lnom_fournisseur.setEnabled(true);
				input_f.setEnabled(true);
				input_f.setText("");
				
				//cacher les checkbox
				chb_ref.hide();
				lblRfrence.hide();
				chb_pa.hide();
				label_prix_achat.hide();
				chb_stock.hide();
				label_stock_bch.hide();
				chb_f.hide();
				label_nom_fournisseur.hide();
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setBounds(103, 356, 89, 47);
		contentPane.add(btnNewButton);
		
		btn_recherche_supp = new JButton("Recherche");
		btn_recherche_supp.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_recherche_supp.setBounds(556, 356, 122, 47);
		contentPane.add(btn_recherche_supp);
		btn_recherche_supp.hide();
		
		input_recherche_supp = new JTextField();
		input_recherche_supp.setToolTipText("");
		input_recherche_supp.setColumns(10);
		input_recherche_supp.setBounds(700, 363, 176, 38);
		contentPane.add(input_recherche_supp);
		input_recherche_supp.hide();
		
		btn_retour = new JButton("<--");
		btn_retour.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_retour.setBounds(10, 356, 89, 47);
		contentPane.add(btn_retour);
		
		JButton btn_enregistrer_supp = new JButton("suppression");
		btn_enregistrer_supp.setBounds(545, 429, 165, 47);
		contentPane.add(btn_enregistrer_supp);
		btn_enregistrer_supp.setFont(new Font("Dialog", Font.BOLD, 16));
		
		btn_enregistrer_supp.hide();
		
		btn_retour.hide();
		
		
		btnRecherche.hide();
		input_recherche.hide();
		
		//bouton de modifier
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				my_panel.show();
				btnEnregistrer.hide();
				btnSupprimer.hide();
				lblVeuillezRemplirCe.hide();;
				lblVeuillezRemplirCe_1.hide();
				btn_modif.hide();
				btnNewButton.hide();
				Lreference.hide();
				input_ref.hide();
				Lprix_achat.hide();
				input_pa.hide();
				Lstock.hide();
				input_stock.hide();
				Lnom_fournisseur.hide();
				input_f.hide();
				
				
				input_recherche.show();
				input_recherche.setText("");
				btnRecherche.show();
				
				//cacher les cases
				label_case.hide();
				chb_ref.hide();
				lblRfrence.hide();
				chb_pa.hide();
				label_prix_achat.hide();
				chb_stock.hide();
				label_stock_bch.hide();
				chb_f.hide();
				label_nom_fournisseur.hide();
				
				connection_display();
				
			}
		});
		
	
		//boutton pour la recherche
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//checkbox reference
				if(!(input_recherche.getText().isEmpty()) ) {
					Connection b=connexion_data.connecterDB();
					
					if(b != null) {
					try {
						
						//on les caches dab
						
						
						Lreference.hide();
						input_ref.hide();
						input_ref.setText("");
						
						Lprix_achat.hide();
						input_pa.hide();
						input_pa.setText("");
						
						Lstock.hide();
						input_stock.hide();
						input_stock.setText("");
						
						Lnom_fournisseur.hide();
						input_f.hide();
						input_f.setText("");
						
						
						
						// on les affcihes 
						label_case.show();
						chb_ref.show();
						lblRfrence.show();
						chb_pa.show();
						label_prix_achat.show();
						chb_stock.show();
						label_stock_bch.show();
						chb_f.show();
						label_nom_fournisseur.show();
						
						//decocher les checkbox
						
						chb_ref.setSelected(false);
						chb_pa.setSelected(false);
						chb_stock.setSelected(false);
						chb_f.setSelected(false);
						
						//enable les checkbox
						chb_ref.setEnabled(true);
						chb_pa.setEnabled(true);
						chb_stock.setEnabled(true);
						chb_f.setEnabled(true);
						
						btn_modif.hide();
						
						//********************************************************** continue d programme 
						
						String sql = "SELECT * FROM produits WHERE Reference='"+input_recherche.getText()+"' AND EXISTS(SELECT Reference from produits where Reference='"+input_recherche.getText()+"')";
						
								/*String sql = "select * "
									+ "from produits "
									+ "where Reference ='"+input_recherche.getText()+"'";
						*/
						
						Statement st =b.createStatement();
						ResultSet rs=st.executeQuery(sql);
						table.setModel(DbUtils.resultSetToTableModel(rs));
						
						String ref=table.getModel().getValueAt(0,0).toString();
						int reff=table.getModel().getRowCount();
						
						
						
					if(reff==1) {
						//on les affiches
						label_case.show();
						chb_ref.show();
						lblRfrence.show();
						chb_pa.show();
						label_prix_achat.show();
						chb_stock.show();
						label_stock_bch.show();
						chb_f.show();
						label_nom_fournisseur.show();
						
					}
						}catch(Exception e1) {
							e1.printStackTrace();
						}
					
						int refff=table.getModel().getRowCount();
					if(refff!=1){
						String[] btn_options = {"ok"};
					    JOptionPane.showOptionDialog(null,"erreur cett référence : "+input_recherche.getText()+" n'existe pas  sur BD","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);

						label_case.hide();
						chb_ref.hide();
						lblRfrence.hide();
						chb_pa.hide();
						label_prix_achat.hide();
						chb_stock.hide();
						label_stock_bch.hide();
						chb_f.hide();
						label_nom_fournisseur.hide();
						
						//enable les autre input
						Lreference.hide();
						input_ref.hide();
						input_ref.setText("");
						
						Lprix_achat.hide();
						input_pa.hide();
						input_pa.setText("");
						
						Lstock.hide();
						input_stock.hide();
						input_stock.setText("");
						
						Lnom_fournisseur.hide();
						input_f.hide();
						input_f.setText("");
						
						
						//decocher les checkbox
						chb_ref.setSelected(false);
						chb_pa.setSelected(false);
						chb_stock.setSelected(false);
						chb_f.setSelected(false);
						
						//enable les checkbox
						chb_ref.setEnabled(true);
						chb_pa.setEnabled(true);
						chb_stock.setEnabled(true);
						chb_f.setEnabled(true);
						
						btn_modif.hide();
						
						connection_display();
					}
					}
					
				
					
		
			}else {JOptionPane.showMessageDialog(null, "veuillez remplir le champ de recherche","Attention",JOptionPane.WARNING_MESSAGE);
			//enable les autre input
			Lreference.setEnabled(true);
			input_ref.setEnabled(true);
			input_ref.setText("");
			
			Lprix_achat.setEnabled(true);
			input_pa.setEnabled(true);
			input_pa.setText("");
			
			Lstock.setEnabled(true);
			input_stock.setEnabled(true);
			input_stock.setText("");
			
			Lnom_fournisseur.setEnabled(true);
			input_f.setEnabled(true);
			input_f.setText("");
			
			input_recherche.setText("");
			
			//decocher les checkbox
			chb_ref.setSelected(false);
			chb_pa.setSelected(false);
			chb_stock.setSelected(false);
			chb_f.setSelected(false);
			
			//enable les checkbox
			chb_ref.setEnabled(true);
			chb_pa.setEnabled(true);
			chb_stock.setEnabled(true);
			chb_f.setEnabled(true);
			
			connection_display();
			
			Lreference.hide();
			input_ref.hide();
			
			Lprix_achat.hide();
			input_pa.hide();
			
			Lstock.hide();
			input_stock.hide();
			
			Lnom_fournisseur.hide();
			input_f.hide();
			
			label_case.hide();
			chb_ref.hide();
			lblRfrence.hide();
			chb_pa.hide();
			label_prix_achat.hide();
			chb_stock.hide();
			label_stock_bch.hide();
			chb_f.hide();
			label_nom_fournisseur.hide();
			
			btn_modif.hide();
			
			connection_display();
			}
			}
		});
		
		//checkbox reference checked
		chb_ref.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(chb_ref.isSelected()) {
					//disabled les chckbox
					chb_pa.hide();
					chb_stock.hide();
					chb_f.hide();
					
					//disabled les autres input
					Lprix_achat.setEnabled(false);
					input_pa.setEnabled(false);
					
					Lstock.setEnabled(false);
					input_stock.setEnabled(false);
					
					Lnom_fournisseur.setEnabled(false);
					input_f.setEnabled(false);
					
					Lreference.show();
					input_ref.show();
					
					Lprix_achat.show();
					input_pa.show();
					
					Lstock.show();
					input_stock.show();
					
					Lnom_fournisseur.show();
					input_f.show();
					
					btn_modif.show();
					
					
					
					
				}else{
					//enable les checkbox
					chb_pa.setEnabled(true);
					chb_stock.setEnabled(true);
					chb_f.setEnabled(true);
					
					chb_pa.show();
					chb_stock.show();
					chb_f.show();
					
					//enable les autre input
					Lprix_achat.setEnabled(true);
					input_pa.setEnabled(true);
					
					Lstock.setEnabled(true);
					input_stock.setEnabled(true);
					
					Lnom_fournisseur.setEnabled(true);
					input_f.setEnabled(true);
					
					Lreference.hide();
					input_ref.hide();
					
					Lprix_achat.hide();
					input_pa.hide();
					
					Lstock.hide();
					input_stock.hide();
					
					Lnom_fournisseur.hide();
					input_f.hide();
					
					btn_modif.hide();
				}
			}
		});
		
		//checkbox prix achat chcked
		chb_pa.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				if(chb_pa.isSelected()) {
					//disabled les chckbox
					chb_ref.hide();
					chb_stock.hide();
					chb_f.hide();
					
					//disabled les autres input
					Lreference.setEnabled(false);
					input_ref.setEnabled(false);
					
					Lstock.setEnabled(false);
					input_stock.setEnabled(false);
					
					Lnom_fournisseur.setEnabled(false);
					input_f.setEnabled(false);
					
					
					Lreference.show();
					input_ref.show();
					
					Lprix_achat.show();
					input_pa.show();
					
					Lstock.show();
					input_stock.show();
					
					Lnom_fournisseur.show();
					input_f.show();
					
					btn_modif.show();
					
				}else{
					//enable les checkbox
					chb_ref.setEnabled(true);
					chb_stock.setEnabled(true);
					chb_f.setEnabled(true);
					
					chb_ref.show();
					chb_stock.show();
					chb_f.show();
					
					//enable les autre input
					Lreference.setEnabled(true);
					input_ref.setEnabled(true);
					
					Lstock.setEnabled(true);
					input_stock.setEnabled(true);
					
					Lnom_fournisseur.setEnabled(true);
					input_f.setEnabled(true);
					
					Lreference.hide();
					input_ref.hide();
					
					Lprix_achat.hide();
					input_pa.hide();
					
					Lstock.hide();
					input_stock.hide();
					
					Lnom_fournisseur.hide();
					input_f.hide();
					
					btn_modif.hide();
				}
				
			}
			
		});
		
		//checkbox pour le stock
		
		chb_stock.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				if(chb_stock.isSelected()) {
					//disabled les chckbox
					chb_ref.hide();
					chb_pa.hide();
					chb_f.hide();
					
					//disabled les autres input
					Lreference.setEnabled(false);
					input_ref.setEnabled(false);
					
					Lprix_achat.setEnabled(false);
					input_pa.setEnabled(false);
					
					Lnom_fournisseur.setEnabled(false);
					input_f.setEnabled(false);
					
					Lreference.show();
					input_ref.show();
					
					Lprix_achat.show();
					input_pa.show();
					
					Lstock.show();
					input_stock.show();
					
					Lnom_fournisseur.show();
					input_f.show();
					
					btn_modif.show();
					
				}else{
					//enable les checkbox
					chb_ref.setEnabled(true);
					chb_pa.setEnabled(true);
					chb_f.setEnabled(true);
					
					chb_ref.show();
					chb_pa.show();
					chb_f.show();
					
					
					//enable les autre input
					Lreference.setEnabled(true);
					input_ref.setEnabled(true);
					
					Lprix_achat.setEnabled(true);
					input_pa.setEnabled(true);
					
					Lnom_fournisseur.setEnabled(true);
					input_f.setEnabled(true);
					
					Lreference.hide();
					input_ref.hide();
					
					Lprix_achat.hide();
					input_pa.hide();
					
					Lstock.hide();
					input_stock.hide();
					
					Lnom_fournisseur.hide();
					input_f.hide();
					
					btn_modif.hide();
				}
				
			}
		});
		
		//checkbox pour le nom de fournisseur
		
		chb_f.addMouseListener(new MouseAdapter() {
			public void  mouseClicked(MouseEvent e) {
				
				if(chb_f.isSelected()) {
					//disabled les chckbox
					chb_ref.hide();
					chb_pa.hide();
					chb_stock.hide();
					
					//disabled les autres input
					Lreference.setEnabled(false);
					input_ref.setEnabled(false);
					
					Lprix_achat.setEnabled(false);
					input_pa.setEnabled(false);
					
					Lstock.setEnabled(false);
					input_stock.setEnabled(false);
					
					Lreference.show();
					input_ref.show();
					
					Lprix_achat.show();
					input_pa.show();
					
					Lstock.show();
					input_stock.show();
					
					Lnom_fournisseur.show();
					input_f.show();
					
					btn_modif.show();
				}else{
					//enable les checkbox
					chb_ref.setEnabled(true);
					chb_pa.setEnabled(true);
					chb_stock.setEnabled(true);
					
					chb_ref.show();
					chb_pa.show();
					chb_stock.show();
					
					//enable les autre input
					Lreference.setEnabled(true);
					input_ref.setEnabled(true);
					
					Lprix_achat.setEnabled(true);
					input_pa.setEnabled(true);
					
					Lstock.setEnabled(true);
					input_stock.setEnabled(true);
					
					Lreference.hide();
					input_ref.hide();
					
					Lprix_achat.hide();
					input_pa.hide();
					
					Lstock.hide();
					input_stock.hide();
					
					Lnom_fournisseur.hide();
					input_f.hide();
					
					btn_modif.hide();
				
					
					
				}
				
				
			}
			
		});
		
		//boutton et action pour enregistrer la modification
		btn_modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection b=connexion_data.connecterDB();
				
				if(chb_ref.isSelected() && !(input_ref.getText().isEmpty()) ) {
					if(b != null) {
						try {
							
							String sql_2="SELECT * FROM vente where Reference ='"+input_recherche.getText()+"'";
							Statement pst =b.createStatement();
							ResultSet rss=pst.executeQuery(sql_2);
							table_1.setModel(DbUtils.resultSetToTableModel(rss));
							
							int reff=table_1.getModel().getRowCount();
							if(reff >= 1) {
								
						String sql = "update produits set Reference='"+input_ref.getText()+"' where Reference='"+input_recherche.getText()+"'";
						String sql_3="UPDATE vente SET  Reference='"+input_ref.getText()+"' where Reference='"+input_recherche.getText()+"'";
						String sql_facturastion="SELECT * from facturation where Reference='"+input_recherche.getText()+"'";

						Statement st =b.createStatement();
						Statement STT=b.createStatement();
						Statement st_facturation =b.createStatement();
						
						
						if (JOptionPane.showConfirmDialog(null, "êtes-vous sur de modifier la Référence ?") == 0) {
							
						st.executeUpdate(sql);
						STT.executeUpdate(sql_3);
						
						ResultSet rs_facturation=st_facturation.executeQuery(sql_facturastion);
						table_1.setModel(DbUtils.resultSetToTableModel(rs_facturation));
						int row =table_1.getModel().getRowCount();
						
						if(row >= 1) {
							
						String sql_upfa="UPDATE facturation SET  Reference='"+input_ref.getText()+"' where Reference='"+input_recherche.getText()+"'";
						Statement st_upfa=b.createStatement();
						st_upfa.executeUpdate(sql_upfa);
						
						}
						ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
						JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
						
						//enable les autre input
						Lreference.setEnabled(true);
						input_ref.setEnabled(true);
						input_ref.setText("");
						
						Lprix_achat.setEnabled(true);
						input_pa.setEnabled(true);
						input_pa.setText("");
						
						Lstock.setEnabled(true);
						input_stock.setEnabled(true);
						input_stock.setText("");
						
						Lnom_fournisseur.setEnabled(true);
						input_f.setEnabled(true);
						input_f.setText("");
						
						input_recherche.setText("");
						
						//decocher les checkbox
						chb_ref.setSelected(false);
						chb_pa.setSelected(false);
						chb_stock.setSelected(false);
						chb_f.setSelected(false);
						
						//enable les checkbox
						chb_ref.setEnabled(true);
						chb_pa.setEnabled(true);
						chb_stock.setEnabled(true);
						chb_f.setEnabled(true);
						
						connection_display();
						
						Lreference.hide();
						input_ref.hide();
						
						Lprix_achat.hide();
						input_pa.hide();
						
						Lstock.hide();
						input_stock.hide();
						
						Lnom_fournisseur.hide();
						input_f.hide();
						
						label_case.hide();
						chb_ref.hide();
						lblRfrence.hide();
						chb_pa.hide();
						label_prix_achat.hide();
						chb_stock.hide();
						label_stock_bch.hide();
						chb_f.hide();
						label_nom_fournisseur.hide();
						
						btn_modif.hide();
						}
						
						}else {
							
							String sql = "update produits set Reference='"+input_ref.getText()+"' where Reference='"+input_recherche.getText()+"'";
		
							
							Statement st =b.createStatement();
							
							if (JOptionPane.showConfirmDialog(null, "êtes-vous sur de modifier la Référence ?") == 0) {
								
							st.executeUpdate(sql);
							
							ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");

							JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
							
							//enable les autre input
							Lreference.setEnabled(true);
							input_ref.setEnabled(true);
							input_ref.setText("");
							
							Lprix_achat.setEnabled(true);
							input_pa.setEnabled(true);
							input_pa.setText("");
							
							Lstock.setEnabled(true);
							input_stock.setEnabled(true);
							input_stock.setText("");
							
							Lnom_fournisseur.setEnabled(true);
							input_f.setEnabled(true);
							input_f.setText("");
							
							input_recherche.setText("");
							
							//decocher les checkbox
							chb_ref.setSelected(false);
							chb_pa.setSelected(false);
							chb_stock.setSelected(false);
							chb_f.setSelected(false);
							
							//enable les checkbox
							chb_ref.setEnabled(true);
							chb_pa.setEnabled(true);
							chb_stock.setEnabled(true);
							chb_f.setEnabled(true);
							
							connection_display();
							
							Lreference.hide();
							input_ref.hide();
							
							Lprix_achat.hide();
							input_pa.hide();
							
							Lstock.hide();
							input_stock.hide();
							
							Lnom_fournisseur.hide();
							input_f.hide();
							
							label_case.hide();
							chb_ref.hide();
							lblRfrence.hide();
							chb_pa.hide();
							label_prix_achat.hide();
							chb_stock.hide();
							label_stock_bch.hide();
							chb_f.hide();
							label_nom_fournisseur.hide();
							
							btn_modif.hide();
							}
							
							
						}
						
						}catch(Exception e1) {
							
							JOptionPane.showMessageDialog(null, "cette Référence : "+input_ref.getText()+" deja existe","ERREUR",JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}else if(chb_pa.isSelected() && !(input_pa.getText().isEmpty()) ) {
					if(b != null) {
						try {
							
							String sql_3="SELECT * FROM vente where Reference ='"+input_recherche.getText()+"'";
							Statement ttt =b.createStatement();
							ResultSet rss=ttt.executeQuery(sql_3);
							table_1.setModel(DbUtils.resultSetToTableModel(rss));
							
							int reff=table_1.getModel().getRowCount();
							
							if(reff >= 1) {
								
						String sql = "update produits set Prix_achat="+input_pa.getText()+" where Reference='"+input_recherche.getText()+"'";
						String sqll ="UPDATE vente SET Prix_achat="+input_pa.getText()+" where Reference='"+input_recherche.getText()+"'";
						Statement st =b.createStatement();
						Statement ST=b.createStatement();
						
						if (JOptionPane.showConfirmDialog(null, "êtes-vous sur de modifier le prix d'achat ?") == 0) {
						st.executeUpdate(sql);
						ST.executeUpdate(sqll);
						
						String sql_2 = "update vente set Benefice=((Prix_vente * Quantite) - ("+input_pa.getText()+" * Quantite)) where Reference='"+input_recherche.getText()+"'";
						Statement pst =b.createStatement();
						pst.executeUpdate(sql_2);
						
						ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
						JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
						
						//enable les autre input
						Lreference.setEnabled(true);
						input_ref.setEnabled(true);
						input_ref.setText("");
						
						Lprix_achat.setEnabled(true);
						input_pa.setEnabled(true);
						input_pa.setText("");
						
						Lstock.setEnabled(true);
						input_stock.setEnabled(true);
						input_stock.setText("");
						
						Lnom_fournisseur.setEnabled(true);
						input_f.setEnabled(true);
						input_f.setText("");
						
						input_recherche.setText("");
						
						//decocher les checkbox
						chb_ref.setSelected(false);
						chb_pa.setSelected(false);
						chb_stock.setSelected(false);
						chb_f.setSelected(false);
						
						//enable les checkbox
						chb_ref.setEnabled(true);
						chb_pa.setEnabled(true);
						chb_stock.setEnabled(true);
						chb_f.setEnabled(true);
						
						connection_display();
						
						Lreference.hide();
						input_ref.hide();
						
						Lprix_achat.hide();
						input_pa.hide();
						
						Lstock.hide();
						input_stock.hide();
						
						Lnom_fournisseur.hide();
						input_f.hide();
						
						label_case.hide();
						chb_ref.hide();
						lblRfrence.hide();
						chb_pa.hide();
						label_prix_achat.hide();
						chb_stock.hide();
						label_stock_bch.hide();
						chb_f.hide();
						label_nom_fournisseur.hide();
						
						btn_modif.hide();
						
						}
						
							}else {
								String sql = "update produits set Prix_achat="+input_pa.getText()+" where Reference='"+input_recherche.getText()+"'";
			
								
								Statement st =b.createStatement();
								
								if (JOptionPane.showConfirmDialog(null, "êtes-vous sur de modifier le prix d'achat ?") == 0) {
								st.executeUpdate(sql);;
								
								String sql_2 = "update vente set Benefice=((Prix_vente * Quantite) - ("+input_pa.getText()+" * Quantite)) where Reference='"+input_recherche.getText()+"'";
								Statement pst =b.createStatement();
								pst.executeUpdate(sql_2);
								
								ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
								JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
								
								//enable les autre input
								Lreference.setEnabled(true);
								input_ref.setEnabled(true);
								input_ref.setText("");
								
								Lprix_achat.setEnabled(true);
								input_pa.setEnabled(true);
								input_pa.setText("");
								
								Lstock.setEnabled(true);
								input_stock.setEnabled(true);
								input_stock.setText("");
								
								Lnom_fournisseur.setEnabled(true);
								input_f.setEnabled(true);
								input_f.setText("");
								
								input_recherche.setText("");
								
								//decocher les checkbox
								chb_ref.setSelected(false);
								chb_pa.setSelected(false);
								chb_stock.setSelected(false);
								chb_f.setSelected(false);
								
								//enable les checkbox
								chb_ref.setEnabled(true);
								chb_pa.setEnabled(true);
								chb_stock.setEnabled(true);
								chb_f.setEnabled(true);
								
								connection_display();
								
								Lreference.hide();
								input_ref.hide();
								
								Lprix_achat.hide();
								input_pa.hide();
								
								Lstock.hide();
								input_stock.hide();
								
								Lnom_fournisseur.hide();
								input_f.hide();
								
								label_case.hide();
								chb_ref.hide();
								lblRfrence.hide();
								chb_pa.hide();
								label_prix_achat.hide();
								chb_stock.hide();
								label_stock_bch.hide();
								chb_f.hide();
								label_nom_fournisseur.hide();
								
								btn_modif.hide();
								
								}
								
							}
						
						}catch(Exception e1) {
							e1.printStackTrace();
						}
					}
					
				}else if(chb_stock.isSelected() && !(input_stock.getText().isEmpty()) ) {
					if(b != null) {
						try {
							
						
							String sql_2="SELECT * FROM vente where Reference ='"+input_recherche.getText()+"'";
							Statement pst =b.createStatement();
							ResultSet rss=pst.executeQuery(sql_2);
							table_1.setModel(DbUtils.resultSetToTableModel(rss));
							
							int reff=table_1.getModel().getRowCount();
							if(reff >= 1) {	
								
								String sql = "update produits set Stock="+input_stock.getText()+" where Reference='"+input_recherche.getText()+"'";
								String sqll ="UPDATE vente SET Quantite=0 where Reference='"+input_recherche.getText()+"'";
								String sql_b = "update vente set Benefice=((Prix_vente*Quantite)-(Prix_achat*Quantite)) where Reference='"+input_recherche.getText()+"'";
				
								Statement st =b.createStatement();
								Statement ST = b.createStatement();
								Statement pstt =b.createStatement();
								

								if (JOptionPane.showConfirmDialog(null, "êtes-vous sur de modifier le stock ?") == 0) {
									
								st.executeUpdate(sql);
								ST.executeUpdate(sqll);
								pstt.executeUpdate(sql_b);
								
								ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
								JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
								
								
								//enable les autre input
								Lreference.setEnabled(true);
								input_ref.setEnabled(true);
								input_ref.setText("");
								
								Lprix_achat.setEnabled(true);
								input_pa.setEnabled(true);
								input_pa.setText("");
								
								Lstock.setEnabled(true);
								input_stock.setEnabled(true);
								input_stock.setText("");
								
								Lnom_fournisseur.setEnabled(true);
								input_f.setEnabled(true);
								input_f.setText("");
								
								input_recherche.setText("");
								
								//decocher les checkbox
								chb_ref.setSelected(false);
								chb_pa.setSelected(false);
								chb_stock.setSelected(false);
								chb_f.setSelected(false);
								
								//enable les checkbox
								chb_ref.setEnabled(true);
								chb_pa.setEnabled(true);
								chb_stock.setEnabled(true);
								chb_f.setEnabled(true);
								
								connection_display();
								
								Lreference.hide();
								input_ref.hide();
								
								Lprix_achat.hide();
								input_pa.hide();
								
								Lstock.hide();
								input_stock.hide();
								
								Lnom_fournisseur.hide();
								input_f.hide();
								
								label_case.hide();
								chb_ref.hide();
								lblRfrence.hide();
								chb_pa.hide();
								label_prix_achat.hide();
								chb_stock.hide();
								label_stock_bch.hide();
								chb_f.hide();
								label_nom_fournisseur.hide();
								
								btn_modif.hide();
							}
							}else {
						String query = "update produits set Stock="+input_stock.getText()+" where Reference='"+input_recherche.getText()+"'";
						Statement state =b.createStatement();
						if (JOptionPane.showConfirmDialog(null, "êtes-vous sur de modifier le stock ?") == 0) {
						state.executeUpdate(query);
						
						ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
						JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
						
						//enable les autre input
						Lreference.setEnabled(true);
						input_ref.setEnabled(true);
						input_ref.setText("");
						
						Lprix_achat.setEnabled(true);
						input_pa.setEnabled(true);
						input_pa.setText("");
						
						Lstock.setEnabled(true);
						input_stock.setEnabled(true);
						input_stock.setText("");
						
						Lnom_fournisseur.setEnabled(true);
						input_f.setEnabled(true);
						input_f.setText("");
						
						input_recherche.setText("");
						
						//decocher les checkbox
						chb_ref.setSelected(false);
						chb_pa.setSelected(false);
						chb_stock.setSelected(false);
						chb_f.setSelected(false);
						
						//enable les checkbox
						chb_ref.setEnabled(true);
						chb_pa.setEnabled(true);
						chb_stock.setEnabled(true);
						chb_f.setEnabled(true);
						
						connection_display();
						
						Lreference.hide();
						input_ref.hide();
						
						Lprix_achat.hide();
						input_pa.hide();
						
						Lstock.hide();
						input_stock.hide();
						
						Lnom_fournisseur.hide();
						input_f.hide();
						
						label_case.hide();
						chb_ref.hide();
						lblRfrence.hide();
						chb_pa.hide();
						label_prix_achat.hide();
						chb_stock.hide();
						label_stock_bch.hide();
						chb_f.hide();
						label_nom_fournisseur.hide();
						
						btn_modif.hide();
						}
						}
				
						}catch(Exception e1) {
							e1.printStackTrace();
						}
					}
					
				}else if(chb_f.isSelected() && !(input_f.getText().isEmpty()) ) {
					if(b != null) {
						try {
						String sql = "update produits set Nom_Fournisseur='"+input_f.getText()+"' where Reference='"+input_recherche.getText()+"'";
						Statement st =b.createStatement();
						if (JOptionPane.showConfirmDialog(null, "êtes-vous sur de modifier le nom de fournisseur ?") == 0) {
						st.executeUpdate(sql);
						
						ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
						JOptionPane.showMessageDialog(null, "modification bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
						
						//enable les autre input
						Lreference.setEnabled(true);
						input_ref.setEnabled(true);
						input_ref.setText("");
						
						Lprix_achat.setEnabled(true);
						input_pa.setEnabled(true);
						input_pa.setText("");
						
						Lstock.setEnabled(true);
						input_stock.setEnabled(true);
						input_stock.setText("");
						
						Lnom_fournisseur.setEnabled(true);
						input_f.setEnabled(true);
						input_f.setText("");
						
						input_recherche.setText("");
						
						//decocher les checkbox
						chb_ref.setSelected(false);
						chb_pa.setSelected(false);
						chb_stock.setSelected(false);
						chb_f.setSelected(false);
						
						//enable les checkbox
						chb_ref.setEnabled(true);
						chb_pa.setEnabled(true);
						chb_stock.setEnabled(true);
						chb_f.setEnabled(true);
						
						connection_display();
						
						Lreference.hide();
						input_ref.hide();
						
						Lprix_achat.hide();
						input_pa.hide();
						
						Lstock.hide();
						input_stock.hide();
						
						Lnom_fournisseur.hide();
						input_f.hide();
						
						label_case.hide();
						chb_ref.hide();
						lblRfrence.hide();
						chb_pa.hide();
						label_prix_achat.hide();
						chb_stock.hide();
						label_stock_bch.hide();
						chb_f.hide();
						label_nom_fournisseur.hide();
						
						btn_modif.hide();
						}
						}catch(Exception e1) {
							e1.printStackTrace();
						}
					}
					
				}else {
						if(input_recherche.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "veuillez remplir le champ de recherche","Attention",JOptionPane.WARNING_MESSAGE);
								//enable les autre input
								Lreference.setEnabled(true);
								input_ref.setEnabled(true);
								input_ref.setText("");
								
								Lprix_achat.setEnabled(true);
								input_pa.setEnabled(true);
								input_pa.setText("");
								
								Lstock.setEnabled(true);
								input_stock.setEnabled(true);
								input_stock.setText("");
								
								Lnom_fournisseur.setEnabled(true);
								input_f.setEnabled(true);
								input_f.setText("");
								
								input_recherche.setText("");
								
								//decocher les checkbox
								chb_ref.setSelected(false);
								chb_pa.setSelected(false);
								chb_stock.setSelected(false);
								chb_f.setSelected(false);
								
								//enable les checkbox
								chb_ref.setEnabled(true);
								chb_pa.setEnabled(true);
								chb_stock.setEnabled(true);
								chb_f.setEnabled(true);
								
								connection_display();
								
								Lreference.hide();
								input_ref.hide();
								
								Lprix_achat.hide();
								input_pa.hide();
								
								Lstock.hide();
								input_stock.hide();
								
								Lnom_fournisseur.hide();
								input_f.hide();
								
								label_case.hide();
								chb_ref.hide();
								lblRfrence.hide();
								chb_pa.hide();
								label_prix_achat.hide();
								chb_stock.hide();
								label_stock_bch.hide();
								chb_f.hide();
								label_nom_fournisseur.hide();
								
								btn_modif.hide();
								
								connection_display();
						}else {
								JOptionPane.showMessageDialog(null, " veuillez remplir le champ vide  ","Attention",JOptionPane.WARNING_MESSAGE);
								connection_display();
						}
						}
			}
		});
		
		//case de recherche
		input_recherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Lreference.hide();
				input_ref.hide();
				input_ref.setText("");
				
				Lprix_achat.hide();
				input_pa.hide();
				input_pa.setText("");
				
				Lstock.hide();
				input_stock.hide();
				input_stock.setText("");
				
				Lnom_fournisseur.hide();
				input_f.hide();
				input_f.setText("");
				
				label_case.hide();
				chb_ref.hide();
				lblRfrence.hide();
				chb_pa.hide();
				label_prix_achat.hide();
				chb_stock.hide();
				label_stock_bch.hide();
				chb_f.hide();
				label_nom_fournisseur.hide();
				
				chb_ref.setSelected(false);
				chb_pa.setSelected(false);
				chb_stock.setSelected(false);
				chb_f.setSelected(false);
				
				btn_modif.hide();
				connection_display();
				
			}
		});
		
		//action pour boutton supprimer
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//affichage des bouttons
				btnNewButton.hide();
				btnModifier.hide();
				btn_recherche_supp.show();
				input_recherche_supp.show();
				input_recherche_supp.setText("");
				connection_display();
				btn_retour.show();
				btn_enregistrer_supp.hide();
			}
			
		});
		
		//action de boutton retour
		btn_retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_recherche_supp.hide();
				input_recherche_supp.hide();
				input_recherche_supp.setText("");
				btnNewButton.show();
				btnModifier.show();
				btn_retour.hide();
				connection_display();
				btn_enregistrer_supp.hide();
				
			}
		});
		
		//action pour boutton recherche apres boutton supp
		btn_recherche_supp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection b=connexion_data.connecterDB();
				
				if(b != null) {
				try {
					
					String sql = "SELECT * FROM produits WHERE Reference='"+input_recherche_supp.getText()+"' AND EXISTS(SELECT Reference from produits where Reference='"+input_recherche_supp.getText()+"')";
					Statement st =b.createStatement();
					ResultSet rs=st.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					int reff=table.getModel().getRowCount();
					if(input_recherche_supp.getText().isEmpty()) {
						
						JOptionPane.showMessageDialog(null, "veuillez remplir le champ de recherche","Attention",JOptionPane.WARNING_MESSAGE);
						connection_display();
					}else if(reff==1){
						btn_enregistrer_supp.show();
					
					}else if(reff!=1){
						String[] btn_options = {"ok"};
					    JOptionPane.showOptionDialog(null,"Cette référence : "+input_recherche_supp.getText()+" n'existe pas sur BD","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);

						btn_enregistrer_supp.hide();
						connection_display();
					}
				}catch(Exception e1) {
							btn_enregistrer_supp.hide();
							connection_display();
					}
				}
				
				
			}
		});
		
		//action de la case suppression
		input_recherche_supp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_enregistrer_supp.hide();
				connection_display();
			}
			
		});
		
		
		//boutton de suppression 
		btn_enregistrer_supp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection b=connexion_data.connecterDB();
				if(b != null) {
					try {
						
						
					if(!(input_recherche_supp.getText().isEmpty()) ) {
					
					if (JOptionPane.showConfirmDialog(null, "êtes-vous sur de supprimer ?") == 0) {
						
						String sql = "delete from produits where produits.Reference='"+input_recherche_supp.getText()+"'";
						Statement st =b.createStatement();
						st.executeUpdate(sql);
						
						ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
						JOptionPane.showMessageDialog(null, "suppression bien établie","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
					
					
					btn_enregistrer_supp.hide();
					
					connection_display();
					input_recherche_supp.setText("");
					
					}
				}else {
					JOptionPane.showMessageDialog(null, "veuillez remplir le champ de recherche","Attention",JOptionPane.WARNING_MESSAGE);
				}
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
		JLabel lblimage = new JLabel("New label");
		
		JLabel lblGestionDeVente = new JLabel("GESTION DE STOCK");
		lblGestionDeVente.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionDeVente.setForeground(Color.WHITE);
		lblGestionDeVente.setFont(new Font("Dialog", Font.BOLD, 23));
		lblGestionDeVente.setBounds(254, 11, 324, 65);
		contentPane.add(lblGestionDeVente);
		
		JLabel lblNewLabel = new JLabel("GESTION DE STOCK");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(240, 11, 1099, 760);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\background_same_2.jpg"));
		lblNewLabel.setBounds(0, 0, 896, 750);
		contentPane.add(lblNewLabel);
		
		table_1 = new JTable();
		table_1.setBounds(173, 35, 1, 1);
		contentPane.add(table_1);
		
		//pour l'affichage de la base donnee dans le tableau (jtable avec l'ajout d'un fichier xml)
	
		Connection b=connexion_data.connecterDB();
		if(b != null) {
			try {
			String sql = "select * from produits";
			Statement st =b.createStatement();
			ResultSet rs=st.executeQuery(sql);
			table.setModel(DbUtils.resultSetToTableModel(rs));

			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
					
	}
	public static void connection_display(){
		Connection b=connexion_data.connecterDB();
		if(b != null) {
			try {
			String sql = "select * from produits";
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
