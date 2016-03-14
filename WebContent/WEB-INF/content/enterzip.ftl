<div id="enterZipContainer" class="center">
	<div>
		<@s.form id="getZipForm" action="enterzip" cssClass="form-group">
			<@s.hidden name="postBack" value="true" />
			<@s.textfield name="enteredZip" placeholder="Zip Code" cssClass="form-control form-group clear" />
			<@s.textfield name="enteredRadius" placeholder="Radius of search" cssClass="form-control form-group clear" />
			<@sj.submit value="Search" id="getZipFormSubmit" formIds="getZipForm" button="true" cssClass="btn btn-default" />
		</@s.form>
	</div>

</div>