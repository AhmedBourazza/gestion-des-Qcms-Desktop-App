package qcmProjet;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
//cette classe permet de créer un objet QCM à partir d'une table QCM qui existe dans la base de donnée
public class Qcm {
	//Accès à la base de donnée personnelle
	static String url = "jdbc:mysql://localhost:3306/qcmdb";
	  static   String username = "root";
	  static  String password = "root";
	   static  Connection connection = null;
	    Statement st=null;
	    ResultSet rsTableQCM=null;//pour obtenir la table qcm depuis la bd
	    ResultSet rsTableQCMsInfos=null;//pour obtenir les informatios de la qcm : profPropritaire,filiereCible,niveauCible,systemeCanadien
	
	 //Accès à la table du QCM dans la base de donnée
	    String nomQCMBD;//le nom de la qcm doit etre exactement celui choisit dans la base de donnée
	    
	//Relatif à la classe Qcm , il seront remplit depuis la base de donnée
	    ArrayList<Question> tableQCM;//table qui contient chaque question avec les 3 suggestions et la reponse correcte
	    Professeur profProprietaire;//Proprietaire du QCM
	    boolean systemeCanadien;//=true:le qcm est en systeme canadien/=false=le qcm n'est pas en systeme canadien
	    String filiereCible;
	    int niveauCible;
	    String titre;//nom à afficher dans l'interface graphique
	//fonction qui teste la connection au driver
	   static  public  void seConnecterAuDriver()
		{
			//tester si le driver est bien deployé
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

			} catch (ClassNotFoundException e) {
				System.out.println("le driver n'est pas accessible!");
			}
		
		}
	
	//Connection à la base dedonnée
	   static public  void seConnecterAlaBD()
		{
	        
			  try {

		            // Connecter à la base de donnée
		            
		            connection = DriverManager.getConnection(url, username, password);
		            
		            //System.out.println("connection établie!");
		        }catch (SQLException e) {
		            System.out.println("probléme de connection à la base de donnée.");
		            e.printStackTrace();
		        }
		
		}
		
	    
		public Qcm(String titre,String nomQCMBD) 
		{
			super();	
			seConnecterAlaBD();
		    this.titre=titre;
			this.nomQCMBD = nomQCMBD;
			this.tableQCM = new ArrayList<>();
			try {
				st= connection.createStatement();
		//remplir les informations concernant le QCM
				rsTableQCMsInfos=st.executeQuery("SELECT * FROM qcmsinfos WHERE NomQcm='"+nomQCMBD+"'");
				if (rsTableQCMsInfos.next()) 
				{  this.profProprietaire = new Professeur(rsTableQCMsInfos.getString("Nom_ProfProprietaire"),rsTableQCMsInfos.getString("Specialite_ProfProprietaire"));
				    this.systemeCanadien = rsTableQCMsInfos.getBoolean("systemeCanadien");
				    this.filiereCible = rsTableQCMsInfos.getString("filiereCible");
				    this.niveauCible =rsTableQCMsInfos.getInt("niveauCible");
				} 
			else {
				    System.out.println("Aucune ligne trouvée dans la table qcmsinfos pour le qcm " + nomQCMBD);
				}
		//remplir la tableQCM
				rsTableQCM=st.executeQuery("SELECT * FROM "+nomQCMBD);//obtenir la table qui se trouve dans la base de donnée
				while(rsTableQCM.next())//remplir la tableQCM
	            {
	            	int numQst=rsTableQCM.getInt("NumQst");
	            	String question=rsTableQCM.getString("Question");
	            	String rpnsPossible1=rsTableQCM.getString("RpnsPossible1");
	            	String rpnsPossible2=rsTableQCM.getString("RpnsPossible2");
	            	String rpnsPossible3=rsTableQCM.getString("RpnsPossible3");
	            	int rpnsCorrecte=rsTableQCM.getInt("RpnsCorrecte");
	            	byte[]img=rsTableQCM.getBytes("QstImage");
	            	tableQCM.add(new Question(numQst,question,rpnsPossible1,rpnsPossible2,rpnsPossible3,rpnsCorrecte,img));
	            }}
			catch(SQLException e)
			{
				System.out.println("erreur lors de la remplissage de la table QCM");
			}			
			
		}
	   
		public void printInfosQCM() {
		    System.out.println("Nom du QCM : " + titre);
		    System.out.println("Propriétaire : " + profProprietaire.getNom() + ", spécialité : " + profProprietaire.getSpecialite());
		    System.out.println("Système canadien : " + systemeCanadien);
		    System.out.println("Filière ciblée : " + filiereCible);
		    System.out.println("Niveau ciblé : " + niveauCible);
		    System.out.println("Nombre de questions : " + tableQCM.size());
		    System.out.println("Table QCM :");
	        for (Question q : tableQCM) {
	            System.out.println("Question " + q.getNumQst() + " : " + q.getQst());
	            System.out.println("1. " + q.getRpnsPossible1());
	            System.out.println("2. " + q.getRpnsPossible2());
	            System.out.println("3. " + q.getRpnsPossible3());
	            System.out.println("3. " + q.getImg());
	            System.out.println("Reponse correcte : " + q.getReponseCorrecte());
	            System.out.println("");
	        }
		}
		
		public static void main(String[] args) {
			Qcm qcm=new Qcm("qcm de Java","qcm1");
		    qcm.printInfosQCM();
		   }
}/*
JLabel imageLabel = new JLabel();

// create an ImageIcon from the BufferedImage
   ImageIcon imageIcon;
   if(qcm.tableQCM.get(1).img!=null)
   {
 imageIcon = new ImageIcon(qcm.tableQCM.get(1).img);

// set the icon of the JLabel
imageLabel.setIcon(imageIcon);

// create a JFrame and add the JLabel to it
JFrame frame = new JFrame();
frame.add(imageLabel);

// set the size and make the frame visible
frame.setSize(500, 500);
frame.setVisible(true);
}*/