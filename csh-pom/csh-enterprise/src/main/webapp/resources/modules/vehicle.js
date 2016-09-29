var oldVehicleListStatus = []
var allVehicleListStatus = []

/**
 * 
 * @param map 地图
 * @param arrPois 行驶轨迹上的点
 */
function driveVehicle(map,arrPois,content){
    
    var lushu = new BMapLib.LuShu(map,arrPois,{
    	defaultContent:content,
    	autoView:false,//是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
    	icon  : new BMap.Icon("../../resources/images/carrun.png", new BMap.Size(39,20)),
    	speed: 80,
    	enableRotation:true,//是否设置marker随着道路的走向进行旋转
		landmarkPois: []
    });
    lushu.start();
}
function drawMutPoint(map,allVehicleListStatus){
	var points = [];
	var myIcon = new BMap.Icon("../../resources/images/carrun.png", new BMap.Size(39,20));
	
	function addMarker(point){
		var marker = new BMap.Marker(point,{icon:myIcon});  // 创建标注
		map.addOverlay(marker);
	}
	function deletePoint(point){
		debugger;
		var allOverlay = map.getOverlays();
		for (var i = 0; i < allOverlay.length -1; i++){
			if(allOverlay[i].getPosition()!= null && allOverlay[i].getPosition().lon == point.lon
					&& allOverlay[i].getPosition().lat == point.lat){
				map.removeOverlay(allOverlay[i]);
				return false;
			}
		}
	}
	debugger;
	map.clearOverlays();
	for(var i=0;i<allVehicleListStatus.length;i++){
		var point = new BMap.Point(allVehicleListStatus[i].lon,allVehicleListStatus[i].lat);
		
		if(oldVehicleListStatus.length ==0){
//			deletePoint(point);
			addMarker(point);
		}else{
//			if(oldVehicleListStatus[i].lon == allVehicleListStatus[i].lon &&
//					oldVehicleListStatus[i].lat == allVehicleListStatus[i].lat){
//				continue;
//			}
			var pointsArrs = [];
			for(var k=0;k<oldVehicleListStatus.length;k++){
				if(oldVehicleListStatus[k].plate == allVehicleListStatus[i].plate){
					var spoint = new BMap.Point(oldVehicleListStatus[k].lon,oldVehicleListStatus[k].lat);
					var epoint = new BMap.Point(allVehicleListStatus[i].lon,allVehicleListStatus[i].lat);
					pointsArrs.push(spoint);
					pointsArrs.push(epoint);
					driveVehicle(map,pointsArrs,oldVehicleListStatus[k].plate);
				}
			}
			
		}
		console.log('lon:'+allVehicleListStatus[i].lon,"; lat:"+allVehicleListStatus[i].lat)
	}
	
	
	
}
var index = 0;
function loadAllVehicleStatus(map){
//	$.ajax({
//		url:"../vehicle/allVehicleStatus.jhtml",
//		type:"post",
//		success:function(result,response,status){
//			if(result.length>0 ){
//				oldVehicleListStatus = allVehicleListStatus;
//				allVehicleListStatus = new Array();
//				for(var i = 0;i<result.length;i++){
//					var vehicleStatus = new Object();
//					vehicleStatus.lat = result[i].lat; 
//					vehicleStatus.lon = result[i].lon;
//					vehicleStatus.plate = result[i].plate;
//					allVehicleListStatus.push(vehicleStatus);
//				}
//			}else{
//				
//			}
//			drawMutPoint(map,allVehicleListStatus);
//		}
//	});
	var vehicleStatus1 = new Object();
	var vehicleStatus2 = new Object();
	if(index == 0){
		oldVehicleListStatus = allVehicleListStatus;
		allVehicleListStatus = new Array();
		
		vehicleStatus1.lat = 30.524146;
		vehicleStatus1.lon = 104.071378;
		vehicleStatus1.plate = "test1";
		allVehicleListStatus.push(vehicleStatus1);
		
		
		vehicleStatus2.lat = 30.526122;
		vehicleStatus2.lon = 104.069788;
		vehicleStatus2.plate = "test2";
		allVehicleListStatus.push(vehicleStatus2);
		
		index++;
	}else if( index ==1 ){
		oldVehicleListStatus = allVehicleListStatus;
		allVehicleListStatus = new Array();
		
		vehicleStatus1.lat = 30.525181;
		vehicleStatus1.lon = 104.07304;
		vehicleStatus1.plate = "test1";
		allVehicleListStatus.push(vehicleStatus1);
		
		vehicleStatus2.lat = 30.526651;
		vehicleStatus2.lon = 104.071917;
		vehicleStatus2.plate = "test2";
		allVehicleListStatus.push(vehicleStatus2);
		index++;
	}else if( index ==2 ){
		oldVehicleListStatus = allVehicleListStatus;
		allVehicleListStatus = new Array();
		
		vehicleStatus1.lat = 30.526363;
		vehicleStatus1.lon = 104.072078;
		vehicleStatus1.plate = "test1";
		allVehicleListStatus.push(vehicleStatus1);
		
		vehicleStatus2.lat = 30.527133;
		vehicleStatus2.lon = 104.073534;
		vehicleStatus2.plate = "test2";
		allVehicleListStatus.push(vehicleStatus2);
		index++;
	}
	else{
		oldVehicleListStatus = allVehicleListStatus;
		allVehicleListStatus = new Array();
		
		var vehicleStatus = new Object();
		vehicleStatus.lat = vehicleStatus1.lat+0.0001,
		vehicleStatus.lon = vehicleStatus1.lon+0.0001;
		vehicleStatus.plate = "test1";
		allVehicleListStatus.push(vehicleStatus);
	}
	
	var vehicleStatus3 = new Object();
	vehicleStatus3.lat = 104.171045,
	vehicleStatus3.lon = 30.125325;
	vehicleStatus3.plate = "test3";
	allVehicleListStatus.push(vehicleStatus3);
	
	drawMutPoint(map,allVehicleListStatus);
}
var vehicle_manager_tool = {
		add:function(){
			$('#addVehicle').dialog({
			    title: message("csh.vehicle.add"),    
			    width: 600,    
			    height: 350,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addVehicle_form').form('validate');
						
						if(validate){
								$.ajax({
									url:"../vehicle/add.jhtml",
									type:"post",
									data:$("#addVehicle_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addVehicle').dialog("close")
											$("#addVehicle_form").form("reset");
											$("#vehicle-table-list").datagrid('reload');
										}else{
											alertErrorMsg();
										}
									}
								});
						};
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addVehicle').dialog("close");
						 $("#addVehicle_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$("#vehicleSelectVehicleBrand-add").combobox({
			    		url: '../vehicleBrand/findAllVehicleBrand.jhtml',
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
					    formatter: function(row){
							var opts = $(this).combobox('options');
							return row[opts.textField];
						},
						onSelect: function(rec){    
				            var url = '../vehicleLine/findVehicleLineByBrand.jhtml?vehicleBrandId='+rec.id;    
				            $('#vehicleSelectVehicleLine-add').combobox('clear');
				            $('#vehicleSelectVehicleLine-add').combobox('reload', url);    
				        }

					});
			    	$("#vehicleSelectVehicleLine-add").combobox({
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    groupField:'group',
						loadFilter :function(data) {
							var newData=new Array()
							for(var i=0;i<data.length;i++){
								var newDataItem = null;
								newData.name = data[i].name;
								newData.id = data[i].id;
								newData.group=data[i].parent.name;
								newData.push(newData)
							}
							return newData;
							
						},
					    prompt:message("csh.common.please.select"),
						onSelect: function(rec){ 
				            var url = '../vehicleBrandDetail/findVehicleBrandDetailByLine.jhtml?vehicleLineId='+rec.id;    
				            $('#vehicleSelectVehicleBrandDetail-add').combobox('clear');
				            $('#vehicleSelectVehicleBrandDetail-add').combobox('reload', url);    
				        }

					});
			    	$("#vehicleSelectVehicleBrandDetail-add").combobox({
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select")
					});
			    	$('#addVehicle_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#vehicle-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			var _dialog = $('#editVehicle').dialog({    
				title: message("csh.common.edit"),     
			    width: 700,    
			    height: 450,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../vehicle/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#vehicle_form').form('validate');
						if(validate){
							$.ajax({
								url:"../vehicle/update.jhtml",
								type:"post",
								data:$("#editVehicle_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editVehicle').dialog("close");
										$("#vehicle-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editVehicle').dialog("close");
					}
			    }],
			    onLoad:function(){
			    	$("#vehicleSelectVehicleBrand-edit").combobox({
			    		url: '../vehicleBrand/findAllVehicleBrand.jhtml',
					    valueField:'id',
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
					    formatter: function(row){
							var opts = $(this).combobox('options');
							return row[opts.textField];
						},
						onSelect: function(rec){    
				            var url = '../vehicleLine/findVehicleLineByBrand.jhtml?vehicleBrandId='+rec.id;
				            $('#vehicleSelectVehicleLine-edit').combobox('clear');
				            $('#vehicleSelectVehicleLine-edit').combobox('reload', url);    
				        },
						onChange:function(newValue, oldValue){
							var url = '../vehicleLine/findVehicleLineByBrand.jhtml?vehicleBrandId='+newValue;
				            $('#vehicleSelectVehicleLine-edit').combobox('clear');
				            $('#vehicleSelectVehicleLine-edit').combobox('reload', url);
						}

					});
			    	$("#vehicleSelectVehicleLine-edit").combobox({
			    		url : '../vehicleLine/findVehicleLineByBrand.jhtml',
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
					    groupField:'group',
						loadFilter :function(data) {
							var newData=new Array()
							for(var i=0;i<data.length;i++){
								var newDataItem = null;
								newData.name = data[i].name;
								newData.id = data[i].id;
								newData.group=data[i].parent.name;
								newData.push(newData)
							}
							return newData;
						},
						onSelect: function(rec){ 
				            var url = '../vehicleBrandDetail/findVehicleBrandDetailByLine.jhtml?vehicleLineId='+rec.id;
				            $('#vehicleSelectVehicleBrandDetail-edit').combobox('clear');
				            $('#vehicleSelectVehicleBrandDetail-edit').combobox('reload', url);    
				        },
				        onChange:function(newValue, oldValue){
				        	var url = '../vehicleBrandDetail/findVehicleBrandDetailByLine.jhtml?vehicleLineId='+newValue;
				            $('#vehicleSelectVehicleBrandDetail-edit').combobox('clear');
				            $('#vehicleSelectVehicleBrandDetail-edit').combobox('reload', url);    
						}

					});
			    	$("#vehicleSelectVehicleBrandDetail-edit").combobox({
			    		url:'../vehicleBrandDetail/findVehicleBrandDetailByLine.jhtml',
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select")
					});
			    	$('#editVehicle_form').show();
			    	$("#vehicleSelectVehicleBrand-edit").combobox("setValue",$("#vehicleSelectVehicleBrand-edit").attr("data-value"));
			    	$("#vehicleSelectVehicleLine-edit").combobox("setValue",$("#vehicleSelectVehicleLine-edit").attr("data-value"));
			    	$("#vehicleSelectVehicleBrandDetail-edit").combobox("setValue",$("#vehicleSelectVehicleBrandDetail-edit").attr("data-value"));
			    }
			});  
		},
		remove:function(){
			listRemove('vehicle-table-list','../vehicle/delete.jhtml');
		},
		realTimeCarCondition:function(){
			var _select_row = $('#vehicle-table-list').datagrid('getSelected');
			if( _select_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			$('#realTimeCarCondition').dialog({
			    title: message("csh.vehicle.realTimeCarCondition"),    
			    width: 700,    
			    height: 620,
			    iconCls:'icon-mini-add',
			    cache: false,
			    href:'../vehicle/realTimeCarCondition.jhtml?deviceId='+_select_row.deviceNo,
			    buttons:[{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#realTimeCarCondition').dialog("close");
						 $("#realTimeCarCondition_form").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$('#realTimeCarCondition_form').show();
			    	var point = new BMap.Point($('#realTimeCarConditonLon').val(),$('#realTimeCarConditonLat').val());
			    	var map = new BMap.Map("vehicleLocationMap");  
			    	map.centerAndZoom(point,13);// 初始化地图,设置中心点坐标和地图级别。
			    	map.enableScrollWheelZoom();//启用滚轮放大缩小
			    	map.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
			    	map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
			    	map.addControl(new BMap.OverviewMapControl()); //添加缩略地图控件
			    	
			    	BMap.Convertor.translate(point,0,function (point){
			    		var marker = new BMap.Marker(point);  // 创建标注
			    		map.addOverlay(marker);               // 将标注添加到地图中
			    		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
			    	}); 
			    },
			});  
		},
		vehicleDailyReport:function(){
			var _select_row = $('#vehicle-table-list').datagrid('getSelected');
			if( _select_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			var params=new Object();
				params.vehicleId=_select_row.id;
			
			if($('#queryReportDate').length){
				params.date = $('#queryReportDate').datebox('getValue');
			}
			$('#vehicleDailyReport').dialog({
			    title: message("csh.vehicle.vehicleDailyReport"),    
			    width: 600,    
			    height: 350,
			    href:'../vehicle/vehicleDailyReport.jhtml?vehicleId='+_select_row.id,
			    method:"get",
			    queryParams:params,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#vehicleDailyReport').dialog("close");
						 $("#vehicleDailyReport_form").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$('#reportVehicleId').val(_select_row.id);
			    	$('#queryReportDate').datebox({
			    	    onSelect: function(date){
			    	    	$.ajax({
								url:"../vehicle/getVehicleDailyData.jhtml",
								type:"post",
								data:{
									vehicleId:$('#reportVehicleId').val(),
									date:date
								},
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										$('reportDailyMileage').textbox('setValue',result.dailyMileage);
										$('reportAverageFuelConsumption').textbox('setValue',result.averageFuelConsumption);
										$('reportFuelConsumption').textbox('setValue',result.fuelConsumption);
										$('reportCost').textbox('setValue',result.cost);
										$('reportAverageSpeed').textbox('setValue',result.averageSpeed);
										$('reportEmergencybrakecount').textbox('setValue',result.emergencybrakecount);
										$('reportSuddenturncount').textbox('setValue',result.suddenturncount);
										$('reportRapidlyspeedupcount').textbox('setValue',result.rapidlyspeedupcount);
									}else{
										alertErrorMsg();
									}
								}
							});
			    	    }
			    	});


			    	$('#vehicleDailyReport_form').show();
			    },
			});  
		},
		showFaultDetail:function(faultCode){
			$('#faultCodeDetail').dialog({
			    title: message("csh.vehicle.faultCode"),    
			    width: 600,    
			    height: 400,
			    iconCls:'icon-mini-add',
			    cache: false,
			    href:'../vehicle/showFaultDetail.jhtml?faultCode='+faultCode,
			    buttons:[{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#faultCodeDetail').dialog("close");
						 $("#realTimeCarCondition_form").form("reset");
					}
			    }],
			    onLoad:function(){}
			});
		},
		showAllVehicle:function(){
			var timer;
			$('#allVehicleStatus').dialog({
			    title: message("csh.vehicle.allVehicleStatus"),    
			    width: document.body.clientWidth,    
			    height: document.body.clientHeight,
			    iconCls:'icon-mini-add',
			    cache: false,
			    href:'../vehicle/allVehicleStatus.jhtml',
			    buttons:[{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#allVehicleStatus').dialog("close");
						 $("#allVehicleStatus_form").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$("#allVehicleStatusMap").css("height", document.body.clientHeight-100);
			    	$('#allVehicleStatus_form').show();
			    	
			    	var map = new BMap.Map("allVehicleStatusMap"); 
			    	
			    	var local = new BMap.Point($('#tenantInfoLon').val(),$('#tenantInfoLat').val())
			    	map.centerAndZoom(local,13);// 初始化地图,设置中心点坐标和地图级别。
			    	map.enableScrollWheelZoom();//启用滚轮放大缩小
			    	map.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
			    	map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
			    	map.addControl(new BMap.OverviewMapControl()); //添加缩略地图控件
			    	
			    	timer = setInterval(function () { loadAllVehicleStatus(map); },"5000")
//			    	loadAllVehicleStatus();
			    },
			    onClose:function(){
			    	clearInterval(timer);
			    }
			});
		}
		
};
$(function(){
	$("#vehicle-table-list").datagrid({
		title:message("csh.vehicle.list"),
		fitColumns:true,
		toolbar:"#vehicle_manager_tool",
		url:'../vehicle/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#vehicleDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 700,    
			    height: 450, 
			    cache: false,
			    modal: true,
			    href:'../vehicle/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#vehicleDetail').dialog("close");
					}
			    }],
			    onLoad:function(){
			    	$("#vehicleSelectVehicleBrand-detail").combobox({
			    		url: '../vehicleBrand/findAllVehicleBrand.jhtml',
					    valueField:'id',
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
					});
			    	$("#vehicleSelectVehicleLine-detail").combobox({
			    		url : '../vehicleLine/findVehicleLineByBrand.jhtml',
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select")
					});
			    	$("#vehicleSelectVehicleBrandDetail-detail").combobox({
			    		url:'../vehicleBrandDetail/findVehicleBrandDetailByLine.jhtml',
					    valueField:'id',    
					    textField:'name',
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select")
					});
			    	$('#editVehicle_form').show();
			    	$("#vehicleSelectVehicleBrand-detail").combobox("setValue",$("#vehicleSelectVehicleBrand-detail").attr("data-value"));
			    	$("#vehicleSelectVehicleLine-detail").combobox("setValue",$("#vehicleSelectVehicleLine-detail").attr("data-value"));
			    	$("#vehicleSelectVehicleBrandDetail-detail").combobox("setValue",$("#vehicleSelectVehicleBrandDetail-detail").attr("data-value"));
			    }
			    
			});   
		},
		onLoadSuccess:function(data){
			$("#totalRecord").val(data.total);
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.vehicle.endUser"),field:"endUser",sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.userName;
			    	  }
		      	  }},
			   {title:message("csh.endUser.mobileNum"),field:"mobileNum",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(row.endUser != null){
			    		  return  row.endUser.mobileNum;
			    	  }
		      	  }},
		      {title:message("csh.vehicle.plate"),field:"plate",sortable:true},
		      {title:message("csh.vehicle.vehicleBrand"),width:100,field:"vehicleBrand",sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(row != null){
			    		  return  row.vehicleBrandDetail.vehicleLine.vehicleBrand.name;
			    	  }
		      	  }},
		      {title:message("csh.vehicle.vehicleLine"),width:100,field:"vehicleLine",sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(row != null){
			    		  return  row.vehicleBrandDetail.vehicleLine.name;
			    	  }
		      	  }},
		      {title:message("csh.vehicle.vehicleBrandDetail"),width:100,field:"vehicleBrandDetail",sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.name;
			    	  }
		      	  }},
		      {title:message("csh.vehicle.vehicleNo"),field:"vehicleNo",sortable:true},
		      {title:message("csh.vehicle.wainingInfo"),field:"wainingInfo",sortable:true},
	      	  {title:message("csh.vehicle.dashboardMileage"),field:"dashboardMileage",sortable:true},
	      	  {title:message("csh.vehicle.dashboardBV"),field:"dashboardBV",sortable:true},
		      {title:message("csh.vehicle.device"),field:"deviceNo",sortable:false},
		      {title:message("csh.vehicle.deviceOnLine"),field:"isOnline",width:70,sortable:false,
		    	  formatter: function(value,row,index){
			    	  if(value ){
			    		  return message("csh.vehicle.deviceOnLine.online");
			    	  }else{
			    		  return message("csh.vehicle.deviceOnLine.offline");
			    	  }
		      	  },
		      	  styler: function(value,row,index){
						if (value){
							return 'color:#008000;';
						}else{
							return 'color:#FF0000;';
						}
					}  
		      },
		      {title:message("csh.vehicle.faultCode"),field:"faultCodeSet",sortable:false,
		    	  formatter:  function(value,row,index){
		    		  var str = "";
		    		  for(var i=0; i< value.length;i++){
		    			  str=str+"<a href='#' onclick='vehicle_manager_tool.showFaultDetail(\""+value[i]+"\")'>"+value[i]+"</a> "
		    		  }
		    		  return str;
				}},
		      {title:message("csh.vehicle.obdStatusTime"),field:"obdStatusTime",width:130,sortable:true,formatter: function(value,row,index){
		    	  if(value != null){
						return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					}else{
						return "";
					}
				}
		      },
		      {title:message("csh.common.createDate"),field:"createDate",width:130,sortable:true,formatter: function(value,row,index){
		    	  if(value != null){
						return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					}else{
						return "";
					}
				}
		      }
		   ]
		]
	});

	
	$("#vehicle-search-btn").click(function(){
	  var _queryParams = $("#vehicle-search-form").serializeJSON();
	  $('#vehicle-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#vehicle-table-list").datagrid('reload');
	})
})
