<form id="addCarServiceItem_form" method="post" >
	<input type="hidden" id="editItemPartId" name="id" value="${itemPart.id}"/>
	 <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.itemPart.serviceItemPartName")}:</th>
    		<td colspan="4">
    			 <input  class="easyui-textbox" value="${itemPart.serviceItemPartName}" name="serviceItemPartName"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.itemPart.price")}:</th>
    		<td colspan="4">
    			 <input  class="easyui-numberbox" value="${itemPart.price}" name = "price" />
    		</td>
    	</tr>
    	<tr>
	    	<th colspan="4">
    			<a href="#" class="easyui-linkbutton" id="addItemPart_default"  data-options="toggle:true [#if itemPart.isDefault],selected :true[/#if]" >是否默认</a>
    		</th>
    	</tr>
    	<tr>
    		<th>${message("csh.itemPart.serviceItemPartDesc")}:</th>
    		<td colspan="4">
    			 <input  class="easyui-textbox" name = "serviceItemPartDesc" value="${itemPart.serviceItemPartName}"  style="height:100px;width:400px"/>
    		</td>
    	</tr>
    	<tr rowspan="2">
    		<th>${message("csh.itemPart.serviceItemPartBrand")}:</th>
    		<td>
    			<a href="#" id="editItemPartVehicleBrand_selectAll" class="easyui-linkbutton" data-options="toggle:true">全选</a>
    		</td>
    		<td colspan="3">
    			<ul id="itemPart_edit_vehicleBrand" class="easyui-tree vehicleBrand" checkbox="true"></ul>
    			<input type="button" class="btn btn-primary vehicleBrand" id= "editItemPartVehicleBrand_load" value="加载更多"></input>
    		</td>
    	</tr>
    </table>
	</form>



