<form id="tenantClearingRecordDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
   	<tr>
   		<th>店铺名称：</th>
		<td>
			 <input type="text" class="easyui-textbox" name="tenantName" id= "tenantName" value="${tenantClearingRecord.tenantName}"  data-options="required:true"/>
		</td>
		<th>结算周期从：</th>
		<td>
			 <input type="text" class="easyui-datetimebox"  name="periodBeginDate" value="${tenantClearingRecord.peroidBeginDate}" readonly="readonly" />
		</td>
		<th>到：</th>
		<td>
			 <input type="text" class="easyui-datetimebox"  name="periodEndDate" value="${tenantClearingRecord.peroidEndDate}" readonly="readonly"/>
		</td>
   	</tr>
   	<tr>
   		<th>结算金额：</th>
		<td>
			 <input type="text" class="easyui-textbox" name="amountOfCurrentPeriod" value="${tenantClearingRecord.amountOfCurrentPeriod}" id= "amountOfCurrentPeriod"   data-options="required:true"/>
		</td>
   	</tr>
   	<tr>
   		<table id="clearingCarServiceRecord-table-list" class="easyui-datagrid">   
   			
   			<th data-options="field:'serviceName'">服务名称</th>
   			<th data-options="field:'chargeStatus'">支付状态</th>
   			<th data-options="field:'paymentType'">支付方式</th>
   			<th data-options="field:'endUser'">购买用户</th>
   			<th data-options="field:'price'">价格</th>
   			<th data-options="field:'paymentTime'">付款时间</th> 
   			[#list tenantClearingRecord.carServiceRecords as carServiceRecord]
				<tr>   
            		<td>${carServiceRecord.carService.serviceName}</td>
            		<td>${carServiceRecord.chargeStatus}</td>
            		<td>${carServiceRecord.paymentType}</td>
            		<td>${carServiceRecord.endUser.userName}</td>
            		<td>${carServiceRecord.price}</td>
            		<td>${carServiceRecord.paymentDate}</td>
        		</tr> 
			[/#list]
   		</table>
   	</tr>
   </table>
</form>



