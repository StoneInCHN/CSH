var smsMessageInfo_manager_tool = {
		add:function(){
			$('#addSmsMessageInfo').dialog({
			    title: message("csh.endUser.add"),    
			    width: 700,    
			    height: 550,
			    href:'../messageInfo/add.jhtml?sendType=SMS',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addSmsMessageInfo_form').form('validate');
						var options = $("#endUserSmsMultiselect_to").children();
						var ids=[];
						debugger;
						for(var i=0;i<options.length;i++){
							ids.push($(options[i]).val());
						}
						$("#addSmsMessageInfo_form").append("<input type='hidden' name='ids' value="+ids+"></input>")
						if(validate){
								$.ajax({
									url:"../messageInfo/add.jhtml",
									type:"post",
									data:$("#addSmsMessageInfo_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addSmsMessageInfo').dialog("close")
											$("#addSmsMessageInfo_form").form("reset");
											$("#smsMessageInfo-table-list").datagrid('reload');
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
						 $('#addSmsMessageInfo').dialog("close");
						 $("#addSmsMessageInfo_form").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$('#endUserSmsFilter').textbox({
			    		onChange:function(){
				    		$.ajax({
								url:"../vehicle/findVehicleUserInfoUnderTenant.jhtml",
								type:"post",
								data:{endUserFilter:$("#endUserSmsFilter").val()},
								beforeSend:function(){
								},
								success:function(result,response,status){
									$('#endUserSmsMultiselect').empty();
									for(var i=0; i< result.length;i++){
										$('#endUserSmsMultiselect').append("<option value='"+result[i].endUser.id+"'>"+result[i].plate+"("+result[i].endUser.userName+":"+result[i].endUser.mobileNum+")</option>")
									}
								}
							});
			    		}
			    	});
			    	$.ajax({
						url:"../vehicle/findVehicleUserInfoUnderTenant.jhtml",
						type:"post",
						data:{endUserFilter:$("#endUserSmsFilter").val()},
						beforeSend:function(){
						},
						success:function(result,response,status){
							$('#endUserSmsMultiselect').empty();
							for(var i=0; i< result.length;i++){
								$('#endUserSmsMultiselect').append("<option value='"+result[i].endUser.id+"'>"+result[i].plate+"("+result[i].endUser.userName+":"+result[i].endUser.mobileNum+")</option>")
							}
						}
					});
			    	$('#endUserSmsMultiselect').multiselect({
			    		right:'#endUserSmsMultiselect_to',
			    		rightAll:'#js_right_All_1',
			    		rightSelected:'#js_right_Selected_1',
			    		leftSelected:'#js_left_Selected_1',
			    		leftAll:'#js_left_All_1'
			    	});
			    	$('#addSmsMessageInfo_form').show();
			    },
			    onClose:function(){
			    	$('#addSmsMessageInfo').empty();
			    }
			});  
		},
		details:function(){
			var _details_row = $('#smsMessageInfo-table-list').datagrid('getSelected');
			if( _details_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			
			$('#smsMessageInfoDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 600,    
			    height: 450, 
			    cache: false,
			    modal: true,
			    href:'../messageInfo/details.jhtml?id='+_details_row.id+'&sendType=SMS',
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#smsMessageInfoDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#smsMessageInfoDetail').empty();
			    }
			});   
		
		},
		remove:function(){
			listRemove('smsMessageInfo-table-list','../smsMessageInfo/delete.jhtml');
		}
};

$(function(){
	$("#smsMessageInfo-table-list").datagrid({
		title:message("csh.messageInfo.list"),
		fitColumns:true,
		toolbar:"#smsMessageInfo_manager_tool",
		url:'../messageInfo/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		queryParams: {
			sendType: 'SMS'
		},
		onDblClickRow : function (rowIndex, rowData){},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.messageInfo.title"),width:100,field:"messageTitle",sortable:true},
	    	  {title:message("csh.messageInfo.type"),width:100,field:"messageType",sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(value == "PERSONALMSG"){
		    			  return message("csh.messageInfo.type.PERSONALMSG");
		    		  }else if(value == "NEWSMSG"){
		    			  return message("csh.messageInfo.type.NEWSMSG");;
		    		  }else if(value == "PROMOTION"){
		    			  return message("csh.messageInfo.type.PROMOTION");;
		    		  }
		    	  }},
	    	  {title:message("csh.common.createDate"),width:100,field:"createDate",sortable:true,
		    	  formatter:function(value,row,index){
		    		 return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
		    	  }},
		   ]
		]
	});
	
	$("#smsMessageInfo-search-btn").click(function(){
	  var _queryParams = $("#smsMessageInfo-search-form").serializeJSON();
	  $('#smsMessageInfo-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#smsMessageInfo-table-list").datagrid('reload');
	});
	
})
