package formatCSV;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.jee.DAO;

/**
 * Servlet implementation class CSVservlet
 */
@WebServlet("/CSVservlet")
public class CSVservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CSVservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = (String)request.getSession().getAttribute("user");
		String[] logs = user.split(" ");
		DAO.getCSVcourant(logs[0], logs[1]);
		DAO.getCSVepargne(logs[0], logs[1]);		
		DAO.getTransactionCSV(logs[0], logs[1]);
			response.setContentType("application/csv");
			response.setHeader("content-disposition","filename= releves.xls");
		File file = new File("./WebContent/releves.csv");
		FileInputStream fileIn = new FileInputStream(file);
		ServletOutputStream out = response.getOutputStream();

		byte[] outputByte = new byte[4096];
		//copy binary contect to output stream
		while(fileIn.read(outputByte, 0, 4096) != -1)
		{
			out.write(outputByte, 0, 4096);
		}
		fileIn.close();
		out.flush();
		out.close();
	}

}
