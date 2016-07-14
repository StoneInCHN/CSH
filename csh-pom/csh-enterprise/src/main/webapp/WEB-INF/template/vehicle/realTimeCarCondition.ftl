<form id="vehicleDetail_form" method="post">
	<input type="hidden" id="realTimeCarConditonLat" value="${realTimeCarCondition.lat}"/>
	<input type="hidden" id="realTimeCarConditonLon" value="${realTimeCarCondition.lon}">   
	 <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>里程:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${realTimeCarCondition.mileAge}" name="plate" id= "plate"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    		<th>运行时间:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${realTimeCarCondition.engineRuntime}" name="color" id= "color" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>平均油耗:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${realTimeCarCondition.averageOil}" id="editVehicle_deviceInfo" panelHeight="150px" disabled="disabled"/>
	    		</td>
	    		<th>速度:</th>
	    		<td>
	    			 <input class="easyui-textbox" value="${realTimeCarCondition.averageOil}"  id="editVehicle_endUser" panelHeight="150px" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr >
	    		<th >经度:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${realTimeCarCondition.lon}" disabled="disabled"/>
	    			
	    		</td>
	    		<th >纬度:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${realTimeCarCondition.lat}" disabled="disabled"/>
	    			
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<th>方位角:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${realTimeCarCondition.azimuth}" name="dashboardMileage" id= "dashboardMileage" disabled="disabled"/>
	    		</td>
	    		<th>是否启动:</th>
	    		<td >
	    			 <input class="easyui-textbox" value="${realTimeCarCondition.acc}" name="dashboardBV" id= "dashboardBV" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>当前位置</th>
	    		<td colspan="4">
	    			<div id="vehicleLocationMap" style="height:350px;width:99%;">
	    		</td>
	    	</tr>
	    </table>
	    
</form>



