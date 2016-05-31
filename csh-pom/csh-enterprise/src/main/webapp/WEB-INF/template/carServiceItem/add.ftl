<form id="addCarServiceItem_form" method="post" >
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.carServiceItem.serviceName")}:</th>
    		<td>
    			 <input  class="easyui-textbox"  name="serviceItemName"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.carServiceItem.serviceDesc")}:</th>
    		<td colspan="4">
    			 <input  class="easyui-textbox" name = "serviceDesc"   style="height:100px;width:400px"/>
    		</td>
    	</tr>
    </table>
	</form>