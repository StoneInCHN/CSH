<script src="${base}/resources/modules/reportWashStatistics.js"></script>
<div class="easyui-tabs"  data-options="tabPosition:'right', tabWidth:45,tabHeight:30,headerWidth:50" style="width:98%;height:auto">
<div title="按天">
	<div style="height:auto;width: 95%;margin-left:2%;margin-top:1%">
	  <fieldset>
	  <legend>报表查询</legend>
	    <form id="report_wash_statistics_search_form" class="search-form">
			<div class="search-item">
			    <label> 筛选时间:</label>
			    <input type="text" class="Wdate" id="beginDate" name="beginDate" readonly="readonly" onclick="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
			</div>
			<div class="search-item">
			    <label>${message("csh.to")}:</label>
			   	<input type="text" class="Wdate" id="endDate"  name="endDate" readonly="readonly" onclick="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="report_wash_statistics_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("csh.search")}</button>
	    </div>
	  </fieldset>
</div>
	<div style="height:auto;width: 95%;margin-left:2%">
		<div id="washStatisticsReportId" style="height:65%;margin:2%"></div>
		<table id = "reportWashStatistics-table-list" class="table table-striped"> </table>
	</div>
</div>
<div title="按月" >
	<div style="height:auto;width: 95%;margin-left:2%;margin-top:1%">
	  <fieldset>
	  <legend>报表查询</legend>
	    <form id="monthly_report_wash_form" class="search-form">
			<div class="search-item">
			    <label> 搜索最近 :</label>
			   		<input class="easyui-combobox" data-options="
				     valueField: 'label',
				     textField: 'value',
				     data: [
				     		[#list 1..36 as i]
				     		{
						      label: ${i},
						      value: ${i}+'个月'
				     		},
			    			[/#list]
				     ],
				     prompt:'${message("csh.common.please.select")}',panelMaxHeight:300"  name="maximum" style="width:110px;" editable="false"/>
			</div>
		</form>
		<div class="search-item">
	  	  <button id="monthly_report_wash_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("csh.search")}</button>
	    </div>
	  </fieldset>
	</div>
	<div  style="width: 95%">
		<div id="monthlyReportWashID" style="width:1000px;margin:2%"></div>
		<table id = "monthlyRreportWash-table-list" class="table table-striped"> </table>
	</div>
</div>