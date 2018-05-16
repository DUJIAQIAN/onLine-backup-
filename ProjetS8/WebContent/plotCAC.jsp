<%@page import="cacRead.*" %>
<%@page import="java.util.*"%>
<%@page import="cacRead.ReadCAC.*" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>CAC</title>
	<%String excelFilePath1 = "C:\\Users\\horizon\\eclipse-workspace\\ProjetS8\\WebContent\\PX11.xlsx";
    ReadCAC reader1 = new ReadCAC();
    List<market> list1 = reader1.readMarket(excelFilePath1); 
    %>
	<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      
      
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Cotation'],
          <%for(int i=0;i<list1.size();i++){
        	  %>[<%=i%>,<%=list1.get(i).getValue()%>],
          <%}%>
        ]);

        var options = {
          title: 'Cours du CAC40',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
</head>
<body>
	<div id="curve_chart" style="width: 900px; height: 500px"></div>
</body>
</html>