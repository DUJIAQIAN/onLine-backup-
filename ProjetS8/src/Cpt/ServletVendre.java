package Cpt;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Cpt.*;
import DAO.jee.DAO;
import logging.LoggerExample;
import logging.MyLogger;

/**
 * Servlet implementation class ServletVendre
 */
@WebServlet("/ServletVendre")
public class ServletVendre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVendre() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
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
		request.setCharacterEncoding("utf-8"); 
		Map<String, String> infos = new HashMap<String, String>();
		
		String nomAction;
		float total;
		int quantity, numeroCptTitre;
		double solde;
		Action action=new Action();
		
	try {	
		String user = (String)request.getSession().getAttribute("user");
		String[] logs = user.split(" ");
		int compte = DAO.getNumCptTitre(logs[0], logs[1]);	
		Cpt cptCourant = DAO.getSolde(logs[0], logs[1]);
		
		//numeroCptTitre= compte.getNumero();
		numeroCptTitre= compte;
		
		
		nomAction=request.getParameter("nom1");
		quantity=Integer.parseInt(request.getParameter("qttAchat1"));
		total= Float.parseFloat(request.getParameter("total1"));
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		//System.out.println(dateFormat.format(date));
		String strDate = dateFormat.format(date);
		
		solde=cptCourant.getSolde();
		cptCourant.setSolde(solde);
		solde=solde+total;			  
		cptCourant.setSolde(solde);
	   
	   //la ligne d'action (vente)
		action=DAO.getAction(numeroCptTitre, nomAction);
		total=action.getTotal()-total;
		
		 if(quantity>action.getNumAchat()) {
			 LoggerExample TrackLogs = new LoggerExample();
				try {
		            MyLogger.setup();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		            throw new RuntimeException("Problems with creating the log files");
		        }
				//On localhost it shows the 0:0:0:0:0:0:0:1 address
				String Ip = getClientIp(request);
				TrackLogs.VenteAction(user, Ip, 0);
			 infos.put("erreur", "Le volume d'action est petit que le quantit¨¦ saisi");
		 }
		 else{
			 quantity=action.getNumAchat()-quantity;
			 System.out.println(quantity);
			 action.setTotal(total);
			 action.setNumAchat(quantity);
			 DAO.UpdateAction(action,"v");
			 DAO.setSolde(cptCourant.getNumero(), solde);
			 DAO.addTransactionVirementVente(Integer.toString(cptCourant.getNumero()),request.getParameter("total1"), strDate);
			 LoggerExample TrackLogs = new LoggerExample();
				try {
		            MyLogger.setup();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		            throw new RuntimeException("Problems with creating the log files");
		        }
				//On localhost it shows the 0:0:0:0:0:0:0:1 address
				String Ip = getClientIp(request);
				TrackLogs.VenteAction(user, Ip, 1);
			 infos.put("successCreation", "Merci de votre commande.");
		 }

	}catch(Exception e) {
		e.printStackTrace();
	}
				
		/* Stockage du r¨¦sultat et des messages d'erreur dans l'objet request */
    	request.setAttribute( "infos", infos );
		this.getServletContext().getRequestDispatcher("/SeeCAC.jsp").forward( request, response );
	}

}
