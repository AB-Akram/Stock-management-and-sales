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
import javax.swing.table.DefaultTableModel;

import gestion_de_stock.connexion_data;
import gestion_de_stock.Produits;
import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vente extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JTextField input_ref;
	private JTextField input_pa;
	private JTextField input_pv;
	private JTextField input_qt;
	private JTable table_1;
	private JTextField input_recherche;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vente frame = new vente();
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
	public vente() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 20, 902, 744);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(null);
	setContentPane(contentPane);
	contentPane.setLayout(null);
		
		JButton btn_retour_acceuil = new JButton("<--");
		btn_retour_acceuil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Acceuil home=new Acceuil();
				home.setVisible(true);
				vente.this.dispose();
				connexion_data.closeconnexion();
			}
			
		});
		
		JLabel label_facturation = new JLabel("Facturation");
		label_facturation.setForeground(Color.WHITE);
		label_facturation.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_facturation.setHorizontalAlignment(SwingConstants.CENTER);
		label_facturation.setBounds(726, 665, 150, 29);
		contentPane.add(label_facturation);
		
		JButton btnfacturation = new JButton("New button");
		btnfacturation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				facturation fact = new facturation();
				fact.setVisible(true);
				vente.this.dispose();
			}
		});
		btnfacturation.setForeground(Color.WHITE);
		btnfacturation.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\facture_2.png"));
		btnfacturation.setBounds(705, 556, 191, 110);
		contentPane.add(btnfacturation);
		
		JButton btn_supp = new JButton("Supprimer");
		btn_supp.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_supp.setBounds(492, 356, 122, 47);
		contentPane.add(btn_supp);
		
		input_recherche = new JTextField();
		input_recherche.setToolTipText("");
		input_recherche.setColumns(10);
		input_recherche.setBounds(649, 363, 176, 38);
		contentPane.add(input_recherche);
		
		input_recherche.hide();
		
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.setFont(new Font("Dialog", Font.BOLD, 16));
		btnRecherche.setBounds(505, 356, 122, 47);
		contentPane.add(btnRecherche);
		
		btnRecherche.hide();
		
		JPanel my_panel = new JPanel();
		my_panel.setLayout(null);
		my_panel.setForeground(Color.BLACK);
		my_panel.setBounds(50, 414, 646, 269);
		contentPane.add(my_panel);
		my_panel.hide();
		
		JLabel label_case = new JLabel("veuillez cocher une seule case :\r\n");
		label_case.setForeground(Color.RED);
		label_case.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_case.setBounds(183, 11, 308, 14);
		my_panel.add(label_case);
		
		JLabel label_pv_chb = new JLabel("  Prix_vente");
		label_pv_chb.setBounds(65, 53, 79, 14);
		my_panel.add(label_pv_chb);
		
		JLabel Lreference = new JLabel("Référence :");
		Lreference.setHorizontalAlignment(SwingConstants.CENTER);
		Lreference.setFont(new Font("Tahoma", Font.BOLD, 14));
		Lreference.setBounds(22, 97, 105, 31);
		my_panel.add(Lreference);
		
		JLabel Lprix_achat = new JLabel("Prix Achat :");
		Lprix_achat.setHorizontalAlignment(SwingConstants.CENTER);
		Lprix_achat.setFont(new Font("Tahoma", Font.BOLD, 14));
		Lprix_achat.setBounds(22, 166, 93, 31);
		my_panel.add(Lprix_achat);
		Lprix_achat.setEnabled(false);
		
		JLabel Lpv = new JLabel("Prix Vente :");
		Lpv.setHorizontalAlignment(SwingConstants.CENTER);
		Lpv.setFont(new Font("Tahoma", Font.BOLD, 14));
		Lpv.setBounds(349, 97, 93, 31);
		my_panel.add(Lpv);
		
		JLabel Lquantite = new JLabel("Quantit\u00E9 :");
		Lquantite.setHorizontalAlignment(SwingConstants.CENTER);
		Lquantite.setFont(new Font("Tahoma", Font.BOLD, 14));
		Lquantite.setBounds(349, 166, 128, 31);
		my_panel.add(Lquantite);
		
		JButton btn_modif_2 = new JButton("Modification");
		btn_modif_2.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_modif_2.setBounds(252, 222, 146, 47);
		my_panel.add(btn_modif_2);
		
		input_ref = new JTextField();
		input_ref.setColumns(10);
		input_ref.setBounds(137, 104, 86, 20);
		my_panel.add(input_ref);
		
		input_pa = new JTextField();
		input_pa.setColumns(10);
		input_pa.setBounds(137, 173, 86, 20);
		my_panel.add(input_pa);
		input_pa.setEnabled(false);
		
		input_pv = new JTextField();
		input_pv.setText("0");
		input_pv.setColumns(10);
		input_pv.setBounds(476, 104, 105, 20);
		my_panel.add(input_pv);
		
		input_qt = new JTextField();
		input_qt.setText("0");
		input_qt.setColumns(10);
		input_qt.setBounds(505, 173, 106, 20);
		my_panel.add(input_qt);
		
		JCheckBox chb_pv = new JCheckBox("");
		chb_pv.setBounds(47, 49, 21, 23);
		my_panel.add(chb_pv);
		
		//boutton d'enregistrement apres l'ajout 
		JButton btnEnregistrer = new JButton("Enregistrer");
		
		btnEnregistrer.setFont(new Font("Dialog", Font.BOLD, 16));
		btnEnregistrer.setBounds(241, 222, 120, 47);
		my_panel.add(btnEnregistrer);
		
		JLabel label_ref = new JLabel("Veuillez remplir cette case");
		label_ref.setForeground(Color.RED);
		label_ref.setBounds(87, 79, 230, 14);
		my_panel.add(label_ref);
		
		JLabel label_f = new JLabel("Veuillez remplir cette case");
		label_f.setForeground(Color.RED);
		label_f.setBounds(400, 148, 236, 14);
		my_panel.add(label_f);
		
		JButton btnNewButton_1_1 = new JButton("X");
		btnNewButton_1_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton_1_1.setBackground(Color.RED);
		btnNewButton_1_1.setBounds(589, 9, 47, 31);
		my_panel.add(btnNewButton_1_1);
		
		JLabel label_qt_bch = new JLabel("  Quantité");
		label_qt_bch.setBounds(395, 57, 82, 14);
		my_panel.add(label_qt_bch);
		
		JCheckBox chb_qt = new JCheckBox("");
		chb_qt.setBounds(377, 53, 21, 23);
		my_panel.add(chb_qt);
		
		JButton btn_modif = new JButton("Modifier");
		btn_modif.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_modif.setBounds(285, 356, 122, 47);
		contentPane.add(btn_modif);
		
		//action de boutton ajouter
		JButton btn_ajout = new JButton("Ajouter");
		btn_ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				my_panel.show();
				btn_modif.hide();
				label_ref.hide();;
				label_f.hide();
				label_case.hide();
				btn_modif.hide();
				btnEnregistrer.show();
				
				//afficher les input
				Lreference.show();
				input_ref.show();
				Lprix_achat.show();
				Lprix_achat.setEnabled(false);
				input_pa.show();
				input_pa.setEnabled(false);
				Lpv.show();
				input_pv.show();
				Lquantite.show();
				input_qt.show();
				
				//enable les input
				Lreference.setEnabled(true);
				input_ref.setEnabled(true);
				input_ref.setText("");
				
				Lprix_achat.setEnabled(false);
				input_pa.setEnabled(false);
				
				Lpv.setEnabled(true);
				input_pv.setEnabled(true);
				input_pv.setText("0");
				
				Lquantite.setEnabled(true);
				input_qt.setEnabled(true);
				input_qt.setText("0");
				
				//cacher les checkbox
				chb_pv.hide();
				label_pv_chb.hide();
				chb_pv.setEnabled(true);
				chb_pv.setSelected(false);
				
				chb_qt.hide();
				label_qt_bch.hide();
				chb_qt.setEnabled(true);
				chb_qt.setSelected(false);
				
				btn_modif_2.hide();
				
				//cacher boutton recherche de modifier
				btnRecherche.hide();
				input_recherche.hide();
				
				btnfacturation.hide();
				label_facturation.hide();
				btn_supp.hide();
				
				connection_display();
			}
		});
		
		btn_ajout.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_ajout.setBounds(103, 356, 89, 47);
		contentPane.add(btn_ajout);
		
		//boutton pour exit
				btnNewButton_1_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//apres la sortie on affiche les trois boutton ajout et modif et suppr
						
						my_panel.hide();
						btn_ajout.show();
						btn_modif.show();
						
						//et on vide toute les case qui se trouve sur le deuxieme panel apres on cliquant sur le boutton ajouter
						input_ref.setText("");
						input_pv.setText("0");
						input_qt.setText("");
						
						//pour la sortie o cache les bouton qui appartient au modif comme btn recherche...
						label_case.hide();
						
						chb_pv.hide();
						label_pv_chb.hide();
						chb_pv.setEnabled(true);
						chb_pv.setSelected(false);
						
						chb_qt.hide();
						label_qt_bch.hide();
						chb_qt.setEnabled(true);
						chb_qt.setSelected(false);
						
						btnRecherche.hide();
						input_recherche.hide();
						
						btnfacturation.show();
						label_facturation.show();
						btn_supp.show();
						connection_display();
						
					}
				});
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(32, 87, 753, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		btn_retour_acceuil.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_retour_acceuil.setBounds(10, 11, 56, 38);
		contentPane.add(btn_retour_acceuil);
		
		JLabel lblGestionDeVente = new JLabel("GESTION DE VENTE");
		lblGestionDeVente.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionDeVente.setForeground(Color.WHITE);
		lblGestionDeVente.setFont(new Font("Dialog", Font.BOLD, 23));
		lblGestionDeVente.setBounds(240, 11, 324, 65);
		contentPane.add(lblGestionDeVente);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\background_same_2.jpg"));
		lblNewLabel.setBounds(0, 0, 896, 750);
		contentPane.add(lblNewLabel);
		
		table_1 = new JTable();
		table_1.setBounds(629, 26, 1, 1);
		contentPane.add(table_1);
		table_1.hide();
		
		
		//boutton enregistre apres le boutton ajouter
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection b=connexion_data.connecterDB();
				
				String Reference = input_ref.getText();
				String pv = input_pv.getText();
				String quantite = input_qt.getText();
				int qtt=Integer.parseInt(quantite);
				Double pvv=Double.parseDouble(pv);
				
				if(b != null) {
					
						//String sql ="insert into produits (Reference,Prix achat,Stock,Nom Fournisseur)values('"+input_ref.getText()+"',"+input_pa.getText()+","+input_stock.getText()+",'"+input_f.getText()+"')";
						
						if(input_ref.getText().isEmpty() && input_qt.getText().isEmpty() ) {
							label_ref.show();
							label_f.show();
						}else if(input_ref.getText().isEmpty()) {
							label_ref.show();
						}else if(input_qt.getText().isEmpty()) {
							label_f.show();
						}else if( !(input_qt.getText().isEmpty() ) && !(input_ref.getText().isEmpty()) ){
							label_ref.hide();
							label_f.hide();
							
							try {
						
						 
						String sql_2="Select * from produits where Reference='"+Reference+"'";
						Statement pst=b.createStatement();	
						ResultSet rss=pst.executeQuery(sql_2);
						
						table_1.setModel(DbUtils.resultSetToTableModel(rss));
						int reff=table_1.getModel().getRowCount();
					
				
						if(reff==1) {
							
							String sqll="SELECT Stock from produits where Reference='"+input_ref.getText()+"'";
							Statement stt =b.createStatement();
							ResultSet rs_2=stt.executeQuery(sqll);
							table_1.setModel(DbUtils.resultSetToTableModel(rs_2));
							
							String ref=table_1.getModel().getValueAt(0,0).toString();
							int stock=Integer.parseInt(ref);
							int qt=Integer.parseInt(input_qt.getText());
							
							if(stock >= qt) {
								
								String sql_ref="Select @ref := Reference from produits where Reference ='"+Reference+"'";
								Statement st_ref=b.createStatement();
								ResultSet rs_ref=st_ref.executeQuery(sql_ref);
								
								String sql_pa="Select @pa :=  Prix_achat from produits where Reference='"+Reference+"'";
								Statement st_pa=b.createStatement();
								ResultSet rs_pa=st_pa.executeQuery(sql_pa);
								
								
								String sql="INSERT INTO vente(Reference,Prix_achat,Prix_vente,Quantite,Benefice) VALUES(@ref,@pa,"+pvv+","+qtt+",(("+pvv+"*"+qtt+")-(@pa*"+qtt+")) )";
							//String sql ="CALL verification_ref((Select Reference from produits where Reference ='"+Reference+"'),(SELECT Prix_achat from produits where Reference='"+Reference+"'),"+pv+","+quantite+",@out);";
							
							
							Statement st =b.createStatement();
							st.executeUpdate(sql);
							
							String sql_stock="UPDATE produits set Stock=(Stock -"+qtt+") where Reference='"+Reference+"'";
							Statement st_stock=b.createStatement();
							st_stock.executeUpdate(sql_stock);
							
							
							ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
							JOptionPane.showMessageDialog(null, "Ajout bien établi","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
							
							String sql_22 = "select * from vente";
							Statement pstt =b.createStatement();
							ResultSet rs=pstt.executeQuery(sql_22);
							table.setModel(DbUtils.resultSetToTableModel(rs));
							input_ref.setText("");
							input_pv.setText("0");
							input_qt.setText("0"); 
							
							}else {
								JOptionPane.showMessageDialog(null, "vous n'avez pas assez de stock pour cette quantité");
								
							}
							
						
						
						}else {
							
							String[] btn_options = {"ok"};
						    JOptionPane.showOptionDialog(null,"erreur cette Référence : "+input_ref.getText()+" n'existe pas","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);

								connection_display();
							}
							
							}catch(Exception e1) {
								e1.printStackTrace();
							}
							}
						
						
						/*String sql = "update produits set Prix_achat="+input_pa.getText()+" where Reference='"+input_recherche.getText()+"'";
						Statement st =b.createStatement();
						if (JOptionPane.showConfirmDialog(null, "etes vous sur de modifier prix d'achat ?") == 0) {
						st.executeUpdate(sql);
						JOptionPane.showMessageDialog(my_panel, "modification est bien établie");*/
						
						
					}		
					
			}		
		});
		
		//action pour boutton modifier
		btn_modif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				my_panel.show();
				btnEnregistrer.hide();
				label_ref.hide();;
				label_f.hide();
				btn_modif_2.hide();
				btn_ajout.hide();
				Lreference.hide();
				input_ref.hide();
				Lprix_achat.hide();
				input_pa.hide();
				Lpv.hide();
				input_pv.hide();
				Lquantite.hide();
				input_qt.hide();
				
				
				input_recherche.show();
				input_recherche.setText("");
				btnRecherche.show();
				
				//cacher les cases
				label_case.hide();
				
				chb_pv.hide();
				label_pv_chb.hide();
				chb_pv.setEnabled(true);
				chb_pv.setSelected(false);
				
				chb_qt.hide();
				label_qt_bch.hide();
				chb_qt.setEnabled(true);
				chb_qt.setSelected(false);
				
				btnfacturation.hide();
				label_facturation.hide();
				
				btn_supp.hide();
				
				connection_display();
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
						
						Lpv.hide();
						input_pv.hide();
						input_pv.setText("");
						
						Lquantite.hide();
						input_qt.hide();
						input_qt.setText("");
						
						label_case.hide();
						chb_pv.hide();
						label_pv_chb.hide();
						chb_pv.setEnabled(true);
						chb_pv.setSelected(false);
						
						chb_qt.hide();
						label_qt_bch.hide();
						chb_qt.setEnabled(true);
						chb_qt.setSelected(false);
						
						btn_modif_2.hide();
						connection_display();
						
					}
				});
				
				
				//boutton pour la recherche
				btnRecherche.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						//on les affiches
						label_case.show();
						
						chb_pv.show();
						label_pv_chb.show();
						chb_pv.setEnabled(true);
						chb_pv.setSelected(false);
						
						chb_qt.show();
						label_qt_bch.show();
						chb_qt.setEnabled(true);
						chb_qt.setSelected(false);
						
						btn_modif_2.hide();
						
						// on les caches 
						Lreference.hide();
						input_ref.hide();
						
						Lprix_achat.hide();
						input_pa.hide();
						
						Lquantite.hide();
						input_qt.hide();
						input_qt.setText("0");
						
						Lpv.hide();
						input_pv.hide();
						input_pv.setText("0");
						
						
						if(!(input_recherche.getText().isEmpty()) ) {
							Connection b=connexion_data.connecterDB();
							
							if(b != null) {
							try {
								String sql = "SELECT * FROM vente WHERE Reference='"+input_recherche.getText()+"' AND EXISTS(SELECT Reference from vente where Reference='"+input_recherche.getText()+"')";
								
										/*String sql = "select * "
											+ "from produits "
											+ "where Reference ='"+input_recherche.getText()+"'";
								*/
								
								Statement st =b.createStatement();
								ResultSet rs=st.executeQuery(sql);
								table.setModel(DbUtils.resultSetToTableModel(rs));
								
								String ref=table.getModel().getValueAt(0,0).toString();
								int reff=table.getModel().getRowCount();
								
								
								
							if(reff >= 1) {
								//on les affiches
								label_case.show();
								
								chb_pv.show();
								label_pv_chb.show();
								chb_pv.setEnabled(true);
								chb_pv.setSelected(false);
								
								chb_qt.show();
								label_qt_bch.show();
								chb_qt.setEnabled(true);
								chb_qt.setSelected(false);
								
								btn_modif_2.hide();
								
							}
								}catch(Exception e1) {
									
									String[] btn_options = {"ok"};
								    JOptionPane.showOptionDialog(null,"erreur cette Référence : "+input_recherche.getText()+" n'existe pas","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);
									
									label_case.hide();
									chb_pv.hide();
									label_pv_chb.hide();
									chb_pv.setEnabled(true);
									chb_pv.setSelected(false);
									
									chb_qt.hide();
									label_qt_bch.hide();
									chb_qt.setEnabled(true);
									chb_qt.setSelected(false);
									
									btn_modif_2.hide();
							
							
									connection_display();
								}
							
							}
							
					}else {
							JOptionPane.showMessageDialog(null, "veuillez remplir le champ de recherche","Attention",JOptionPane.WARNING_MESSAGE);
					
							label_case.hide();
							chb_pv.hide();
							label_pv_chb.hide();
							chb_pv.setEnabled(true);
							chb_pv.setSelected(false);
							
							chb_qt.hide();
							label_qt_bch.hide();
							chb_qt.setEnabled(true);
							chb_qt.setSelected(false);
							
							btn_modif_2.hide();
					
					
					connection_display();
					}
					}
				});
				
				
				
				//checkbox prix_vente checked
				chb_pv.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if(chb_pv.isSelected()) {
							//disabled les chckbox
							chb_qt.hide();
							
							//disabled les autres input
							Lprix_achat.setEnabled(false);
							input_pa.setEnabled(false);
							
							Lreference.setEnabled(false);
							input_ref.setEnabled(false);
							
							Lquantite.setEnabled(false);
							input_qt.setEnabled(false);
							
							Lreference.show();
							input_ref.show();
							
							Lprix_achat.show();
							input_pa.show();
							
							Lpv.show();
							input_pv.show();
							input_pv.setText("0");
							input_pv.setEnabled(true);
							Lpv.setEnabled(true);
							
							Lquantite.show();
							input_qt.show();
							
							btn_modif_2.show();
							
							
							
							
						}else{
							//enable les checkbox
							chb_qt.show();
							chb_qt.setEnabled(true);
							
							//enable les autre input
							Lprix_achat.setEnabled(true);
							input_pa.setEnabled(true);
							
							Lreference.setEnabled(true);
							input_ref.setEnabled(true);
							
							Lquantite.setEnabled(true);
							input_qt.setEnabled(true);
							
							Lreference.hide();
							input_ref.hide();
							
							Lprix_achat.hide();
							input_pa.hide();
							
							Lquantite.hide();
							input_qt.hide();
							
							Lpv.hide();
							input_pv.hide();
							input_pv.setText("0");
							
							btn_modif_2.hide();
						}
					}
				});
				
				//checkbox quantite checked
				chb_qt.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if(chb_qt.isSelected()) {
							//disabled les chckbox
							chb_pv.hide();
							
							//disabled les autres input
							Lprix_achat.setEnabled(false);
							input_pa.setEnabled(false);
							
							Lreference.setEnabled(false);
							input_ref.setEnabled(false);
							
							Lquantite.setEnabled(true);
							input_qt.setEnabled(true);
							
							Lpv.setEnabled(false);
							input_pv.setEnabled(false);
							
							Lreference.show();
							input_ref.show();
							
							Lprix_achat.show();
							input_pa.show();
							input_qt.setText("0");
							
							Lpv.show();
							input_pv.show();
							
							Lquantite.show();
							Lquantite.setEnabled(true);
							input_qt.setEnabled(true);
							input_qt.show();
							
							btn_modif_2.show();
							
							
							
							
						}else{
							//enable les checkbox
							chb_pv.show();
							chb_pv.setEnabled(true);
							
							//enable les autre input
							Lprix_achat.setEnabled(true);
							input_pa.setEnabled(true);
							
							Lreference.setEnabled(true);
							input_ref.setEnabled(true);
							
							Lpv.setEnabled(true);
							input_pv.setEnabled(true);
							
							Lreference.hide();
							input_ref.hide();
							
							Lprix_achat.hide();
							input_pa.hide();
							
							Lquantite.hide();
							input_qt.hide();
							input_qt.setText("0");
							Lpv.hide();
							input_pv.hide();
							input_pv.setText("0");
							
							btn_modif_2.hide();
						}
					}
				});
				
				//action pour boutton enregistrement de modification 
				btn_modif_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						Connection b=connexion_data.connecterDB();
						
						if (table.getSelectionModel().isSelectionEmpty()) {
							JOptionPane.showMessageDialog(null, "veuillez sélectionner une seule ligne de la table ");
						
						}else if(input_recherche.getText().isEmpty()){
							
							JOptionPane.showMessageDialog(null, "veuillez remplir le champ de recherche  ","Attention",JOptionPane.WARNING_MESSAGE);
							label_case.hide();
							
							chb_pv.hide();
							label_pv_chb.hide();
							chb_pv.setEnabled(true);
							chb_pv.setSelected(false);
							
							chb_qt.hide();
							label_qt_bch.hide();
							chb_qt.setEnabled(true);
							chb_qt.setSelected(false);
							
							
							// on les caches 
							Lreference.hide();
							input_ref.hide();
							
							Lprix_achat.hide();
							input_pa.hide();
							
							Lquantite.hide();
							input_qt.hide();
							input_qt.setText("0");
							
							Lpv.hide();
							input_pv.hide();
							input_pv.setText("0");
							
							btn_modif_2.hide();
					
					
							connection_display();

						}else if(b != null) {
						int ligne = table.getSelectedRow();
						
						String pv = table.getModel().getValueAt(ligne, 2).toString();
						String qt = table.getModel().getValueAt(ligne, 3).toString();
						String pa =table.getModel().getValueAt(ligne, 1).toString();
						String lg = table.getModel().getValueAt(ligne, 5).toString();
						
						Double pvv = Double.parseDouble(pv);
						Double paa = Double.parseDouble(pa);
						int qtt=Integer.parseInt(qt);
						int lgg=Integer.parseInt(lg);
							//pour Prix de vente
							if(!(input_pv.getText().isEmpty()) && chb_pv.isSelected()) {
								
							try {
								
								String query="SELECT Reference from produits where Reference='"+input_recherche.getText()+"'";
								Statement state=b.createStatement();
								ResultSet resultat=state.executeQuery(query);
								table_1.setModel(DbUtils.resultSetToTableModel(resultat));
								int existe = table_1.getRowCount();
								
								if(existe==1) {
								
								if(JOptionPane.showConfirmDialog(null, " vous voulez modifier ?")==0) {
							String sql = "update vente set Prix_vente="+input_pv.getText()+" where Reference='"+input_recherche.getText()+"' and ligne="+lgg;
							Statement st =b.createStatement();
							st.executeUpdate(sql);
							
							String sql_2 = "update vente set Benefice=("+input_pv.getText()+"*"+qtt+")-("+paa+"*"+qtt+") where Reference='"+input_recherche.getText()+"' and ligne="+lgg;
							Statement pst =b.createStatement();
							pst.executeUpdate(sql_2);
							
							ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
							JOptionPane.showMessageDialog(null, " modification bien établie ","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
							
							input_pv.hide();
							input_pv.setText("0");
							Lpv.hide();
							
							input_ref.hide();
							Lreference.hide();
							
							Lprix_achat.hide();
							input_pa.hide();
							
							Lquantite.hide();
							input_qt.hide();
							
							chb_pv.setSelected(false);
							chb_pv.hide();
							label_pv_chb.hide();
							
							chb_qt.hide();
							label_qt_bch.hide();
							
							input_recherche.setText("");
							
							label_case.hide();
							btn_modif_2.hide();
							connection_display();
							
							}
								}else {
									String[] btn_options = {"ok"};
								    JOptionPane.showOptionDialog(null,"cette référence : "+input_recherche.getText()+" n'existe pas sur gestion de stock","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);

								}
							}catch(Exception e1) {
								e1.printStackTrace();
							}
							
						}else if ( input_pv.getText().isEmpty() && chb_pv.isSelected()){
							JOptionPane.showMessageDialog(null, "remplir le champ de Prix de vente","Attention",JOptionPane.WARNING_MESSAGE);
						}
							
							
							
							//pour quantite
							if(!(input_qt.getText().isEmpty()) && chb_qt.isSelected()) { 
								
								try {
									
									String query="SELECT Reference from produits where Reference='"+input_recherche.getText()+"'";
									Statement state=b.createStatement();
									ResultSet resultat=state.executeQuery(query);
									table_1.setModel(DbUtils.resultSetToTableModel(resultat));
									int existe = table_1.getRowCount();
									
									if(existe==1) {
									
									if (JOptionPane.showConfirmDialog(null, " vous voulez modifier ?")==0) {
										
										String sqll="SELECT Stock from produits where Reference='"+input_recherche.getText()+"'";
										Statement stt =b.createStatement();
										ResultSet rss=stt.executeQuery(sqll);
										table_1.setModel(DbUtils.resultSetToTableModel(rss));
										
										String ref=table_1.getModel().getValueAt(0,0).toString();
										int stock=Integer.parseInt(ref);
										int quantite=Integer.parseInt(input_qt.getText());
									
								if(stock >= quantite ) {
										
								String sql = "update vente set Quantite="+input_qt.getText()+" where Reference='"+input_recherche.getText()+"' and ligne="+lgg;
								Statement st =b.createStatement();
								st.executeUpdate(sql);
								
								String sql_2 = "update produits set Stock=(Stock +"+qtt+")-"+input_qt.getText()+" where Reference='"+input_recherche.getText()+"'";
										Statement pst_2 =b.createStatement();
										pst_2.executeUpdate(sql_2);
								
								String sql_3 = "update vente set Benefice=("+pvv+"*"+input_qt.getText()+")-("+paa+"*"+input_qt.getText()+") where Reference='"+input_recherche.getText()+"' and ligne="+lgg;
								Statement pst_3 =b.createStatement();
								pst_3.executeUpdate(sql_3);
								
								ImageIcon my_icon = new ImageIcon("C:/Users/seddik/Desktop/app/image/verification_2.png");
								JOptionPane.showMessageDialog(null, " modification  bien établie ","Succès",JOptionPane.INFORMATION_MESSAGE,my_icon);
								
								input_pv.hide();
								input_pv.setText("0");
								Lpv.hide();
								
								input_ref.hide();
								Lreference.hide();
								
								Lprix_achat.hide();
								input_pa.hide();
								
								Lquantite.hide();
								input_qt.setText("0");
								input_qt.hide();
								
								chb_pv.setSelected(false);
								chb_pv.hide();
								label_pv_chb.hide();
								
								chb_qt.setSelected(false);
								chb_qt.hide();
								label_qt_bch.hide();
								
								input_recherche.setText("");
								
								label_case.hide();
								btn_modif_2.hide();
								connection_display();
								
								}else {
										JOptionPane.showMessageDialog(null, "vous n'avez pas assez de stock pour cette quantité");
									}
									
								}
									
								}else {
									String[] btn_options = {"ok"};
								    JOptionPane.showOptionDialog(null,"cette référence : "+input_recherche.getText()+" n'existe pas sur gestion de stock","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);
								    
								}
									
								}catch(Exception e1) {
									e1.printStackTrace();
								}
							}else if(input_qt.getText().isEmpty() && chb_qt.isSelected()){
								JOptionPane.showMessageDialog(null, "remplir le champ de Quantité","Attention",JOptionPane.WARNING_MESSAGE);
							}
					}
						
					
					}
				});
				
				//mousse clicked pour la table
				scrollPane.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						}
				});
				
				
				//suppression d'une ligne 
				btn_supp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Connection b=connexion_data.connecterDB();
						
						if (table.getSelectionModel().isSelectionEmpty()) {
							JOptionPane.showMessageDialog(null, "veuillez sélectionner une seule ligne de la table ");
						}else if(b != null) {
						int ligne = table.getSelectedRow();
						String ref = table.getModel().getValueAt(ligne, 0).toString();
						String pv = table.getModel().getValueAt(ligne, 2).toString();
						String qt = table.getModel().getValueAt(ligne, 3).toString();
						String pa =table.getModel().getValueAt(ligne, 1).toString();
						String lg = table.getModel().getValueAt(ligne, 5).toString();
						
						Double pvv = Double.parseDouble(pv);
						Double paa = Double.parseDouble(pa);
						int qtt=Integer.parseInt(qt);
						int lgg=Integer.parseInt(lg);
						int num=0;
						
					
							
							
							try {
								if(JOptionPane.showConfirmDialog(null, " vous voulez supprimer ?")==0) {
									String sql = "delete from vente where vente.Reference='"+ref+"'and ligne="+lgg;
								
									Statement st =b.createStatement();
									st.executeUpdate(sql);
									
									String sql_ligne="SET @num := 0";
									Statement stmt=b.createStatement();
									stmt.executeUpdate(sql_ligne);
									
									String sql_ligne_3="UPDATE vente SET ligne=@num := (@num+1);";
									Statement stmt_3=b.createStatement();
									stmt.executeUpdate(sql_ligne_3);
									
									String sql_ligne_2="ALTER TABLE vente AUTO_INCREMENT = 1";
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
				
		
		//pour l'affichage de la base donnee dans le tableau (jtable avec l'ajout d'un fichier xml)
		
		Connection b=connexion_data.connecterDB();
		if(b != null) {
			try {
			String sql = "select * from vente";
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
			String sql = "select * from vente";
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
