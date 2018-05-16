<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

	<!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">


   <!-- Custom CSS -->
    <link href="css/pageCptTitre.css" rel="stylesheet">

	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
<title>Créer Compte Titre</title>
</head>

<body>
<%	try{ 
		//If the user isn't logged in
		if (request.getSession().getAttribute("user")==null){
		response.sendRedirect("/PlotCac/LoginForm.jsp"); 
		}else{ %>
	<div id="page">
		<div class="headerpage">
			<p class="banquename" style="color:white;">JISP</p>
			
			</div>
		<div class="headerpage1"></div>
	</div>	
<!-- menu -->
<section class="menu-group">
 <div class="container">
		<div class="btn-group btn-group-justified">
			<div class="btn-group">
    		<a type="submit" class="btn btn-default" href="/ProjetS8/index.jsp">
    		<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
    		Home</a>
    		</div>
     
  		   <div class="btn-group">
   		   <button type="button" class="btn btn-default" data-toggle="dropdown">
    		Vos Compte  <span class="caret"></span></button>
    			<ul class="dropdown-menu" role="menu" style="min-width:100%">
    				<li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span>
    				Consulter tous vos comptes</a></li>
    				<li><a href="CreerCptEpargne.jsp"><span class="glyphicon glyphicon-chevron-right"></span>
    				Créer un compte épargne</a></li>
    				<li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span>
    				Créer un compte titre</a></li>
  				</ul>
		  </div>
		  
		   <div class="btn-group" role="group">
          	 <button type="button" class="btn btn-default" data-toggle="dropdown">
               Opérations Courantes <span class="caret"></span></button>
               <ul class="dropdown-menu" role="menu" style="min-width:100%">
               <li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span>
    				Virements interne</a></li>
    			<li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span>
    				Virements externe</a></li>
    		  </ul>
  		  </div>
		</div>
	</div>
</section>

<!-- meéssage erreur/success  -->
<span class="text-danger" id="erreur" name="erreur"><h4 class="text-center">${infos['erreur']}</h4></span>
<span class="text-danger" id="successCreation" name="successCreation"><h4 class="text-center">${infos['successCreation']}</h4></span>
<!-- créer un compte épargne -->
	<h1><p class="text-info text-center ">Création d'un compte Titre</p></h1>
	
	<div class="container">
		<div class="step">		
				<span class="detail">
					&nbsp;En Détail
				</span>	
				<br>	
		</div>
		<div class="stepContent step">
		 <ul>
		 	<li>Toute personne ayant la capacité juridique à contracter peut souscrire à un compte titre. Ceci sans condition de nationalité ou de lieu de résidence.</li>
			 <li>Un compte titre est toujours rattaché à un compte courant pour pouvoir réaliser les achats et ventes de titres(CAC40).</li>
 		</ul> 		 
		</div>
	</div>
	

<div class="container">	
	   <div class="step">			
			<span>	
				<strong >Saisissez</strong> les coordonnées du compte à ajouter :
			</span>
			 <form class="form-inline " action="#" method="post" accept-charset='UTF-8'>
  			<div class="form-group stepContent">
   			 	 <label for="numeroCpt">Numéro de compte courant (Ex. : 8888)</label>
   				 <input type="text" class="form-control" id="numeroCpt" name="numeroCpt">
  			</div>		
			<span>	
				Veuillez <strong >saisir</strong> votre mot de passe et <strong >valider</strong> votre choix :
			</span>
  			 <div class="form-group stepContent">
    			<label for="pwd">Mot de passe:</label>
    			<input type="password" style="width:180px;" class="form-control" id="pwd" name="pwd">
 			</div>
 			<br>
 			<div class="form-group stepContent">	
 			<button type="submit" class="btn btn-primary" id="submit" name="submit">Valider</button>
 			
 			<script>
	function che(){
			tt = document.getElementById("Alert");
			tt.style.visibility="visible";
			document.getElementById("numeroCpt").value = "";	
			document.getElementById("pwd").value = "";
	}
	</script>
 			
 			<a href="#Alert" class="btn btn-primary" id="Annuler" onclick="che();">Annuler</a>		
 		</div>			
 	</div>
 	<span class="stepContent">	
	Ce compte sera inscrit à votre contrat après <strong>validation</strong>.</span>
				<br>
				<span class="stepContent">Si vous voulez annuler votre demmande, cliquez<strong> &lt;Annuler&gt;</strong>
	            </span>	
 	</div>
 		</form>	
 			</div>		 		
		</div>	

		<div id="Alert" style="visibility:hidden" class="stepContent"><h3 class="text-warning">Votre demande est annulée.</h3></div>

		<div class="headerpage1"></div>
	<%}
		}catch (Exception e){}%>
	</body>
</html>