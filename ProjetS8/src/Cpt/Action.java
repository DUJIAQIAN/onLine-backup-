package Cpt;


public class Action {
	private int numeroCptTitre;
	private String nomAction;
	private float valueAction;
	private int numAchat;
	private float total;

	public Action() {		
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
	
	public float getTotal() {
		return total;
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
	
	public void setTotal(float total) {
		this.total = total;
	}
}
