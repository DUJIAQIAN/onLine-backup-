package Login_Logout;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.manager.DAO;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * controller for login  as manager
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {		
			HttpSession session = request.getSession();
		if (request.getParameter("login").isEmpty() || request.getParameter("mdp").isEmpty()) {
			response.sendRedirect("/Manager/LoginManager.jsp"); 
			session.setAttribute("userManager", "Invalid login or password.");
		}
		else {
			String logs = DAO.login(request.getParameter("login"), request.getParameter("mdp"));
			if (logs.equals("null")) {
					session.setAttribute("userManager", "Invalid login or password.");
					response.sendRedirect("/Manager/LoginManager.jsp");
					}
			else{
					session.setAttribute("userManager", logs);
					response.sendRedirect("/Manager/Acceuil.jsp");				
				}
			}
		}catch (NullPointerException e) {
			HttpSession session = request.getSession();
			session.setAttribute("userManager", "Invalid login or password.");
			response.sendRedirect("/Manager/LoginManager.jsp");
		}
	}

}
