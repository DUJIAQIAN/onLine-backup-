package DAO.jee;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logging.LoggerExample;
import logging.MyLogger;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
   
	/** Connexion sur l'espace client
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pwd;
		try {		
			LoggerExample TrackLogs = new LoggerExample();
			HttpSession session = request.getSession();
			 System.out.println(request.getParameter("login"));
			 System.out.println(request.getParameter("mdp"));
		if (request.getParameter("login").isEmpty() || request.getParameter("mdp").isEmpty()) {
			try {
			MyLogger.setup();
			} catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
			}
		//On localhost it shows the 0:0:0:0:0:0:0:1 address
		String Ip = getClientIp(request);
		TrackLogs.LoginClient("tentative echoue", Ip, 0);
			response.sendRedirect("/ProjetS8/LoginForm.jsp"); 
			session.setAttribute("user", "Invalid login or password.");
		}
		else {
		
		    pwd=DAO.Encrypter(request.getParameter("mdp"));
			String	logs = DAO.login(request.getParameter("login"), pwd);
				System.out.println(logs);
				if (logs.equals("null")) {
					try {
				            MyLogger.setup();
				        } catch (IOException e) {
				            e.printStackTrace();
				            throw new RuntimeException("Problems with creating the log files");
				        }
						//On localhost it shows the 0:0:0:0:0:0:0:1 address
						String Ip = getClientIp(request);
						TrackLogs.LoginClient("Tentative de connexion echoue", Ip, 0);
						session.setAttribute("user", "Invalid login or password.");
						response.sendRedirect("/ProjetS8/LoginForm.jsp");
						}
				else{
					
						try {
				            MyLogger.setup();
				        } catch (IOException e) {
				            e.printStackTrace();
				            throw new RuntimeException("Problems with creating the log files");
				        }
						//On localhost it shows the 0:0:0:0:0:0:0:1 address
						String Ip = getClientIp(request);
						TrackLogs.LoginClient(logs, Ip, 1);
						session.setAttribute("user", logs);
						response.sendRedirect("/ProjetS8/index.jsp");				
					}
		    
		
			
			}
		}catch (NullPointerException e) {
			HttpSession session = request.getSession();
			session.setAttribute("user", "Invalid login or password.");
			response.sendRedirect("/ProjetS8/LoginForm.jsp");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
