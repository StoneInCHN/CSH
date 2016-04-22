<form id="repareReservationDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
	    	<table class="table table-striped"  border="0">
		    <tr>
	    		<th>${message("csh.vehicle.endUser")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${repareReservation.endUser.userName}"  id= "addRepareReservation_tenantUser"  data-options="required:true,editable:false" disabled="disabled"/>
	    		</td>
	    		<th>${message("csh.mobile")}:</th>
	    		<td>
	    			 <input  class="easyui-textbox" id= "endUserMobileNum" value="${repareReservation.endUser.mobileNum}"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicle.plate")}:</th>
	    		<td >
	    			 <input  class="easyui-combobox" name="vehicleId" value="${repareReservation.plate}"  id= "vehiclePlate"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    		<th>${message("csh.vehicle.vehicleBrand")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${repareReservation.vehicleBrand}" name="vehicleBrand" id= "vehicleBrand"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>预约时间:</th>
	    		<td colspan="5">
	    			 <input  class="easyui-datebox" value="${repareReservation.reservationDate}" name="reservationDate" id= "reservationDate"  data-options="required:true" disabled="disabled"/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th >备注:</th>
	    		<td colspan="5">
	    			 <input  class="easyui-textbox" name="remark" id= "remark" value="${repareReservation.remark}" data-options="multiline:true,height:120,width:500" disabled="disabled"/>
	    		</td>
	    	</tr>
	    </table>
</form>



