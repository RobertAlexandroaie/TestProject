<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/profilePage/profileStyle.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>

<div class="main">
	<div class="header">
		<div class="header-top">
		
			<div id="headInfosRight">
				<ul>
					
					<li><a href="settings">Account settings</a></li>
				</ul>
			</div>
				<ul>
					<li><a href="index.html"><span>Home</span></a></li>
					<li><a href="#"><span>Back</span></a></li>
					<li><a  href='<c:url value='/logout'/>'>Logout</a></li>
					
				</ul>
				
		</div>
		

<div class="content">

<img src="${pageContext.request.contextPath}/display/" />

<div id="container">

	 <form:form method="post" action="save.html"
        commandName="fileForm" enctype="multipart/form-data">
 
  
    <table id="uploadImg">
        <tr>
            <td><input name="file" type="file" /></td>
            
        </tr>
        
        <tr>       
        	<td><input type="submit" value="Upload" /></td>
        
        </tr>
        
    </table>
    <br/>
</form:form>

<div class = "items">

<ul>
	<li><a href=""><span>Messages</span></a></li>
	<li><a href="#"><span>Wishlist</span></a></li>
	<li><a  href="#">Bids/Offers</a></li>
	<li><a  href="#">Start seeling now</a></li>
					
</ul>

</div>

</div>

</div>
</div>

</div>







</body>
</html>