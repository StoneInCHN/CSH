<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/estore/parameterGroup.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.parameterGroup.search")}</legend>
	    <form id="parameterGroup-search-form" class="search-form">
			<div class="search-item">
			    <label> ${message("csh.parameterGroup.name")}:</label>
				<input type="text" class="easyui-textbox" id="brandNameSearch" name="parameterGroupNameSearch" validtype="length[0,20]"/>
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
	  	  <button id="parameterGroup-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="parameterGroup-table-list"></table>
<div id="parameterGroup_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="parameterGroup_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="parameterGroup_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="parameterGroup_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addParameterGroup"></div>
<div id="editParameterGroup"></div>
<div id="parameterGroupDetail"></div>




