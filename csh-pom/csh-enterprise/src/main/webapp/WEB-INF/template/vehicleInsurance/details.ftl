<form id="repareReservationDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
	
	    <tr>
    		<th>${message("csh.vehicle.endUser")}:</th>
    		<td >
    			 <input  class="easyui-textbox"  id= "addVehicleInsurancen_endUser" value="${vehicleInsurance.endUser.userName}" disabled="disabled" data-options="required:true,editable:false"/>
    			 <a href="#" id="endUser_search_btn" class="easyui-linkbutton" onclick="searchEndUser('addVehicleInsurancen_endUser')" iconCls="icon-search" plain=true"></a>
    		</td>
    		<th>${message("csh.mobile")}:</th>
    		<td>
    			 <input  class="easyui-textbox"  id= "endUserMobileNum" value="${vehicleInsurance.endUser.mobileNum}" disabled="disabled" data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.vehicle.plate")}:</th>
    		<td >
    			 <input  class="easyui-combobox" name="vehicleId" id= "editInsuranceVehiclePlate" value="${vehicleInsurance.vehicle.plate}" disabled="disabled"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.vehicle.vehicleBrand")}:</th>
    		<td >
    			 <input  class="easyui-textbox" name="vehicleBrand" id= "vehicleBrand" value="${vehicleInsurance.vehicle.vehicleBrandDetail.vehicleLine.name}" disabled="disabled" data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>保险开始时间:</th>
    		<td >
    			 <input  class="easyui-datebox" name="insuranceStartDate" id= "insuranceStartDate" value="${vehicleInsurance.insuranceStartDate}" disabled="disabled"  data-options="required:true,editable:false"/>
    		</td>
    		<th>保险结束时间:</th>
    		<td >
    			 <input  class="easyui-datebox" name="insuranceEndDate" id= "insuranceEndDate" value="${vehicleInsurance.insuranceEndDate}" disabled="disabled" data-options="required:true,editable:false"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.vehicleInsurancen.isOwned")}:</th>
    		<td>
    			<input class="easyui-combobox" disabled="disabled" data-options="
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
    			<input class="easyui-combobox" disabled="disabled" data-options="
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
    	<tr rowspan="4">
    		
    		<th>${message("csh.vehicleInsurancen.IDphoto")}:</th>
    		<td  colspan="4">
    			 <img  style="width:50px;height=50px" src="${base}/${vehicleInsurance.IDphoto}"/>
    		</td>
    	</tr>
    	<tr rowspan="4">
    		
    		<th>${message("csh.vehicleInsurancen.drivingLicensePhoto")}:</th>
    		<td  colspan="4">
    			<img style="width:50px;height=50px" src="${base}/${vehicleInsurance.drivingLicensePhoto}"/>
    		</td>
    	</tr>
    	<tr rowspan="4">
    		
    		<th>${message("csh.vehicleInsurancen.driverLicensePhoto")}:</th>
    		<td  colspan="4">
    			<img style="width:50px;height=50px" src="${base}/${vehicleInsurance.driverLicensePhoto}"/>
    		</td>
    	</tr>
    </table>
</form>



