<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.walletRecord.list")}</title>
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
						<a ><i class="fa fa-user"></i> ${message("csh.main.walletRecord")}</a> 
						<span class="divider">/</span> 
						<span  class="bread-current"><i class="fa fa-list"></i>${message("csh.walletRecord.list")}(${message("csh.page.total", page.total)})</span>
					</div>
					<div class="clearfix"></div>
				</div>
			
			<form id="listForm" action="list.jhtml" method="get">
				  <div class="container operation">
					<div class="row">
						  <div class="col-xs-12 col-md-12 col-lg-12">
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
				
					</div>
				</div>
				
				<div class="matter">
					<div class="container">
						<div class="row">
			              <div class="col-md-12">
			                <div class="widget">
									 <div class="widget-head">
						                  <div class="pull-left"><i class="fa fa-list"></i>${message("csh.main.walletRecord")}</div>
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
														${message("csh.walletRecord.wallet")}
													</th>
													<th>
														<a href="javascript:;" class="sort" name="money">${message("csh.walletRecord.money")}</a>
													</th>
											
													<th>
														${message("csh.walletRecord.remark")}
													</th>
													<th>
														<a href="javascript:;" class="sort" name="createDate">${message("csh.common.createDate")}</a>
													</th>
												</tr>
											</thead>
											<tbody>
												[#list page.content as walletRecord]
												<tr>
													<td>
														${walletRecord.wallet.endUser.userName}
													</td>
													<td>
														${walletRecord.money}
													</td>
											
													<td>
														${walletRecord.remark}
													</td>
													<td>
														[#if walletRecord.createDate??]
														<span title="${walletRecord.createDate?string("yyyy-MM-dd HH:mm:ss")}">${walletRecord.createDate}</span>
														[/#if]	
												</td>
												</tr>
												[/#list]
											</tbody>
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