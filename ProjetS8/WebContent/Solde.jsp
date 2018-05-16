<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@page import="DAO.jee.*"%>
        <%@page import="Cpt.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Solde</title>

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
		}else{ %>
		
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
          <a class="js-scroll-trigger" href="/ProjetS8/historique.jsp">Historique de mes transactions</a>
        </li>
         <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/ProjetS8/logout">Logout</a>
        </li>
        <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/ProjetS8/index.jsp">Accueil</a>
      </ul>
    </nav>

	<%
		String user = (String)request.getSession().getAttribute("user");
			String[] logs = user.split(" ");
			Cpt compte = DAO.getSolde(logs[0], logs[1]);
	%>
	
	<div class="card bg-primary text-white">
    		<div class="card-body">Mes comptes</div>
 		</div>

	<div class="card">
    			<div class="card-body">Compte courant n°: <%= compte.getNumero() %><br>
    			Solde: <%= compte.getSolde() %> €  </div>   			
 			 </div>
 	<a href="/ProjetS8/Alimenter.jsp" class="btn btn-info" role="button">Alimenter votre compte courant</a>
 	
 	<%
			Cpt compteEpargne = DAO.getEpargne(logs[0], logs[1]);
 			if (compteEpargne == null){
 				
 			}else{
	%>

	<div class="card">
    			<div class="card-body">Compte épargne n°: <%= compteEpargne.getNumero() %><br>
    			Solde épargne: <%= compteEpargne.getSolde() %> €  </div>   			
 			 </div>
 			 
		<%}
			ArrayList<CptTitre> TitreList = DAO.getTitre(compte);
 			if (TitreList.size() == 0){
 				
 			}else{
 				for (int i = 0; i < TitreList.size(); i++) {			
	%>
	<div class="col-md-6 col-md-offset-1">
 			<div class="panel-body">
	<table class="table table-striped table-bordered table-list">
		 		<tbody>
                          <tr>
                            <td>Titre <%= TitreList.get(i).getNumero() %></td>
                            <td><%= TitreList.get(i).getEntreprise() %></td>
                            <td><%= TitreList.get(i).getQuantite() %></td>
                            <td><%= TitreList.get(i).getPrix() %> euros</td>
                          </tr>
                        </tbody>
                        </table>
                        </div>
                        </div>
                <%}
		}		
 			}
		
		}catch(Exception e){}%>
		<br/>
		<a href="CSVservlet" class="btn btn-info" role="button">Votre relevé</a>
		<a href="/ProjetS8/virementInterne.jsp" class="btn btn-info" role="button">faire un virement</a>
		

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