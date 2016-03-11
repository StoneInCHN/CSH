<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.role.list")}</title>
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
		${message("common.current.position")}：${message("asup.main.vendor")}&raquo; ${message("asup.vendor.list")}
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
		                <option [#if page.searchProperty == "vendorName"] selected="selected"[/#if] value="vendorName">${message("asup.vendor.vendorName")}</option>
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
			<span>${message("asup.main.vendor")}</span>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th class="check">
					<input type="checkbox" id="selectAll" />
				</th>
				<th>
					<a href="javascript:;" class="sort" name="vendorName">${message("asup.vendor.vendorName")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="vendorPhone">${message("asup.vendor.vendorPhone")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="vendorAddress">${message("asup.vendor.vendorAddress")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="vendorImagePath">${message("asup.vendor.vendorImagePath")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="vendorType">${message("asup.vendor.vendorType")}</a>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="vendorStatus">${message("asup.vendor.vendorStatus")}</a>
				</th>
				<th>
					<span>${message("asup.vendor.vendorIntro")}</span>
				</th>
				<th>
					<a href="javascript:;" class="sort" name="createDate">${message("asup.common.createDate")}</a>
				</th>
				<th>
					<span>${message("asup.common.handle")}</span>
				</th>
			</tr>
			[#list page.content as vendor]
				<tr>
					<td>
						<input type="checkbox" name="ids" />
					</td>
					<td>
						${vendor.vendorName}
					</td>
					<td>
						${vendor.vendorPhone}
					</td>
					<td>
						${vendor.vendorAddress}
					</td>
					<td>
						<a href="${base}/upload/vendor/${vendor.vendorImagePath}" target="1024"><img src="${base}/upload/vendor/${vendor.vendorImagePath}"  style="max-width:100px;max-height:100px;padding:5px" alt="${message("asup.vendor.image")}"></a>
					</td>
					<td>
						${message("asup.vendor.vendorType."+vendor.vendorType)}
					</td><td>
						${message("asup.vendor.vendorStatus."+vendor.vendorStatus)}
					</td>
					<td>
						[#if vendor.vendorIntro??]
							<span title="${vendor.vendorIntro}">${abbreviate(vendor.vendorIntro, 50, "...")}</span>
						[/#if]
					</td>
					<td>
						<span title="${vendor.createDate?string("yyyy-MM-dd HH:mm:ss")}">${vendor.createDate}</span>
					</td>
					<td>
						[#if  vendor.vendorStatus =="AUDIT_WAITING"]<a href="approval.jhtml?id=${vendor.id}">[${message("asup.common.approval")}]</a>[/#if]
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