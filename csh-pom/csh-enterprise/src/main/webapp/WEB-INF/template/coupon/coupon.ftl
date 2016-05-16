<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/coupon.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.coupon.search")}</legend>
	    <form id="coupon-search-form" class="search-form">
	    	<div class="search-item">
			    <label> 优惠券类型:</label>
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
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60" name="couponTypeSearch" style="width:110px;" />
			</div>
			<div class="search-item">
			    <label> 发送类型:</label>
				<input class="easyui-combobox" data-options="
					     valueField: 'label',
					     textField: 'value',
					     data: [{
					      label: 'NORMAL',
					      value: '${message("csh.coupon.sendType.NORMAL")}'
					     },{
					      label: 'BIND',
					      value: '${message("csh.coupon.sendType.BIND")}'
					     }],
					     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60"  name="couponSendTypeSearch" style="width:110px;" />
			</div>
			<div class="search-item">
			    <label> 过期类型:</label>
				 <input class="easyui-combobox" data-options="
						     valueField: 'label',
						     textField: 'value',
						     data: [{
						      label: 'BYDAY',
						      value: '${message("csh.coupon.overDueType.BYDAY")}'
						     },{
						      label: 'BYDATE',
						      value: '${message("csh.coupon.overDueType.BYDATE")}'
						     }],
						     prompt:'${message("csh.common.please.select")}',panelMaxHeight:60"  name="couponOverDueTypeSearch" style="width:110px;" />
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
	  	  <button id="coupon-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="coupon-table-list"></table>
<div id="coupon_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="coupon_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="coupon_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="coupon_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addCoupon"></div>
<div id="editCoupon"></div>
<div id="couponDetail"></div>




