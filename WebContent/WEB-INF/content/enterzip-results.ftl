<#include "head.ftl">

<#include "navbar.ftl">

<@s.property value="zipCode.code"/><br/>
<@s.property value="zipCode.latitude"/><br/>
<@s.property value="zipCode.longitude"/><br/>

<div>
<@s.iterator value="restaurants" status="status">
	<@s.property value="name"/><br/>
</@s.iterator>
</div>

<#include "footer.ftl">