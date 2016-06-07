<script src="${base}/resources/js/common.js"></script>
<script src="${base}/resources/modules/itemPart.js"></script>
<script src="${base}/resources/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<div class="easyui-layout" data-options="fit:true">
     <div data-options="region:'west',split:true" style="width:350px;padding-left:10px" title="筛选">
		  <fieldset>
		    <legend>${message("csh.carServiceItem.search")}</legend>
		   <form id="carServiceItem_itemPart-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.carService.serviceName")}:</label>
			    <input type="text" class="easyui-textbox" id="serviceNameSearch" style="width:100px" name="serviceNameSearch" validtype="length[0,20]"/>
			</div>
		</form>
			<div class="search-item">
		  	  <button id="carServiceItem_itemPart-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
		    </div>
		  </fieldset>
        <table id="carServiceItem_itemPart-table-list"></table>  
     </div>
	<div data-options="region:'center'" style="padding-right:10px">
		<div>
			  <fieldset>
			    <legend>${message("csh.carServiceItem.search")}</legend>
			    <form id="itemPart-search-form" class="search-form">
			    	<div class="search-item">
					    <label> ${message("csh.carServiceItem.serviceName")}:</label>
					    <input type="text" class="easyui-textbox" style="width:80px" id="serviceItemNameSearch" name="serviceItemPartNameSearch" validtype="length[0,20]"/>
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
			  	  <button id="itemPart-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
			    </div>
			  </fieldset>
		</div>
		<table id="itemPart-table-list" style="width:800px"></table>
		<div id="itemPart_manager_tool">
			<div class="tool-button">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="itemPart_manager_tool.add();">添加</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="itemPart_manager_tool.edit();">修改</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="itemPart_manager_tool.remove();">删除</a>
			</div>
			<div class="tool-filter"></div>
		</div>
		<div id="addItemPart"></div>
		<div id="editItemPart"></div>
		<div id="itemPartDetail"></div>
	</div>
</div>



