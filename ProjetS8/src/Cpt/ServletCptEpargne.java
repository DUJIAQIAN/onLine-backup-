package Cpt;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
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
 * Servlet implementation class ServletCptEpargne
 */
@WebServlet("/ServletCptEpargne")
public class ServletCptEpargne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCptEpargne() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8"); 
		Map<String, String> erreurs = new HashMap<String, String>();
		
		//CptEpargne cptE = new CptEpargne();
		int solde=0;
		String  vmtMontant,numeroCpt, mdp; 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		//System.out.println(dateFormat.format(date));
		String strDate = dateFormat.format(date);
		try { 
			numeroCpt = request.getParameter("numeroCpt"); 
			vmtMontant = request.getParameter("vmtInitial"); 
			solde = Integer.parseInt(vmtMontant);
			mdp = request.getParameter("pwd"); 
			
			
			/*System.out.println("numero est:"+numeroCpt);*/
			//System.out.println("vmt est:"+solde);
			//System.out.println("mdp est:"+mdp);
			String user = (String)request.getSession().getAttribute("user");
			String[] logs = user.split(" ");
	
			if(DAO.insererCptEpargne(solde, numeroCpt, mdp)) {
				Cpt epargne = DAO.getEpargne(logs[0], logs[1]);
				DAO.addTransactionVirement(numeroCpt, vmtMontant, strDate, Integer.toString(epargne.getNumero()));
			}
			response.sendRedirect("/ProjetS8/Solde.jsp");

		}catch(Exception e) {
	    erreurs.put("erreur" , e.getMessage());
	    request.setAttribute( "erreurs", erreurs );
	     
		this.getServletContext().getRequestDispatcher("/CreerCptEpargne.jsp").forward( request, response );
		e.printStackTrace(); 
		}
		
		/* Stockage du r¨¦ultat et des messages d'erreur dans l'objet request */
       
		//doGet(request, response);
	}

}
