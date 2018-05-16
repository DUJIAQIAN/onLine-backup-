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
import logging.LoggerExample;
import logging.MyLogger;

/**
 * Servlet implementation class ServletVirementExterne
 */
@WebServlet("/ServletVirementExterne")
public class ServletVirementExterne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVirementExterne() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Map<String, String> infos = new HashMap<String, String>();
		int soldeVirement=0;
		String numeroCpt, mdp, montantSaisie, numeroCptDest;
		//n'importe quelle numero compte destinataire
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		//System.out.println(dateFormat.format(date));
		String strDate = dateFormat.format(date);
		try {
			numeroCpt = request.getParameter("numeroCpt"); 
			montantSaisie = request.getParameter("montant"); 
			numeroCptDest = request.getParameter("numeroCptDest");
			soldeVirement = Integer.parseInt(montantSaisie);
			mdp = request.getParameter("pwd");
			int flag = DAO.virementExterne(numeroCpt, soldeVirement, mdp, numeroCptDest);
			if(flag == 1){
				DAO.addTransactionVirementExterne(numeroCpt, montantSaisie, strDate);
				LoggerExample TrackLogs = new LoggerExample();
				try {
		            MyLogger.setup();
		        } catch (IOException e) {
		            e.printStackTrace();
		            throw new RuntimeException("Problems with creating the log files");
		        }
				//On localhost it shows the 0:0:0:0:0:0:0:1 address
				String Ip = getClientIp(request);
				String logs = (String) request.getSession().getAttribute("user");
				TrackLogs.VirementExterneClient(logs, Ip, 1);
				infos.put("successAliment", "Vous avez effectué un virement qui est en attente de validation.");
			}
		}catch(Exception e) {
			LoggerExample TrackLogs = new LoggerExample();
			try {
	            MyLogger.setup();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            throw new RuntimeException("Problems with creating the log files");
	        }
			//On localhost it shows the 0:0:0:0:0:0:0:1 address
			String Ip = getClientIp(request);
			String logs = (String) request.getSession().getAttribute("user");
			TrackLogs.VirementExterneClient(logs, Ip, 0);
			    infos.put("erreur" , e.getMessage());
			    e.printStackTrace(); 
				}
		
		request.setAttribute( "infos", infos );
		this.getServletContext().getRequestDispatcher("/virementExterne.jsp").forward( request, response );

	}
	}


