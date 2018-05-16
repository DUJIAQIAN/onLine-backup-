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

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
<title>Créer Compte Epargne</title>
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
    		<a type="submit" class="btn btn-default" href="index.jsp">
    		<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
    		Accueil</a>
    		</div>
     
  		   <div class="btn-group">
   		   <button type="button" class="btn btn-default" data-toggle="dropdown">
    		Vos Compte  <span class="caret"></span></button>
    			<ul class="dropdown-menu" role="menu" style="min-width:100%">
    				<li><a href="Solde.jsp"><span class="glyphicon glyphicon-chevron-right"></span>
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
               <li><a href="virementInterne.jsp"><span class="glyphicon glyphicon-chevron-right"></span>
    				Virements interne</a></li>
    			<li><a href="#"><span class="glyphicon glyphicon-chevron-right"></span>
    				Virements externe</a></li>
    		  </ul>
  		  </div>
		</div>
	</div>
</section>

<span class="text-danger" id="erreur"><h4 class="text-center">${erreurs['erreur']}</h4></span>
<!-- créer un compte épargne -->
	<h1><p class="text-info text-center ">Création d'un compte épargne</p></h1>
	
	<div class="container">
		<div class="step">		
				<span class="detail">
					&nbsp;En Détail
				</span>	
				<br>	
		</div>
		<div class="stepContent step">
			<strong>RÉMUNÉRATION</strong><br>
			 <ul>
			  <li>
				Le taux du Livret A est de <strong>0,75%</strong> (taux en vigueur au 01/08/2015) net d'impôts et de contributions sociales.
			  </li>
			 </ul>
			 
			 <strong>PLAFOND</strong><br>
			 <ul>
			 	<li>Le plafond du Livret A est de 22 950 euros.
					Ce plafond ne peut être dépassé que par la capitalisation des intérêts.</li>

				<li>Exemple : votre Livret A est actuellement à 16 280 euros en raison des dépôts et retraits effectués et de la capitalisation des intérêts.

					Vous pouvez donc verser 22 950 euros - 16 280 euros soit 6 670 euros.
			 	</li>
			 </ul>
			 
			 <strong>DURÉE</strong><br>
			 <ul>
			  <li>
				La durée du Livret A est illimitée.
			  </li>
			 </ul>
			 
			  <strong>VERSEMENTS ET ALIMENTATION</strong><br>
			 <ul>
			  <li>
				Les versements sur le Livret A sont de 10 euros minimum pour le dépôt initial et les versements ultérieurs.
			 </li>
			  <li>L'alimentation du Livret A est libre ou périodique (mensuelle , trimestrielle, semestrielle).
			  </li>
			 </ul>
			 		 
		</div>
	</div>
	
	<div class="container">	
	   <div class="step">		
				<span class="stepNum">
					&nbsp;1
				</span>			
			<span>	
				Consultez les <strong>conditions relatives</strong> à la création d'un compte épargne :
			</span>
		</div>
	<div class="stepContent step">
	
			Vous vous apprêtez à demander l&#39;ajout à votre contrat d&#39;un compte épargne dont <strong>vous n&#39;êtes pas titulaire ou co-titulaire</strong> (compte joint).
			<br/>
		   <strong>Seuls peuvent être ajoutés</strong> grâce à cette fonction les comptes dont :
						
			<ul>
			  <li>
				<strong>le titulaire est un enfant mineur non émancipé dont vous êtes le représentant légal,</strong>
			  </li>
			  <li>
				<strong>le titulaire, ou chacun d&#39;entre eux s&#39;il s&#39;agit d&#39;un compte collectif, vous a nommé comme mandataire au titre d&#39;un acte de procuration générale dûment signé et enregistré chez JISP,</strong>
			  </li>
			  <li>
				<strong>le titulaire majeur est placé sous un régime juridique de protection des incapables qui vous a désigné en qualité de représentant légal.</strong> 
			  </li>
			</ul>

			<strong>Le titulaire majeur du compte dont vous souhaitez la création d'un compte épargne à votre contrat sera demandé à faire un virement par votre compte courant à donner son accord à votre demande.</strong>
				<br/>
				Vous reconnaissez que vous êtes bien dans l&#39;un des cas repris ci-dessus.
									
	</div>	
</div>

	

<div class="container">	
	   <div class="step">	
				<span class="stepNum">					  		
					&nbsp;2
				</span>			
			<span>	
				<strong >Saisissez</strong> les coordonnées du compte courant à associer :
			</span>
			 <form class="form-inline " action="ServletCptEpargne" method="post" accept-charset='UTF-8'>
  			<div class="form-group stepContent">
   			 	 <label for="numeroCpt">Numéro de compte (Ex. : 8888)</label>
   				 <input type="text" class="form-control" id="numeroCpt" name="numeroCpt">
  			</div>	
  			<div class="form-group stepContent">
   			 	 <label for="vmtInitial">Versement initial (10 euros minimum):</label>
   				 <input type="text" class="form-control" id="vmtInitial" name="vmtInitial">
  			</div>
  		
  			<span class="stepContent">	
				Ce compte sera inscrit à votre contrat après validation d'étape 3 ci-dessus.
			</span>
			
			
			<div class="container">
	  		<div class="step">
				<span class="stepNum">
					&nbsp;3
				</span>			
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
			document.getElementById("vmtInitial").value = "";
			document.getElementById("pwd").value = "";
	}
	</script>
 			
 			<a href="#Alert" class="btn btn-primary" id="Annuler" onclick="che();">Annuler</a>		
 		</div>			
 	</div>
 	<span class="stepContent">	
	Ce compte sera inscrit à votre contrat après <strong>validation</strong>.</span>
				<br>
				<span class="stepContent">Si vous voulez annuler votre demmande, cliquez<strong>Annuler</strong>
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