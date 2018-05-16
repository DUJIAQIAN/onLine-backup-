<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="DAO.manager.*"%>
<%@page import="Inscription.*"%>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Suppression</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

 <!-- Bootstrap core CSS -->
    <link href="../../../../dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet"> 
    <link href="css/stylish-portfolio.min.css" rel="stylesheet">
  </head>

   <%	try{ 
		//If the user isn't logged in
		if (request.getSession().getAttribute("userManager")==null){
		response.sendRedirect("/Manager/LoginManager.jsp"); 
		}else{ %>
  <body >
    <div class="container text-center">
<h1 style="border:2px solid Tomato;">JISP Bank/Manager</h1>
 </div>
   
   <!-- Navigation -->
    <a class="menu-toggle rounded" href="#">
      <i class="fa fa-bars"></i>
    </a>
    <nav id="sidebar-wrapper">
      <ul class="sidebar-nav">      
         <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/Manager/ServeltLogout">Logout</a>
        </li>
        <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/Manager/Acceuil.jsp">Accueil</a>
      </ul>
    </nav>
<%}
}
catch(Exception e){}%>
   
     <div class="container">
     <div  class="text-center">
     <span class="text-danger" id="success" name="success"><h4 class="text-center">${infos['success']}</h4></span>
   <%   
  List<Conseiller> liste = DAO.selectInfos();
		%> 
    <form action="suppressionServlet" method="post">
    <form class="form-signin">
      
      <p style="color:red;"><em>Pour supprimer un conseiller front-office déjà existant, effectuer une recherche ci-dessous 
      et confirmer la suppression</em></p>
      
      <h3 style="color:red;">Recherche</h3>
      
        <div class="form-group">
      <label for="sel1">Selectionnez un conseiller:</label><br>
<select class="selectpicker" name="cfo">

 <%   for(int i=0; i< liste.size(); i++){
   if(liste.get(i).getNom()!= null){
   %> 
<option><%= liste.get(i).getNom()%> </option>
	   
	   <%}
     }%> 
      <br><br><br>
     </select>
       </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Valider suppression</button><br>
      <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
   
 </form>
 </div>
 </div>
    
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