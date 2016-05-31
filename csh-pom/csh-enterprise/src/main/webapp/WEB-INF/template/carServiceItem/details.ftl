<form id="carServiceDetail_form" method="post">   
	 <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.carService.serviceName")}:</th>
    		<td >
    			 <input  class="easyui-textbox" value="${carServiceRecord.carService.serviceName}" disabled="disabled" id= "serviceName" name="serviceName"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.carServiceRecord.endUser")}:</th>
    		<td>
    			 <input  class="easyui-textbox" disabled="disabled"  value="${carServiceRecord.endUser.userName}"/>
    		</td>
    		<th>${message("csh.carService.serviceCategory")}:</th>
    		<td>
    			 <input  class="easyui-textbox" disabled="disabled" id= "carServiceCategoryDetail" value="${carServiceRecord.carService.serviceCategory.categoryName}" name="serviceCategoryId"  data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.carServiceRecord.paymentType")}:</th>
    		<td >
    			 <input  class="easyui-textbox"
    			  	[#if carServiceRecord.paymentType == 'ALIPAY']
    			  		value="${message('csh.carServiceRecord.paymentType.ALIPAY')}"
				  	[/#if]
				  	[#if carServiceRecord.paymentType == 'WECHAT']
				  		value="${message('csh.carServiceRecord.paymentType.WECHAT')}"
				  	[/#if]
				  	[#if carServiceRecord.paymentType == 'WALLET']
				  		value="${message('csh.carServiceRecord.paymentType.WALLET')}"
				  	[/#if]
    			   disabled="disabled"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.carServiceRecord.paymentDate")}:</th>
    		<td>
    			 <input  class="easyui-textbox" disabled="disabled"  value="${carServiceRecord.paymentDate}"/>
    		</td>
    		<th>${message("csh.carServiceRecord.chargeStatus")}:</th>
    		<td>
    			 <input  class="easyui-textbox" disabled="disabled" 
    			 	[#if carServiceRecord.chargeStatus == 'PAID']
    			  		value="${message('csh.carServiceRecord.chargeStatus.PAID')}"
    			  	[/#if]
    			  	[#if carServiceRecord.chargeStatus == 'RESERVATION_SUCCESS']
    			  		value="${message('csh.carServiceRecord.chargeStatus.RESERVATION_SUCCESS')}"
    			  	[/#if]
    			  	[#if carServiceRecord.chargeStatus == 'RESERVATION_FAIL']
    			  		value="${message('csh.carServiceRecord.chargeStatus.RESERVATION_FAIL')}"
    			  	[/#if]
    			  	[#if carServiceRecord.chargeStatus == 'UNPAID']
    			  		value="${message('csh.carServiceRecord.chargeStatus.UNPAID')}"
    			  	[/#if]
    			  	[#if carServiceRecord.chargeStatus == 'FINISH']
    			  		value="${message('csh.carServiceRecord.chargeStatus.FINISH')}"
    			  	[/#if]
    			  	[#if carServiceRecord.chargeStatus == 'OVERDUE']
    			  		value="${message('csh.carServiceRecord.chargeStatus.OVERDUE')}"
    			  	[/#if]
    			  	[#if carServiceRecord.chargeStatus == 'RESERVATION']
    			  		value="${message('csh.carServiceRecord.chargeStatus.RESERVATION')}"
    			  	[/#if]
    			   data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.carServiceRecord.clearingDate")}:</th>
    		<td >
    			 <input  class="easyui-textbox" value="${carServiceRecord.clearingDate}" disabled="disabled" id= "serviceName" name="serviceName"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.carServiceRecord.price")}:</th>
    		<td>
    			 <input  class="easyui-textbox" disabled="disabled"  value="${carServiceRecord.price}"/>
    		</td>
    		<th>${message("csh.carServiceRecord.recordNo")}:</th>
    		<td>
    			 <input  class="easyui-textbox" disabled="disabled"  value="${carServiceRecord.recordNo}" data-options="required:true"/>
    		</td>
    	</tr>
    </table>
</form>



