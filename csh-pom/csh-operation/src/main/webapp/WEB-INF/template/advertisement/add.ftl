<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.advertisement.add")}</title>
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
<script type="text/javascript" src="${base}/resources/js/jquery.form.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $inputForm = $("#inputForm");
	var $loadingBar = $(".loadingBar");
	// 表单验证
	$inputForm.validate({
		rules: {
			advName: {
				required: true
			},
			advImage: {
				required: true
			},
			order:{
				number:true,
				min:1
			},
			status: {
				required: true
			},
			adType: {
				required: true
			}
			/*
			,
			resolutionConfigId: {
				required: true
			}
			*/
		},
		submitHandler:function(form){
			$inputForm.ajaxSubmit({
			      	dataType:"json",
			       	beforeSubmit:function(){
			       		$('input[type="submit"]').attr("disabled","disabled");
			       		$loadingBar.show();
			       	},
			       	success:function(result){
			       		if(result.type == "error"){
			       			$loadingBar.hide();
			       		//	$.dialog({type:"warn",content:result.content})
			       			alert(result.content);
			       			$('input[type="submit"]').attr("disabled",false);
			       		}else{
			       			$loadingBar.hide();
			       		//	$.dialog({type:"success",content:result.content})
			       			alert(result.content);
			       			location.href="list.jhtml";
			       		}
			       		
			       }
			});
		}
			
	});
	$('#resConf').hide();
	$('#adType').change(function(){ 
		var selectedType = $(this).children('option:selected').val(); 
		if(selectedType == 'STARTING_AD'){
			$('#resConf').show();
		}else if(selectedType == 'NORMAL_AD'){
			$('#resConf').hide();
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
				<a href="list.jhtml" class="bread-current"><i class="fa fa-list"></i>${message("csh.advertisement.list")}</a>
				<span class="divider">/</span> 
				<span  class="bread-current"><i class="fa fa-plus"></i>${message("csh.advertisement.add")}</span>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left"><i class="fa fa-plus"></i>${message("csh.advertisement.add")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                     <form id="inputForm" action="save.jhtml" method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
                     	<table class="input tabContent">
                     		<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.advertisement.adType")}:
								</th>
								<td>
									<select name="adType" id="adType">
										<option value="">${message("csh.advertisement.adType.select")}</option>
										<option value="NORMAL_AD">${message("csh.advertisement.adType.NORMAL_AD")}</option>
										<option value="STARTING_AD">${message("csh.advertisement.adType.STARTING_AD")}</option>
									</select>
								</td>
							</tr>
							<tr id="resConf">
								<th>
									<span class="requiredField">*</span>${message("csh.advertisement.resolution")}:
								</th>
								<td>
									<select name="resolutionConfigId" id="resolutionConfigId">
										<option value="">${message("csh.advertisement.resolution.select")}</option>
										[#list resConfs as resConf]
    			 						<option value="${resConf.id}">${resConf.name} (${resConf.piWidth } * ${resConf.piHeight })</option>
    			 						[/#list]
									</select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.advertisement.advName")}:
								</th>
								<td>
									<input type="text" name="advName" class="text" maxlength="50" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.advertisement.advImage")}:
								</th>
								<td>
									<input type="file" name="advImage"/>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.advertisement.advContentLink")}:
								</th>
								<td>
									<input type="text" name="advContentLink" class="text" />
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.advertisement.order")}:								</th>
								<td>
									<input type="text" name="order" class="text" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>${message("csh.advertisement.status")}:
								</th>
								<td>
									<select name="status">
										<option value="">${message("csh.advertisement.status.select")}</option>
										<option value="ENABLE">${message("csh.advertisement.status.ENABLE")}</option>
										<option value="DISABLE">${message("csh.advertisement.status.DISABLE")}</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									${message("csh.advertisement.remark")}:
								</th>
								<td>
									<input type="text" name="remark" class="text" />
								</td>
							</tr>
							<tr>
								<td  colspan="2">
									<span class="loadingBar" style="margin-left:120px;display:none"></span>
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