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
    </table>
</form>

