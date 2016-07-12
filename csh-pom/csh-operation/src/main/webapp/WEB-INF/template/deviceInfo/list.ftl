<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.deviceInfo.list")}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0 user-scalable=no,">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/bootstrap-theme.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/dialog.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/list.css" rel="stylesheet" type="text/css" />
<style>
	#allmap{height:400px;width:100%;}
</style>
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="${base}/resources/js/html5shim.js"></script>
  <![endif]-->
</head>
<body>
<div class="mainbar">
				<div class="page-head">
					<div class="bread-crumb">
						<a ><i class="fa fa-user"></i> ${message("csh.main.deviceInfo")}</a> 
						<span class="divider">/</span> 
						<span  class="bread-current"><i class="fa fa-list"></i>${message("csh.deviceInfo.list")}(${message("csh.page.total", page.total)})</span>
					</div>
					<div class="clearfix"></div>
				</div>
			
			<form id="listForm" action="list.jhtml" method="get">
				<input type="hidden" id="deviceStatus" name="deviceStatus" value="${deviceStatus}" />
				  <div class="container operation">
					<div class="row">
						  <div class="col-xs-9 col-md-9 col-lg-9">
						  		<ul class="nav">
									 <li class="pull-left">
										<div class="btn-group operationButton">
										  <button type="button" id="addButton" class="btn btn-default"><i class="fa fa-plus"></i>&nbsp;&nbsp;${message("csh.common.add")}</button>
										</div>
										<div class="btn-group operationButton">
										  <button type="button" id="batchdAddButton" class="btn btn-default"><i class="fa fa-plus"></i>&nbsp;&nbsp;${message("csh.deviceInfo.batchAdd")}</button>
										</div>
										<div class="btn-group operationButton">
										  <button type="button" id="deleteButton" class="btn btn-default disabled"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;${message("csh.common.delete")}</button>
										</div>
										<div class="btn-group operationButton">
										  <button type="button" id="refreshButton" class="btn btn-default"><i class="fa fa-refresh"></i>&nbsp;&nbsp;${message("csh.common.refresh")}</button>
										</div>
										<div class="btn-group operationButton">
											<button type="button" id="deviceProvide"  class="btn btn-default disabled"><i class="fa fa-wrench"></i>${message("csh.deviceInfo.deviceProvide")}</button>
										</div>
									</li>
									  <li role="presentation" class="dropdown pull-right">
										    <a id="pageSizeSelect" aria-expanded="false" role="button" aria-haspopup="true" data-toggle="dropdown" class="dropdown-toggle" href="#">
										      ${message("csh.page.pageSize")} <span class="caret"></span>
										    </a>
										    <ul id="pageSizeOption" class="dropdown-menu" role="menu" aria-labelledby="pageSizeSelect">
										     	<li>
													<a href="javascript:;"[#if page.pageSize == 10] class="active"[/#if] val="10">10</a>
												</li>
												<li>
													<a href="javascript:;"[#if page.pageSize == 20] class="active"[/#if] val="20">20</a>
												</li>
												<!--
												<li>
													<a href="javascript:;"[#if page.pageSize == 50] class="active"[/#if] val="50">50</a>
												</li>
												<li>
													<a href="javascript:;"[#if page.pageSize == 100] class="active"[/#if] val="100">100</a>
												</li>
												-->
										    </ul>
									  </li>
										<li role="presentation" class="dropdown pull-right ">
											<a href="javascript:;" id="filterSelect" aria-expanded="false" role="button" aria-haspopup="true" data-toggle="dropdown" class="dropdown-toggle" href="#">
													${message("csh.deviceInfo.deviceStatus.filter")}<span class="caret"></span>
											</a>
											<ul id="filterOption" class="dropdown-menu" role="menu" aria-labelledby="filterSelect">
												<li>
													<a href="javascript:;" name="deviceStatus" val="" [#if deviceStatus == null] class="checked"[/#if]>${message("csh.deviceInfo.deviceStatus.all")}</a>
												</li>
												<li>
													<a href="javascript:;" name="deviceStatus" val="INITED" [#if deviceStatus == "INITED"] class="checked"[/#if]>${message("csh.deviceInfo.deviceStatus.INITED")}</a>
												</li>
												<li>
													<a href="javascript:;" name="deviceStatus" val="SENDOUT" [#if deviceStatus == "SENDOUT"] class="checked"[/#if]>${message("csh.deviceInfo.deviceStatus.SENDOUT")}</a>
												</li>
												<li>
													<a href="javascript:;" name="deviceStatus" val="STORAGEOUT" [#if deviceStatus == "STORAGEOUT"] class="checked"[/#if]>${message("csh.deviceInfo.deviceStatus.STORAGEOUT")}</a>
												</li>
												<li>
													<a href="javascript:;" name="deviceStatus" val="SALEOUT" [#if deviceStatus == "SALEOUT"] class="checked"[/#if]>${message("csh.deviceInfo.deviceStatus.SALEOUT")}</a>
												</li>
												<li>
													<a href="javascript:;" name="deviceStatus" val="REFUNDED" [#if deviceStatus == "REFUNDED"] class="checked"[/#if]>${message("csh.deviceInfo.deviceStatus.REFUNDED")}</a>
												</li>
											</ul>
										</li>
									</ul>
						  </div>
						  <div class="col-xs-3 col-md-3 col-lg-3">
						  		<div class="input-group">
								      <div class="input-group-btn">
								        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">${message("csh.common.choose")} <span class="caret"></span></button>
								        <ul class="dropdown-menu menuWrap" id="searchPropertyOption" role="menu">
								          <li [#if page.searchProperty == "deviceNo" ] selected="selected" class="active" [/#if] title="deviceNo"><a href="#">${message("csh.deviceInfo.deviceNo")}</a></li>
								           <li [#if page.searchProperty == "simNo" ] selected="selected" class="active" [/#if] title="simNo"><a href="#">${message("csh.deviceInfo.simNo")}</a></li>
								        </ul>
								      </div>
								      <input type="text" class="form-control" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
							    </div>
						  </div>
					</div>
				</div>
				
				<div class="matter">
					<div class="container">
						<div class="row">
			              <div class="col-md-12">
			                <div class="widget">
									 <div class="widget-head">
						                  <div class="pull-left"><i class="fa fa-list"></i>${message("csh.main.deviceInfo")}</div>
						                  <div class="clearfix"></div>
						              </div>
						              <div class="widget-content">
										<table id="listTable" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="check">
														<input type="checkbox" id="selectAll" />
													</th>
													<th>
														<a href="javascript:;" class="sort" name="bindTime">${message("csh.deviceInfo.bindTime")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="unBindTime">${message("csh.deviceInfo.unBindTime")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="deviceNo">${message("csh.deviceInfo.deviceNo")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="simNo">${message("csh.deviceInfo.simNo")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="type">${message("csh.deviceInfo.type")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="deviceStatus">${message("csh.deviceInfo.deviceStatus")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="bindStatus">${message("csh.deviceInfo.bindStatus")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="distributor">${message("csh.deviceInfo.distributorId")}</a>
													</th>
													<th>
														${message("csh.deviceInfo.lastUpdateDate")}
													</th>
													<th>
														${message("csh.deviceInfo.currentPosition")}
													</th>
													<th>
														<span>${message("csh.common.handle")}</span>
													</th>
												</tr>
											</thead>
											<tbody>
												[#list page.content as deviceInfo]
												<tr>
													<td>
														[#if deviceInfo.deviceStatus?? && deviceInfo.deviceStatus == "INITED"]
														  <input type="checkbox"  name="ids"  title="${message("csh.role.deleteSystemNotAllowed")}"  value="${deviceInfo.id}" />
														[#else]
															<input type="checkbox"  name="ids"  title="${message("csh.role.deleteSystemNotAllowed")}" disabled="disabled" value="${deviceInfo.id}" />
														[/#if]
													</td>
													<td>
														[#if deviceInfo.bindTime??]
															<span title="${deviceInfo.bindTime?string("yyyy-MM-dd HH:mm:ss")}">${deviceInfo.bindTime}</span>
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if deviceInfo.unBindTime??]
															<span title="${deviceInfo.unBindTime?string("yyyy-MM-dd HH:mm:ss")}">${deviceInfo.unBindTime}</span>
														[#else]
															-
														[/#if]
													</td>
													<td class="deviceNo-td" data-deviceNo="${deviceInfo.deviceNo}">
														${deviceInfo.deviceNo}
													</td>
													<td>
														${deviceInfo.simNo}
													</td>
													<td>
														${deviceInfo.type.name}
													</td>
													<td>
														[#if deviceInfo.deviceStatus??]
														   ${message("csh.deviceInfo.deviceStatus."+deviceInfo.deviceStatus)}
														[/#if]
													</td>
													<td>
														[#if deviceInfo.bindStatus??]
														   ${message("csh.deviceInfo.bindStatus."+deviceInfo.bindStatus)}
														[/#if]
													</td>
													<td>
														[#if deviceInfo.distributor ??]
															${deviceInfo.distributor.distributorName}
														[#else]
															--
														[/#if]
													</td>
													<td class="lastUpdateDate-td">
														--
													</td>
													<td class="currentPosition-td">
														<i class="fa fa-eye" style="display:none"></i>
													</td>
													<td>
														<a href="edit.jhtml?id=${deviceInfo.id}" title="${message("csh.common.edit")}"><i class="fa fa-pencil-square-o"></i></a>
														<a href="details.jhtml?id=${deviceInfo.id}" title="${message("csh.common.details")}"><i class="fa fa-eye"></i></a>
													</td>
												</tr>
											</tbody>
											[/#list]
										</table>
										<div class="widget-foot">
					                       [@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
												[#include "/include/pagination.ftl"]
										   [/@pagination]
				                   		 </div>
									</div>
								</div>
							</div>
						</div>
					 </div>
				</div>
			</form>
</div>
<div class="modal fade" id="currentPositionModal" tabindex="-1" role="dialog" aria-labelledby="operationModalLabel" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title"></h4>
	      </div>
	      <div class="modal-body" style="margin:0;padding:0">
	      	<div id="allmap"></div>
	      </div>
	      <div class="modal-footer">
	        <button  type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	      </div>
	    </div>
	  </div>
	</div>
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=D767b598a9f1e43c3cadc4fe26cdb610"></script>
<script type="text/javascript">
$().ready(function() {

	var map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(116.331398,39.897445),13);
	map.enableScrollWheelZoom(true);
	var $listForm = $("#listForm");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	var $deviceProvide = $("#deviceProvide");
	var $deviceStatus = $("#deviceStatus");
	var $batchdAddButton = $("#batchdAddButton");
	var $deviceNoTd = $(".deviceNo-td");
	var $currentPositionModal = $("#currentPositionModal");
	$batchdAddButton.click(function(){
		location.href="batchAdd.jhtml";
	})
	
	$deviceProvide.click(function(){
		var $deviceBinding = window.parent.$('#operationModal');
		var $operationModalIframe= window.parent.$('#operationModalIframe');
		$deviceBinding.find(".modal-title").text("设备发放");
		$deviceBinding.modal("show");
		$deviceBinding.attr("data-ids","&"+$("#listTable input[name='ids']:checked").serialize());
		$operationModalIframe.attr("src","${base}/console/deviceInfo/deviceProvide.jhtml");
		$operationModalIframe.css("height",380);
	
	})
	
	// 筛选
	$filterSelect.mouseover(function() {
		var $this = $(this);
		var offset = $this.offset();
		var $menuWrap = $this.closest("div.menuWrap");
		var $popupMenu = $menuWrap.children("div.popupMenu");
		$popupMenu.css({left: offset.left - 20, top: offset.top + $this.height() + 2}).show();
		$menuWrap.mouseleave(function() {
			$popupMenu.hide();
		});
	});
	
	// 筛选选项
	$filterOption.click(function() {
		var $this = $(this);
		var $dest = $("#" + $this.attr("name"));
		if ($this.hasClass("checked")) {
			$dest.val("");
		} else {
			$dest.val($this.attr("val"));
		}
		$listForm.submit();
		return false;
	});

	$deviceNoTd.each(function(i){
	  var $this = $(this);
	  //ajax 

	  $.ajax({
        url: 'http://139.129.5.114:20001/obd-data/tenantVehicleData/vehicleCoordinate.jhtml',
        dataType: "jsonp",
        data:{"deviceId": $this.attr("data-deviceNo")},
        jsonp: "callback",
        success: function (res) {
            console.log(res.msg);
            if(res.msg){
            	$this.parent().find(".lastUpdateDate-td").text(res.msg.createtime);
            	$currentPosition.attr("data-longitude",res.msg.lon);
           		$currentPosition.attr("data-latitude",res.msg.lat);
            }
        }
   	 })
	  var $currentPosition = $this.parent().find(".currentPosition-td");
	  $currentPosition.find("i").show().bind("click",function(){
	   $currentPositionModal.modal("show");
	  var longitude=$currentPosition.attr("data-longitude");
	  var latitude=$currentPosition.attr("data-latitude");
		setTimeout(function(){
			if(longitude&&latitude){
			map.clearOverlays(); 
			var new_point = new BMap.Point(longitude,latitude);
			var marker = new BMap.Marker(new_point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
			map.panTo(new_point);   
		}
		}, 1000);
	  })
	  
	});
	
});
</script>
</body>
</html>