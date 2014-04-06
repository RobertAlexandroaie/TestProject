<form:form method="POST" commandName="combinedAuctionKeywords" id="main">
	<h2 id="sign-up">Add auction</h2>
	<fieldset id="step-1">
		<div class="field">
			<label>Product name:</label>
			<form:input required="required" path="auctionForm.productName" />
			<div class="errors">
				<form:errors path="auctionForm.productName" />
			</div>
		</div>
		<div class="field">
			<label>Product description:</label>
			<form:input required="required" path="auctionForm.productDescription" />
			<div class="errors">
				<form:errors path="auctionForm.productDescription" />
			</div>
		</div>

		<div class="field">
			<div id="keywordsListForm">
				<table>
					<thead>
						<tr>
							<td><label>Keyword</label></td>
						</tr>
					</thead>
					<tbody id="keywordsListContainer">
						<c:forEach items="${keysListContainer.keywordsList}" var="Keyword"
							varStatus="i" begin="0">
							<tr class="keyword">
								<td><input class="keyword-input" type="text"
									name="keywordsList[].name" value="${Keyword.name}" /></td>
								<td><div class="img removeKeyword">
										<i class="icon-remove"></i>
									</div></td>
							</tr>
						</c:forEach>

						<c:if test="${keysListContainer.keywordsList.size() == 0}">
							<tr class="keyword defaultKeyword">
								<td><input class="keyword-input" type="text"
									name="keywordsList[].name" value="" /></td>

								<td><div class="img removeKeyword">
										<i class="icon-remove"></i>
									</div></td>
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
		<div class="field">
			<label>Image:</label>
			<div id="images-field">
				<input type="text" id="image1" />
			</div>
			<div class="errors">
				<form:errors path="auctionForm.images" />
			</div>
		</div>
		<div class="field" id="subcategory">
			<label>Minimum bid:</label>
			<form:input type="number" min="1" maxlength="18"
				path="auctionForm.minimumBid" required="required" />
			<div class="errors">
				<form:errors path="auctionForm.minimumBid" />
			</div>
		</div>
		<div class="field" id="subcategory">
			<label>Buyout:</label>
			<form:input type="number" min="1" maxlength="18"
				path="auctionForm.buyout" />
			<div class="errors">
				<form:errors path="auctionForm.buyout" />
			</div>
		</div>

		<div class="field">
			<label>Auction expiration date:</label>
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
			<div class="errors">
				<form:errors path="auctionForm.year" />
			</div>
		</div>

		<div class="field">
			<label>Location restrictions:</label>
			<form:input path="auctionForm.restrictions" />
			<div class="errors">
				<form:errors path="auctionForm.restrictions" />
			</div>
		</div>
		<label>Payment method:</label>
		<div class="field" id="pay">
			<form:radiobutton path="auctionForm.paymentMethod" value="pay-pall"
				label="Paypal" class="radioVal" />
			<form:radiobutton path="auctionForm.paymentMethod" value="C.O.D"
				label="C.O.D. (cash on delivery)" class="radioVal" />
			<div class="errors">
				<form:errors path="auctionForm.paymentMethod" />
			</div>
		</div>
		<div class="field">
			<label>Bid step:</label>
			<form:input type="number" min="1" maxlength="18"
				path="auctionForm.step" />
			<div class="errors">
				<form:errors path="auctionForm.step" />
			</div>
		</div>



		<input type="submit" value="Add auction" class="button" />
	</fieldset>
</form:form>