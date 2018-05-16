<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@page import="DAO.manager.*"%>
 <%@page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>somme dépôts</title>
<!-- Bootstrap Core CSS -->
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

<%	try{ 
		//If the user isn't logged in
		if (request.getSession().getAttribute("userManager")==null){
		response.sendRedirect("/Manager/LoginManager.jsp"); 
		}else{ %>
		
<body>
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

	<%
		String user = (String)request.getSession().getAttribute("userManager");
		String[] logs = user.split(" ");
		List<String> list = DAO.getListNumero();
		String numero;
	%>
	
		<div class="card bg-primary text-white">
    		<div class="card-body actions">Mon Espace</div>
 		</div>

	<div class="card">
    			<div class="actions">Bienvenue Manager <%= logs[0]  %>
    		 	</div> 
    		
    </div>
    	  <center><h3>Consulter les dépôts de chaqun client:</h3></center>
    <div class="container" >
	     	<%  double somme=0;
	     		double sommeClients=0;
								
									for(int i=0 ; i<list.size() ;i++)
	  								{    
										numero=list.get(i);										
										List<transaction> depot = DAO.getDepots(numero);
										if(!depot.isEmpty()){
					%>
					<table class="table table-bordered table-hover class">
						<thead>
							<tr>
								<th class="column1"><center>NumeroCpt</center></th>
								<th class="column2"><center>Montant</center></th>
								<th class="column3"><center>Motif</center></th>																
							</tr>
						</thead>
						<tbody>
									<% 
										for(int j=0;j<depot.size();j++){
											somme+=Double.parseDouble(depot.get(j).getMontant());
	  								%>
								<tr>									
									<td class="actions"><%= depot.get(j).getNumero() %></td>   
									<td class="actions"><%= depot.get(j).getMontant() %></td>
									<td class="actions"><%= depot.get(j).getMotif() %></td>																		
								</tr>
								<tr>
								
								<%} %>
								<td align="center" colspan="3" bgcolor="#CCCCCC">
								 <h4 id="total">Total: <%= somme %> euros</h4>
								</td>
								</tr>
								<% sommeClients+=somme;
								somme=0; %>
								<%} %>  
								
						</tbody>
					</table>	
					<%} %>		
	</div> 			
			<center><h3>La somme de depôt des clients: 
			<input type="text" readonly="true" style="border: 0px;" value=<%= sommeClients%> >
			</h3></center>
			
	
    
	<%}
		}catch(Exception e){}%> 
		
		 	
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