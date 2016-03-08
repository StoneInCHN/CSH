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
										alertErrorMsg();
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
	$('#searchTenantUser')
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
								$('#searchTenantUser').dialog("close");
							}
						} ],
						onLoad : function() {
							/**
							 * 此datagrid 用户展示老人数据,并且提供查询功能
							 */
							$("#common-tenantUser-table-list")
									.datagrid(
											{
												title : message("csh.elderlyinfo"),
												fitColumns : true,
												url : '../tenantUser/list.jhtml',
												pagination : true,
												loadMsg : message("csh.common.loading"),
												striped : true,
												onDblClickRow : function(
														rowIndex, rowData) {
													if(id.indexOf("NurseArrangement") != -1){//护理员安排
														if(id.indexOf("add")==0){//以add开头
															$("#addNurseArrangement_nurseAssistantID").val(rowData.id); // 隐藏域 护理员id
															$("#addNurseArrangement_nurseAssistantName").textbox('setValue',rowData.realName); 
														}
														if(id.indexOf("edit")==0){//以edit开头
															$("#editNurseArrangement_nurseAssistantID").val(rowData.id); // 隐藏域 护理员id
															$("#editNurseArrangement_nurseAssistantName").textbox('setValue',rowData.realName); 
														}
														$('#searchTenantUser').dialog("close");
														return false;
													}
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
													$('#searchTenantUser')
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

							$("#common_elderlyinfo_search_btn")
									.click(
											function() {
												var _queryParams = $(
														"#common_elderlyinfo_search_form")
														.serializeJSON();
												$(
														'#common_elderlyInfoSearch-table-list')
														.datagrid('options').queryParams = _queryParams;
												$(
														"#common_elderlyInfoSearch-table-list")
														.datagrid('reload');
											})
						}
					});

}
function searchRoles(id) {
	alert(id);
	$('#searchRoles')
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
								$('#searchRoles').dialog("close");
							}
						} ],
						onLoad : function() {
							/**
							 * 此datagrid 用户展示老人数据,并且提供查询功能
							 */
							$("#common-roles-table-list")
									.datagrid(
											{
												title : message("csh.elderlyinfo"),
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
													$('#searchRoles').dialog(
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

							$("#common_elderlyinfo_search_btn")
									.click(
											function() {
												var _queryParams = $(
														"#common_elderlyinfo_search_form")
														.serializeJSON();
												$(
														'#common_elderlyInfoSearch-table-list')
														.datagrid('options').queryParams = _queryParams;
												$(
														"#common_elderlyInfoSearch-table-list")
														.datagrid('reload');
											})
						}
					});

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

