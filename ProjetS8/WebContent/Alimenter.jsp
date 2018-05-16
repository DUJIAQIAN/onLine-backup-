<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">


   <!-- Custom CSS -->
    <link href="css/pageCptEpargne.css" rel="stylesheet">
	<link href="" rel="stylesheet">

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
<title>Alimenter Compte Courant</title>
</head>

<body>
<%	try{ 
		//If the user isn't logged in
		if (request.getSession().getAttribute("user")==null){
		response.sendRedirect("/ProjetS8/LoginForm.jsp"); 
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
    		Accueil</a>
    		</div>
     
  		   <div class="btn-group">
   		   <button type="button" class="btn btn-default" data-toggle="dropdown">
    		Vos Compte  <span class="caret"></span></button>
    			<ul class="dropdown-menu" role="menu" style="min-width:100%">
    				<li><a href="/ProjetS8/Solde.jsp"><span class="glyphicon glyphicon-chevron-right"></span>
    				Consulter tous vos comptes</a></li>
    				<li><a href="/ProjetS8/CreerCptEpargne.jsp"><span class="glyphicon glyphicon-chevron-right"></span>
    				Créer un compte épargne</a></li>
    				<li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span>
    				Créer un compte titre</a></li>
  				</ul>
		  </div>
		  
		   <div class="btn-group" role="group">
          	 <button type="button" class="btn btn-default" data-toggle="dropdown">
               Opérations Courantes <span class="caret"></span></button>
               <ul class="dropdown-menu" role="menu" style="min-width:100%">
               <li><a href="virementInterne.jsp"><span class="glyphicon glyphicon-chevron-right"></span>
    				Virement interne</a></li>
    			<li><a href="/ProjetS8/virementExterne.jsp"><span class="glyphicon glyphicon-chevron-right"></span>
    				Virement externe</a></li>
    		  </ul>
  		  </div>
		</div>
	</div>
</section>

<!-- Afficher les erreurs des coordonnées s'il existent -->
<span class="text-danger" id="erreur" name="erreur"><h4 class="text-center">${infos['erreur']}</h4></span>
<span class="text-danger" id="successAliment" name="successAliment"><h4 class="text-center">${infos['successAliment']}</h4></span>

<!-- alimenter un compte courant -->
	<h1><p class="text-info text-center ">Alimenter votre compte courant</p></h1>
	<div class="container">	
	   <div class="step">		
				<span class="detail">
					&nbsp;En détail
				</span>	
				<br>		
			<span class="stepContent step">	
				<strong>Consultez les conditions relatives</strong> à l'alimentation d'un compte courant :
			</span>
		</div>
		<div class="stepContent step">
			<ul>
			<li>Une fois que vous alimentez compte courant chez JISP, vous pouvez sélectionner le choix "Vos comptes" figurant en haute de la page 
			afin de créer un compte épargne(Livret A) ou un compte titre</li>
			<br>
			<li>Pour alimenter votre compte courant,
			vous devez suivre les étapes : saisir votre numéro de compte courant puis saisir le montant,
			enfin, saisir votre code personnerl du compte pour fialiser voter alimentation</li>			
			</ul>
		</div>
	</div>
	
	<div class="container">
	   <div class="step">							
			<span class="stepContent">	
				<Strong>Saisissez les coordonnées</Strong> du compte à alimenter :
			</span>
			<div class="container">
			 <form class="form-inline " action="ServletAlimenter" method="post" accept-charset='UTF-8'>
  			<div class="form-group stepContent">
   			 	 <label for="numeroCpt">Numéro de compte (Ex. : 8888)</label>
   				 <input type="text" class="form-control" id="numeroCpt" name="numeroCpt">
  			</div>		   
  			<div class="form-group stepContent">
   			 	 <label for="montant">Montant (10 euros minimum):</label>
   				 <input type="text" class="form-control" id="montant" name="montant">
  			</div>	
  				
			
  			 <div class="form-group stepContent step">
    			<label for="pwd">Code personnel:</label>
    			<input type="password"  class="form-control" id="pwd" name="pwd">
 			</div> 
 					
 			<div class="form-group stepContent">	
 			<button type="submit" class="btn btn-primary" id="submit" name="submit">Valider</button>			
 			<script>
	function che(){
		tt = document.getElementById("Alert");
		tt.style.visibility="visible";
		document.getElementById("numeroCpt").value = "";
		document.getElementById("montant").value = "";
		document.getElementById("pwd").value = "";
	}
		</script>
 			
 			<a href="#Alert" class="btn btn-primary" id="Annuler" onclick="che();">Annuler</a>		
 		</div>	
 		</form>	
 			<span class="stepContent">	
	Ce compte sera inscrit à votre contrat après <strong>validation</strong>.</span>
				<br>
	<span class="stepContent">Si vous voulez annuler votre demmande, cliquez<strong>Annuler</strong>
	</span>
 			</div>		 		
		</div>	
	 </div>

			<%}
		}catch (Exception e){}%>
<!-- footer -->
<footer>
</footer>
<div class="headerpage1"></div>
</body>
</body>
</html>