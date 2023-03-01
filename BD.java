package gestion_de_stock;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.Resultset;

import javax.swing.JTable;
import gestion_de_stock.connexion_data;
import gestion_de_stock.Produits;
import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class BD extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JButton btnRecherche;
	private JTextField input_recherche;
	private JButton btn_retour_acceuil;
	private JLabel lblBaseDeDonnes;
	private JScrollPane scrollPane;
	private JLabel lblGestionDeVente;
	private static JTable table_1;
	private JTextField input_recherche_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BD frame = new BD();
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
	public BD() {
		setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(250, 20, 902, 763);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//action de retour a l'acceuil
		btn_retour_acceuil = new JButton("<--");
		btn_retour_acceuil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Acceuil ac=new Acceuil();
				ac.setVisible(true);
				BD.this.dispose();
				connexion_data.closeconnexion();
			}
		});
		
		input_recherche_2 = new JTextField();
		input_recherche_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				connection_display2();
			}
		});
		input_recherche_2.setToolTipText("");
		input_recherche_2.setText("");
		input_recherche_2.setColumns(10);
		input_recherche_2.setBounds(700, 423, 176, 38);
		contentPane.add(input_recherche_2);
		
		JButton btnRecherche_2 = new JButton("Recherche");
		btnRecherche_2.setFont(new Font("Dialog", Font.BOLD, 16));
		btnRecherche_2.setBounds(566, 416, 122, 47);
		contentPane.add(btnRecherche_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(52, 490, 781, 217);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		lblGestionDeVente = new JLabel("LISTE DE VENTES");
		lblGestionDeVente.setHorizontalAlignment(SwingConstants.CENTER);
		lblGestionDeVente.setForeground(Color.WHITE);
		lblGestionDeVente.setFont(new Font("Dialog", Font.BOLD, 23));
		lblGestionDeVente.setBounds(254, 344, 324, 79);
		contentPane.add(lblGestionDeVente);
		
		lblBaseDeDonnes = new JLabel("LISTE DE PRODUITS");
		lblBaseDeDonnes.setHorizontalAlignment(SwingConstants.CENTER);
		lblBaseDeDonnes.setForeground(Color.WHITE);
		lblBaseDeDonnes.setFont(new Font("Dialog", Font.BOLD, 23));
		lblBaseDeDonnes.setBounds(242, 1, 324, 79);
		contentPane.add(lblBaseDeDonnes);
		btn_retour_acceuil.setFont(new Font("Dialog", Font.BOLD, 16));
		btn_retour_acceuil.setBounds(10, 23, 56, 38);
		contentPane.add(btn_retour_acceuil);
		
		//boutton de recherche
		btnRecherche = new JButton("Recherche");
		btnRecherche.setFont(new Font("Dialog", Font.BOLD, 16));
		btnRecherche.setBounds(554, 80, 122, 47);
		contentPane.add(btnRecherche);
		
		//case de recherche 
		input_recherche = new JTextField();
		input_recherche.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				connection_display1();
			}
		});
		input_recherche.setToolTipText("");
		input_recherche.setColumns(10);
		input_recherche.setBounds(688, 87, 176, 38);
		contentPane.add(input_recherche);
		input_recherche.setText("");
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 138, 781, 195);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\background_same_2.jpg"));
		lblNewLabel.setBounds(0, 1, 906, 760);
		contentPane.add(lblNewLabel);
		
		//action de boutton de recherche pour stock
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Connection b=connexion_data.connecterDB();
				
				if(b != null) {
				try {
					String sql = "SELECT * FROM produits WHERE Reference='"+input_recherche.getText()+"' AND EXISTS(SELECT Reference from produits where Reference='"+input_recherche.getText()+"')";
					
					Statement st =b.createStatement();
					ResultSet rs=st.executeQuery(sql);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					 Produits p=new Produits();
					 
					int reff=table.getModel().getRowCount();
					if(input_recherche.getText().isEmpty()) {
						
						JOptionPane.showMessageDialog(null, "veuillez remplir le champ de recherche","Attention",JOptionPane.WARNING_MESSAGE);
						connection_display1();
					
					}else if(reff!=1){
						String[] btn_options = {"ok"};
					    JOptionPane.showOptionDialog(null,"Cette référence : "+input_recherche.getText()+"  n'existe pas sur BD","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);
						connection_display1();
						
					}
				}catch(Exception e1) {
					connection_display1();
					}
				}
			}
		});
		
		//action de boutton de recherche pour vente
				btnRecherche_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						Connection b=connexion_data.connecterDB();
						
						if(b != null) {
						try {
							String sql = "SELECT * FROM vente WHERE Reference='"+input_recherche_2.getText()+"'";
							Statement st =b.createStatement();
							ResultSet rs=st.executeQuery(sql);
							table_1.setModel(DbUtils.resultSetToTableModel(rs));
							
							int reff=table_1.getModel().getRowCount();
							
							
							if(input_recherche_2.getText().isEmpty()) {
								
								JOptionPane.showMessageDialog(null, "veuillez remplir le champ de recherche","Attention",JOptionPane.WARNING_MESSAGE);
								connection_display2();
							
							}else if(reff >= 1){
								String sql_2 = "SELECT * FROM vente WHERE Reference='"+input_recherche_2.getText()+"'";
								Statement pst =b.createStatement();
								ResultSet rss=pst.executeQuery(sql_2);
								table_1.setModel(DbUtils.resultSetToTableModel(rss));
								
							}else if(reff!=1){
								String[] btn_options = {"ok"};
							    JOptionPane.showOptionDialog(null,"Cette référence : "+input_recherche_2.getText()+"  n'existe pas sur BD","ERREUR", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,btn_options,0);
								connection_display2();
								
							}
						}catch(Exception e1) {
							connection_display2();
							}
						}
					}
				});
				
		
		//affichage de la base de donnee
		Connection b=connexion_data.connecterDB();
		if(b != null) {
			try {
			String sql = "select * from produits";
			String sql_2="select * from vente";
			Statement st =b.createStatement();
			ResultSet rs=st.executeQuery(sql);
			
			Statement st_2 =b.createStatement();
			ResultSet rs_2=st_2.executeQuery(sql_2);
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			table_1.setModel(DbUtils.resultSetToTableModel(rs_2));
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		
			
			
	}
		public static void connection_display1(){
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
		
		
		public static void connection_display2(){
			Connection b=connexion_data.connecterDB();
			if(b != null) {
				try {
				String sql = "select * from vente";
				Statement st =b.createStatement();
				ResultSet rs=st.executeQuery(sql);
				//sur import faut faire static pour tabel
				table_1.setModel(DbUtils.resultSetToTableModel(rs));

				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		}
}
