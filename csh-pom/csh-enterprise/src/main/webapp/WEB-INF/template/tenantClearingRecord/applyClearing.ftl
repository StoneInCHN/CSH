<form id="applyClearingRecordForm" method="post" >
   <table class="table table-striped"  border="0">
   	<tr>
   		<th>店铺名称：</th>
		<td>
			 <input type="text" class="easyui-textbox" name="staffID" id= "staffID"   data-options="required:true"/>
		</td>
		<th>结算周期从：</th>
		<td>
			 <input type="text" class="easyui-datetimebox"  name="periodBeginDate" value="${peroidBeginDate}" readonly="readonly" />
		</td>
		<th>到：</th>
		<td>
			 <input type="text" class="easyui-datetimebox"  name="periodEndDate" value="${peroidEndDate}" readonly="readonly"/>
		</td>
   	</tr>
   	<tr>
   		<th>结算金额：</th>
		<td>
			 <input type="text" class="easyui-textbox" name="amountOfCurrentPeriod" id= "amountOfCurrentPeriod"   data-options="required:true"/>
		</td>
   	</tr>
   	<tr>
   		<table id="clearingCarServiceRecord-table-list"></table>
   	</tr>
   </table>
</form>