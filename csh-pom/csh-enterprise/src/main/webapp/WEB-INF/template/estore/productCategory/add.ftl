<form id="addProductCategory_form" method="post" >
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.productCategory.name")}:</th>
    		<td>
    			 <input  class="easyui-textbox" name="name"  data-options="required:true" validtype="length[0,200]"/>
    		</td>
    		<th >${message("csh.productCategory.seoTitle")}:</th>
    		<td>
    			<input  class="easyui-textbox" name="seoTitle" validtype="length[0,200]"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.productCategory.seoKeywords")}:</th>
    		<td>
    			<input  class="easyui-textbox" name="seoKeywords" validtype="length[0,200]"/>
    		</td>
    		<th>${message("csh.productCategory.seoDescription")}:</th>
    		<td>
    			<input  class="easyui-textbox" name="seoDescription" validtype="length[0,200]"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.productCategory.parent")}:</th>
			 <td colspan="4">
    			 <input class="easyui-combobox" id="addProductCategory_parent" name="parentId"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.productCategory.brand")}:</th>
			 <td colspan="4">
    			 [#list brands as brand]
    			 	<input type="checkbox" name="brandIds" style="width:30px" value="${brand.id}">${brand.name}</input>
    			 [/#list]
    		</td>
    	</tr>
    	<tr >
    		<th>${message("csh.productCategory.parameter")}:</th>
    		<td >
	    			<table class="easyui-datagrid" id="productCategoryAdd-parameterGroup" style="float:left;width:80%">   
					   <div id="productCategoryAdd-parameterGroup-tb" style="height:auto">
							<a href="javascript:void(0)"  title="添加" id="productCategoryAdd-parameterGroup-tb-append" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append('productCategoryAdd-parameterGroup')">添加</a>
							<a href="javascript:void(0)" title="修改" id="productCategoryAdd-parameterGroup-tb-edit" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="edit('productCategoryAdd-parameterGroup')">修改</a>
							<a href="javascript:void(0)" title="删除" id="productCategoryAdd-parameterGroup-tb-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit('productCategoryAdd-parameterGroup')" style="display: none;"></a>
							<a href="javascript:void(0)" title="确认" id="productCategoryAdd-parameterGroup-tb-save"  class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept('productCategoryAdd-parameterGroup')" style="display: none;"></a>
							<a href="javascript:void(0)" title="撤销" id="productCategoryAdd-parameterGroup-tb-repeal"  class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject('productCategoryAdd-parameterGroup')" style="display: none;"></a>
						</div>
					</table>
			</td>
			<td></td>
			<td >
				<table id="productCategoryAdd-parameter">
					<div id="productCategoryAdd-parameter-tb" style="height:auto">
						<a href="javascript:void(0)"  title="添加" id="productCategoryAdd-parameter-tb-append" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append('productCategoryAdd-parameter')"></a>
						<a href="javascript:void(0)" title="修改" id="productCategoryAdd-parameter-tb-edit" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="edit('productCategoryAdd-parameter')"">修改</a>
						<a href="javascript:void(0)" title="删除" id="productCategoryAdd-parameter-tb-remove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit('productCategoryAdd-parameter')"></a>
						<a href="javascript:void(0)" title="保存" id="productCategoryAdd-parameter-tb-save"  class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept('productCategoryAdd-parameter')"></a>
						<a href="javascript:void(0)" title="撤销" id="productCategoryAdd-parameter-tb-repeal"  class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject('productCategoryAdd-parameter')"></a>
					</div>
				</table>   
    		<td>
    	</tr>
    </table>
</form>

