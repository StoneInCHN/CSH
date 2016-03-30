<form id="editVehicleInsurancen_form" method="post">
	<input type="hidden" name="endUserId" id="editVehicleInsurancen_endUserID" value="${vehicleInsurance.endUser.id}">
	<input type="hidden" name="id" id="id" value="${vehicleInsurance.id}">
	<input type="hidden" id="vehicleListMap" value= '${vehicleListMap}'/>   
	<table class="table table-striped"  border="0">
	
	    <tr>
    		<th>${message("csh.vehicle.endUser")}:</th>
    		<td >
    			 <input  class="easyui-textbox"  id= "addVehicleInsurancen_endUser" value="${vehicleInsurance.endUser.userName}"  data-options="required:true,editable:false"/>
    			 <a href="#" id="endUser_search_btn" class="easyui-linkbutton" onclick="searchEndUser('addVehicleInsurancen_endUser')" iconCls="icon-search" plain=true"></a>
    		</td>
    		<th>${message("csh.mobile")}:</th>
    		<td>
    			 <input  class="easyui-textbox"  id= "endUserMobileNum" value="${vehicleInsurance.endUser.mobileNum}" data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.vehicle.plate")}:</th>
    		<td >
    			 <input  class="easyui-combobox" name="vehicleId" id= "editInsuranceVehiclePlate" data-value="${vehicleInsurance.vehicle.id}"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.vehicle.vehicleBrand")}:</th>
    		<td >
    			 <input  class="easyui-textbox" name="vehicleBrand" id= "vehicleBrand" value="${vehicleInsurance.vehicle.vehicleBrandDetail.vehicleLine.name}" data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>保险开始时间:</th>
    		<td >
    			 <input  class="easyui-datebox" name="insuranceStartDate" id= "insuranceStartDate" value="${vehicleInsurance.insuranceStartDate}"  data-options="required:true,editable:false"/>
    		</td>
    		<th>保险结束时间:</th>
    		<td >
    			 <input  class="easyui-datebox" name="insuranceEndDate" id= "insuranceEndDate" value="${vehicleInsurance.insuranceEndDate}" data-options="required:true,editable:false"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.vehicleInsurancen.isOwned")}:</th>
    		<td>
    			<input class="easyui-combobox" data-options="
			     valueField: 'label',
			     textField: 'value',
			     data: [{
			      label: 'true',
			      value: '${message("csh.common.yes")}'
			      [#if vehicleInsurance.isOwned == true]
			       , 'selected':true
			      [/#if] 
			     },{
			      label: 'false',
			      value: '${message("csh.common.no")}'
			      [#if vehicleInsurance.isOwned == false]
			       , 'selected':true
			      [/#if]
			     }],
			     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="isOwned" style="width:110px;" required=true/>
    		</td>
    		<th>${message("csh.vehicleInsurancen.isLoaned")}:</th>
    		<td>
    			<input class="easyui-combobox" data-options="
			     valueField: 'label',
			     textField: 'value',
			     data: [{
			      label: 'true',
			      value: '${message("csh.common.yes")}'
			      [#if vehicleInsurance.isLoaned == true]
			       , 'selected':true 
			      [/#if]
			     },{
			      label: 'false',
			      value: '${message("csh.common.no")}'
			      [#if vehicleInsurance.isLoaned == false]
			       , 'selected':true 
			      [/#if]
			     }],
			     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="isLoaned" style="width:110px;" required=true/>
    		</td>
    	</tr>
    	<tr>
    		
    		<th>${message("csh.vehicleInsurancen.IDphoto")}:</th>
    		<td  >
    			<input id = "editIDphotoFile" type="file" accept="image/*" name = "file">
	    		<input type="hidden" id ="editIDphotoFileValue" value="${vehicleInsurance.IDphoto}" name = "IDphoto">
    		</td>
    		<td>
    			<img style="width:50px;height=50px" id="editIDphotoImg" src="${base}/${vehicleInsurance.IDphoto}"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.vehicleInsurancen.drivingLicensePhoto")}:</th>
    		<td  >
    			<input id = "editDrivingLicensePhoto" type="file" accept="image/*" name = "file">
	    		<input type="hidden"  id ="editDrivingLicensePhotoValue" value="${vehicleInsurance.drivingLicensePhoto}" name = "drivingLicensePhoto">
    		</td>
    		<td>
    			<img style="width:50px;height=50px" id="editDrivingLicensePhotoImg" src="${base}/${vehicleInsurance.drivingLicensePhoto}"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.vehicleInsurancen.driverLicensePhoto")}:</th>
    		<td >
    			<input id = "editDriverLicensePhoto" type="file" accept="image/*" name = "file">
	    		<input type="hidden" id ="editDriverLicensePhotoValue" value="${vehicleInsurance.driverLicensePhoto}" name = "driverLicensePhoto">
    		</td>
    		<td>
    			<img style="width:50px;height=50px" id="editDriverLicensePhotoImg" src="${base}/${vehicleInsurance.driverLicensePhoto}"/>
    		</td>
    	</tr>
    </table>
</form>



