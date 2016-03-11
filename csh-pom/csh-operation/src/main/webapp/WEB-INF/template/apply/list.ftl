<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.apply.list")}</title>
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
		${message("common.current.position")}：${message("asup.main.apply")}&raquo; ${message("asup.apply.list")} <span>(${message("asup.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
			<a href="javascript:;" id="deleteButtonFirst" class="iconButton disabled">
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
				<span>
					<select id="searchPropertyOption"  class="selectField">
						<option value="" selected="selected">${message("asup.common.choose")}</option>
		                <option [#if page.searchProperty == "email"] selected="selected"[/#if] value="email">${message("asup.apply.email")}</option>
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
			<span>${message("asup.main.apply")}</span>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="email">${message("asup.apply.email")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="nickname">${message("asup.apply.nickname")}</a>
				</th>
				<th>
					<span>${message("asup.apply.address")}</span>
				</th>
				<th>
					<span>${message("asup.apply.license")}</span>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="phone"><span>${message("asup.apply.phone")}</span>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="applyStatus"><span>${message("asup.apply.applyStatus")}</span>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">${message("asup.common.createDate")}</a>
				</th>
				<th>
					<span>${message("asup.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as apply]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${apply.id}"/>
					</td>
					<td>
						${apply.email}
					</td>
					<td>
						${apply.nickname}
					</td>
					<td>
						[#if apply.address??]
							<span title="${apply.address}">${abbreviate(apply.address, 50, "...")}</span>
						[/#if]
					</td>
					<td>
						<a href="${base}/upload/license/${apply.license}" target="1024"><img src="${base}/upload/license/${apply.license}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("asup.apply.license")}"></a>
					</td>
					<td>
						${apply.phone}
					</td>
					<td>
						${message("asup.apply.applyStatus."+apply.applyStatus)}
					</td>
					<td>
						<span title="${apply.createDate?string("yyyy-MM-dd HH:mm:ss")}">${apply.createDate}</span>
					</td>
					<td>
						[#if  apply.applyStatus =="AUDIT_WAITING"]<a href="edit.jhtml?id=${apply.id}">[${message("asup.common.edit")}]</a>[/#if]
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