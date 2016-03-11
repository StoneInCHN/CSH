<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.message.send")}</title>
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
		${message("asup.current.position")}：${message("asup.message.manage")}&raquo; ${message("asup.message.send.list")} <span>(${message("asup.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="userSelect.jhtml?id=${msgId}" method="get">
		<div class="bar">
			<a href="list.jhtml" id="backButton" class="iconButton">
					<span class="button"></span>${message("asup.common.back")}
			</a>
			<a href="javascript:;" id="sendSelectedButton" class="iconButton disabled">
					<span class="button">&nbsp;</span>${message("asup.message.sendSelected")}
			</a>
			<a href="javascript:;" id="sendAllButton" class="iconButton">
					<span class="button">&nbsp;</span>${message("asup.message.sendAll")}
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
		                <option [#if page.searchProperty == "userName"] selected="selected"[/#if] value="userName">${message("asup.message.mobile")}</option>
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
			<span>${message("asup.message.userList")}</span>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="userName">${message("asup.message.userName")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">${message("asup.common.createDate")}</a>
				</th>
				
			</tr>
			[#list page.content as user]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${user.id}" />
						<input type="hidden" id="messageId" value="${msgId}"/>
					</td>
					<td>
						${user.userName}
					</td>
					<td>
						<span title="${user.createDate?string("yyyy-MM-dd HH:mm:ss")}">${user.createDate}</span>
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