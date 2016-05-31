<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/pushMessageInfo.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.messageInfo.search")}</legend>
	    <form id="pushMessageInfo-search-form" class="search-form">
	    <input type="hidden" name="totalRecord" id="totalRecord"/>
	    
			<div class="search-item">
			    <label> ${message("csh.messageInfo.title")}:</label>
			    <input type="text" class="easyui-textbox" id="titleSearch" name="titleSearch" validtype="length[0,20]"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.messageInfo.type")}:</label>
			    
    			 <input class="easyui-combobox",
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'PERSONALMSG',
				      value: '${message("csh.messageInfo.type.PERSONALMSG")}'
				     },{
				      label: 'NEWSMSG',
				      value: '${message("csh.messageInfo.type.NEWSMSG")}'
				     },{
				      label: 'PROMOTION',
				      value: '${message("csh.messageInfo.type.PROMOTION")}'}],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="messageTypeSearch" style="width:110px;"/>
			</div>
			<div class="search-item">
			    <label>创建时间:</label>
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
<input type="hidden" id="smsSendType" name="sendType" value='SMS'"/>
<table id="pushMessageInfo-table-list"></table>
<div id="pushMessageInfo_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="pushMessageInfo_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="pushMessageInfo_manager_tool.details();">查看详情</a>
		<!--<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="pushMessageInfo_manager_tool.remove();">删除</a>-->
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addPushMessageInfo"></div>
<div id="pushMessageInfoDetail"></div>




