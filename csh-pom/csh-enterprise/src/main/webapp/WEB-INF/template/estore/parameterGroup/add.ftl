<form id="addParameterGroup_form" method="post" >
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.parameterGroup.productCategory")}:</th>
    		<td colspan="4">
    			 <input class="easyui-combobox" id="addParameterGroup_productCategory" name="productCategoryId"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.parameterGroup.name")}:</th>
    		<td>
    			<input  class="easyui-textbox" name="name" validtype="length[0,200]"/>
    		</td>
    		<th>${message("csh.parameterGroup.order")}:</th>
    		<td>
    			<input  class="easyui-numberbox" name="order" validtype="length[0,20]"/>
    		</td>
    	</tr>
    	<tr>
    		<td colspan = '4' style='text-align: center;'>
    			<a href="#" id="addParameterGroup-parameterAdd" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
    		</td>
    	<tr>
    </table>
     
    <table class="table table-striped" id="addParameterGroup_parameterTable" border="0">
	    <tr>
    		<th>${message("csh.parameter.name")}:</th>
    		<th>${message("csh.parameter.order")}:</th>
    		<th>${message("csh.parameter.operate")}:</th>
    	</tr>
    </table>
</form>

