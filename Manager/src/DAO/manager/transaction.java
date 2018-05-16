package DAO.manager;

import java.util.Date;
/**
 * DTO transaction for get and set the datas of table historique in database
 * @author DU JIAQIAN
 *
 */
public class transaction {
  private String numero;
  private String montant;
  private String operation;
  private String motif;
  private String date;
  
  //getters
  /**
   * allows to get the number of the account
   * @return the number of the account
   */
  public String getNumero() {
	  return this.numero;
  }
  
  /**
   * allows to get the amount of transaction
   * @return the amount of transaction
   */
  public String getMontant(){
	  return this.montant;
  }
  
  /**
   * allows to get the operation(cr¨¦diter/d¨¦biter)
   * @return the operation
   */
  public String getOperation(){
	  return this.operation;
  }
  
  /**
   * allows to get the reason for the transaction 
   * @return the reason for the transaction
   */
  public String getMotif(){
	  return this.motif;
  }
  
  /**
   * allows to get the date of transaction
   * @return
   */
  public String getDate(){
	  return this.date;
  }
  
  //setters
  /**
   * allows to set the account number
   * @param numero account number
   */
  public void setNumero(String numero) {
	  this.numero = numero;
  }
  /**
   * allows to set the amount of transaction 
   * @param montant amount of transaction
   */
  public void setMontant(String montant) {
	  this.montant = montant;
  }
  
  /**
   * allows to set the reason of transaction 
   * @param motif reason of transaction 
   */
  public void setMotif(String motif) {
	  this.motif = motif;
  }
  
  /**
   * allows to set the operation of transaction 
   * @param ope operation of transaction
   */
  public void setOperation(String ope) {
	  this.operation = ope;
  }
  
  /**
   * allows to set the date of transaction
   * @param date date of transaction
   */
  public void setDate(String date) {
	  this.date = date;
  }
}
