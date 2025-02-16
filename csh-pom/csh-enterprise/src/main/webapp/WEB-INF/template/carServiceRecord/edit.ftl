<form id="editCarServiceRecord_form" method="post">
	<input type="hidden" id="carServiceRecordId" value="${carServiceRecord.id}" name="id"/>   
	 <table class="table table-striped"  border="0">
	 	<tr>
	 		<th>${message("csh.carServiceRecord.recordNo")}:</th>
    		<td colspan="5">
    			 <input  class="easyui-textbox" disabled="disabled"  value="${carServiceRecord.recordNo}" data-options="width:250,required:true"/>
    		</td>
	 	</tr>
	    <tr>
    		<th>${message("csh.carService.serviceName")}:</th>
    		<td >
    			 <input  class="easyui-textbox" value="${carServiceRecord.carService.serviceName}" disabled="disabled"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.carServiceRecord.endUser")}:</th>
    		<td>
    			 <input  class="easyui-textbox" disabled="disabled"  value="${carServiceRecord.endUser.userName}"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.carServiceRecord.paymentType")}:</th>
    		<td >
    			 <input  class="easyui-textbox"
				  	[#if carServiceRecord.paymentType == 'ALIPAY']
    			  		${message('csh.carServiceRecord.paymentType.ALIPAY')}
				  	[/#if]
				  	[#if carServiceRecord.paymentType == 'WECHAT']
				  		${message('csh.carServiceRecord.paymentType.WECHAT')}
				  	[/#if]
				  	[#if carServiceRecord.paymentType == 'COUPON']
				  		${message('csh.carServiceRecord.paymentType.COUPON')}
				  	[/#if]
				  	[#if carServiceRecord.paymentType == 'WASHCOUPON']
				  		${message('csh.carServiceRecord.paymentType.WASHCOUPON')}
				  	[/#if]
				  	[#if carServiceRecord.paymentType == 'OFFLINEBALLANCE']
				  		${message('csh.carServiceRecord.paymentType.OFFLINEBALLANCE')}
				  	[/#if]
				  	[#if carServiceRecord.paymentType == 'MIXCOUPONOFFLINE']
				  		${message('csh.carServiceRecord.paymentType.MIXCOUPONOFFLINE')}
				  	[/#if]
    			   disabled="disabled"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.carServiceRecord.paymentDate")}:</th>
    		<td>
    			 <input  class="easyui-textbox" name = "paymentDate" disabled="disabled" value="${carServiceRecord.paymentDate}"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.carService.serviceCategory")}:</th>
    		<td>
    			<input  class="easyui-textbox" disabled="disabled" id= "carServiceCategoryDetail" value="${carServiceRecord.carService.serviceCategory.categoryName}" name="serviceCategoryId"  data-options="required:true"/>
    		</td>
    		<th>${message("csh.carServiceRecord.clearingDate")}:</th>
    		<td >
    			 <input  class="easyui-textbox" value="${carServiceRecord.clearingDate}" disabled="disabled" id= "serviceName" name="serviceName"  data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.carServiceRecord.price")}:</th>
    		<td>
    			 <input  class="easyui-textbox" name="price"  [#if carServiceRecord.chargeStatus != 'IN_SERVICE'] readonly="true"[/#if] [#if carServiceRecord.price != '-1']value="${carServiceRecord.price}"[/#if] data-options="required:true"/>
    		</td>
    		
    		<th>${message("csh.carServiceRecord.chargeStatus")}:</th>
    		<td>
    			 
    			 [#if carServiceRecord.chargeStatus == 'PAID']
    				<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'RESERVATION_SUCCESS',
				      value: '${message("csh.carServiceRecord.chargeStatus.PAID")}'
				      [#if carServiceRecord.chargeStatus == 'PAID']
    			  		, selected:true
    			  	  [/#if]
				     },{
				      label: 'FINISH',
				      value: '${message("csh.carServiceRecord.chargeStatus.FINISH")}'
				      [#if carServiceRecord.chargeStatus == 'FINISH']
    			  		, selected:true
    			  	  [/#if]
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100" id="carServiceEditChargeStatus" name="chargeStatus" style="width:110px;"/>
				[#elseif carServiceRecord.chargeStatus == 'IN_SERVICE']
				     <input class="easyui-combobox" readOnly = "true" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'UNPAID',
				      value: '${message("csh.carServiceRecord.chargeStatus.UNPAID")}', 
				      selected:true
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100" id="carServiceEditChargeStatus" name="chargeStatus" style="width:110px;"/>
				[#else]
    			    <input class="easyui-combobox" readOnly = "true" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'RESERVATION',
				      value: '${message("csh.carServiceRecord.chargeStatus.RESERVATION")}'
				      [#if carServiceRecord.chargeStatus == 'RESERVATION']
    			  		, selected:true
    			  	  [/#if]
				     },{
				      label: 'RESERVATION_SUCCESS',
				      value: '${message("csh.carServiceRecord.chargeStatus.RESERVATION_SUCCESS")}'
				      [#if carServiceRecord.chargeStatus == 'RESERVATION_SUCCESS']
    			  		, selected:true
    			  	  [/#if]
				     },{
				      label: 'RESERVATION_FAIL',
				      value: '${message("csh.carServiceRecord.chargeStatus.RESERVATION_FAIL")}'
				      [#if carServiceRecord.chargeStatus == 'RESERVATION_FAIL']
    			  		, selected:true
    			  	  [/#if]
				     },{
				      label: 'UNPAID',
				      value: '${message("csh.carServiceRecord.chargeStatus.UNPAID")}'
				      [#if carServiceRecord.chargeStatus == 'UNPAID']
    			  		, selected:true
    			  	  [/#if]
				     },{
				      label: 'IN_SERVICE',
				      value: '${message("csh.carServiceRecord.chargeStatus.IN_SERVICE")}'
				      [#if carServiceRecord.chargeStatus == 'IN_SERVICE']
    			  		, selected:true
    			  	  [/#if]
				     },{
				      label: 'FINISH',
				      value: '${message("csh.carServiceRecord.chargeStatus.FINISH")}'
				      [#if carServiceRecord.chargeStatus == 'FINISH']
    			  		, selected:true
    			  	  [/#if]
				     },{
				      label: 'PAID',
				      value: '${message("csh.carServiceRecord.chargeStatus.PAID")}'
				      [#if carServiceRecord.chargeStatus == 'PAID']
    			  		, selected:true
    			  	  [/#if]
				     },{
				      label: 'OVERDUE',
				      value: '${message("csh.carServiceRecord.chargeStatus.OVERDUE")}'
				      [#if carServiceRecord.chargeStatus == 'OVERDUE']
    			  		, selected:true
    			  	  [/#if]
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100" id="carServiceEditChargeStatus" name="chargeStatus"  style="width:110px;"/>
    			[/#if]
    		</td>
    	</tr>
    	[#if carServiceRecord.carService.serviceCategory.id == 2]
    	<tr class="payCodeValid">
    		<th>支付验证码：</th>
    		<td>
    			<input  class="easyui-textbox"  id="validPayCode"  />
    			<input type="button" class="btn btn-primary" id="confirmPayCode" value="确认" />
    			<input type="hidden" id="payCodeValidFlag">
    		</td>
    	</tr>
    	[/#if]
    </table>
</form>



