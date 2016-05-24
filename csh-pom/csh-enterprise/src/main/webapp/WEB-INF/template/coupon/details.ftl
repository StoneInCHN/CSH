<form id="carServiceDetail_form" method="post">
		<input type="hidden" id="couponDetail_carServiceList" value="${carServiceIds}">   
	  	<table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.coupon.amount")}:</th>
    		<td>
    			 <input  class="easyui-numberbox" disabled="disabled" value="${coupon.amount}" name="amount"  data-options="required:true,min:0"  style="width:110px;"/>
    		</td>
    		<th>${message("csh.coupon.counts")}:</th>
    		<td>
    			 <input  class="easyui-numberbox" disabled="disabled" name="counts" value="${coupon.counts}" data-options="required:true"  style="width:110px;"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.coupon.type")}:</th>
    		<td >
    			 <input class="easyui-combobox" disabled="disabled" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'COMMON',
				      value: '${message("csh.coupon.type.COMMON")}'
				      [#if coupon.type == 'COMMON']
				      ,selected:true
				        [/#if]
				     },{
				      label: 'SPECIFY',
				      value: '${message("csh.coupon.type.SPECIFY")}'
				      [#if coupon.type == 'SPECIFY']
				      ,selected:true
				       [/#if]
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60" id="couponType-detail" name="type" style="width:110px;" required=true/>
    		</td>
    		<th >${message("csh.coupon.sendType")}:</th>
    		<td >
    			 <input class="easyui-combobox" disabled="disabled" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'NORMAL',
				      value: '${message("csh.coupon.sendType.NORMAL")}'
				      [#if coupon.sendType == 'NORMAL']
				      ,selected:true
				      [/#if]
				     },{
				      label: 'TENANTBIND',
				      value: '${message("csh.coupon.sendType.TENANTBIND")}'
				       [#if coupon.sendType == 'TENANTBIND']
					      ,selected:true
					   [/#if]
				     },{
				      label: 'DEVICEBIND',
				      value: '${message("csh.coupon.sendType.DEVICEBIND")}'
				       [#if coupon.sendType == 'DEVICEBIND']
				      ,selected:true
				      [/#if]
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60" id="couponSendType-detail"  name="sendType" style="width:110px;" required=true/>
    		</td>
    	</tr>
    	 <tr class="couponOverDueDetailClass">
	    	 <th>${message("csh.coupon.overDueType")}:</th>
	    		<td >
	    			 <input class="easyui-combobox" disabled="disabled" id="couponOverDueType-detail" data-options="
					     valueField: 'label',
					     textField: 'value',
					     data: [{
					      label: 'BYDAY',
					      value: '${message("csh.coupon.overDueType.BYDAY")}'
					      [#if coupon.overDueType == 'BYDAY']
				      		,selected:true
				      	   [/#if]
					     },{
					      label: 'BYDATE',
					      value: '${message("csh.coupon.overDueType.BYDATE")}'
					      [#if coupon.overDueType == 'BYDATE']
				      		,selected:true
				      	   [/#if]
					     }],
					     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60"  name="overDueType" style="width:110px;" required=true/>
	    	</td>
    		<th class="couponOverDueDayDetailClass">${message("csh.coupon.overDueDay")}:</th>
    		<td class="couponOverDueDayDetailClass">
    			 <input  class="easyui-textbox" disabled="disabled" style="width:110px;" value="${coupon.overDueDay}" id="couponOverDueDayDetail" name="overDueDay"  data-options="required:true"/>
    		</td>
    		<th class="couponOverDueTimeDetailClass">${message("csh.coupon.overDueTime")}:</th>
    		<td class="couponOverDueTimeDetailClass">
    			 <input  class="easyui-datebox"  disabled="disabled" style="width:110px;" value="${coupon.overDueTime}" id = "couponOverDueTimeDetail" name="overDueTime"  data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
	    	<th>${message("csh.coupon.isEnable")}:</th>
    		<td>
    			 <input class="easyui-combobox" disabled="disabled" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'true',
				      value: '${message("csh.common.yes")}'
				       [#if coupon.isEnabled]
				      	 ,selected:true
				        [/#if]
				     },{
				      label: 'false',
				      value: '${message("csh.common.no")}'
				      [#if !coupon.isEnabled]
				      	 ,selected:true
				       [/#if]
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60"  name="isEnabled" style="width:110px;" required=true/>
    		</td>
	    	<th class="couponDeadlineTimeDetailClass">${message("csh.coupon.deadlineTime")}:</th>
    		<td class="couponDeadlineTimeClassDetail">
    			 <input  class="easyui-datebox"  disabled="disabled" name="deadlineTime" value="${coupon.deadlineTime}" id = "couponOverDeadlineTime-detail" style="width:110px;" data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.coupon.remark")}:</th>
    		<td colspan="5">
    			 <input  class="easyui-textbox" disabled="disabled" id= "remark" name = "remark" value="${coupon.remark}"  style="height:100px;width:100%"/>
    		</td>
    	</tr>
    </table>
</table>
</form>
<table id="coupon-detail-carService-table-list">
    </table>


