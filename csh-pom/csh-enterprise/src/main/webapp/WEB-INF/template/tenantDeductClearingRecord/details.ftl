<form id="tenantDeductClearingRecordDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
   <!--	<tr>
		<th>结算周期从：</th>
		<td>
			 <input type="text" class="easyui-datetimebox"  name="periodBeginDate" value="${tenantDeductClearingRecord.periodBeginDate}" readonly="readonly" disabled="disabled"/>
		</td>
		<th>到：</th>
		<td>
			 <input type="text" class="easyui-datetimebox"  name="periodEndDate" value="${tenantDeductClearingRecord.periodEndDate}" readonly="readonly" disabled="disabled"/>
		</td>
   	</tr>
   	-->
   	<tr>
   		<th>结算金额：</th>
		<td>
			 <input type="text" class="easyui-textbox" name="amountOfCurrentPeriod" value="${tenantDeductClearingRecord.amountOfCurrentPeriod}" id= "amountOfCurrentPeriod"   data-options="required:true" disabled="disabled"/>
		</td>
   	</tr>
   	<tr>
   		<table id="clearingCarServiceRecord-table-list" class="easyui-datagrid">   
   			<thead> 
   			<th data-options="field:'serviceName'">服务名称</th>
   			<th data-options="field:'chargeStatus',width:'50'">支付状态</th>
   			<th data-options="field:'paymentType'">支付方式</th>
   			<th data-options="field:'endUser'">购买用户</th>
   			<th data-options="field:'price'">价格</th>
   			<th data-options="field:'paymentTime'">付款时间</th>
   			</thead> 
   			<tbody>
   			[#list tenantDeductClearingRecord.carServiceTenantDeductRecords as carServiceRecord]
				<tr>   
            		<td>${carServiceRecord.carService.serviceName}</td>
            		<td> 
	    			 	[#if carServiceRecord.chargeStatus == 'PAID']
	    			  		${message('csh.carServiceRecord.chargeStatus.PAID')}
	    			  	[/#if]
	    			  	[#if carServiceRecord.chargeStatus == 'RESERVATION_SUCCESS']
	    			  		${message('csh.carServiceRecord.chargeStatus.RESERVATION_SUCCESS')}
	    			  	[/#if]
	    			  	[#if carServiceRecord.chargeStatus == 'RESERVATION_FAIL']
	    			  		${message('csh.carServiceRecord.chargeStatus.RESERVATION_FAIL')}
	    			  	[/#if]
	    			  	[#if carServiceRecord.chargeStatus == 'UNPAID']
	    			  		${message('csh.carServiceRecord.chargeStatus.UNPAID')}
	    			  	[/#if]
	    			  	[#if carServiceRecord.chargeStatus == 'FINISH']
	    			  		${message('csh.carServiceRecord.chargeStatus.FINISH')}
	    			  	[/#if]
	    			  	[#if carServiceRecord.chargeStatus == 'OVERDUE']
	    			  		${message('csh.carServiceRecord.chargeStatus.OVERDUE')}
	    			  	[/#if]
	    			  	[#if carServiceRecord.chargeStatus == 'RESERVATION']
	    			  		${message('csh.carServiceRecord.chargeStatus.RESERVATION')}
	    			  	[/#if]

    			   </td>
            		<td>
    			  	[#if carServiceRecord.paymentType == 'ALIPAY']
    			  		${message('csh.carServiceRecord.paymentType.ALIPAY')}
				  	[/#if]
				  	[#if carServiceRecord.paymentType == 'WECHAT']
				  		${message('csh.carServiceRecord.paymentType.WECHAT')}
				  	[/#if]
				  	[#if carServiceRecord.paymentType == 'WALLET']
				  		${message('csh.carServiceRecord.paymentType.WALLET')}
				  	[/#if]
    			   </td>
            		<td>${carServiceRecord.endUser.userName}</td>
            		<td>${carServiceRecord.price}</td>
            		<td>${carServiceRecord.paymentDate}</td>
        		</tr> 
			[/#list]
			</tbody>
   		</table>
   	</tr>
   </table>
</form>



