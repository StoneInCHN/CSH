<form id="repareReservationDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.vehicle.endUser")}:</th>
    		<td >
    			 <input  class="easyui-textbox"  id= "addVehicleInsurancen_endUser"  data-options="required:true,editable:false"/>
    			 <a href="#" id="endUser_search_btn" class="easyui-linkbutton" onclick="searchEndUser('addVehicleInsurancen_endUser')" iconCls="icon-search" plain=true"></a>
    		</td>
    		<th>${message("csh.mobile")}:</th>
    		<td>
    			 <input  class="easyui-textbox"  id= "endUserMobileNum"  data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.vehicle.plate")}:</th>
    		<td >
    			 <input  class="easyui-combobox" name="vehicleId" id= "vehiclePlate"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.vehicle.vehicleBrand")}:</th>
    		<td >
    			 <input  class="easyui-textbox" name="vehicleBrand" id= "vehicleBrand"  data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>保险开始时间:</th>
    		<td >
    			 <input  class="easyui-datebox" name="insuranceStartDate" id= "insuranceStartDate"  data-options="required:true,editable:false"/>
    		</td>
    		<th>保险结束时间:</th>
    		<td >
    			 <input  class="easyui-datebox" name="insuranceEndDate" id= "insuranceEndDate"  data-options="required:true,editable:false"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.vehicleInsurancen.isOwned")}:</th>
    		<td>
    			<input class="easyui-combobox" data-options="
			     valueField: 'label',
			     textField: 'value',
			     data: [{
			      label: true,
			      value: '${message("csh.common.yes")}'
			     },{
			      label: false,
			      value: '${message("csh.common.no")}'
			     }],
			     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="isOwned" style="width:110px;" required=true/>
    		</td>
    		<th>${message("csh.vehicleInsurancen.isLoaned")}:</th>
    		<td>
    			<input class="easyui-combobox" data-options="
			     valueField: 'label',
			     textField: 'value',
			     data: [{
			      label: true,
			      value: '${message("csh.common.yes")}'
			     },{
			      label: false,
			      value: '${message("csh.common.no")}'
			     }],
			     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="isLoaned" style="width:110px;" required=true/>
    		</td>
    	</tr>
    	<tr rowspan="4">
    		
    		<th>${message("csh.vehicleInsurancen.IDphoto")}:</th>
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
    	<tr rowspan="4">
    		
    		<th>${message("csh.vehicleInsurancen.drivingLicensePhoto")}:</th>
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
    	<tr rowspan="4">
    		
    		<th>${message("csh.vehicleInsurancen.driverLicensePhoto")}:</th>
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
    </table>
</form>



