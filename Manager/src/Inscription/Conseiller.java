package Inscription;

import java.sql.Date;
/**
 * ins¨¦rer des conseillers front-office dans la BDD
 * @author DIALLO Ibrahim
 *
 */
public class Conseiller {
	private String sexe;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String email;
	private String telephone;
	private String adresse;
	private String nationalite;
	private String pwd;
	/**
	 * Cto for create a new front-line advisor
	 * @param sexe sexe of advisor
	 * @param name name of advisor
	 * @param firstname fist name of advisor
	 * @param date of birth birthday  of advisor
	 * @param email email of advisor
	 * @param number telephone number of advisor
	 * @param address address of advisor
	 * @param password password of advisor
	 * @param nationality nationality of advisor
	 */
	public Conseiller(String sexe,String nom, String prenom, Date dateNaissance, 
			String email, String telephone, 
			String adresse,String pwd, String nationalite) {
		
		sexe = this.sexe;
		nom = this.nom;
		prenom = this.prenom;
		dateNaissance = this.dateNaissance;
		email = this.email;
		telephone = this.telephone;
		adresse = this.adresse;
		pwd = this.pwd;
		nationalite = this.nationalite;
		
	}

	public Conseiller() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * getter of the method getSexe
	 * @return sexe
	 */
	public String getSexe() {
		return sexe;
	}
	/**
	 * setter of the method getSexe
	 * @return sexe
	 */
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	/**
	 * getter of the method getNom
	 * @return name
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * setter of the method getNom
	 * @return name
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * getter of the method getPrenom
	 * @return firstname
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * setter of the method getPrenom
	 * @return firstname
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * getter of the method getDateNaissance
	 * @return date of birth
	 */
	public Date getDateNaissance() {
		return dateNaissance;
	}
	/**
	 * setter of the method getDateNaissance
	 * @return date of birth
	 */
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	/**
	 * getter of the method getEmail
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * setter of the method getEmail
	 * @return email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * getter of the method getAdresse
	 * @return address
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * setter of the method getAdresse
	 * @return address
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * getter of the method getTelephone
	 * @return number
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * setter of the method getTelephone
	 * @return number
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * getter of the method getPwd
	 * @return password
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * setter of the method getPwd
	 * @return password
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * getter of the method getNationalite
	 * @return nationality
	 */
	public String getNationalite() {
		return nationalite;
	}
	/**
	 * setter of the method getNationalite
	 * @return nationality
	 */
	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	
}