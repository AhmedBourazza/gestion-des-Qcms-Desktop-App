package qcmProjet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.Border;

import java.awt.Component;




public class AfficherQcmGUI extends JFrame implements ActionListener
{
//parametres du temps
	
	int tempsPourChaqueQst=5;//en secondes
	int tempsTotalDuQcm;
	Timer horloge ;
	
//parametres  l'interface graphique
	
	Color bgColor=new Color(0,0,50);//new Color(00,0, 200)
	Color btnColor = new Color(255, 255, 255);//new Color(26, 43, 66)
	Color textColor=new Color(0,0, 200);
	Color winColor=Color.green;
	Font font = new Font("Open Sans", Font.BOLD, 14);
	JPanel mainPanel,centerPanel,northPanel,southPanel,centerPanelInsideUp,centerPanelInsideDown,centerPanelInsideBar;
	JButton rpnsPossible1Btn,rpnsPossible2Btn,rpnsPossible3Btn;
	JButton passerQstSuivante;
	JProgressBar bar;

	
	int uilargeur=800;
	int uiHauteur=600;
	
	
//parametres du QCM
	
	Qcm qcm;//qcm à afficher
	static int score=0;
	static int i=0;//static pour que si il est changé par horloge, il sera changer par tous et donc je peux savoir la question actuelle dans boutton listner
	static boolean passe;
	
	
	int counter=0;
	public AfficherQcmGUI(Qcm qcm)
	{
		this.qcm=qcm;
		tempsTotalDuQcm=tempsPourChaqueQst*qcm.tableQCM.size();
		
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(qcm.titre);
		this.setSize(uilargeur,uiHauteur);
		//Border border = BorderFactory.createMatteBorder(0,20, 0,20,bgColor);

		JTextArea qcmQuestion = new JTextArea();
		qcmQuestion.setLineWrap(true);
		qcmQuestion.setWrapStyleWord(true);
		qcmQuestion.setEditable(false);
		qcmQuestion.setBackground(btnColor);
		qcmQuestion.setBounds(10, 10, (int) (uilargeur * 0.77), (int) (uiHauteur * 0.28));
		qcmQuestion.setText("parmi les meilleur joueurs des mondes , qui sont les joueurs qui ont gagné une coupe du monde");
		qcmQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
		qcmQuestion.setAlignmentY(Component.CENTER_ALIGNMENT);
		qcmQuestion.setFont(font);
		qcmQuestion.setForeground(bgColor);
		
		centerPanelInsideUp=new JPanel();
		centerPanelInsideUp.setBackground(btnColor);
		centerPanelInsideUp.add(qcmQuestion);
		
		
		JLabel qcmTete=new JLabel(qcm.titre);
		qcmTete.setBackground(btnColor);
		qcmTete.setForeground(btnColor);
		qcmTete.setHorizontalAlignment(JLabel.CENTER);
		qcmTete.setPreferredSize(new Dimension((int)(uilargeur*0.77),50) );
	//	qcmTete.setBorder(border);
		
		
		
		passerQstSuivante=new JButton("Passer à la question suivante");
		passerQstSuivante.setBackground(btnColor);
		passerQstSuivante.setFocusable(false);
		passerQstSuivante.setFont(font);
		passerQstSuivante.setForeground(bgColor);
		passerQstSuivante.addActionListener(this);
		
		
	
		
		  
		
		rpnsPossible1Btn=new JButton("suggestion1");
		rpnsPossible1Btn.setFont(font);
		rpnsPossible1Btn.setForeground(bgColor);
		rpnsPossible1Btn.setPreferredSize(new Dimension(new Dimension((int)(uilargeur*0.8),50) ));
		rpnsPossible1Btn.setBackground(btnColor);
		rpnsPossible1Btn.setFocusable(false);
		rpnsPossible1Btn.addActionListener(this);
		
		rpnsPossible2Btn=new JButton("suggestion2");
		rpnsPossible2Btn.setFont(font);
		rpnsPossible2Btn.setForeground(bgColor);
		rpnsPossible2Btn.setPreferredSize(new Dimension(new Dimension((int)(uilargeur*0.8),50) ));
		rpnsPossible2Btn.setBackground(btnColor);
		rpnsPossible2Btn.setFocusable(false);
		rpnsPossible2Btn.addActionListener(this);

		
		rpnsPossible3Btn=new JButton("suggestion3");
		rpnsPossible3Btn.setFont(font);
		rpnsPossible3Btn.setForeground(bgColor);
		rpnsPossible3Btn.setPreferredSize(new Dimension(new Dimension((int)(uilargeur*0.8),50) ));
		rpnsPossible3Btn.setBackground(btnColor);
		rpnsPossible3Btn.setFocusable(false);
		rpnsPossible3Btn.addActionListener(this);
	
		centerPanelInsideDown=new JPanel(new FlowLayout(FlowLayout.CENTER, 0, (int)(uiHauteur*0.12)));
		centerPanelInsideDown.add(rpnsPossible1Btn);
		centerPanelInsideDown.add(rpnsPossible2Btn);
		centerPanelInsideDown.add(rpnsPossible3Btn);
		centerPanelInsideDown.setBackground(bgColor);
		
		bar=new JProgressBar();
		bar.setStringPainted(true);
		bar.setValue(0);
		bar.setBackground(bgColor);
		bar.setForeground(btnColor);
		bar.setString("");
		
		centerPanelInsideBar=new JPanel(new BorderLayout());
		centerPanelInsideBar.add(bar);
		
		
		centerPanel =new JPanel(new BorderLayout());
		centerPanel.setBackground(bgColor);
		//centerPanel.setBorder(border);
		centerPanel.add(centerPanelInsideUp,BorderLayout.NORTH);
		centerPanel.add(centerPanelInsideDown,BorderLayout.CENTER);
		centerPanel.add(centerPanelInsideBar,BorderLayout.SOUTH);
		mainPanel=new JPanel(new BorderLayout());
		mainPanel.add(passerQstSuivante,BorderLayout.SOUTH);
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		mainPanel.add(qcmTete,BorderLayout.NORTH);

		mainPanel.setBackground(bgColor);

	
		
		Timer progressBarTimer = new Timer((1000*tempsPourChaqueQst-900)/100, new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (counter <= 100) {
		            bar.setStringPainted(true);
		            bar.setValue(counter);
		    		//bar.setString(100-counter+"");	afficher la valeur actuelle	
		            counter++;
		        } 
		    }
		});

		
		
		horloge=new Timer(1000*tempsPourChaqueQst, new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(i<qcm.tableQCM.size())
				{
					rpnsPossible1Btn.setEnabled(true);
					rpnsPossible2Btn.setEnabled(true);
					rpnsPossible3Btn.setEnabled(true);
					rpnsPossible1Btn.setBackground(btnColor);
					rpnsPossible2Btn.setBackground(btnColor);
					rpnsPossible3Btn.setBackground(btnColor);

					
					
					qcmQuestion.setText(qcm.tableQCM.get(i).qst);
					rpnsPossible1Btn.setText(qcm.tableQCM.get(i).rpnsPossible1);
					rpnsPossible2Btn.setText(qcm.tableQCM.get(i).rpnsPossible2);
					rpnsPossible3Btn.setText(qcm.tableQCM.get(i).rpnsPossible3);
					qcmTete.setText(i+1+"/"+qcm.tableQCM.size());
					counter=0;
					progressBarTimer.start();

					i++;
					
				}
				else 
					horloge.stop();
			}
		});
		horloge.setInitialDelay(10);
		progressBarTimer.setInitialDelay(10);
		
		horloge.start();
		
		
		
		
		this.setContentPane(mainPanel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		Qcm qcm=new Qcm("java","qcm1");
		new AfficherQcmGUI(qcm);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if((e.getSource()==rpnsPossible1Btn)&&qcm.tableQCM.get(i-1).reponseCorrecte==1)
		{
			score=score+1;
			rpnsPossible1Btn.setBackground(Color.gray);
			
			rpnsPossible1Btn.setEnabled(false);
			rpnsPossible2Btn.setEnabled(false);
			rpnsPossible3Btn.setEnabled(false);

		}
		if((e.getSource()==rpnsPossible2Btn)&&qcm.tableQCM.get(i-1).reponseCorrecte==2)
		{
			score=score+1;
			rpnsPossible2Btn.setBackground(Color.gray);
			
			rpnsPossible1Btn.setEnabled(false);
			rpnsPossible2Btn.setEnabled(false);
			rpnsPossible3Btn.setEnabled(false);

		}
		if((e.getSource()==rpnsPossible3Btn)&&qcm.tableQCM.get(i-1).reponseCorrecte==3)
		{
			score=score+1;
			rpnsPossible3Btn.setBackground(Color.gray);
			rpnsPossible1Btn.setEnabled(false);
			rpnsPossible2Btn.setEnabled(false);
			rpnsPossible3Btn.setEnabled(false);

		}
		if(((e.getSource()==rpnsPossible1Btn)&&qcm.tableQCM.get(i-1).reponseCorrecte!=1)&&qcm.systemeCanadien)
		{
			score=score-1;
			rpnsPossible1Btn.setBackground(Color.gray);
			rpnsPossible1Btn.setEnabled(false);
			rpnsPossible2Btn.setEnabled(false);
			rpnsPossible3Btn.setEnabled(false);

		}
		if(((e.getSource()==rpnsPossible2Btn)&&qcm.tableQCM.get(i-1).reponseCorrecte!=2)&&qcm.systemeCanadien)
		{
			score=score-1;
			rpnsPossible2Btn.setBackground(Color.gray);
			rpnsPossible1Btn.setEnabled(false);
			rpnsPossible2Btn.setEnabled(false);
			rpnsPossible3Btn.setEnabled(false);

		}
		if(((e.getSource()==rpnsPossible3Btn)&&qcm.tableQCM.get(i-1).reponseCorrecte!=3)&&qcm.systemeCanadien)
		{
			score=score-1;
			rpnsPossible3Btn.setBackground(Color.gray);
			rpnsPossible1Btn.setEnabled(false);
			rpnsPossible2Btn.setEnabled(false);
			rpnsPossible3Btn.setEnabled(false);

		}
		if(e.getSource()==passerQstSuivante)
		{
			passe=true;
		}
		
	}
}
