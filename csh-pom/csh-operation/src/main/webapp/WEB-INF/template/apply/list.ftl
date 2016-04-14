<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.apply.list")}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/bootstrap-theme.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/dialog.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/list.css" rel="stylesheet" type="text/css" />
  <!-- HTML5 Support for IE -->
  <!--[if lt IE 9]>
  <script src="${base}/resources/js/html5shim.js"></script>
  <![endif]-->
</head>
<body>
<div class="mainbar">
				<div class="page-head">
					<div class="bread-crumb">
						<a ><i class="fa fa-user"></i> ${message("csh.main.admin")}</a> 
						<span class="divider">/</span> 
						<span  class="bread-current"><i class="fa fa-list"></i>${message("csh.apply.list")}(${message("csh.page.total", page.total)})</span>
					</div>
					<div class="clearfix"></div>
				</div>
			
			<form id="listForm" action="list.jhtml" method="get">
				<input type="hidden" id="applyStatus" name="applyStatus" value="${applyStatus}" />
				  <div class="container operation">
					<div class="row">
						  <div class="col-xs-9 col-md-9 col-lg-9">
						  		<ul class="nav">
									 <li class="pull-left">
										<div class="btn-group operationButton">
										  <button type="button" id="deleteButtonFirst" class="btn btn-default disabled"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;${message("csh.common.delete")}</button>
										</div>
										<div class="btn-group operationButton">
										  <button type="button" id="refreshButton" class="btn btn-default"><i class="fa fa-refresh"></i>&nbsp;&nbsp;${message("csh.common.refresh")}</button>
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
												<li>
													<a href="javascript:;"[#if page.pageSize == 50] class="active"[/#if] val="50">50</a>
												</li>
												<li>
													<a href="javascript:;"[#if page.pageSize == 100] class="active"[/#if] val="100">100</a>
												</li>
										    </ul>
									  </li>
									  <li role="presentation" class="dropdown pull-right ">
										<a href="javascript:;" id="filterSelect" aria-expanded="false" role="button" aria-haspopup="true" data-toggle="dropdown" class="dropdown-toggle" href="#">
												${message("csh.apply.applyStatus.filter")}<span class="caret"></span>
										</a>
										<ul id="filterOption" class="dropdown-menu" role="menu" aria-labelledby="filterSelect">
											<li>
												<a href="javascript:;" name="applyStatus" val="" [#if applyStatus == null] class="checked"[/#if]>${message("csh.apply.applyStatus.all")}</a>
											</li>
											<li>
												<a href="javascript:;" name="applyStatus" val="AUDIT_WAITING" [#if applyStatus == "AUDIT_WAITING"] class="checked"[/#if]>${message("csh.apply.applyStatus.AUDIT_WAITING")}</a>
											</li>
											<li>
												<a href="javascript:;" name="applyStatus" val="AUDIT_PASSED" [#if applyStatus == "AUDIT_PASSED"] class="checked"[/#if]>${message("csh.apply.applyStatus.AUDIT_PASSED")}</a>
											</li>
											<li>
												<a href="javascript:;" name="applyStatus" val="AUDIT_FAILED" [#if applyStatus == "AUDIT_FAILED"] class="checked"[/#if]>${message("csh.apply.applyStatus.AUDIT_FAILED")}</a>
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
								          <li [#if page.searchProperty == "tenantName"] selected="selected" class="active" [/#if] title="tenantName"><a href="#">${message("csh.apply.tenantName")}</a></li>
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
						                  <div class="pull-left"><i class="fa fa-list"></i>${message("csh.main.apply")}</div>
						                  <div class="widget-icons pull-right">
						                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
						                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
						                  </div>  
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
														<a href="javascript:;" class="sort" name="tenantName">${message("csh.apply.tenantName")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="contactPhone">${message("csh.apply.contactPhone")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="contactPerson">${message("csh.apply.contactPerson")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="address">${message("csh.apply.address")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="area">${message("csh.apply.area")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="license">${message("csh.apply.license")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="photo">${message("csh.apply.photo")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="createDate">${message("csh.common.createDate")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="applyStatus">${message("csh.apply.applyStatus")}</a>
													</th>
													<th>
														<span>${message("csh.common.handle")}</span>
													</th>
												</tr>
											</thead>
											<tbody>
												[#list page.content as tenantApply]
												<tr>
													<td>
														<input type="checkbox"  name="ids" value="${tenantApply.id}" />
													</td>
													<td>
														${tenantApply.tenantName}
													</td>
													<td>
														${tenantApply.contactPhone}
													</td>
													<td>
														${tenantApply.contactPerson}
													</td>
													<td>
														${tenantApply.address}
													</td>
													<td>
														${tenantApply.area}
													</td>
													<td>
														<a href="${base}${tenantApply.license}" target="1024"><img src="${base}${tenantApply.license}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("csh.apply.license")}"></a>
													</td>
													<td>
														<a href="${base}${tenantApply.photo}" target="1024"><img src="${base}${tenantApply.photo}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("csh.apply.photo")}"></a>
													</td>
													<td>
														<span title="${tenantApply.createDate?string("yyyy-MM-dd HH:mm:ss")}">${tenantApply.createDate}</span>
													</td>
													<td>
														${message("csh.apply.applyStatus."+tenantApply.applyStatus)}
													</td>
													<td>
														[#if  tenantApply.applyStatus =="AUDIT_WAITING"]
															<a href="edit.jhtml?id=${tenantApply.id}" title="${message("csh.common.edit")}"><i class="fa fa-pencil-square-o"></i></a>
														[#else]
															<a href="details.jhtml?id=${tenantApply.id}" title="${message("csh.common.details")}"><i class="fa fa-eye"></i></a>
														[/#if]
														
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
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $listForm = $("#listForm");
	var $filterSelect = $("#filterSelect");
	var $filterOption = $("#filterOption a");
	
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
	
});
</script>
</body>
</html>