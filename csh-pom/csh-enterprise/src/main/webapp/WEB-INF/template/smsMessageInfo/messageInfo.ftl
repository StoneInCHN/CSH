<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/smsMessageInfo.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.vehicle.search")}</legend>
	    <form id="smsMessageInfo-search-form" class="search-form">
	    <input type="hidden" name="totalRecord" id="totalRecord"/>
	    <input type="hidden" name="sendType" value=${sendType}"/>
	    	<div class="search-item">
			    <label> ${message("csh.vehicle.plate")}:</label>
			    <input type="text" class="easyui-textbox" id="plateSearch" name="plateSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.reservation.userName")}:</label>
			    <input type="text" class="easyui-textbox" id="userNameSearch" name="userNameSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.reservation.infoFrom")}:</label>
			    
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'APP',
				      value: '${message("csh.reservation.infoFrom.app")}'
				     },{
				      label: 'CALL',
				      value: '${message("csh.reservation.infoFrom.call")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="infoFromSearch" style="width:110px;"/>
			    
			</div>
			<div class="search-item">
			    <label> 预约时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>到:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="maintainReservation-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="smsMessageInfo-table-list"></table>
<div id="smsMessageInfo_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="smsMessageInfo_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="smsMessageInfo_manager_tool.details();">查看详情</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="smsMessageInfo_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addSmsMessageInfo"></div>
<div id="smsMessageInfoDetail"></div>




