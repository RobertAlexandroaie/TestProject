<div id="myModal" class="reveal-modal">
	<h2 id="login">Login</h2>
	<form:form method="POST" commandName="loginForm" id="login-form">
		<div id="username">
			<form:label path="email" id="userlabel">E-mail</form:label>
			<form:input path="email" id="usernameinpt" required="required" />
		</div>
		<div id="password">
			<form:label path="password" id="passlabel">Password</form:label>
			<form:password path="password" id="passwordinpt" required="required" />
		</div>

		<form:errors path="incorrect" />
		<div id="login-signup">
			<div class="btn-div">
				<input type="submit">Login</button>
			</div>
			<div>
				or <a href="account/signup">Sign up</a>
			</div>
		</div>
		<div class="clear-both"></div>
		<a id="forgotpass" href="#">Forgot your password?</a>
	</form:form>
</div>