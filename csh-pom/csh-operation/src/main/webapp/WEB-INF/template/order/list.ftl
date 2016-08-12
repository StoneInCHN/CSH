<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.order.list")}</title>
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
						<a ><i class="fa fa-user"></i> ${message("csh.main.order")}</a> 
						<span class="divider">/</span> 
						<span  class="bread-current"><i class="fa fa-list"></i>${message("csh.order.list")}(${message("csh.page.total", page.total)})</span>
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
									</ul>
						  </div>
						  <div class="col-xs-3 col-md-3 col-lg-3">
						  		<div class="input-group">
								      <div class="input-group-btn">
								        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">${message("csh.common.choose")} <span class="caret"></span></button>
								        <ul class="dropdown-menu menuWrap" id="searchPropertyOption" role="menu">
								          <li [#if page.searchProperty == "sn" ] selected="selected" class="active" [/#if] title="sn"><a href="#">${message("csh.order.sn")}</a></li>
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
						                  <div class="pull-left"><i class="fa fa-list"></i>${message("csh.main.order")}</div>
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
														<a href="javascript:;" class="sort" name="sn">${message("csh.order.sn")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="orderStatus">${message("csh.order.orderStatus")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="paymentStatus">${message("csh.order.paymentStatus")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="shippingStatus">${message("csh.order.shippingStatus")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="afterSalesStatus">${message("csh.order.afterSalesStatus")}</a>
													</th>														
													<th>
														<a href="javascript:;" class="sort" name="consignee">${message("csh.order.consignee")}</a>
													</th>	
													<th>
														<a href="javascript:;" class="sort" name="phone">${message("csh.order.phone")}</a>
													</th>															
													<th>
														<span>${message("csh.common.handle")}</span>
													</th>
												</tr>
											</thead>
											<tbody>
												[#list page.content as order]
												<tr>
													<td>
														<input type="checkbox"  name="ids" value="${order.id}" />
													</td>
													<td>
														${order.sn}
													</td>
													<td>
														[#if order.orderStatus??]
															${message("csh.order.orderStatus."+order.orderStatus)}
														[/#if]
													</td>
													<td>
														[#if order.paymentStatus??]
															${message("csh.order.paymentStatus."+order.paymentStatus)}
														[/#if]
													</td>
													<td>
														[#if order.shippingStatus??]
															${message("csh.order.shippingStatus."+order.shippingStatus)}
														[/#if]
													</td>
													<td>
														[#if order.afterSalesStatus??]
															${message("csh.order.afterSalesStatus."+order.afterSalesStatus)}
														[#else]
															-
														[/#if]
													</td>													
													<td>
														${order.consignee}
													</td>
													<td>
														${order.phone}
													</td>
													<td>
														<a href="details.jhtml?id=${order.id}" title="${message("csh.common.details")}"><i class="fa fa-eye"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;
														[#if order.paymentStatus == 'refunded' || order.paymentStatus == 'partialRefunds']
															 <a href="viewRefunds.jhtml?orderId=${order.id}" title="${message("csh.refunds.view")}">${message("csh.refunds.view")}</a>
														[#else]
															[#if order.afterSalesStatus == 'approvedRefund' || order.afterSalesStatus == 'approvedPartialRefund']
															<a href="addRefunds.jhtml?orderId=${order.id}" title="${message("csh.order.refund")}"><i class="fa fa-jpy"></i></a>
															[/#if]
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
</body>
</html>