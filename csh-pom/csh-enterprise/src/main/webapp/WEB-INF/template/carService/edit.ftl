<form id="editCarService_form" method="post">   
		<input type="hidden" id="editCarServiceId" name="id" value="${carService.id}">  
	  <table class="table table-striped"  border="0">
	    <tr>
    		
    		<th >${message("csh.carService.serviceCategory")}:</th>
    		<td>
    			 <input  class="easyui-textbox" id= "editCarServiceCategory" data-value="${carService.serviceCategory.id}" name="serviceCategoryId"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.carService.serviceName")}:</th>
    		<td>
    			 <input  class="easyui-textbox"  data-options="required:true" validtype="length[0,20]" value="${carService.serviceName}" id= "editServiceName" name="serviceName"  data-options="required:true"/>
    		</td>
    	</tr>
    	<tr class='car-service'>
    		<th>${message("csh.carService.price")}:</th>
    		<td >
    			 <input  class="easyui-numberbox"  id= "editCarServicePrice" value="${carService.price}" name="price"  data-options="required:true,min:0,precision:2"/>
    		</td>
    		<th>${message("csh.carService.promotionPrice")}:</th>
    		<td>
    			 <input  class="easyui-numberbox" name="promotionPrice" value="${carService.promotionPrice}"  id= "editCarServicePromotionPrice" data-options="required:true,min:0,precision:2"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.carService.serviceStatus")}:</th>
    		<td >
    			 <input class="easyui-combobox" data-options="
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
    	</tr>
    	<tr>
    		<th>${message("csh.carService.isAllowedRedPackage")}:</th>
    		<td >
    			 <input class="easyui-combobox" id="editCarServiceIsAllowedRedPackage" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'true',
				      value: '${message("csh.carService.isAllowedRedPackage.true")}'
				      [#if carService.isAllowedRedPackage == true]
				      	,'selected':true
				      [/#if]
				     },{
				      label: 'false',
				      value: '${message("csh.carService.isAllowedRedPackage.false")}'
				      [#if carService.isAllowedRedPackage == false]
				      	,'selected':true
				      [/#if]
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="isAllowedRedPackage" style="width:110px;" required=true/>
    		</td>
    		<th>${message("csh.carService.redPackageMax")}:</th>
    		<td>
    			 <input  class="easyui-numberbox" value="${carService.redPackageMax}" name="redPackageMax" id= "editCarServiceRedPackageMax" data-options="min:0,precision:2"/>
    		</td>
    	</tr>
    	<tr rowspan="4">
    		
    		<th>${message("csh.carService.imgPath")}:</th>
    		<td  colspan="4">
    			 <div title="图片上传" class="easyui-tooltip headWarp">
	    				<div id="carServiceUploader-edit" class="single-uploader">
						    <div  class="queueList filled">
						        <div  class="placeholder element-invisible">
						        	<div id="carServiceFilePicker-edit" ></div>
						        </div>
						        <div class="show-img">
						        	<p class="imgWrap img-thumbnail">
										    <img id ="carServiceImgPath-edit" src="${carService.imgPath}" style ="width:110px;hight:110 px">
									 </p>
						        </div>
						    </div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						    </div>
						</div>
						<div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						        <div id="carServiceFilePicker-edit2" class="margin-left-40">选择文件</div>
						        <div class="btn btn-info savePhoto margin-left-40" style="display:none">保存头像</div>
						    </div>
	    			</div>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.carService.serviceDesc")}:</th>
    		<td colspan="5">
    			 <textarea id= "edit_carService_desc"  
    			 style="height:400px;width:400px" name="serviceDesc">${carService.serviceDesc}</textarea>   
    		</td>
    	</tr>
    </table>
</form>



