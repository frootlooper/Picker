<#include "head.ftl">

<div id="pageContainer">
	<div id="loginFormContainer">
		<h2>Login</h2>

		<@s.if test="hasActionErrors()">
   			<div class="errors">
      			<@s.actionerror/>
   			</div>
		</@s.if>

		<@s.form id="loginForm" action="login" validate="true" cssClass="form-group">
			<@s.hidden name="postBack" value="true" />
			<@s.textfield name="enteredUsername" placeholder="Username" cssClass="form-control form-group clear" />
			<@s.password name="enteredPassword" placeholder="Password" cssClass="form-control form-group clear" />
			<@sj.submit value="Submit" id="loginFormSubmit" formIds="loginForm" button="true" cssClass="btn btn-default" />
		</@s.form>

		<@s.a href="/Picker/register-input" button="true">Register</@s.a>
	</div>
</div>

<#include "footer.ftl">