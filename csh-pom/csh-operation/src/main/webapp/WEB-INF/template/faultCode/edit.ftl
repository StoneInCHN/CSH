<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.faultCode.edit")}</title>
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
<style type="text/css">
	.wid380{
		width:380px !important ;
	}
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	
	// 表单验证
	$inputForm.validate({
		rules: {
			code: {
				required: true,
				maxlength:20
			},
			defineCh: {
				required: true,
				maxlength:200
			},
			defineEn: {
				required: true,
				maxlength:200
			},
			scope: {
				required: true,
				maxlength:200
			},
			info:{
				maxlength:500
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.faultCode")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.faultCode.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.faultCode.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.faultCode.edit")}i</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    <form id="inputForm" action="update.jhtml" method="post">
						<input type="hidden" name="id" value="${faultCode.id}" />
						<table class="input tabContent">
                     		<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.faultCode.code")}:
								</th>
								<td>
									<input type="text"  name="code" class="text wid380" maxlength="20" value="${faultCode.code}" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.faultCode.defineCh")}:
								</th>
								<td>
									<input type="text"  name="defineCh" class="text wid380" maxlength="200" value="${faultCode.defineCh}" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.faultCode.defineEn")}:
								</th>
								<td>
									<input type="text"  name="defineEn" class="text wid380" maxlength="200" value="${faultCode.defineEn}" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.faultCode.scope")}:
								</th>
								<td>
									<input type="text"  name="scope" class="text wid380" maxlength="200" value="${faultCode.scope}" />
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.faultCode.info")}:
								</th>
								<td>
									<textarea name="info" cols="60" rows="5" maxlength="500">${faultCode.info} </textarea>
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