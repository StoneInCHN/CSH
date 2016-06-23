<form id="brandDetail_form" method="post">
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.productCategory.name")}:</th>
    		<td>
    			 <input  class="easyui-textbox" name="name" value="${productCategory.name}" data-options="required:true" validtype="length[0,200]"/>
    		</td>
    		<th >${message("csh.productCategory.seoTitle")}:</th>
    		<td>
    			<input  class="easyui-textbox" value="${productCategory.seoTitle}" name="seoTitle" validtype="length[0,200]"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.productCategory.seoKeywords")}:</th>
    		<td>
    			<input  class="easyui-textbox" value="${productCategory.seoKeywords}" name="seoKeywords" validtype="length[0,200]"/>
    		</td>
    		<th>${message("csh.productCategory.seoDescription")}:</th>
    		<td>
    			<input  class="easyui-textbox" value="${productCategory.seoDescription}" name="seoDescription" validtype="length[0,200]"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.productCategory.parent")}:</th>
			 <td colspan="4">
    			 <input class="easyui-combobox" id="productCategoryDetail_parent" data-value="${parentId}" name="parentId"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.productCategory.brand")}:</th>
			 <td colspan="4">
    			 [#list brands as brand]
    			 	<input type="checkbox" [#if productCategory.brands?seq_contains(brand)] checked="checked"[/#if] name="brandIds" style="width:30px" value="${brand.id}">${brand.name}</input>
    			 [/#list]
    		</td>
    	</tr>
    </table>
</form>



