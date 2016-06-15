<form id="addItemPart_form" method="post" >
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.itemPart.serviceItemPartName")}:</th>
    		<td colspan="4">
    			 <input  class="easyui-textbox" data-options="required:true" validtype="length[0,20]" name="serviceItemPartName"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.itemPart.price")}:</th>
    		<td colspan="4">
    			 <input  class="easyui-numberbox" data-options="required:true,min:0" name = "price" />
    		</td>
    	</tr>
    	<tr>
	    	<th colspan="4">
    			<a href="#" class="easyui-linkbutton" id="addItemPart_default" data-options="toggle:true">是否默认</a>
    		</th>
    	</tr>
    	<tr>
    		<th>${message("csh.itemPart.serviceItemPartDesc")}:</th>
    		<td colspan="4">
    			 <input  class="easyui-textbox" name = "serviceItemPartDesc"   style="height:100px;width:400px"/>
    		</td>
    	</tr>
    	<tr rowspan="2">
    		<th>${message("csh.itemPart.serviceItemPartBrand")}:</th>
    		<td>
    			<a href="#" id="addItemPartVehicleBrand_selectAll" class="easyui-linkbutton" data-options="toggle:true">全选</a>
    		</td>
    		<td colspan="3">
    			<ul id="itemPart_add_vehicleBrand" class="easyui-tree vehicleBrand" checkbox="true"></ul>
    			<input type="button" class="btn btn-primary vehicleBrand" id= "addItemPartVehicleBrand_load" value="加载更多"></input>
    		</td>
    	</tr>
    </table>
	</form>
	