package qcmProjet;

public class Professeur 
{
	String nom;
	String specialite;
	public Professeur()
	{
		
	}
	@Override
	public String toString() {
		return "Professeur [nom=" + nom + ", specialite=" + specialite + "]";
	}
	public Professeur(String nom, String specialite) {
		super();
		this.nom = nom;
		this.specialite = specialite;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	
}
