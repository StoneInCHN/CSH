<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.message.receiver")}</title>
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
		${message("asup.current.position")}：${message("asup.message.manage")}&raquo; ${message("asup.message.receiver")} <span>(${message("asup.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="receiver.jhtml?id=${msgId}" method="get">
		<div class="bar">
			<a href="list.jhtml" id="backButton" class="iconButton">
					<span class="button"></span>${message("asup.common.back")}
			</a>
			<a href="javascript:;" id="deleteSpecialButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("asup.common.delete")}
			</a>
			<a href="javascript:;" id="refreshButton" class="iconButton">
				<span class="refreshIcon">&nbsp;</span>${message("asup.common.refresh")}
			</a>
			
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
			<span>${message("asup.message.receiver")}</span>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" name="userName">${message("asup.message.userName")}</a>
				</th>
				<th>
					<a href="javascript:;" name="messageTitle">${message("asup.message.title")}</a>
				</th>
				<th>
					<a href="javascript:;" name="messageContent">${message("asup.message.content")}</a>
				</th>
				<th>
					<a href="javascript:;" name="isPush">${message("asup.message.isPush")}</a>
				</th>
				</th>
				<th>
					<a href="javascript:;" name="receiveDate">${message("asup.message.sendDate")}</a>
				</th>
			</tr>
			[#list page.content as relation]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${relation.id}" />
					</td>
					<td>
						${relation.user.userName}
					</td>
					<td>
						${relation.message.messageTitle}
					</td>
					<td>
						[#if relation.message.messageContent??]
      					   <span title="${relation.message.messageContent}">${abbreviate(relation.message.messageContent,50, "...")}</span>
      					[/#if]
					</td>
					<td>
						[#if relation.isPush]
							${message("asup.message.send.yes")} 
						[#else]
							${message("asup.message.send.no")} 
						[/#if]
						
					</td>
					<td>
						[#if relation.isPush]
							${relation.modifyDate?string("yyyy-MM-dd HH:mm:ss")}
						[#else]
							-
						[/#if]
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