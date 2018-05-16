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
 * Servlet implementation class ServletVirementInterne
 */
@WebServlet("/ServletVirementInterne")
public class ServletVirementInterne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVirementInterne() {
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
		int soldeVirement;
		String numeroCpt, mdp, montantSaisie, numeroCptDest;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		//System.out.println(dateFormat.format(date));
		String strDate = dateFormat.format(date);
		try {
			numeroCpt = request.getParameter("numeroCpt"); 
			//System.out.println(numeroCpt);
			montantSaisie = request.getParameter("montant"); 
			numeroCptDest = request.getParameter("numeroCptDest");
			soldeVirement = Integer.parseInt(montantSaisie);
			mdp = request.getParameter("pwd");
			
			if(DAO.virementInterne(numeroCpt, soldeVirement, mdp, numeroCptDest)){
				DAO.addTransactionVirement(numeroCpt, montantSaisie, strDate, numeroCptDest);
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
				TrackLogs.VirementInterneClient(logs, Ip, 1);
				infos.put("successAliment", "Vous avez réussi à faire un virement.");
			}else {
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
					TrackLogs.VirementInterneClient(logs, Ip, 0); 
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
			    infos.put("erreur" , e.getMessage() + "Vérifier les informations fournies ");
			    e.printStackTrace();
				}
		
		request.setAttribute( "infos", infos );
		this.getServletContext().getRequestDispatcher("/virementInterne.jsp").forward( request, response );

	}
	}


