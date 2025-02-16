/**
* 将秒数换成时分秒格式
* 整理：www.jbxue.com
*/
  
function formatSeconds(value) {
    var theTime = parseInt(value);// 秒
    var theTime1 = 0;// 分
    var theTime2 = 0;// 小时
    if(theTime > 60) {
        theTime1 = parseInt(theTime/60);
        theTime = parseInt(theTime%60);
            if(theTime1 > 60) {
            theTime2 = parseInt(theTime1/60);
            theTime1 = parseInt(theTime1%60);
            }
    }
        var result = ""+parseInt(theTime)+"秒";
        if(theTime1 > 0) {
        result = ""+parseInt(theTime1)+"分"+result;
        }
        if(theTime2 > 0) {
        result = ""+parseInt(theTime2)+"小时"+result;
        }
    return result;
}
 
// 对Date的扩展，将 Date 转化为指定格式的String 
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}

var setting = {
	priceScale : "2",
	priceRoundType : "roundHalfUp",
	uploadImageExtension : "jpg,jpeg,bmp,gif,png",
	uploadFlashExtension : "swf,flv",
	uploadMediaExtension : "swf,flv,mp3,wav,avi,rm,rmvb",
	uploadFileExtension : "zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx"
};

/**
 * 公共提示信息 response==success
 * 
 * @param msgStr
 */
function showSuccessMsg(msgStr) {
	$.messager.show({
		title : message("csh.common.prompt"),
		msg : msgStr,
		timeout : 3000,
		showType : 'slide'
	});
}

/**
 * 公共提示信息 error
 */
function alertErrorMsg() {
	$.messager.alert(message("csh.common.fail"),
			message("csh.common.unknow.error"), 'error');
}
/**
 * 公共提示信息 error
 */
function alertErrorMsg(content) {
	if(content){
		$.messager.alert(message("csh.common.fail"),
				message(content), 'error');
	}else{
		$.messager.alert(message("csh.common.fail"),
				message("csh.common.unknow.error"), 'error');
	}
	
}

/**
 * 公共提示信息 info
 */
function alertInfoMsg(content) {
	if(content){
		$.messager.alert(message("csh.common.fail"),
				message(content), 'info');
	}else{
		$.messager.alert(message("csh.common.fail"),
				message("csh.common.unknow.error"), 'info');
	}
	
}


// 添加Cookie
function addCookie(name, value, options) {
	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name))
				+ "="
				+ encodeURIComponent(String(value))
				+ (options.expires ? "; expires="
						+ options.expires.toUTCString() : "")
				+ (options.path ? "; path=" + options.path : "")
				+ (options.domain ? "; domain=" + options.domain : ""),
				(options.secure ? "; secure" : "");
	}
}

// 获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name))
				+ "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 移除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}
//导出excel数据
function exportData(control, form) {
	// 建议一次导出excel数据的最大值为500
	var maxSize = 500;
	$.ajax({
				url : "../" + control + "/count.jhtml",
				type : "post",
				data : $("#" + form).serialize(),
				success : function(result, response, status) {
					if (result.count != null) {
						var text = "";
						if (result.count == 0) {
							// "当前条件无可导出的数据。"
							text = message("yly.common.notice.current_condition_no_export_data");
							$.messager.alert(message("yly.common.notice"),
									text, 'warning');
						} else if (result.count <= maxSize) {
							// "确定导出 {0}条记录？"
							text = message(
									"yly.common.notice.comfirm_export_data",
									result.count);
							$.messager
									.confirm(
											message("yly.common.confirm"),
											text,
											function(r) {
												if (r) {
													$("#" + form)
															.attr(
																	"action",
																	"../"
																			+ control
																			+ "/exportData.jhtml");
													$("#" + form).attr(
															"target", "_blank");
													$("#" + form).submit();
												}

											});
						} else {
							// "导出数据超过 "+maxSize+" 条数据，建议搜索查询条件以缩小查询范围，再导出。";
							text = message(
									"yly.common.notice.export_data_too_much_advice_use_filter",
									maxSize);
							$.messager
									.confirm(
											message("yly.common.notice"),
											text,
											function(r) {
												if (!r) {
													// "导出共有"+ result.count
													// +"条数据，导出超过 "+maxSize+"
													// 条数据可能需要您耐心等待，仍需操作请确定继续。";
													text = message(
															"yly.common.notice.need_wait_export_too_much_data",
															result.count,
															maxSize);
													$.messager
															.confirm(
																	message("yly.common.confirm"),
																	text,
																	function(
																			yes) {
																		if (yes) {
																			$(
																					"#"
																							+ form)
																					.attr(
																							"action",
																							"../"
																									+ control
																									+ "/exportData.jhtml");
																			$(
																					"#"
																							+ form)
																					.attr(
																							"target",
																							"_blank");
																			$(
																					"#"
																							+ form)
																					.submit();
																		}
																	});
												}
											})
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error");
					$.messager.progress('close');
					alertErrorMsg();
				}
			});
}
function exportExcel(control,form,totalRecord){
	var total = $('#'+totalRecord).val();
	var max = 500;
	if(total > max){
	$.messager
	.confirm(
			message("yly.common.notice"),
			text,
			function(r) {
				if (!r) {
					// "导出共有"+ result.count
					// +"条数据，导出超过 "+maxSize+"
					// 条数据可能需要您耐心等待，仍需操作请确定继续。";
					text = message(
							"yly.common.notice.need_wait_export_too_much_data",
							result.count,
							maxSize);
				}else{
					$("#"+ form).attr("action","../"+ control+ "/exportData.jhtml");
					$("#"+ form).attr("target","_blank");
					$("#"+ form).submit();
				}
			});
	}else if (total == 0 ){
		text = message("yly.common.notice.current_condition_no_export_data");
		$.messager.alert(message("yly.common.notice"),
				text, 'warning');
	}else{
		$("#"+ form).attr("action","../"+ control+ "/exportData.jhtml");
		$("#"+ form).attr("target","_blank");
		$("#"+ form).submit();
	}
	
}
/**
 * 删除公用方法 id table_list id url 删除是向后台发送的链接
 */
function listRemove(id, url) {
	var _id = id;
	var _url = url
	var _rows = $("#" + _id).datagrid('getSelections');
	if (_rows.length == 0) {
		$.messager.alert(message("csh.common.prompt"),
				message("csh.common.select.deleteRow"), 'warning');
	} else {
		var _ids = [];
		for (var i = 0; i < _rows.length; i++) {
			_ids.push(_rows[i].id);
		}
		if (_ids.length > 0) {
			$.messager.confirm(message("csh.common.confirm"),
					message("csh.common.delete.confirm"), function(r) {
						if (r) {
							$.ajax({
								url : _url,
								type : "post",
								traditional : true,
								data : {
									"ids" : _ids
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
										$("#" + _id).datagrid('reload');
									} else {
										debugger;
										alertErrorMsg(resultMsg);
									}
								}
							});
						}
					})
		}

	}
}


// 查询用户
function searchTenantUser(id) {
	$('#commonMainDialog')
			.dialog(
					{
						title : message("csh.tenantUser.search"),
						width : 1000,
						height : 500,
						modal : true,
						cache : false,
						href : '../tenantUser/commonTenantUserSearch.jhtml',
						buttons : [ {
							text : message("csh.common.cancel"),
							iconCls : 'icon-cancel',
							handler : function() {
								$('#commonMainDialog').dialog("close");
							}
						} ],
						onLoad : function() {
							/**
							 * 此datagrid 用户展示老人数据,并且提供查询功能
							 */
							$("#common-tenantUser-table-list")
									.datagrid(
											{
												title : message("csh.tenantUser.list"),
												fitColumns : true,
												url : '../tenantUser/list.jhtml',
												pagination : true,
												loadMsg : message("csh.common.loading"),
												striped : true,
												onDblClickRow : function(
														rowIndex, rowData) {
													debugger;
													$("#" + id + "ID").val(
															rowData.id);
													$("#" + id).textbox(
															'setValue',
															rowData.realName);
													if ($("#identifier").length >0) {
														$("#identifier")
																.val(
																		rowData.identifier);
													}
													$('#commonMainDialog')
															.dialog("close");
												},
												columns : [ [
														{
															title : message("csh.common.name"),
															field : "realName",
															width : 100,
															sortable : true
														},
														{
															title : message("csh.common.age"),
															field : "age",
															width : 100,
															sortable : true
														},
														{
															title : message("csh.tenantUser.staffID"),
															field : "staffID",
															width : 100,
															sortable : true
														},
														{
															title : message("csh.tenantUser.staffStatus"),
															field : "staffStatus",
															width : 100,
															sortable : true,
															formatter : function(
																	value, row,
																	index) {
																if (value == "INSERVICE") {
																	return message("csh.tenantUser.staffStatus.inService");
																} else if (value = "OUTSERVICE") {
																	return message("csh.tenantUser.staffStatus.outService");
																}
															}
														},

														{
															title : message("csh.tenantUser.department"),
															field : "department",
															width : 100,
															sortable : true,
															formatter : function(
																	value, row,
																	index) {
																if (value) {
																	return value.name;
																} else {
																	return value;
																}
															}
														},
														{
															title : message("csh.tenantUser.position"),
															field : "position",
															width : 100,
															sortable : true,
															formatter : function(
																	value, row,
																	index) {
																if (value) {
																	return value.name;
																} else {
																	return value;
																}
															}
														},
														{
															title : message("csh.tenantUser.hireDate"),
															field : "hireDate",
															width : 100,
															sortable : true,
															formatter : function(
																	value, row,
																	index) {
																return new Date(
																		value)
																		.Format("yyyy-MM-dd");
															}
														}, ] ]

											});

							$("#common_tenantUserSearch-search-btn")
									.click(
											function() {
												var _queryParams = $(
														"#common_tenantUser_search_form")
														.serializeJSON();
												$(
														'#common-tenantUser-table-list')
														.datagrid('options').queryParams = _queryParams;
												$(
														"#common-tenantUser-table-list")
														.datagrid('reload');
											})
						}
					});

}
function searchRoles(id) {
	$('#commonMainDialog')
			.dialog(
					{
						title : message("csh.role.search"),
						width : 1000,
						height : 500,
						modal : true,
						cache : false,
						href : '../role/commonRolesSearch.jhtml',
						buttons : [ {
							text : message("csh.common.cancel"),
							iconCls : 'icon-cancel',
							handler : function() {
								$('#commonMainDialog').dialog("close");
							}
						} ],
						onLoad : function() {
							/**
							 * 此datagrid 用户展示老人数据,并且提供查询功能
							 */
							$("#common-roles-table-list")
									.datagrid(
											{
												title : message("csh.role.list"),
												fitColumns : true,
												url : '../role/list.jhtml',
												pagination : true,
												loadMsg : message("csh.common.loading"),
												striped : true,
												onDblClickRow : function(
														rowIndex, rowData) {
													$("#" + id + "ID").val(
															rowData.id);
													$("#" + id).textbox(
															'setValue',
															rowData.name);
													if ($("#identifier")) {
														$("#identifier")
																.val(
																		rowData.identifier);
													}
													$('#commonMainDialog').dialog(
															"close");
												},
												columns : [ [
														{
															title : message("csh.role.name"),
															field : "name",
															width : 20,
															align : 'center',
															formatter : function(
																	value, row,
																	index) {
																return row.name;
															}
														},
														{
															title : message("csh.role.description"),
															field : "description",
															width : 80,
															align : 'center',
															formatter : function(
																	value, row,
																	index) {
																return row.description;
															}
														} ] ]

											});

							$("#common_role_search_btn")
									.click(
											function() {
												var _queryParams = $(
														"#common_role_search_form")
														.serializeJSON();
												$(
														'#common-roles-table-list')
														.datagrid('options').queryParams = _queryParams;
												$(
														"#common-roles-table-list")
														.datagrid('reload');
											})
						}
					});

}
//查询终端用户用户
function searchEndUser(id) {
	$('#commonMainDialog')
			.dialog(
					{
						title : message("csh.endUser.search"),
						width : 1000,
						height : 500,
						modal : true,
						cache : false,
						href : '../endUser/commonEndUserSearch.jhtml',
						buttons : [ {
							text : message("csh.common.cancel"),
							iconCls : 'icon-cancel',
							handler : function() {
								$('#commonMainDialog').dialog("close");
							}
						} ],
						onLoad : function() {
							$("#common-endUser-table-list")
									.datagrid(
											{
												title : message("csh.endUser.list"),
												fitColumns : true,
												url : '../endUser/list.jhtml',
												pagination : true,
												loadMsg : message("csh.common.loading"),
												striped : true,
												onDblClickRow : function(
														rowIndex, rowData) {
													$("#" + id + "ID").val(
															rowData.id);
													$("#" + id).textbox(
															'setValue',
															rowData.realName?rowData.realName:rowData.userName);
													if($("#endUserMobileNum")!=undefined){
														
														$("#endUserMobileNum").textbox('setValue',rowData.mobileNum);
													};
													if($("#vehiclePlate")!=undefined){
														$("#vehiclePlate").combobox({
															url:"../vehicle/findVehicleUnderUser.jhtml?userId="+rowData.id,
														    valueField:'id',
														    method:"get",
														    textField:'plate',
														    editable : false,
														    required:true,
														    prompt:message("csh.common.please.select"),
														    onSelect:function(record){
														    	if($("#dashboardMileage")!=undefined)
														    		$("#dashboardMileage").textbox('setValue',record.dashboardMileage);
														    	if($("#lastMaintainMileage")!=undefined)
														    		$("#lastMaintainMileage").textbox('setValue',record.dashboardMileage);
														    	if($("#vehicleBrand")!=undefined)
														    	$("#vehicleBrand").textbox('setValue',record.vehicleFullBrand);
														    },
														    onLoadSuccess:function(){
														},
														});
														$("#vehiclePlate").combobox('clear');
														$("#vehicleBrand").textbox('setValue','');
													};
													$('#commonMainDialog')
															.dialog("close");
												},
												columns : [ [
														      {field:'ck',checkbox:true},
														      {title:message("csh.endUser.userName"),field:"userName",width:100,sortable:true},
														      {title:message("csh.endUser.realName"),field:"realName",width:100,sortable:true   },
														      {title:message("csh.endUser.mobileNum"),field:"mobileNum",width:100,sortable:true},
														      {title:message("csh.endUser.qq"),field:"qq",width:100,sortable:true},
														      {title:message("csh.endUser.accoutStatus"),field:"accoutStatus",width:100,sortable:true,
														    	  formatter: function(value,row,index){
															    	  if(value == "ACTIVED"){
															    		  return  message("csh.endUser.active");
															    	  }else if (value == "LOCKED"){
															    		  return  message("csh.endUser.locked");
															    	  }
														      	  }  
														      },
														      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
																	return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
																}
														      },
														   ] ]

											});

							$("#common_endUser_search_btn")
									.click(
											function() {
												var _queryParams = $(
														"#common_endUser_search_form")
														.serializeJSON();
												debugger;
												$('#common-endUser-table-list')
														.datagrid('options').queryParams = _queryParams;
												$("#common-endUser-table-list")
														.datagrid('reload');
											})
						}
					});

}

//查询车辆信息
function searchVehicle(id,unbind) {
$('#commonMainDialog')
.dialog(
		{
			title : message("csh.vehicle.search"),
			width : 1000,
			height : 500,
			modal : true,
			cache : false,
			href : '../deviceInfo/commonVehicleSearch.jhtml',
			buttons : [ {
				text : message("csh.common.cancel"),
				iconCls : 'icon-cancel',
				handler : function() {
					$('#commonMainDialog').dialog("close");
				}
			} ],
			onLoad : function() {
				var url ='../vehicle/list.jhtml';
				if(unbind){
					url = '../vehicle/listUnBuindVehicle.jhtml';
				}
				$("#common-vehicle-table-list")
						.datagrid(
								{
									title : message("csh.vehicle.list"),
									fitColumns : true,
									url : url,
									pagination : true,
									loadMsg : message("csh.common.loading"),
									striped : true,
									onDblClickRow : function(
											rowIndex, rowData) {
										$("#" + id + "ID").val(
												rowData.id);
										$("#" + id).textbox(
												'setValue',
												rowData.plate);
										$('#commonMainDialog')
												.dialog("close");
									},
									columns : [ [{field:'ck',checkbox:true},
											      {title:message("csh.vehicle.endUser"),field:"endUser",sortable:true,
								    	  formatter: function(value,row,index){
									    	  if(value != null){
									    		  return  value.userName;
									    	  }
								      	  }},
									   {title:message("csh.endUser.mobileNum"),field:"mobileNum",width:100,sortable:true,
								    	  formatter: function(value,row,index){
									    	  if(row.endUser != null){
									    		  return  row.endUser.mobileNum;
									    	  }
								      	  }},
								      {title:message("csh.vehicle.plate"),field:"plate",sortable:true},
								      {title:message("csh.vehicle.vehicleBrand"),width:100,field:"vehicleBrand",sortable:true,
								    	  formatter: function(value,row,index){
									    	  if(row != null){
									    		  return  row.vehicleBrandDetail.vehicleLine.vehicleBrand.name;
									    	  }
								      	  }},
								      {title:message("csh.vehicle.vehicleLine"),width:100,field:"vehicleLine",sortable:true,
								    	  formatter: function(value,row,index){
									    	  if(row != null){
									    		  return  row.vehicleBrandDetail.vehicleLine.name;
									    	  }
								      	  }},
								      {title:message("csh.vehicle.vehicleBrandDetail"),width:100,field:"vehicleBrandDetail",sortable:true,
								    	  formatter: function(value,row,index){
									    	  if(value != null){
									    		  return  value.name;
									    	  }
								      	  }},
								      {title:message("csh.vehicle.vehicleNo"),field:"vehicleNo",sortable:true},
							      	  {title:message("csh.vehicle.dashboardMileage"),field:"dashboardMileage",sortable:true},
							      	  {title:message("csh.vehicle.dashboardBV"),field:"dashboardBV",sortable:true},
								      {title:message("csh.vehicle.device"),field:"deviceNo",width:100,sortable:false},
								      {title:message("csh.common.createDate"),field:"createDate",width:100,sortable:true,formatter: function(value,row,index){
											return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
										}
								      }] ]

								});

				$("#common-vehicle-search-btn")
						.click(
								function() {
									var _queryParams = $(
											"#vehicle-search-form")
											.serializeJSON();
									$('#common-vehicle-table-list')
											.datagrid('options').queryParams = _queryParams;
									$("#common-vehicle-table-list")
											.datagrid('reload');
								})
			}
		});

}
//查询设备信息
function searchDevice(id) {
	$('#commonMainDialog')
			.dialog(
					{
						title : message("csh.deviceInfo.search"),
						width : 1000,
						height : 500,
						modal : true,
						cache : false,
						href : '../deviceInfo/commonDeviceInfoSearch.jhtml',
						buttons : [ {
							text : message("csh.common.cancel"),
							iconCls : 'icon-cancel',
							handler : function() {
								$('#commonMainDialog').dialog("close");
							}
						} ],
						onLoad : function() {
							/**
							 * 此datagrid 用户展示设备数据,并且提供查询功能
							 */
							$("#common-deviceInfo-table-list")
									.datagrid(
											{
												title : message("csh.deviceInfo"),
												fitColumns : true,
												url : '../deviceInfo/list.jhtml',
												pagination : true,
												loadMsg : message("csh.common.loading"),
												striped : true,
												onDblClickRow : function(
														rowIndex, rowData) {
													$("#" + id + "ID").val(
															rowData.id);
													$("#" + id).textbox(
															'setValue',
															rowData.deviceNo);
													
													$('#commonMainDialog')
															.dialog("close");
												},
												onBeforeLoad:function(param){
													param.deviceStatusSearch = "STORAGEOUT";
												},
												columns : [ [
														      {field:'ck',checkbox:true},
														      {title:message("csh.deviceInfo.deviceNO"),field:"deviceNo",width:100,sortable:true},
														      {title:message("csh.deviceInfo.deviceType"),field:"type",width:100,sortable:true,
														    	  formatter: function(value,row,index){
															    	  if(value != null){
															    		  return  value.name;
															    	  }
														      	  }},
														      {title:message("csh.deviceInfo.deviceStatus"),field:"deviceStatus",width:100,sortable:true,
														    	  formatter: function(value,row,index){
															    	  if(value == "INITED"){
															    		  return  message("csh.deviceInfo.deviceStatus.INITED");
															    	  }else if (value = "SENDOUT"){
															    		  return  message("csh.deviceInfo.deviceStatus.SENDOUT");
															    	  }else if (value = "STORAGEOUT"){
															    		  return  message("csh.deviceInfo.deviceStatus.STORAGEOUT");
															    	  }else if (value = "BINDED"){
															    		  return  message("csh.deviceInfo.deviceStatus.BINDED");
															    	  }else if (value = "REFUNDED"){
															    		  return  message("csh.deviceInfo.deviceStatus.REFUNDED");
															    	  }
														      	  }  
														      },
														      {title:message("csh.deviceInfo.bindTime"),field:"bindTime",width:100,sortable:true,formatter: function(value,row,index){
																	return new Date(value).Format("yyyy-MM-dd:hh:mm:ss");
																}
														      },
														   ] ]

											});

							$("#common_deviceInfo_search_btn")
									.click(
											function() {
												var _queryParams = $(
														"#common_deviceInfo_search_form")
														.serializeJSON();
												$(
														'#common-deviceInfo-table-list')
														.datagrid('options').queryParams = _queryParams;
												$(
														"#common-deviceInfo-table-list")
														.datagrid('reload');
											})
						}
					});

}
// 线性图表
function refreshLine(id, data, categoryName, valueName, viewName,dateFormat) {
	if (data.length > 0) {
		id.xAxis.categories = [];
		if (valueName instanceof Array) {
			for (var j = 0; j < valueName.length; j++) {
				var value = new Object();
				value.name = viewName[j];
				value.data = [];
				id.series.push(value);
			}
		}
		for (var i = 0; i < data.length; i++) {
			id.xAxis.categories.push(new Date(data[i][categoryName])
					.Format(dateFormat));
			if (valueName instanceof Array) {
				for (var j = 0; j < valueName.length; j++) {
					id.series[j].data.push(data[i][valueName[j]]);
				}
			} else {
				id.series[0].data.push(data[i][valueName]);
			}
		}
	}
	var chart = new Highcharts.Chart(id);
}

function refreshPie(id, data, nameArray, dataArray) {
	if (nameArray != null && dataArray != null) {
		for (var i = 0; i < nameArray.length; i++) {
			var value = [ nameArray[i], data[dataArray[i]] ];
			id.series[0].data.push(value);
		}
	} 
	var chart = new Highcharts.Chart(id);
}

function formReset(formId, tableId) {
	$('#' + formId)[0].reset();
	var _queryParams = {}
	$('#' + tableId).datagrid('options').queryParams = _queryParams;
}

// 返回指定长度字符串截取,超出部分不显示,以...作为后缀显示
function formatLongString(str, len) {
	if (str != null && str != "" && len > 0) {
		if (str.length > len) {
			return '<span title="' + str + '">' + str.substring(0, len) + "..."
					+ '<span>'
		} else {
			return str;
		}
	}
	return "";
}
// 导出excel数据
function exportData(control, form) {
	// 建议一次导出excel数据的最大值为500
	var maxSize = 500;
	$
			.ajax({
				url : "../" + control + "/count.jhtml",
				type : "post",
				data : $("#" + form).serialize(),
				success : function(result, response, status) {
					if (result.count != null) {
						var text = "";
						if (result.count == 0) {
							// "当前条件无可导出的数据。"
							text = message("csh.common.notice.current_condition_no_export_data");
							$.messager.alert(message("csh.common.notice"),
									text, 'warning');
						} else if (result.count <= maxSize) {
							// "确定导出 {0}条记录？"
							text = message(
									"csh.common.notice.comfirm_export_data",
									result.count);
							$.messager
									.confirm(
											message("csh.common.confirm"),
											text,
											function(r) {
												if (r) {
													$("#" + form)
															.attr(
																	"action",
																	"../"
																			+ control
																			+ "/exportData.jhtml");
													$("#" + form).attr(
															"target", "_blank");
													$("#" + form).submit();
												}

											});
						} else {
							// "导出数据超过 "+maxSize+" 条数据，建议搜索查询条件以缩小查询范围，再导出。";
							text = message(
									"csh.common.notice.export_data_too_much_advice_use_filter",
									maxSize);
							$.messager
									.confirm(
											message("csh.common.notice"),
											text,
											function(r) {
												if (!r) {
													// "导出共有"+ result.count
													// +"条数据，导出超过 "+maxSize+"
													// 条数据可能需要您耐心等待，仍需操作请确定继续。";
													text = message(
															"csh.common.notice.need_wait_export_too_much_data",
															result.count,
															maxSize);
													$.messager
															.confirm(
																	message("csh.common.confirm"),
																	text,
																	function(
																			yes) {
																		if (yes) {
																			$(
																					"#"
																							+ form)
																					.attr(
																							"action",
																							"../"
																									+ control
																									+ "/exportData.jhtml");
																			$(
																					"#"
																							+ form)
																					.attr(
																							"target",
																							"_blank");
																			$(
																					"#"
																							+ form)
																					.submit();
																		}
																	});
												}
											})
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("error");
					$.messager.progress('close');
					alertErrorMsg();
				}
			});
}


var math ={
	mul:function(arg1, arg2){
      var m = 0, s1 = arg1.toString(), s2 = arg2.toString(),value = 0;
         try { m += s1.split(".")[1].length } catch (e) { }
         try { m += s2.split(".")[1].length } catch (e) { }
         value = Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
         return value.toFixed(2);
	},
	add:function(arg1, arg2) {
    var r1, r2, m, c;
    try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }
    try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }
    c = Math.abs(r1 - r2);
    m = Math.pow(10, Math.max(r1, r2))
    if (c > 0) {
        var cm = Math.pow(10, c);
        if (r1 > r2) {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", "")) * cm;
        }
        else {
            arg1 = Number(arg1.toString().replace(".", "")) * cm;
            arg2 = Number(arg2.toString().replace(".", ""));
        }
    }
    else {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", ""));
    }
    return (arg1 + arg2) / m
}
		
}
Number.prototype.toPercent = function(){
	return (Math.round(this * 10000)/100).toFixed(2) + '%';
}

//根据值，从数组中删除某个元素
function deleteByValue(arr,valueToDelete){
	var ret = $.grep(arr,function(value,i){
  		return value != valueToDelete;
	});  
  	return ret;
}