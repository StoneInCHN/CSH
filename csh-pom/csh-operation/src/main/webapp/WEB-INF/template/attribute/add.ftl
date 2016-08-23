<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.attribute.add")}</title>
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
<style>
	.deleteOption{color:red}
</style>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.attribute")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" class="bread-current"><i class="fa fa-list"></i>${message("csh.attribute.list")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-plus"></i>${message("csh.attribute.add")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-plus"></i>${message("csh.attribute.add")}</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                    <a href="#" class="wclose"><i class="fa fa-times"></i></a>
                  </div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                     <form id="inputForm" action="save.jhtml" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                     	<table class="input">
							<tr>
								<th>
									${message("csh.attribute.productCategory")}:
								</th>
								<td colspan="3">
									<select name="productCategoryId">
										[#list productCategoryTree as productCategory]
											<option value="${productCategory.id}">
												[#if productCategory.grade != 0]
													[#list 1..productCategory.grade as i]
														&nbsp;&nbsp;
													[/#list]
												[/#if]
												${productCategory.name}
											</option>
										[/#list]
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.attribute.name")}:
								</th>
								<td colspan="3">
									<input type="text" name="name" class="text" maxlength="200" />
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.common.order")}:
								</th>
								<td colspan="3">
									<input type="text" name="order" class="text" maxlength="9" />
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td colspan="3">
									<a href="javascript:;" id="addOption" class=" btn btn-default">${message("csh.attribute.addOption")}</a>
								</td>
							</tr>
							<tr>
								<td></td>
								<td colspan="3">
									<table id="attributeTable" class="input table table-hover table-striped table-bordered table-condensed">
										<tr class="title">
											<td>
												${message("csh.attribute.options")}
											</td>
											<td>
												${message("csh.common.delete")}
											</td>
										</tr>
										<tr class="optionTr">
											<td>
												<input type="text" name="options" class="text" maxlength="200" />
											</td>
											<td>
												<a href="javascript:;" class="deleteOption">[ ${message("csh.common.delete")}]</a>
											</td>
										</tr>
									</table>
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
	<script>
		
		$(function(){
			var $inputForm = $("#inputForm");
			var $attributeTable = $("#attributeTable");
			var $addOption = $("#addOption");
			var $deleteOption = $("a.deleteOption");
			// 增加参数
			$addOption.bind("click", function() {
				var $this = $(this);
				[@compress single_line = true]
					var trHtml = 
					'<tr class="optionTr">
						<td>
							<input type="text" name="options" class="text" maxlength="200" \/>
						<\/td>
						<td>
							<a href="javascript:;" class="deleteOption">[ ${message("csh.common.delete")}]<\/a>
						<\/td>
					<\/tr>';
				[/@compress]
				$attributeTable.append(trHtml);
			});
			
			// 删除参数
			$attributeTable.delegate("a", "click", function(){
			    var $this = $(this);
				if ($attributeTable.find("tr.optionTr").size() <= 1) {
					$.message("warn", "${message("csh.attribute.deleteAllParameterNotAllowed")}");
				} else {
					$this.closest("tr").remove();
				}
			});			
			
			// 表单验证
			$inputForm.validate({
				rules: {
					productCategoryId: "required",
					name: "required",
					order: "digits",
					options: "required"
				}
			});
		})
	</script>
</body>
</html>