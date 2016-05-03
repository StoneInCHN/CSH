<link rel="stylesheet" type="text/css"href="${base}/resources/js/kindeditor/themes/default/default.css" >
<script  src="${base}/resources/js/kindeditor/kindeditor-min.js"></script>
<script  src="${base}/resources/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<script src="${base}/resources/modules/advertisement.js"></script>
<div>
	  <fieldset>
	    <legend>${message("csh.fixedAssets.search")}</legend>
	    <form id="notification-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.common.title")}:</label>
			    <input type="text" class="easyui-textbox" id="title" name="title" />
			</div>
			<div class="search-item">
			    <label> ${message("csh.elderly.name")}:</label>
			    <input type="text" class="easyui-textbox" id="name" name="name" />
			</div>
			<div class="search-item">
			    <label> ${message("csh.physicalExamination.physicalExaminationDate")}:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("csh.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="advertisement-search-btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</button>
	    </div>
	  </fieldset>
</div>
<table id="advertisement-table-list"></table>
<div id="advertisement_manager_tool">
	<div class="tool-button">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="advertisement_manager_tool.add();">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain=true onclick="advertisement_manager_tool.edit();">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain=true onclick="advertisement_manager_tool.remove();">删除</a>
	</div>
	<div class="tool-filter"></div>
</div>
<div id="addAdvertisement"> 
	<form id="addAdvertisement_form" method="post" class="form-table">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.advertisement.operator")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="operator" id= "operator" data-options="required:true" />
	    		</td>
	    		<th>${message("csh.common.title")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="title" id= "title" data-options="required:true" />
	    		</td>
	    		
	    	</tr>
	    	<tr >
		    	<th >${message("csh.advertisement.publishTime")}:</th>
		    	<td colspan="3">
		    		<input type="text" class="Wdate" id="publishTime" name="publishTime" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />   
		    	</td>
	    	</tr>
	    	<tr>
	    		<th>${message("csh.advertisement.content")}:</th>
	    		<td colspan="3">
	    			 <textarea id= "add_notification_content"  
	    			 style="height:200px;width:300px" name="content"></textarea>   
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editAdvertisement"></div>
<div id="advertisementDetail"></div>




