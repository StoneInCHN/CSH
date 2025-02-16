<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/estore/brand.js"></script>
<link rel="stylesheet" type="text/css"href="${base}/resources/js/kindeditor/themes/default/default.css" >
<script  src="${base}/resources/js/kindeditor/kindeditor-min.js"></script>
<script  src="${base}/resources/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>

<div>
	  <fieldset>
	    <legend>${message("csh.brand.search")}</legend>
	    <form id="brand-search-form" class="search-form">
			<div class="search-item">
			    <label> ${message("csh.brand.name")}:</label>
				<input type="text" class="easyui-textbox" id="brandNameSearch" name="brandNameSearch" validtype="length[0,20]"/>
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
	  	  <button id="brand-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="brand-table-list"></table>
<div id="brand_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="brand_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="brand_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="brand_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addBrand"></div>
<div id="editBrand"></div>
<div id="brandDetail"></div>




