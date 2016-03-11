<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.message.list")}</title>
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
		${message("asup.current.position")}：${message("asup.message.manage")}&raquo; ${message("asup.message.list")} <span>(${message("asup.page.total", page.total)})</span>
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
		                <option [#if page.searchProperty == "messageTitle"] selected="selected"[/#if] value="messageTitle">${message("asup.message.title")}</option>
		                <option [#if page.searchProperty == "messageContent"] selected="selected"[/#if] value="messageContent">${message("asup.message.content")}</option>
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
			<span>${message("asup.message.manage")}</span>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="messageTitle">${message("asup.message.title")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">${message("asup.message.createDate")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="messageType">${message("asup.message.type")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="messageContent">${message("asup.message.content")}</a>
				</th>
				<th>
					<span>${message("asup.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as msg]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${msg.id}" />
					</td>
					<td>
						${msg.messageTitle}
					</td>
					<td>
						[#if msg.createDate??]
							<span title="${msg.createDate?string("yyyy-MM-dd HH:mm:ss")}">${msg.createDate}</span>
						[#else]
							-
						[/#if]
					</td>
					<td>
						[#if msg.messageType == "personalMsg"]${message("asup.message.personalMsg")} [/#if]
						[#if msg.messageType == "systemMsg"]${message("asup.message.systemMsg")} [/#if]
						[#if msg.messageType == "broadcastMsg"]${message("asup.message.broadcastMsg")} [/#if]
					</td>
					<td>
						[#if msg.messageContent??]
      					   <span title="${msg.messageContent}">${abbreviate(msg.messageContent,50, "...")}</span>
      					[/#if]
					</td>
					<td>
						<a href="userSelect.jhtml?id=${msg.id}">[${message("asup.message.send")}]</a>
						<a href="edit.jhtml?id=${msg.id}">[${message("asup.common.edit")}]</a>
						<a href="receiver.jhtml?id=${msg.id}">[${message("asup.message.receiver")}]</a>
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