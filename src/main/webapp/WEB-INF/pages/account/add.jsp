<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<form:form method="POST" commandName="accountForm">
    <table> 
        <tr>
            <td><h3>Registration form</h3></td>
        </tr> 
        <tr>
            <td><form:label path="firstName"><spring:message code="accountForm.firstName"/></form:label></td>
            <td><form:input path="firstName"/><form:errors path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="lastName">Last name:</form:label></td>
            <td><form:input path="lastName"/><form:errors path="lastName"/></td>
        </tr>
        <tr>
            <td><form:label path="email">e-mail:</form:label></td>
            <td><form:input path="email"/><form:errors path="email"/></td>
        </tr>
        <tr>
            <td><form:label path="mobilePhone">Mobile phone (0712123456):</form:label></td>
            <td><form:input path="mobilePhone"/><form:errors path="mobilePhone"/></td>
        </tr>
        <tr>
            <td><form:label path="gender">Gender:</form:label></td>
            <td>
                <form:radiobutton path="gender" value="M" label="M"/>
                <form:radiobutton path="gender" value="F" label="F"/>
            </td>
        </tr>
        <tr>
            <td><form:label path="address">Address:</form:label></td>
            <td><form:input path="address"/></td>
        </tr>
        <tr>
            <td><form:label path="city">City:</form:label></td>
            <td><form:input path="city"/></td>
        </tr>
        <tr>
            <td><form:label path="county">County:</form:label></td>
            <td><form:input path="county"/></td>
        </tr>
        <tr>
            <td><form:label path="birthDate">Birth date (dd-MM-yyyy):</form:label></td>
            <td><form:input path="birthDate"/><form:errors path="birthDate"/></td>
        </tr>
        <tr>
            <td><form:label path="password">Password:</form:label></td>
            <td><form:password path="password"/><form:errors path="password"/></td>
        </tr>
        <tr>
            <td><form:label path="passwordCheck">Retype password:</form:label></td>
            <td><form:password path="passwordCheck"/><form:errors path="passwordCheck"/></td>
        </tr>

        <tr>
            <td><form:label path="newsletterAgreement">Newsletter agreement:</form:label></td>
            <td><form:checkbox path="newsletterAgreement" value="true"/><form:errors path="newsletterAgreement"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="Create account"></td>
        </tr>
    </table>
</form:form>
</body>
</html>