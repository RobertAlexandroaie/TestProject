<!--Pattern HTML-->
<div id="pattern" class="pattern">
	<!--Begin Pattern HTML-->
	<div class="wrap" id="wrap">
		<a href="#menu" class="menu-link">Menu</a>
		<nav id="menu" role="navigation">
			<ul>
				<li><a href="#">Home</a></li>
				<li><a href="#">About</a></li>
				<li><a href="#">Products</a></li>
				<li><a href="#">Services</a></li>
				<li><a href="#">Contact</a></li>
				<%
					String userEmail = (String) session.getAttribute("user_name");
					if (userEmail == null) {
				%>
				<%@include file="/WEB-INF/pages/newUserMenu.jsp"%>
				<%
					} else {
				%>
				<%@include file="/WEB-INF/pages/signedInMenu.jsp"%>
				<%
					}
				%>
			</ul>
		</nav>
		<%@ include file="/WEB-INF/pages/loginPopUp.jsp"%>