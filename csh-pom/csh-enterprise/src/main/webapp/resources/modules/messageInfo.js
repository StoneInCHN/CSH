var messageInfo_manager_tool = {
		add:function(){
			$('#addMessageInfo').dialog({
			    title: message("csh.endUser.add"),    
			    width: 700,    
			    height: 550,
			    href:'../messageInfo/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addMessageInfo_form').form('validate');
						var options = $("#endUserMultiselect_to").children();
						var ids=[];
						debugger;
						for(var i=0;i<options.length;i++){
							ids.push($(options[i]).val());
						}
						$("#addMessageInfo_form").append("<input type='hidden' name='ids' value="+ids+"></input>")
						if(validate){
								$.ajax({
									url:"../messageInfo/add.jhtml",
									type:"post",
									data:$("#addMessageInfo_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addMessageInfo').dialog("close")
											$("#addMessageInfo_form").form("reset");
											$("#messageInfo-table-list").datagrid('reload');
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
						 $('#addMessageInfo').dialog("close");
						 $("#addMessageInfo_form").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$('#endUserFilter').textbox({
			    		onChange:function(){
				    		$.ajax({
								url:"../vehicle/findVehicleUserInfoUnderTenant.jhtml",
								type:"post",
								data:{endUserFilter:$("#endUserFilter").val()},
								beforeSend:function(){
								},
								success:function(result,response,status){
									$('#endUserMultiselect').empty();
									for(var i=0; i< result.length;i++){
										$('#endUserMultiselect').append("<option value='"+result[i].endUser.id+"'>"+result[i].plate+"("+result[i].endUser.userName+":"+result[i].endUser.mobileNum+")</option>")
									}
								}
							});
			    		}
			    	});
			    	$.ajax({
						url:"../vehicle/findVehicleUserInfoUnderTenant.jhtml",
						type:"post",
						data:{endUserFilter:$("#endUserFilter").val()},
						beforeSend:function(){
						},
						success:function(result,response,status){
							$('#endUserMultiselect').empty();
							for(var i=0; i< result.length;i++){
								$('#endUserMultiselect').append("<option value='"+result[i].endUser.id+"'>"+result[i].plate+"("+result[i].endUser.userName+":"+result[i].endUser.mobileNum+")</option>")
							}
						}
					});
			    	$('#endUserMultiselect').multiselect({
			    		right:'#endUserMultiselect_to',
			    		rightAll:'#js_right_All_1',
			    		rightSelected:'#js_right_Selected_1',
			    		leftSelected:'#js_left_Selected_1',
			    		leftAll:'#js_left_All_1'
			    	});
			    	$('#addMessageInfo_form').show();
			    },
			    onClose:function(){
			    	$('#addMessageInfo').empty();
			    }
			});  
		},
		details:function(){
			var _details_row = $('#messageInfo-table-list').datagrid('getSelected');
			if( _details_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			
			$('#messageInfoDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 600,    
			    height: 450, 
			    cache: false,
			    modal: true,
			    href:'../messageInfo/details.jhtml?id='+_details_row.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#messageInfoDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#messageInfoDetail').empty();
			    }
			});   
		
		},
		remove:function(){
			listRemove('maintainReservation-table-list','../maintainReservation/delete.jhtml');
		}
};

$(function(){
	$("#messageInfo-table-list").datagrid({
		title:message("csh.messageInfo.list"),
		fitColumns:true,
		toolbar:"#messageInfo_manager_tool",
		url:'../messageInfo/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
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
	
	$("#messageInfo-search-btn").click(function(){
	  var _queryParams = $("#messageInfo-search-form").serializeJSON();
	  $('#messageInfo-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#messageInfo-table-list").datagrid('reload');
	});
	
})
