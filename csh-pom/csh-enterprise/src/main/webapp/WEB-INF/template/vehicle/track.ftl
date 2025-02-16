<script src="${base}/resources/modules/vehicleTrack.js"></script>
<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" style="width:250px" title="${message("csh.vehicle.search")}">
			<fieldset style="padding:5px 0px 4px 4px;margin:0px">
			        <form id="track_vehicle_search_form" class="search-form">
						<div class="search-item">
						   <label>${message("csh.vehicle.plate")}:</label>
						   <input type="text" class="easyui-textbox"  id="vehiclePlateSearch" name="plateSearch" validtype="length[0,10]" style="width:60px;"/>
						</div>
					</form>
					<div class="search-item">
				  	  <button id="track_vehicle_search_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-search'">${message("csh.search")}</button>
				    </div>
				  
				</fieldset>
                <table id="trackVehicleSearch-table-list"></table>
		</div>
		<div data-options="region:'center'" >
				<div class="easyui-panel" style="background:#fafafa;" data-options="border:false">
						  <fieldset>
								  	<legend>${message("csh.vehicleTrack.condition.search")}</legend>
								    <form id="track_search_form" class="search-form">
										<div class="search-item">
										    <label>${message("csh.vehicleTrack.time")}:</label>
										    <input type="text" class="Wdate" id="trackSearchDate" name="searchDate" readonly="readonly" onclick="WdatePicker({});" />
										</div>
										
										<input type="hidden" name="vehicleID" id="track_vehicleID">
									</form>
									<div class="search-item">
								  	  	<button id="track_search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">${message("csh.search")}</button>
								    </div>
								    <div class="search-item">
								  		<button id="track_export_btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-redo'">${message("csh.button.export")}</button>
								     </div>
								  </fieldset>
								<div id="vehicleTrack" style="height:900px;width:99%">
									<table id="track-table-list"></table>
									<div id="vehicleTrackMap"/>
								</div>
				</div>
		</div>
</div>
<div id="trackExportDialog">
	<form id="trackExport_form" method="post" class="form-table">
		<div class="search-item">
		    <label> ${message("csh.vehicleTrack.startTime")}:</label>
		    <input type="text" class="Wdate" id="trackExportStartDate" readonly="readonly" name="beginDate"  onclick="WdatePicker({maxDate: '#F{$dp.$D(\'trackExportEndDate\')}'});" />
		</div>
		<div class="search-item">
		    <label>${message("csh.vehicleTrack.endTime")}:</label>
		   	<input type="text" class="Wdate" id="trackExportEndDate" readonly="readonly" name="endDate" onclick="WdatePicker({minDate: '#F{$dp.$D(\'trackExportStartDate\')}'});"/>
		</div>
		<input type="hidden" name="vehicleID" id="track_export_vehicleID">
	</form>
</div>