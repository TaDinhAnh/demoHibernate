<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h3>${msg }</h3> 
	<h3>Update INfo</h3>
	<form method="post"
		action="${pageContext.request.contextPath }/account">
		<label>Account ID:</label> <input type="text" name="idAcc" /> <br />
		Email: <input type="text" name="email"> <br />
		Phone: <input type="text" name="phone"> <br />
		<input type="submit" value="update">
	</form>
	<a href="${pageContext.request.contextPath }/transaction?action=list">list transaction</a>
</body>
</html>