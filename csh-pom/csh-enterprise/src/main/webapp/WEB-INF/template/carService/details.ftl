<form id="carServiceDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.carService.serviceName")}:</th>
    		<td >
    			 <input  class="easyui-textbox" value="${carService.serviceName}" disabled="disabled" id= "serviceName" name="serviceName"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.carService.serviceCategory")}:</th>
    		<td>
    			 <input  class="easyui-textbox" disabled="disabled" id= "carServiceCategoryDetail" data-value="${carService.serviceCategory.id}" name="serviceCategoryId"  data-options="required:true"/>
    		</td>
    	</tr>
    	[#if carService.serviceCategory.id ==2]
    	<tr>
    		<th>${message("csh.carService.price")}:</th>
    		<td >
    			 <input  class="easyui-textbox" disabled="disabled"  id= "price" value="${carService.price}" name="price"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.carService.promotionPrice")}:</th>
    		<td>
    			 <input  class="easyui-textbox" disabled="disabled" name="promotionPrice" value="${carService.promotionPrice}"  id= "promotionPrice" />
    		</td>
    	</tr>
    	[/#if]
    	<tr>
    		<th>${message("csh.carService.serviceStatus")}:</th>
    		<td >
    			 <input class="easyui-combobox" disabled="disabled" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ENABLED',
				      value: '${message("csh.common.enable")}'
				      [#if carService.serviceStatus == 'ENABLED']
				      	,'selected':true
				      [/#if]
				     },{
				      label: 'DISABLED',
				      value: '${message("csh.common.disable")}'
				       [#if carService.serviceStatus == 'DISABLED']
				      	,'selected':true
				      [/#if]
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="serviceStatus" style="width:110px;" required=true/>
    		</td>
    		<th>${message("csh.carService.rate")}:</th>
    		<td>
    			 <input  class="easyui-textbox" disabled="disabled" id= "rate"  name="rate" value="${carService.rate}" />
    		</td>
    	</tr>
    	<tr rowspan="4">
    		
    		<th>${message("csh.carService.imgPath")}:</th>
    		<td  colspan="4">
    			 <div title="" class="easyui-tooltip headWarp">
	    				<div id="carServiceUploader-edit" class="single-uploader">
						    <div  class="queueList filled">
						        <div  class="placeholder element-invisible">
						        	<div id="carServiceFilePicker-edit" ></div>
						        </div>
						        <div class="show-img">
						        	<p class="imgWrap img-thumbnail">
										    <img id ="carServiceImgPath-edit" src="${base}/${carService.imgPath}" style ="width:110px;hight:110 px">
									 </p>
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
    		<th>${message("csh.carService.serviceDesc")}:</th>
    		<td colspan="5">
    			 <div id= "edit_carService_desc"  
    			 style="height:400px;width:400px" name="serviceDesc">"${carService.serviceDesc}</div>   
    		</td>
    	</tr>
    </table>
</form>



