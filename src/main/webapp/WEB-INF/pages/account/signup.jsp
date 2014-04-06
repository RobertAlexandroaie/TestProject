<%@ include file="/WEB-INF/pages/generalCSSIncludes.jsp"%>
<!-- Sign Up includes -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/signUpPage/signup.css" />
<title>Sign Up</title>
</head>

<body>
	<!--[if lt IE 7]>
	            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
	        <![endif]-->
	<%@ include file="/WEB-INF/pages/header.jsp"%>
	<form:form method="POST" commandName="signUpForm" id="main">
		<h2 id="sign-up">Sign Up</h2>

		<div class="field">
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
			<br>
		</div>

		<div class="field">
			<form:label path="email" class="label">
				<spring:message code="signUpForm.email" />
			</form:label>
			<form:input path="email" class="input" />
			<div class="errors">
				<form:errors path="email" />
			</div>
			<br>
			<form:label path="emailVer" class="label">
				<spring:message code="signUpForm.emailVer" />
			</form:label>
			<form:input path="emailVer" class="input" />
			<div class="errors">
				<form:errors path="emailVer" />
			</div>
			<br>
		</div>


		<div class="field">
			<form:label path="password" class="label">
				<spring:message code="signUpForm.password" />
			</form:label>
			<form:password path="password" class="input" />
			<div class="errors">
				<form:errors path="password" />
			</div>
			<br>
			<form:label path="passwordCheck" class="label">
				<spring:message code="signUpForm.passwordVer" />
			</form:label>
			<form:password path="passwordCheck" class="input" />
			<div class="errors">
				<form:errors path="passwordCheck" />
			</div>
		</div>

		<div class="field">
			<label title="birthday" class="label"> <spring:message
					code="signUpForm.birthday" />
			</label>
			<form:select path="day">
				<option value="default">
					<spring:message code="signUpForm.day" />
				</option>
				<option value=""></option>
				<%
					for (int i = 1; i <= 31; i++) {
								out.println("<option value=\"" + i + "\"'>" + i
										+ "</option>");
							}
				%>
			</form:select>

			<form:select path="month">
				<form:option value="default">
					<spring:message code="signUpForm.month" />
				</form:option>
				<form:option value="" />
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

			<form:select path="year">
				<form:option value="default">
					<spring:message code="signUpForm.year" />
				</form:option>
				<form:option value="" />
				<%
					for (int i = 2013; i >= 1920; i--) {
								out.println("<option value=\"" + i + "\"'>" + i
										+ "</option>");
							}
				%>
			</form:select>
			<div class="errors">
				<form:errors path="year" />
			</div>
		</div>

		<div class="field">
			<label title="address" class="label"> <spring:message
					code="signUpForm.address" />
			</label>
			<form:select path="country" id="countries">
				<option value="default">
					<spring:message code="signUpForm.country" />
				</option>
				<option value=""></option>
				<form:options items="${countries}" itemValue="iso" itemLabel="name"></form:options>
			</form:select>

			<form:select path="city" id="cities">
				<option value="default">
					<spring:message code="signUpForm.city" />
				</option>
				<option value=""></option>
			</form:select>
			<form:input path="otherCity" placeholder="Other city" />
			<br>
			<form:label path="street" class="label">
				<spring:message code="signUpForm.street" />
			</form:label>
			<br>
			<form:textarea path="street" />
		</div>

		<div class="field">
			<label class="label"> <spring:message
					code="signUpForm.gender" />
			</label>

			<form:radiobutton path="gender" value="Male" label="M"
				checked="checked" class="radioVal" />
			<form:radiobutton path="gender" value="Female" label="F"
				class="radioVal" />

		</div>

		<div class="field">
			<%
				ReCaptcha c = ReCaptchaFactory.newReCaptcha(
							"6LeHMeUSAAAAAMA1w0y935nD8f1a0TmEgBl2vONS",
							"6LeHMeUSAAAAAK3GPHrmdrqt0LKMHEM1YXUwLP8X", false);
					out.print(c.createRecaptchaHtml(null, null));
			%>
			<div class="errors">
				<form:errors path="captcha" />
			</div>
		</div>

		<input type="submit" value="Sign Up" class="button">
	</form:form>
	</div>		
</div>
	<%@ include file="/WEB-INF/pages/generalJSIncludes.jsp"%>
	<!-- Sign Up includes -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/scripts/signUp/functions.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/scripts/signUp/register.js"></script>
</body>
</html>