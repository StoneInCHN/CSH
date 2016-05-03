<form id="applyClearingRecordForm" method="post" >
   <table class="table table-striped"  border="0">
   
   	<tr>
   		<th>结算金额：</th>
		<td>
			 <input type="text" class="easyui-textbox"  name="amountOfCurrentPeriod" id= "amountOfCurrentPeriod"   data-options="required:true"/>
		</td>
		<th>实得金额：</th>
		<td>
			 <input type="text" class="easyui-textbox" readOnly="true" name="amountRealIncome" id= "amountRealIncome"   data-options="required:true"/>
		</td>
		<th>平台提成比例：</th>
		<td>
			 <input type="text" class="easyui-textbox" readOnly="true" name="platformRate" id= "platformRate"  value="${commissionRate.platformRate}" data-options="required:true"/>
		</td>
   	</tr>
   	<tr>
   		<table id="clearingCarServiceRecord-table-list"></table>
   	</tr>
   </table>
</form>
