<form id="addCarService_form" method="post" >
	<input type="hidden" id="addCarService_form_file_input" name="imgPath">  
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.carService.serviceCategory")}:</th>
    		<td>
    			 <input  class="easyui-combobox" id= "addCarServiceCategory" name="serviceCategoryId"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.carService.serviceName")}:</th>
    		<td>
    			 <input  class="easyui-textbox" data-options="required:true" validtype="length[0,20]"  id= "addServiceName" name="serviceName"/>
    		</td>
    	</tr>
    	<tr class='car-service'>
    		<th>${message("csh.carService.price")}:</th>
    		<td >
    			 <input  class="easyui-numberbox"  id= "addCarServicePrice" name="price" validType="moreThan['addCarServicePromotionPrice','优惠价']"  data-options="required:true,min:-1,precision:2"/>
    		</td>
    		<th>${message("csh.carService.promotionPrice")}:</th>
    		<td>
    			 <input  class="easyui-numberbox" name="promotionPrice" id= "addCarServicePromotionPrice" validType="lessThan['addCarServicePrice','原价']" data-options="required:true,min:-1,precision:2"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.carService.serviceStatus")}:</th>
    		<td >
    			 <input class="easyui-combobox"  data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ENABLED',
				      value: '${message("csh.common.enable")}'
				     },{
				      label: 'DISABLED',
				      value: '${message("csh.common.disable")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="serviceStatus" style="width:110px;" required=true/>
    		</td>
    		
    	</tr>
    	<tr>
    		<th>${message("csh.carService.isAllowedRedPackage")}:</th>
    		<td >
    			 <input class="easyui-combobox" id="addCarServiceIsAllowedRedPackage" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'true',
				      value: '${message("csh.carService.isAllowedRedPackage.true")}'
				     },{
				      label: 'false',
				      value: '${message("csh.carService.isAllowedRedPackage.false")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="isAllowedRedPackage" style="width:110px;" required=true/>
    		</td>
    		<th>${message("csh.carService.redPackageMax")}:</th>
    		<td>
    			 <input  class="easyui-numberbox" name="redPackageMax" disabled="disabled" id= "addCarServiceRedPackageMax"  data-options="min:0,precision:2"/>
    		</td>
    	</tr>
    	<tr rowspan="4">
    		
    		<th>${message("csh.carService.imgPath")}:</th>
    		<td  colspan="4">
    			 <div title="图片上传" class="easyui-tooltip headWarp">
	    				<div id="carServiceUploader-add" class="single-uploader">
						    <div  class="queueList">
						        <div  class="placeholder">
						        	<div id="carServiceFilePicker-add" ></div>
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
    			 <textarea id= "add_carService_desc"  
    			 style="height:400px;width:400px" name="serviceDesc"></textarea>   
    		</td>
    	</tr>
    </table>
	</form>