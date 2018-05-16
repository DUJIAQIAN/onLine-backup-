package cfo;

import java.io.IOException;
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
 * Servlet implementation class suppressionServlet
 * remove a front office advisor from his name
 */
@WebServlet("/suppressionServlet")
public class suppressionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/suppression.jsp";
	 //public static final String CHAMP_NOM = "cfo";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public suppressionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultat;
        Map<String, String> infos = new HashMap<String, String>();
        DAO dao = new DAO();
        /* R¨¦cup¨¦ration des champs du formulaire. */
        // supprimerConseiller cfo = new supprimerConseiller();
        String nom = request.getParameter( "cfo" );
        /* Suppression*/
        try {
			dao.supprimerConseiller(nom);
			infos.put("success", "Le conseiller est d¨¦j¨¤ supprim¨¦");
            //response.sendRedirect("/Manager/suppression.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        /* Stockage du r¨¦sultat et des messages d'erreur dans l'objet request */
        request.setAttribute( "infos", infos );
     
		this.getServletContext().getRequestDispatcher("/suppression.jsp").forward( request, response );
	}
	
	

}
