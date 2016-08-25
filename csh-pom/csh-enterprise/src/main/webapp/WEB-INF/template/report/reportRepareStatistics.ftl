<script src="${base}/resources/modules/reportRepareStatistics.js"></script>
<div>
	  <fieldset>
	  <legend>报表查询</legend>
	    <form id="report_repare_statistics_search_form" class="search-form">
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
	  	  <button id="report_repare_statistics_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("csh.search")}</button>
	    </div>
	  </fieldset>
</div>
<div id="reportRepareStatistics">
	<div id="repareStatisticsReportId" style="height:65%;width: 90%;margin:2%">
	</div>
	<table id = "reportRepareStatistics-table-list" class="table table-striped" style="height:auto;width: 98%;">   
	</table>
</div>