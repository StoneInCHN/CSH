<form id="editVehicle_form" method="post">   
		<input type="hidden" name="id" value= "${vehicle.id}"/>
		<input type="hidden" name="tenantID" value= "${vehicle.tenantID}"/>
		<input type="hidden" name="endUserID" id="editVehicle_endUserID" value="${vehicle.endUser.id}">
		<input type="hidden" name="deviceInfoID" id="editVehicle_deviceInfoID" value="${vehicle.device.id}">
	   <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>车牌号:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.plate}" name="plate" id= "plate" disabled="disabled" data-options="required:true"/>
	    		</td>
	    		<th>车辆颜色:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicle.color}" name="color" id= "color" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>绑定设备:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicle.device.deviceNo}" id="editVehicle_deviceInfo" panelHeight="150px" data-options="required:true,editable:false,prompt:'${message("csh.common.please.select")}'" disabled="disabled"/>
	    			 <!--<a href="#" id="common_device_search_btn" class="easyui-linkbutton" onclick="searchDevice('editVehicle_deviceInfo')" iconCls="icon-search" plain=true"></a>-->
	    		</td>
	    		<th>车辆所有者:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${vehicle.endUser.userName}"  id="editVehicle_endUser" panelHeight="150px" data-options="required:true,editable:false,prompt:'${message("csh.common.please.select")}'" disabled="disabled"/>
	    			 <!--<a href="#" id="common_endUser_search_btn" class="easyui-linkbutton" onclick="searchEndUser('editVehicle_endUser')" iconCls="icon-search" plain=true"></a>-->
	    		</td>
	    	</tr>
	    	<tr >
	    		<th >车型:</th>
	    		<td colspan="3">
	    			 <input class="easyui-combobox" id="vehicleSelectVehicleBrand-edit" data-value="${vehicle.vehicleBrandDetail.vehicleLine.vehicleBrand.id}" disabled="disabled"/>
	    			 <input class="easyui-combobox" id="vehicleSelectVehicleLine-edit" data-value="${vehicle.vehicleBrandDetail.vehicleLine.id}" disabled="disabled"/>
	    			 <input class="easyui-combobox" id="vehicleSelectVehicleBrandDetail-edit" name="vehicleBrandDetailId" data-value="${vehicle.vehicleBrandDetail.id}" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>仪表盘里程（公里）:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.dashboardMileage}" name="dashboardMileage" id= "dashboardMileage" disabled="disabled"/>
	    		</td>
	    		<th>电瓶电压（V）:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicle.dashboardBV}" name="dashboardBV" id= "dashboardBV" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>油量(L):</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.dashboradOil}" name="dashboradOil" id= "dashboradOil"  disabled="disabled"/>
	    		</td>
	    		<th>上牌时间:</th>
	    		<td >
	    			 <input type="text" class="easyui-datebox" name="plateDate" data-options="editable:false" value="${vehicle.plateDate}" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>车架号:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.vehicleNo}" name="vehicleNo" id= "vehicleNo"  disabled="disabled"/>
	    		</td>
	    		<th>生产日期:</th>
	    		<td >
	    			 <input type="text" class="easyui-datebox" name="produceDate" data-options="editable:false" value="${vehicle.produceDate}" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>上次保养里程:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicle.lastMaintainMileage}" name="lastMaintainMileage" id= "lastMaintainMileage-edit"/>
	    		</td>
	    		<th>保养公里数（多少公里保养一次）:</th>
	    		<td >
	    			 <input type="text" class="easyui-textbox" name="mileagePerMaintain" value="${vehicle.mileagePerMaintain}"/>
	    		</td>
	    	</tr>
	    </table>
</form>



