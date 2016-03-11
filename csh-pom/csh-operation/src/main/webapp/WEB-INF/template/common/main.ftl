
[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>${message("asup.main.title")}</title>
<link rel="shortcut icon" type="image/x-icon" href="${base}/resources/images/car.ico" media="screen" /> 
<link href="${base}/resources/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.cookie.js"></script>
<style type="text/css">
*{
	font: 12px 微软雅黑 ，tahoma, Arial, Verdana, sans-serif;
}
html, body {
	width: 100%;
	height: 100%;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $nav = $("#nav a");
	var $menu = $("#menu dl");
	var $menuItem = $("#menu a");
	var $accoutSetting = $("#accoutSetting");
	var $iframe=$("#iframe");
	
	$nav.click(function() {
		var $this = $(this);
		$nav.removeClass("current");
		$this.addClass("current");
		var $currentMenu = $($this.attr("href"));
		$menu.hide();
		$currentMenu.show();
		var $currentItem = $currentMenu.find("dd:first").find("a");
		$menuItem.removeClass("current");
		$currentItem.addClass("current");
		window.iframe.location.href= $currentItem.attr("href");
		return false;
	});
	
	$accoutSetting.click(function(){
		var $currentMenu = $("#account");
 		$nav.removeClass("current");
 		$menu.hide();
 		$currentMenu.show();
 		var $currentItem = $currentMenu.find("dd:first").find("a");
 		$currentItem.addClass("current");
 		window.iframe.location.href= $currentItem.attr("href");
 	});
 	
	$menuItem.click(function() {
		var $this = $(this);
		$menuItem.removeClass("current");
		$this.addClass("current");
	});

    $.cookie('user_name','[@shiro.principal /]',{expires:1,path:'/'})
});


</script>

</head>
<body>
	<script type="text/javascript">
		if (self != top) {
			top.location = self.location;
		};
	</script>
	<table class="main" border="0" rowspan="0" colspan="0">
		<tr>
			<th class="logo">
				<a href="main.jhtml">
					<span></span>
				</a>
			</th>
			<th >
				<div class="link">
					<strong>[@shiro.principal /]</strong>
					${message("asup.main.hello")}!
					<a href="#account" id="accoutSetting">[${message("asup.account.setting")}]</a>
					<a href="../common/logout.jhtml" target="_top">[${message("asup.main.logout")}]</a>
				</div>
			</th>
		</tr>
		<tr>
			<td class="preNav"></td>
			<td class="navTd">
				<div id="nav" class="nav">
					<ul>
						[#list ["admin:admin", "admin:role"] as permission]
							[@shiro.hasPermission name = permission]
								<li>
									<a href="#admin" [#if admin.isSystem]class="current" [/#if]>${message("asup.main.systemNav")}</a>
								</li>
								[#break /]
							[/@shiro.hasPermission]
						[/#list]
						[#list ["admin:apply"] as permission]
							[@shiro.hasPermission name = permission]
								<li>
									<a href="#apply" >${message("asup.nav.apply")}</a>
								</li>
								[#break /]
							[/@shiro.hasPermission]
						[/#list]
						[#list ["admin:vender"] as permission]
							[@shiro.hasPermission name = permission]
								<li>
									<a href="#vender" [#if !admin.isSystem ] class="current" [/#if]>${message("asup.nav.vender")}</a>
								</li>
								[#break /]
							[/@shiro.hasPermission]
						[/#list]
						[#list ["admin:service"] as permission]
							[@shiro.hasPermission name = permission]
								<li>
									<a href="#service" >${message("asup.nav.service")}</a>
								</li>
								[#break /]
							[/@shiro.hasPermission]
						[/#list]
						[#list ["admin:card","vendor:card"] as permission]
							[@shiro.hasPermission name = permission]
								<li>
									<a href="#card" >${message("asup.nav.card")}</a>
							</li>
								[#break /]
							[/@shiro.hasPermission]
						[/#list]
						[#list ["admin:message"] as permission]
							[@shiro.hasPermission name = permission]
								<li>
									<a href="#message">${message("asup.main.message")}</a>
								</li>
								[#break /]
							[/@shiro.hasPermission]
						[/#list]
						[#list ["admin:comment"] as permission]
							[@shiro.hasPermission name = permission]
								<li>
									<a href="#comment">${message("asup.main.comment")}</a>
								</li>
								[#break /]
							[/@shiro.hasPermission]
						[/#list]
					</ul>
				</div>
			</td>
		</tr>
		<tr>
			<td class="menuTd">
				<div id="menu" class="menu">
					<dl id="admin" [#if admin.isSystem ] class="default"[/#if]>
						<dt><i></i>${message("asup.login.welcome")}</dt>
						[@shiro.hasPermission name="admin:admin"]
							<dd>
								<a href="../admin/list.jhtml" class="current" target="iframe"><i></i>${message("asup.main.admin")}</a>
							</dd>
						[/@shiro.hasPermission]
						[@shiro.hasPermission name="admin:role"]
							<dd>
								<a href="../role/list.jhtml" target="iframe"><i></i>${message("asup.main.role")}</a>
							</dd>
						[/@shiro.hasPermission]
					</dl>
					<dl id="apply">
							<dt><i></i>${message("asup.login.welcome")}</dt>
							[@shiro.hasPermission name="admin:apply"]
							<dd>
								<a href="../apply/list.jhtml" target="iframe"><i></i>${message("asup.main.apply")}</a>
							</dd>
							[/@shiro.hasPermission]
					 </dl>
					 <dl id="account">
							<dt><i></i>${message("asup.login.welcome")}</dt>
							[@shiro.hasPermission name="setting:account"]
								<dd>
									<a href="../account/accountInfo.jhtml" target="iframe"><i></i>${message("asup.account.settingGroup")}</a>
								</dd>
							[/@shiro.hasPermission]
					 </dl>
					 <dl id="vender" [#if !admin.isSystem ] class="default" [/#if]>
							<dt><i></i>${message("asup.login.welcome")}</dt>
							[@shiro.hasPermission name="admin:vender"]
								<dd>
										[#if admin.isSystem]
											<a href="../vendor/list.jhtml" target="iframe"><i></i>${message("asup.nav.vender")}</a>
										[/#if]
										[#if !admin.isSystem && !admin.vendor  ]
											<a href="../vendor/add.jhtml" target="iframe"><i></i>${message("asup.nav.vender")}</a>
										[/#if]
										[#if !admin.isSystem && admin.vendor && (admin.vendor.vendorStatus  =="AUDIT_PASSED" || admin.vendor.vendorStatus  =="AUDIT_WAITING")]
											<a href="../vendor/info.jhtml" target="iframe"><i></i>${message("asup.nav.vender.info")}</a>
										[/#if]
										[#if !admin.isSystem && admin.vendor && admin.vendor.vendorStatus  =="AUDIT_FAILED" ]
											<a href="../vendor/edit.jhtml?id=${admin.vendor.id}" target="iframe"><i></i>${message("asup.nav.vender.edit")}</a>
										[/#if]
									
								</dd>
							[/@shiro.hasPermission]
					 </dl>
					 <dl id="service">
							<dt><i></i>${message("asup.login.welcome")}</dt>
							[@shiro.hasPermission name="admin:service"]
								<dd>
									<a href="../service/list.jhtml" target="iframe"><i></i>${message("asup.nav.service")}</a>
								</dd>
							[/@shiro.hasPermission]
					 </dl>
					 <dl id="card">
							<dt><i></i>${message("asup.login.welcome")}</dt>
							[@shiro.hasPermission name="vendor:card"]
								<dd>
									<a href="../card/vendor.jhtml" target="iframe"><i></i>${message("asup.vendor.card")}</a>
								</dd>
							[/@shiro.hasPermission]
							[@shiro.hasPermission name="admin:card"]
								<dd>
									<a href="../card/list.jhtml" target="iframe"><i></i>${message("asup.card.manage")}</a>
								</dd>
							[/@shiro.hasPermission]
					 </dl>
					 <dl id="message">
						<dt><i></i>${message("asup.login.welcome")}</dt>
						[@shiro.hasPermission name="admin:message"]
							<dd>
								<a href="../message/list.jhtml" class="current" target="iframe"><i></i>${message("asup.message.manage")}</a>
							</dd>
						[/@shiro.hasPermission]
					</dl>
					 <dl id="comment">
						<dt><i></i>${message("asup.login.welcome")}</dt>
						[@shiro.hasPermission name="admin:comment"]
							<dd>
								<a href="../comment/list.jhtml" class="current" target="iframe"><i></i>${message("asup.comment.list")}</a>
							</dd>
						[/@shiro.hasPermission]
					</dl>
				 </div>
			</td>
			<td class="iframeTd">
					[#if admin.isSystem]
						<iframe id="iframe" name="iframe" src="../admin/list.jhtml" frameborder="0" ></iframe>
					[/#if]
					[#if !admin.isSystem && !admin.vendor]
						<iframe id="iframe" name="iframe" src="../vendor/add.jhtml" frameborder="0" ></iframe>
					[/#if]
					[#if !admin.isSystem && admin.vendor]
						<iframe id="iframe" name="iframe" src="../vendor/info.jhtml" frameborder="0" ></iframe>
					[/#if]
					
					
			</td>
		</tr>
	</table>
</body>
</html>

