<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.distributorDeductClearingRecord.list")}</title>
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
						<a ><i class="fa fa-user"></i> ${message("csh.main.distributorDeductClearingRecord")}</a> 
						<span class="divider">/</span> 
						<span  class="bread-current"><i class="fa fa-list"></i>${message("csh.distributorDeductClearingRecord.list")}(${message("csh.page.total", page.total)})</span>
					</div>
					<div class="clearfix"></div>
				</div>
			
			<form id="listForm" action="list4distributor.jhtml" method="get">
				  <div class="container operation">
					<div class="row">
						  <div class="col-xs-9 col-md-9 col-lg-9">
						  		<ul class="nav">
									 <li class="pull-left">
										<div class="btn-group operationButton">
										  <button type="button" id="refreshButton" class="btn btn-default"><i class="fa fa-refresh"></i>&nbsp;&nbsp;${message("csh.common.refresh")}</button>
										</div>
										<div class="btn-group operationButton">
										  <button type="button" id="submitClearingRecord" class="btn btn-default"><i class="fa fa-jpy"></i>&nbsp;&nbsp;${message("csh.common.submitClearingRecord")}</button>
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
									  <li class="dateClass pull-right">
											${message("csh.operate.log.date")}: <input type="text" id="beginDate" name="beginDate" class="text Wdate" value="${(beginDate?string('yyyy-MM-dd'))!}" onfocus="WdatePicker({maxDate: '#F{$dp.$D(\'endDate\')}'});" />
											~~ <input type="text"  id="endDate" name="endDate" class="text Wdate" value="${(endDate?string('yyyy-MM-dd'))!}" onfocus="WdatePicker({minDate: '#F{$dp.$D(\'beginDate\')}'});" />
										</li>
									</ul>
						  </div>
						  <div class="col-xs-3 col-md-3 col-lg-3">
						  		<div class="input-group">
								      <div class="input-group-btn">
								        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">${message("csh.common.choose")} <span class="caret"></span></button>
								        <ul class="dropdown-menu menuWrap" id="searchPropertyOption" role="menu">
								          <li [#if page.searchProperty == "clearingSn" ] selected="selected" class="active" [/#if] title="clearingSn"><a href="#">${message("csh.distributorDeductClearingRecord.clearingSn")}</a></li>
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
						                  <div class="pull-left"><i class="fa fa-list"></i>${message("csh.main.distributorDeductClearingRecord")}</div>
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
													<th>
														<a href="javascript:;" class="sort" name="clearingSn">${message("csh.distributorDeductClearingRecord.clearingSn")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="tenantInfo">${message("csh.distributorDeductClearingRecord.tenantInfo")}</a>
													</th>
													<th>
														${message("csh.distributorDeductClearingRecord.clearingStatus")}
													</th>
													<th>
														<a href="javascript:;" class="sort" name="amountOfCurrentPeriod">${message("csh.distributorDeductClearingRecord.amountOfCurrentPeriod")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="createDate">${message("csh.common.createDate")}</a>
													</th>
													<th>
														<span>${message("csh.common.handle")}</span>
													</th>
												</tr>
											</thead>
											<tbody>
												[#list page.content as distributorDeductClearingRecord]
												<tr>
													<td>
														${distributorDeductClearingRecord.clearingSn}
													</td>
													<td>
														[#if distributorDeductClearingRecord.distributor??]
															${distributorDeductClearingRecord.distributor.distributorName}
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if distributorDeductClearingRecord.clearingStatus??]
															${message("csh.commonEnum.ClearingStatus."+distributorDeductClearingRecord.clearingStatus)}
														[#else]
															-
														[/#if]	
													</td>
													<td>
														${distributorDeductClearingRecord.amountOfCurrentPeriod}
													</td>
													<td>
														<span title="${distributorDeductClearingRecord.createDate?string("yyyy-MM-dd HH:mm:ss")}">${distributorDeductClearingRecord.createDate}</span>
													</td>
													<td>
														<a href="details4distributor.jhtml?id=${distributorDeductClearingRecord.id}" title="${message("csh.common.details")}"><i class="fa fa-eye"></i></a>
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
<script type="text/javascript" src="${base}/resources/js/jquery.bootstrap.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
	$(function(){
		var $submitClearingRecord = $("#submitClearingRecord");
		var $operationModal = window.parent.$('#operationModal');
		var $operationModalIframe= window.parent.$('#operationModalIframe');
		var $operationModalOK = window.parent.$('#operationModalOK')
		$submitClearingRecord.click(function(){
			//$.messager.popup("结算订单生成中");
			$operationModal.find(".modal-title").text("结算单预览");
			$operationModal.modal("show");
			$operationModalIframe.attr("src","${base}/console/distributorDeductClearingRecord/viewClearingRecord.jhtml");
			$operationModalIframe.css("height",380);
			$operationModalIframe.css("width",700);
			$operationModal.find(".modal-dialog").css("width",705)
		})
		$operationModalOK.click(function(){
			 $.ajax({
			   type: "POST",
			   url: "applyClearingRecord.jhtml",
			   data:{},
			   success: function(result){
			     // $.messager.popup(result.content);
			     alert(result.content)
			   }
			});
			
			return false;
		})
	})
</script>
</body>
</html>