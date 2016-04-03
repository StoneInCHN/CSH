<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.placeholder.js"></script>
<style type="text/css">
	input.text{
		width:120px;
	}
</style>
</head>
<body>
   <form id="inputForm" action="save.jhtml" method="post" class="form-horizontal" role="form">
        <table class="input tabContent">
			<tr>
				<td>
					<input type="text" name="deviceNo" class="text" maxlength="20" placeholder="${message('csh.deviceInfo.deviceNo')}"/>
				</td>
			</tr>
		</table>
  </form>
<script type="text/javascript">
 $(":input[name='deviceNo']").blur(function(){
 	$.get("demo_test.asp",function(data,status){
    alert("Data: " + data + "\nStatus: " + status);
  });
 })
</script>
</body>
</html>