<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
               <%@page import="DAO.manager.*"%>
       
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

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
		for(int j=0; j<list.size();j++){
			 String[] date=list.get(j).getDate().split("/");
			
				if(!listAnnee.contains(date[2])){
					listAnnee.add(date[2]);				
				}			
			 }
}
}
	%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
    	 var data2 = google.visualization.arrayToDataTable([
    	        ["Type", "somme", { role: "style" } ],
    	        <%for(int i=0;i<listAnnee.size();i++){
    	        	  %>["<%=listAnnee.get(i)%>", <%=a%>,"#b87333"],
    	          <%}%>
       
    	      ]);
      var view = new google.visualization.DataView(data2);
      var options = {
    	        title: "Reporting Virement",
    	        width: 600,
    	        height: 400,
    	        bar: {groupWidth: "30%"},
    	        curveType: 'function',
    	        legend: { position: "none" },
    	      };
    	      
    	      var chart = new google.visualization.ColumnChart(document.getElementById("reportVmt"));
    	      chart.draw(view, options);
    }
    </script>
    <div id="reportVmt" style="width: 900px; height: 300px; "  ></div>
      </body>
</html>