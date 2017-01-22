var deviceInfo_manager_tool = {
		//售出
		deviceStatusUpdate:function(deviceStatus){
			var _rows = $("#deviceInfo-table-list").datagrid('getSelections');
			if (_rows.length == 0) {
				$.messager.alert(message("csh.common.prompt"),
						message("csh.common.select.editRow"), 'warning');
			}else if(deviceStatus !="SALEOUT" && _rows.length >1){
				$.messager.alert(message("csh.common.prompt"),message("该修改不能进行批量操作，只能选择一条记录！"), 'warning');
			} else {
				var _ids = [];
				for (var i = 0; i < _rows.length; i++) {
					_ids.push(_rows[i].id);
				}
				if (_ids.length > 0) {
					$.messager.confirm(message("csh.common.confirm"),
							message("csh.deviceInfo.statusUpdate.confirm"), function(r) {
								if (r) {
									$.ajax({
										url : "../deviceInfo/deviceStatusUpdate.jhtml",
										type : "post",
										traditional : true,
										data : {
											"ids" : _ids,
											"deviceStatus":deviceStatus
										},
										beforeSend : function() {
											$.messager.progress({
												text : message("csh.common.progress")
											});
										},
										success : function(result, response, status) {
											$.messager.progress('close');
											var resultMsg = result.content;
											if (response == "success") {
												showSuccessMsg(resultMsg);
												$("#deviceInfo-table-list").datagrid('reload');
											} else {
												alertErrorMsg();
											}
										}
									});
								}
							})
				}

			}
		},
		//解绑车辆
		unBind:function(){
			var _rows = $("#deviceInfo-table-list").datagrid('getSelections');
			if( _rows.length >1){
					$.messager.alert(message("csh.common.prompt"),message("该修改不能进行批量操作，只能选择一条记录！"), 'warning');
					return false;
			}
			var _edit_row = $('#deviceInfo-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');  
				return false;
			}else 
			if(_edit_row.bindStatus=="UNBINDED"){
				$.messager.alert(message("csh.deviceInfo.bindStatus"),message("csh.deviceInfo.bindStatus.unBinded"),'warning');  
				return false;
			}
			$.messager.confirm('确认解绑', '确认解绑？', function(r){
				if (!r){
					return false;
				}else{
					$.ajax({
						url:"../deviceInfo/unBind.jhtml?id="+_edit_row.id,
						type:"get",
						beforeSend:function(){
							$.messager.progress({
								text:message("csh.common.saving")
							});
						},
						success:function(result,response,status){
							$.messager.progress('close');
							showSuccessMsg(result.content);
							$("#deviceInfo-table-list").datagrid('reload');
						},
						error:function (XMLHttpRequest, textStatus, errorThrown) {
							$.messager.progress('close');
							alertErrorMsg();
						}
					});
				}
			});
			
		},
		//绑定车辆
		bind:function(){
				var _rows = $("#deviceInfo-table-list").datagrid('getSelections');
				if( _rows.length >1){
						$.messager.alert(message("csh.common.prompt"),message("该修改不能进行批量操作，只能选择一条记录！"), 'warning');
						return false;
				}
				var _edit_row = $('#deviceInfo-table-list').datagrid('getSelected');
				
				if( _edit_row == null ){
					$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');  
					return false;
				}
				if(_edit_row.bindStatus=="BINDED"){
					$.messager.alert(message("csh.deviceInfo.bindStatus"),message("csh.deviceInfo.bindStatus.binded"),'warning');  
					return false;
				}
				$('#bindDevice').dialog({
				    title: message("csh.deviceInfo.bindVehicle"),    
				    width: 300,    
				    height: 250,
				    iconCls:'icon-mini-add',
				    cache: false, 
				    buttons:[{
				    	text:message("csh.common.save"),
				    	iconCls:'icon-save',
						handler:function(){
							debugger;
							var validate = $('#bindDevice_form').form('validate');
							var _edit_row = $('#deviceInfo-table-list').datagrid('getSelected');
							
							
							$('#bindDevice_form').append('<input type="hidden" name="deviceId" value="'+_edit_row.id+'"/>')
							if(validate){
									$.ajax({
										url:"../deviceInfo/bind.jhtml",
										type:"post",
										data:$("#bindDevice_form").serialize(),
										beforeSend:function(){
											$.messager.progress({
												text:message("csh.common.saving")
											});
										},
										success:function(result,response,status){
											$.messager.progress('close');
											if(response == "success"){
												showSuccessMsg(result.content);
												$('#bindDevice').dialog("close")
												$("#bindDevice_form").form("reset");
												$("#deviceInfo-table-list").datagrid('reload');
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
							 $('#bindDevice').dialog("close");
							 $("#bindDevice_form").form("reset");
						}
				    }],
				    onOpen:function(){
				    	$('#bindDevice_form').show();
				    },
				});  
		}
};

$(function(){
	$("#deviceInfo-table-list").datagrid({
		title:message("csh.deviceInfo.list"),
		fitColumns:true,
		toolbar:"#deviceInfo_manager_tool",
		url:'../deviceInfo/list.jhtml',  
		pagination:true,
	//	singleSelect:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#deviceInfoDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../deviceInfo/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#deviceInfoDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.deviceInfo.deviceNO"),field:"deviceNo",width:100,sortable:true},
		      {title:message("csh.deviceInfo.deviceType"),field:"type",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.name;
			    	  }
		      	  }},
		      {title:message("csh.deviceInfo.bindStatus"),field:"bindStatus",width:100,sortable:true,
		      		styler: function(value,row,index){
		      			if(value == "BINDED"){
		      				return 'color:green;';
						}else if (value == "UNBINDED" || value == null){
							return 'color:red;';
						}
					},
		    	  formatter: function(value,row,index){
			    	  if(value == "BINDED"){
			    		  return  message("csh.deviceInfo.bindStatus.BINDED");
			    	  }else if (value == "UNBINDED" || value == null){
			    		  return  message("csh.deviceInfo.bindStatus.UNBINDED");
			    	  }
		      	  }  
		      },
		      {title:message("csh.deviceInfo.deviceStatus"),field:"deviceStatus",width:100,sortable:true,
		      		
		    	  formatter: function(value,row,index){
			    	  if(value == "SALEOUT"){
			    		  return  message("csh.deviceInfo.deviceStatus.SALEOUT");
			    	  }else if (value == "REFUNDED"){
			    		  return  message("csh.deviceInfo.bindStatus.REFUNDED");
			    	  }else{
			    		  return  message("csh.deviceInfo.bindStatus.ONSALE");
			    	  }
		      	  }  
		      },
		      {title:message("csh.deviceInfo.bindVehicle"),field:"vehicle",width:100,sortable:true,
		    	  formatter: function(value,row,index){
			    	  if(value != null){
			    		  return  value.plate;
			    	  }else {
			    		  return null;
			    	  }
		      	  }  
		      },
		      {title:message("csh.deviceInfo.bindTime"),field:"bindTime",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value != null)
		    	  		return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
		    	  	else
		    	  		return null;
				}
		      },
		      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value != null){
						return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					}else{
						return "";
					}
				}
		      },
		   ]
		]
	});

	
	$("#deviceInfo-search-btn").click(function(){
	  var _queryParams = $("#deviceInfo-search-form").serializeJSON();
	  $('#deviceInfo-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#deviceInfo-table-list").datagrid('reload');
	})
})
