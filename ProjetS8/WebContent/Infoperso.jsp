<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="DAO.jee.*"%>
        <%@page import="inscription.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html >

<html>
<head>
<meta http-equiv="Content-Type">
<title>Mes informations</title>
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
          <a class="js-scroll-trigger" href="/ProjetS8/Solde.jsp">mes comptes</a>
        </li>
        <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/ProjetS8/historique.jsp">Historique des transactions</a>
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
		Client userData = DAO.getInfos(logs[0], logs[1]);
		
	%>

	<div class="card bg-primary text-white">
    		<div class="card-body">Mes informations personnelles</div>
 		</div>
 	
 	<form action="Modifier" method="POST">
 	
 	 		 <div class="card">
    			<div class="card-body">Nationalite: <%= userData.getNationalite() %></div>	 
 			 </div>
 			 
 			  <div class="card">
    			<div class="card-body">Sexe: <%if (userData.getSexe().equals("H")){
    				%> Homme <%
    				} 
    				if (userData.getSexe().equals("F")){
    					%> Femme <%} %></div>	 
 			 </div>
 			 
			<div class="card">
    			<div class="card-body">nom: <%= userData.getNom() %> </div>
 			 </div>
		 
		 	<div class="card">
    			<div class="card-body">prenom: <%= userData.getPrenom() %> </div>
 			 </div>
 			 
 		 	<div class="card">
    			<div class="card-body">date de naissance: <%= userData.getDateNaissance() %> </div>
 			 </div>
 			 
 			 <div class="card">
    			<div class="card-body">email: <%= userData.getEmail() %> <input type="text" name="email"></div>
    			<span class="erreur">${erreurs['email']}</span>
 			 </div>
 			 
 			 <div class="card">
    			<div class="card-body">telephone: <%= userData.getTelephone() %> <input type="text" name="tel"></div>
    			<span class="erreur">${erreurs['telephone']}</span>
 			 </div>
 			 
 			 <div class="card">
    			<div class="card-body">adresse: <%= userData.getAdresse() %> <%@include  file="Googlemaps.jsp" %></div>	 
    			 <span class="erreur">${erreurs['autocomplete']}</span>
 			 </div>
 			 
 			 <div class="card">
    			<div class="card-body">nouveau mot de passe: <input type="password" name="pwd"></div>
    			<span class="erreur">${erreurs['pwd']}</span>
 			 </div>
 			 
 			  <div class="card">
    			<div class="card-body">Confirmer votre mot de passe <input type="password" name="pwd_confirm"></div>
    			<span class="erreur">${erreurs['pwd_confirm']}</span>
 			 </div>
 		 	<button class="btn btn-primary" type="submit" value="Submit">Modifier</button>

		</form>
		  <%} 
	}catch (Exception e){} %>
	
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