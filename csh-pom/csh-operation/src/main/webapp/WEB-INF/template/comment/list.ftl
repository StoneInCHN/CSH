<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.comment.list")}</title>
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
		${message("common.current.position")}：${message("asup.main.comment")}&raquo; ${message("asup.comment.list")}
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
		[#if admin.isSystem ]
			<a href="javascript:;" id="deleteButtonFirst" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("asup.common.delete")}
			</a>
			<a href="javascript:;" id="refreshButton" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("asup.common.refresh")}
			</a>
		[#else]
			<a href="javascript:;" id="refreshButtonFirst" class="iconButton">
					<span class="refreshIcon">&nbsp;</span>${message("asup.common.refresh")}
			</a>
		[/#if]
			
			<div class="menuWrap">
				<div class="search">
					<input type="text" id="searchValue" name="searchValue" value="${page.searchValue}" maxlength="200" />
					<button type="submit">&nbsp;</button>
				</div>
				<span>
					<select id="searchPropertyOption"  class="selectField">
						<option value="" selected="selected">${message("asup.common.choose")}</option>
		                <option [#if page.searchProperty == "serviceName"] selected="selected"[/#if] value="serviceName">${message("asup.service.name")}</option>
		                <option [#if page.searchProperty == "rate"] selected="selected"[/#if] value="rate">${message("asup.comment.rate")}</option>
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
			<span>${message("asup.main.service")}</span>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					${message("asup.service.name")}
				</th>
				<th>
					${message("asup.comment.contents")}
				</th>
				<th>
					<a href="javascript:;" class="sort" name="rate">${message("asup.comment.rate")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">${message("asup.common.createDate")}</a>
				</th>
			</tr>
			[#list page.content as comment]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${comment.id}"/>
					</td>
					<td>
						${comment.service.name}
					</td>
					<td>
						[#if comment.contents??]
							<span title="${comment.contents}">${abbreviate(comment.contents, 10, "...")}</span>
						[/#if]
					</td>
					<td>
						${comment.rate}
					</td>
					<td>
						<span title="${comment.createDate?string("yyyy-MM-dd HH:mm:ss")}">${comment.createDate}</span>
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