<form id="vehicleBrandDetail_form" method="post">   
	<table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.vehicle.endUser")}:</th>
	    		<td colspan="3">
	    			 <input  class="easyui-textbox"  id= "addVehicleMaintain_endUser" value="${vehicleMaintain.vehicle.endUser.userName}" disabled="disabled"data-options="required:true,editable:false"/>
	    			 <a href="#" id="common_endUser_search_btn" class="easyui-linkbutton" onclick="searchEndUser('addVehicleMaintain_endUser')" disabled="disabled" iconCls="icon-search" plain=true"></a>
	    		</td>
	    		<th>${message("csh.mobile")}:</th>
	    		<td>
	    			 <input  class="easyui-textbox" id= "vehicleMaintainMobileNum" value="${vehicleMaintain.vehicle.endUser.mobileNum}" disabled="disabled" data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicle.plate")}:</th>
	    		<td colspan="3">
	    			 <input  class="easyui-combobox" name="vehicleId" value="${vehicleMaintain.vehicle.plate}" id= "editVehicleMaintain_plate" disabled="disabled" data-options="required:true"/>
	    		</td>
	    		<th>${message("csh.vehicleMaintain.dashboardMileage")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" name="dashboardMileage" id= "dashboardMileage" value="${vehicleMaintain.vehicle.dashboardMileage}" disabled="disabled" data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicleMaintain.lastMaintainDate")}:</th>
	    		<td colspan="3">
	    			 <input type="text" class="easyui-datebox" name="lastMaintainDate" value="${vehicleMaintain.lastMaintainDate}" disabled="disabled" data-options="required:true,editable:false"/>   
	    		</td>
	    		<th>${message("csh.vehicleMaintain.lastMaintainMileage")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" name="lastMaintainMileage" id= "lastMaintainMileage" value="${vehicleMaintain.lastMaintainMileage}" disabled="disabled" data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicleMaintain.nextMaintainDate")}:</th>
	    		<td colspan="3">
	    			 <input type="text" class="easyui-datebox" name="nextMaintainDate" value="${vehicleMaintain.nextMaintainDate}" disabled="disabled" data-options="required:true,editable:false"/>   
	    		</td>
	    		<th>${message("csh.vehicleMaintain.nextMaintainMileage")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" name="nextMaintainMileage" id= "nextMaintainMileage" value="${vehicleMaintain.nextMaintainMileage}" disabled="disabled" data-options="required:true"/>
	    		</td>
	    	</tr>
	    </table>
</form>



