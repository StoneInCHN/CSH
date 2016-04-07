<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.endUser.details")}</title>
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.endUser")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.endUser.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.endUser.details")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.endUser.details")}</div>
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
									${message("csh.endUser.realName")}:
								</th>
								<td>
									${endUser.realName}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.endUser.userName")}:
								</th>
								<td>
									${endUser.userName}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.endUser.nickName")}:
								</th>
								<td>
									${endUser.nickName}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.endUser.age")}:
								</th>
								<td>
									${endUser.age}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.endUser.gender")}:
								</th>
								<td>
									${endUser.gender}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.endUser.mobileNum")}:
								</th>
								<td>
									${endUser.mobileNum}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.endUser.photo")}:
								</th>
								<td>
									${endUser.photo}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.endUser.birthDay")}:
								</th>
								<td>
									${endUser.birthDay}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.endUser.address")}:
								</th>
								<td>
									${endUser.address}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.endUser.qq")}:
								</th>
								<td>
									${endUser.qq}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.endUser.signature")}:
								</th>
								<td>
									[#if endUser.signature??]
										${endUser.signature}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.endUser.vipLevel")}:
								</th>
								<td>
									[#if endUser.vipLevel??]
										${endUser.vipLevel}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.endUser.defaultVehicle")}:
								</th>
								<td>
									[#if endUser.defaultVehicle??]
										${endUser.defaultVehicle.plate}
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.endUser.driverLicense")}:
								</th>
								<td>
									[#if endUser.driverLicense??]
										<a href="../driverLicense/details.jhtml?id=${endUser.driverLicense.id}" target="3">${endUser.driverLicense.sn_no}</a>
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