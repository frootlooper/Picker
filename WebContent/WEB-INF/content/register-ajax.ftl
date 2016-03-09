<h2>Register</h2>

<@s.if test="hasActionErrors()">
   <div class="errors">
      <@s.actionerror/>
   </div>
</@s.if>

<@s.form id="registerForm" action="register" validate="true" cssClass="form-group">
	<@s.hidden name="postBack" value="true" />
	<@s.textfield name="enteredUsername" label="Username" labelposition="top" cssClass="form-control form-group clear" />
	<@s.textfield name="enteredFirstName" label="First Name" labelposition="top" cssClass="form-control form-group clear" />
	<@s.textfield name="enteredLastName" label="Last Name" labelposition="top" cssClass="form-control form-group clear" />
	<@s.password name="enteredPassword" label="Password" labelposition="top" cssClass="form-control form-group clear" />
	<@sj.submit value="Submit" id="registerFormSubmit" formIds="registerForm" targets="result" button="true" cssClass="btn btn-default" />
</@s.form>
