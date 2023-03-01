package gestion_de_stock;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import gestion_de_stock.connexion_data;

public class Acceuil extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Acceuil frame = new Acceuil();
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
	public Acceuil() {
		setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(250, 20, 902, 715);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Gestion de Stock");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\image_stock.jpg"));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection b = connexion_data.connecterDB();
				
				if(b!=null) {
				Produits prd=new Produits();
				prd.setVisible(true);
				Acceuil.this.dispose();
				}
			}
				
		});
		
		JButton btn_log = new JButton("Deconnexion");
		btn_log.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(null, "voulez-vous vous deconnectez ?")==0) {
				
					log_in log=new log_in();
				log.setVisible(true);
				Acceuil.this.dispose();
				
				}
			}
		});
		btn_log.setFont(new Font("Tahoma", Font.BOLD, 23));
		btn_log.setBounds(696, 617, 200, 53);
		contentPane.add(btn_log);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\RS_blanc_2.jpg"));
		lblNewLabel_2.setBounds(0, 0, 175, 106);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Base de donn\u00E9e");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1_2.setBounds(639, 524, 237, 37);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Gestion de vente");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(328, 524, 237, 37);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Gestion de stock");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 524, 237, 37);
		contentPane.add(lblNewLabel_1);
		btnNewButton.setBounds(0, 247, 292, 252);
		contentPane.add(btnNewButton);
		
		//affichage de l'interface gestion de produit
		JButton btnNewButton_1 = new JButton("Gestion de vente");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection b = connexion_data.connecterDB();
				
				if(b!=null) {
				vente vt=new vente();
				vt.setVisible(true);
				Acceuil.this.dispose();
				}
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\gestion de vente.png"));
		btnNewButton_1.setBounds(302, 247, 292, 252);
		contentPane.add(btnNewButton_1);
		
		//affichage de l'interface data base
		JButton btnNewButton_2 = new JButton("BD");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection b = connexion_data.connecterDB();
				
				if(b!=null) {
				BD data=new BD();
				data.setVisible(true);
				Acceuil.this.dispose();
				}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\db.png"));
		btnNewButton_2.setBounds(604, 247, 292, 252);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\seddik\\Desktop\\app\\image\\background_same_2.jpg"));
		lblNewLabel.setBounds(0, 0, 896, 750);
		contentPane.add(lblNewLabel);
	}
}
