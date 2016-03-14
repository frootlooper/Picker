<#include "head.ftl">

<#include "navbar.ftl">

<div id="updateUserFormContainer">
	<h2>Profile Information</h2>

	<@s.if test="hasActionErrors()">
		<div class="errors">
   			<@s.actionerror/>
		</div>
	</@s.if>

	<@s.form id="updateUserForm" action="updateUser" validate="true" cssClass="form-group">
		<@s.hidden name="postBack" value="true" />
		<@s.textfield name="enteredUsername" placeholder="Username" cssClass="form-control form-group clear" />
		<@s.textfield name="enteredFirstName" placeholder="First Name" cssClass="form-control form-group clear" />
		<@s.textfield name="enteredLastName" placeholder="Last Name" cssClass="form-control form-group clear" />
		<@s.password name="enteredPassword" placeholder="Password" cssClass="form-control form-group clear" />
		<@sj.submit value="Update Information" id="updateUserFormSubmit" formIds="updateUserForm" button="true" cssClass="btn btn-default" />
	</@s.form>

</div>

<#include "footer.ftl">