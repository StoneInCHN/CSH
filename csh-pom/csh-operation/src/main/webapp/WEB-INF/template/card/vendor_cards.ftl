<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("asup.card.list")}</title>
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
		${message("asup.current.position")}：${message("asup.vendor.card")}&raquo; ${message("asup.card.list")} <span>(${message("asup.page.total", page.total)})</span>
	</div>
	<form id="listForm" action="vendor.jhtml" method="get">
		<div class="bar">
			[#if  admin.vendor.vendorStatus =="AUDIT_PASSED" ]
				<a href="add.jhtml" id="addButton" class="iconButton">
					<span class="addIcon">&nbsp;</span>${message("asup.common.add")}
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
				<span class="selectSpan">
					<select id="searchPropertyOption" class="selectField">
						<option value="" selected="selected">${message("asup.common.choose")}</option>
		                <option [#if page.searchProperty == "cardDescription"] selected="selected"[/#if] value="cardDescription">${message("asup.card.servicename")}</option>
		               
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
			<span>${message("asup.card.list")}</span>
		</div>
		<table id="listTable" class="list">
			<tr>
				<th>
					<a href="javascript:;" name="imagePath">${message("asup.card.imagePath")}</a>
				</th>
				<th>
					<a href="javascript:;" name="createDate">${message("asup.card.createDate")}</a>
				</th>
				<th>
					<a href="javascript:;" name="cardDescription">${message("asup.card.servicename")}</a>
				</th>
				<th>
					<a href="javascript:;" name="vendorName">${message("asup.card.cardDescription")}</a>
				</th>
				<th>
					<a href="javascript:;" name="effictiveFrom">${message("asup.card.startDate")}</a>
				</th>
				<th>
					<a href="javascript:;" name="effictiveTo">${message("asup.card.endDate")}</a>
				</th>
				<th>
					<a href="javascript:;" name="effictiveTo">${message("asup.card.expire")}</a>
				</th>
				<th>
					<a href="javascript:;" name="status">${message("asup.card.status")}</a>
				</th>
				<th>
					<a href="javascript:;" name="status">${message("asup.card.review.remark")}</a>
				</th>
			</tr>
			[#list page.content as card]
				<tr>
					<td>
						<a href="${base}/upload/card/${card.imagePath}" target="1024"><img src="${base}/upload/card/${card.imagePath}" style="max-width:60px;max-height:60px;padding:5px" alt="${message("asup.card.imagePath")}"/></a>
					</td>
					<td>
						[#if card.createDate??]
							<span title="${card.createDate?string("yyyy-MM-dd HH:mm:ss")}">${card.createDate}</span>
						[#else]
							-
						[/#if]
					</td>
					<td>
						${card.service.name}
					</td>
					<td>
						[#if card.cardDescription??]
      					   <span title="${card.cardDescription}">${abbreviate(card.cardDescription,45, "...")}</span>
      					[/#if]
					</td>
					<td>
						[#if card.effictiveFrom??]
							<span title="${card.effictiveFrom?string("yyyy-MM-dd HH:mm:ss")}">${card.effictiveFrom}</span>
						[#else]
							-
						[/#if]
					</td>
					<td>
						[#if card.effictiveTo??]
							<span title="${card.effictiveTo?string("yyyy-MM-dd HH:mm:ss")}">${card.effictiveTo}</span>
						[#else]
							-
						[/#if]
					</td>
					<td>
						[#if card.isExpire]
							${message("asup.card.expire.inactive")}
						[#else]
							${message("asup.card.expire.active")}
						[/#if]
					</td>
					<td>
						${message("asup.card.status."+card.status)}
					</td>
					<td>
						[#if card.remark??]
							<span title="${card.remark}">${abbreviate(card.remark,30, "...")}</span>
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