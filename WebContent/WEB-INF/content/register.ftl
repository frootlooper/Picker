<#include "head.ftl">

<div id="pageContainer" class="center">
	<div id="registerFormContainer">
		<h2>Register</h2>

		<@s.if test="hasActionErrors()">
   			<div class="errors">
      			<@s.actionerror/>
   			</div>
		</@s.if>

		<@s.form id="registerForm" action="register" validate="true" cssClass="form-group">
			<@s.hidden name="postBack" value="true" />
			<@s.textfield name="enteredUsername" placeholder="Username" cssClass="form-control form-group clear" />
			<@s.textfield name="enteredFirstName" placeholder="First Name" cssClass="form-control form-group clear" />
			<@s.textfield name="enteredLastName" placeholder="Last Name" cssClass="form-control form-group clear" />
			<@s.password name="enteredPassword" placeholder="Password" cssClass="form-control form-group clear" />
			<@sj.submit value="Register" id="registerFormSubmit" formIds="registerForm" button="true" cssClass="btn btn-default" />
		</@s.form>

		<@s.a href="/Picker/login-input">Return to login</@s.a>
	</div>
</div>

<#include "footer.ftl">