<form id="editCoupon_form" method="post">   
		<input type="hidden" name="id" value="${coupon.id}">
		<input type="hidden" id="editCoupon_carServiceList" value="${carServiceIds}">  
	  <table class="table table-striped"  border="0">
	    <tr>
    		<th>${message("csh.coupon.amount")}:</th>
    		<td>
    			 <input  class="easyui-numberbox" value="${coupon.amount}" name="amount"  data-options="required:true,min:0,precision:2"  style="width:110px;"/>
    		</td>
    		<th>${message("csh.coupon.counts")}:</th>
    		<td>
    			 <input  class="easyui-numberbox" name="counts" value="${coupon.counts}" data-options="required:true"  style="width:110px;"/>
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
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60" id="couponType-edit" name="type" style="width:110px;" required=true/>
    		</td>
    		<th >${message("csh.coupon.sendType")}:</th>
    		<td >
    			 <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'NORMAL',
				      value: '${message("csh.coupon.sendType.NORMAL")}'
				      [#if coupon.sendType == 'NORMAL']
				      ,selected:true
				      [/#if]
				     },{
				      label: 'BIND',
				      value: '${message("csh.coupon.sendType.BIND")}'
				       [#if coupon.sendType == 'BIND']
				      ,selected:true
				       [/#if]
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60" id="couponSendType-edit"  name="sendType" style="width:110px;" required=true/>
    		</td>
    	</tr>
    	 <tr class="editCouponOverDueClass">
	    	 <th>${message("csh.coupon.overDueType")}:</th>
	    		<td >
	    			 <input class="easyui-combobox" id="couponOverDueType-edit" data-options="
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
    		<th class="editCouponOverDueDayClass">${message("csh.coupon.overDueDay")}:</th>
    		<td class="editCouponOverDueDayClass">
    			 <input  class="easyui-textbox"  style="width:110px;" value="${coupon.overDueDay}" id="editCouponOverDueDay" name="overDueDay"  data-options="required:true"/>
    		</td>
    		<th class="editCouponOverDueTimeClass">${message("csh.coupon.overDueTime")}:</th>
    		<td class="editCouponOverDueTimeClass">
    			 <input  class="easyui-datebox"  style="width:110px;" value="${coupon.overDueTime}" id = "editCouponOverDueTime" name="overDueTime"  data-options="required:true"/>
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
	    	<th class="editCouponDeadlineTimeClass">${message("csh.coupon.deadlineTime")}:</th>
    		<td class="editCouponDeadlineTimeClass">
    			 <input  class="easyui-datebox" name="deadlineTime" value="${coupon.deadlineTime}" id = "couponOverDeadlineTime-edit" style="width:110px;" data-options="required:true"/>
    		</td>
    	</tr>
    	<tr>
    		<th>${message("csh.coupon.remark")}:</th>
    		<td colspan="5">
    			 <input  class="easyui-textbox" id= "remark" name = "remark" value="${coupon.remark}"  style="height:100px;width:100%"/>
    		</td>
    	</tr>
    </table>
</table>
</form>
	<div class="couponSearchCarService-edit">
	  <fieldset>
	    <legend>${message("csh.carService.search")}</legend>
	    <form id="coupon-editCarService-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.carService.serviceName")}:</label>
			    <input type="text" class="easyui-textbox" name="serviceNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.carService.serviceCategory")}:</label>
			    <input type="text" class="easyui-textbox" id="coupon-editServiceCategorySearch" name="serviceCategorySearch" validtype="length[0,20]"/>
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
	  	  <button id="coupon-editCarService-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
    <table id="coupon-edit-carService-table-list">
    
    </table>
    </div>


