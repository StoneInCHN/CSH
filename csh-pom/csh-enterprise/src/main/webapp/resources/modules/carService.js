var carService_manager_tool = {
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
					    onSelect:function(record){
					    	if(record.id != 2){
					    		$("#addCarServicePrice").textbox('setValue',-1);
					    		$("#addCarServicePrice").textbox({
					    			readonly:true,
					    			min:-1
					    		});
					    		$("#addCarServicePromotionPrice").textbox('setValue',-1);
					    		$("#addCarServicePromotionPrice").textbox({
					    			readonly:true,
					    			min:-1
					    		});
					    		$('.car-service').hide();
					    		$('#addServiceName').textbox({
					    			required:false
					    		});
					    	}else{
					    		$("#addCarServicePrice").textbox({
					    			readonly:false,
					    			min:0
					    		});
					    		$("#addCarServicePrice").textbox('setValue',0);
					    		$("#addCarServicePromotionPrice").textbox({
					    			readonly:false,
					    			min:0
					    		});
					    		$("#addCarServicePromotionPrice").textbox('setValue',0);
					    		$('.car-service').show();
					    		$('#addServiceName').textbox({
					    			required:true
					    		});
					    	}
					    }
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
			var _edit_row = $('#carService-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editCarService').dialog({    
				title: message("csh.common.edit"),     
			    width: 700,    
			    height: 550,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../carService/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#editCarService_form').form('validate');
						if(validate){
							$.ajax({
								url:"../carService/update.jhtml",
								type:"post",
								data:$("#editCarService_form").serialize(),
								beforeSend:function(){
									$.messager.progress({
										text:message("csh.common.saving")
									});
								},
								success:function(result,response,status){
									$.messager.progress('close');
										showSuccessMsg(result.content);
										$('#editCarService').dialog("close");
										$("#carService-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editCarService').dialog("close")
					}
			    }],
			    onLoad:function(){
			    	$("#editCarServiceCategory").combobox({    
					    valueField:'id',
					    textField:'categoryName',
					    url:"../serviceCategory/findAllServiceCategory.jhtml",
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
					    onLoadSuccess:function(){
							$('#editCarServiceCategory').combobox("setValue",$("#editCarServiceCategory").attr("data-value"));
						},
						 onSelect:function(record){
						    	if(record.id != 2){
						    		$("#editCarServicePrice").textbox('setValue',-1);
						    		$("#editCarServicePrice").textbox({
						    			readonly:true,
						    			min:-1
						    		});
						    		$("#editCarServicePromotionPrice").textbox('setValue',-1);
						    		$("#editCarServicePromotionPrice").textbox({
						    			readonly:true,
						    			min:-1
						    		});
						    		$('.car-service').hide();
						    		$('#editServiceName').textbox({
						    			required:false
						    		});
						    	}else{
						    		$("#editCarServicePrice").textbox({
						    			readonly:false,
						    			min:0
						    		});
						    		$("#editCarServicePrice").textbox('setValue',0);
						    		$("#editCarServicePromotionPrice").textbox({
						    			readonly:false,
						    			min:0
						    		});
						    		$("#editCarServicePromotionPrice").textbox('setValue',0);
						    		$('.car-service').show();
						    		$('#editServiceName').textbox({
						    			required:true
						    		});
						    	}
						    }
			    	});
			    	var editOptions ={
			     			createOption:{
			     				pick: {
					                 id: '#carServiceFilePicker-edit',
					                 innerHTML :'',
					                 multiple :true
					             },
					             dnd: '#carServiceUploader-edit .queueList',
					             accept: {
					                 title: 'Images',
					                 extensions: 'gif,jpg,jpeg,bmp,png',
					                 mimeTypes: 'image/*'
					             },
					             thumb:{
					            	    width: 150,
					            	    height: 150,
					            	    quality: 90,
					            	    allowMagnify: false,
					            	    crop: false,
					            	    type: 'image/jpeg'
					            	},
					             // swf文件路径
					             swf: BASE_URL + '/js/Uploader.swf',
					             disableGlobalDnd: true,
					             server: '../carService/uploadPhoto.jhtml',
					             fileNumLimit: 100,
					             fileSizeLimit: 10 * 1024 * 1024,    // 10 M
					             fileSingleSizeLimit: 10 * 1024 * 1024,    //单个文件上传大小  10 M
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
			     			warp :"editCarService_form",
			     			uploadImageType:"edit",
			     			addButton:{
			     				id: '#carServiceFilePicker-edit2',
			     				innerHTML: '替换头像'
			     			},
			     			uploadBeforeSend:function(object, data, headers){
			     				 //
//			     				 data.staffID =$("#editCarService_form").find("input[name='staffID']").val();
			     				 data.carServiceId=$("#editCarService_form").find("input[name='id']").val();;
			     			}
			     	};
			     	singleUpload(editOptions);
			     	$("#editCarService_form").find(".savePhoto").on("click",function(){
			     		$.messager.confirm('确认','头像保存后将直接修改当前用户的头像，确认要上传吗？',function(res){    
			     		    if (res){
			     		    	$("#carServiceUploader-edit .uploadBtn").trigger("upload");
			     		    }    
			     		}); 
			     		//alert("保存头像");
			     	});
			    },
			    onClose:function(){
			    	$('#editCarService').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('carService-table-list','../carService/delete.jhtml');
		}
};

$(function(){
	
	$("#serviceCategorySearch").combobox({    
	    valueField:'id',
	    textField:'categoryName',
	    url:"../serviceCategory/findAllServiceCategory.jhtml",
	    editable : false,
	    prompt:message("csh.common.please.select"),
    	});
	
	$("#carService-table-list").datagrid({
		title:message("csh.carService.list"),
		fitColumns:true,
		toolbar:"#carService_manager_tool",
		url:'../carService/list.jhtml',  
		pagination:true,
		loadMsg:message("csh.common.loading"),
		striped:true,
		onDblClickRow : function (rowIndex, rowData){
			$('#carServiceDetail').dialog({    
			    title: message("csh.common.detail"),    
			    width: 700,    
			    height: 550, 
			    cache: false,
			    modal: true,
			    href:'../carService/details.jhtml?id='+rowData.id,
			    buttons:[{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#carServiceDetail').dialog("close");
					}
			    }],
			    onClose:function(){
			    	$('#carServiceDetail').empty();
			    },
			    onLoad:function(){
			    	$("#carServiceCategoryDetail").combobox({    
					    valueField:'id',
					    textField:'categoryName',
					    url:"../serviceCategory/findAllServiceCategory.jhtml",
					    editable : false,
					    required:true,
					    prompt:message("csh.common.please.select"),
					    onLoadSuccess:function(){
							$('#carServiceCategoryDetail').combobox("setValue",$("#carServiceCategoryDetail").attr("data-value"));
						},
			    	});
			    }
			});   
		},
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
		      {title:message("csh.carService.rate"),field:"rate",width:100,sortable:true},
		      {title:message("csh.carService.serviceStatus"),field:"serviceStatus",width:100,sortable:true,
		    	  formatter:function(value,row,index){
		    		  if(value =="ENABLED"){
		    			  return message("csh.carService.serviceStatus.ENABLED")
		    		  }else if(value =="DISABLED"){
		    			  return message("csh.carService.serviceStatus.DISABLED")
		    		  }
		    	  }},
		      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
					return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
				}
		      },
		   ]
		]
	});

	
	$("#carService-search-btn").click(function(){
	  var _queryParams = $("#carService-search-form").serializeJSON();
	  $('#carService-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#carService-table-list").datagrid('reload');
	})
})
