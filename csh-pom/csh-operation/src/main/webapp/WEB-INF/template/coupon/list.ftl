<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.coupon.list")}</title>
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
						<a ><i class="fa fa-user"></i> ${message("csh.main.coupon")}</a> 
						<span class="divider">/</span> 
						<span  class="bread-current"><i class="fa fa-list"></i>${message("csh.coupon.list")}(${message("csh.page.total", page.total)})</span>
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
										  <button type="button" id="addButton" class="btn btn-default"><i class="fa fa-plus"></i>&nbsp;&nbsp;${message("csh.common.add")}</button>
										</div>
										<!--
										<div class="btn-group operationButton">
										  <button type="button" id="deleteButton" class="btn btn-default disabled"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;${message("csh.common.delete")}</button>
										</div>
										-->
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
								          <li [#if page.searchProperty == "remark" ] selected="selected" class="active" [/#if] title="remark"><a href="#">${message("csh.coupon.remark")}</a></li>
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
						                  <div class="pull-left"><i class="fa fa-list"></i>${message("csh.main.coupon")}</div>
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
														<a href="javascript:;" class="sort" name="remark">${message("csh.coupon.remark")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="amount">${message("csh.coupon.amount")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="type">${message("csh.coupon.type")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="overDueType">${message("csh.coupon.overDueType")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="sendType">${message("csh.coupon.sendType")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="isEnabled">${message("csh.coupon.isEnabled")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="overDueDay">${message("csh.coupon.overDueDay")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="overDueTime">${message("csh.coupon.overDueTime")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="deadlineTime">${message("csh.coupon.deadlineTime")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="counts">${message("csh.coupon.counts")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="remainNum">${message("csh.coupon.remainNum")}</a>
													</th>
													<th>
														<span>${message("csh.common.handle")}</span>
													</th>
												</tr>
											</thead>
											<tbody>
												[#list page.content as coupon]
												<tr>
													<td>
														<input type="checkbox"  name="ids" value="${coupon.id}" />
													</td>
													<td>
														${coupon.remark}
													</td>
													<td>
														${coupon.amount}
													</td>
													<td>
														[#if coupon.type??]
															${message("csh.commonEnum.CouponType."+coupon.type)}
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if coupon.overDueType??]
															${message("csh.commonEnum.CouponOverDueType."+coupon.overDueType)}
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if coupon.sendType??]
															${message("csh.commonEnum.CouponSendType."+coupon.sendType)}
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if coupon.isEnabled?? && coupon.isEnabled]
															启用
														[#elseif coupon.isEnabled?? && !coupon.isEnabled]
															禁用
														[/#if]
													</td>
													<td>
														[#if coupon.overDueDay??]
															${coupon.overDueDay}
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if coupon.overDueTime??]
															${coupon.overDueTime}
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if coupon.deadlineTime??]
															${coupon.deadlineTime}
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if coupon.counts??]
															${coupon.counts}
														[#else]
															-
														[/#if]
													</td>
													<td>
														[#if coupon.remainNum??]
															${coupon.remainNum}
														[#else]
															-
														[/#if]
													</td>
													<td>
														<a href="edit.jhtml?id=${coupon.id}" title="${message("csh.common.edit")}"><i class="fa fa-pencil-square-o"></i></a>
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
</body>
</html>