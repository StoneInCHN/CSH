<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.5&ak=48E5B926fa6a18bd765400ab3211cbad"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/resources/js/jquery.tools.js"></script>
	<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
	<script type="text/javascript" src="${base}/resources/js/common.js"></script>
	<script type="text/javascript" src="${base}/resources/js/input.js"></script>
	<script type="text/javascript" src="${base}/resources/js/coord_tools.js"></script>
	
    <title>位置点上传</title>
    <style type="text/css">
    html,body{
		height: 100%;
		width:100%;
		font-size:14px;
		font-family:"微软雅黑";
	}
        #allmap {height:100%;margin:0px auto;}
        table.input th{width:50px;}
    </style>
<script>
$(function(){
	var $inputForm = $("#inputForm");
	var $submit =$(":submit");
	// 表单验证
	$inputForm.validate({
		rules: {
			longitude:"required",
			latitude:"required"
		},
		submitHandler: function(form) {
		$submit.prop("disabled", true);
		sendPostion();
		}
	});
	
	function sendPostion(){
		window.parent.$("#longitude").val($("#longitude").val());
		window.parent.$("#latitude").val($("#latitude").val());
		window.parent.$(".dialogOverlay").remove();
		window.parent.$(".xxDialog").remove();
	}
})
</script>
</head>
<body>
		<form id="inputForm"  method="post">
			<table class="input tabContent" style="margin-top:0px">
				<tr>
					<td >
						<div id="r-result"><input  id="suggestId" size="20"  class="text" placeholder="地址" type="text"></div>
					</td>
					<th>
						<span class="requiredField">*</span>${message("pisotion.point.longitude")}:
					</th>
					<td width="220px">
						<input type="text" id="longitude" readonly name="longitude" class="text"/>
					</td>
					<th>
						<span class="requiredField">*</span>${message("pisotion.point.latitude")}:
					</th>
					<td width="220px">
						<input type="text" id="latitude" readonly name="latitude" class="text"/>
					</td>
					<td width="50px">
						<input type="submit" id="send" class="button" value="${message("position.upload.send")}" />
					</td>
					<td>
					<div id="loading_bar" style="display:none;background: url(../../resources/images/loading_bar.gif) no-repeat;width:208px;height:13px;z-index: 100;"></div>
					</td>
				</tr>
			</table>
	</form>
		<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	

    var map = null;
    $().ready(function () {
    
    			var $parent = window.parent;
				var address = $parent.$("select[name='areaId_select']  option:selected").text();
    
                /** 百度地图API功能 **/
                map = new BMap.Map("allmap");            // 创建Map实例
                var point = new BMap.Point(116.404074, 39.913904);    // 创建点坐标
                map.centerAndZoom(point,10);
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
					{"input" : "suggestId"
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
				$("#suggestId").attr("placeholder",address); 
				
    });
</script>
