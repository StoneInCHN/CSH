<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.vehicle.details")}</title>
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.vehicle")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.vehicle.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.vehicle.details")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.vehicle.details")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
						<table class="input tabContent">
                     		<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.vehicleNo")}:
								</th>
								<td>
									${vehicle.vehicleNo}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.color")}:
								</th>
								<td>
									${vehicle.color}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.device")}:
								</th>
								<td>
									${vehicle.device}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.drivingLicense")}:
								</th>
								<td>
									${vehicle.drivingLicense}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.isDefault")}:
								</th>
								<td>
									${vehicle.isDefault}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.plate")}:
								</th>
								<td>
									${vehicle.plate}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.vin")}:
								</th>
								<td>
									${vehicle.vin}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.dashboardMileage")}:
								</th>
								<td>
									${vehicle.dashboardMileage}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.dashboardBV")}:
								</th>
								<td>
									${vehicle.dashboardBV}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.dashboradOil")}:
								</th>
								<td>
									${vehicle.dashboradOil}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.vehicleFullBrand")}:
								</th>
								<td>
									[#if vehicle.vehicleFullBrand??]
										<a href="../vehicleBrandDetail/details.jhtml?id=${vehicle.vehicleBrandDetail.id}" target="2">${vehicle.vehicleFullBrand}</a>
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.trafficInsuranceExpiration")}:
								</th>
								<td>
									[#if vehicle.trafficInsuranceExpiration??]
										${vehicle.trafficInsuranceExpiration}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.commercialInsuranceExpiration")}:
								</th>
								<td>
									[#if vehicle.commercialInsuranceExpiration??]
										${vehicle.commercialInsuranceExpiration}
									[/#if]
								</td>
							</tr>
							
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.produceDate")}:
								</th>
								<td>
									${vehicle.produceDate}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.vehicle.endUser")}:
								</th>
								<td>
									[#if vehicle.endUser??]
										<a href="../endUser/details.jhtml?id=${vehicle.endUser.id}" target="3">${vehicle.endUser.userName}</a>
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