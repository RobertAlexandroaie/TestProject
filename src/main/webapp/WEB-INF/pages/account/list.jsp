<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>Registered users: ${accountCount}</p>
	<c:if test="${not empty accounts}">
		<c:forEach var="account" items="${accounts}">
			<p>${account.id} - ${account.firstName} - ${account.lastName} - ${account.email} - ${account.mobilePhone} - ${account.gender} 
			- ${account.address} - ${account.city} - ${account.county} - ${account.birthDate} - ${account.newsLetterAgreement}  </p>
			<br/>
		</c:forEach>
	</c:if>
</body>
</html>