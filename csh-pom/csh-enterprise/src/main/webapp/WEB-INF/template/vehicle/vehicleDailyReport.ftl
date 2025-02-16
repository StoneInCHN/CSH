<form id="vehicleDailyReport_form" method="post"> 
	<input type="hidden" id="reportVehicleId" name="vehicleId"/>
	[#if vehicleReportDate??]  
	 <table class="table table-striped"  border="0">
	 		<tr>
	    		<th>查询日期:</th>
	    		<td >
	    		<input class="easyui-datebox" name="date" id="queryReportDate" value="${vehicleReportDate}" data-options="editable:false" onclick="WdatePicker()" id="reportDateQuery" />
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>设备ID:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicleDailyReport.deviceId}" name="deviceId" id="reportVehicleId" data-options="required:true" disabled="disabled"/>
	    		</td>
	    		<th>每天行驶公里数(Km):</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicleDailyReport.mileAge}" id= "reportDailyMileage" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>平均油耗(L/100Km):</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${vehicleDailyReport.averageFuelConsumption}" id="reportAverageFuelConsumption" panelHeight="150px"  disabled="disabled"/>
	    		</td>
	    		<th>当日油耗(L):</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${vehicleDailyReport.fuelConsumption}"  id="reportFuelConsumption" panelHeight="150px" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th >当日油费(元):</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${vehicleDailyReport.cost}" id="reportCost"disabled="disabled"/>
	    			
	    		</td>
	    		<th >行驶时间:</th>
	    		<td >
	    			 <input type="hidden" value = "${vehicleDailyReport.runningTime}" id = "dailyReportRunningTimeHidden">
	    			 <input class="easyui-textbox" id = "reportRunningTime" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>平均速度(Km/h)</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicleDailyReport.averageSpeed}" name="averageSpeed" id= "reportAverageSpeed" disabled="disabled"/>
	    		</td>
	    		<th>驾驶得分</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicleDailyReport.score}" id= "reportScore" disabled="disabled"/>
	    		</td>
	    	</tr>
	    		<th>急刹车次数:</th>
	    		<td >
	    			 <input type="text" class="easyui-textbox" value="${vehicleDailyReport.emergencybrakecount}" id= "reportEmergencybrakecount" disabled="disabled"/>
	    		</td>
	    		<th>疲劳驾驶次数:</th>
	    		<td >
	    			 <input type="text" class="easyui-textbox" value="${vehicleDailyReport.fatiguedrivingcount}" id= "reportFatiguedrivingcount" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>急转弯次数:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${vehicleDailyReport.suddenturncount}" id= "reportSuddenturncount" data-options="required:true" disabled="disabled"/>
	    		</td>
	    		<th>急加速次数:</th>
	    		<td >
	    			 <input type="text" class="easyui-textbox" value="${vehicleDailyReport.rapidlyspeedupcount}" id= "reportRapidlyspeedupcount" disabled="disabled"/>
	    		</td>
	    	</tr>
	    </table>
	    [#else]
	    	当日无车辆数据
		[/#if]
</form>



