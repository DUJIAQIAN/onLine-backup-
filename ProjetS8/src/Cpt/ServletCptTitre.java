package Cpt;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.jee.DAO;
/**
 * Servlet implementation class ServletCptTitre
 */
@WebServlet("/ServletCptTitre")
public class ServletCptTitre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCptTitre() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		Map<String, String> infos = new HashMap<String, String>();

		String numeroCpt, mdp; 
		try { 
			numeroCpt = request.getParameter("numeroCpt"); 		
			mdp = request.getParameter("pwd");
			
			if(DAO.insererCptTitre(numeroCpt, mdp)) {
				infos.put("successCreation", "Vous avez un compte titre.");
				request.setAttribute( "infos", infos );
				this.getServletContext().getRequestDispatcher("/InvestirCAC40.jsp").forward( request, response );
			}
			
	}catch(Exception e) {
	    infos.put("erreur" , e.getMessage());
		e.printStackTrace(); 
		 request.setAttribute( "infos", infos );
		this.getServletContext().getRequestDispatcher("/CreerCptTitre.jsp").forward( request, response );
		}

	}
}
