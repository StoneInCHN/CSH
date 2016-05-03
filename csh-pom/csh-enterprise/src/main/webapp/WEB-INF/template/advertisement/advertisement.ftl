<link rel="stylesheet" type="text/css"href="${base}/resources/js/kindeditor/themes/default/default.css" >
<script  src="${base}/resources/js/kindeditor/kindeditor-min.js"></script>
<script  src="${base}/resources/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<script src="${base}/resources/modules/advertisement.js"></script>
<div>
	  <fieldset>
	    <legend>${message("csh.advertisement.search")}</legend>
	    <form id="advertisement-search-form" class="search-form">
	    	<div class="search-item">
			    <label> ${message("csh.advertisement.advName")}:</label>
			    <input type="text" class="easyui-textbox" id="searchAdvName" name="searchAdvName" />
			</div>
			<div class="search-item">
			    <label> ${message("csh.advertisement.status")}:</label>
			    <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ENABLE',
				      value: '${message("csh.common.enable")}'
				     },{
				      label: 'DISABLE',
				      value: '${message("csh.common.disable")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="searchStatus" style="width:110px;"/>
			</div>
			<div class="search-item">
			    <label> ${message("csh.createDate")}:</label>
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
		<input type="hidden" id="addAdvertisement_form_file_input" name="advImageUrl">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>${message("csh.advertisement.advName")}:</th>
	    		<td>
	    			 <input class="easyui-textbox" name="advName" id= "advName" data-options="required:true" />
	    		</td>
	    		<th>${message("csh.advertisement.status")}:</th>
	    		<td>
	    			  <input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [{
				      label: 'ENABLE',
				      value: '${message("csh.common.enable")}'
				     },{
				      label: 'DISABLE',
				      value: '${message("csh.common.disable")}'
				     }],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:100"  name="status" style="width:110px;" required=true/>
	    		</td>
	    		
	    	</tr>
	    	<tr>
	    		<th>${message("csh.advertisement.advContentLink")}:</th>
	    		<td colspan="4">
	    			 <input class="easyui-textbox" name="advContentLink" id= "advContentLink" data-options="required:true" />
	    		</td>
	    	</tr>
	    	<tr rowspan="4">
    		
    		<th>${message("csh.advertisement.advImage")}:</th>
    		<td  colspan="4">
    			 <div title="图片上传" class="easyui-tooltip headWarp">
	    				<div id="advertisementUploader-add" class="single-uploader">
						    <div  class="queueList">
						        <div  class="placeholder">
						        	<div id="advertisementFilePicker-add" ></div>
						        </div>
						    </div>
						    <div class="btns">
						        <div class="uploadBtn state-pedding"></div>
						    </div>
						</div>
	    			</div>
    		</td>
    	</tr>
	    	<tr>
	    		<th>${message("csh.advertisement.remark")}:</th>
	    		<td>
	    			 <input type="text" class="easyui-textbox" name="remark" validtype="length[0,150]" data-options="multiline:true,height:90,width:260" />   
	    		</td>
	    	</tr>
	    </table>
	</form>
</div>
<div id="editAdvertisement"></div>
<div id="advertisementDetail"></div>




