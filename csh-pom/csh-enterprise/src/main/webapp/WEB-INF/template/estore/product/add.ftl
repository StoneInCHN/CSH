 <form id="addProduct_form" method="post" >
	<div id="addProductTab" data-options="tabPosition:'top',headerWidth:100" class="easyui-tabs">  
		<div title="${message("csh.product.baseInfo")}" style="padding:10px"> 
			  <table class="table table-striped"  border="0">
			  	<tr>
			  		<th >${message("csh.product.sn")}:</th>
		    		<td colspan="6">
		    			<input  class="easyui-textbox" name="sn" />
		    		</td>
			  	</tr>
			    <tr>
		    		<th >${message("csh.product.productCategory")}:</th>
		    		<td>
		    			<input  class="easyui-combobox" id="addProduct_productCategory" name="productCategoryId"/>
		    		</td>
		    		<th>${message("csh.product.name")}:</th>
		    		<td>
		    			 <input  class="easyui-textbox" name="name" validtype="length[0,20]"/>
		    		</td>
		    		<th >${message("csh.product.fullName")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="fullName" validtype="length[0,50]"/>
		    		</td>
		    	</tr>
		    	 <tr>
		    		<th >${message("csh.product.price")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="price"/>
		    		</td>
		    		<th >${message("csh.product.cost")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="cost"/>
		    		</td>
		    		<th >${message("csh.product.marketPrice")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="marketPrice"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.image")}:</th>
		    		<td colspan="6">
		    			<input  class="easyui-textbox" name="image"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.unit")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="unit"/>
		    		</td>
		    		<th >${message("csh.product.weight")}:</th>
		    		<td >
		    			<input  class="easyui-textbox" name="weight"/>
		    		</td >
		    		<th >${message("csh.product.point")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="point"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.stock")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="stock"/>
		    		</td>
		    		<th >${message("csh.product.stockMemo")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="stockMemo"/>
		    		</td >
		    		<th >${message("csh.product.stockAlertCount")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="stockAlertCount"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.brand")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" id="addProduct_brand" name="brandId"/>
		    		</td >
		    		<th >${message("csh.product.memo")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="memo"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.keyword")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="keyword"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.seoKeywords")}:</th>
		    		<td colspan="6">
		    			<input  class="easyui-textbox"  name="seoKeywords"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.seoTitle")}:</th>
		    		<td colspan="6">
		    			<input  class="easyui-textbox" name="seoTitle"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.seoDescription")}:</th>
		    		<td colspan="6">
		    			<input  class="easyui-textbox" name="seoDescription"/>
		    		</td >
		    	</tr>
		    </table>
	    </div>
	    <div title="${message("csh.product.detail")}" style="padding:10px">
    		<table class="table table-striped"  border="0">
    	  		<tr>
				 	<td colspan="5">
	    			 	<textarea id= "add_product_introduction"  
	    			 	style="height:200px;width:300px" name="introduction"></textarea>   
	    			</td>
    			</tr>
	    	</table>
	    </div>
	    <div title="${message("csh.product.productParameter")}" style="padding:10px;text-align: centor;">
	    	<table id="addProductProductParameter" class="easyui-propertygrid" style="width:300px">
	    	</table>
	    </div>
	</div>
</form>


