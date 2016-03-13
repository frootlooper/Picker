<div>
	<@s.form id="getZipForm" action="enterzip" cssClass="form-group">
		<@s.hidden name="postBack" value="true" />
		<@s.textfield name="enteredZip" label="Your Zip Code" labelposition="top" cssClass="form-control form-group clear" />
		<@s.textfield name="enteredRadius" label="Radius of search (in meters)" labelposition="top" cssClass="form-control form-group clear" />
		<@sj.submit value="Submit" id="getZipFormSubmit" formIds="getZipForm" targets="enterzipresult" button="true" cssClass="btn btn-default" />
	</@s.form>
</div>

<div id="enterzipresult">
<#include "enterzip-ajax.ftl">
</div>
