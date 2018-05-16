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

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Table V01</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<body>
	<%
	try{
		String excelFilePath2 = "E:\\Eclipse\\jee-oxygen\\work\\PlotCac\\WebContent\\stocksCAC40.xlsx";
	    readstock reader2 = new readstock();
	    List<readstock.stock> list2 = reader2.readStocks(excelFilePath2);
	    %>
	    <div class="limiter" >
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100">
					<table>
						<thead>
							<tr class="table100-head">
								<th class="column1">Company</th>
								<th class="column2">Price</th>
								<th class="column3">Portfolio</th>
								<th class="column4"><center>Buy </center></th>
								<th class="column5"><center>Total</center></th>
							</tr>
						</thead>
						<tbody>
								<%
								for(int i=1; i<41;i++)
								{
									%><tr>
									<td class="column1"><%=list2.get(i).getName()%></td>
									<td class="column2"><%=list2.get(i).getValue()%></td>
									<td class="column3">0</td>
									<td class="column4"><center></left><input type="text" size="5" value="0"></left></td>
									<td class="column5"><center>0</center></td>

								</tr>
								<%}
								%>
								
						</tbody>
					</table>
				</div> 
			</div>
		</div>
	</div>

	    
	<%  } catch(IOException e){
		e.printStackTrace();
	}
	%>
	

	

<!--===============================================================================================-->	
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>