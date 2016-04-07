<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.driverLicense.details")}</title>
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.driverLicense")}</a> 
				<span class="divider">/</span> 
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.driverLicense.details")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.driverLicense.details")}</div>
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
									${message("csh.driverLicense.sn_no")}:
								</th>
								<td>
									${driverLicense.sn_no}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.driverLicense.name")}:
								</th>
								<td>
									${driverLicense.name}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.driverLicense.address")}:
								</th>
								<td>
									${driverLicense.address}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.driverLicense.birthday")}:
								</th>
								<td>
									[#if driverLicense.birthday??]
										<span title="${driverLicense.birthday?string("yyyy-MM-dd HH:mm:ss")}">${driverLicense.birthday}</span>
									[#else]
										-
									[/#if]
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.driverLicense.grade")}:
								</th>
								<td>
									${driverLicense.grade}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.driverLicense.expirationDate")}:
								</th>
								<td>
									[#if driverLicense.expirationDate??]
										<span title="${driverLicense.expirationDate?string("yyyy-MM-dd HH:mm:ss")}">${driverLicense.expirationDate}</span>
									[#else]
										-
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