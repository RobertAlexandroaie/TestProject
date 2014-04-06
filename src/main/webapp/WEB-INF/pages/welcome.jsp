<%@ include file="/WEB-INF/templates/include.jsp" %>
<table border="1" cellpadding="2" cellspacing="2" align="center">
	<tr>
    	<td height="30" colspan="2"><jsp:include page="/WEB-INF/templates/header.jsp"></jsp:include>
		</td>
	</tr>
	<tr>
		<td width="350">
			<form method="post" action="<c:url value='/pages/hello'/>">
				<input name="name" type="text">
				<input type="submit" value="submit"/>
				<br/>${message}<br/>
				<input type="button" id="jsonBtn" value="json" >
			</form>
		</td>
	</tr>
	<tr>
		<td height="30" colspan="2"><jsp:include page="/WEB-INF/templates/footer.jsp"></jsp:include>
		</td>
    </tr>
</table>
