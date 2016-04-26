var carServiceRecord_manager_tool = {
		add:function(){
			$('#addCarService').dialog({
			    title: message("csh.carService.add"),    
			    width: 700,    
			    height: 550,
			    href:'../carService/add.jhtml',
			    method:"get",
			    iconCls:'icon-mini-add',
			    cache: false, 
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#addCarService_form').form('validate');
						var $photoLi = $("#carServiceUploader-add ul.filelist li");
						if(validate){
							if($photoLi.length >0){
								$("#carServiceUploader-add .uploadBtn").trigger("upload");
							}else{
								$.ajax({
									url:"../carService/add.jhtml",
									type:"post",
									data:$("#addCarService_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("csh.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										if(response == "success"){
											showSuccessMsg(result.content);
											$('#addCarService').dialog("close")
											$("#addCarService_form").form("reset");
											$("#carService-table-list").datagrid('reload');
										}else{
											alertErrorMsg();
										}
									}
								});
							}
						};
					}
				},{
					text:message("csh.common.cancel"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#addCarService').dialog("close");
						 $("#addCarService_form").form("reset");
					}
			    }],
				onLoad:function(){
					$("#addCarServiceCategory").combobox({    
					    valueField:'id',
					    textField:'categoryName',
					    url:"../serviceCategory/findAllServiceCategory.jhtml",
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
				    	});
					//头像上传
			     	var options ={
			     			createOption:{
			     				pick: {
					                 id: '#carServiceFilePicker-add',
					                 label: '',
					                 multiple :false
					             },
					             dnd: '#carServiceUploader-add .queueList',
					             accept: {
					                 title: 'Images',
					                 extensions: 'gif,jpg,jpeg,bmp,png',
					                 mimeTypes: 'image/*'
					             },
					             //缩略图
					             thumb:{
					            	    width: 110,
					            	    height: 110,
					            	    quality: 90,
					            	    allowMagnify: false,
					            	    crop: false,
					            	    type: 'image/jpeg'
					              },
					             // swf文件路径
					             swf: BASE_URL + '/js/Uploader.swf',
					             disableGlobalDnd: true,
					             server: '../file/uploadPhoto.jhtml',
					             fileNumLimit: 1,
					             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
					             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
					             //图片裁剪
					             compress:{
					            	 width: 110,
					            	 height: 110,
					            	 // 图片质量，只有type为`image/jpeg`的时候才有效。
					            	 quality: 90,
					            	 // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
					            	 allowMagnify: false,
					            	 // 是否允许裁剪。
					            	 crop: false,
					            	 // 是否保留头部meta信息。
					            	 preserveHeaders: true,
					            	 // 如果发现压缩后文件大小比原来还大，则使用原来图片
					            	 // 此属性可能会影响图片自动纠正功能
					            	 noCompressIfLarger: false,
					            	 // 单位字节，如果图片大小小于此值，不会采用压缩。
					            	 compressSize: 0
					             }
			     			},
			     			//包裹上传组件的div id
			     			warp :"addCarService_form",
			     			uploadBeforeSend:function(object, data, headers){
			     				 data.imageType ='CARSERVICEPICTURE';
			     			},
			     			uploadSuccess:function(file, response){
			     				//将返回的图片路径放到隐藏的input中，用于表单保存
			     				$("#addCarService_form_file_input").val(response.content);
			     				$.ajax({
									url:"../carService/add.jhtml",
									type:"post",
									data:$("#addCarService_form").serialize(),
									beforeSend:function(){
										$.messager.progress({
											text:message("yly.common.saving")
										});
									},
									success:function(result,response,status){
										$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#addCarService').dialog("close")
										$("#addCarService_form").form("reset");
										$("#carService-table-list").datagrid('reload');
										
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) {
										$.messager.progress('close');
										alertErrorMsg();
									}
								});
			     			}
			     	};
			     	
			     	singleUpload(options);
				},
			    onClose:function(){
			    	$("#carServiceUploader-add .uploadBtn").trigger("clearFileQuene");
			    	$('#addCarService').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#carServiceRecord-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editCarServiceRecord').dialog({    
				title: message("csh.common.edit"),     
			    width: 700,    
			    height: 550,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../carServiceRecord/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editCarServiceRecord_form').form('validate');
						var payCodeFlageCheck = true;
						debugger;
						if($('#confirmPayCode').length != 0 && !$('#confirmPayCode').is(":hidden")){
							$.messager.show({
								title : message("csh.common.prompt"),
								msg :'输入验证码后请确认',
								timeout : 3000,
								showType : 'slide'
							});
							return;
						}
						if($('#carServiceEditChargeStatus').combobox('getValue') == 'FINISH' &&$('#confirmPayCode').length != 0 && !$('#payCodeValidFlag').val()){
							payCodeFlageCheck = false;
							$.messager.show({
								title : message("csh.common.prompt"),
								msg :'支付验证码有误',
								timeout : 3000,
								showType : 'slide'
							});
						}
						if(validate && payCodeFlageCheck){
							$.ajax({
								url:"../carServiceRecord/update.jhtml",
								type:"post",
								data:$("#editCarServiceRecord_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editCarServiceRecord').dialog("close");
										$("#carServiceRecord-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editCarServiceRecord').dialog("close")
					}
			    }],
			    onLoad:function(){
			    	
			    	if($('#carServiceEditChargeStatus').combobox('getValue') == 'FINISH'){
			    		$('.payCodeValid').show();
	    				$('#validPayCode').textbox({
	    					required:true,
//	    					validType:'payCodeEqual['+$("#payCode").val()+']'
	    				});
	    			}else{
	    				$('#validPayCode').textbox({
	    					required:false,
	    				});
	    				$('.payCodeValid').hide();
			    	}
			    	$('#carServiceEditChargeStatus').combobox({
			    		onSelect:function(record){
			    			if(record.label == 'FINISH'){
			    				$('.payCodeValid').show();
			    				$('#validPayCode').textbox({
			    					required:true,
			    				});
			    			}else{
			    				$('#validPayCode').textbox({
			    					required:false,
			    				});
			    				$('.payCodeValid').hide();
			    			}
			    		}
			    			
			    	});
			    	$('#confirmPayCode').click(function(){
			    		var payCode=$('#validPayCode').val();
			    		var carServiceRecordId=$('#carServiceRecordId').val();
			    		$.ajax({
							url:"../carServiceRecord/payCodeCheck.jhtml?carServiceRecordId="+carServiceRecordId+"&payCode="+payCode,
							type:"post",
							success:function(result,response,status){
								if(result ){
									$('#payCodeValidFlag').val(result);
									$('#confirmPayCode').hide();
								}
								else{
									$.messager.show({
										title : message("csh.common.prompt"),
										msg :'支付验证码有误',
										timeout : 3000,
										showType : 'slide'
									});
								}
							}
						});
			    	});
			    },
			    onClose:function(){
			    	$('#editCarServiceRecord').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('carServiceRecord-table-list','../carServiceRecord/delete.jhtml');
		}
};

$(function(){
	
	
	$("#carServiceTenantDeductRecord-table-list").datagrid({
		title:message("csh.carServiceRecord.list"),
		fitColumns:true,
		toolbar:"#carServiceRecord_manager_tool",
		url:'../carServiceTenantDeductRecord/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#carServiceTenantDeductRecordDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 700,    
			    height: 350, 
			    cache: false,
			    modal: true,
			    href:'../carServiceTenantDeductRecord/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#carServiceRecordDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#carServiceTenantDeductRecordDetail').empty();
			    },
			    onLoad:function(){
			    }
			});   
		},
		columns:[
		   [
		      {field:'ck',checkbox:true},
		      
		      {title:message("csh.carServiceRecord.recordNo"),field:"recordNo",sortable:true},
		      {title:message("csh.carService.serviceName"),field:"carService",sortable:true,formatter:function(value,row,index){
	    		  if(value != null){
	    			  return value.serviceName;
	    		  }else{
	    			  return "";
	    		  }
	    	  }},
		      {title:message("csh.carService.serviceCategory"),field:"serviceCategory",sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(row != null){
		    			  return row.carService.serviceCategory.categoryName;
		    		  }else{
		    			  return "";
		    		  }
		    	  }},
	    	  {title:message("csh.carServiceRecord.endUser"),field:"endUser",sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(value != null){
		    			  return value.userName;
		    		  }else{
		    			  return "";
		    		  }
		    	  }},
		    	  {title:message("csh.carServiceRecord.price"),field:"price",width:100,sortable:true},
		      {title:message("csh.carServiceRecord.paymentType"),field:"paymentType",width:100,sortable:true,
		    		  formatter:function(value,row,index){
		    			if(value=="ALIPAY"){
		    				return message("csh.carServiceRecord.paymentType.ALIPAY");
		    			}else if(value == "WECHAT"){
		    				return message("csh.carServiceRecord.paymentType.WECHAT");
		    			}else if (value == "WALLET"){
		    				return message("csh.carServiceRecord.paymentType.WALLET");
		    			}
		    	  }},
		     
		      {title:message("csh.carServiceRecord.chargeStatus"),field:"chargeStatus",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    			if(value=="RESERVATION"){
		    				return message("csh.carServiceRecord.chargeStatus.RESERVATION");
		    			}else if(value == "UNPAID"){
		    				return message("csh.carServiceRecord.chargeStatus.UNPAID");
		    			}else if (value == "PAID"){
		    				return message("csh.carServiceRecord.chargeStatus.PAID");
		    			}else if (value == "RESERVATION_SUCCESS"){
		    				return message("csh.carServiceRecord.chargeStatus.RESERVATION_SUCCESS");
		    			}else if (value == "RESERVATION_FAIL"){
		    				return message("csh.carServiceRecord.chargeStatus.RESERVATION_FAIL");
		    			}else if (value == "FINISH"){
		    				return message("csh.carServiceRecord.chargeStatus.FINISH");
		    			}else if (value == "OVERDUE"){
		    				return message("csh.carServiceRecord.chargeStatus.OVERDUE");
		    			}
		    			
		    			
		    	  }},
	    	  {title:message("csh.carServiceRecord.paymentDate"),field:"paymentDate",width:100,sortable:true,formatter: function(value,row,index){
					if(value != null){
						return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					}else{
						return "";
					}
					
				}
		      },{title:message("csh.carServiceRecord.clearingDate"),field:"clearingDate",width:100,sortable:true,formatter: function(value,row,index){
					if(value != null){
						return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
					}else{
						return message('csh.carServiceRecord.unClearinged');
					}
					
				}
		      },
		   ]
		]
	});

	
	$("#carServiceTenantDeductRecord-search-btn").click(function(){
	  var _queryParams = $("#carServiceTenantDeductRecord-search-form").serializeJSON();
	  $('#carServiceTenantDeductRecord-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#carServiceTenantDeductRecord-table-list").datagrid('reload');
	})
})
