<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">

 
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

  <!-- Custom CSS -->
    <link href="css/acceuil.css" rel="stylesheet">
    <link href="css/stylish-portfolio.min.css" rel="stylesheet">
</head>
<body>
<%	try{ 
		//If the user isn't logged in

		if (request.getSession().getAttribute("userManager")!=null){
			String username = (String)request.getSession().getAttribute("userManager"); %>
			<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <div  class="container-fluid">
            <div class="row">
                <div class="col-sm-3">
                    <div class="nav-side-menu">
                        <div class="brand">JISP Bank</div>
                        <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
                        <div class="menu-list">
                            <ul id="menu-content" class="menu-content collapse out">
                                <li>
                                    <a href="formConseiller.jsp">
                                        <i class="fa fa-dashboard fa-lg"></i> Ajouter un nouveau conseiller front-office
                                    </a>
                                </li>
                                <li>
                                    <a href="suppression.jsp">
                                        <i class="fa fa-dashboard fa-lg"></i> Supprimer un conseiller front-office
                                    </a>
                                </li>
                                <li>
                                    <a href="sommeDepots.jsp">
                                        <i class="fa fa-dashboard fa-lg"></i> Consulter la somme des depôts des clients
                                    </a>
                                </li>
                                <li>
                                    <a href="ReportVirement.jsp">
                                        <i class="fa fa-dashboard fa-lg"></i> Générer des reportings de virement
                                    </a>
                    			</li>
                    			
                    			<li>
                                    <a href="ReportBoucier.jsp">
                                        <i class="fa fa-dashboard fa-lg"></i> Générer le reporting des Actulités boursières
                                    </a>
                    			</li>
                            </ul>
                        </div>
                    </div>
                </div>
                
                     <div class="col-sm-8 col-sm-offset-1">
                    <h1><em>Bienvenue sur votre page d'acccueil</em></h1>
                  
                       <p>Déconnexion:
    <input type="button" value="Se déconnecter" onclick="window.location.href='/Manager/ServeltLogout'">
       
        
      </p>
      </div>
            </div>
        </div>
			 <%}else{%> 
			           <div class="col-sm-9 col-sm-offset-1">
                   <h1><em>Bienvenue sur votre espace Manager</em></h1>
                  
                       <p>Connecter:
                       <input type="button" value="Se connecter" onclick="window.location.href='/Manager/LoginManager.jsp'">
    </p>
      </div>
			<% }
			 
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