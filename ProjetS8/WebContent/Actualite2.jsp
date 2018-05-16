<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>Impots 2018</title>
        <meta charset="utf-8">
        <!-- DÃ©claration de page adaptative -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Chargement de la librairie font-awesome -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- Chargement de la feuille de style adaptative Bootrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
        <!-- Chargement de la feuille de style locale -->
        <link rel="stylesheet" href="styles.css">
         <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/stylish-portfolio.min.css" rel="stylesheet">
        
    </head>
    <!-- Contenu de la page -->
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
          <a class="js-scroll-trigger" href="/ProjetS8/index.jsp">Accueil</a>
        </li>
        <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/ProjetS8/Actualite1.jsp">offres-alternance</a>
        </li>
        <li class="sidebar-nav-item">
          <a class="js-scroll-trigger" href="/ProjetS8/Actualite2.jsp">Impots 2018</a>
        </li>
      </ul>
    </nav>
     	
        
        <!-- La page d'accueil contient souvent un Jumbotron, qui vèhicule le message fort de la page -->
     
            <h1 class="display-3">Les impÃ´ts 2018</h1>
            <p class="lead"><img id="imgBigData" src="img/Impots-2018.jpg" alt="bigdata"></p>
 
        <h3 id="Impots" class="bg-info text-light">PrÃ©lÃ¨vement Ã  la source de lâimpÃ´t sur le revenu</h3>
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-6 col-lg-6">
                        <article>
                        <p>La mise en place du prÃ©lÃ¨vement Ã  la source de lâimpÃ´t sur le revenu (PAS), initialement prÃ©vue en 2018, interviendra finalement au 1er janvier 2019 et portera sur les revenus concernÃ©s perÃ§us Ã  compter de cette date.</p>
                        <!-- Un lien dont l'attribut href a pour valeur une URI absolue dirige vers une ressource d'un autre site -->                       
                    </article>
                </div>
                <div class="col-12 col-md-6 col-lg-6">
                    <article id="langageC">
                        <p>DÃ¨s le printemps 2018, vous pourrez consulter votre taux de prÃ©lÃ¨vement personnel lors de la dÃ©claration en ligne de vos revenus 2017. Vous pourrez plutÃ´t opter, si vous le souhaitez, pour des taux individualisÃ©s au sein du couple ou, si vous Ãªtes salariÃ©, pour un taux neutre.</p>
                        <aside>Pour en savoir plus, voir notre article consacrÃ© au 
                        <a href="#">prÃ©lÃ¨vement Ã  la source de lâimpÃ´t sur le revenu.</a></aside>
                    </article>
                </div>
            </div>
            </div>
            
              <div class="container">
             <div class="row">
                <div class="col-12">
                    <h3 class="bg-info text-light mt-5">FiscalitÃ© de lâÃ©pargne : instauration dâun prÃ©lÃ¨vement forfaitaire unique (PFU)</h3>
                </div>
        
                <article class="col-12 col-md-6 col-lg-4">                    
                   <p>La fiscalitÃ© des revenus mobiliers (intÃ©rÃªts, dividendes) et des plus-values mobiliÃ¨res perÃ§us depuis le 1er janvier 2018 est profondÃ©ment modifiÃ©e par lâinstauration dâun prÃ©lÃ¨vement forfaitaire unique (PFU). Le PFU, ou Â« flat tax Â», consiste en une imposition Ã  l'impÃ´t sur le revenu Ã  un taux forfaitaire unique de 12,8 % auquel s'ajoutent les prÃ©lÃ¨vements sociaux de 17,2 %, soit une taxation globale de 30 %. Lâabattement de 40 % sur les dividendes et les abattements sur plus-values pour durÃ©e de dÃ©tention ne sont plus applicables (sauf nouvel abattement pour dirigeants partant Ã  la retraite).</p>                  
                </article>
                
                <article  lang="fr" class="col-12 col-md-6 col-lg-4">                  
                   <p>Les contribuables y ayant intÃ©rÃªt peuvent toutefois renoncer au PFU et opter pour l'imposition selon le barÃ¨me progressif de l'impÃ´t sur le revenu (option globale portant obligatoirement sur l'ensemble des revenus mobiliers et plus-values mobiliÃ¨res). Dans ce cas, lâabattement de 40 % sur les dividendes est applicable ainsi que, pour les plus-values de cession de titres acquis avant le 1er janvier 2018, l'abattement proportionnel de droit commun pour durÃ©e de dÃ©tention et l'abattement renforcÃ© pour les titres de PME de moins de 10 ans.</p>                  
                </article>
                
                      <article  lang="fr" class="col-12 col-md-6 col-lg-4">         
                       <p>L'imposition en deux temps des revenus mobiliers est maintenue : un prÃ©lÃ¨vement forfaitaire non libÃ©ratoire (PFNL) au taux de 12,8 % est perÃ§u Ã  titre d'acompte lâannÃ©e du versement des revenus (sauf exception pour les contribuables modestes) et l'annÃ©e suivante, ces revenus sont soumis Ã  l'impÃ´t sur le revenu (PFU ou, sur option, barÃ¨me progressif) sous dÃ©duction du PFNL prÃ©levÃ© Ã  la source.</p>   
                  		<aside>La fiscalitÃ© des PEL et CEL ouverts depuis le 1er janvier 2018 est Ã©galement modifiÃ©e et la prime dâÃ©pargne supprimÃ©e : voir notre article
                        <a href="#">Pourquoi ouvrir un PEL et un CEL avant le 31 dÃ©cembre 2017 ?</a></aside>
                  </article>
            </div>		
            </div>
            
          <footer id="basdepage" class="bg-secondary mt-3 pt-2">
            <div class="container">
                <div class="row">
                    <div id="adresse" class="col-12 col-md-6">
                        <address>
                            <p>JSIP : TechnopÃ´le du Madrillet<br>
                                Avenue GalilÃ©e - BP 10024<br>
                                76801 Saint-Etienne du Rouvray Cedex</p>
                        </address>
                    </div>
                    <div id="contact" class="col-12 col-md-6">
                        <p><i class="fa fa-phone fa-lg"></i>&nbsp;(+33) 02 12 34 56 78 90</p>
                        <p><i class="fa fa-envelope fa-lg"></i>&nbsp;JSIP@esigelec.fr</p>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Ajout des scripts pour Bootstrap
jQuery, puis Popper.js, et enfin Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
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