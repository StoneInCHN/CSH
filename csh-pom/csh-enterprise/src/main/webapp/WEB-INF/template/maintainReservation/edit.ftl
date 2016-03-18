<form id="editMaintainReservation_form" method="post">   
		<input type="hidden" name="id" value="${maintainReservation.id}"/>
		<input type="hidden" name="endUserID"  value="${maintainReservation.endUser.id}" id="editRepareReservation_tenantUserID">
		<input type="hidden" id="vehicleListMap" value= '${vehicleListMap}'/>  
	    <table class="table table-striped"  border="0">
	    	<table class="table table-striped"  border="0">
		    <tr>
	    		<th>${message("csh.vehicle.endUser")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${maintainReservation.endUser.userName}"  id= "editRepareReservation_tenantUser"  data-options="required:true,editable:false"/>
	    			 <a href="#" id="endUser_search_btn" class="easyui-linkbutton" onclick="searchEndUser('editRepareReservation_tenantUser')" iconCls="icon-search" plain=true"></a>
	    		</td>
	    		<th>${message("csh.mobile")}:</th>
	    		<td>
	    			 <input  class="easyui-textbox" id= "endUserMobileNum" value="${maintainReservation.endUser.mobileNum}"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.vehicle.plate")}:</th>
	    		<td >
	    			 <input  class="easyui-combobox" name="vehicleId" data-value="${maintainReservation.plate}"  id= "vehiclePlate"  data-options="required:true"/>
	    		</td>
	    		<th>${message("csh.vehicle.vehicleBrand")}:</th>
	    		<td >
	    			 <input  class="easyui-textbox" value="${maintainReservation.vehicleBrand}" name="vehicleBrand" id= "vehicleBrand"  data-options="required:true"/>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>预约时间:</th>
	    		<td colspan="5">
	    			 <input  class="easyui-datebox" value="${maintainReservation.reservationDate}" name="reservationDate" id= "reservationDate"  data-options="required:true"/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th >备注:</th>
	    		<td colspan="5">
	    			 <input  class="easyui-textbox" name="remark" id= "remark" value="${maintainReservation.remark}" data-options="multiline:true,height:120,width:500"/>
	    		</td>
	    	</tr>
	    </table>
</form>



