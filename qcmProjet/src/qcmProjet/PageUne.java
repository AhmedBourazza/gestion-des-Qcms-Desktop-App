package qcmProjet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class PageUne extends JFrame implements ActionListener
{
	Color bgColor=Color.WHITE;
	Color btnColor;
	Color textColor;
	int uiLargeur=800;
	int uiHauteur=600;
	
	
	JPanel mainPanel,upPanel;
	JPanel centerPanelReception,downPanelReception;//page de Reception
	JPanel centerPanelEtudiant,downPanelReceptionEtudiant;//page d'enregistrement d'etudiant
	JPanel centerPanelProf,downPanelReceptionProf;//page d'enregistrement du prof
	JLabel tete;
	JButton Professeurbtn,Etudiantbtn;
	JButton envoyerE;//boutton envoyer page etudiant;
	JButton envoyerP;//boutton envoyer page professeur;
	JTextField nomEArea,filiereArea,niveauArea,nomPArea,specialiteArea;

	
	Clip clip;
	
	boolean estUnProfesseur;
	
	

	public PageUne()
	{
		this.setSize(uiLargeur,uiHauteur);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Page de réception");
		
//-------------------------Page de reception----------------------------//
		tete =new JLabel("Veuillez choisir parmi :");
		tete.setHorizontalAlignment(JLabel.CENTER);
		tete.setVerticalAlignment(JLabel.CENTER);
		Font fontTete = new Font("Open Sans", Font.BOLD, 19);//consolas
		tete.setFont(fontTete);
		tete.setPreferredSize(new Dimension(uiLargeur-400, 50)); // par exemple, ici on définit une hauteur de 50 pixels
		tete.setBackground(bgColor);
		tete.setForeground(Color.WHITE);
		Border teteBorder=BorderFactory.createMatteBorder(0, 0, 10, 0, Color.white);
		tete.setBorder(teteBorder);
		
		
		upPanel=new JPanel(){
		    @Override
		    public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g;
		        int w = getWidth();
		        int h = getHeight();
		        GradientPaint gp = new GradientPaint(0, 0, Color.black, 0, h, Color.blue);
		        g2d.setPaint(gp);
		        g2d.fillRect(0, 0, w, h);
		    }
		};
		upPanel.add(tete);
		
		
		
		
		
		ImageIcon imageProfesseur=new ImageIcon("C:\\Users\\PC\\OneDrive\\Bureau\\professor.png");
		JLabel labelProfesseur =new JLabel(imageProfesseur);
		
		labelProfesseur.setPreferredSize(new Dimension(200,200));
		Professeurbtn =new JButton("Professeur");
		Professeurbtn.setHorizontalAlignment(JLabel.CENTER);
		Professeurbtn.setVerticalAlignment(JLabel.CENTER);
		Professeurbtn.setFont(fontTete);
		Professeurbtn.setPreferredSize(new Dimension(uiLargeur/4, 50)); // par exemple, ici on définit une hauteur de 50 pixels
		Professeurbtn.setBackground(bgColor);
		Professeurbtn.setForeground(Color.black);
		Professeurbtn.setFocusable(false);
		Border border = BorderFactory.createMatteBorder(0,0, 0,20,new Color(0,0,50));
		Professeurbtn.setBorder(border);
		Professeurbtn.addActionListener(this);
		
		ImageIcon imageEtudiant=new ImageIcon("C:\\Users\\PC\\OneDrive\\Bureau\\etudiant3.png");
		JLabel labelEtudiant =new JLabel(imageEtudiant);
		
		labelEtudiant.setPreferredSize(new Dimension(200,200));
		Etudiantbtn =new JButton("Etudiant");
		Etudiantbtn.setHorizontalAlignment(JLabel.CENTER);
		Etudiantbtn.setVerticalAlignment(JLabel.CENTER);
		Etudiantbtn.setFont(fontTete);
		Etudiantbtn.setPreferredSize(new Dimension(uiLargeur/4, 50)); // par exemple, ici on définit une hauteur de 50 pixels
		Etudiantbtn.setBackground(bgColor);
		Etudiantbtn.setForeground(Color.black);
		Etudiantbtn.setFocusable(false);
		Etudiantbtn.addActionListener(this);
		
		
		
		
		
		downPanelReception=new JPanel(new GridLayout(1,2));
		downPanelReception.setPreferredSize(new Dimension(uiLargeur-50,50));
		downPanelReception.add(Professeurbtn);
		downPanelReception.add(Etudiantbtn);
		
		centerPanelReception=new JPanel(new FlowLayout(1,180,80)){
		    @Override
		    public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g;
		        int w = getWidth();
		        int h = getHeight();
		        GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, h, Color.black);
		        g2d.setPaint(gp);
		        g2d.fillRect(0, 0, w, h);
		    }
		};
		centerPanelReception.add(labelProfesseur);
		centerPanelReception.add(labelEtudiant);
		centerPanelReception.add(downPanelReception);
		
		mainPanel = new JPanel(new BorderLayout()) ;
		mainPanel.add(upPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanelReception,BorderLayout.CENTER);
		
		
		
		
//----------Debut page d'enregistrement d'etudiant-------------//

		
		
		JLabel nomEtudiant=new JLabel("Nom : ");
		nomEArea=new JTextField();
		nomEtudiant.setHorizontalAlignment(JLabel.CENTER);
		nomEtudiant.setVerticalAlignment(JLabel.CENTER);
		Font font2 = new Font("Open Sans", Font.BOLD, 19);//consolas
		nomEtudiant.setFont(font2);
		nomEtudiant.setPreferredSize(new Dimension(uiLargeur-510, 50)); // par exemple, ici on définit une hauteur de 50 pixels
		nomEtudiant.setBackground(bgColor);
		nomEtudiant.setForeground(Color.white);
		
		nomEArea.setPreferredSize(new Dimension(250,50) );
		
		
		JLabel filiereEtudiant=new JLabel("Filiere : ");
		filiereArea=new JTextField();
		
		filiereEtudiant.setHorizontalAlignment(JLabel.CENTER);
		filiereEtudiant.setVerticalAlignment(JLabel.CENTER);
		filiereEtudiant.setFont(font2);
		filiereEtudiant.setPreferredSize(new Dimension(uiLargeur-510, 50)); // par exemple, ici on définit une hauteur de 50 pixels
		filiereEtudiant.setBackground(bgColor);
		filiereEtudiant.setForeground(Color.white);
		
		filiereArea.setPreferredSize(new Dimension(250,50) );
		
		
		
		
		
		JLabel niveauEtudiant=new JLabel("Niveau : ");
		niveauArea=new JTextField();
		
		niveauEtudiant.setHorizontalAlignment(JLabel.CENTER);
		niveauEtudiant.setVerticalAlignment(JLabel.CENTER);
		niveauEtudiant.setFont(font2);
		niveauEtudiant.setPreferredSize(new Dimension(uiLargeur-510, 50)); // par exemple, ici on définit une hauteur de 50 pixels
		niveauEtudiant.setBackground(bgColor);
		niveauEtudiant.setForeground(Color.white);
		
		niveauArea.setPreferredSize(new Dimension(250,50) );
		
		
		envoyerE=new  JButton("Entrer");
		envoyerE.setFont(font2);
		envoyerE.setForeground(Color.black);
		envoyerE.setPreferredSize(new Dimension(new Dimension((int)(uiLargeur*0.5),50) ));
		envoyerE.setBackground(btnColor);
		envoyerE.setFocusable(false);
		envoyerE.addActionListener(this);
		
		
		JLabel pourAjustement=new JLabel();
		pourAjustement.setPreferredSize(new Dimension(uiLargeur-200, 50));
		pourAjustement.setOpaque(false);
		
		centerPanelEtudiant=new JPanel(new FlowLayout(1,10,30)){
		    @Override
		    public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g;
		        int w = getWidth();
		        int h = getHeight();
		        GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, h, Color.black);
		        g2d.setPaint(gp);
		        g2d.fillRect(0, 0, w, h);
		    }
		};
		centerPanelEtudiant.add(pourAjustement);
		centerPanelEtudiant.add(nomEtudiant);
		centerPanelEtudiant.add(nomEArea);
		centerPanelEtudiant.add(filiereEtudiant);
		centerPanelEtudiant.add(filiereArea);
		centerPanelEtudiant.add(niveauEtudiant);
		centerPanelEtudiant.add(niveauArea);
		
	
//----------Debut page d'enregistrement d'un prof-------------//

		
		
		JLabel nomProfesseur=new JLabel("Nom : ");
		nomPArea=new JTextField();
		nomProfesseur.setHorizontalAlignment(JLabel.CENTER);
		nomProfesseur.setVerticalAlignment(JLabel.CENTER);
		nomProfesseur.setFont(font2);
		nomProfesseur.setPreferredSize(new Dimension(uiLargeur-510, 50)); // par exemple, ici on définit une hauteur de 50 pixels
		nomProfesseur.setBackground(bgColor);
		nomProfesseur.setForeground(Color.white);
		
		nomPArea.setPreferredSize(new Dimension(250,50) );
		
		
		
		JLabel specialite=new JLabel("Specialité : ");
		
		specialiteArea=new JTextField();
		
		specialite.setHorizontalAlignment(JLabel.CENTER);
		specialite.setVerticalAlignment(JLabel.CENTER);
		specialite.setFont(font2);
		specialite.setPreferredSize(new Dimension(uiLargeur-510, 50)); // par exemple, ici on définit une hauteur de 50 pixels
		specialite.setBackground(bgColor);
		specialite.setForeground(Color.white);
		
		specialiteArea.setPreferredSize(new Dimension(250,50) );
		
		

		envoyerP=new  JButton("Entrer");
		envoyerP.setFont(font2);
		envoyerP.setForeground(Color.black);
		envoyerP.setPreferredSize(new Dimension(new Dimension((int)(uiLargeur*0.5),50) ));
		envoyerP.setBackground(btnColor);
		envoyerP.setFocusable(false);
		envoyerP.addActionListener(this);
		
		centerPanelProf=new JPanel(new FlowLayout(1,10,30)){
		    @Override
		    public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g;
		        int w = getWidth();
		        int h = getHeight();
		        GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, h, Color.black);
		        g2d.setPaint(gp);
		        g2d.fillRect(0, 0, w, h);
		    }
		};
		
		JLabel pourAjustement2=new JLabel();
		pourAjustement2.setPreferredSize(new Dimension(uiLargeur-200, 80));
		pourAjustement2.setOpaque(false);
		
		centerPanelProf.add(pourAjustement2);
		centerPanelProf.add(nomProfesseur);
		centerPanelProf.add(nomPArea);
		centerPanelProf.add(specialite);
		centerPanelProf.add(specialiteArea);
		

		
		
		this.setContentPane(mainPanel);
		this.setVisible(true);
		//audio 
	/*	try {
			File audio = new File("C:\\Users\\PC\\OneDrive\\Bureau\\clic.wav");
		    AudioInputStream audioIn = AudioSystem.getAudioInputStream(audio);
		    AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
		    audioIn = AudioSystem.getAudioInputStream(format, audioIn);
		     clip = AudioSystem.getClip();
		    clip.open(audioIn);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
		    e.printStackTrace();
		}

		
		
		*/
		
		
		
	}
	public static void main(String[] args) {
		new PageUne();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Professeurbtn)
			{
			estUnProfesseur=true;
			Professeurbtn.setBackground(new Color(0,0,50));;

			Professeurbtn.setEnabled(false);
			Etudiantbtn.setEnabled(false);
			
			centerPanelReception.setVisible(false);
			mainPanel.remove(centerPanelReception);
			tete.setText("Veuillez remplir les champs suivants :");
			this.setTitle("Espace Professeur");
			mainPanel.add(centerPanelProf,BorderLayout.CENTER);
			mainPanel.add(envoyerP,BorderLayout.SOUTH);
			
			System.out.println("c'est un professeur");

			}
		if(e.getSource()==Etudiantbtn)
			{
			estUnProfesseur=false;
			Etudiantbtn.setBackground(new Color(0,0,50));;
			
			Professeurbtn.setEnabled(false);
			Etudiantbtn.setEnabled(false);
			centerPanelReception.setVisible(false);
			mainPanel.remove(centerPanelReception);
			tete.setText("Veuillez remplir les champs suivants :");
			this.setTitle("Espace étudiant");
			mainPanel.add(centerPanelEtudiant,BorderLayout.CENTER);
			mainPanel.add(envoyerE,BorderLayout.SOUTH);

			System.out.println("c'est un étudiant");

			}
		
		
		
		if(e.getSource()==envoyerE )
		{
			if(!nomEArea.getText().isEmpty() && !filiereArea.getText().isEmpty()&& !niveauArea.getText().isEmpty())
			{
				System.out.println("bonjour "+nomEArea.getText());
				
				Qcm.seConnecterAuDriver();
				Qcm.seConnecterAlaBD();
				try {
					Statement s=Qcm.connection.createStatement();
					ResultSet rs=s.executeQuery("SELECT * From etudiants WHERE NomEtudiant='"+nomEArea.getText()+"' AND Filiere='"+filiereArea.getText()+"'  AND Niveau="+Integer.parseInt(niveauArea.getText()) );
					if(!rs.next())
					{
						s.execute("INSERT INTO `etudiants` (`NomEtudiant`, `Filiere`, `Niveau`) VALUES ('"+nomEArea.getText()+"','"+filiereArea.getText()+"','"+Integer.parseInt(niveauArea.getText()) +"')");
						System.out.println("Nouveau étudiant est ajouté dans la base de donnée\n "+"Bienvenue "+nomEArea.getText());
					}
					else
						{
						System.out.println("cet étudiant existe deja dans la base de donnée");
						System.out.println("Bienvenue "+ nomEArea.getText() );
						}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			else
		        JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!");

		}
		
		
		if(e.getSource()==envoyerP)
			{
			
			if(!nomPArea.getText().isEmpty()&&!specialiteArea.getText().isEmpty())
			
			{
				Qcm.seConnecterAuDriver();
				Qcm.seConnecterAlaBD();
				try {
					Statement st=Qcm.connection.createStatement();
					ResultSet rs=st.executeQuery("SELECT * FROM professeurs WHERE NomProfesseur='"+nomPArea.getText()+"' AND Specialite= '"+specialiteArea+"'");
					if(!rs.next())
					{
						st.execute("INSERT INTO `professeurs` (`NomProfesseur`, `Specialite`) VALUES ('"+nomPArea.getText()+"','"+specialiteArea.getText()+"')");
						System.out.println("Nouveau professeur est ajouté dans la base de donnée\n "+"Bienvenue professeur "+nomPArea.getText());
					}
					else
						{
						System.out.println("cet professeur existe deja dans la base de donnée");
						System.out.println("Bienvenue professeur"+ nomPArea.getText() );
						}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else
				JOptionPane.showMessageDialog(null,"Veuillez remplir tous les champs!");
	}
	}
	
	
}
