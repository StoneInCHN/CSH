<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.apply.edit")}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/common.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.apply")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.apply.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-eye"></i>${message("csh.apply.details")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.apply.details")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
						<table class="input">
							<tr>
								<th>
									${message("csh.apply.tenantName")}:
								</th>
								<td>
									${apply.tenantName}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.contactPerson")}:
								</th>
								<td>
									${apply.contactPerson}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.contactPhone")}:
								</th>
								<td>
									${apply.contactPhone}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.address")}:
								</th>
								<td>
									${apply.address}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.license")}:
								</th>
								<td>
									<a href="${base}${apply.license}" target="1024"><img src="${base}${apply.license}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("csh.apply.license")}"></a>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.photo")}:
								</th>
								<td>
									<a href="${base}${apply.photo}" target="1024"><img src="${base}${apply.photo}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("csh.apply.photo")}"></a>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.area")}:
								</th>
								<td>
									${apply.area}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.applyStatus")}:
								</th>
								<td>
									${message("csh.apply.applyStatus."+apply.applyStatus)}
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.apply.notes")}:
								</th>
								<td>
									${apply.notes}
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