var coupon_manager_tool = {
		add:function(){
			$('#addCoupon').dialog({
			    title: message("csh.coupon.add"),    
			    width: 850,    
			    height: 700,
			    href:'../coupon/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						debugger;
						var validate = $('#addCoupon_form').form('validate');
						var _ids = [];
						if($('#couponType-add').combobox('getValue') == 'SPECIFY'){
							var _rows = $("#coupon-add-carService-table-list").datagrid('getSelections');
							if (_rows.length == 0) {
								$.messager.alert(message("csh.common.prompt"),
										message("csh.common.select.carService"), 'warning');
							} else {
								
								for (var i = 0; i < _rows.length; i++) {
									_ids.push(_rows[i].id);
								}
							}
						}
						if(validate){
							$.ajax({
								url:"../coupon/add.jhtml?carServiceIds="+_ids,
								type:"post",
								data:$("#addCoupon_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
									if(response == "success"){
										showSuccessMsg(result.content);
										$('#addCoupon').dialog("close")
										$("#addCoupon_form").form("reset");
										$("#coupon-table-list").datagrid('reload');
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
						 $('#addCoupon').dialog("close");
						 $("#addCoupon_form").form("reset");
					}
			    }],
				onLoad:function(){
					$("#couponType-add").combobox({
						onChange: function(newValue, oldValue){
							if(newValue =='SPECIFY'){
								$(".couponSearchCarService-add").show();
								$("#coupon-add-carService-table-list").datagrid({
									title:message("csh.carService.list"),
									fitColumns:true,
									idField:"id",
									url:'../carService/list.jhtml',  
									pagination:true,
									loadMsg:message("csh.common.loading"),
									striped:true,
									columns:[
									   [
									      {field:'ck',checkbox:true},
									      {title:message("csh.carService.serviceName"),field:"serviceName",sortable:true},
									      {title:message("csh.carService.serviceCategory"),field:"serviceCategory",sortable:true,
									    	  formatter:function(value,row,index){
									    		  if(value != null){
									    			  return value.categoryName;
									    		  }else{
									    			  return "";
									    		  }
									    	  }},
									      {title:message("csh.carService.price"),field:"price",width:100,sortable:true,formatter:function(value,row,index){
								    		  if(value =="-1"){
								    			  return message("csh.carService.price.postpaid")
								    		  }else{
								    			  return value;
								    		  }
								    			  
								    	  }},
									      {title:message("csh.carService.promotionPrice"),field:"promotionPrice",width:100,sortable:true,formatter:function(value,row,index){
								    		  if(value =="-1"){
								    			  return message("csh.carService.price.postpaid")
								    		  }else{
								    			  return value;
								    		  }
								    	  }},
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
									onOpen:function(){
										$("#coupon-addServiceCategorySearch").combobox({    
										    valueField:'id',
										    textField:'categoryName',
										    url:"../serviceCategory/findAllServiceCategory.jhtml",
										    prompt:message("csh.common.please.select"),
									    	});
									}
								});
								$("#coupon-addCarService-search-btn").click(function(){
									  var _queryParams = $("#coupon-addCarService-search-form").serializeJSON();
									  $('#coupon-add-carService-table-list').datagrid('options').queryParams = _queryParams;  
									  $("#coupon-add-carService-table-list").datagrid('reload');
								});
							}else{
								$(".couponSearchCarService-add").hide();
							}
						}
					});
					
					$("#couponSendType-add").combobox({
						onChange: function(newValue, oldValue){
							if(newValue =='BIND'){
								$('.addCouponCountsClass').hide();
								$('#addCouponCounts').textbox({
									required:false,
									value:''
								});
								$('.addCouponDeadlineTimeClass').hide();
								$('.couponOverDeadlineTime-add').textbox({
									required:false,
									value:''
								});
								$('#couponOverDeadlineTime-add').datebox('setValue', '');
								$("#couponOverDueType-add").combobox({
									readonly:true,
									value:'BYDAY'
								})
							}else if(newValue = 'NORMAL'){
								$('.addCouponDeadlineTimeClass').show();
								$('.couponOverDeadlineTime-add').textbox({
									required:true
								});
								$('.addCouponCountsClass').show();
								$('#addCouponCounts').textbox({
									required:true,
								});
								$("#couponOverDueType-add").combobox({
									readonly:false,
									onChange: function(newValue, oldValue){
										if(newValue =='BYDAY'){
											$('.addCouponOverDueTimeClass').hide();
											$('#addCouponOverDueTime').textbox({
												required:false
											});
											$('#addCouponOverDueTime').datebox('setValue', '');
											$('#addCouponOverDueDay').textbox({
												required:true
											});
											$('.addCouponOverDueDayClass').show();
										}else if( newValue == 'BYDATE'){
											$('.addCouponOverDueTimeClass').show();
											$('.addCouponOverDueDayClass').hide();
											$('#addCouponOverDueDay').textbox({
												required:false,
												value:''
											});
											$('#addCouponOverDueTime').textbox({
												required:true
											});
										}
							        }

								});
							}
						}
					});
					$('#addCouponOverDueTime').datebox('calendar').calendar({
						validator: function(date){
							var now = new Date(new Date().Format("yyyy-M-d"));
							return now<=date ;
						},
						onChange:function(newValue, oldValue){
							$('#couponOverDeadlineTime-add').datebox('calendar').calendar({
								validator: function(date){
									var now = new Date(new Date().Format("yyyy-M-d"));
									return newValue>=date && date>=now;
								}
							});
						}
					});
					$('#couponOverDeadlineTime-add').datebox('calendar').calendar({
						validator: function(date){
							var now = new Date(new Date().Format("yyyy-M-d"));
							return now<=date ;
						},
						onChange:function(newValue, oldValue){
							$('#addCouponOverDueTime').datebox('calendar').calendar({
								validator: function(date){
									var now = new Date(new Date().Format("yyyy-M-d"));
									return newValue <=date && date>=now;
								},
							});
						}
					});
					
				},
			    onClose:function(){
			    	$("#carServiceUploader-add .uploadBtn").trigger("clearFileQuene");
			    	$('#addCarService').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#coupon-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.prompt"),message("csh.common.select.editRow"),'warning');
				return false;
			}
			var _dialog = $('#editCoupon').dialog({    
				title: message("csh.common.edit"),     
			    width: 850,    
			    height: 700,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../coupon/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editCoupon_form').form('validate');
						var _ids = [];
						if($('#couponType-edit').combobox('getValue') == 'SPECIFY'){
							var _rows = $("#coupon-edit-carService-table-list").datagrid('getSelections');
							if (_rows.length == 0) {
								$.messager.alert(message("csh.common.prompt"),
										message("csh.common.select.carService"), 'warning');
							} else {
								
								for (var i = 0; i < _rows.length; i++) {
									_ids.push(_rows[i].id);
								}
							}
						}
						if(validate){
								$.ajax({
									url:"../coupon/update.jhtml?carServiceIds="+_ids,
									type:"post",
									data:$("#editCoupon_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
											showSuccessMsg(result.content);
											$('#editCoupon').dialog("close");
											$("#coupon-table-list").datagrid('reload');
									}
								});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editCoupon').dialog("close")
					}
			    }],
			    onLoad:function(){

					$("#couponType-edit").combobox({
						onChange: function(newValue, oldValue){
							if(newValue =='SPECIFY'){
								$(".couponSearchCarService-edit").show();
								var carServiceListStr=$("#editCoupon_carServiceList").val();
								var carServiceList=eval(carServiceListStr);
								$("#coupon-edit-carService-table-list").datagrid({
									title:message("csh.carService.list"),
									fitColumns:true,
									idField:"id",
									url:'../carService/list.jhtml',  
									pagination:true,
									loadMsg:message("csh.common.loading"),
									striped:true,
									columns:[
									   [
									      {field:'ck',checkbox:true},
									      {title:message("csh.carService.serviceName"),field:"serviceName",sortable:true},
									      {title:message("csh.carService.serviceCategory"),field:"serviceCategory",sortable:true,
									    	  formatter:function(value,row,index){
									    		  if(value != null){
									    			  return value.categoryName;
									    		  }else{
									    			  return "";
									    		  }
									    	  }},
									      {title:message("csh.carService.price"),field:"price",width:100,sortable:true,formatter:function(value,row,index){
								    		  if(value =="-1"){
								    			  return message("csh.carService.price.postpaid")
								    		  }else{
								    			  return value;
								    		  }
								    			  
								    	  }},
									      {title:message("csh.carService.promotionPrice"),field:"promotionPrice",width:100,sortable:true,formatter:function(value,row,index){
								    		  if(value =="-1"){
								    			  return message("csh.carService.price.postpaid")
								    		  }else{
								    			  return value;
								    		  }
								    	  }},
									      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
									    	  if(value != null){
													return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
												}else{
													return "";
												}
											}
									      }
									   ]
									],
									onOpen:function(){
										$("#coupon-editServiceCategorySearch").combobox({    
										    valueField:'id',
										    textField:'categoryName',
										    url:"../serviceCategory/findAllServiceCategory.jhtml",
										    prompt:message("csh.common.please.select"),
									    	});
									},
									onLoadSuccess:function(data){
										var ids = data.rows;
										for(var i = 0;i<ids.length;i++){
											for(var j=0;j<carServiceList.length;j++){
												if(ids[i].id == carServiceList[j]){
													$("#coupon-edit-carService-table-list").datagrid('selectRecord',ids[i]);
												}
											}
										}
									}
								});
								$("#coupon-editCarService-search-btn").click(function(){
									  var _queryParams = $("#coupon-editCarService-search-form").serializeJSON();
									  $('#coupon-edit-carService-table-list').datagrid('options').queryParams = _queryParams;  
									  $("#coupon-edit-carService-table-list").datagrid('reload');
								});
							}else{
								$(".couponSearchCarService-edit").hide();
							}
						}
					});
					$("#couponSendType-edit").combobox({
						onChange: function(newValue, oldValue){
							if(newValue =='BIND'){
								$('.editCouponCountsClass').hide();
								$('#editCouponCounts').textbox({
									required:false,
									value:''
								});
								$('.editCouponDeadlineTimeClass').hide();
								$('.couponOverDeadlineTime-edit').textbox({
									required:false,
									value:''
								});
								$('#couponOverDeadlineTime-edit').datebox('setValue', '');
							}else if(newValue = 'NORMAL'){
								$('.editCouponDeadlineTimeClass').show();
								$('.couponOverDeadlineTime-edit').textbox({
									required:true
								});
								$('.editCouponCountsClass').show();
								$('#editCouponCounts').textbox({
									required:true
								});
								$("#couponOverDueType-edit").combobox({
									onChange: function(newValue, oldValue){
										if(newValue =='BYDAY'){
											$('.editCouponOverDueTimeClass').hide();
											$('#editCouponOverDueTime').textbox({
												required:false,
												value:''
											});
											$('#editCouponOverDueDay').textbox({
												required:true
											});
											$('.editCouponOverDueDayClass').show();
										}else if( newValue == 'BYDATE'){
											$('.editCouponOverDueTimeClass').show();
											$('.editCouponOverDueDayClass').hide();
											$('#editCouponOverDueDay').textbox({
												required:false,
												value:''
											});
											$('#editCouponOverDueTime').textbox({
												required:true
											});
										}
							        }

								});
							}
						}
					});
					$('#editCouponOverDueTime').datebox('calendar').calendar({
						validator: function(date){
							var now = new Date(new Date().Format("yyyy-M-d"));
							return now<=date ;
						},
						onChange:function(newValue, oldValue){
							$('#couponOverDeadlineTime-edit').datebox('calendar').calendar({
								validator: function(date){
									var now = new Date(new Date().Format("yyyy-M-d"));
									return newValue>=date && date>=now;
								}
							});
						}
					});
					$('#couponOverDeadlineTime-edit').datebox('calendar').calendar({
						validator: function(date){
							var now = new Date(new Date().Format("yyyy-M-d")) ;
							return now<=date ;
						},
						onChange:function(newValue, oldValue){
							$('#editCouponOverDueTime').datebox('calendar').calendar({
								validator: function(date){
									var now = new Date(new Date().Format("yyyy-M-d"));
									return newValue <=date && date>=now;
								},
							});
						}
					});
					var deadTime = $('#couponOverDeadlineTime-edit').datebox('getValue');
					debugger;
					var dt = new Date(deadTime.replace(/-/,"/"))
					if(deadTime != ''){
						$('#editCouponOverDueTime').datebox('calendar').calendar({
							validator: function(date){
								var now = new Date(new Date().Format("yyyy-M-d"));
								return now<=date && date <=dt;
							}
						});
					}
			    },
			    onClose:function(){
			    	$('#editCoupon').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('coupon-table-list','../coupon/delete.jhtml');
		}
};

$(function(){
	
	$("#coupon-table-list").datagrid({
		title:message("csh.coupon.list"),
		fitColumns:true,
		toolbar:"#coupon_manager_tool",
		url:'../coupon/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#couponDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 700,    
			    height: 550, 
			    cache: false,
			    modal: true,
			    href:'../coupon/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#couponDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#couponDetail').empty();
			    },
			    onLoad:function(){
					$("#couponType-detail").combobox({
						onChange: function(newValue, oldValue){
							if(newValue =='SPECIFY'){
								$(".couponSearchCarService-detial").show();
								var carServiceListStr=$("#couponDetail_carServiceList").val();
								var carServiceList=eval(carServiceListStr);
								$("#coupon-detail-carService-table-list").datagrid({
									title:message("csh.carService.list"),
									fitColumns:true,
									idField:"id",
									url:'../carService/list.jhtml',  
									pagination:true,
									loadMsg:message("csh.common.loading"),
									striped:true,
									columns:[
									   [
									      {field:'ck',checkbox:true},
									      {title:message("csh.carService.serviceName"),field:"serviceName",sortable:true},
									      {title:message("csh.carService.serviceCategory"),field:"serviceCategory",sortable:true,
									    	  formatter:function(value,row,index){
									    		  if(value != null){
									    			  return value.categoryName;
									    		  }else{
									    			  return "";
									    		  }
									    	  }},
									      {title:message("csh.carService.price"),field:"price",width:100,sortable:true,formatter:function(value,row,index){
								    		  if(value =="-1"){
								    			  return message("csh.carService.price.postpaid")
								    		  }else{
								    			  return value;
								    		  }
								    			  
								    	  }},
									      {title:message("csh.carService.promotionPrice"),field:"promotionPrice",width:100,sortable:true,formatter:function(value,row,index){
								    		  if(value =="-1"){
								    			  return message("csh.carService.price.postpaid")
								    		  }else{
								    			  return value;
								    		  }
								    	  }},
									      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
									    	  if(value != null){
													return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
												}else{
													return "";
												}
											}
									      }
									   ]
									],
									onLoadSuccess:function(data){
										var ids = data.rows;
										for(var i = 0;i<ids.length;i++){
											for(var j=0;j<carServiceList.length;j++){
												if(ids[i].id == carServiceList[j]){
													$("#coupon-detail-carService-table-list").datagrid('selectRecord',ids[i]);
												}
											}
										}
									}
								});
							}else{
								$(".couponSearchCarService-detail").hide();
							}
						}
					});
					$("#couponSendType-detail").combobox({
						onChange: function(newValue, oldValue){
							if(newValue =='BIND'){
								$('.couponOverDueDetailClass').hide();
								$('.couponDeadlineTimeDetailClass').hide();
								$('.couponOverDeadlineTime-detail').textbox({
									required:false
								});
							}else if(newValue = 'NORMAL'){
								$('.couponDeadlineTimeDetailClass').show();
								$('.couponOverDeadlineTime-detail').textbox({
									required:true
								});
								$('.couponOverDueDetailClass').show();
								$("#couponOverDueType-detail").combobox({
									onChange: function(newValue, oldValue){
										if(newValue =='BYDAY'){
											$('.couponOverDueTimeDetailClass').hide();
											$('#couponOverDueTimeDetail').textbox({
												required:false
											});
											$('#couponOverDueDayDetail').textbox({
												required:true
											});
											$('.couponOverDueDayDetailClass').show();
										}else if( newValue == 'BYDATE'){
											$('.couponOverDueTimeDetailClass').show();
											$('.couponOverDueDayDetailClass').hide();
											$('#couponOverDueDayDetail').textbox({
												required:false
											});
											$('#couponOverDueTimeDetail').textbox({
												required:true
											});
										}
							        }

								});
							}
						}
					});
			    
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      {title:message("csh.coupon.amount"),field:"amount",sortable:true},
		      {title:message("csh.coupon.counts"),field:"counts",sortable:true},
		      {title:message("csh.coupon.remainNum"),field:"remainNum",sortable:true},
		      {title:message("csh.coupon.claimedNum"),field:"claimedNum",sortable:true,
		    	  formatter:function(value,row,index){
		    		 return row.counts-row.remainNum
		    	  }},
		      {title:message("csh.coupon.type"),field:"type",sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(value =="COMMON"){
		    			  return message("csh.coupon.type.COMMON")
		    		  }else if(value =="SPECIFY"){
		    			  return message("csh.coupon.type.SPECIFY")
		    		  }else{
		    			  return value;
		    		  }
		    	  }},
		      {title:message("csh.coupon.overDueType"),field:"overDueType",width:100,sortable:true,formatter:function(value,row,index){
	    		  if(value =="BYDAY"){
	    			  return message("csh.coupon.overDueType.BYDAY")
	    		  }else if(value =="BYDATE"){
	    			  return message("csh.coupon.overDueType.BYDATE")
	    		  }else{
	    			  return value;
	    		  }
	    			  
	    	  }},
		      {title:message("csh.coupon.overDueDay"),field:"overDueDay",width:100,sortable:true},
		      {title:message("csh.coupon.overDueTime"),field:"overDueTime",width:100,sortable:true,formatter: function(value,row,index){
		    	  if(value != null){
						return new Date(value).Format("yyyy-MM-dd");
					}else{
						return "";
					}}
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

	
	$("#coupon-search-btn").click(function(){
		debugger;
	  var _queryParams = $("#coupon-search-form").serializeJSON();
	  $('#coupon-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#coupon-table-list").datagrid('reload');
	});
})
