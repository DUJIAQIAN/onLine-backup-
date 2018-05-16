<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
            <%@page import="DAO.manager.*"%>
       
<%@page import="java.util.*"%>
<%     
   response.setHeader("refresh" , "30" );  
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reporting Virment</title>

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


<div id="piechart"></div>
<% 

/*Repporting Virement par type(interne/externe)*/
List<transaction> list =  DAO.getTransaction();
List<String> listAnnee = new ArrayList<>();
float a=0, b=0, c=0,d=0;

String ope;
for(int i=0; i<list.size();i++){
	ope=list.get(i).getOperation();		
	String[] montant = list.get(i).getMontant().split(" ");
	if(list.get(i).getMotif().equals("Virement interne")){
		if(ope.equals("créditer")){
	  	a+=Double.parseDouble(montant[1]);
	  	c+=Double.parseDouble(montant[1]);
	  	}
		if(ope.equals("débiter")){
		a-=Double.parseDouble(montant[1]);
		d+=Double.parseDouble(montant[1]);
		}
	}
	if(list.get(i).getMotif().equals("Virement externe")){
		if(ope.equals("créditer")){
	  	b+=Double.parseDouble(montant[1]);
	  	c+=Double.parseDouble(montant[1]);
	  	}
		if(ope.equals("débiter")){
		b-=Double.parseDouble(montant[1]);
		d+=Double.parseDouble(montant[1]);
		}
		
		/*Repporting Virement par annee*/
		String[] date=list.get(i).getDate().split("/");
		if(!listAnnee.contains(date[2])){
			listAnnee.add(date[2]);			
		}
}
  



}		
	%>

</head>

 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() { 
    	/*Visualizations*/
      var data = google.visualization.arrayToDataTable([
        ["Type", "somme", { role: "style" } ],
        ["Virement Interne", <%=a%>, "#b87333"],    
        ["Virement Externe", <%=b%>, "gold"],
      ]);
      
      var data1 = google.visualization.arrayToDataTable([
          ['Type', 'somme', { role: 'style' }],
          ['Crédit', <%=c%>, 'stroke-color: #703593; stroke-width: 4; fill-color: #C5A5CF'],
       	  ['Débit', <%=d%>, 'stroke-color: #871B47; stroke-opacity: 0.6; stroke-width: 8; fill-color: #BC5679; fill-opacity: 0.2']          
        ]);
      
      var data2 = google.visualization.arrayToDataTable([
	        ["Type", "somme", { role: "style" } ],
	        <%float vmtAnnee=0;
	         for(int i=0;i<listAnnee.size();i++){       		
	         
	        	for(int j=0; j<list.size();j++){
	        		String[] date=list.get(j).getDate().split("/");
	        		if(date[2].equals(listAnnee.get(i))){
	        			if(list.get(j).getMontant().split(" ")[0].equals("+")) {
	        			vmtAnnee += Float.parseFloat(list.get(j).getMontant().split(" ")[1]) ;
	        			}else {
	        			vmtAnnee -= Float.parseFloat(list.get(j).getMontant().split(" ")[1]) ;
	        			}
	        		}      		      		
	          }%>
	        	["<%=listAnnee.get(i)%>", <%= vmtAnnee%>,"#b87333"],
	        	<%vmtAnnee=0;%>       	
	          <%}%>
    ]);
	
      var view = new google.visualization.DataView(data);
      var view1 = new google.visualization.DataView(data1);
      var view2 = new google.visualization.DataView(data2);
     
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);
      
      view1.setColumns([0, 1,
          { calc: "stringify",
            sourceColumn: 1,
            type: "string",
            role: "annotation" },
          2]);
      
      view2.setColumns([0, 1,
          { calc: "stringify",
            sourceColumn: 1,
            type: "string",
            role: "annotation" },
          2]);
      
 
      var options = {
        title: "Reporting Virement",
        width: 600,
        height:300,
        bar: {groupWidth: "30%"},
        legend: { position: "none" },
      };
      
      var chart = new google.visualization.ColumnChart(document.getElementById("reportVmt"));
      chart.draw(view, options);
      
      var chart1 = new google.visualization.BarChart(document.getElementById("reportVmtCD"));
      chart1.draw(view1, options);
      
      var chart2 = new google.visualization.ColumnChart(document.getElementById("reportVmtAnnee"));
      chart2.draw(view2, options);
      
     
  }
    
     
     
  </script>
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
    
    <div class="container">
   <center><h4>Reporting Virement</h4></center>
	<center><div id="reportVmt" style="width: 900px; height: 300px;" class="virement"  ></div></center>
	
	<center><div id="reportVmtCD" style="width: 900px; height: 300px;" class="virement"  ></div></center>

   <center><div id="reportVmtAnnee" style="width: 900px; height: 300px;" class="virement"  ></div></center>
</div>
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