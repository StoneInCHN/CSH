<form id="addCoupon_form" method="post" >
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.coupon.amount")}:</th>
    		<td>
    			 <input  class="easyui-numberbox" name="amount"  data-options="required:true,min:0,precision:2"  style="width:110px;"/>
    		</td>
    		<th class="addCouponCountsClass">${message("csh.coupon.counts")}:</th>
    		<td class="addCouponCountsClass">
    			 <input  class="easyui-numberbox" name="counts" id="addCouponCounts"  data-options="required:true"  style="width:110px;"/>
    		</td>
    		
    	</tr>
    	<tr>
    		<th>${message("csh.coupon.type")}:</th>
    		<td >
    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'COMMON',
				      value: '${message("csh.coupon.type.COMMON")}'
				     },{
				      label: 'SPECIFY',
				      value: '${message("csh.coupon.type.SPECIFY")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60" id="couponType-add" name="type" style="width:110px;" required=true/>
    		</td>
    		<th >${message("csh.coupon.sendType")}:</th>
    		<td >
    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'NORMAL',
				      value: '${message("csh.coupon.sendType.NORMAL")}',
				      selected:true
				     },{
				      label: 'TENANTBIND',
				      value: '${message("csh.coupon.sendType.TENANTBIND")}'
				     },{
				      label: 'DEVICEBIND',
				      value: '${message("csh.coupon.sendType.DEVICEBIND")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60" id="couponSendType-add"  name="sendType" style="width:110px;" required=true/>
    		</td>
    	</tr>
    	 <tr class="addCouponOverDueClass">
	    	 <th>${message("csh.coupon.overDueType")}:</th>
	    		<td >
	    			 <input class="easyui-combobox" id="couponOverDueType-add" data-options="
					     valueField: 'label',
					     textField: 'value',
					     data: [{
					      label: 'BYDAY',
					      value: '${message("csh.coupon.overDueType.BYDAY")}',
					      selected:true
					     },{
					      label: 'BYDATE',
					      value: '${message("csh.coupon.overDueType.BYDATE")}'
					     }],
					     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60"  name="overDueType" style="width:110px;" required=true/>
	    	</td>
    		<th class="addCouponOverDueDayClass">${message("csh.coupon.overDueDay")}:</th>
    		<td class="addCouponOverDueDayClass">
    			 <input  class="easyui-textbox"  style="width:110px;" id="addCouponOverDueDay" name="overDueDay"  data-options="required:true"/>
    		</td>
    		<th class="addCouponOverDueTimeClass">${message("csh.coupon.overDueTime")}:</th>
    		<td class="addCouponOverDueTimeClass">
    			 <input  class="easyui-datebox"  style="width:110px;" id = "addCouponOverDueTime" name="overDueTime"  data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
	    	<th>${message("csh.coupon.isEnable")}:</th>
    		<td>
    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'true',
				      value: '${message("csh.common.yes")}',
				      selected:true
				     },{
				      label: 'false',
				      value: '${message("csh.common.no")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60"  name="isEnabled" style="width:110px;" required=true/>
    		</td>
	    	<th class="addCouponDeadlineTimeClass">${message("csh.coupon.deadlineTime")}:</th>
    		<td class="addCouponDeadlineTimeClass">
    			 <input  class="easyui-datebox" name="deadlineTime" id = "couponOverDeadlineTime-add" style="width:110px;" data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.coupon.remark")}:</th>
    		<td colspan="5">
    			 <input  class="easyui-textbox" id= "remark" name = "remark"   style="height:100px;width:100%"/>
    		</td>
    	</tr>
    </table>
</table>
</form>
	<div class="couponSearchCarService-add" style="display:none">
	  <fieldset>
	    <legend>${message("csh.carService.search")}</legend>
	    <form id="coupon-addCarService-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.carService.serviceName")}:</label>
			    <input type="text" class="easyui-textbox" name="serviceNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.carService.serviceCategory")}:</label>
			    <input type="text" class="easyui-textbox" id="coupon-addServiceCategorySearch" name="serviceCategorySearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> 创建时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="coupon-addCarService-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
    <table id="coupon-add-carService-table-list">
    
    </table>
    </div>