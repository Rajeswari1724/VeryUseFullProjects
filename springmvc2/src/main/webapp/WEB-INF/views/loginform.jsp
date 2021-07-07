<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    String emp=(String)request.getAttribute("emp");
    String errmsg =(String)request.getAttribute("errmsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <%
	if (emp != null && !emp.isEmpty()) {
	%>
	<h1><%=emp%></h1>
	<%
	}
	%>
	
	
	<%
	if (errmsg != null && !errmsg.isEmpty()) {
	%>
	<h1><%=errmsg%></h1>
	<%
	}
	%>
<fieldset>
<legend>Login form</legend>

<form action="./emplogin" method="post">
<label>ID:</label>
<input type="text" name="id" required="required"><br><br>
<label> PASSWORD: </label>
<input type="password" name="password" required="required"><br><br>
<input type="submit">

</form>

</fieldset>


</body>
</html>