var pushMessageInfo_manager_tool = {
		add:function(){
			$('#addPushMessageInfo').dialog({
			    title: message("csh.endUser.add"),    
			    width: 700,    
			    height: 550,
			    href:'../messageInfo/add.jhtml?sendType=PUSH',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addPushMessageInfo_form').form('validate');
						var options = $("#endUserPushMultiselect_to").children();
						var ids=[];
						debugger;
						for(var i=0;i<options.length;i++){
							ids.push($(options[i]).val());
						}
						$("#addPushMessageInfo_form").append("<input type='hidden' name='ids' value="+ids+"></input>")
						if(validate){
								$.ajax({
									url:"../messageInfo/add.jhtml",
									type:"post",
									data:$("#addPushMessageInfo_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addPushMessageInfo').dialog("close")
											$("#addPushMessageInfo_form").form("reset");
											$("#pushMessageInfo-table-list").datagrid('reload');
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
						 $('#addPushMessageInfo').dialog("close");
						 $("#addPushMessageInfo_form").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$('#endUserPushFilter').textbox({
			    		onChange:function(){
				    		$.ajax({
								url:"../vehicle/findVehicleUserInfoUnderTenant.jhtml",
								type:"post",
								data:{endUserFilter:$("#endUserPushFilter").val()},
								beforeSend:function(){
								},
								success:function(result,response,status){
									$('#endUserPushMultiselect').empty();
									for(var i=0; i< result.length;i++){
										$('#endUserPushMultiselect').append("<option value='"+result[i].endUser.id+"'>"+result[i].plate+"("+result[i].endUser.userName+":"+result[i].endUser.mobileNum+")</option>")
									}
								}
							});
			    		}
			    	});
			    	$.ajax({
						url:"../vehicle/findVehicleUserInfoUnderTenant.jhtml",
						type:"post",
						data:{endUserFilter:$("#endUserPushFilter").val()},
						beforeSend:function(){
						},
						success:function(result,response,status){
							$('#endUserPushMultiselect').empty();
							for(var i=0; i< result.length;i++){
								$('#endUserPushMultiselect').append("<option value='"+result[i].endUser.id+"'>"+result[i].plate+"("+result[i].endUser.userName+":"+result[i].endUser.mobileNum+")</option>")
							}
						}
					});
			    	$('#endUserPushMultiselect').multiselect({
			    		right:'#endUserPushMultiselect_to',
			    		rightAll:'#js_right_All_1',
			    		rightSelected:'#js_right_Selected_1',
			    		leftSelected:'#js_left_Selected_1',
			    		leftAll:'#js_left_All_1'
			    	});
			    	$('#addPushMessageInfo_form').show();
			    },
			    onClose:function(){
			    	$('#addPushMessageInfo').empty();
			    }
			});  
		},
		details:function(){
			var _details_row = $('#pushMessageInfo-table-list').datagrid('getSelected');
			if( _details_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			
			$('#pushMessageInfoDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 600,    
			    height: 450, 
			    cache: false,
			    modal: true,
			    href:'../messageInfo/details.jhtml?id='+_details_row.id+'&sendType="PUSH"',
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#pushMessageInfoDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#pushMessageInfoDetail').empty();
			    }
			});   
		
		},
		remove:function(){
			listRemove('pushMessageInfo-table-list','../pushMessageInfo/delete.jhtml');
		}
};

$(function(){
	$("#pushMessageInfo-table-list").datagrid({
		title:message("csh.messageInfo.list"),
		fitColumns:true,
		toolbar:"#pushMessageInfo_manager_tool",
		url:'../messageInfo/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		queryParams: {
			sendType: 'PUSH'
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
	
	$("#pushMessageInfo-search-btn").click(function(){
	  var _queryParams = $("#pushMessageInfo-search-form").serializeJSON();
	  $('#pushMessageInfo-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#pushMessageInfo-table-list").datagrid('reload');
	});
	
})
