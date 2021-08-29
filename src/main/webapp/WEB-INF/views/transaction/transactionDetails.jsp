<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <h3>${msg }</h3> 
	<h3>Transaction</h3>
	<form method="post"
		action="${pageContext.request.contextPath }/transaction">
		<label>Account ID:</label> <input type="text" name="idAcc" /> <br />
		Money: <input type="text" name="money"> <br /> <input
			type="radio" name="transType" value="1" checked="checked">Deposit
		<input type="radio" name="transType" value="2">WithDraw <br />
		<input type="submit" value="Accept">
	</form>
	<a href="${pageContext.request.contextPath }/transaction?action=list">list transaction</a>
	<a href="${pageContext.request.contextPath }/account">update info</a>
</body>
</html>