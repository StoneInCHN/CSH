
$("#track_search_btn").click(function(){
	  var _queryParams = $("#track_search_form").serializeJSON();
	  if(_queryParams["vehicleID"] == "" || _queryParams["searchDate"] == ""){
			showSuccessMsg(message("csh.vehicle.miss.param"));
			return;
	  }
	  
	  $.ajax({
			url:"../vehicleTrack/drawVehicleTrackMultiple.jhtml",
			type:"post",
			data:_queryParams,
			beforeSend:function(){
				$.messager.progress({
					text:message("csh.common.progress")
				});
			},
			success:function(result,response,status){
				$.messager.progress('close');
				console.log(result);
				
				if(result.length == 0){
					$("#vehicleTrackMap").html(message("csh.vehicle.no.track"));
				}else{
					createMap(result);
				}
				
				//$("#vehicleTrackMap")
			},
			error:function (XMLHttpRequest, textStatus, errorThrown) {
				$.messager.progress('close');
				alertErrorMsg();
			}
		});
});

function createMarker(point, icon,map,isEndPoint){  // 创建图标对象   
	var myIcon = new BMap.Icon(icon, new BMap.Size(30, 90), {    
	// 指定定位位置。   
	// 当标注显示在地图上时，其所指向的地理位置距离图标左上    
	// 角各偏移10像素和25像素。您可以看到在本例中该位置即是   
	   // 图标中央下端的尖角位置。    
	   //offset: new BMap.Size(15, -25),    
	   // 设置图片偏移。   
	   // 当您需要从一幅较大的图片中截取某部分作为标注图标时，您   
	   // 需要指定大图的偏移位置，此做法与css sprites技术类似。    
	   //imageOffset: new BMap.Size(0, -25)   // 设置图片偏移    
	 });      
	// 创建标注对象并添加到地图   
	 var marker = new BMap.Marker(point, {icon: myIcon});  
	 map.addOverlay(marker);
//	 var opts=new Object();
//	 if(isEndPoint){
//		 opts.offset=new BMap.Size(20,-10);
//	 }else{
//		 opts.offset=new BMap.Size(-20,-10);
//	 }
//	 var label = new BMap.Label("12:30",opts);
//	 marker.setLabel(label);
}  



function createMap(trackList){
	debugger;
	var map = new BMap.Map("vehicleTrackMap");  
	
	var trackMap=[];
	for(var i=0;i<trackList.length;i++){
		var track = trackList[i];
		
		var s_point = track[0];
		var e_point = track[track.length-1];
		
		var startPoint = new BMap.Point(s_point["x"], s_point["y"]);
		var endPoint = new BMap.Point(e_point["x"], e_point["y"]);
		
		createMarker(startPoint,"../../resources/images/start.png",map,false);
		createMarker(endPoint,"../../resources/images/end.png",map,true);
		
		var flag = 0;
		for(var j=0;j<track.length;j++){
			var m = new BMap.Point(track[j]["x"], track[j]["y"]);
			trackMap.push(m);
		}
		map.centerAndZoom(startPoint,13);// 初始化地图,设置中心点坐标和地图级别。
	}
	
	
	
	
	map.enableScrollWheelZoom();//启用滚轮放大缩小
	map.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
	map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
	map.addControl(new BMap.OverviewMapControl()); //添加缩略地图控件
	
	
	var polyline = new BMap.Polyline(trackMap,{strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5});    
	map.addOverlay(polyline);
	map.addEventListener("click",function(e){
		console.log(e.point.lng + "," + e.point.lat);
	});
	
	
	
}

//车辆查询
$(function(){
	$("#trackVehicleSearch-table-list").datagrid({
		url:'../vehicle/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		singleSelect:true,
		onSelect:function(rowIndex,rowData){
			$('#track_vehicleID').val(rowData.id);
		},
		onDblClickRow : function (rowIndex, rowData){
			
		},
		columns:[[
			{field : 'ck',checkbox : true},
			{title : message("csh.vehicle.plate"),field : "plate",width :"47%",align : 'center',sortable : true},
			{title : "品牌图标",field : "brandIcon",width :"47%",align : 'center',sortable : true},					
		]]
});
$("#track_vehicle_search_btn").click(function(){
		  var _queryParams = $("#track_vehicle_search_form").serializeJSON();
		  $('#trackVehicleSearch-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#trackVehicleSearch-table-list").datagrid('reload');			
		});
});