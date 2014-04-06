<div id="container">

	<img src="${pageContext.request.contextPath}/download" height="130" width="100" />
	
	<a href="${pageContext.request.contextPath}/download/${document.id}.html"><img 
				src="${pageContext.request.contextPath}/img/save_icon.gif" border="0" 
				title="Download this document"/></a> 

	<form:form method="post" action="save.html" commandName="fileForm"
		enctype="multipart/form-data">
		
		
		<a href="${pageContext.request.contextPath}/download.html"><img 
				src="${pageContext.request.contextPath}/img/save_icon.gif" border="0" 
				title="Download this document"/></a>

		<p>Select files to upload. Press Add button to add more file
			inputs.</p>

		<input id="addFile" type="button" value="Add File" />
		<table id="fileTable">
			<tr>
				<td><input name="file" type="file" /></td>

			</tr>

		</table>
		<br />
		<input type="submit" value="Upload" />
	</form:form>

	<p>${fileName}</p>
	<p>${imagePath}</p>

	<h4>This is my profile.</h4>

</div>