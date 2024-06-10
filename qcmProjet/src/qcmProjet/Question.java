package qcmProjet;

public class Question{
	public int numQst;
	public String qst;
	public String rpnsPossible1;
	public String rpnsPossible2;
	public String rpnsPossible3;
	public int reponseCorrecte;
	byte[] img;
	public Question()
	{
		
	}
	public Question(int numQst, String qst, String rpnsPossible1, String rpnsPossible2, String rpnsPossible3,
			 int reponseCorrecte,byte[] img) {
		super();
		this.numQst = numQst;
		this.qst = qst;
		this.rpnsPossible1 = rpnsPossible1;
		this.rpnsPossible2 = rpnsPossible2;
		this.rpnsPossible3 = rpnsPossible3;
		this.reponseCorrecte = reponseCorrecte;
		this.img=img;
	}
	
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public int getNumQst() {
		return numQst;
	}
	public void setNumQst(int numQst) {
		this.numQst = numQst;
	}
	public String getQst() {
		return qst;
	}
	public void setQst(String qst) {
		this.qst = qst;
	}
	public String getRpnsPossible1() {
		return rpnsPossible1;
	}
	public void setRpnsPossible1(String rpnsPossible1) {
		this.rpnsPossible1 = rpnsPossible1;
	}
	public String getRpnsPossible2() {
		return rpnsPossible2;
	}
	public void setRpnsPossible2(String rpnsPossible2) {
		this.rpnsPossible2 = rpnsPossible2;
	}
	public String getRpnsPossible3() {
		return rpnsPossible3;
	}
	public void setRpnsPossible3(String rpnsPossible3) {
		this.rpnsPossible3 = rpnsPossible3;
	}
	public int getReponseCorrecte() {
		return reponseCorrecte;
	}
	public void setReponseCorrecte(int reponseCorrecte) {
		this.reponseCorrecte = reponseCorrecte;
	}
}