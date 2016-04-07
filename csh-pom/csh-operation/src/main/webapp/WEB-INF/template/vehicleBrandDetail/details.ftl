<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.vehicleBrandDetail.details")}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.vehicleBrandDetail")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.vehicleBrandDetail.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.vehicleBrandDetail.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.vehicleBrandDetail.edit")}i</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
						<table class="input tabContent">
							<tr>
								<th>
									${message("csh.vehicleBrandDetail.name")}:
								</th>
								<td>
									${vehicleBrandDetail.name}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.vehicleBrandDetail.averageOil")}:
								</th>
								<td>
									${vehicleBrandDetail.averageOil}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.vehicleBrandDetail.disp")}:
								</th>
								<td>
									${vehicleBrandDetail.disp}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.vehicleBrandDetail.canGetmileage")}:
								</th>
								<td>
								 	[#if vehicleBrandDetail.canGetmileage?string("yes","no") =="yes"] 
										${message("csh.vehicleBrandDetail.support.yes")}
									[#elseif vehicleBrandDetail.canGetmileage?string("yes","no") =="no"] 
										${message("csh.vehicleBrandDetail.support.no")}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicleBrandDetail.canGetoil")}:
								</th>
								<td>
									[#if vehicleBrandDetail.canGetoil?string("yes","no") =="yes"] 
										${message("csh.vehicleBrandDetail.support.yes")}
									[#elseif vehicleBrandDetail.canGetoil?string("yes","no") =="no"] 
										${message("csh.vehicleBrandDetail.support.no")}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.vehicleBrandDetail.maxbv")}:
								</th>
								<td>
									${vehicleBrandDetail.maxbv}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.vehicleBrandDetail.minbv")}:
								</th>
								<td>
									${vehicleBrandDetail.minbv}
								</td>
							</tr>
							
							<tr>
								<th>
									${message("csh.vehicleBrandDetail.canOBD")}:
								</th>
								<td>
									[#if vehicleBrandDetail.canOBD?string("yes","no") =="yes"] 
										${message("csh.vehicleBrandDetail.support.yes")}
									[#elseif vehicleBrandDetail.canOBD?string("yes","no") =="no"] 
										${message("csh.vehicleBrandDetail.support.no")}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.vehicleBrandDetail.oilPerHundred")}:
								</th>
								<td>
									${vehicleBrandDetail.oilPerHundred}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.vehicleBrandDetail.tank")}:
								</th>
								<td>
									${vehicleBrandDetail.tank}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.vehicleBrandDetail.vehicleLine")}:
								</th>
								<td>
									<select name="vehicleLineId">
										${vehicleBrandDetail.vehicleLine.code}&nbsp;&nbsp;&nbsp;${vehicleBrandDetail.vehicleLine.name}
									</select>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.vehicleBrandDetail.status")}:
								</th>
								<td>
										[#if vehicleBrandDetail.status == "ENABLE"]${message("csh.vehicleBrandDetail.status.ENABLE")}
										[#elseif vehicleBrandDetail.status == "DISABLE"]${message("csh.vehicleBrandDetail.status.DISABLE")}[/#if]
								</td>
							</tr>
                     		<tr>
								<th>
									${message("csh.vehicleBrandDetail.oilType")}:
								</th>
								<td>
										[#if vehicleBrandDetail.oilType == "P0"]
											${message("csh.vehicleBrandDetail.oilType.P0")}
										[#elseif vehicleBrandDetail.oilType == "P90"]
											${message("csh.vehicleBrandDetail.oilType.P90")}
										[#elseif vehicleBrandDetail.oilType == "P93"]
											${message("csh.vehicleBrandDetail.oilType.P93")}
										[#elseif vehicleBrandDetail.oilType == "P97"]
											${message("csh.vehicleBrandDetail.oilType.P97")}
										[/#if]
								</td>
							</tr>
						</table>
                  </div>
                </div>
              </div>  
            </div>
          </div>
        </div>
	   </div>
	</div>
	<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
</body>
</html>