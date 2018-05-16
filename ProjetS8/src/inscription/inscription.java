package inscription;



import java.io.IOException;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.jee.DAO;

/**
 * Servlet implementation class inscription
 */
@WebServlet("/inscription")
public class inscription extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public static final String VUE = "/formulaire.jsp";
	 public static final String CHAMP_SEXE = "sexe";
	 public static final String CHAMP_NATIONALITE = "nationalite";
	 public static final String CHAMP_NOM = "nom";
	 public static final String CHAMP_PRENOM = "prenom";
	 public static final String CHAMP_DATE = "date";
	 public static final String CHAMP_EMAIL = "email";
	 public static final String CHAMP_TELEPHONE = "telephone";
	 public static final String CHAMP_ADRESSE = "autocomplete";
	 public static final String CHAMP_PWD = "pwd";
	 public static final String CHAMP_PWD_CONFIRM = "pwd_confirm";
	 public static final String ATT_ERREURS  = "erreurs";
	 public static final String ATT_RESULTAT = "resultat";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();
        
		/* R¨¦cup¨¦ration des champs du formulaire. */
        String nom = request.getParameter( "nom" );
        String prenom = request.getParameter( "prenom");
        Date date = Date.valueOf(request.getParameter( "date"));
        String email = request.getParameter( "email" );
        String telephone = request.getParameter( "telephone" );
        String adresse = request.getParameter( "autocomplete" );
        String pwd = request.getParameter( "pwd" );
        String pwd_confirm = request.getParameter( "pwd_confirm" );
        String sexe = request.getParameter( "sexe" );
        String nationalite = request.getParameter( "nationalite" );
        /* Validation du champ adresse. */
        try {
            valid.validationAdresse(adresse);
        } catch ( Exception e ) {
            erreurs.put(CHAMP_ADRESSE, e.getMessage());
            Cookie cookie = new Cookie("ADRESSE", "Adresse");
			response.addCookie(cookie);
        }

        /* Validation du champ date. */
        try {
        	
         valid.validationDate(date );
        } catch ( Exception e ) {
            erreurs.put( CHAMP_DATE, e.getMessage());
            Cookie cookie = new Cookie("DATE", "date!");
			response.addCookie(cookie);
        }
        
        /* Validation du champ nationalité. */
        try {
            valid.validationNationalite(nationalite);
        } catch ( Exception e ) {
            erreurs.put( CHAMP_NATIONALITE, e.getMessage());
            Cookie cookie = new Cookie("NATIONALITE", "nationalite!");
			response.addCookie(cookie);
        }

        /* Validation du champ email. */
        try {
            valid.validationEmail( email );
        } catch ( Exception e ) {
            erreurs.put(CHAMP_EMAIL, e.getMessage());
            Cookie cookie = new Cookie("EMAIL", "email!");
			response.addCookie(cookie);
        }
        /* Validation du champ t¨¦l¨¦hone. */
        try {
            valid.validationTelephone(telephone);
        } catch ( Exception e ) {
            erreurs.put(CHAMP_TELEPHONE, e.getMessage());
            Cookie cookie = new Cookie("TEL", "telephone!");
			response.addCookie(cookie);
        }
        /* Validation des champs mot de passe et confirmation. */
        try {
            valid.validationMotsDePasse(pwd,pwd_confirm);
        } catch ( Exception e ) {
        	Cookie cookie;
        	if (!DAO.CheckMdp(pwd)) {
        		cookie = new Cookie("PASSWORD", "existant");
           }else {    
        	    cookie = new Cookie("PASSWORD", "password!");
           }
            erreurs.put( CHAMP_PWD, e.getMessage() );
			response.addCookie(cookie);
        }
        /* Affichage du r¨¦sultat*/
        System.out.println(erreurs);
        if (erreurs.isEmpty()) {

            Client client = new Client();
            client.setSexe(sexe);
			client.setNom(nom);
			client.setPrenom(prenom);
			client.setNationalite(nationalite);
			client.setDateNaissance(date);
			client.setEmail(email);
			client.setTelephone(telephone);
			client.setAdresse(adresse);
			client.setPwd(pwd);
			DAO.InsererClient(client);
			int id = DAO.getIdClient(client.getNom(), client.getPrenom());
			DAO.insererCptCourant(id); 
			resultat = "succes";
			Cookie cookie = new Cookie("RESULT", resultat);
			response.addCookie(cookie);
            response.sendRedirect("/ProjetS8/LoginForm.jsp");
        } else {
            /* Stockage du résultat et des messages d'erreur dans l'objet request */
           request.setAttribute( ATT_ERREURS, erreurs );

            /* Transmission de la paire d'objets request/response notre JSP */
            response.sendRedirect("/ProjetS8/formulaire.jsp");
        }

       
	}

}
