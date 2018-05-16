package inscription;

import java.sql.Date;

public class Client {

	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String email;
	private String telephone;
	private String adresse;
	private String code_postal;
	private String ville;
	private String pays;
	private String pwd;
	private String nationalite;
	private String sexe;
	
	public Client(String nom, String prenom, Date dateNaissance, 
			String email, String telephone, 
			String adresse, String code_postal, String ville, String pays, String pwd, String nationalite, String sexe) {
		
		
		nom = this.nom;
		prenom = this.prenom;
		dateNaissance = this.dateNaissance;
		email = this.email;
		telephone = this.telephone;
		adresse = this.adresse;
		code_postal = this.code_postal;
		ville = this.ville;
		pays = this.pays;
		pwd = this.pwd;
		nationalite = this.nationalite;
		sexe = this.sexe;
	}
	
	public Client() {
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	
}
