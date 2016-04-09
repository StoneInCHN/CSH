<form id="vehicleDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>设备ID:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicleDailyReport.deviceid}" name="plate" id= "plate"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    		<th>百公里油耗:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicleDailyReport.fuelconsumption}" name="color" id= "color" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>速度:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicleDailyReport.speed}" id="editVehicle_deviceInfo" panelHeight="150px"  disabled="disabled"/>
	    		</td>
	    		<th>第一次里程:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${vehicleDailyReport.firstmileage}"  id="editVehicle_endUser" panelHeight="150px" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th >最后一次里程:</th>
	    		<td colspan="3">
	    			 <input class="easyui-textbox" value="${vehicleDailyReport.lastmileage}" disabled="disabled"/>
	    			
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>运行时间(驾驶时间):</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicleDailyReport.runningtime}" name="dashboardMileage" id= "dashboardMileage" disabled="disabled"/>
	    		</td>
	    		<th>花费:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicleDailyReport.cost}" name="dashboardBV" id= "dashboardBV" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>第一次剩余油量:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicleDailyReport.firstremaininggas}" name="dashboradOil" id= "dashboradOil"  disabled="disabled"/>
	    		</td>
	    		<th>最后一次剩余油量:</th>
	    		<td >
	    			 <input type="text" class="easyui-textbox" value="${vehicleDailyReport.lastremaininggas}" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>更新次数:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicleDailyReport.updatecount}" name="vehicleNo" id= "vehicleNo"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    		<th>急刹车次数:</th>
	    		<td >
	    			 <input type="text" class="easyui-textbox" name="produceDate" value="${vehicleDailyReport.emergencybrakecount}" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>急转弯次数:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicleDailyReport.suddenturncount}" name="vehicleNo" id= "vehicleNo"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    		<th>急加速次数:</th>
	    		<td >
	    			 <input type="text" class="easyui-textbox" name="produceDate" value="${vehicleDailyReport.rapidlyspeedupcount}" disabled="disabled"/>
	    		</td>
	    	</tr>
	    </table>
</form>



