<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br>
<form method="get" action="./rest/login" >
	<input type="text" name="login"/><br/>
	<input type="password" name="password"/>
	<input type="submit">
</form>
<a href="./rest/city">Cities</a>
<br/>
<a href="./rest/team">Teams</a>
<br>
<a href="./DbTestServlet">DbPoolTest</a>
</body>
</html>