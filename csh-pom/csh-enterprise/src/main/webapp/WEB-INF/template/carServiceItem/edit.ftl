<form id="editCarServiceItem_form" method="post" >
	<input type="hidden" name="id" value="${carServiceItem.id}"/>
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.carServiceItem.serviceName")}:</th>
    		<td>
    			 <input  class="easyui-textbox" data-options="required:true" validtype="length[0,20]" value="${carServiceItem.serviceItemName}" name="serviceItemName"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.carServiceItem.serviceDesc")}:</th>
    		<td colspan="4">
    			 <input  class="easyui-textbox" name = "serviceDesc"  value="${carServiceItem.serviceDesc}" style="height:100px;width:400px"/>
    		</td>
    	</tr>
    </table>
	</form>



