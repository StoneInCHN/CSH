<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.admin.list")}</title>
<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/list.js"></script>
<style type="text/css">
	html{
		padding-left: 20px;
		padding-right: 15px;
	}
</style>
<script type="text/javascript">
$().ready(function() {

		[@flash_message /]

});
</script>
</head>
<body>
	<div class="path">
		${message("asup.current.position")}：${message("asup.main.admin")}&raquo; ${message("asup.admin.list")} <span>(${message("asup.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			<a href="add.jhtml" id="addButton" class="iconButton">
				<span class="addIcon">&nbsp;</span>${message("asup.common.add")}
			</a>
			<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("asup.common.delete")}
			</a>
			<a href="javascript:;" id="refreshButton" class="iconButton">
				<span class="refreshIcon">&nbsp;</span>${message("asup.common.refresh")}
			</a>
			<div class="menuWrap">
				<div class="search">
					<input type="text" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<span class="selectSpan">
					<select id="searchPropertyOption" class="selectField">
						<option value="" selected="selected">${message("asup.common.choose")}</option>
		                <option [#if page.searchProperty == "username"] selected="selected"[/#if] value="username">${message("asup.admin.username")}</option>
		                <option [#if page.searchProperty == "email"] selected="selected"[/#if] value="email">${message("asup.admin.email")}</option>
		                <option [#if page.searchProperty == "name"] selected="selected"[/#if] value="name">${message("asup.admin.name")}</option>
		             </select>
	            </span>
			</div>
			<div class="menuWrap">
				<a href="javascript:;" id="pageSizeSelect" class="button">
					${message("asup.page.pageSize")}
				</a>
				<div class="popupMenu">
						<ul id="pageSizeOption">
							<li>
								<a href="javascript:;"[#if page.pageSize == 10] class="current"[/#if] val="10">10</a>
							</li>
							<li>
								<a href="javascript:;"[#if page.pageSize == 20] class="current"[/#if] val="20">20</a>
							</li>
							<li>
								<a href="javascript:;"[#if page.pageSize == 50] class="current"[/#if] val="50">50</a>
							</li>
							<li>
								<a href="javascript:;"[#if page.pageSize == 100] class="current"[/#if] val="100">100</a>
							</li>
						</ul>
				</div>
			</div>
		</div>
		<div class="listTopBar">
			<span>${message("asup.main.admin")}</span>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="userName">${message("asup.admin.username")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="email">${message("asup.admin.email")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="name">${message("asup.admin.name")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="loginDate">${message("asup.admin.loginDate")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="loginIp">${message("asup.admin.loginIp")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">${message("asup.common.createDate")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="adminStatus">${message("asup.admin.adminStatus")}</a>
				</th>
				<th>
					<span>${message("asup.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as admin]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${admin.id}" />
					</td>
					<td>
						${admin.username}
					</td>
					<td>
						${admin.email}
					</td>
					<td>
						${admin.name}
					</td>
					<td>
						[#if admin.loginDate??]
							<span title="${admin.loginDate?string("yyyy-MM-dd HH:mm:ss")}">${admin.loginDate}</span>
						[#else]
							-
						[/#if]
					</td>
					<td>
						${(admin.loginIp)!"-"}
					</td>
					<td>
						<span title="${admin.createDate?string("yyyy-MM-dd HH:mm:ss")}">${admin.createDate}</span>
					</td>
					<td>
						${message("asup.admin.adminStatus."+admin.adminStatus)}
					</td>
					<td>
						<a href="edit.jhtml?id=${admin.id}">[${message("asup.common.edit")}]</a>
					</td>
				</tr>
			[/#list]
		</table>
		[@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
			[#include "/include/pagination.ftl"]
		[/@pagination]
	</form>
</body>
</html>