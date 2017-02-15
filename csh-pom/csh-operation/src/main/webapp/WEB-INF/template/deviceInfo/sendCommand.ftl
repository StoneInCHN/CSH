<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>平台下发指令</title>
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
			commandType: {
				required: true
			}
		}
	});
	
	$("#commandType").change(function(){
		var commandType=$(this).children('option:selected').val();
		if(commandType == 'sm'){
			$("#sm_parameter").show();
		}else{
			$("#sm_parameter").hide();
		}
		if(commandType == 'nobd'){
			$("#nobd_parameter").show();
		}else{
			$("#nobd_parameter").hide();
		}
	});

});
</script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.deviceInfo")}</a> 
				<span class="divider">/</span> 
				<a href="list.jhtml" ><i class="fa fa-list"></i>${message("csh.deviceInfo.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>平台下发指令</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">平台下发指令</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
                    <form id="inputForm" action="sendCommandResult.jhtml" method="post">
						<input type="hidden" name="deviceNo" value="${deviceInfo.deviceNo}" />
						<table class="input tabContent">
							<tr>
								<th>
									${message("csh.deviceInfo.deviceNo")}:
								</th>
								<td>
									${deviceInfo.deviceNo}
								</td>
							</tr>
							<tr>
								<th>
									<span class="requiredField">*</span>选择指令:
								</th>
								<td>
									<select name="commandType" id="commandType">
										<option value="">请选择指令...</option>
										<option value="sm" >标定初始里程</option>
										<option value="arm" >车辆设防</option>
										<option value="disarm" >车辆撤防</option>
										<option value="ion" >断油电</option>
										<option value="ioff" >恢复油电</option>
										<option value="nobd" >nobd模式</option>
										<option value="rhw" >终端重启</option>
									</select>
								</td>
							</tr>
							<tr id="sm_parameter" style="display:none">
								<th><span class="requiredField">*</span>初始里程:</th>
								<td><input type="text" name="parameterSM" class="text" number="true" min="0" style="width:100px" /></td>
							</tr>
							<tr id="nobd_parameter" style="display:none">
								<th><span class="requiredField">*</span>选择模式:</th>
								<td>
									<input type="radio" name="parameterNOBD"  value="0" checked="checked"/>obd模式  &nbsp; &nbsp;
									<input type="radio" name="parameterNOBD"  value="1"/>nobd模式 
								</td>
							</tr>							
						</table>
						<table class="input">
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" class="button" value="下发指令" />
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