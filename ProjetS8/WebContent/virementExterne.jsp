<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="DAO.jee.*"%>
    <%@page import="Cpt.*"%>
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
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Virements externes</title>
<body>
<%try{ 
		//If the user isn't logged in
		if (request.getSession().getAttribute("user")==null){
		response.sendRedirect("/ProjetS8/LoginForm.jsp"); 
		}else{ 
			String user = (String)request.getSession().getAttribute("user");
			String[] logs = user.split(" ");
			
			Cpt compte = DAO.getSolde(logs[0], logs[1]);
		%>
		
		
<div id="page">
		<div class="headerpage">
			<p class="banquename" style="color:white;">JISP</p>
			
			</div>
		<div class="headerpage1"></div>
	</div>	
	
	
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
    				<li><a href="/ProjetS8/CreerCptTitre.jsp"><span class="glyphicon glyphicon-chevron-right"></span>
    				Créer un compte titre</a></li>
  				</ul>
		  </div>
		  
		   <div class="btn-group" role="group">
          	 <button type="button" class="btn btn-default" data-toggle="dropdown">
               Opérations Courantes <span class="caret"></span></button>
               <ul class="dropdown-menu" role="menu" style="min-width:100%">
               <li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span>
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
	<h1><p class="text-info text-center ">Effectuer un virement externe</p></h1>
	<div class="container">	
	   <div class="step">		
				<span class="detail">
					&nbsp;Détails
				</span>	
				<br>		
			<span class="stepContent step">	
				<strong>Faire un virement externe</strong>  :
			</span>
		</div>
		<div class="stepContent step">
			<ul>
			<li><strong>Option</strong> Chez JISP Bank, il est possible d'ajouter le beneficiaire du virement en nous envoyant un message sur votre Espace Client.
			 Suite à la validation du beneficiaire par la banque, renseignez le beneficiaire en question puis indiquez le montant du virement, avant de le valider.</li>
			<br>
			<li><strong>Attention</strong> Pour les virements externes, après avoir renseigné l'IBAN du compte bénéficiaire, le numéro du compte à débiter,
			 le montant du virement et votre code secret, l'opération sera en attente de validation par la banque afin de vérifier
			 l'IBAN du destinataire.</li>			
			</ul>
		</div>
	</div>
	
	<div class="container">
	   <div class="step">							
		
			<div class="container">
			 <form class="form-inline " action="ServletVirementExterne" method="post" accept-charset='UTF-8'>
  			<div class="form-group stepContent">
   			 	 <label for="numeroCpt">IBAN du compte bénéficiaire</label>
   				 <input type="text" class="form-control" id="iban" name="iban" maxlength="20" placeholder="FR76 1245 5623 4789 5696" required>
  			</div>	
  			<div class="form-group stepContent">
  			<Strong>Sélectionnez</Strong> un compte:
  				 <select name="numeroCpt" required >
					<option value="<%= compte.getNumero() %>" selected="selected"><%= compte.getNumero() %></option>
					
   			 	 </select>
  			</div>		  
  			<div class="form-group stepContent">
   			 	 <label for="montant">Montant</label>
   				 <input type="text" class="form-control" id="montant" name="montant" placeholder="10 euros minimum">
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
	Cette transaction sera validée par votre banque <strong>validation</strong>.</span>
				<br>
	<span class="stepContent">Si vous voulez annuler votre demmande, cliquez<strong> &lt;Annuler&gt;</strong>
	</span>
 			</div>		 		
		</div>	
	 </div>
<%} 
	}catch (Exception e){} %>
			
<!-- footer -->
<footer>
	
</footer>
<div class="headerpage1"></div>
</body>
</body>
</html>