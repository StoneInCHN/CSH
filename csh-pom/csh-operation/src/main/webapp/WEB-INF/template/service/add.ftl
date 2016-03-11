<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.service.add")}</title>
<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
<style type="text/css">
	html{
		padding-left: 20px;
		padding-right: 15px;
	}
	     #CategoryTable{
            width: 550px;
            margin: 10px auto;
            padding: 10px;
        }
         #CategoryTable tr{
           border-bottom: 1px solid #e3f3ff;
        }
        #CategoryTable th{
            font-size: 15px;
            width: 20%;
        }
        #CategoryTable td{
            font-size: 14px;
        }
    	#CategoryTable label{
    		margin:2px 5px;
            display:inline-block;
            *display:inline;
            *zoom:1;
        }
</style>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");

	// 表单验证
	$inputForm.validate({
		rules: {
			name:{
				required:true,
				maxlength:20
			},
			intro:{
				required:true,
				maxlength:200
			},
			file:{
				required:true
			},
			isTop:{
				required:true
			},
			price:{
				required:true,
				min: 0,
				decimal: {
					integer: 12,
					fraction:2
				}
				
			},
			category:{
				required:true
			}
		},
		messages: {
			price: {
				decimal:"${message("admin.validate.decimal.fraction")}"
			}
		}
	});
	
});
</script>
</head>
<body>
	<div class="path">
		${message("common.current.position")}：${message("asup.main.service")}&raquo; ${message("asup.service.add")}
	</div>
	<form id="inputForm" action="save.jhtml" method="post" enctype="multipart/form-data">
		<input type="hidden" id="category" name="category"  class="text"/>
		<table class="input">
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.name")}:
				</th>
				<td>
					<input type="text" name="name" class="text" maxlength="20"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.intro")}:
				</th>
				<td>
					<textarea rows="50" rows="6" name="intro" class="text" maxlength="200"></textarea>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.file")}:
				</th>
				<td>
					<input type="file" name="file"  />
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.price")}:
				</th>
				<td>
					<input type="text" name="price"  class="text"/>
				</td>
			</tr>
			<tr>
				<th>
					<span class="requiredField">*</span>${message("asup.service.serviceCategory")}:
				</th>
				<td>
					<input type="text" id="categoryButton" readOnly  class="text"/>
				</td>
			</tr>
			<tr>
				<th>
					&nbsp;
				</th>
				<td>
					<input type="submit" class="button" value="${message("asup.common.submit")}" />
					<input type="button" class="button" value="${message("asup.common.back")}" onclick="location.href='list.jhtml'" />
				</td>
			</tr>
		</table>
	</form>
<script>
	$(function(){
		var categoryButton = $("#categoryButton");
		categoryButton.click(function(){
			selectCategory();
		});
		
		function selectCategory(){
			 	 $.dialog({
				title: "${message("asup.service.serviceCategory.select")}",
				content:'<div class="authorities"><span class="fieldSet">[#list serviceCategory as category]<label style="margin:5px"><input type="radio" name="category" value="${category.id}" /><span>${category.categoryName}</span></label>[/#list]</span></div>',
				content:'<table id="CategoryTable">[#list serviceCategory as rootCategory][#if rootCategory.parentId == "0"]<tr><th>${rootCategory.categoryName}</th><td> [#list serviceCategory as category][#if category.parentId == rootCategory.id]<label><input type="radio" name="category" value="${category.id}" ><span>${category.categoryName}</span></label>[/#if][/#list]</td></tr>[/#if][/#list]</table>',
				modal: true,
				width:550,
				close:true,
				onOk: function() {
				$("#category").val($("[name='category']:checked").val());
				$("#categoryButton").val($("[name='category']:checked").next().text());
			}
			});
		}
		
		
	});
</script>
</body>
</html>