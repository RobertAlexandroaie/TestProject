<form action="#" id="register">
	<header>		
		<h2> Sign up </h2>
	</header>
	<fieldset id="step-1">
			<legend>Step:1 Account details</legend>				
			<div class="input-prepend">
				<label for="emailsignup" class="youmail"> Your email</label>
				<span class="add-on"><i class="icon-envelope"></i></span>
				<input class="span1" type="text" placeholder="Email address">
			</div>
			<div class="input-prepend">
				<label for="passwordsignup" class="youpasswd">Your password </label>
				<span class="add-on"><i class="icon-key"></i></span>
				<input class="span1" type="password" placeholder="Password">
			</div>
			<div class="input-prepend">
				<label for="passwordsignup" class="youpasswd">Please confirm your password </label>
				<span class="add-on"><i class="icon-key"></i></span>
				<input class="span1" type="password" placeholder="Confirm password">
			</div>
	</fieldset>
	<fieldset id="step-2">
			<legend>Step:2 Personal details</legend>				
			<div class="input-prepend">
				<label for="firstnamesignup" class="youfirstname"> First Name</label>
				<span class="add-on"><i class="icon-user"></i></span>
				<input class="span2" type="text" placeholder="First name">
			</div>
			<div class="input-prepend">
				<label for="lastnamesignup" class="youlastname">Last Name </label>
				<span class="add-on"><i class="icon-user"></i></span>
				<input class="span2" type="text" placeholder="Last name">
			</div>
			<div class="input-prepend">
				<label for="birthdaysignup" class="youbirthday">Birthday </label>
				<span class="add-on"><i class="icon-calendar"></i></span>
				<input class="span2" type="date" placeholder="MM-DD-YYYY" id="birthdate">
			</div>
			<div class="input-prepend">
				<legend>Gender</legend>
				<div class="gender">
					<i class="icon-female visible-lg"></i>
					<input id="F" name=gender type=radio value="female"><label for="F">Female</label>
				</div>
				<div class="gender">
					<i class="icon-male visible-lg"></i>
					<input id="M" name=gender type=radio value="male"><label for="M">Male</label>
				</div>
			</div>
	</fieldset>
	<fieldset id="step-3">
			<legend>Step:3 Shipping and card details</legend>				
			<div class="input-prepend">
				<label for="addresssignup" class="youaddress"> Address</label>
				<span class="add-on"><i class="icon-map-marker"></i></span>
				<input class="span3" type="text" placeholder="Address">
			</div>
			<div class="input-prepend">
				<label for="streetsignup" class="youpstreet">Street </label>
				<span class="add-on"><i class="icon-road"></i></span>
				<input class="span3" type="text" placeholder="Street">
			</div>
			<div class="input-prepend">
				<legend>Card type</legend>
				<div class="card-type">
					<input id=visa type=radio>
					<label for=visa>VISA</label>
				</div>
				<div class="card-type">
					<input id=mastercard type=radio>
					<label for=mastercard>Mastercard</label>
				</div>
			</div>
	</fieldset>
	<div class="btn-div"><button type="button" class="btn btn-default navbar-btn" id="next-button" href="step-2">Next</button><div class="clear-both"></div></div>
	<div class="circle current" id="step-1-circle"><p>1</p></div>
	<div class="circle" id="step-2-circle"><p>2</p></div>
	<div class="circle" id="step-3-circle"><p>3</p></div>
	<div class="clear-both"></div>
</form>