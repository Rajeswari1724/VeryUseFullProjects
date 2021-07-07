<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<%String msg=(String)request.getAttribute("msg");
	String errmsg=(String)request.getAttribute("errmsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	if (msg != null && !msg.isEmpty()) {
	%>
	<h1 style="blue"><%=msg%></h1>
	<%
	}
	%>
	
	<%
	if (errmsg != null && !errmsg.isEmpty()) {
	%>
	<h1 style="red"><%=errmsg%></h1>
	<%
	}
	%>
	<form action="./add" method="post">
	<label>Enter the Employee id </label>
		<input type="text" name="id"><br>
	<label>Enter the Employee Name </label>
		<input type="text" name="name"><br>
	<label>Enter the Employee DOB </label>
		<input type="date" name="dob"><br>
	<label>Enter the Employee Password </label>
		<input type="password" name="password"><br>
		<br>
		<input type="submit" value="add">
	</form>
</body>
</html>