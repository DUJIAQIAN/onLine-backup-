<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@page import="cacRead.*" %>
               <%@page import="DAO.jee.*"%>
        <%@page import="Cpt.*"%>
<%@page import="java.util.*"%>
<%@page import="net.codejava.excel.*" %>
<%@page import="net.codejava.excel.readstock" %>
<%@page import="java.util.*" %>
<%@page import="java.io.File" %>
<%@page import="java.io.FileInputStream" %>
<%@page import="java.io.IOException" %>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.poi.ss.usermodel.Cell"%>
<%@page import="org.apache.poi.ss.usermodel.Row" %>
<%@page import="org.apache.poi.ss.usermodel.Sheet" %>
<%@page import="org.apache.poi.ss.usermodel.Workbook" %>
<%@page import="org.apache.poi.xssf.usermodel.XSSFWorkbook" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EuroNext Paris</title>

     <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
    <link href="vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet">

    <!-- Custom CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body id="page-top">

	 <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="index.jsp">JISP BANK</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="#about">History</a>
            </li>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="#services">Real-Time</a>
            </li>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="#third">Cours</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

 <header class="bg-primary text-white">
      <div class="container text-center">
        <h1>Bienvenue sur les services boursiers de JISP</h1>
        <p class="lead">La bourse la plus simple du monde.</p>
      </div>
    </header>
    
    <section id="about">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 mx-auto">
            <h2>History</h2>
            <p class="lead">Historique de la cotation du CAC 40 sur les 3 dernières années :</p>
            <%@ include file="plotCAC.jsp" %>
          </div>
        </div>
      </div>
    </section>
    
    <section id="services" class="bg-light">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 mx-auto">
            <h2>Le cours en direct :</h2>
            <p class="lead">L'indice CAC 40 vaut actuellement :</p><% cacRead read = new cacRead();
			String line = read.readCAC();%>
			<h1><%=line %></h1>
          </div>
        </div>
      </div>
    </section>
    
     <div class="container">
        <div class="row align-items-start">
          <div class="col-8 col-sm-6">
            <h2>Investissez dans les actions du CAC :</h2>
     		<%@ include file="stocks.jsp" %>
     		</div>
     		<div class="col-8 col-sm-6">
     		<%	try{ 
		//If the user isn't logged in
		if (request.getSession().getAttribute("user")==null){%>
		<h2>Vous n'êtes pas connecté</h2><%}
		else{ 
			String user = (String)request.getSession().getAttribute("user");
			String[] logs = user.split(" ");
			int compte = DAO.getNumCptTitre(logs[0], logs[1]);
			if (compte==0){
				%><h2>Vous n'avez pas de compte titre</h2><%
			}else{
				Cpt cptCourant = DAO.getSolde(logs[0], logs[1]);
				List<Action> listActions = DAO.selectActions(compte);
			%>
			<div class="pull-left" style="width: 200px;">
	
			<div class="card bg-primary text-white">
		    		<div class="card-body actions">Mon compte titre</div>
		 		</div>
		
			<div class="card">
		    			<div class="actions">Compte Titre n°: <%= compte %>
		    		 	</div> 
		    		 	<div class="actions">Fonds disponibles: <%= cptCourant.getSolde() %>
		    		 	</div> 
		    </div>		 	  
		    <h2>Vos Actions :</h2>    
							<table class="table table-bordered table-hover class">
								<thead>
									<tr>
										<th class="column6" style="color:white;"><center>Action</center></th>
										<th class="column6" style="color:white;">Quantite</th>
										<th class="column6" style="color:white;">Prix</th>																
									</tr>
								</thead>
								<tbody>
									<%	for(int i=0; i<listActions.size();i++)
										{
											if(listActions.get(i).getNumAchat()!=0){
										%>	
										<tr  style="width: 50px;">
											<td class="actions" ><%=listActions.get(i).getNomAction()%></td>              								
											<td class="actions"><%= listActions.get(i).getNumAchat()%></td>	
											<td class="actions"><%= listActions.get(i).getTotal()%></td>								
										<%}
										}%>
										</tr>		  
								</tbody>
							</table>			
			
          <span class="text-danger"  name="fautSolde"><h4 class="text-center">${infos['solde']}</h4></span>
          <span class="text-danger" id="successCreation" name="successCreation"><h4 class="text-center">${infos['successCreation']}</h4></span>  
          
           <h2>Achetez des actions :</h2>
     		 <form action="ServletAchat" method="post" accept-charset='UTF-8'>	     
				<table class="table table-bordered table-hover class" width="300">
				<thead>
	 				 <tr>
				  		<th class="column6" style="color:white;">Company</th>
				  		<th class="column6" style="color:white;">Prix</th>
				  		<th class="column6" style="color:white;"><center>Acheter</center></th>
				  		<th class="column6" style="color:white;">total</th>
	  				</tr>
				 </thead>
				 <tbody>
	 		 <% String excelFilePath2 = "C:\\Users\\horizon\\eclipse-workspace\\ProjetS8\\WebContent\\stocksCAC40.xlsx";
	   			 readstock reader2 = new readstock();
	    		List<readstock.stock> list2 = reader2.readStocks(excelFilePath2);
			 %>
			 <tr>
	  			<td>
	  				<select id="slist" name="slist"  onchange="selectShow()" >
					  		<% for(int i=1 ; i<41 ;i++)
					   		 {%>
					    		<option value=<%=list2.get(i).getValue()%>><%=list2.get(i).getName()%></option>	   		
					   		 <%}%>
					  	</select >
					  	<input id="nom" name="nom" type="hidden" onchange="selectShow()">
				</td>
					  <td><input id="prix" name="prix" type="text" readonly="true" class="achat"></td>
					  <td><input id="qttAchat" name="qttAchat" type="text" onkeyup="account()" required/></td>
					  <td><input id="total" name="total" type="text" readonly="true" class="achat" ></td>
				</tr>
				<tr height="40">  
                	<td align="center" colspan="4" bgcolor="#CCCCCC">  
                    <button type="submit" class="btn btn-primary"  id="submit" name="submit">Valider</button>  
                     <input type="reset" class="btn btn-primary" name="reset" value="Reset" />
                	</td>  
    		    </tr>
					  </tbody>
					</table>
					
					</form>	
					

					<script type="text/javascript">
					 function account(){
					     a=document.all.prix.value;
					     b=document.all.qttAchat.value;
					     c=a*b;
					    document.all.total.value=c.toFixed(2);	
					 }
						
					 window.onload=selectShow;
					 function selectShow(){
						 var select = document.getElementById("slist");
						 var options = select.options;
						 var index = select.selectedIndex;
					 	document.getElementById("prix").value= select.value; 	 
					 	document.getElementById("nom").value = options[index].text;
					 	document.getElementById("total").value ="";
					 	document.getElementById("qttAchat").value ="";
					 }
					 </script>	
					 
					 
	<%
		int compte_1 = DAO.getNumCptTitre(logs[0], logs[1]);	
		List<Action> listActions_1 = DAO.selectActions(compte_1);
	%>	 	
	<h2>Vendez des actions :</h2>
    <div class="container" style="width:50px;">
	
	    <form action="ServletVendre" method="post" accept-charset='UTF-8'>	     
					<table class="table table-bordered table-hover class">
						<thead>
							<tr>
								<th class="column6" style=color:white;><center>Action</center></th>
								<th class="column6" style=color:white;><center>Vendre</center></th>
								<th class="column6" style=color:white;><center>Prix</center></th>																
							</tr>
						</thead>
						<tbody>
								<tr>
									<td class="actions" >
									<select id="slist1" name="slist1"  onchange="selectShow1()">
	  									<% for(int i=0 ; i<listActions.size() ;i++)
	  										if(listActions.get(i). getNumAchat()!=0){
	   		 								{%>
	    								<option value=<%=listActions_1.get(i).getValueAction()%>><%=listActions_1.get(i).getNomAction()%></option>	   		
	   									 <%}}%>
	  								</select >
	  								<input id="nom1" name="nom1" type="hidden" onchange="selectShow1()">
									</td>              								
									<td>
									<p>Quantity: <button type="button" class="btn btn-primary" onclick="numDec()">-</button> 
									<button type="button" class="btn btn-primary" onclick="numAdd()">+</button>
									<input style="border-width: 1px;" id="qttAchat1" name="qttAchat1" type="text" onkeyup="account1()" required/>
									</p> 
									</td>	
									<td><input id="total1" name="total1" type="text" readonly="true" style="border: 0px;" class="achat" ></td>								
							
								</tr>	
								
							    <tr height="40">  
                	<td align="center" colspan="3" bgcolor="#CCCCCC">  
                    <button type="submit" class="btn btn-primary"  id="submit" name="submit">Valider</button>  
                     <input type="reset" class="btn btn-primary" name="reset" value="Reset" />
                	</td>  
    						 </tr>	  
						</tbody>
					</table>			
			</form>
	</div> 		
					</div>	
					</div>
					
					</div>
				</div>	  			
					 <script type="text/javascript">
					 function account1(){
					     a=document.all.slist1.value;
					     b=document.all.qttAchat1.value;
					     c=a*b;
					    document.all.total1.value=c.toFixed(2);	
					 }
						
					 window.onload=selectShow1;
					 function selectShow1(){
						 var select = document.getElementById("slist1");
						 var options = select.options;
						 var index = select.selectedIndex; 	 
					 	document.getElementById("nom1").value = options[index].text;
					 	document.getElementById("total1").value ="";
					 	document.getElementById("qttAchat1").value ="";
					 }
					/*Augmenter 1 quand cliquer +*/ 
					 function numAdd(){  
					     var quantity = document.getElementById("qttAchat1").value; 
					     var num_add = parseInt(quantity)+1;
					     var price=document.all.slist1.value;
					     if(quantity==""){  
					         num_add = 1;  
					     }     
					         document.getElementById("qttAchat1").value=num_add;
					  		account1();
					 }  
					 
					 /*Réduire 1 quand cliquer -*/ 
					 function numDec(){  
					     var quantity = document.getElementById("qttAchat1").value;    
					     var num_dec = parseInt(quantity)-1;  
					     if(num_dec>0){  
					         document.getElementById("qttAchat1").value=num_dec;
					         account1();
					     }  
					 }  
					 </script>

     		<%}
		}
     		}catch (Exception e){} %>
     		




</body>
</html>