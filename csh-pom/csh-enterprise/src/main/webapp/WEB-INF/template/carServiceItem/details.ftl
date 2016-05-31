<form id="addCarServiceItem_form" method="post" >
	<input type="hidden" name="id" value="${carServiceItem.id}"/>
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.carServiceItem.serviceName")}:</th>
    		<td>
    			 <input  class="easyui-textbox" disabled="disabled" value="${carServiceItem.serviceItemName}" name="serviceItemName"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.carServiceItem.serviceDesc")}:</th>
    		<td colspan="4">
    			 <input  class="easyui-textbox" disabled="disabled" name = "serviceDesc"  value="${carServiceItem.serviceDesc}" style="height:100px;width:400px"/>
    		</td>
    	</tr>
    </table>
	</form>