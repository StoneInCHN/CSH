<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@page import="java.util.UUID"%>
<%@page import="org.apache.commons.lang.ArrayUtils"%>
<%@page import="com.csh.beans.Setting"%>
<%@page import="com.csh.utils.SettingUtils"%>
<%@page import="com.csh.utils.SpringUtils"%>
<%@page import="com.csh.beans.Setting.CaptchaType"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%
  String base = request.getContextPath();
  String captchaId = UUID.randomUUID().toString();
  Setting setting = SettingUtils.get();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title><%=SpringUtils.getMessage("csh.apply.title")%></title>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<link rel="shortcut icon" type="image/x-icon" href="<%=base%>/resources/images/carlife.ico" media="screen" /> 
<link href="<%=base%>/resources/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="<%=base%>/resources/style/apply.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=base%>/resources/js/jquery.js"></script>
<script type="text/javascript" src="<%=base%>/resources/js/common.js"></script>
<script type="text/javascript" src="<%=base%>/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=base%>/resources/js/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=base%>/resources/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="<%=base%>/resources/js/jquery.lSelect.js"></script>
<script type="text/javascript" src="<%=base%>/resources/js/bootstrap-modal.js"></script>
<script type="text/javascript">
	$().ready( function() {
		
		var $applyForm = $("#applyForm");
		var $tenantName = $("#tenantName");
		var $contactPhone = $("#contactPhone");
		var $contactPerson = $("#contactPerson");
		var $address = $("#address");
		var $licenseFile = $("#licenseFile");
		var $photoFile = $("#photoFile");
		var $captcha = $("#captcha");
		var $captchaId = $("#captchaId");
		var $captchaImage = $("#captchaImage");
		var $submit = $(":submit");
		var $areaId = $("#areaId");
		
		// 地区选择
		$areaId.lSelect({
			url: "<%=base%>/console/common/area.jhtml"
		});
	
		// 更换验证码
		$captchaImage.click( function() {
			$captchaImage.attr("src", "<%=base%>/console/common/captcha.jhtml?captchaId=<%=captchaId%>&timestamp=" + (new Date()).valueOf());
		});
		
		//表单验证
		$applyForm.validate({
			rules: {
				tenantName:{
					required:true
				},
				contactPhone: {
					required:true,
					pattern:/^[0-9]{1,20}$/
				},
				contactPerson:{
					required:true
				},
				address:{
					required:true
				},
				licenseFile:{
					required:true
				},
				photoFile:{
					required:true
				}
			},
				
			messages: {
				tenantName:{
					required:"<%=SpringUtils.getMessage("csh.apply.tenantNameRequired")%>"
				},
				contactPhone:{
					required:"<%=SpringUtils.getMessage("csh.apply.contactPhoneRequired")%>",
					pattern: "<%=SpringUtils.getMessage("csh.apply.contactPhoneIllegal")%>"
				},
				contactPerson:{
					required:"<%=SpringUtils.getMessage("csh.apply.contactPersonRequired")%>"
				},
				address:{
					required:"<%=SpringUtils.getMessage("csh.apply.addressRequired")%>"
				},
				licenseFile:{
					required:"<%=SpringUtils.getMessage("csh.apply.licenseFileRequired")%>"
				},
				photoFile:{
					required:"<%=SpringUtils.getMessage("csh.apply.photoFileRequired")%>"
				}
			},
			
			submitHandler:function(form){
				<%if (ArrayUtils.contains(setting.getCaptchaTypes(),
					CaptchaType.adminApply)) {%>
					if ($captcha.val() == "") {
						$.message("warn", "<%=SpringUtils.getMessage("csh.apply.captchaRequired")%>");
						return false;
					}
				<%}%>
				
				$.ajax({
					url: "<%=base%>/console/common/captchaCheck.jhtml",
					type: "GET",
					data:{
						"captchaType":"adminApply",
						"captcha":$captcha.val(),
						"captchaId":$captchaId.val()
					},
					dataType: "script",
					cache: false,
					success:function(result){
						if(result == "false" ){
							$.message("warn", "<%=SpringUtils.getMessage("csh.apply.captcha.faild")%>");
							return false;
						} else {
							form.submit();
						}
					}
				});

			}
		});
		
	});
</script>
</head>
<body>
	<div class="header">
		<img class="logo" src="resources/images/logo.png" alt="<%=SpringUtils.getMessage("csh.apply.logo")%>" />
	</div>
	<div class="container">
		<div class="stance"></div>
		<div class="apply">
			<div class="title">
				<h1><%=SpringUtils.getMessage("csh.apply.title")%></h1>
			</div>
			<div class="content">
				<form id="applyForm" action="<%=base%>/console/apply/submit.jhtml" method="post" enctype="multipart/form-data">
					<%
					  if (ArrayUtils.contains(setting.getCaptchaTypes(), CaptchaType.adminApply)) {
					%>
					<input type="hidden" id="captchaId" name="captchaId" value="<%=captchaId%>" />
					<%
					  }
					%>
					<table class="input">
						<tr>
							<th><span class="requiredField">*</span><%=SpringUtils.getMessage("csh.apply.tenantName")%>:</th>
							<td><input type="text" id="tenantName" name="tenantName" class="text" maxlength="30" /></td>
						</tr>
						<tr>
							<th><span class="requiredField">*</span><%=SpringUtils.getMessage("csh.apply.contactPerson")%>:</th>
							<td><input type="text" id="contactPerson" name="contactPerson" class="text" maxlength="20" /></td>
						</tr>
						<tr>
							<th><span class="requiredField">*</span><%=SpringUtils.getMessage("csh.apply.contactPhone")%>:</th>
							<td><input type="text" id="contactPhone" name="contactPhone" class="text" maxlength="30" /></td>
						</tr>
						<tr>
							<th><span class="requiredField">*</span><%=SpringUtils.getMessage("csh.apply.area")%>:</th>
							<td><input type="hidden" id="areaId"  name="areaId"  /></td>
						</tr>
						<tr>
							<th><span class="requiredField">*</span><%=SpringUtils.getMessage("csh.apply.address")%>:</th>
							<td>
							<input type="text" id="address" class="text" name="address" autocomplete="off" />
							</td>
						</tr>
						<tr>
							<th><span class="requiredField">*</span><%=SpringUtils.getMessage("csh.apply.pisotion.point")%>:</th>
							<td>
								<input type="hidden"  id="longitude" name="longitude" />
								<input type="hidden"  id="latitude" name="latitude" />
								<button type="button" class="btn btn-info " id="selectPositionPoint">
								  <%=SpringUtils.getMessage("csh.apply.select.pisotion.point")%>
								</button>
							</td>
						</tr>
						<tr>
							<th><span class="requiredField">*</span><%=SpringUtils.getMessage("csh.apply.license")%>:</th>
							<td><input type="file" name="licenseFile" id="licenseFile" /></td>
						</tr>
						<tr>
							<th><span class="requiredField">*</span><%=SpringUtils.getMessage("csh.apply.photo")%>:</th>
							<td><input type="file" name="photoFile" id="photoFile" /></td>
						</tr>
						<%
						  if (ArrayUtils.contains(setting.getCaptchaTypes(), CaptchaType.adminApply)) {
						%>
						<tr>

							<th><span class="requiredField">*</span><%=SpringUtils.getMessage("csh.captcha.code")%>:</th>
							<td><input type="text" id="captcha" name="captcha" class="captcha" maxlength="4" autocomplete="off" /> <img id="captchaImage" class="captchaImage"
								src="<%=base%>/console/common/captcha.jhtml?captchaId=<%=captchaId%>" title="<%=SpringUtils.getMessage("csh.captcha.imageTitle")%>" /></td>
						</tr>
						<%
						  }
						%>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" class="applyButton" value="<%=SpringUtils.getMessage("csh.common.apply")%>" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="mapModal" tabindex="-1" role="dialog" aria-labelledby="mapModalLabel" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="mapModalLabel">
	        <%=SpringUtils.getMessage("csh.apply.pisotion.point")%>
	        	<input type="text" class="text" id="modalTitleInput">
	        </h4>
	      </div>
	      <div class="modal-body">
	       	<div id="allmap"></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="footer-warp">
		<div class="copy-warp">
			<div class="copy">
				版权所有©2014<span>Copyright © 2016 </span>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//百度地图API功能
		function loadJScript() {
			var script = document.createElement("script");
			script.type = "text/javascript";
			script.src = "http://api.map.baidu.com/api?v=2.0&ak=D767b598a9f1e43c3cadc4fe26cdb610&callback=init";
			document.body.appendChild(script);
		}
		function init() {
			// 百度地图API功能
			var address = $("select[name='areaId_select']  option:selected").text();
			var map = new BMap.Map("allmap");
			var point = new BMap.Point(116.331398,39.897445);
			map.centerAndZoom(point,12);
            map.addControl(new BMap.NavigationControl());
            map.enableScrollWheelZoom(true);
            map.addControl(new BMap.OverviewMapControl());              //添加默认缩略地图控件
			map.addControl(new BMap.OverviewMapControl({isOpen:true}));   //右上角，打开
			map.addEventListener("click", showInfo);
			var marker ;
			 function showInfo(e){
				 if(marker){
					map.removeOverlay(marker);
				 }
				$("#longitude").val(e.point.lng);
				$("#latitude").val(e.point.lat);
				marker = new BMap.Marker(new BMap.Point(e.point.lng, e.point.lat));  // 创建标注
				map.addOverlay(marker);              // 将标注添加到地图中
				marker.enableDragging(); 
				marker.addEventListener("dragend", function showInfo(){
					 var p = marker.getPosition();       //获取marker的位置
					 $("#longitude").val(p.lng);
					 $("#latitude").val(p.lat); 
				});
			};
			
			var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : "modalTitleInput"
				,"location" : map
			});
			
			ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
			var str = "";
				var _value = e.fromitem.value;
				var value = "";
				if (e.fromitem.index > -1) {
					value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				}    
				str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
				
				value = "";
				if (e.toitem.index > -1) {
					_value = e.toitem.value;
					value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				}    
				str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
				$("#searchResultPanel").html(str);
			});
			
			ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
			var _value = e.item.value;
				var	myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				$("#searchResultPanel").html("onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue);
				setPlace(myValue);
			});
		
			function setPlace(myValue){
				map.clearOverlays();    //清除地图上所有覆盖物
				function myFun(){
					var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
					map.centerAndZoom(pp, 14);
					marker = new BMap.Marker(new BMap.Point(pp.lng, pp.lat));  // 创建标注
					map.addOverlay(marker);              // 将标注添加到地图中
					$("#longitude").val(pp.lng);
					$("#latitude").val(pp.lat);
					marker.enableDragging(); 
					marker.addEventListener("dragend", function changePosition(){
						 var p = marker.getPosition();       //获取marker的位置
						 $("#longitude").val(p.lng);
						 $("#latitude").val(p.lat); 
					});
				}
				var local = new BMap.LocalSearch(map, { //智能搜索
				  onSearchComplete: myFun
				});
				local.search(myValue);
			};
			 
			setPlace(address);
			$("#modalTitleInput").attr("placeholder",address);
		}  
		
		var $selectPositionPoint = $("#selectPositionPoint");
		$selectPositionPoint.click(function(){
			
			var val = $("#areaId").val();
			if(!val){
				alert("请先选择地区");
			}else{
				$('#mapModal').modal("show");
			}
			
			
		});
		$('#mapModal').on('show.bs.modal', function (e) {
			loadJScript();
		})
		$('#mapModal').on('hidden.bs.modal', function (e) {
			alert("位置点选择成功")
		})
</script>
</body>
</html>
