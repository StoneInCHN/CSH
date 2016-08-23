<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.brand.add")}</title>
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
<script type="text/javascript" src="${base}/resources/js/editor/kindeditor-min.js"></script>
<script type="text/javascript" src="${base}/resources/js/editor/lang/zh_CN.js"></script>
<script>
       KindEditor.ready(function(K) {
				K.create('textarea[name="introduction"]', {
					autoHeightMode : true,
					afterCreate : function() {
						var self = this;
						self.loadPlugin('autoheight');
					},
					cssPath : '${base}/resources/js/editor/plugins/code/prettify.css',
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : true,
					items : [
						'source','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
					uploadJson: '${base}/console/common/uploadImg.jhtml',
					afterChange: function() {
						this.sync();
					}
				});
			});
</script>
<script type="text/javascript">
$().ready(function() {
	var $inputForm = $("#inputForm");
	var $type = $("#type");
	var $logo = $("#logo");
	
	[@flash_message /]
	
	
	// 表单验证
	$inputForm.validate({
		rules: {
			name: "required",
			logo: "required",
			order: "digits"
		}
	});
	
	
});
</script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.brand")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" class="bread-current"><i class="fa fa-list"></i>${message("csh.brand.list")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-plus"></i>${message("csh.brand.add")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-plus"></i>${message("csh.brand.add")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                     <form id="inputForm" action="save.jhtml"  method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                     	<table class="input tabContent">
								<tr>
									<th>
										<span class="requiredField">*</span>${message("csh.brand.name")}:
									</th>
									<td>
										<input type="text" name="name" class="text" maxlength="200" />
									</td>
								</tr>
								<tr>
									<th>
										${message("csh.brand.logo")}:
									</th>
									<td>
										<span class="fieldSet">
											<input type="file" id="logo" name="imgFile"  maxlength="200" />
										</span>
									</td>
								</tr>
								<tr>
									<th>
										${message("csh.brand.url")}:
									</th>
									<td>
										<input type="text" name="url" class="text" maxlength="200" />
									</td>
								</tr>
								<tr>
									<th>
										${message("csh.brand.order")}:
									</th>
									<td>
										<input type="text" name="order" class="text" maxlength="9" />
									</td>
								</tr>
								<tr>
									<th>
										${message("csh.brand.introduction")}:
									</th>
									<td>
										<textarea id="editor" name="introduction" class="editor" rows="7"></textarea>
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