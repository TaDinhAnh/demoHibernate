<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>${msg }</h3>
	<form method="post"
		action="${pageContext.request.contextPath }/transaction?action=listTrans">
		<label>Account ID:</label> <input type="text" name="idAcc" /> <select
			name="typeTrans">
			<option value="1">Deposit</option>
			<option value="2">WithDraw</option>
		</select> <br /> <input type="submit" value="Search">
	</form>
	<a href="${pageContext.request.contextPath }/transaction">list
		transaction</a>

	<h3>List Transaction</h3>
	<table border="1">
		<tr>
			<td>AccId</td>
			<td>TransMoney</td>
			<td>TransType</td>
			<td>DateOfTran</td>

		</tr>
		<c:forEach var="item" items="${list }">
			<tr>
				<td>${item.accId }</td>
				<td>${item.tranMoney }</td>
				<td>${item.transType }</td>
				<td>${item.dateOfTrans }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>