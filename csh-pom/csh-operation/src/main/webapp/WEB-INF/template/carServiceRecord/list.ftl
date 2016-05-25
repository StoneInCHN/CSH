<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.carServiceRecord.list")}</title>
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
						<a ><i class="fa fa-user"></i> ${message("csh.main.carServiceRecord")}</a> 
						<span class="divider">/</span> 
						<span  class="bread-current"><i class="fa fa-list"></i>${message("csh.carServiceRecord.list")}(${message("csh.page.total", page.total)})</span>
					</div>
					<div class="clearfix"></div>
				</div>
			
			<form id="listForm" action="list.jhtml" method="get">
				  <div class="container operation">
					<div class="row">
						  <div class="col-xs-9 col-md-9 col-lg-9">
						  		<ul class="nav">
									 <li class="pull-left">
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
								          <li [#if page.searchProperty == "recordNo" ] selected="selected" class="active" [/#if] title="recordNo"><a href="#">${message("csh.carServiceRecord.recordNo")}</a></li>
								          <li [#if page.searchProperty == "tenantName" ] selected="selected" class="active" [/#if] title="tenantName"><a href="#">${message("csh.carServiceRecord.tenantName")}</a></li>
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
						                  <div class="pull-left"><i class="fa fa-list"></i>${message("csh.main.carServiceRecord")}</div>
						                  <div class="clearfix"></div>
						              </div>
						              <div class="widget-content">
										<table id="listTable" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>
														<a href="javascript:;" class="sort" name="recordNo">${message("csh.carServiceRecord.recordNo")}</a>
													</th>
													<th>
														${message("csh.carServiceRecord.carService")}
													</th>
													<th>
														${message("csh.carServiceRecord.endUser")}
													</th>
													<th>
														${message("csh.carServiceRecord.paymentType")}
													</th>
													<th>
														<a href="javascript:;" class="sort" name="paymentDate">${message("csh.carServiceRecord.paymentDate")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="chargeStatus">${message("csh.carServiceRecord.chargeStatus")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="price">${message("csh.carServiceRecord.price")}</a>
													</th>
												
													<th>
														<a href="javascript:;" class="sort" name="tenantName">${message("csh.carServiceRecord.tenantName")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="clearingDate">${message("csh.carServiceRecord.clearingDate")}</a>
													</th>
												
												</tr>
											</thead>
											<tbody>
												[#list page.content as carServiceRecord]
												<tr>
													<td>
														${carServiceRecord.recordNo}
													</td>
													<td>
														[#if carServiceRecord.carService??]
															${carServiceRecord.carService.serviceName}
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if carServiceRecord.endUser??]
															${carServiceRecord.endUser.userName}
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if carServiceRecord.paymentType??]
															${message("csh.commonEnum.paymentType."+carServiceRecord.paymentType)}
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if carServiceRecord.paymentDate??]
															<span title="${carServiceRecord.paymentDate?string("yyyy-MM-dd HH:mm:ss")}">${carServiceRecord.paymentDate}</span>
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if  carServiceRecord.chargeStatus?? && carServiceRecord.price?? && carServiceRecord.price == -1 ]
															未定价
														[#elseif  carServiceRecord.chargeStatus?? && carServiceRecord.price?? && carServiceRecord.price != -1]
															${message("csh.commonEnum.ChargeStatus."+carServiceRecord.chargeStatus)}
														[#else]
															-
														[/#if]
														
													</td>
													<td>
														[#if carServiceRecord.price?? && carServiceRecord.price == -1 ]
															0
														[#elseif carServiceRecord.price?? && carServiceRecord.price != -1]
															${carServiceRecord.price}
														[#else]
															-
														[/#if]
													</td>
										
													<td>
														${carServiceRecord.tenantName}
													</td>
													<td>
														[#if carServiceRecord.clearingDate??]
															<span title="${carServiceRecord.clearingDate?string("yyyy-MM-dd HH:mm:ss")}">${carServiceRecord.clearingDate}</span>
														[#else]
															-
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
<script type="text/javascript" src="${base}/resources/js/datePicker/WdatePicker.js"></script>
</body>
</html>