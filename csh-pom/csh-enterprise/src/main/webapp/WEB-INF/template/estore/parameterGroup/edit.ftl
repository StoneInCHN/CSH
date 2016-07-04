<form id="editParameterGroup_form" method="post">
	<input type="hidden" name="id" value="${parameterGroup.id}" />   
	 <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.parameterGroup.productCategory")}:</th>
    		<td colspan="4">
    			 <input class="easyui-combobox" id="editParameterGroup_productCategory" data-value="${parameterGroup.productCategory.id}" name="productCategoryId"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.parameterGroup.name")}:</th>
    		<td>
    			<input  class="easyui-textbox" name="name" value="${parameterGroup.name}" validtype="length[0,200]"/>
    		</td>
    		<th>${message("csh.parameterGroup.order")}:</th>
    		<td>
    			<input  class="easyui-numberbox" name="order" value="${parameterGroup.order}" validtype="length[0,20]"/>
    		</td>
    	</tr>
    	<tr>
    		<td colspan = '4' style='text-align: center;'>
    			<a href="#" id="editParameterGroup-parameterAdd" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
    		</td>
    	<tr>
    </table>
    <table class="table table-striped" id="editParameterGroup_parameterTable" border="0">
	    <tr>
	    	<th>
					&nbsp;
			</th>
    		<th>${message("csh.parameter.name")}:</th>
    		<th>${message("csh.parameter.order")}:</th>
    		<th>${message("csh.parameter.operate")}:</th>
    	</tr>
    	<input type="hidden" id="editParameterGroupParameterSize" value = "${parameterGroup.parameters?size}"/>
    	[#list parameterGroup.parameters as parameter]
    		<tr id='editParameterGroup-parameterAddTr${parameter_index}'>
    			<td>
					<input type="hidden" name="parameters[${parameter_index}].id" value="${parameter.id}" />
				</td>
    			<td>
    				<input  class="easyui-textbox" name="parameters[${parameter_index}].name" id ="editParameterGroup-parameterName${parameter_index}" value="${parameter.name}"/>
    			</td>
    			<td>
    				<input  class="easyui-textbox" name="parameters[${parameter_index}].order" id ="editParameterGroup-parameterOrder${parameter_index}" value="${parameter.order}"/>
    			</td>
    			<td>
    				<input  id ="editParameterGroup-parameterDelete${parameter_index}" type="button" value="删除" onclick="deleteParameter('editParameterGroup-parameterAddTr${parameter_index}')"/>
    			</td>
    		</tr>
    	[/#list]
    </table>
</form>


