package DAO.jee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import Cpt.InsererCpt;



public class DAO {
	
	public static int min;
	public static int max;
	public static int len = max-min+1;
	
	private final static String url = "jdbc:mysql://localhost/projects8";
	private final static String username = "root";
	private final static String password = "";
	static Connection connection = null;
	
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
	
	public static boolean CheckMdp(String pwd, int idClient) {
		boolean mdp = true;
		if (connect()) {
			try {
				String query = "SELECT mdp_cli FROM client WHERE id_client="+idClient;
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
	

	public static boolean insererCptTitre(int idClient) {
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
		    			  if(rs.getString(1).equals(mdp)) {
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
		    		throw new Exception("Le montant min est 10 euros.");
		    		}
		    }else
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
	
	
	
	
	public static int insererCptEpargne(int solde, String numeroCptCourant, String mdp) throws Exception {
		min=5556;
		max=9999;
		len = max-min+1;
		int flag=0;
		int numero1;
		int idClient;
		int statu;// le statu du cpt courant(1: déj?aliment? 0: pas encore aliment?
		String mdp_cli;
		Statement stmt1;
		if (connect()) {
		try {
			 //System.out.println(numeroCptCourant);
			 stmt1 = connection.createStatement();
			 ResultSet rs = null;
			 //définir un numéro compte épargne alératoire
			   do {
			   numero1=InsererCpt.randomNumeroCompte(min,max,len);
			   String sql="SELECT * FROM compteEpargne where numeroCptEpargne="+numero1;
			    rs =stmt1.executeQuery(sql);
			   // System.out.println(numero1);
			   }while(rs.next());
			   
			   
			   String sql1="SELECT idClient,status FROM comptecourant WHERE numeroCptCourant="+numeroCptCourant;
			   rs=stmt1.executeQuery(sql1);
			   if(rs.next()) {
				   idClient = rs.getInt(1);//obtenir idClient
				   statu = rs.getInt(2);// obtenir solde du cpt Courant. determiner si deja alimente?
				   if(statu != 1) {
					   throw new Exception("Vous ne pouvez pas cr¨¦er un compte ¨¦pargne parce que votre compte courant n'est pas encore aliment?");  
				   }else {
				   
		// déterminer si le client a déj?un cpt épargne.
				   String sql2="SELECT * FROM compteEpargne WHERE idClient="+idClient;
				   rs=stmt1.executeQuery(sql2);
				   if(rs.next()) {					   
					   throw new Exception( "Attention! Vous avez d'un compte ¨¦pargne. Contactez votre conseiller pour plus d'information" ); 
				   }else {
		// déterminer si le mdp est bon.	
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
						  		   if(solde > 10){
								   //System.out.println(solde);
								 //créer un cpt épargne(insérer dans la BDD)
								  String insert = "INSERT INTO compteepargne(idClient, numeroCptEpargne, solde) VALUES(?,?,?)";
								   PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(insert);
								  /* preStmt.setInt(1,idClient);
								   preStmt.setInt(2,numero1);
								   preStmt.setInt(3,solde);
								   preStmt.executeUpdate();*/
								   								 
								 //Réduire le montant saisi sur la solde du compte courant
								   String sql4="SELECT solde FROM compteCourant WHERE idClient="+idClient;
								   rs =stmt1.executeQuery(sql4);
								   if(rs.next()) {
									   int soldeCourant=rs.getInt(1);									  
									   System.out.println("solde client:"+soldeCourant);
									   int soldeC= soldeCourant - solde;
									   System.out.println("soldeC:"+soldeC);
									  if(soldeC > 0) {
										  System.out.println("yes");
									   sql4="UPDATE compteCourant SET solde = ? where idClient=?"; 
									   /*preStmt =(PreparedStatement) connection.prepareStatement(sql4);
									   preStmt.setInt(1,soldeC);
									   preStmt.setInt(2,idClient);
									   preStmt.executeUpdate();		*/			   
								
									  }
									  else {
										  throw new Exception( "Attention! La solde du compte courant est moins que le montant que vous saissiez."); 
								      }
								      }
								   flag=1;
								   System.out.println("sucess insertion"); 
							   }else {
								   System.out.println("exception solde");
								   throw new Exception( "Attention! le montant du versement initial minimum est 10euros." );
							   }
						   }
						   
					  }
				   }
				   }
			   }
			   
				   				  
		//le cient n'exist pas   
			   else{
			        throw new Exception( "Attention! Vous n'avez pas encore un compte. Veuillez-vous le ouvrir?" );
			    }
			 }		   
		  catch (SQLException e) {
			// TODO Auto-generated catch block			  
			e.printStackTrace();
		}
		}
		return flag;		
	}

}
