package Cpt;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Cpt.CptTitre;
import Cpt.*;
import DAO.jee.DAO;
import logging.LoggerExample;
import logging.MyLogger;
import net.codejava.excel.readstock;

import java.util.List;

/**
 * Servlet implementation class ServletAchat
 */
@WebServlet("/ServletAchat")
public class ServletAchat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAchat() {
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
		float total,prix;
		int quantity;
		double solde;
	try {	
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		//System.out.println(dateFormat.format(date));
		String strDate = dateFormat.format(date);
		String user = (String)request.getSession().getAttribute("user");
		String[] logs = user.split(" ");
		int compte = DAO.getNumCptTitre(logs[0], logs[1]);	
		Cpt cptCourant = DAO.getSolde(logs[0], logs[1]);
		
		nomAction=request.getParameter("nom");
		quantity=Integer.parseInt(request.getParameter("qttAchat"));
		total= Float.parseFloat(request.getParameter("total"));
		prix= Float.parseFloat(request.getParameter("prix"));

		
		Action achat=new Action();
		achat.setnomAction(nomAction);
		achat.setNumAchat(quantity);
		//achat.setValueAction(total);
		achat.setNumeroCptTitre(compte);
		achat.setValueAction(prix);
		achat.setTotal(total);
		
		solde=cptCourant.getSolde();
		
		if(solde<total) {
			LoggerExample TrackLogs = new LoggerExample();
			try {
	            MyLogger.setup();
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Problems with creating the log files");
	        }
			//On localhost it shows the 0:0:0:0:0:0:0:1 address
			String Ip = getClientIp(request);
			TrackLogs.AchatAction(user, Ip, 0);
			infos.put("solde", "Vous n'avez pas d'assez d'argent dans votre compte courant.");	
		}else {		
			 DAO.AcheterAction(achat,total);
			  solde=solde-total;			  
			  cptCourant.setSolde(solde);
			 // DAO.UpdateAction(achat,"a");
			  DAO.setSolde(cptCourant.getNumero(), solde);
			   DAO.addTransactionBourse(Integer.toString(compte), request.getParameter("total"), strDate);
			   LoggerExample TrackLogs = new LoggerExample();
				try {
		            MyLogger.setup();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		            throw new RuntimeException("Problems with creating the log files");
		        }
				//On localhost it shows the 0:0:0:0:0:0:0:1 address
				String Ip = getClientIp(request);
				TrackLogs.AchatAction(user, Ip, 1);
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
