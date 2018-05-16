package Inscription;

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

import DAO.manager.DAO;

/**
 * Servlet implementation class InscriptionConseiller
 */
@WebServlet("/InscriptionConseiller")
public class InscriptionConseiller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionConseiller() {
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

	 public static final String VUE = "/formConseiller.jsp";
	 public static final String CHAMP_SEXE = "sexe";
	 public static final String CHAMP_NOM = "nom";
	 public static final String CHAMP_PRENOM = "prenom";
	 public static final String CHAMP_DATE = "date";
	 public static final String CHAMP_EMAIL = "email";
	 public static final String CHAMP_TELEPHONE = "telephone";
	 public static final String CHAMP_ADRESSE = "autocomplete";
	 public static final String CHAMP_NATIONALITE = "nationalite";
	 public static final String ATT_ERREURS  = "erreurs";
	 public static final String ATT_RESULTAT = "resultat";
 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();
        /**
         * Get and check fields of the form 
         * and register a front-line advisor
         */
		/* R¨¦cup¨¦ration des champs du formulaire. */
        String sexe = request.getParameter( "sexe" );
        String nom = request.getParameter( "nom" );
        String prenom = request.getParameter( "prenom");
        Date date = Date.valueOf(request.getParameter( "date"));
        String email = request.getParameter( "email" );
        String telephone = request.getParameter( "telephone" );
        String adresse = request.getParameter( "autocomplete" );
        String nationalite = request.getParameter( "nationalite" );
        /* Validation du champ adresse. */
        try {
            valid.validationAdresse(adresse);
        } catch ( Exception e ) {
            erreurs.put(CHAMP_ADRESSE, e.getMessage());
            Cookie cookie = new Cookie("adresse", "Entrer une adresse postale");
            response.addCookie(cookie);
        }
		/* Validation du champ nom. */
        try {
            valid.validationNom( nom );
        } catch ( Exception e ) {
            erreurs.put( CHAMP_NOM, e.getMessage());
        }
        /* Validation du champ pr¨¦nom. */
        try {
            valid.validationPrenom( prenom );
        } catch ( Exception e ) {
            erreurs.put( CHAMP_PRENOM, e.getMessage());
        }

        /* Validation du champ email. */
        try {
            valid.validationEmail( email );
        } catch ( Exception e ) {
            erreurs.put(CHAMP_EMAIL, e.getMessage());
            Cookie cookie = new Cookie("email", "Merci de saisir une adresse mail valide.");
            response.addCookie(cookie);
        }
        /* Validation du champ t¨¦l¨¦phone. */
        try {
            valid.validationTelephone(telephone);
        } catch ( Exception e ) {
            erreurs.put(CHAMP_TELEPHONE, e.getMessage());
            Cookie cookie = new Cookie("telephone", "Le numero de t¨¦l¨¦phone doit contenir 10 caract¨¨res.");
            response.addCookie(cookie);
        }
        /* Affichage du r¨¦sultat*/
        if (erreurs.isEmpty()) {

           Conseiller conseiller = new Conseiller();
            conseiller.setSexe(sexe);
			conseiller.setNom(nom);
			conseiller.setPrenom(prenom);
			conseiller.setNationalite(nationalite);
			conseiller.setDateNaissance(date);
			conseiller.setEmail(email);
			conseiller.setTelephone(telephone);
			conseiller.setAdresse(adresse);
			DAO.InsererConseiller(conseiller);
			int idCfo = DAO.getIdConseiller(conseiller.getNom(), conseiller.getPrenom()); 
		
				try {
					DAO.insererMdp(idCfo);
					Map<String, String> infos = new HashMap<String, String>();
					infos.put("success", "Le conseiller est cr¨¦¨¦");
					request.setAttribute( "infos", infos );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			this.getServletContext().getRequestDispatcher("/formConseiller.jsp").forward( request, response );
        } else {
            /* Stockage du r¨¦sultat et des messages d'erreur dans l'objet request */
            request.setAttribute( ATT_ERREURS, erreurs );           
            /* Transmission de la paire d'objets request/response notre JSP */
            response.sendRedirect("/formConseiller.jsp");
        }
	}
}
