
<%@page import="net.sf.cglib.core.Local"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/templates/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/font-awesome/css/font-awesome.min.css" />
	
<link type="text/css" rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/bootstrap-responsive.min.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/auction/auctionStyle.css" />

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/auction/keywords/dynamic_list_helper.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/auction/keywords/dynamic_list_helper2.js"></script>
		<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/auction/keywords/dynamic_list_helper3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/scripts/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
<script type="text/javascript">
var selectedOption = '<c:out value="${combinedAuctionKeywords.auctionForm.category}"/>';
var selectedSubcategory = '<c:out value="${combinedAuctionKeywords.auctionForm.subcategory}"/>';
	
</script>
<title>Add auction form</title>
</head>
<body>
	<form:form method="POST" commandName="combinedAuctionKeywords"
		id="main" enctype="multipart/form-data">
		<h2 id="sign-up">Add auction</h2>
		
		Image:<input name="file" type="file" />
		<fieldset id="step-1">
			<div class="field">
				<label>Product name: <img src="../resources/images/auction/required_star.gif" alt="required-star"> </label>
				<form:input  required="required" path="auctionForm.productName" />
				<div class="errors">
					<form:errors path="auctionForm.productName" />
				</div>
			</div>
			<div class="field">
				<label>Product description: <img src="../resources/images/auction/required_star.gif" alt="required-star"> </label>
				<form:input  required="required" path="auctionForm.productDescription" />
				<div class="errors">
					<form:errors path="auctionForm.productDescription" />
				</div>
		   </div>

			<div class="field">
				<div id="keywordsListForm">
					<table>
						<thead>
							<tr>
								<td><label>Keywords <img src="../resources/images/auction/required_star.gif" alt="required-star"></label></td>
							</tr>
						</thead>
						<tbody id="keywordsListContainer">
							<c:forEach items="${allListContainer.keywordsListContainer.keywordsList}" var="Keyword"
								varStatus="i" begin="0">
								<tr class="keyword">
								   <td><form:input class="keyword-input" path="keywordsListContainer.keywordsList[${i.index}].name" id="name${i.index}" /></td>
									<!-- <td><input class="keyword-input" type="text"
										name="keywordsListContainer.keywordsList[].name" value="${Keyword.name}" /></td>
	                                 -->
									<td><div class="img removeKeyword"><i class="icon-remove"></i></div></td>

							</c:forEach>
	
							<c:if test="${allListContainer.keywordsListContainer.keywordsList.size() == 0}">
								<tr class="keyword defaultKeyword">
									<td><input class="keyword-input" type="text"
										name="keywordsListContainer.keywordsList[].name" value="" /></td>
	
									<td><div class="img removeKeyword"><i class="icon-remove"></i></div></td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<p id="add-reset">
						<a href="#" id="addKeyword">Add more keywords</a>
						<!-- <a href="?f=" id="reset-keywords">Reset List</a> -->
					</p>
					<div id="submit"></div>
				</div>
	
			</div>
		</fieldset>
		<fieldset id="step-1">
			<div class="field" id="subcategory">
				<label>Category:</label>
				<form:select path="auctionForm.category" id="select-categories">
					<c:if test="${not empty categories}">
						<c:forEach var="category" items="${categories}">
							<option value="${category.id}">${category.name}</option>
	
						</c:forEach>
					</c:if>
	
				</form:select>
				<div class="errors">
					<form:errors path="auctionForm.category" />
				</div>
			</div>
			<div class="field" id="subcategory">
				<label>Subcategory:</label>
				<c:set var="salary" scope="session" value="3" />
				<form:select path="auctionForm.subcategory" id="subcategory-select">
				</form:select>
			</div>
	   </fieldset>
		
		
		<fieldset id="step-1">
			
			<div class="field" >
				<label>Minimum bid: <img src="../resources/images/auction/required_star.gif" alt="required-star"></label>
				<form:input type="number" min="1" maxlength="18" path="auctionForm.minimumBid" required="required"/>
				<div class="errors">
					<form:errors path="auctionForm.minimumBid" />
				</div>
			</div>
			<div class="field">
				<label>Buyout:</label>
				<form:input type="number" min="1" maxlength="18" path="auctionForm.buyout" />
				<div class="errors">
					<form:errors path="auctionForm.buyout" />
				</div>
			</div>
	
			<div class="field">
				<label>Auction expiration date: <img src="../resources/images/auction/required_star.gif" alt="required-star"></label>
				<form:select path="auctionForm.day">
					<option value="1">Day</option>
					<%
						for (int i = 1; i <= 31; i++) {
									out.println("<option value=\"" + i + "\"'>" + i
											+ "</option>");
								}
					%>
				</form:select>
	
				<form:select path="auctionForm.month">
					<option value="1">Month</option>
					<form:option value="1">January</form:option>
					<form:option value="2">February</form:option>
					<form:option value="3">March</form:option>
					<form:option value="4">April</form:option>
					<form:option value="5">May</form:option>
					<form:option value="6">June</form:option>
					<form:option value="7">July</form:option>
					<form:option value="8">August</form:option>
					<form:option value="9">September</form:option>
					<form:option value="10">October</form:option>
					<form:option value="11">November</form:option>
					<form:option value="12">December</form:option>
				</form:select>
	
				<form:select path="auctionForm.year">
					<option value="2014">Year</option>
					<%
						for (int i = 2013; i <= 2020; i++) {
									out.println("<option value=\"" + i + "\"'>" + i
											+ "</option>");
								}
					%>
				</form:select>
			
			</div>
		   
			<div class="field">
				<div id="locationsListForm">
					<table>
						<thead>
							<tr>
								<label>Delivery locations</label>
							</tr>
						</thead>
						<tbody id="locationsListContainer">
							<c:forEach items="${allListContainer.locationsListContainer.locationsList}" var="Location"
								varStatus="j" begin="0">
								<tr class="location">
								  <td><form:input class="location-input"  path="locationsListContainer.locationsList[${j.index}].name" id="name${j.index}" /></td>

								  <!-- 
									<td><input class="location-input" type="text"
										name="locationsListContainer.locationsList[].name" value="${Location.name}" /></td>
	                               -->
									<td><div class="img removeLocation"><i class="icon-remove"></i></div></td>
								</tr>
							</c:forEach>
	
							<c:if test="${allListContainer.locationsListContainer.locationsList.size() == 0}">
								<tr class="location defaultLocation">
									<td><input class="location-input" type="text"
										name="locationsListContainer.locationsList[].name" value="" /></td>
	
									<td><div class="img removeLocation"><i class="icon-remove"></i></div></td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<p id="add-reset-locations">
						<a href="#" id="addLocation">Add more</a>
						<!-- <a href="?f=" id="reset-keywords">Reset List</a> -->
					</p>
					<div id="submit-location"></div>
				</div>
			</div>
			
			<label>Payment method: <img src="../resources/images/auction/required_star.gif" alt="required-star"></label>
			<div class="field" id="pay">
				<form:radiobutton path="auctionForm.paymentMethod" value="pay-pall" label="Paypal" class="radioVal"/>
				<form:radiobutton path="auctionForm.paymentMethod" value="C.O.D" label="C.O.D. (cash on delivery)" class="radioVal" checked="checked"/>
				<div class="errors">
					<form:errors path="auctionForm.paymentMethod" />
				</div>
			</div>
			<div class="field">
				<label>Bid step:</label>
				<form:input type="number" min="1" maxlength="18" path="auctionForm.step" />
				<div class="errors">
					<form:errors path="auctionForm.step" />
				</div>
			</div>
			
			<div class="field">
				<label>Promote with :</label>
				<form:input type="number" min="1" maxlength="5" size="5" path="auctionForm.promoted" /><span style="font-size: 30px; color:black;">$</span>
				<div class="errors">
					<form:errors path="auctionForm.promoted" />
				</div>
			</div>
	</fieldset>  
	
	  <fieldset> 
	              <label>Auction results: </label>
				  <form:input id="auction-results" type="text" disabled="true" path="auctionForm.end" />
				  <input type="submit" value="Add auction" class="button" />
	 </fieldset> 
		
	

	   
	</form:form>

	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/scripts/auction/categorySelect.js"></script>
</body>
</html>