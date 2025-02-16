<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.distributor.list")}</title>
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
			<form id="listForm" action="selectDistributor.jhtml" method="get">
				  <div class="container operation">
					<div class="row">
						  <div class="col-xs-12 col-md-12 col-lg-12">
						  		<div class="input-group">
								      <div class="input-group-btn">
								        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">${message("csh.distributor.distributorName")} <span class="caret"></span></button>
								        <ul class="dropdown-menu menuWrap" id="searchPropertyOption" role="menu">
								          <li [#if page.searchProperty == "distributorName" || page.searchProperty ==null] selected="selected" class="active" [/#if] title="distributorName"><a href="#">${message("csh.distributor.distributorName")}</a></li>
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
						              <div class="widget-content">
										<table id="listTable" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="check">
														<input type="checkbox" id="selectAll"  disabled="disabled"/>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="distributorName">${message("csh.distributor.distributorName")}</a>
													</th>
												
													<th>
														<a href="javascript:;" class="sort" name="distributorPhone">${message("csh.distributor.distributorPhone")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="distributorAddress">${message("csh.distributor.distributorAddress")}</a>
													</th>
													<th>
														<a href="javascript:;" class="sort" name="area">${message("csh.distributor.area")}</a>
													</th>
												</tr>
											</thead>
											<tbody>
												[#list page.content as distributor]
												<tr>
													<td>
														<input type="checkbox"  name="distributorIds" value="${distributor.id}" data-name="${distributor.distributorName}"/>
													</td>
													<td>
														${distributor.distributorName}
													</td>
												
													<td>
														${distributor.distributorPhone}
													</td>
													<td>
														${distributor.distributorAddress}
													</td>
													<td>
														[#if distributor.area??]${distributor.area}[/#if]
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
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
<script type="text/javascript">
	$(function(){
		var $distributorIds = $("#listTable input[name='distributorIds']");
		var $operationModalOK = window.parent.$("#operationModalOK");
		var iframe = window.parent.frames["iframe"].document;
		$distributorIds.click( function() {
			var $this = $(this);
			if ($this.prop("checked")) {
				$distributorIds.prop("checked", false);
				$this.prop("checked", true);
			} else {
				$distributorIds.prop("checked", false);
			}
		});
		
	  $operationModalOK .click(function(){
	  	var $checkedIds = $("#listTable input[name='distributorIds']:checked");
	  	if($checkedIds.length <1){
	  		return ;
	  	}
	  	var distributorId = $($checkedIds[0]).val();
	  	var distributorName = $($checkedIds[0]).attr("data-name");
	  	$(iframe).find("#distributorName").text(distributorName);
	  	$(iframe).find("#distributorId").val(distributorId);
	  	
	  });
	})
</script>


</body>
</html>