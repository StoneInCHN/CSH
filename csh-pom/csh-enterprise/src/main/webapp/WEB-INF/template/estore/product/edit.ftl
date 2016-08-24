<form id="editProduct_form" method="post">   
	<input type="hidden" id="editProductId" name="id" value="${product.id}"/>
	<input type="hidden" id="deleteImageIdList" name="deleteImageIdList"/>
	<input type="hidden" id="editProductImageList_form_file_input"  name="productImageListSrcs"/>
	<div id="editProductTab" data-options="tabPosition:'top',headerWidth:100" class="easyui-tabs">  
		<div title="${message("csh.product.baseInfo")}" style="padding:10px"> 
			  <table class="table table-striped"  border="0">
			  	<tr>
			  		<th >${message("csh.product.sn")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="sn" value=${product.sn} required="required" readonly="true"/>
		    		</td>
		    		<th>${message("csh.product.productCategory")}:</th>
		    		<td>
		    			<input  class="easyui-combobox" required="required" id="editProduct_productCategory" data-value="${product.productCategory.id}" name="productCategoryId"/>
		    		</td>
		    		<td rowspan="5" colspan="2">
	    			
	    			 <div title="图片上传" class="easyui-tooltip headWarp" id="editProductImageWarp">
	    				<div id="productImageUploader-edit" class="single-uploader">
						    <div  class="queueList filled">
						         <div  class="placeholder element-invisible">
						        	<div id="productImageFilePicker-edit" ></div>
						        </div>
						 
							    <div class="show-img">
						        	<p class="imgWrap img-thumbnail">
										 <img id ="productImage-edit" src="../../${product.image}" style ="width:80px;hight:80 px"/>
									</p>
							    </div>
							</div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						    </div>
						</div>
						<div class="btns">
					        <div class="uploadBtn state-pedding"></div>
					        <div id="productImageFilePicker-edit2" class="margin-left-20">选择文件</div>
					        <div class="btn btn-info savePhoto margin-left-20" style="display:none">保存图片</div>
					    </div>
	    			</div>
		    		</td>
			  	</tr>
			    <tr>
		    		
		    		<th>${message("csh.product.name")}:</th>
		    		<td>
		    			 <input  class="easyui-textbox" required="required" name="name" value="${product.name}" validtype="length[0,20]"/>
		    		</td>
		    		<th >${message("csh.product.fullName")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" required="required"  name="fullName" value="${product.fullName}" validtype="length[0,50]"/>
		    		</td>
		    	</tr>
		    	 <tr>
		    		<th >${message("csh.product.price")}:</th>
		    		<td>
		    			<input  class="easyui-numberbox" required="required" value="${product.price}" name="price" data-options="min:0,max:100000,precision:2"/>
		    		</td>
		    		<th >${message("csh.product.cost")}:</th>
		    		<td>
		    			<input  class="easyui-numberbox"  name="cost" value="${product.cost}" data-options="min:0,max:100000,precision:2"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.marketPrice")}:</th>
		    		<td>
		    			<input  class="easyui-numberbox" name="marketPrice" value="${product.marketPrice}" data-options="min:0,max:100000,precision:2"/>
		    		</td>
		    		<th >${message("csh.product.point")}:</th>
		    		<td>
		    			<input  class="easyui-numberbox" name="point" value="${product.point}" data-options="min:0"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.unit")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="unit" value="${product.unit}"/>
		    		</td>
		    		<th >${message("csh.product.weight")}:</th>
		    		<td >
		    			<input  class="easyui-numberbox" name="weight" value="${product.weight}" data-options="min:0"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.stock")}:</th>
		    		<td>
		    			<input  class="easyui-numberbox" name="stock" value="${product.stock}" data-options="min:0"/>
		    		</td>
		    		<th >${message("csh.product.stockMemo")}:</th>
		    		<td>
		    			<input  class="easyui-numberbox" name="stockMemo" value="${product.stockMemo}" data-options="min:0"/>
		    		</td >
		    		<th >${message("csh.product.stockAlertCount")}:</th>
		    		<td>
		    			<input  class="easyui-numberbox" name="stockAlertCount" value="${product.stockAlertCount}" data-options="min:0"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th>${message("csh.product.isAllowedRedPackage")}:</th>
		    		<td >
		    			 <input class="easyui-combobox" data-options="
						     valueField: 'label',
						     textField: 'value',
						     data: [{
						      label: 'true',
						      value: '${message("csh.product.isAllowedRedPackage.true")}'
						      [#if product.isAllowedRedPackage == true]
						      	,'selected':true
						      [/#if]
						     },{
						      label: 'false',
						      value: '${message("csh.product.isAllowedRedPackage.false")}'
						      [#if product.isAllowedRedPackage == false]
						      	,'selected':true
						      [/#if]
						     }],
						     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="isAllowedRedPackage" style="width:110px;" required=true/>
		    		</td>
		    		<th>${message("csh.product.redPackageMax")}:</th>
		    		<td>
		    			 <input  class="easyui-numberbox" value="${product.redPackageMax}" name="redPackageMax" id= "addRedPackageMax" data-options="min:0,precision:2"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.brand")}:</th>
		    		<td>
		    			<input class="easyui-combotbox" required="required" data-value="${product.brand.id}" id="editProduct_brand" name="brandId"/>
		    		</td >
		    		<th >${message("csh.product.memo")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="memo" value="${product.memo}"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.keyword")}:</th>
		    		<td>
		    			<input  class="easyui-textbox" name="keyword" value="${product.keyword}"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.seoKeywords")}:</th>
		    		<td colspan="6">
		    			<input  class="easyui-textbox"  name="seoKeywords" value="${product.seoKeywords}"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.seoTitle")}:</th>
		    		<td colspan="6">
		    			<input  class="easyui-textbox" name="seoTitle" value="${product.seoTitle}"/>
		    		</td >
		    	</tr>
		    	<tr>
		    		<th >${message("csh.product.seoDescription")}:</th>
		    		<td colspan="6">
		    			<input  class="easyui-textbox" name="seoDescription" value="${product.seoDescription}"/>
		    		</td >
		    	</tr>
		    </table>
	    </div>
	    <div title="${message("csh.product.detail")}" style="padding:10px">
    		<table class="table table-striped"  border="0">
    	  		<tr>
				 	<td colspan="5">
	    			 	<textarea id= "edit_product_introduction"  
	    			 	style="height:200px;width:300px" name="introduction">${product.introduction}</textarea>   
	    			</td>
    			</tr>
	    	</table>
	    </div>
	    <div title="${message("csh.product.productImageList")}" style="padding:10px;text-align: centor;">
			 <div title="图片上传" class="easyui-tooltip">
				<div id="productImageListUploader-edit" class="multiple-uploader">
				    <div  class="queueList">
			        	 <div  class="multiplePlaceholder">
				        	<div id="productImageListFilePicker-add" ></div>
				        </div>
				    </div>
				    <div class="btns">
				    	<div id="filePicker2_productImageList"></div>
				        <div class="uploadBtn state-pedding"></div>
				    </div>
				</div>
			</div>
			
			<div title="图片上传" class="easyui-tooltip"  id="editProductImageListWarp">
		    				<div id="productImageListUploader-edit" class="multiple-uploader">
							    <div  class="queueList">
							        <div class="show-img">
							        <ul class="imgWrap img-thumbnail" style="list-style-type: none;">
							        	[#list product.productImages as productImage]
							        	<li class="productImage_edit${productImage.id}"  style="float:left;height: 200px;width: 130px;">
								        	<a class="preview" style ="width:300px;hight:300 px">
											    <img class="preview" src="${productImage.source}" style ="width:110px;hight:110 px">
											</a>
											<button type="button" onclick="product_manager_tool.deleteProductImageList(${productImage.id})" 
												class="btn btn-primary" style="background-color:#00A2D4;border-color:#fff;margin: 0 15px;width: 100px;">
												删除图片</button>
										</li>
										[/#list]
									</ul>
						        	</div>
						        	 <div  class="placeholder">
							        	<div id="productImageListFilePicker-edit" ></div>
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
	    	<table id="editProductProductParameter" class="easyui-propertygrid" style="width:300px">
	    	</table>
	    </div>
	</div>
</form>


