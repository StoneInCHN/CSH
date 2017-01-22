<script src="${base}/resources/modules/vehicleMileageReport.js"></script>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:false" style="width:250px" title="车辆查询">
			<fieldset style="padding:5px 0px 4px 4px;margin:0px">
			        <form id="vehicleMileage_vehicle_search_form" class="search-form">
						<div class="search-item">
						   <label>车牌:</label>
						   <input type="text" class="easyui-textbox"  id="vehiclePlateSearch" name="plateSearch" validtype="length[0,10]" style="width:60px;"/>
						</div>
					</form>
					<div class="search-item">
				  	  <button id="vehicleMileage_vehicle_search_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-search'">${message("csh.search")}</button>
				    </div>
				</fieldset>
                <table id="vehicleMileageVehicleSearch-table-list"></table>
		</div>
		<div data-options="region:'center'" >
				<div class="easyui-panel" style="height:800px;background:#fafafa;" data-options="border:false">
						  <fieldset>
								  	<legend>条件查询</legend>
								    <form id="vehicleMileageReport_search_form" class="search-form">
								    	<div class="search-item">
										    <label> 选择年份:</label>
										    <input class="easyui-combobox" data-options="valueField: 'label',textField: 'value',
												data: [
													[#list selectYears as selectYear]
														{label: ${selectYear},value:${selectYear}},
													[/#list]
												],prompt:'${message("csh.common.please.select")}',panelMaxHeight:100" value="${year}"  id="vehicleStatusYearID"  />
										</div>		
										<div class="search-item">
										    <label> 选择月份:</label>
										    <input class="easyui-combobox" data-options="valueField: 'label',textField: 'value',
												data: [
													[#list [1,2,3,4,5,6,7,8,9,10,11,12] as month]
														{label: ${month},value:${month}},
													[/#list]
												],prompt:'${message("csh.common.please.select")}',panelMaxHeight:250" value="${month}"  id="vehicleStatusMonthID" />
										</div>										
										<input type="hidden" name="vehicleID" id="vehicleMileage_deviceNo" value="1">
									</form>
									<div class="search-item">
								  	  <button id="vehicleMileageReport_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("csh.search")}</button>
								  	  <button id="vehicleStatus_export_btn" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">${message("csh.button.export")}</button>
								    </div>
								  </fieldset>
							<div id="vehicleMileageReport">
								<div class="topTabNav">
									<ul class="topTabNavBox">
										<li><a  href="javascript:void(0);" id = "report_dailyMileageID" class="topTabActive"><span>行驶里程</span></a></li>
										<li><a  href="javascript:void(0);" id = "report_averageFuelConsumptionID"><span>平均油耗</span></a></li>
										<li><a  href="javascript:void(0);" id = "report_fuelConsumptionID"><span>油耗量</span></a></li>
										<li><a  href="javascript:void(0);" id = "report_costID"><span>油费金额</span></a></li>
										<li><a  href="javascript:void(0);" id = "report_averageSpeedID"><span>平均速度</span></a></li>
										<li><a  href="javascript:void(0);" id = "report_emergencybrakecountID"><span>驾驶行为</span></a></li>
									</ul>
								</div>									
								<div id="vehicleMileageReportDivId" style="height:400px;width:99%;">
								</div>
								<table id = "vehicleMileageReport-table-list" class="table table-striped" >   
								</table>
						</div>
				</div>
		</div>
</div>
<div id="vehicleStatus_export_div">
	<form id="vehicleStatus_export_form" method="post" class="form-table">  
	<fieldset style="padding:5px">
		 <div class="search-item">
		    <label> 选择年份:</label>
		    <input class="easyui-combobox" data-options="valueField: 'label',textField: 'value',
				data: [
					[#list selectYears as selectYear]
						{label: ${selectYear},value:${selectYear}},
					[/#list]
				],prompt:'${message("csh.common.please.select")}',panelMaxHeight:100" value="${year}"  id="vehicleStatusExportYear"  />
		    &nbsp;&nbsp;&nbsp;&nbsp;
		    <label> 选择月份:</label>
		    <input class="easyui-combobox" data-options="valueField: 'label',textField: 'value',
				data: [
					[#list [1,2,3,4,5,6,7,8,9,10,11,12] as month]
						{label: ${month},value:${month}},
					[/#list]
				],prompt:'${message("csh.common.please.select")}',panelMaxHeight:250" value="${month}"  id="vehicleStatusExportMonth" />
		</div>	
		<input type="hidden" id="exportFromDate"	name="fromDate">
		<input type="hidden" id="exportToDate"	name="toDate">
	</fieldset>				
		 <table class="table table-striped">
	    	<tr>
	    		<th  style="width:130px">目标车辆:</th>
	    		<td>所有车辆</td>
	    	</tr>
	    	<tr>
	    		<th  style="width:130px">选择项目:</th>
	    		<td>
	    								<span class="fieldSet">
											<label>
												<input type="checkbox" value="dailyMileage"  name="exportItems" checked/><span class="check_box_text">行驶里程</span>
											</label>
											<label>
												<input type="checkbox" value="averageFuelConsumption"  name="exportItems" checked /><span class="check_box_text">平均油耗</span>
											</label>
											<label>
												<input type="checkbox" value="fuelConsumption"  name="exportItems" checked /><span class="check_box_text">油耗量&nbsp;&nbsp;&nbsp;&nbsp;</span>
											</label>
											<label>
												<input type="checkbox" value="cost"  name="exportItems" checked /><span class="check_box_text">油费金额</span>
											</label>
											<label>
												<input type="checkbox" value="averageSpeed"  name="exportItems" checked /><span class="check_box_text">平均速度</span>
											</label>
											<label>
												<input type="checkbox" value="drivingBehavior"  name="exportItems" checked /><span class="check_box_text">驾驶行为</span>
											</label>				
										</span>
	    		</td>
	    	</tr>
	    </table>	
	</from>
</div>
<iframe name="downloadReport_iframe"></iframe>