<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.newsCategory.edit")}</title>
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
			name: {
				required: true
			},
			categoryDesc: {
				required: true
			},
			isEnabled: {
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
				<a ><i class="fa fa-user"></i> ${message("csh.main.newsCategory")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.newsCategory.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.newsCategory.edit")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.newsCategory.edit")}i</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    <form id="inputForm" action="update.jhtml" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${newsCategory.id}" />
						<input type="hidden" name="iimgUrld" value="${newsCategory.imgUrl}" />
						<table class="input tabContent">
                     		<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.newsCategory.name")}:
								</th>
								<td>
									<input type="text" id="name" name="name" class="text" maxlength="20" value="${newsCategory.name}" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.newsCategory.imgUrl")}:
								</th>
								<td>
									<input type="file" id="imgFile" name="imgFile"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.newsCategory.imgUrl")}:
								</th>
								<td>
									<a href="${newsCategory.imgUrl}" target="100"><img src="${newsCategory.imgUrl}" alt="新闻配图" style="width:100px;height:100px"><a>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.newsCategory.categoryDesc")}:
								</th>
								<td>
									<input type="text" name="categoryDesc" class="text" maxlength="20" value="${newsCategory.categoryDesc}"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.newsCategory.isEnabled")}:
								</th>
								<td>
									<select name="isEnabled">
										<option value="true" [#if newsCategory.isEnabled ?? && newsCategory.isEnabled] selected="selected" [/#if]>${message("csh.newsCategory.isEnabled.true")}</option>
										<option value="false" [#if newsCategory.isEnabled ?? && !newsCategory.isEnabled] selected="selected" [/#if]>${message("csh.newsCategory.isEnabled.false")}</option>
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