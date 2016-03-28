<form id="addVehicleInsurance_form" method="post">
<input type="hidden" name="endUserId" id="addVehicleInsurancen_endUserID">
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
    			 <input  class="easyui-datebox" name="insuranceEndDate" id= "insuranceEndDate"  data-options="required:true,editable:false" />
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
			      value: '${message("csh.common.no")}',
			     }],
			     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="isLoaned" style="width:110px;" required=true/>
    		</td>
    	</tr>
    	<tr>
    		
    		<th>${message("csh.vehicleInsurancen.IDphoto")}:</th>
    		<td  >
	    		<input id = "addIDphotoFile" type="file" accept="image/*" name = "file">
	    		<input type="hidden" id ="addIDphotoFileValue" name = "IDphoto">
    		</td>
    		<td>
    			<img style="width:50px;height=50px" id="addIDphotoImg" />
    		</td>
    	</tr>
    	<tr>
    		
    		<th>${message("csh.vehicleInsurancen.drivingLicensePhoto")}:</th>
    		<td  >
    			<input id = "addDrivingLicensePhoto" type="file" accept="image/*" name = "file">
	    		<input type="hidden"  id ="addDrivingLicensePhotoValue" name = "drivingLicensePhoto">
    		</td>
    		<td>
    			<img style="width:50px;height=50px" id="addDrivingLicensePhotoImg" />
    		</td>
    	</tr>
    	<tr>
    		
    		<th>${message("csh.vehicleInsurancen.driverLicensePhoto")}:</th>
    		<td >
    			<input id = "addDriverLicensePhoto" type="file" accept="image/*" name = "file">
	    		<input type="hidden" id ="addDriverLicensePhotoValue" name = "driverLicensePhoto">
    		</td>
    		<td>
    			<img style="width:50px;height=50px" id="addDriverLicensePhotoImg" />
    		</td>
    	</tr>
    </table>
</form>
