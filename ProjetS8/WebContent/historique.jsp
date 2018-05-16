<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*"%>
    <%@page import="DAO.jee.*"%>
    <%@page import="transaction.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Historique de mes transactions</title>

  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/stylish-portfolio.min.css" rel="stylesheet">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  
</head>
<body>
<%	try{ 
		//If the user isn't logged in
		if (request.getSession().getAttribute("user")==null){
		response.sendRedirect("/ProjetS8/LoginForm.jsp"); 
		}else{ 
			String user = (String)request.getSession().getAttribute("user");
			String[] logs = user.split(" ");
			ArrayList<historiqueTransaction> histList = DAO.getTransaction(logs[0], logs[1]);
%>


		<div class="card bg-primary text-white">
    		<div class="card-body">Mes transactions</div>
 		</div>
 		<div class="col-md-10 col-md-offset-1">
 			<div class="panel-body">
                <table class="table table-striped table-bordered table-list">
                  <thead>
                    <tr>
                        <th class="hidden-xs">numero de Compte</th>
                        <th>Motif</th>
                        <th>Operation</th>
                        <th>Montant</th>
                        <th>Date</th>
                    </tr> 
                  </thead>
                  <%for (int i = 0; i < histList.size(); i++) { %>
                  <tbody>
                          <tr>
                            <td><%= histList.get(i).getNum() %></td>
                            <td><%= histList.get(i).getMotif() %></td>
                            <td><%= histList.get(i).getOperation() %></td>
                            <td><%= histList.get(i).getMontant() %> euros</td>
                            <td><%= histList.get(i).getDate() %></td>
                          </tr>
                        </tbody>
          
                <%} %>
                      </table>
                </div>
                </div>
                <%}
		}catch (Exception e){}%>
 		
 		
 <body>
 <!-- Navigation -->
    <a class="menu-toggle rounded" href="#">
      <i class="fa fa-bars"></i>
    </a>
    <nav id="sidebar-wrapper">
      <ul class="sidebar-nav">
        <li class="sidebar-brand">
          <a class="js-scroll-trigger" href="#page-top">Start Jisp</a>
        </li>
       <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/ProjetS8/Infoperso.jsp">mes informations personnelles</a>
        </li>
        <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/ProjetS8/Solde.jsp">Mes comptes</a>
        </li>
         <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/ProjetS8/logout">Logout</a>
        </li>
        <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/ProjetS8/index.jsp">Accueil</a>
      </ul>
    </nav>
    
    



 <!-- Footer -->
    <footer class="footer text-center">
      <div class="container">
        <p class="text-muted small mb-0">Copyright &copy; S8 - TIC1 - G2 : DU Jaqian ; DIALLO Ibrahim ; FALL Sidy ; FOURNET Paul</p>
        <br>
        <p><img width="10%" height="10%" src="img/Logo_ESIGELEC.jpg" ></p>
      </div>
    </footer>

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded js-scroll-trigger" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for this template -->
    <script src="js/stylish-portfolio.min.js"></script>
</body>
</html>