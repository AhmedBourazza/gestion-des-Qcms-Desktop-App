package qcmProjet;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ApplicationEtudiant extends JFrame
{
	ArrayList<Qcm> listeQcm;//liste de tous les qcms, le professeur doit entrer les noms des qcms avec les titres
	
	
	Color bgColor=Color.blue;
	Color textColor=Color.orange;
	JPanel mainPanel;
	JPanel centerPanel;
	JPanel NorthPanel;
	JPanel southPanel;

	ApplicationEtudiant()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("QCM");
		this.setLocationRelativeTo(null);
		
		
		
		
		
		
		
		
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new ApplicationEtudiant();
	}
}
