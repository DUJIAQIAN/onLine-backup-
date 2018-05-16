package DAO.manager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import cfo.InsererMdp;
import Inscription.Conseiller;


public class DAO {

	public static int min;
	public static int max;
	public static int len = max-min+1;
	
	private final static String url = "jdbc:mysql://localhost/projects8";
	private final static String username = "root";
	private final static String password = "";
	static Connection connection = null;
	/**
	 * connect the database
	 * @return ture or false
	 * @author Du Jiaqian 
	 */
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
	
	/** Comparaison of the login and password entered with those present in the database
	 * @param login = name of manger
	 * @param password password for login
	 * @return name and last name of manager
	 * @author Du Jiaqian 
	 */
	public static String login(String login, String password) {
		if (connect()) {
			try {
				String query = "SELECT Nom_manager, Prenom_manager FROM manager WHERE Nom_manager= ? and mdp_manager = ?";
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
	
	/**
	 * Get the list of current account numbers
	 * @return a list of current account numbers
	 * @author Du Jiaqian 
	 */
	public static List<String> getListNumero(){
		List<String> listNumero = new ArrayList<>();
		if (connect()) {
			try {
				String query = "SELECT numero FROM historique";
				PreparedStatement stmt =(PreparedStatement) connection.prepareStatement(query);		
				ResultSet rs = stmt.executeQuery();
     
				while (rs.next()) {
					if(!listNumero.contains(rs.getString(1)))
					listNumero.add(rs.getString(1));
				}
											
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				close();
			}
			return listNumero;	
		}
		else {
			return listNumero;
		}		
	}
		



/**
 * Get the list of clients' deposits (the account number of client, the deposit amount and the reason of deposit)
 * @param numero current account number
 * @return a list deposit
 * @author Du Jiaqian 
 */
	public static List<transaction> getDepots(String numero) {
		 	List<transaction> list=new ArrayList<>();
		 	double montant,somme=0;
		 	String[] logs;
		 	
			if (connect()) {
				try {
					String query = "SELECT * FROM historique WHERE motif=? AND numero=?";
					PreparedStatement stmt =(PreparedStatement) connection.prepareStatement(query);
					stmt.setString(1, "Alimentation du compte");
					stmt.setString(2, numero);
					ResultSet rs = stmt.executeQuery();
	     
					while (rs.next()) {
						transaction d = new transaction();
						d.setNumero(rs.getString(1));
						logs = rs.getString(2).split(" ");
						d.setMontant(logs[1]); 
						d.setMotif(rs.getString(4));
						//Double.parseDouble(logs[1])
					 	list.add(d);			
					}
												
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					close();
				}
				return list;	
			}
			else {
				return list;
			}		
		}

	/**
	 *Get all of the transaction history in the database
	 * @return a transaction list
	 * @author Du Jiaqian 
	 */
	public static List<transaction> getTransaction(){
		List<transaction> list = new ArrayList<>();
		String query = "SELECT * FROM historique";
		
		if(connect()) {
		try {
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				transaction t = new transaction();
				t.setNumero(rs.getString(1));
				t.setMontant(rs.getString(2));
				t.setOperation(rs.getString(3));
				t.setMotif(rs.getString(4));
				t.setDate(rs.getString(5));
				list.add(t);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return list;		
	}

	
	/**
	 *Add a front-line advisor in the database
	 *@param conseuller a front office advisor
	 * @return true if the front office advisor is created and false if he isn't created
	 * @author Diallo Ibrahim
	 */
	
	public static boolean InsererConseiller(Conseiller conseiller) {
		if (connect()) {
			try {
				String insert =  "INSERT INTO conseiller(sexe_cfo, nom_cfo, prenom_cfo, date_naissance_cfo, email_cfo, telephone_cfo, adresse_cfo,nationalite_cfo)" 
						+ "VALUES(?,?,?,?,?,?,?,?)";
				PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(insert);
				preStmt.setString(1,conseiller.getSexe());
				preStmt.setString(2,conseiller.getNom());
				preStmt.setString(3,conseiller.getPrenom());
				preStmt.setDate(4,conseiller.getDateNaissance());
				preStmt.setString(5,conseiller.getEmail());
				preStmt.setString(6,conseiller.getTelephone());
				preStmt.setString(7,conseiller.getAdresse());
				preStmt.setString(8,conseiller.getNationalite());
				preStmt.executeUpdate();
				
				
				
				return true;
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}else {
			return false;
		}
	}
	
	/**
	 *Remove a front-line advisor in the database
	 *@param name the name of advisor
	 * @return 0 if not successful to remove an advior, 1 if successful
	 * @author Diallo Ibrahim
	 */
	public static int supprimerConseiller(String name) throws Exception{
		
		if (connect()) {
		  try {
			//String query="DELETE FROM conseiller WHERE `nom_cfo` = "+name+""; 
			String query="DELETE FROM conseiller WHERE nom_cfo = ?";
			PreparedStatement myStmt = connection.prepareStatement(query);
			myStmt.setString(1, name);
			//ResultSet resultSet = myStmt.executeQuery(query);
			return myStmt.executeUpdate();
		    
		  }catch (Exception e) {
				return 0;
			}finally {
				close();}
		}
		else {
			return 0;
		}
		//return len;

}
	/**
	 * Get the id of a front-line advisor from the database
	 * @param nom the name of advisor
	 * @param prenom first name of advisor
	 * @return an id
	 * @author Diallo Ibrahim
	 */
	public static int getIdConseiller(String nom, String prenom) {
		if (connect()) {
			try {
				int idCfo;
				String query = "SELECT * FROM conseiller WHERE nom_cfo = ? and prenom_cfo = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, nom);
				ps.setString(2, prenom);
				ResultSet resultSet = ps.executeQuery();
				if (resultSet.next()) {
					idCfo = resultSet.getInt(1);
					return idCfo;
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
	/**
	 * Get the list of front-line advisors
	 * @return a list of front-line advisors 
	 * @author Diallo Ibrahim
	 */
	public static List<Conseiller> selectInfos() {
		 List<Conseiller> list=new ArrayList<>(); 
			if (connect()) {
				try {
				String query = "SELECT nom_cfo FROM conseiller";
				PreparedStatement ps = connection.prepareStatement(query);
				
				ResultSet resultSet = ps.executeQuery();
				while (resultSet.next()) {
					Conseiller conseiller = new Conseiller();
					conseiller.setNom(resultSet.getString(1));
				  //conseiller.setPrenom(resultSet.getString(2));
					list.add(conseiller);
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
/**
 * check password from database
 * @param pwd the password of account advisor
 * @return true if password exists,false if password doesn't exist
 * @author Diallo Ibrahim
 */
	
	public static boolean CheckMdp(String pwd) {
		boolean mdp = true;
		if (connect()) {
			try {
				String query = "SELECT mdp_cfo FROM conseiller";
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
	
	/**
	 * encrypts the password in the database
	 * @param pwd password that the client entered during registration
	 * @return a encrypted password
	 * @throws Exception the errors of encrypting password
	 * @author Diallo Ibrahim
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
	 * Get a password by random and insert into database
	 * @param id_cfo identifiant of advisor
	 * @return a password
	 * @throws Exception  the errors
	 * @author Du jiaqian
	 */
	public static boolean insererMdp(int id_cfo) throws Exception {
		min=1000;
		max=10000;
	    len = max-min+1;
		 	String number;
		if (connect()) {
			try {
				  
				  ResultSet rs = null;
				  do {
					   number=InsererMdp.randomMdp(min,max,len);
					   String sql="SELECT * FROM password where mdp="+number;
					   PreparedStatement stmt =(PreparedStatement) connection.prepareStatement(sql);
					   rs = stmt.executeQuery();
					   }while(rs.next());
				  String insert = "INSERT INTO password(id_cfo, mdp) VALUES(?,?)";
				   PreparedStatement preStmt =(PreparedStatement) connection.prepareStatement(insert);
				   preStmt.setInt(1,id_cfo);
				   preStmt.setString(2,DAO.Encrypter(number));
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
}


