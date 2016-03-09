<@s.property value="zipCode.code"/><br/>
<@s.property value="zipCode.latitude"/><br/>
<@s.property value="zipCode.longitude"/><br/>

<div>
<@s.if test="%{restaurants.isEmpty()}">
	There are no restaurants in this area.
</@s.if>
<@s.iterator value="restaurants" status="status">
	<@s.property value="name"/><br/>
</@s.iterator>
</div>