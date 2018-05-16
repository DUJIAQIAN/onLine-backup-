package Cpt;

public class Achat {
	private int numeroCptTitre;
	private String nomAction;
	private float valueAction;
	private int numAchat;

	public Achat() {		
	}
//getters
	public int getNumeroCptTitre() {
		return numeroCptTitre;
	}
	
	public String getNomAction() {
		return nomAction;
	}
	public int getNumAchat() {
		return numAchat;
	}
	public float getValueAction() {
		return valueAction;
	}
//setters
	public void setnomAction(String nomAction) {
		this.nomAction = nomAction;
	}
	public void setNumAchat(int numAchat) {
		this.numAchat = numAchat;
	}
	
	public void setValueAction(float value) {
		this.valueAction = value;
	}
	
	public void setNumeroCptTitre(int numero) {
		this.numeroCptTitre = numero;
	}
}
