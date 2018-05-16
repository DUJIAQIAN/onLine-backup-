package Cpt;


public class Cpt {


	private int numero;
	private double solde;
	private int status;

	public Cpt(int numero, double solde, int status) {		
		numero = this.numero;
		solde = this.solde;
		status = this.status;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Cpt() {
		// TODO Auto-generated constructor stub
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

}
