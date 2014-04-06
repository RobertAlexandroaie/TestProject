<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/profilePage/profileStyle.css" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Settings</title>
</head>
<body>

	<div class="main">
	<div class="header">
		<div class="header-top">
		
			<div id="headInfosRight">
				<ul>					
					<li><a href="">Home</a></li>
					<li><a href="">Back</a></li>
				</ul>
			</div>
			
			
			<div id="headInfosLeft">
			<ul>
					<li>Account Info</li>
					<li><a href="#"><span>Preferences</span></a></li>
			</ul>
			</div>		
			
			</div>
			
			</div>
			
			<div class="content">
			
			<form:form method="POST" commandName="signUpForm">
    			<form:label path="firstName" class="label">
					<spring:message code="signUpForm.firstName" />
				</form:label>
				<form:input path="firstName" class="input" />

			<div class="errors">
				<form:errors path="firstName" />
			</div>

			<br>
				<form:label path="lastName" class="label">
					<spring:message code="signUpForm.lastName" />
				</form:label>
				<form:input path="lastName" class="input" />

				<div class="errors">
					<form:errors path="lastName" />
				</div>
		</form:form>
			
			
			</div>
			
			</div>
			
			
		

</body>
</html>