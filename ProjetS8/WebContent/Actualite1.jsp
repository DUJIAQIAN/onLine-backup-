
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">

    <head>
        <!-- èlèments obligatoires dans toute page HTML -->
        <title>offres-alternance</title>
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
            <h1 class="display-3">L'ALTERNANCE CHEZ JISP</h1>
            <p class="lead"><img id="imgAlternance" src="img/Recrutement-Alternance.jpg" alt="alternance"></p>
      
    
      
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h3 class="bg-info text-light mt-5">JISP, une entreprise qui recrute chaque annÃ©e en alternance</h3>
                    <header>
                        <ul>
                           <h3> Vous souhaitez :</h3>
                        </ul>
                    </header>
              
                
                    <article id="java">
                        <ul>
                           <li><h5>DÃ©couvrir le secteur de la banque/assurance ?</h5></li>
                        </ul>
                        
                         <ul>
                           <li><h5> Prendre part aux projets de transformation de notre entreprise pour répondre aux nouveaux besoins de clients toujours plus connectÃ©s ?</h5></li>
                        </ul>
                        
                         <ul>
                           <li><h5>IntÃ©grer une entreprise qui a Ã  cÅur de faire progresser ses alternants et leur permettre de bénéficier d'une expÃ©rience solide pour la suite de leur parcours ?</h5></li>
                        </ul>
                    </article>

        <div class="container">
         
            <div class="row">
                <div class="col-12 col-lg-6">
                    <h6>Chaque année, JISP recrute 600 alternants partout en France et sur diffÃ©rentes fonctions de lâentreprise (mÃ©tiers en contact avec la clientÃ¨le et fonctions support : finance, informatique, marketing, RH, etc.), dÃ¨s le niveau bac+3. Lâalternance peut sâeffectuer en contrat dâapprentissage, ou en contrat de professionnalisation (pour en savoir plus :<a href="#"> cliquez ici</a>).</h6>
                </div>
                <div class="col-12 col-lg-5 ml-lg-auto">                    
                    <iframe id="videoEsigelec" src="https://www.youtube.com/embed/4kHLn5MMjtk"></iframe>
                </div>
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