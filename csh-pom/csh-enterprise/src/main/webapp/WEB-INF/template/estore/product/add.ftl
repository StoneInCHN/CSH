 <form id="addProduct_form" method="post" >
 	<input type="hidden" id="addProductImage_form_file_input" name="image">
	<div id="addProductTab" data-options="tabPosition:'top',headerWidth:100" class="easyui-tabs">  
		<div title="${message("csh.product.baseInfo")}" style="padding:10px"> 
			  <table class="table table-striped"  border="0">
			  	<tr>
			  		<th >${message("csh.product.sn")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="sn" value=${sn} required="required" readonly="true"/>
		    		</td>
		    		<th>${message("csh.product.productCategory")}:</th>
		    		<td>
		    			<input  class="easyui-combobox" required="required" id="addProduct_productCategory" name="productCategoryId"/>
		    		</td>
		    		<td rowspan="5" colspan="2">
		    			<div title="图片上传" class="easyui-tooltip headWarp" id="addProductImageWarp">
	    				<div id="productImageUploader-add" class="single-uploader">
						    <div  class="queueList">
						        <div  class="placeholder">
						        	<div id="productImageFilePicker-add" ></div>
						        </div>
						    </div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						    </div>
						</div>
	    			</div>
		    		</td>
			  	</tr>
			    <tr>
		    		
		    		<th>${message("csh.product.name")}:</th>
		    		<td>
		    			 <input  class="easyui-textbox" required="required" name="name" validtype="length[0,20]"/>
		    		</td>
		    		<th >${message("csh.product.fullName")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" required="required"  name="fullName" validtype="length[0,50]"/>
		    		</td>
		    	</tr>
		    	 <tr>
		    		<th >${message("csh.product.price")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" required="required" name="price"/>
		    		</td>
		    		<th >${message("csh.product.cost")}:</th>
		    		<td>
		    			<input  class="easyui-textbox"  name="cost"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.marketPrice")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="marketPrice"/>
		    		</td>
		    		<th >${message("csh.product.point")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="point" data-options="min:0"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.unit")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="unit"/>
		    		</td>
		    		<th >${message("csh.product.weight")}:</th>
		    		<td >
		    			<input  class="easyui-numberbox" name="weight" data-options="min:0"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.stock")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="stock" data-options="min:0"/>
		    		</td>
		    		<th >${message("csh.product.stockMemo")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="stockMemo" data-options="min:0"/>
		    		</td >
		    		<th >${message("csh.product.stockAlertCount")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="stockAlertCount" data-options="min:0"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.brand")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" required="required" id="addProduct_brand" name="brandId"/>
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
	    <div title="${message("csh.product.productImageList")}" style="padding:10px;text-align: centor;">
			 <div title="图片上传" class="easyui-tooltip" id="addProductImageListWarp">
				<div id="productImageListUploader-add" class="multiple-uploader">
				    <div  class="queueList">
			        	 <div  class="placeholder">
				        	<div id="productImageListFilePicker-add" ></div>
				        </div>
				    </div>
				    <div class="btns">
				    	<div id="filePicker2_Photos"></div>
				        <div class="uploadBtn state-pedding"></div>
				    </div>
				</div>
			</div>
	    </div>
	    <div title="${message("csh.product.productParameter")}" style="padding:10px;text-align: centor;">
	    	<table id="addProductProductParameter" class="easyui-propertygrid" style="width:300px">
	    	</table>
	    </div>
	</div>
</form>


