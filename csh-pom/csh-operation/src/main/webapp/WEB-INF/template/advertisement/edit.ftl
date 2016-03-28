<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.advertisement.edit")}</title>
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
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			advName: {
				required: true
			},
			advContentLink: {
				required: true
			},
			remark: {
				required: true
			},
			status: {
				required: true
			}
		}
	});

});
</script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.advertisement")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.advertisement.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.advertisement.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.advertisement.edit")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    <form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${advertisement.id}" />
						<input type="hidden" name="advImageUrl" value="${advertisement.advImageUrl}" />
						<table class="input tabContent">
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.advertisement.advName")}:
								</th>
								<td>
									<input type="text" name="advName" class="text" maxlength="20" value="${advertisement.advName}"/>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.advertisement.advImageUrl")}:
								</th>
								<td>
									<a href="${base}/upload/advertisement/${advertisement.advImageUrl}" target="1024"><img src="${base}/upload/license/${advertisement.advImageUrl}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("csh.advertisement.advImageUrl")}"></a>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.advertisement.advImage")}:
								</th>
								<td>
									<input type="file" name="advImage" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.advertisement.advContentLink")}:
								</th>
								<td>
									<input type="text" name="advContentLink" class="text" value="${advertisement.advContentLink}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.advertisement.remark")}:
								</th>
								<td>
									<input type="text" name="remark" class="text" value="${advertisement.remark}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.advertisement.order")}:
d
								</th>
								<td>
									<input type="text" name="order" class="text" value="${advertisement.order}"/>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.advertisement.status")}:
								</th>
								<td>
									<select name="status" class="text">
										<option value="ENABLE" [#if advertisement.status== "ENABLE" ] selected="selected" [/#if]>${message("csh.advertisement.status.ENABLE")}</option>
										<option value="DISABLE" [#if advertisement.status== "DISABLE" ]selected="selected"[/#if]>${message("csh.advertisement.status.DISABLE")}</option>
									</select>
								</td>
							</tr>
						</table>
						<table class="input">
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" class="button" value="${message("csh.common.submit")}" />
									<input type="button" class="button" value="${message("csh.common.back")}" onclick="location.href='list.jhtml'" />
								</td>
							</tr>
						</table>
					</form>
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