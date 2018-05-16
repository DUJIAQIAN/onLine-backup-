<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>
<head>
<title>LoginForm Manager</title>
<!-- Bootstrap Core CSS -->
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
          <a class="js-scroll-trigger" href="/Manager/Index.jsp">Accueil</a>
        </li>
      </ul>
    </nav>
    
    <h3>Connecter en tant qu'un manager</h3>
<div class="container">
  <div class="panel-group">
    <div class="panel panel-default">
      <div class="panel-heading">
         
		<div class="card bg-primary text-white">
    		<div class="card-body">Please enter your login and password</div>
 		</div>
 		
		<form action="ServletLogin" method="POST">
			<div class="card">
    			<div class="card-body">votre nom <input type="text" name="login"></div>
 			 </div>
		 
		 	<div class="card">
    			<div class="card-body">votre mot de passe <input type="password" name="mdp"></div>
 			 </div>
 		 
 		 	<button class="btn btn-primary" type="submit" value="Submit">Log in</button>

		</form>
		</div>
	</div>
  </div>
</div>

	<%Cookie[] cookies = request.getCookies();

	//getting the cookie if it exists
	try {
		for (Cookie c : cookies){
		 if (c.getName().equals("RESULT")){
			 %> <div class="alert alert-info alert-dismissible fade in">
				<a href="/Manager/LoginManager.jsp" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			 <span><%=c.getValue()%></span>
			 </div><%
			 c.setMaxAge(0);
			 response.addCookie(c);
		 }
		}
	}catch(Exception e) {	
	} %>

	
	
	<!-- Si les informations sont mauvaises on affiche un message d'erreur -->
	<% 	if ((String)request.getSession().getAttribute("userManager")==null){ } else{%>
		<div class="alert alert-danger alert-dismissible fade in">
    	<a href="/Manager/LoginManager.jsp" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<span><%=(String)request.getSession().getAttribute("userManager")%></span>
		</div>
	<% } request.getSession().invalidate();%>
	
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