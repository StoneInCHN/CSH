var endUser_manager_tool = {
		add:function(){
			$('#addEndUser').dialog({
			    title: message("csh.endUser.add"),    
			    width: 400,    
			    height: 350,
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addEndUser_form').form('validate');
						
						if(validate){
								$.ajax({
									url:"../endUser/add.jhtml",
									type:"post",
									data:$("#addEndUser_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addEndUser').dialog("close")
											$("#addEndUser_form").form("reset");
											$("#endUser-table-list").datagrid('reload');
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
						 $('#addEndUser').dialog("close");
						 $("#addEndUser_form").form("reset");
					}
			    }],
			    onOpen:function(){
			    	$('#addEndUser_form').show();
			    },
			});  
		},
		edit:function(){
			var _edit_row = $('#endUser-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editEndUser').dialog({    
				title: message("csh.common.edit"),     
			    width: 400,    
			    height: 350,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../endUser/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#endUser_form').form('validate');
						if(validate){
							$.ajax({
								url:"../endUser/update.jhtml",
								type:"post",
								data:$("#editEndUser_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editEndUser').dialog("close");
										$("#endUser-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editEndUser').dialog("close").form("reset");
					}
			    }],
			    onLoad:function(){
			    	$("#editAccountStatus").combobox("setValue",$("#editAccountStatus").attr("data-value"))
			    }
			});  
		},
		remove:function(){
			listRemove('endUser-table-list','../endUser/delete.jhtml');
		}
};

$(function(){
	$("#maintainReservation-table-list").datagrid({
		title:message("csh.maintainReservation.list"),
		fitColumns:true,
		toolbar:"#maintainReservation_manager_tool",
		url:'../maintainReservation/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#maintainReservationDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 400,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../maintainReservation/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#maintainReservationDetail').dialog("close");
					}
			    }]
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.reservation.userName"),field:"endUser",sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(value != null){
		    			  return value.userName;
		    		  }else{
		    			  return null;
		    		  }
		    		  
		    	  }},
	    	  {title:message("csh.endUser.mobileNum"),field:"mobileNum",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(row != null){
		    			  return row.endUser.mobileNum;
		    		  }else{
		    			  return null;
		    		  }
		    		  
		    	  }},
		      {title:message("csh.reservation.plate"),field:"plate",width:100,sortable:true},
		      {title:message("csh.reservation.vehicleBrand"),field:"vehicleBrand",width:100,sortable:true},
		      {title:message("csh.reservation.reservationInfoFrom"),field:"reservationInfoFrom",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(value =="APP"){
		    			  return message("csh.reservation.reservationInfoFrom.app")
		    		  }else if(value =="CALL"){
		    			  return message("csh.reservation.reservationInfoFrom.call")
		    		  }
		    	  }},
		      {title:message("csh.reservation.reservationDate"),field:"reservationDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      },
		   ]
		]
	});

	
	$("#maintainReservation-search-btn").click(function(){
	  var _queryParams = $("#maintainReservation-search-form").serializeJSON();
	  $('#maintainReservation-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#maintainReservation-table-list").datagrid('reload');
	})
})
