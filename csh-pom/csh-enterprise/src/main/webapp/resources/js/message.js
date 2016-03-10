var messages = {
	/**
	 * common
	 */
	"csh.common.createDate" : "创建时间",
	"csh.common.modifyDate" : "修改时间",
	"csh.common.title" : "标题",
	"csh.common.loading" : "加载中...",
	"csh.common.save" : "保存",
	"csh.common.saving" : "保存中...",
	"csh.common.add" : "添加",
	"csh.common.edit" : "编辑",
	"csh.common.remove" : "删除",
	"csh.common.cancel" : "取消",
    "csh.common.close" : "关闭",
	"csh.common.progress" : "正在处理中...",
	"csh.common.prompt" : "操作提示",
	"csh.common.success" : "操作成功",
	"csh.common.fail" : "操作失败",
	"csh.common.unknow.error" : "未知错误",
	"csh.common.select.editRow" : "请选择要编辑的记录",
	"csh.common.select.editRow.unique" : "只能选择一条记录修改",
	"csh.common.select.deleteRow" : "请选择要删除的内容",
	"csh.common.confirm" : "确认",
	"csh.common.delete.confirm" : "您确认想要删除记录吗？",
	"csh.common.gender": "性别",
	"csh.common.name":"姓名",
	"csh.common.age":"年龄",
	"csh.common.birthday":"出生日期",
	"csh.common.relation":"与老人关系",
	"csh.common.infoChannel":"信息来源",
	"csh.common.remark":"备注",
	"csh.common.phonenumber":"电话号码",
	"csh.common.male":"男",
	"csh.common.female":"女",
	"csh.common.other":"其它",
	"csh.common.yes":"是",
	"csh.common.no":"否",
	"csh.common.detail":"详情",
	"csh.common.relation.self":"本人",
	"csh.common.relation.children":"子女",
	"csh.common.relation.marriage_relationship":"夫妻",
	"csh.common.relation.grandparents_and_grandchildren":"祖孙",
	"csh.common.relation.brothers_or_sisters":"兄弟或姐妹",
	"csh.common.relation.daughterinlaw_or_soninlaw":"儿媳或女婿",
	"csh.common.relation.friend":"朋友",
	"csh.common.relation.infochannel.network":"网络",
	"csh.common.relation.infochannel.community":"社区",
	"csh.common.relation.infochannel.ohter_introduct":"他人介绍",
	"csh.common.relation.infochannel.advertisement":"广告",
	"csh.common.idcard":"身份证号码",
	"csh.common.disable":"禁用",
	"csh.common.enable":"启用",
	"csh.common.elderlyname":"老人姓名",
	"csh.common.bedRoom":"房间",
	"csh.common.nurseLevel":"护理等级",
	"csh.common.please.select":"请选择...",
	"csh.common.insertDate": "录入时间",
	"csh.common.notice":"注意",
	"csh.common.notice.current_condition_no_export_data":"当前条件无可导出的数据。",
	"csh.common.notice.comfirm_export_data":"确定导出 {0} 条记录？",
	"csh.common.notice.export_data_too_much_advice_use_filter":"导出数据超过 {0} 条数据，建议搜索查询条件以缩小查询范围，再导出。",
	"csh.common.notice.need_wait_export_too_much_data":"导出共有 {0} 条数据，导出超过 {1} 条数据可能需要您耐心等待，仍需操作请确定继续。",
	"csh.common.address":"地址",
	"csh.common.print":"打印",
	"csh.common.action":"操作",
	"csh.common.dateFormatChina":"yyyy年MM月dd日",
	"csh.common.elderlyInfo":"老人基本信息",
	
	
	
	//租户用户
	"csh.tenantUser.add":"添加租户用户",
	"csh.tenantUser.search":"用户查询",
	"csh.tenantUser.list":"用户列表",
	"csh.tenantUser.realName":"姓名",
	"csh.tenantUser.gender":"性别",
	"csh.tenantUser.age":"年龄",
	"csh.tenantUser.staffID":"员工编号",
	"csh.tenantUser.staffStatus":"员工状态",
	"csh.tenantUser.staffStatus.inService":"在职",
	"csh.tenantUser.staffStatus.outService":"离职",
	"csh.tenantUser.department":"所在部门",
	"csh.tenantUser.position":"担任职务",
	"csh.tenantUser.hireDate":"入职时间",
	
	//department
	"csh.department.list":"部门列表",
	"csh.department.name":"部门名称",
	"csh.department.grade":"部门层级",
	"csh.department.parent":"上级部门",
	"csh.department.add":"添加部门",
	
	//position
	"csh.position.list":"职位列表",
	"csh.position.add":"添加职位",
	"csh.position.name":"职位名称",
	"csh.position.department":"隶属部门",

	//权限管理
	"csh.role.createDate":"创建时间",
	"csh.role.record":"角色管理",
	"csh.role.name":"角色名称",
	"csh.role.description":"描述",
	"csh.role.add":"添加角色",
	"csh.role.auth":"授权",
	"csh.role.auth.manange":"权限分配",
	"csh.role.auth.name":"菜单名称",
	"csh.role.auth.status":"权限状态",
	
	// 租户用户
	"csh.tenantAccount.add":"添加用户",
	"csh.tenantAccount.userName":"用户名",
	"csh.tenantAccount.realName":"真实姓名",
	"csh.tenantAccount.isSystem":"是否内置",
	"csh.tenantAccount.accoutStatus":"账号状态",
	"csh.tenantAccount.loginDate":"最后登录时间",
	"csh.tenantAccount.loginIp":"最后登录IP",
	"csh.tenantAccount.active":"账号正常",
	"csh.tenantAccount.list":"用户列表",
	"csh.tenantAccount.locked":"账号锁定",
	
};
//多语言
function message(code) {
	if (code != null) {
		var content = messages[code] != null ? messages[code] : code;
		if (arguments.length == 1) {
			return content;
		} else {
			if ($.isArray(arguments[1])) {
				$.each(arguments[1], function(i, n) {
					content = content.replace(
							new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			} else {
				$.each(Array.prototype.slice.apply(arguments).slice(1),
						function(i, n) {
							content = content.replace(new RegExp("\\{" + i
									+ "\\}", "g"), n);
						});
				return content;
			}
		}
	}
}
