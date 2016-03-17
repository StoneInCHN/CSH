<form id="editVehicleMaintain_form" method="post">   
		<input type="hidden" name="id" value= "${vehicleMaintain.id}"/>
		<input type="hidden" id="vehicleListMap" value= '${vehicleListMap}'/>
	     
	     <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.vehicle.endUser")}:</th>
	    		<td colspan="3">
	    			 <input  class="easyui-textbox"  id= "addVehicleMaintain_endUser" value="${vehicleMaintain.vehicle.endUser.userName}" data-options="required:true,editable:false"/>
	    			 <a href="#" id="common_endUser_search_btn" class="easyui-linkbutton" onclick="searchEndUser('addVehicleMaintain_endUser')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    		<th>${message("csh.mobile")}:</th>
	    		<td>
	    			 <input  class="easyui-textbox" id= "mobileNum" value="${vehicleMaintain.vehicle.endUser.mobileNum}"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicle.plate")}:</th>
	    		<td >
	    			 <input  class="easyui-combobox" name="vehicleId" data-value="${vehicleMaintain.vehicle.id}" id= "editVehicleMaintain_plate"  data-options="required:true"/>
	    		</td>
	    		<th>${message("csh.vehicleMaintain.dashboardMileage")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" name="dashboardMileage" id= "dashboardMileage" value="${vehicleMaintain.vehicle.dashboardMileage}" data-options="required:true"/>
	    		</td>
	    		<th>${message("csh.common.currentDate")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-datebox" name="currentDate"  data-options="required:true,editable:false"/>   
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicleMaintain.lastMaintainDate")}:</th>
	    		<td colspan="3">
	    			 <input type="text" class="easyui-datebox" name="lastMaintainDate" value="${vehicleMaintain.lastMaintainDate}" data-options="required:true,editable:false"/>   
	    		</td>
	    		<th>${message("csh.vehicleMaintain.lastMaintainMileage")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" name="lastMaintainMileage" id= "lastMaintainMileage" value="${vehicleMaintain.lastMaintainMileage}" data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicleMaintain.nextMaintainDate")}:</th>
	    		<td colspan="3">
	    			 <input type="text" class="easyui-datebox" name="nextMaintainDate" value="${vehicleMaintain.nextMaintainDate}" data-options="required:true,editable:false"/>   
	    		</td>
	    		<th>${message("csh.vehicleMaintain.nextMaintainMileage")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" name="nextMaintainMileage" id= "nextMaintainMileage" value="${vehicleMaintain.nextMaintainMileage}" data-options="required:true"/>
	    		</td>
	    	</tr>
	    </table>
</form>



