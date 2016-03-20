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
					             server: '../file/uploadProfilePhoto.jhtml',
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
			     				 //在参数中增加一个员工编号字段 staffID
//			     				 data.staffID =$("#staffID").val();
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
			    	$("#tenantUserUploader-add .uploadBtn").trigger("clearFileQuene");
			    	$('#addCarService').empty();
			    }
			});  
		},
		edit:function(){
			var _edit_row = $('#maintainReservation-table-list').datagrid('getSelected');
			if( _edit_row == null ){
				$.messager.alert(message("csh.common.select.editRow"));  
				return false;
			}
			var _dialog = $('#editMaintainReservation').dialog({    
				title: message("csh.common.edit"),     
			    width: 600,    
			    height: 450,    
			    modal: true,
			    iconCls:'icon-mini-edit',
			    href:'../maintainReservation/edit.jhtml?id='+_edit_row.id,
			    buttons:[{
			    	text:message("csh.common.save"),
			    	iconCls:'icon-save',
					handler:function(){
						var validate = $('#maintainReservation_form').form('validate');
						if(validate){
							$.ajax({
								url:"../maintainReservation/update.jhtml",
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
										$('#editMaintainReservation').dialog("close");
										$("#maintainReservation-table-list").datagrid('reload');
								}
							});
						};
					}
				},{
					text:message("csh.common.close"),
					iconCls:'icon-cancel',
					handler:function(){
						 $('#editMaintainReservation').dialog("close")
					}
			    }],
			    onLoad:function(){
			    	$("#serviceCategory").combobox({    
				    valueField:'id',
				    textField:'plate',
				    url:"../serviceCategory/findAllServiceCategory.jhtml",
				    editable : false,
				    required:true,
				    prompt:message("csh.common.please.select"),
			    	});
			    },
			    onClose:function(){
			    	$('#editMaintainReservation').empty();
			    }
			});  
		},
		remove:function(){
			listRemove('maintainReservation-table-list','../maintainReservation/delete.jhtml');
		}
};

$(function(){
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
			    width: 600,    
			    height: 450, 
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
		      {title:message("csh.carService.price"),field:"price",width:100,sortable:true},
		      {title:message("csh.carService.promotionPrice"),field:"promotionPrice",width:100,sortable:true},
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

	
	$("#maintainReservation-search-btn").click(function(){
	  var _queryParams = $("#maintainReservation-search-form").serializeJSON();
	  $('#maintainReservation-table-list').datagrid('options').queryParams = _queryParams;  
	  $("#maintainReservation-table-list").datagrid('reload');
	})
})
