var vehicleStatus_manager_tool = {
		realTimeCarCondition:function(){
			var _select_row = $('#vehicleStatus-table-list').datagrid('getSelected');
			if( _select_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			$('#realTimeCarCondition').dialog({
			    title: message("csh.vehicle.realTimeCarCondition"),    
			    width: 600,    
			    height: 350,
			    iconCls:'icon-mini-add',
			    cache: false,
			    href:'../vehicleStatus/realTimeCarCondition.jhtml?deviceId='+_select_row.id,
			    buttons:[{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#realTimeCarCondition').dialog("close");
						 $("#realTimeCarCondition_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#realTimeCarCondition_form').show();
			    },
			});  
		},
		vehicleDailyReport:function(){
			var _select_row = $('#vehicleStatus-table-list').datagrid('getSelected');
			if( _select_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var params=new Object();
				params.vehicleId=_select_row.id;
				debugger;
			
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
		}
		
};

$(function(){
	$("#vehicleStatus-table-list").datagrid({
		title:message("csh.vehicle.list"),
		fitColumns:true,
		toolbar:"#vehicle_manager_tool",
		url:'../vehicleStatus/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
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
		      {title:message("csh.vehicle.vehicleNo"),field:"vehicleNo",sortable:true},
	      	  {title:message("csh.vehicle.dashboardMileage"),field:"dashboardMileage",sortable:true},
	      	  {title:message("csh.vehicle.dashboardBV"),field:"dashboardBV",sortable:true},
		      {title:message("csh.vehicle.device"),field:"deviceNo",width:100,sortable:false},
		      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      },
		   ]
		]
	});

	
	$("#vehicleStatus-search-btn").click(function(){
	  var _queryParams = $("#vehicleStatus-search-form").serializeJSON();
	  $('#vehicleStatus-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#vehicleStatus-table-list").datagrid('reload');
	})
})
