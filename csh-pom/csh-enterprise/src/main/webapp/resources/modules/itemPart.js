var itemPart_manager_tool = {
		add:function(){
			$('#addItemPart').dialog({
			    title: message("csh.itemPart.add"),    
			    width: 700,    
			    height: 550,
			    href:'../itemPart/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addItemPart_form').form('validate');
						if(validate){
							var vehicleBrandIds = [];
							var vehicleLineIds = [];
							var vehicleBrandDetailIds = [];
							var _selected_brand=$('#itemPart_add_vehicleBrand').tree('getChecked');
							for(var i=0;i<_selected_brand.length;i++){
								var level = $('#itemPart_add_vehicleBrand').tree("getLevel",_selected_brand[i].target)
								if(level == 1){
									vehicleBrandIds.push(_selected_brand[i].id);
								}else if(level ==2){
									vehicleLineIds.push(_selected_brand[i].id);
								}else if(level ==3){
									vehicleLineIds.push(_selected_brand[i].id);
								}else if(level ==4){
									vehicleBrandDetailIds.push(_selected_brand[i].id);
								}
							}
							var _selected_row = $('#carServiceItem_itemPart-table-list').datagrid('getSelected');
							if( _selected_row == null ){
								$.messager.alert(message("csh.common.prompt"),message("csh.common.select.selectCarServiecItem"),'warning');
								return false;
							}
							var _data=$("#addItemPart_form").serialize()
										+"&carServiceItemId="+_selected_row.id
										+"&vehicleBrandIds="+vehicleBrandIds
										+"&vehicleLineIds="+vehicleLineIds
										+"&vehicleBrandDetailIds="+vehicleBrandDetailIds
										+"&selectAll="+$('#addItemPartVehicleBrand_selectAll').linkbutton('options').selected
										+"&isDefault="+$('#addItemPart_default').linkbutton('options').selected;
								$.ajax({
									url:"../itemPart/add.jhtml",
									type:"post",
									data:_data,
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addItemPart').dialog("close")
											$("#addItemPart_form").form("reset");
											$("#itemPart-table-list").datagrid('reload');
										}else{
											alertErrorMsg();
										}
									}
								});
							}
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addItemPart').dialog("close");
						 $("#addItemPart_form").form("reset");
					}
			    }],
				onLoad:function(){
					var loadCount = 1;
					$('#itemPart_add_vehicleBrand').tree({    
					    url:'../vehicleBrand/listVehicleBrand.jhtml?size=20',
					    cache:false,
			    	    animate:true,
			    	    lines:true,
			    	    onBeforeExpand:function(node){
			    	    	var level = $('#itemPart_add_vehicleBrand').tree("getLevel",node.target);
			    	    	if(level == 1){
			    	    		$('#itemPart_add_vehicleBrand').tree('options').url ='../vehicleBrand/listVehicleBrand.jhtml?vehicleBrandId='+node.id+'&size=20';
			    	    	}else if(level == 2){
			    	    		$('#itemPart_add_vehicleBrand').tree('options').url ='../vehicleBrand/listVehicleBrand.jhtml?vehicleLineParentId='+node.id+'&size=20';
			    	    	}else if(level == 3){
			    	    		$('#itemPart_add_vehicleBrand').tree('options').url ='../vehicleBrand/listVehicleBrand.jhtml?vehicleLineId='+node.id+'&size=20';
			    	    	}else{
			    	    		$('#itemPart_add_vehicleBrand').tree('options').url ='';
			    	    	}
			    	    },
		    	    
					});
					$('#addItemPartVehicleBrand_load').click(function(){
						$.ajax({
							url:"../vehicleBrand/listVehicleBrand.jhtml?size=20&selectCount="+loadCount,
							type:"post",
							success:function(result,response,status){
								if(result.length>0){
									$('#itemPart_add_vehicleBrand').tree('append', {
										data: result
									});
									loadCount++;
								}else{
									$('#addItemPartVehicleBrand_load').hide();
								}
							},
							beforeSend:function(){
								$('#addItemPartVehicleBrand_load').attr('disabled',"true");
							},
							complete:function(){
								$('#addItemPartVehicleBrand_load').removeAttr("disabled");
							}
						});
					});
					$('#addItemPartVehicleBrand_selectAll').bind('click', function(){    
				        if($('#addItemPartVehicleBrand_selectAll').linkbutton('options').selected){
				        	$('.vehicleBrand').hide();
				        }else{
				        	$('.vehicleBrand').show();
				        }
				    }); 
				},
			    onClose:function(){
			    	$('#addItemPart').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#itemPart-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			var deleteBrandItem = [];
			var deleteLineParentItem = [];
			var deleteLineChildItem = [];
			var deleteBrandDetailItem = [];
			
			var selectBrandItem = [];
			var selectLineParentItem = [];
			var selectLineChildItem = [];
			var selectBrandDetailItem = [];
			var _dialog = $('#editItemPart').dialog({    
				title: message("csh.common.edit"),     
			    width: 700,    
			    height: 550,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../itemPart/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editItemPart_form').form('validate');
						
						
						if(validate){
							var _data=$("#editItemPart_form").serialize()
							+"&deleteBrandItemIds="+deleteBrandItem
							+"&deleteLineParentItemIds="+deleteLineParentItem
							+"&deleteLineChildItemIds="+deleteLineChildItem
							+"&deleteBrandDetailItemIds="+deleteBrandDetailItem
							+"&selectBrandItemIds="+selectBrandItem
							+"&selectLineParentItemIds="+selectLineParentItem
							+"&selectLineChildItemIds="+selectLineChildItem
							+"&selectBrandDetailItemIds="+selectBrandDetailItem
							+"&selectAll="+$('#editItemPartVehicleBrand_selectAll').linkbutton('options').selected
							+"&isDefault="+$('#editItemPart_default').linkbutton('options').selected;
							debugger;
							$.ajax({
								url:"../itemPart/update.jhtml",
								type:"post",
								data:_data,
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editItemPart').dialog("close");
										$("#itemPart-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editItemPart').dialog("close")
					}
			    }],
			    onLoad:function(){

					var loadCount = 1;
					var itemPartId= $('#editItemPartId').val();
					var selected = false;
					$('#itemPart_edit_vehicleBrand').tree({    
					    url:'../vehicleBrand/listVehicleBrand.jhtml?itemPartId='+itemPartId+"&size=20",
					    cache:false,
			    	    animate:true,
			    	    lines:true,
			    	    onBeforeExpand:function(node){
			    	    	var level = $('#itemPart_edit_vehicleBrand').tree("getLevel",node.target);
			    	    	if(level == 1){
			    	    		$('#itemPart_edit_vehicleBrand').tree('options').url ='../vehicleBrand/listVehicleBrand.jhtml?vehicleBrandId='+node.id+'&itemPartId='+itemPartId+"&size=20";
			    	    	}else if(level == 2){
			    	    		$('#itemPart_edit_vehicleBrand').tree('options').url ='../vehicleBrand/listVehicleBrand.jhtml?vehicleLineParentId='+node.id+'&itemPartId='+itemPartId+"&size=20";
			    	    	}else if(level == 3){
			    	    		$('#itemPart_edit_vehicleBrand').tree('options').url ='../vehicleBrand/listVehicleBrand.jhtml?vehicleLineId='+node.id+'&itemPartId='+itemPartId+"&size=20";
			    	    	}else{
			    	    		$('#itemPart_edit_vehicleBrand').tree('options').url ='';
			    	    	}
			    	    	if(node.checked){
			    	    		selected=true;
			    	    	}else{
			    	    		selected=false;
			    	    	}
			    	    },
			    	    onExpand:function(node){
			    	    	if(selected){
			    	    		$('#itemPart_edit_vehicleBrand').tree('check', node.target);
			    	    	}
			    	    	
			    	    },
			    	    onSelect:function(node){
			    	    	debugger;
			    	    },
			    	    onCheck:function(node, checked){
			    	    	debugger;
			    	    	var level = $('#itemPart_edit_vehicleBrand').tree("getLevel",node.target);
			    	    	if(level == 1){
			    	    		if(checked){
			    	    			if($.inArray(node.id, selectBrandItem) == -1){
			    	    				selectBrandItem.push(node.id);
			    	    			}
			    	    			if($.inArray(node.id, deleteBrandItem) != -1){
			    	    				deleteBrandItem=deleteByValue(deleteBrandItem,node.id)
			    	    			}
			    	    		}else{
			    	    			if($.inArray(node.id, deleteBrandItem) == -1){
			    	    				deleteBrandItem.push(node.id);
			    	    			}
			    	    			if($.inArray(node.id, selectBrandItem) != -1){
			    	    				selectBrandItem=deleteByValue(selectBrandItem,node.id)
			    	    			}
			    	    		}
			    	    	}else if(level == 2){
			    	    		if(checked){
			    	    			if($.inArray(node.id, selectLineParentItem) == -1){
			    	    				selectLineParentItem.push(node.id);
			    	    			}
			    	    			if($.inArray(node.id, deleteLineParentItem) != -1){
			    	    				deleteLineParentItem=deleteByValue(deleteLineParentItem,node.id)
			    	    			}
			    	    		}else{
			    	    			if($.inArray(node.id, deleteLineParentItem) == -1){
			    	    				deleteLineParentItem.push(node.id);
			    	    			}
			    	    			if($.inArray(node.id, selectLineParentItem) != -1){
			    	    				selectLineParentItem=deleteByValue(selectLineParentItem,node.id)
			    	    			}
			    	    		}
			    	    	}else if(level == 3){
			    	    		if(checked){
			    	    			if($.inArray(node.id, selectLineChildItem) == -1){
			    	    				selectLineChildItem.push(node.id);
			    	    			}
			    	    			if($.inArray(node.id, deleteLineChildItem) != -1){
			    	    				deleteLineChildItem=deleteByValue(deleteLineChildItem,node.id)
			    	    			}
			    	    		}else{
			    	    			if($.inArray(node.id, deleteLineChildItem) == -1){
			    	    				deleteLineChildItem.push(node.id);
			    	    			}
			    	    			if($.inArray(node.id, selectLineChildItem) != -1){
			    	    				selectLineChildItem=deleteByValue(selectLineChildItem,node.id)
			    	    			}
			    	    		}
			    	    	}else if(level == 4){
			    	    		if(checked){
			    	    			if($.inArray(node.id, selectBrandDetailItem) == -1){
			    	    				selectBrandDetailItem.push(node.id);
			    	    			}
			    	    			if($.inArray(node.id, deleteBrandDetailItem) != -1){
			    	    				deleteBrandDetailItem=deleteByValue(deleteBrandDetailItem,node.id)
			    	    			}
			    	    		}else{
			    	    			if($.inArray(node.id, deleteBrandDetailItem) == -1){
			    	    				deleteBrandDetailItem.push(node.id);
			    	    			}
			    	    			if($.inArray(node.id, selectBrandDetailItem) != -1){
			    	    				selectBrandDetailItem=deleteByValue(selectBrandDetailItem,node.id)
			    	    			}
			    	    		}
			    	    	}
			    	    }
		    	    
					});
					$('#editItemPartVehicleBrand_load').click(function(){
						$.ajax({
							url:"../vehicleBrand/listVehicleBrand.jhtml?size=20&selectCount="+loadCount+'&itemPartId='+itemPartId,
							type:"post",
							success:function(result,response,status){
								if(result.length>0){
									$('#itemPart_edit_vehicleBrand').tree('append', {
										data: result
									});
									loadCount++;
								}else{
									$('#editItemPartVehicleBrand_load').hide();
								}
							},
							beforeSend:function(){
								$('#editItemPartVehicleBrand_load').attr('disabled',"true");
							},
							complete:function(){
								$('#editItemPartVehicleBrand_load').removeAttr("disabled");
							}
						});
					});
					$('#editItemPartVehicleBrand_selectAll').bind('click', function(){    
				        if($('#editItemPartVehicleBrand_selectAll').linkbutton('options').selected){
				        	$('.vehicleBrand').hide();
				        }else{
				        	$('.vehicleBrand').show();
				        }
				    }); 
				
			    },
			    onClose:function(){
			    	$('#editItemPart').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('itemPart-table-list','../itemPart/delete.jhtml');
		}
};

$(function(){
	$("#itemPart-table-list").datagrid({
		title:message("csh.itemPart.list"),
		fitColumns:true,
		toolbar:"#itemPart_manager_tool",
		url:'../itemPart/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#itemPartDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 700,    
			    height: 550, 
			    cache: false,
			    modal: true,
			    href:'../itemPart/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#itemPartDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#itemPartDetail').empty();
			    },
			    onLoad:function(){
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.itemPart.serviceItemPartName"),field:"serviceItemPartName",sortable:true},
		      {title:message("csh.itemPart.price"),field:"price",sortable:true},
		      {title:message("csh.itemPart.idDefault"),field:"isDefault",sortable:true,formatter: function(value,row,index){
		    	  if(value == true){
		    		  return message("csh.common.yes");
		    	  }else {
		    		  return message("csh.common.no");
		    	  }
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
	$("#carServiceItem_itemPart-table-list").datagrid({
		title:message("csh.carServiceItem.list"),
		fitColumns:true,
		url:'../carServiceItem/list.jhtml',  
		pagination:true,
		singleSelect:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		columns:[
		   [
		      {title:message("csh.carServiceItem.serviceItemName"),field:"serviceItemName",sortable:true},
		      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value != null){
						return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					}else{
						return "";
					}
				}
		      },
		   ]
		],
		onSelect:function(rowIndex, rowData){
			  $('#itemPart-table-list').datagrid('load',{
				  carServiceItemId:rowData.id
				});
	      },
	});
	
	$("#itemPart-search-btn").click(function(){
	  var _queryParams = $("#itemPart-search-form").serializeJSON();
	  $('#itemPart-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#itemPart-table-list").datagrid('reload');
	});
	$("#carServiceItem_itemPart-search-btn").click(function(){
		  var _queryParams = $("#carServiceItem_itemPart-search-form").serializeJSON();
		  $('#carServiceItem_itemPart-table-list').datagrid('options').queryParams = _queryParams;  
		  $("#carServiceItem_itemPart-table-list").datagrid('reload');
		});
	
})
