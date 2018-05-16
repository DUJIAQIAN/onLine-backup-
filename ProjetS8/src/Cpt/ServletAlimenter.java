package Cpt;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.jee.DAO;

/**
 * Servlet implementation class ServeltAlimenter
 */
@WebServlet("/ServletAlimenter")
public class ServletAlimenter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAlimenter() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, String> infos = new HashMap<String, String>();
		int soldeAjout=0;
		String montantSaisie,numeroCpt, mdp; 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		//System.out.println(dateFormat.format(date));
		String strDate = dateFormat.format(date);
		try {
			numeroCpt = request.getParameter("numeroCpt"); 
			montantSaisie = request.getParameter("montant"); 
			soldeAjout = Integer.parseInt(montantSaisie);
			mdp = request.getParameter("pwd");
			int flag = DAO.Alimenter(numeroCpt, soldeAjout, mdp);
			if(flag == 1){
				DAO.addTransactionAlimenter(numeroCpt, montantSaisie, strDate);
				infos.put("successAliment", "Vous avez réussi à alimenter votre compte courant.");
			}			
		}catch(Exception e) {
			    infos.put("erreur" , e.getMessage());
			    e.printStackTrace(); 
				}
		
		request.setAttribute( "infos", infos );
		this.getServletContext().getRequestDispatcher("/Alimenter.jsp").forward( request, response );

	}

}
