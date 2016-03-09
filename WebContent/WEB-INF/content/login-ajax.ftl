
<h2>Login</h2>

<@s.if test="hasActionErrors()">
   <div class="errors">
      <@s.actionerror/>
   </div>
</@s.if>

<@s.form id="loginForm" action="login" validate="true" cssClass="form-group">
	<@s.hidden name="postBack" value="true" />
	<@s.textfield name="enteredUsername" label="Username" labelposition="top" cssClass="form-control form-group clear" />
	<@s.password name="enteredPassword" label="Password" labelposition="top" cssClass="form-control form-group clear" />
	<@sj.submit value="Submit" id="loginFormSubmit" formIds="loginForm" targets="result" button="true" cssClass="btn btn-default" />
</@s.form>

<@s.a href="/Picker/register-input" button="true">Register</@s.a>