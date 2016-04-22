<form id="addBeautifyReservation_form" method="post">
<input type="hidden" name="endUserID" id="addBeautifyReservation_endUserID">
    <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.vehicle.endUser")}:</th>
    		<td >
    			 <input  class="easyui-textbox"  id= "addBeautifyReservation_endUser"  data-options="required:true,editable:false"/>
    			 <a href="#" id="endUser_search_btn" class="easyui-linkbutton" onclick="searchEndUser('addBeautifyReservation_endUser')" iconCls="icon-search" plain=true"></a>
    		</td>
    		<th>${message("csh.mobile")}:</th>
    		<td>
    			 <input  class="easyui-textbox" id= "endUserMobileNum"  data-options="required:true"  validtype="mobile"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.vehicle.plate")}:</th>
    		<td >
    			 <input  class="easyui-combobox" name="vehicleId" id= "vehiclePlate"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.vehicle.vehicleBrand")}:</th>
    		<td >
    			 <input  class="easyui-textbox" name="vehicleBrand" id= "vehicleBrand"  data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>预约时间:</th>
    		<td colspan="5">
    			 <input  class="easyui-datebox" name="reservationDate" id= "reservationDate"  data-options="required:true,editable:false"/>
    		</td>
    		
    	</tr>
    	<tr>
    		<th >备注:</th>
    		<td colspan="5">
    			 <input  class="easyui-textbox" name="remark" id= "remark"  data-options="multiline:true,height:120,width:500"/>
    		</td>
    	</tr>
    </table>
</form>
