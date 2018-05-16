<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@page import="DAO.manager.*"%>    
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Repporting Actualités Bouciéres </title>
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


 <%
        List<transaction> list =  DAO.getTransaction();
        List<String> listAnnee = new ArrayList<>();
        float a=0, b=0, c=0, d=0;
        /*Repporting Actualités Bouciéres par annee*/
        
        for(int i=0; i<list.size();i++){       	
        	String[] date=list.get(i).getDate().split("/");
    		if(!listAnnee.contains(date[2])){
    			listAnnee.add(date[2]);	
    		}
        }	
 %>
 
		 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  		 <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        
    	  var data = google.visualization.arrayToDataTable([
          ['Year', 'Achat bousier', 'Vente Bousier'],
         
         <%  for(int j=0; j<listAnnee.size();j++){
	       	  
	       	  for(int i=0; i<list.size();i++){ 
	       		String[] date=list.get(i).getDate().split("/");
       		if(date[2].equals(listAnnee.get(j))){
	       		  String[] montant = list.get(i).getMontant().split(" ");
	       		  if(list.get(i).getMotif().equals("Achat boursier")){  
	   				a+=Double.parseDouble(montant[1]);    				
	   				
	   		}  
	   		
	   		if(list.get(i).getMotif().equals("Vente boursier")){    			
	   			  	b+=Double.parseDouble(montant[1]);    			  		   			  
	   		}
	   		
	      }
	       	  }	     
   	 %>
  	["<%=listAnnee.get(j)%>", <%=a %>, <%=b %>],
	<%a=0; b=0;%>       	
  <%
  }%>
          
        ]);

        var options = {
          chart: {
            title: 'Actualités boursières',
            subtitle: 'Achat boursier et Vente boursier chaque anneé',
          }
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
    </script>
<body>

<%	try{ 
		//If the user isn't logged in
		if (request.getSession().getAttribute("userManager")==null){
		response.sendRedirect("/Manager/LoginManager.jsp"); 
		}else{ %>
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
    
      <center><h4>Reporting Actualités Boursières</h4></center>
 <center><div id="columnchart_material" style="width: 800px; height: 500px;"></div></center>>
<%}
}
catch(Exception e){}%>

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