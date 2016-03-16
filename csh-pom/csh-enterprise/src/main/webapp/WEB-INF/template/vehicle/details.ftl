<form id="vehicleDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>车牌号:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.plate}" name="plate" id= "plate"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    		<th>车辆颜色:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicle.color}" name="color" id= "color" disabled="disabled"/>
	    		</td>
	    		
	    	</tr>
	    	<tr >
	    		<th >车型:</th>
	    		<td colspan="3">
	    			 <input class="easyui-combobox" id="vehicleSelectVehicleBrand-detail" data-value="${vehicle.vehicleBrandDetail.vehicleLine.vehicleBrand.id}" disabled="disabled"/>
	    			 <input class="easyui-combobox" id="vehicleSelectVehicleLine-detail" data-value="${vehicle.vehicleBrandDetail.vehicleLine.id}" disabled="disabled"/>
	    			 <input class="easyui-combobox" id="vehicleSelectVehicleBrandDetail-detail" name="vehicleBrandDetailId" data-value="${vehicle.vehicleBrandDetail.id}" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>绑定设备:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${vehicle.device.deviceNo}" id="editVehicle_deviceInfo" panelHeight="150px" data-options="required:true,editable:false,prompt:'${message("csh.common.please.select")}'" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>车辆所有者:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${vehicle.endUser.userName}"  id="editVehicle_endUser" panelHeight="150px" data-options="required:true,editable:false,prompt:'${message("csh.common.please.select")}'" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicle.status")}:</th>
	    		<td colspan='2'>
	    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ENABLE',
				      value: '${message("csh.common.enable")}'
				      [#if vehicle.status =="ENABLE"], selected:true [/#if]
				     },{
				      label: 'DISABLE',
				      value: '${message("csh.common.disable")}'
				      [#if vehicle.status =="DISABLE"], selected:true [/#if]
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="status" id="editStatus" style="width:110px;" disabled="disabled"/>
	    		</td>
	    		</tr>
	    	<tr>
	    </table>
</form>



