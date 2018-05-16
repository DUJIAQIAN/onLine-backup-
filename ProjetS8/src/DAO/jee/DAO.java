package DAO.jee;

import java.io.File;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.*;
import java.security.*;

import Cpt.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import inscription.Client;
import transaction.historiqueTransaction;

public class DAO {
	
	public static int min;
	public static int max;
	public static int len = max-min+1;
	
	private final static String url = "jdbc:mysql://localhost/projects8";
	private final static String username = "root";
	private final static String password = "";
	static Connection connection = null;
	private static HSSFWorkbook wb;
	private static HSSFWorkbook wb3;
	
	
	private static boolean connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection =
					DriverManager.getConnection(url, username, password);
			return true;
		}catch (Exception e) {return false;}
			
	}
	
	private static void close() {
		if (connection != null) {
			 try {
			 connection.close();
			 } catch (SQLException ex) {
			 ex.printStackTrace();
			 }
			 }
	}
	
	/** Comparaison du login et mot de passe rentrés avec ceux présents dans la bdd
	 * @param login
	 * @param password
	 * @return
	 */
	public static String login(String login, String password) {
		if (connect()) {
			try {
				String query = "SELECT nom_cli, prenom_cli FROM client WHERE nom_cli= ? and mdp_cli = ?";
				PreparedStatement myStmt = connection.prepareStatement(query);
				myStmt.setString(1, login);
				myStmt.setString(2, password);
				ResultSet resultSet = myStmt.executeQuery();	
				if (resultSet.next()) {
					//System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
					return resultSet.getString(1) + " " + resultSet.getString(2);
				}
				else
					return null;
			}catch (Exception e) {
				System.out.println(e);
				return null;
				}finally {
					close();}
		}else {
		return null;}
	}
	
	
	/** Recherche des informations d'un client
	 * @param user
	 * @return
	 */
	public static Client getInfos(String nom, String prenom) {
		if (connect()) {
			try {
				String query = "SELECT * FROM client WHERE nom_cli = ? and prenom_cli = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, nom);
				ps.setString(2, prenom);
				ResultSet resultSet = ps.executeQuery();
				if (resultSet.next()) {
					 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					 java.util.Date result =  df.parse(resultSet.getString(3)); 
					 java.sql.Date sqlDate = new java.sql.Date(result.getTime());
					Client client = new Client();
					client.setNom(resultSet.getString(1));
					client.setPrenom(resultSet.getString(2));
					client.setDateNaissance(sqlDate);
					client.setEmail(resultSet.getString(4));
					client.setTelephone(resultSet.getString(5));
					client.setAdresse(resultSet.getString(6));
					//client.setPwd(resultSet.getString(7));
					client.setNationalite(resultSet.getString(9));
					client.setSexe(resultSet.getString(10));
					return client;
				}else {					
					return null;
				}
			}catch (Exception e) {
				return null;
			}finally {
				close();
			}
		}else {
			return null;
		}
	}
/**
 * encrypts the password in the database
 * @param pwd, password that the client entered during registration
 * @return a encrypted password
 * @throws Exception
 */
	public static String Encrypter(String pwd) throws Exception{
		 String result = "";
        MessageDigest m;
		try {
				m = MessageDigest.getInstance("MD5");
		        m.update(pwd.getBytes("UTF8"));
		        byte s[] = m.digest();
		       
		        for (int i = 0; i < s.length; i++) {
		            result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00).substring(6);
		        	}     
		        
		        System.out.println(result);
		    }catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   return result;
	}
	
/**
 * allows to insert a new customer in the database with personal information	
 * @param client 
 * @return
 */
	public static boolean InsererClient(Client client) {
		if (connect()) {
			try {				
				String insert =  "INSERT INTO client(nom_cli, prenom_cli, date_naissance_cli, email_cli, telephone_cli, adresse_cli, mdp_cli, nationalite_cli, sexe_cli)" 
						+ "VALUES(?,?,?,?,?,?,?,?,?)";
				PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(insert);
				preStmt.setString(1,client.getNom());
				preStmt.setString(2,client.getPrenom());
				preStmt.setDate(3,client.getDateNaissance());
				preStmt.setString(4,client.getEmail());
				preStmt.setString(5,client.getTelephone());
				preStmt.setString(6,client.getAdresse());
				preStmt.setString(7,DAO.Encrypter(client.getPwd()));
				preStmt.setString(8,client.getNationalite());
				preStmt.setString(9,client.getSexe());
				
				preStmt.executeUpdate();
				System.out.println("success");
				return true;
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}else {
			return false;
		}
	}
	
	public static boolean CheckMdp(String pwd) {
		boolean mdp = true;
		if (connect()) {
			try {
				String query = "SELECT mdp_cli FROM client";
				PreparedStatement ps = connection.prepareStatement(query);
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					if (pwd.equals(resultSet.getString(1))) {
						mdp = false;
					}
				}
				return mdp;
			}catch (Exception e) {
				return mdp;
			}finally {
				close();}
		}else {
			return mdp;
		}
	}
	
	public static Cpt getSolde(String nom, String prenom) {
		Cpt compte = new Cpt();
		if (connect()) {
			try {
				String query = "SELECT numeroCptCourant, solde FROM comptecourant INNER JOIN client ON client.id_client = comptecourant.idClient WHERE client.nom_cli = ? and client.prenom_cli = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, nom);
				ps.setString(2, prenom);
				ResultSet resultSet = ps.executeQuery();
				if (resultSet.next()) {
					compte.setNumero(resultSet.getInt(1));
					compte.setSolde(resultSet.getInt(2));
					return compte;
				}else {
					return null;
				}		
			}catch (Exception e) {
				return null;
			}finally {
				close();}
		}
		else {
			return null;
		}
	}

	/**
	 * 
	 * 
	 * @param nom
	 * @param prenom
	 * @return
	 */
	public static Cpt getEpargne(String nom, String prenom) {
		Cpt compte = new Cpt();
		if (connect()) {
			try {
				String query = "SELECT numeroCptEpargne, solde FROM compteepargne INNER JOIN client ON client.id_client = compteepargne.idClient WHERE client.nom_cli = ? and client.prenom_cli = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, nom);
				ps.setString(2, prenom);
				ResultSet resultSet = ps.executeQuery();
				if (resultSet.next()) {
					compte.setNumero(resultSet.getInt(1));
					compte.setSolde(resultSet.getInt(2));
					return compte;
				}else {
					return null;
				}		
			}catch (Exception e) {
				return null;
			}finally {
				close();}
		}
		else {
			return null;
		}
	}
	
	public static ArrayList<CptTitre> getTitre(Cpt courant) {
		ArrayList<CptTitre> TitreList = new ArrayList<CptTitre>();
		if (connect()) {
				try {
					String query = "SELECT * FROM avcac  INNER JOIN comptetitre on avcac.fk_numeroCptTitre = comptetitre.numeroCptTitre "
							+ "INNER JOIN comptecourant ON comptecourant.numeroCptCourant = comptetitre.fkCptCourant WHERE comptecourant.numeroCptCourant = ?";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setInt(1, courant.getNumero());
					ResultSet resultSet = ps.executeQuery();
					while (resultSet.next()){
						CptTitre compte = new CptTitre();
						compte.setNumero(resultSet.getInt(2));
						compte.setEntreprise(resultSet.getString(3));
						compte.setQuantite(resultSet.getInt(4));
						compte.setPrix(resultSet.getDouble(5));
						TitreList.add(compte);
					}
					return TitreList;
				}catch (Exception e) {
					return null;
				}finally {
					close();
				}
		}else {
			return null;
		}
	}
	
	public static int getIdClient(String nom, String prenom) {
		if (connect()) {
			try {
				int idClient;
				String query = "SELECT id_client FROM client WHERE nom_cli = ? and prenom_cli = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, nom);
				ps.setString(2, prenom);
				ResultSet resultSet = ps.executeQuery();
				if (resultSet.next()) {
					idClient = resultSet.getInt(1);
					return idClient;
				}else {
					return -1;
				}		
			}catch (Exception e) {
				return -1;
			}finally {
				close();}
		}else {
			return -1;
		}
	}
	
	
	public static boolean Modifier(Client client) {
		if (connect()) {
			try {
				String insert =  "UPDATE client SET email_cli = ?, telephone_cli = ?, adresse_cli = ?, mdp_cli = ?" 
						+ "WHERE nom_cli = ? and prenom_cli = ?";
				PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(insert);

				preStmt.setString(1,client.getEmail());
				preStmt.setString(2,client.getTelephone());
				preStmt.setString(3,client.getAdresse());
				preStmt.setString(4,DAO.Encrypter(client.getPwd()));
				preStmt.setString(5,client.getNom());
				preStmt.setString(6,client.getPrenom());
				
				preStmt.executeUpdate();
				return true;
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}finally {
				close();}
		}else {
			return false;
		}
	}
	
	public static boolean insererCptCourant(int idClient) {
		min=1001;
		max=5555;
	    len = max-min+1;
		 	int numero;
		if (connect()) {
			try {
				  
				  ResultSet rs = null;
				  do {
					   numero=InsererCpt.randomNumeroCompte(min,max,len);
					   String sql="SELECT * FROM comptecourant where numeroCptCourant="+numero;
					   PreparedStatement stmt =(PreparedStatement) connection.prepareStatement(sql);
					   rs = stmt.executeQuery();
					   }while(rs.next());
				  String insert = "INSERT INTO comptecourant(idClient, numeroCptCourant, solde, status) VALUES(?,?,?,?)";
				   PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(insert);
				   preStmt.setInt(1,idClient);
				   preStmt.setInt(2,numero);
				   preStmt.setInt(3,0);
				   preStmt.setInt(4,0);
				   preStmt.executeUpdate();
				   return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			}finally {
				close();}		 
		}else {
			return false;
		}
	}
	
	public static boolean insererCptTitre(String numeroCptCourant, String mdp) throws Exception{
		min=10001;
		max=15555;
		len = max-min+1;
		String sql;
		Statement stmt;
		ResultSet rs = null;
		int numero;
		
		if(connect()) {
			try {
				stmt=connection.createStatement();			 
			  do {
			   numero=InsererCpt.randomNumeroCompte(min,max,len);
			   sql="SELECT * FROM compteTitre where numeroCptTitre="+numero;
			   rs =stmt.executeQuery(sql);			 
			   }while(rs.next());
		 
			//d¨¦terminer s'il existe d¨¦j¨¤ un cpt titre
			  sql="SELECT * FROM compteTitre WHERE fkCptCourant="+numeroCptCourant;
			  rs =stmt.executeQuery(sql);
			  
			  if(rs.next()) {
				  throw new Exception( "Vous avez deja un compte titre."); 	
			  }else {
				  
				  //déterminer si le mdp est bon?
				  if(CheckMdp(mdp)) {
				   throw new Exception( "Attention! Votre mot de passe et/ou votre numero compte semble(nt) incorrects!");
				  }else {
					  connect();
					  sql="	INSERT INTO comptetitre(fkCptCourant,numeroCptTitre) VALUES(?,?)";					 
					  PreparedStatement preStmt =connection.prepareStatement(sql);					
					   preStmt.setString(1,numeroCptCourant);
					   preStmt.setInt(2,numero); 
					   preStmt.executeUpdate();
					   
					  System.out.println("success");
					  return true;
				  }
			  }
			}catch (SQLException e) {			  
				return false;
			}	finally {
				close();
				}	
		}else {
			return false;
		}
	}
	
	
	/**
	 * permet d'obtenir le num¨¦ro du compte titre
	 * @param nom de user
	 * @param prenom user
	 * @return
	 */
	  public static int getNumCptTitre(String nom, String prenom) {
			if (connect()) {
				try {
					String query = "SELECT numeroCptTitre FROM comptetitre INNER JOIN comptecourant on comptecourant.NumeroCptCourant = comptetitre.fkCptCourant "
							+ "INNER JOIN client ON client.id_client = comptecourant.idClient WHERE client.nom_cli = ? and client.prenom_cli = ?";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, nom);
					ps.setString(2, prenom);
					ResultSet resultSet = ps.executeQuery();
					if (resultSet.next()) {
						return resultSet.getInt(1);
					}else {
						return 0;
					}		
				}catch (Exception e) {
					return 0;
				}finally {
					close();}
			}
			else {
				return 0;
			}
		}
	  
	  public static int getNumCptEpargne(String nom, String prenom) {
			if (connect()) {
				try {
					String query = "SELECT numeroCptEpargne FROM compteepargne  "
							+ "INNER JOIN client ON client.id_client = compteepargne.idClient WHERE client.nom_cli = ? and client.prenom_cli = ?";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, nom);
					ps.setString(2, prenom);
					ResultSet resultSet = ps.executeQuery();
					if (resultSet.next()) {;
						return resultSet.getInt(1);
					}else {
						return 0;
					}		
				}catch (Exception e) {
					return 0;
				}finally {
					close();}
			}
			else {
				return 0;
			}
		}
	
	  /**
	   * update la solde du compt courant
	   * @param numeroCptCourant
	   * @param solde
	   */
	  public static void setSolde(int numeroCptCourant,double solde) {
	  	if (connect()) {
	  		try {
	  			
	  			String sql="UPDATE comptecourant SET solde = ? WHERE numeroCptCourant=?";
	  			
	  			PreparedStatement preStmt =connection.prepareStatement(sql);
	  			   preStmt.setDouble(1,solde);
	  			   preStmt.setInt(2, numeroCptCourant);
	  			   preStmt.executeUpdate();	
	  			   preStmt.close();
	  		}catch (Exception e) {
	  			e.printStackTrace();
	  		}finally {
	  			close();}
	  	}
	  }

	  
	  public static boolean getTitre(Achat achat) {
			 boolean flag=false;
			 String sql;
			 ResultSet rs = null;
			 sql="SELECT * FROM avCAC where company=? And fk_numeroCptTitre=?";
			if(connect()) {
				try {
				    PreparedStatement ps = connection.prepareStatement(sql);			
					ps.setString(1, achat.getNomAction());
					ps.setInt(2, achat.getNumeroCptTitre());
					rs = ps.executeQuery();	
					if(rs.next()) {
						flag=true;
					 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	 
			return flag;
		}

	   public static void insererAchat_Vendre(Achat achat) {
	      float total=achat.getValueAction();
	  	 int numeroCptTitre=achat.getNumeroCptTitre();
	  	 String company = achat.getNomAction();
	  	 int qtt = achat.getNumAchat();
	   
	  	 String sql;
	  	 Statement stmt = null;
	  	 ResultSet rs = null;
	  	 if(connect()) {		 
	  		 try{
	  			 stmt=connection.createStatement();
	  			 
	  			 if(!getTitre(achat)) {
	  				 sql="INSERT INTO avCAC(fk_numeroCptTitre,company,quantity,prixTotal) VALUES(?,?,?,?)";
	  				 PreparedStatement preStmt =connection.prepareStatement(sql);
	  				  preStmt.setInt(1,numeroCptTitre);
	  				   preStmt.setString(2,company); 
	  				   preStmt.setInt(3,qtt);
	  				   preStmt.setFloat(4,total);
	  				   preStmt.executeUpdate();	
	  				   preStmt.close();
	  			 }
	  			 else {				 
	  				String sql1="SELECT quantity,prixTotal FROM avCAC WHERE company='"+achat.getNomAction()+"' "
	  			 		+ "AND fk_numeroCptTitre='"+achat.getNumeroCptTitre()+"'";
	  				rs =stmt.executeQuery(sql1);
	  				if(rs.next()) {
	  					qtt=qtt + rs.getInt(1);
	  					total=total+rs.getFloat(2);			
	  				    sql="UPDATE avcac SET quantity = ?, prixTotal = ? WHERE company=? AND fk_numeroCptTitre=?";
	  					
	  					PreparedStatement preStmt =connection.prepareStatement(sql);
	  					   preStmt.setInt(1,qtt);
	  					   preStmt.setFloat(2,total);
	  					   preStmt.setInt(4,numeroCptTitre);
	  					   preStmt.setString(3,company); 
	        				   preStmt.executeUpdate();	
	        				   preStmt.close();
	  				}				
	  			 }
	  			
	  	       }catch (SQLException e) {			  
	  				e.printStackTrace();
	  			}
	  		}
	      }	
	
	
	public static boolean addTransactionVirement (String numeroCpt, String montant, String strDate, String numeroCptDest) {
		if (connect()) {
			try {
				String query = "INSERT INTO historique(numero, montant, operation, motif, date)" 
						+ "VALUES(?,?,?,?,?)";
				PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(query);
				   preStmt.setString(1,numeroCpt);
				   preStmt.setString(2,"- " +montant);
				   preStmt.setString(3,"débiter");
				   preStmt.setString(4,"Virement interne");
				   preStmt.setString(5,strDate);
				   preStmt.executeUpdate();
				   String query_2 = "INSERT INTO historique(numero, montant, operation, motif, date)" 
							+ "VALUES(?,?,?,?,?)";
					PreparedStatement preStmt_2 =(PreparedStatement) connection.prepareStatement(query_2);
					preStmt_2.setString(1,numeroCptDest);
					preStmt_2.setString(2,"+ " +montant);
					preStmt_2.setString(3,"créditer");
					preStmt_2.setString(4,"Virement interne");
					preStmt_2.setString(5,strDate);
				    preStmt_2.executeUpdate();
				   return true;
			}catch (Exception e) {
				return false;
			}finally {
				close();
			}
		}else
			return false;
	}
	
	public static boolean addTransactionVirementExterne (String numeroCpt, String montant, String strDate) {
		if (connect()) {
			try {
				String query = "INSERT INTO historique(numero, montant, operation, motif, date)" 
						+ "VALUES(?,?,?,?,?)";
				PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(query);
				   preStmt.setString(1,numeroCpt);
				   preStmt.setString(2,"- " +montant);
				   preStmt.setString(3,"débiter");
				   preStmt.setString(4,"Virement externe");
				   preStmt.setString(5,strDate);
				   preStmt.executeUpdate();
				   return true;
			}catch (Exception e) {
				return false;
			}finally {
				close();
			}
		}else
			return false;
	}
	
	public static boolean addTransactionVirementVente (String numeroCpt, String montant, String strDate) {
		if (connect()) {
			try {
				String query = "INSERT INTO historique(numero, montant, operation, motif, date)" 
						+ "VALUES(?,?,?,?,?)";
				PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(query);
				   preStmt.setString(1,numeroCpt);
				   preStmt.setString(2,"+ " +montant);
				   preStmt.setString(3,"créditer");
				   preStmt.setString(4,"Vente action");
				   preStmt.setString(5,strDate);
				   preStmt.executeUpdate();
				   return true;
			}catch (Exception e) {
				return false;
			}finally {
				close();
			}
		}else
			return false;
	}
	
	public static boolean addTransactionAlimenter (String numeroCpt, String montant, String strDate) {
		if (connect()) {
			try {
				String query = "INSERT INTO historique(numero, montant, operation, motif, date)" 
						+ "VALUES(?,?,?,?,?)";
				PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(query);
				   preStmt.setString(1,numeroCpt);
				   preStmt.setString(2,"+ " +montant);
				   preStmt.setString(3,"créditer");
				   preStmt.setString(4,"Alimentation du compte");
				   preStmt.setString(5,strDate);
				   preStmt.executeUpdate();
				   return true;
			}catch (Exception e) {
				return false;
			}finally {
				close();
			}
		}else
			return false;
	}
	
	public static boolean addTransactionBourse (String numeroCpt, String montant, String strDate) {
		if (connect()) {
			try {
				String query = "INSERT INTO historique(numero, montant, operation, motif, date)" 
						+ "VALUES(?,?,?,?,?)";
				PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(query);
				   preStmt.setString(1,numeroCpt);
				   preStmt.setString(2,"- " +montant);
				   preStmt.setString(3,"débiter");
				   preStmt.setString(4,"Achat action");
				   preStmt.setString(5,strDate);
				   preStmt.executeUpdate();
				   return true;
			}catch (Exception e) {
				return false;
			}finally {
				close();
			}
		}else
			return false;
	}
	
	public static ArrayList<historiqueTransaction> getTransaction(String nom,String prenom){
		Cpt comptecourant = getSolde(nom,prenom);
		int compteEpargne = getNumCptEpargne(nom,prenom);
		int compteTitre = getNumCptTitre(nom,prenom);
		//System.out.println(compteTitre);
		ArrayList<historiqueTransaction> histList = new ArrayList<historiqueTransaction>();
		if (connect()) {
			try {
	            String query = "SELECT * FROM historique WHERE (numero = ? OR numero = ? OR numero = ?) ORDER BY date ASC";
	            PreparedStatement st = connection.prepareStatement(query);
	            st.setInt(1, comptecourant.getNumero());
	            st.setInt(2, compteEpargne);
	            st.setInt(3, compteTitre);
	            ResultSet rs = st.executeQuery();
	            while (rs.next()) {
	            	historiqueTransaction hist = new historiqueTransaction();
	            	hist.setNum(rs.getString(1));
	            	hist.setMontant(rs.getString(2));
	            	hist.setOperation(rs.getString(3));
	            	hist.setMotif(rs.getString(4));
	            	hist.setDate(rs.getString(5));
	            	System.out.println(hist.getMontant());
	            	histList.add(hist);
	            }
	            return histList;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}finally {
				close();
			}
		}else {
			return null;
		}
	}
	
	public static int CheckCptEpargne(String mdp, String numeroCptDest) {
		int flag = 0;
		if (connect()) {
			try {
				String query = "SELECT numeroCptEpargne FROM compteepargne INNER JOIN client "
						+ "ON client.id_client = compteepargne.idClient WHERE client.mdp_cli = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, mdp);
				
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					if (resultSet.getString(1).equals(numeroCptDest)) {
						flag = 1;
					}
				}
				return flag;
			}catch (Exception e) {
				return flag;
			}finally {
				close();
			}
		}else {
			return flag;
		}
	}
	
	public static boolean CheckNumDest(String numeroCptDest) throws Exception{
		if (connect()) {
			try {
				String query = "SELECT numeroCptCourant FROM comptecourant WHERE numeroCptCourant = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, numeroCptDest);
				ResultSet resultSet = ps.executeQuery();
				if (resultSet.next()) {
					return true;
				}else {
					return false;
				}
			 }catch (Exception e) {
				 return false;
			 }finally {
				 close();
			 }
		}else {
			return false;
		}
	}
	
	public static boolean virementInterne(String numeroCpt, int soldeVirement, String mdp, String numeroCptDest) throws Exception{
		boolean CptcourantDest = CheckNumDest(numeroCptDest);
		// Verification que le numero destinataire est le compte épargne
		int check = CheckCptEpargne(mdp, numeroCptDest);
		//Verification que le numero émetteur est le compte courant
		int check_1 = CheckCptEpargne(mdp, numeroCpt);
		if (connect()) {
			try {
				 String sql;
				Statement stmt = connection.createStatement();
				if (check_1 == 0) {
					 sql="SELECT idClient,solde FROM comptecourant where numeroCptCourant="+numeroCpt;
				}else {
					 sql="SELECT idClient,solde FROM compteepargne where numeroCptEpargne="+numeroCpt;
				}	
				ResultSet rs =stmt.executeQuery(sql);
				if(rs.next()){
			    	int idClient = rs.getInt(1);
			    	int solde = rs.getInt(2);
			    		if(solde>=50) 
			    		{
			    			if(soldeVirement >= 10 ) { 
			    			//si le mdp est bon,on alimente le compteCourant(l'ajout le montant saisi)
								String sql1="SELECT mdp_cli FROM client WHERE id_client="+idClient ;
			    				ResultSet re =stmt.executeQuery(sql1);
			    			if(re.next()) {
			    				 if (check_1 == 0) {
				    				  String virement="UPDATE comptecourant SET solde = solde - ? where idClient=? ";    	
				    				  
				    				// PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(aliment);
				    				 PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(virement);
				    				 
				    				 preStmt.setInt(1,soldeVirement);
				    				  preStmt.setInt(2,idClient);
				    				  //On alimente le compte épargne si c'est le compte destinataire
				    				  if (check == 1) {
				    					  String aliment="UPDATE compteepargne SET solde =  solde + ? where idClient=? "; 
							    			 PreparedStatement preStmt1 =(PreparedStatement) connection.prepareStatement(aliment);
						    				  
						    				  preStmt1.setInt(1,soldeVirement);
						    				  preStmt1.setInt(2,idClient);
						    				  preStmt1.executeUpdate();
						    				  preStmt.executeUpdate();
						    				  System.out.println("ok");
						    				  //preStmt.close(); 
						    				  return true;
				    				  }else {
				    					  if (CptcourantDest) {
				    						  String aliment="UPDATE comptecourant SET solde =  solde + ? where numeroCptCourant=? "; 
								    			 PreparedStatement preStmt1 =(PreparedStatement) connection.prepareStatement(aliment);
							    				  
							    				  preStmt1.setInt(1,soldeVirement);
							    				  preStmt1.setString(2,numeroCptDest);
							    				  preStmt1.executeUpdate();
							    				  System.out.println("ok");
							    				  //preStmt.close(); 
							    				  return true;
				    					  }else {
				    						  throw new Exception("Numero de compte destinataire incorrect");
				    					  }
				    				  }
		    					
			    			}
			    			//si le compte  diter est le compte ¨¦pargne
			    			 else {
			    				 String virement="UPDATE compteepargne SET solde = solde - ? where idClient=? ";    	
			    				 PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(virement);
			    				 
			    				 preStmt.setInt(1,soldeVirement);
			    				  preStmt.setInt(2,idClient);
			    				  
		    					 String aliment="UPDATE comptecourant SET solde =  solde + ? where idClient=? "; 
				    			 PreparedStatement preStmt1 =(PreparedStatement) connection.prepareStatement(aliment);
			    				  
			    				  preStmt1.setInt(1,soldeVirement);
			    				  preStmt1.setInt(2,idClient);
			    				  preStmt.executeUpdate();
			    				  preStmt1.executeUpdate();
			    				  System.out.println("ok");
			    				  return true;
			    			}
		    	}else {
		    		throw new Exception("Votre mot de passe est incorrect!");
		    		  }
	    			}else {
    				throw new Exception("Le montant min est 10 euros.");
	    			 }
			    		}else {
			    			throw new Exception("Votre solde est inf¨¦ieur ¨¤ 50 euros.");
			    		}
				}
			}catch (Exception e) {
			}finally {
				close();
			}
		}else {
			return false;
		}
		return false;
	}
	
	
	
	public static int virementExterne(String numeroCpt, int soldeVirement, String mdp, String numeroCptDest) throws Exception{
		int flag = 0;
		if (connect()) {
		  try {
			Statement stmt = connection.createStatement();
			String sql="SELECT idClient, solde FROM comptecourant where numeroCptCourant="+numeroCpt;
			ResultSet rs =stmt.executeQuery(sql);
		    if(rs.next()){
		    	int idClient = rs.getInt(1);
		    	int solde = rs.getInt(2);
		    		if(solde>=50) 
		    		{
		    			if(soldeVirement >= 10 ) { 
		    				String sql1="SELECT mdp_cli FROM client where id_client="+idClient;
					    	ResultSet re =stmt.executeQuery(sql1);
					    	if(re.next()) {
		    			if(re.getString(1).equals(mdp)) {
		    			
			    			//si le mdp est bon,on alimente le compteCourant(l'ajout le montant saisi)
			    			
		    				String virement="UPDATE comptecourant SET solde = solde - ? where idClient=? ";    	
		    				  
		    				// PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(aliment);
		    				 PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(virement);
		    				 preStmt.setInt(1,soldeVirement);
		    				 preStmt.setInt(2,idClient);
		    				 preStmt.executeUpdate();
				    				  //preStmt.close(); 
				    		flag = 1;
		    			}
					    	}
					    	else {
					    		throw new Exception("Votre mot de passe est incorrect!");
					    	}
		    			}
		    			else {
		    				throw new Exception("Le montant min est 10 euros.");
		    			}
		    		}
		    		else {
		    			throw new Exception("Le montant de votre compte courant est insuffisant.");
		    		}		    		
		    }
		    else
		    {
		    	throw new Exception("Le numéro compte n'existe pas.Vérifiez votre numéro compte.");
		    }
		  }catch(SQLException e) {
			  e.printStackTrace(); 	
		  }finally {
			  close();}
		}
		return flag;
	}
	
	public static int Alimenter(String numeroCpt, int soldeAjout, String mdp) throws Exception{
		int flag = 0;
		if (connect()) {
		  try {
			Statement stmt = connection.createStatement();
			String sql="SELECT idClient,solde FROM comptecourant where numeroCptCourant="+numeroCpt;
		    ResultSet rs =stmt.executeQuery(sql);
		    if(rs.next()){
		    	int idClient = rs.getInt(1);
		    	int solde = rs.getInt(2);
		    		if(soldeAjout >= 10 ) { 
		    			sql="SELECT mdp_cli FROM client where id_client="+idClient;
		    			rs =stmt.executeQuery(sql);
		    			//si le mdp est bon,on alimente le compteCourant(l'ajout le montant saisi)
		    			if(rs.next()) {
		    			  if(rs.getString(1).equals(DAO.Encrypter(mdp))) {
		    				  String aliment="UPDATE comptecourant SET solde = ?, status = ? where idClient=?";    				  
		    				 PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(aliment);
		    				  preStmt.setInt(1,solde + soldeAjout);
		    				  preStmt.setInt(2,1);
		    				  preStmt.setInt(3,idClient);
		    				  preStmt.executeUpdate();
		    				  //preStmt.close(); 
		    				  flag = 1;
		    			  }
		    		}
		    		else {
		    			throw new Exception("Votre mot de passe est incorrect!");
		    		}
		    	}
		    	else {
		    		throw new Exception("Le montant minimum est 10 euros.");
		    		}
		    }else
		    {
		    	throw new Exception("Le numéro compte n'existe pas.Vérifiez votre numéro de compte.");
		    }
	}catch(SQLException e) {
		e.printStackTrace(); 	
    }finally {
		close();}
	}
		return flag;
}
	
	
	public static boolean insererCptEpargne(int solde, String numeroCptCourant, String mdp) throws Exception {
		min=5556;
		max=9999;
		len = max-min+1;
		int numero1;
		int idClient;
		float soldeCourant;
		String mdp_cli;
		Statement stmt1;
		if (connect()) {
			stmt1 = connection.createStatement();
			 ResultSet rs = null;
			 //définir un numéro compte ¨¦pargne al¨¦ratoire
			   do {
			   numero1=InsererCpt.randomNumeroCompte(min,max,len);
			   String sql="SELECT * FROM compteEpargne where numeroCptEpargne="+numero1;
			    rs =stmt1.executeQuery(sql);
			   // System.out.println(numero1);
			   }while(rs.next());
			   
			   String sql1="SELECT idClient,solde FROM comptecourant WHERE numeroCptCourant="+numeroCptCourant;
			   rs=stmt1.executeQuery(sql1);
			   if(rs.next()) {
				   idClient = rs.getInt(1);//obtenir idClient
				   soldeCourant = rs.getFloat(2);// obtenir solde du cpt Courant. determiner si deja alimente?
				   if(soldeCourant < 10) {
					   throw new Exception("Vous ne pouvez pas créer un compte épargne parce que votre compte courant n'est pas encore aliment¨¦.");  
				   }else {
				   
		// déterminer si le client a d'un cpt ¨¦pargne.
				   String sql2="SELECT * FROM compteEpargne WHERE idClient="+idClient;
				   rs=stmt1.executeQuery(sql2);
				   if(rs.next()) {
					   System.out.println("exception cptE");
					   throw new Exception( "Attention! Vous avez d'un compte ¨¦pargne. Contactez votre conseiller pour plus d'information" ); 
				   }else {
		// déterminer si le mdp est bon.	
					   System.out.println(idClient);
					   String sql3="SELECT mdp_cli FROM client WHERE id_client="+idClient ; 
					   rs =stmt1.executeQuery(sql3);
					  if(rs.next()) {
						  mdp_cli = rs.getString(1);//obtenir mdp_cli
						  //System.out.println(mdp_cli);
						  
						  	if(!mdp_cli.equals(mdp)) {
							  System.out.println("exception mdp");
							  throw new Exception( "Attention! Votre mot de passe et/ou votre numero compte semble(nt) incorrects!");
						   }else
						   {
							   if(solde >= 10){
								   //System.out.println(solde);
								 //créer un cpt épargne(insérer dans la BDD)
								 
								   								 
								 //Réduire le montant saisi sur la solde du compte courant
								   String sql4="SELECT solde FROM compteCourant WHERE idClient="+idClient;
								   rs =stmt1.executeQuery(sql4);
								   if(rs.next()) {
									    soldeCourant=rs.getInt(1);									  
									   System.out.println("solde client:"+soldeCourant);
									   float soldeC= soldeCourant - solde;
									   System.out.println("soldeC:"+soldeC);
									  if(soldeC > 0) {
										  String insert = "INSERT INTO compteepargne(idClient, numeroCptEpargne, solde) VALUES(?,?,?)";
										   PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(insert);
										   preStmt.setInt(1,idClient);
										   preStmt.setInt(2,numero1);
										   preStmt.setInt(3,solde);
										   preStmt.executeUpdate();
									
									   sql4="UPDATE compteCourant SET solde = ? where idClient=?"; 
									   preStmt =(PreparedStatement) connection.prepareStatement(sql4);
									   preStmt.setFloat(1,soldeC);
									   preStmt.setInt(2,idClient);
									   preStmt.executeUpdate();		
									   return true;
									  }
									  else {
										  throw new Exception( "Attention! Le solde du compte courant est moins que le montant que vous saissiez."); 
								      }
								    }else {
								    	return false;
								    }
								  // System.out.println("sucess insertion"); 
							   }else {
								   //System.out.println("exception solde");
								   throw new Exception( "Attention! le montant du versement initial minimum est 10euros." );
						  	}
						   }						   }else{
					        throw new Exception( "Attention! Vous n'avez pas encore un compte. Veuillez-vous le ouvrir?" );
					    }
					 }		   
				   }
			   }else {
				   return false;
				   }
			   }else {
				   return false;
			   }
	}
	
	//Enregistrement des informations du compte courant dans le fichier CSV
	public static void getCSVcourant(String nom,String prenom){
		if (connect()) {
			try {
	           
	            String query = "SELECT numeroCptCourant, solde FROM comptecourant INNER JOIN client ON client.id_client = comptecourant.idClient WHERE client.nom_cli = ? and client.prenom_cli = ?";
	            PreparedStatement st = connection.prepareStatement(query);
	            st.setString(1, nom);
	            st.setString(2, prenom);
	            ResultSet rs = st.executeQuery();
	            
	            wb = new HSSFWorkbook();
	            HSSFSheet sheet = wb.createSheet("Excel Sheet");
	            HSSFRow rowhead = sheet.createRow((short) 0);
	            rowhead.createCell((short) 0).setCellValue(nom + " " + prenom);
	            rowhead.createCell((short) 3).setCellValue("n compte courant");
	            rowhead.createCell((short) 4).setCellValue("solde");
	
	            int index = 1;
	            while (rs.next()) {
	
	                    HSSFRow row = sheet.createRow((short) index);
	                    row.createCell((short) 3).setCellValue(rs.getInt(1));
	                    row.createCell((short) 4).setCellValue(rs.getInt(2) + " euros");
	                    index++;
	            }
	            for(int colNum = 0; colNum<rowhead.getLastCellNum();colNum++) { 
	                wb.getSheetAt(0).autoSizeColumn(colNum);
				}
	            //FileOutputStream fileOut = new FileOutputStream(new File("C:\\Users\\horizon\\eclipse-workspace\\ProjetS8\\WebContent\\releves.csv"));
	            FileOutputStream fileOut = new FileOutputStream(new File("./WebContent/releves.csv"));

	            wb.write(fileOut);
	            fileOut.close();
	            System.out.println("Data is saved in excel file.");
	            rs.close();
	    } catch (Exception e) {
	    }
			finally {
				close();}
		}
	}

	//Enregistrement des informations du compte épargne dans le fichier CSV
	public static void getCSVepargne(String nom,String prenom){
		if (connect()) {
			try {
	            
	            String query = "SELECT numeroCptEpargne, solde FROM compteepargne INNER JOIN client ON client.id_client = compteepargne.idClient WHERE client.nom_cli = ? and client.prenom_cli = ?";
	            PreparedStatement st = connection.prepareStatement(query);
	            st.setString(1, nom);
	            st.setString(2, prenom);
	            ResultSet rs = st.executeQuery();
	            
	            FileInputStream file = new FileInputStream(new File("./WebContent/releves.csv"));
	            wb = new HSSFWorkbook(file);
	            HSSFSheet sheet = wb.getSheetAt(0);
	            HSSFRow rowhead = sheet.getRow(0);
	            if (rs.next()) {
	            	rowhead.createCell((short) 6).setCellValue("n compte epargne");
	            	rowhead.createCell((short) 7).setCellValue("solde");

		            int index = 1;
	
		                    HSSFRow row = sheet.getRow((short) index);
		                    row.createCell((short) 6).setCellValue(rs.getInt(1));
		                    row.createCell((short) 7).setCellValue(rs.getInt(2) + " euros");
		                    index++;
		            
	            }
	            for(int colNum = 0; colNum<rowhead.getLastCellNum();colNum++)   
	                wb.getSheetAt(0).autoSizeColumn(colNum);
	            FileOutputStream fileOut = new FileOutputStream("./WebContent/releves.csv");
	            wb.write(fileOut);
	            fileOut.close();
	            System.out.println("Data is saved in excel file.");
	            rs.close();
	    } catch (Exception e) {
	    }
			finally {
				close();}
		}
    }
	
	
	
	//Enregistrement des transactions dans le fichier CSV
	public static void getTransactionCSV(String nom,String prenom){
		ArrayList<historiqueTransaction> histList = getTransaction(nom,prenom);
			try {
	            
	            FileInputStream file = new FileInputStream(new File("./WebContent/releves.csv"));
	            wb3 = new HSSFWorkbook(file);
	            HSSFSheet sheet = wb3.getSheetAt(0);
	            HSSFRow rowhead = sheet.createRow(3);
	            rowhead.createCell((short) 4).setCellValue("n compte");
	            rowhead.createCell((short) 5).setCellValue("operation");
	            rowhead.createCell((short) 6).setCellValue("motif");
	            rowhead.createCell((short) 7).setCellValue("montant");
	            rowhead.createCell((short) 8).setCellValue("date");

	            int index = 4;
	            for (int i = 0; i < histList.size(); i++) {

	                    HSSFRow row = sheet.createRow((short) index);
	                    row.createCell((short) 4).setCellValue(histList.get(i).getNum());
	                    row.createCell((short) 5).setCellValue(histList.get(i).getOperation());
	                    row.createCell((short) 6).setCellValue(histList.get(i).getMotif());
	                    row.createCell((short) 7).setCellValue(histList.get(i).getMontant() + " euros");
	                    row.createCell((short) 8).setCellValue(histList.get(i).getDate());
	                    index++;
	            }
	            for(int colNum = 0; colNum<rowhead.getLastCellNum();colNum++)   
	                wb3.getSheetAt(0).autoSizeColumn(colNum);

	            FileOutputStream fileOut = new FileOutputStream(new File("./WebContent/releves.csv"));
	            wb3.write(fileOut);
	            fileOut.close();
	            System.out.println("Data is saved in excel file.");
	    } catch (Exception e) {
	    	e.printStackTrace();;
	    }
			finally {
				close();}
		}
	
	
	/**
	 * 
	 * @param achat
	 * @param total
	 */
	public static void insererAction(Action action) {
	    float prix=action.getValueAction();
		 int numeroCptTitre=action.getNumeroCptTitre();
		 String company = action.getNomAction();
		 int qtt = action.getNumAchat();
		 float total = action.getTotal();
		 String sql;
		 try {
			 sql="INSERT INTO avCAC(fk_numeroCptTitre,company,quantity,prixTotal,prix) VALUES(?,?,?,?,?)";
			 PreparedStatement preStmt =connection.prepareStatement(sql);
			  preStmt.setInt(1,numeroCptTitre);
			   preStmt.setString(2,company); 
			   preStmt.setInt(3,qtt);
			   preStmt.setFloat(4,total);
			   preStmt.setFloat(5,prix);
			   preStmt.executeUpdate();	
			   preStmt.close();
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 
	}


	/**
	 * 
	 * @param achat
	 * @param total
	 */
	public static void UpdateAction(Action action, String achat_vente) {
		 int numeroCptTitre=action.getNumeroCptTitre();
		 String company = action.getNomAction();
		 int qtt = action.getNumAchat();
		 float total = action.getTotal();
		 String sql;
		 Statement stmt = null;
		 ResultSet rs = null;
		if(connect()) {
		 try {
			 stmt=connection.createStatement();
			 String sql1="SELECT quantity,prixTotal FROM avCAC WHERE company='"+action.getNomAction()+"' "
				 		+ "AND fk_numeroCptTitre='"+action.getNumeroCptTitre()+"'";
					rs =stmt.executeQuery(sql1);
					if(rs.next()) {				
						if(achat_vente.equals("a")) {
						qtt=qtt + rs.getInt(1);
						total=total+rs.getFloat(2);	
						}
					    sql="UPDATE avCAC SET quantity = ?, prixTotal = ? WHERE company=? AND fk_numeroCptTitre=?";
						
						PreparedStatement preStmt =connection.prepareStatement(sql);
						   preStmt.setInt(1,qtt);
						   preStmt.setFloat(2,total);
						   preStmt.setInt(4,numeroCptTitre);
						   preStmt.setString(3,company); 
	   				   preStmt.executeUpdate();	
	   				   preStmt.close();
						}
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 }
	}

	/**
	 * 
	 * @param achat
	 * @param total
	 */
	public static void AcheterAction(Action action,float total) {
		if(connect()) {		 
			 try{			 
				 if(!chercherAction(action)){
					 insererAction(action);				
				 }
				 else {	
					 UpdateAction(action,"a");	
					}				
				 }catch (Exception e) {			  
					e.printStackTrace();
				}
			}
	    }

	public static boolean chercherAction(Action action) {
		 boolean flag=false;
		 String sql;
		 ResultSet rs = null;
		 sql="SELECT * FROM avCAC where company=? And fk_numeroCptTitre=?";
		if(connect()) {
			try {
			    PreparedStatement ps = connection.prepareStatement(sql);			
				ps.setString(1, action.getNomAction());
				ps.setInt(2, action.getNumeroCptTitre());
				rs = ps.executeQuery();	
				if(rs.next()) {
					flag=true;
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	 
		return flag;
	}

	public static Action getAction(int numeroCptTitre, String nomAction) {
		Action action = new Action();
		String sql;
		 ResultSet rs = null;
		 sql="SELECT * FROM avCAC where company=? And fk_numeroCptTitre=?";
		if(connect()) {
			try {
			    PreparedStatement ps = connection.prepareStatement(sql);			
				ps.setString(1, nomAction);
				ps.setInt(2, numeroCptTitre);			
				rs = ps.executeQuery();	
				if(rs.next()) {
					action.setNumeroCptTitre(rs.getInt(2));
					action.setnomAction(rs.getString(3));
					action.setNumAchat(rs.getInt(4));
					action.setTotal(rs.getFloat(5));
					action.setValueAction(rs.getFloat(6));
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	 
		return action;
	}
	 
	/**
	 *  Permettre de trouver tous des actions acht¨¦es attach¨¦s du compte titre
	 * @param numeroCptTitre
	 * @return
	 */
	 public static List<Action> selectActions(int numeroCptTitre) {
		 List<Action> list=new ArrayList<>(); 
			if (connect()) {
				try {
					String query = "SELECT * FROM avCAC WHERE fk_numeroCptTitre=?";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setInt(1, numeroCptTitre);
					ResultSet resultSet = ps.executeQuery();
	     
					while (resultSet.next()) {
					  Action titre = new Action();
						titre.setnomAction(resultSet.getString(3));
						titre.setNumAchat(resultSet.getInt(4));
						titre.setTotal(resultSet.getFloat(5));
						titre.setValueAction(resultSet.getFloat(6));	
						list.add(titre);				
					}
												
				}catch (Exception e) {
					return null;
				}finally {
					close();
				}
				return list;	
			}
			else {
				return null;
			}		
		}
 }