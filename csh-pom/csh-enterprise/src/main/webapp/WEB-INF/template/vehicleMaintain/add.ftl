<form id="addVehicleMaintain_form" method="post">
    <table class="table table-striped"  border="0">
    	<tr>
    		<th>${message("csh.vehicle.endUser")}:</th>
    		<td colspan="3">
    			 <input  class="easyui-textbox"  id= "vehicleMaintain_endUser"  data-options="required:true,editable:false"/>
    			 <a href="#" id="common_endUser_search_btn" class="easyui-linkbutton" onclick="searchEndUser('vehicleMaintain_endUser')" iconCls="icon-search" plain=true"></a>
    		</td>
    		<th>${message("csh.mobile")}:</th>
    		<td>
    			 <input  class="easyui-textbox" id= "endUserMobileNum"  data-options="required:true"  validtype="mobile"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.vehicle.plate")}:</th>
    		<td colspan="3">
    			 <input  class="easyui-combobox" name="vehicleId" id= "vehiclePlate"   data-options="required:true"/>
    		</td>
    		<th>${message("csh.vehicleMaintain.dashboardMileage")}:</th>
    		<td >
    			 <input  class="easyui-numberbox" name="dashboardMileage" id= "dashboardMileage"  data-options="required:true,min:0"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.vehicleMaintain.lastMaintainDate")}:</th>
    		<td colspan="3">
    			 <input type="text" class="Wdate" name="lastMaintainDate" id="addNextMaintainDateStart" data-options="required:true,editable:false"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'addNextMaintainDateEnd\')}'});"/>   
    		</td>
    		<th>${message("csh.vehicleMaintain.lastMaintainMileage")}:</th>
    		<td >
    			 <input  class="easyui-numberbox" name="lastMaintainMileage" id= "lastMaintainMileage"  data-options="required:true,min:0"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.vehicleMaintain.nextMaintainDate")}:</th>
    		<td colspan="3">
    			 <input  type="text" class="Wdate" name="nextMaintainDate" id="addNextMaintainDateEnd"  data-options="required:true,editable:false" onclick="WdatePicker({minDate: '#F{$dp.$D(\'addNextMaintainDateStart\')}'});"/>   
    		</td>
    		<th>${message("csh.vehicleMaintain.nextMaintainMileage")}:</th>
    		<td >
    			 <input  class="easyui-numberbox" name="nextMaintainMileage" id= "nextMaintainMileage"  data-options="required:true,min:0"/>
    		</td>
    	</tr>
    </table>
</form>


