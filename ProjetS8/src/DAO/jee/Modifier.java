package DAO.jee;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inscription.Client;
import inscription.valid;

/**
 * Servlet implementation class Modifier
 */
@WebServlet("/Modifier")
public class Modifier extends HttpServlet {
	 public static final String VUE = "/Infoperso.jsp";
	 public static final String CHAMP_NOM = "nom";
	 public static final String CHAMP_PRENOM = "prenom";
	 public static final String CHAMP_DATE = "date";
	 public static final String CHAMP_EMAIL = "email";
	 public static final String CHAMP_TELEPHONE = "telephone";
	 public static final String CHAMP_ADRESSE = "adresse";
	 public static final String CHAMP_PWD = "pwd";
	 public static final String CHAMP_PWD_CONFIRM = "pwd_confirm";
	 public static final String ATT_ERREURS  = "erreurs";
	 public static final String ATT_RESULTAT = "resultat";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub 
		String resultat;
		 Map<String, String> erreurs = new HashMap<String, String>();
		String user = (String) request.getSession().getAttribute("user");
		String logs[] = user.split(" ");
		Client userData = DAO.getInfos(logs[0], logs[1]);
		
		//Validation des champs à modifier
		 if(!request.getParameter( "email" ).isEmpty()) {
			 try {
		            valid.validationEmail( (String)request.getParameter( "email" ) );
		        } catch ( Exception e ) {
		            erreurs.put(CHAMP_EMAIL, e.getMessage());
		        }
		 }
		 else if(!request.getParameter( "tel" ).isEmpty()) {
			 try {
		            valid.validationTelephone((String)request.getParameter( "tel" ));
		        } catch ( Exception e ) {
		            erreurs.put(CHAMP_TELEPHONE, e.getMessage());
		        }
		 }
		 else if(!request.getParameter( "pwd" ).isEmpty()) {
			 try {
		            valid.validationMotsDePasse((String)request.getParameter( "pwd" ),(String)request.getParameter( "pwd_confirm" ));
		        } catch ( Exception e ) {
		            erreurs.put( CHAMP_PWD, e.getMessage() );
		        }
		 }
		 
		 if (erreurs.isEmpty()) {
			 if(!request.getParameter( "email" ).isEmpty()) {
				 userData.setEmail((String)request.getParameter( "email" ));
			 }
			 if(!request.getParameter( "tel" ).isEmpty()) {
				 userData.setTelephone((String)request.getParameter( "tel" ));
			 }
			 if(!request.getParameter( "autocomplete" ).isEmpty()) {
				 userData.setAdresse((String)request.getParameter( "autocomplete" ));
			 }
			 if(!request.getParameter( "pwd" ).isEmpty()) {
				 userData.setPwd((String)request.getParameter( "pwd" ));
			 }
			 if (DAO.Modifier(userData)) {
				 response.sendRedirect("/ProjetS8/index.jsp");
			 }
		 }
		 else {
		        resultat = "echec de l'inscription.";

		        /* Stockage du r¨¦ultat et des messages d'erreur dans l'objet request */
		        request.setAttribute( ATT_ERREURS, erreurs );
		        request.setAttribute( ATT_RESULTAT, resultat );

		        /* Transmission de la paire d'objets request/response ?notre JSP */
		        this.getServletContext().getRequestDispatcher(VUE).forward( request, response );
		 }
	}

}
