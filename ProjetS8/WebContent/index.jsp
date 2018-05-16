<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 
  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>jisp bank</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">
    
    <!-- bootstrap -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <!-- Custom CSS -->
    <link href="css/stylish-portfolio.min.css" rel="stylesheet">
  	

  </head>
 
  <body id="page-top">
  <%	try{ 
		//If the user isn't logged in
		if (request.getSession().getAttribute("user")!=null){
			String username = (String)request.getSession().getAttribute("user"); %>
			<h1><%= username %></h1> <%}
		}catch (Exception e){} %>
 
    <!-- Navigation -->
    <a class="menu-toggle rounded" href="#">
      <i class="fa fa-bars"></i>
    </a>
    <nav id="sidebar-wrapper">
      <ul class="sidebar-nav">
        <li class="sidebar-brand">
          <a class="js-scroll-trigger" href="#page-top">Start Jisp</a>
        </li>
        <%if ((String)request.getSession().getAttribute("user")!=null){ %>
       <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/ProjetS8/Infoperso.jsp">Modifier mes informations personnelles</a>
        </li>
        <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/ProjetS8/Solde.jsp">Mes comptes</a>
        </li>
        <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/ProjetS8/historique.jsp">Historique des transactions</a>
        </li>
        <%} %>
        <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="#about">About</a>
        </li>
        <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="#portfolio">Services</a>
        </li>
        <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="#contact">Contact</a>
        </li>
      </ul>
    </nav>

    <!-- Header -->
    <header class="masthead d-flex">
      <div class="container text-center my-auto">
        <h1 class="mb-1" style="color:black;"><b>JISP BANK</b></h1>
        <h3 class="mb-5" style="color:black; background-color:grey;">
          <em>La banque en ligne la plus simple du monde.</em>
        </h3>
         <%try { if ((String)request.getSession().getAttribute("user")==null){ %>
        	<a class="btn btn-primary btn-xl js-scroll-trigger" href="/ProjetS8/logout" style="display: none">Se déconnecter</a>
        	<a class="btn btn-primary btn-xl js-scroll-trigger" href="/ProjetS8/LoginForm.jsp" style="display: block">Se connecter</a>
        <%} else {%>
        	<a class="btn btn-primary btn-xl js-scroll-trigger" href="/ProjetS8/logout" style="display: block">Se déconnecter</a>
        	<a class="btn btn-primary btn-xl js-scroll-trigger" href="/ProjetS8/LoginForm.jsp" style="display: none">Se connecter</a>
        <%} } catch(Exception e){ System.out.println(e);}%>
      </div>
      <div class="overlay"></div>
    </header>


    <!-- Portfolio -->
    <section class="content-section" id="portfolio">
      <div class="container">
        <div class="content-section-heading text-center">
          <h2 class="mb-5"><b>Nos services</b></h2>
        </div>
        <div class="row no-gutters">
          <div class="col-lg-6">
            <a class="portfolio-item" href="/ProjetS8/EmailForm.jsp">
              <span class="caption">
                <span class="caption-content">
                  <h1><b> Ouvrir un compte courant </b></h1>
                  <p class="mb-0"><b>Votre compte bancaire en ligne pour toutes vos transactions</b></p>
                </span>
              </span>
              <img class="img-fluid" src="img/portfolio-1.jpg" alt="">
            </a>
          </div>
          <div class="col-lg-6">
            <a class="portfolio-item" href="/ProjetS8/SeeCAC.jsp">
              <span class="caption">
                <span class="caption-content">
                  <h1><b>Consulter l'actualité boursiére</b></h1>
                  <p class="mb-0"><b>La bourse, en ligne, en direct</b></p>
                </span>
              </span>
              <img class="img-fluid" src="img/portfolio-2.jpg" alt="">
            </a>
          </div>
          <div class="col-lg-6">
            <a class="portfolio-item" href="/ProjetS8/CreerCptEpargne.jsp">
              <span class="caption">
                <span class="caption-content">
                  <h1><b>Ouvrir un plan d'épargne</b></h1>
                  <p class="mb-0"><b>Couvrez vous contre l'inflation avec nos offres de livret A</b></p>
                </span>
              </span>
              <img class="img-fluid" src="img/portfolio-3.jpg" alt="">
            </a>
          </div>
          <div class="col-lg-6">
            <a class="portfolio-item" href="CreerCptTitre.jsp">
              <span class="caption">
                <span class="caption-content">
                  <h1><b>Ouvrir un compte-titre</b></h1>
                  <p class="mb-0"><b>Créez votre portefeuille d'actions du CAC 40</b></p>
                </span>
              </span>
              <img class="img-fluid" src="img/portfolio-4.jpg" alt="">
            </a>
          </div>
        </div>
      </div>
    </section>
    
<!-- Actualités -->
   <section class="content-section"  id="actualite" >
	
	<div class="bloc-actu bgc-grey ">
	
	  <div class="content-section-heading text-center">
          <h2 class="text-secondary mb-0"><b>Nos Actualités </b> </h2>      
       </div>
        
        <div class="bloc-actu_links">
         <div class="float-left">
     		
      	 <a href="Actualite1.jsp" class="medium-lien"  target= “_blank” rel="nofollow"><span class="glyphicon glyphicon-chevron-right"></span>
      	 L'ALTERNANCE CHEZ JSIP</a> 
      	 
         <a href="Actualite2.jsp" class="medium-lien" target= “_blank”><span class="glyphicon glyphicon-chevron-right"></span>
         IMPÔTS 2018 :LES PRINCIPAUX CHANGEMENTS</a> 
      	
      	 <!-- <a href="#"  class="btn-orange" title="Actualités LCL" >Toutes les actualites JSIP</a> -->
      		
         </div>   
      
     		<div class="float-right">
      	 <a href="Actualite1.jsp" class="medium-lien"  target= “_blank” rel="nofollow"><span class="glyphicon glyphicon-chevron-right"></span>
      	 L'ALTERNANCE CHEZ JSIP</a> 
         <a href="Actualite2.jsp" class="medium-lien" target= “_blank”><span class="glyphicon glyphicon-chevron-right"></span>
         IMPÔTS 2018 :LES PRINCIPAUX CHANGEMENTS</a> 
     </div>
      	</div>
       
	
  <div id="myCarousel" class="carousel slide"  data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner"  role="listbox">

      <div class="item active">
      <a href="Actualite1.jsp">  
      <img src="img/Recrutement-Alternance.jpg" alt="recrutement" style="width:100%;">
      </a>
      </div>

      <div class="item">
      <a href="Actualite2.jsp"> 
        <img src="img/Impots-2018.jpg" alt="Impots" width="400" style="width:100%;">
        </a>
      </div>
  
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>

  </section>
  
    
    <!-- Call to Action -->
    <section class="content-section bg-primary text-black">
      <div class="container text-center">
        <h2 class="mb-4">Nous contacter</h2>
        
        <center><jsp:include page="EmailFormContact.html"/></center>
        
      </div>
    </section>

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
