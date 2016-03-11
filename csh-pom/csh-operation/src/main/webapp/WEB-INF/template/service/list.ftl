<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.service.list")}</title>
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
		${message("common.current.position")}：${message("asup.main.service")}&raquo; ${message("asup.service.list")}
	</div>
	<form id="listForm" action="list.jhtml" method="get">
		<div class="bar">
		[#if !admin.isSystem && admin.vendor.vendorStatus =="AUDIT_PASSED"]
			<a href="add.jhtml" id="addButton" class="iconButton">
				<span class="addIcon">&nbsp;</span>${message("asup.common.add")}
			</a>
			<a href="javascript:;" id="deleteButton" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("asup.common.delete")}
			</a>
		[/#if]
		[#if !admin.isSystem && admin.vendor.vendorStatus !="AUDIT_PASSED" ]
			<a href="javascript:;" id="deleteButtonFirst" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("asup.common.delete")}
			</a>
		[/#if]
		[#if admin.isSystem ]
			<a href="javascript:;" id="deleteButtonFirst" class="iconButton disabled">
					<span class="deleteIcon">&nbsp;</span>${message("asup.common.delete")}
			</a>
		[/#if]
			
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
		                <option [#if page.searchProperty == "name"] selected="selected"[/#if] value="name">${message("asup.service.name")}</option>
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
					<a href="javascript:;" class="sort" name="name">${message("asup.service.name")}</a>
				</th>
				<th>
					${message("asup.service.intro")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="price">${message("asup.service.price")}</a>
				</th>
				<th>
					${message("asup.service.imgPath")}
				</th>
				<th>
					<a href="javascript:;" class="sort" name="serviceCategory">${message("asup.service.serviceCategory")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="serviceStatus"><span>${message("asup.service.serviceStatus")}</span>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">${message("asup.common.createDate")}</a>
				</th>
				<th>
					<span>${message("asup.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as service]
				<tr>
					<td>
						<input type="checkbox" name="ids" value="${service.id}"/>
					</td>
					<td>
						${service.name}
					</td>
					<td>
						[#if service.intro??]
							<span title="${service.intro}">${abbreviate(service.intro, 10, "...")}</span>
						[/#if]
					</td>
					<td>
						${service.price}
					</td>
					<td>
						<a href="${base}/upload/service/${service.imgPath}" target="1024"><img src="${base}/upload/service/${service.imgPath}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("asup.service.imgPath")}"></a>
					</td>
					<td>
						${service.serviceCategory.categoryName}
					</td>
					<td>
						${message("asup.service.serviceStatus."+service.serviceStatus)}
					</td>
					<td>
						<span title="${service.createDate?string("yyyy-MM-dd HH:mm:ss")}">${service.createDate}</span>
					</td>
					<td>
						[#if !admin.isSystem && service.serviceStatus =="AUDIT_FAILED"]<a href="edit.jhtml?id=${service.id}">[${message("asup.common.edit")}]</a>[/#if]
						[#if admin.isSystem && service.serviceStatus =="AUDIT_WAITING"]<a href="approval.jhtml?id=${service.id}">[${message("asup.common.approval")}]</a>[/#if]
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