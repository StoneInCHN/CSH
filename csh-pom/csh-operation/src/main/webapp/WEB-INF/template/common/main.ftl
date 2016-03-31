[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>${message("csh.main.title")}</title>
<link rel="shortcut icon" type="image/x-icon" href="${base}/resources/images/carlife.ico" media="screen" /> 
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/bootstrap-theme.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" media="screen">
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/main.css" rel="stylesheet" type="text/css" />
<!--[if lt IE 9]>
	<script src="${base}/resources/js/excanvas.min.js"></script>
	<script src="${base}/resources/js/respond.min.js"></script>  
<![endif]-->   

</head>
<body>
	<div class="header">
	    <div class="container">
	      <div class="row">
	        <div class="col-xs-4 col-md-4 col-lg-4">
	          <div class="logo">
	            <h1><a href="#">csh<span class="bold"></span></a></h1>
	            <p class="meta">后台管理系统</p>
	          </div>
	        </div>
	        <div class="col-xs-8 col-md-8 col-lg-8">
		      <nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">                
			        <ul class="nav navbar-nav pull-right">
			          <li class="dropdown pull-right">            
			            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
			              <i class="fa fa-user"></i> [@shiro.principal /] <b class="caret"></b>              
			            </a>
			            <ul class="dropdown-menu">
		             	 	<li><a href="../common/logout.jhtml" target="_top"><i class="fa fa-power-off"></i>&nbsp;&nbsp;${message("csh.main.logout")}</a></li>
			            </ul>
			          </li>
			        </ul>
		      </nav>
		   </div>
	    </div>
	  </div>
    </div>
    <div class="content">
      <div class="sidebar" >
      	<ul id="nav">
           [#list ["admin:admin", "admin:role","admin:area","admin:account"] as permission]
					[@shiro.hasPermission name = permission]
						<li class="has_sub" >
							<a href="#admin" ><i class="fa fa-cog"></i>&nbsp;&nbsp;${message("csh.main.systemNav")}<span class="pull-right"><i class="fa fa-chevron-right"></i></span></a>
							<ul class="sub_ul">
				              [@shiro.hasPermission name="admin:admin"]
								<li >
									<a href="../admin/list.jhtml"  target="iframe"> <i class="fa fa-user"></i>${message("csh.main.admin")}</a>
								</li>
						 	 	[/@shiro.hasPermission]
				                [@shiro.hasPermission name="admin:role"]
								<li>
									<a href="../role/list.jhtml" target="iframe"><i class="fa fa-male"></i>${message("csh.main.role")}</a>
								</li>
							   [/@shiro.hasPermission]
							 	[@shiro.hasPermission name="admin:area"]
								<li>
									<a href="../area/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("csh.main.area")}</a>
								</li>
							 	[/@shiro.hasPermission]
							 	[@shiro.hasPermission name="admin:advertisement"]
								<li>
									<a href="../advertisement/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("csh.main.advertisement")}</a>
								</li>
							 	[/@shiro.hasPermission]
							   [@shiro.hasPermission name="admin:account"]
								<li>
									<a href="../account/accountInfo.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("csh.account.settingGroup")}</a>
								</li>
							 	[/@shiro.hasPermission]
				            </ul>
						</li>
					[#break /]
			[/@shiro.hasPermission]
		[/#list]
		[#list ["admin:apply", "admin:tenantAccount","admin:tenantInfo"] as permission]
					[@shiro.hasPermission name = permission]
						<li class="has_sub" >
							<a href="#tenant" ><i class="fa fa-cog"></i>&nbsp;&nbsp;${message("csh.main.tenant")}<span class="pull-right"><i class="fa fa-chevron-right"></i></span></a>
							<ul class="sub_ul">
							   [@shiro.hasPermission name="admin:apply"]
								<li>
									<a href="../apply/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("csh.main.apply")}</a>
								</li>
							 	[/@shiro.hasPermission]
							 	[@shiro.hasPermission name="admin:tenantAccount"]
								<li>
									<a href="../tenantAccount/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("csh.main.tenantAccount")}</a>
								</li>
							 	[/@shiro.hasPermission]
							 	[@shiro.hasPermission name="admin:tenantInfo"]
								<li>
									<a href="../tenantInfo/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("csh.main.tenantInfo")}</a>
								</li>
							 	[/@shiro.hasPermission]
				            </ul>
						</li>
					[#break /]
			[/@shiro.hasPermission]
		[/#list]
		[#list ["admin:deviceType", "admin:deviceInfo"] as permission]
					[@shiro.hasPermission name = permission]
						<li class="has_sub" >
							<a href="#tenant" ><i class="fa fa-cog"></i>&nbsp;&nbsp;${message("csh.main.device")}<span class="pull-right"><i class="fa fa-chevron-right"></i></span></a>
							<ul class="sub_ul">
							   [@shiro.hasPermission name="admin:deviceType"]
								<li>
									<a href="../deviceType/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("csh.main.deviceType")}</a>
								</li>
							 	[/@shiro.hasPermission]
							 	[@shiro.hasPermission name="admin:deviceInfo"]
								<li>
									<a href="../deviceInfo/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("csh.main.deviceInfo")}</a>
								</li>
							 	[/@shiro.hasPermission]
				            </ul>
						</li>
					[#break /]
			[/@shiro.hasPermission]
		[/#list]
		[#list ["admin:distributor"] as permission]
					[@shiro.hasPermission name = permission]
						<li class="has_sub" >
							<a href="#tenant" ><i class="fa fa-cog"></i>&nbsp;&nbsp;${message("csh.main.distributor")}<span class="pull-right"><i class="fa fa-chevron-right"></i></span></a>
							<ul class="sub_ul">
							   [@shiro.hasPermission name="admin:distributor"]
								<li>
									<a href="../distributor/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("csh.main.distributor")}</a>
								</li>
							 	[/@shiro.hasPermission]
				            </ul>
						</li>
					[#break /]
			[/@shiro.hasPermission]
		[/#list]
		[#list ["admin:vehicleBrand" ,"admin:vehicleLine"] as permission]
			[@shiro.hasPermission name = permission]
				<li class="has_sub" >
					<a href="#tenant" ><i class="fa fa-cog"></i>&nbsp;&nbsp;${message("csh.main.vehicleBrand")}<span class="pull-right"><i class="fa fa-chevron-right"></i></span></a>
						<ul class="sub_ul">
							[@shiro.hasPermission name="admin:vehicleBrand"]
								<li>
									<a href="../vehicleBrand/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("csh.main.vehicleBrand")}</a>
								</li>
							[/@shiro.hasPermission]
							 [@shiro.hasPermission name="admin:vehicleLine"]
								<li>
									<a href="../vehicleLine/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("csh.main.vehicleLine")}</a>
								</li>
							 	[/@shiro.hasPermission]
							 [@shiro.hasPermission name="admin:vehicleBrandDetail"]
								<li>
									<a href="../vehicleBrandDetail/list.jhtml" target="iframe"><i class="fa fa-cog"></i>${message("csh.main.vehicleBrandDetail")}</a>
								</li>
							 	[/@shiro.hasPermission]
				       </ul>
				</li>
				[#break /]
			[/@shiro.hasPermission]
		[/#list]
       </ul>
      </div>
      <div class="mainbar">
		  <iframe  id="iframe" name="iframe" marginheight="0" marginwidth="0" frameborder="0" style="width:100%"scrolling="no" src="../account/accountInfo.jhtml" "></iframe>
	 </div>
</div>
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
<script type="text/javascript" src="${base}/resources/js/resize.js"></script>
<script type="text/javascript">
	$(function(){
		var $sub_li = $(".sub_ul li");
		$sub_li.click(function(){
			var $this = $(this);
			$sub_li.removeClass("active");
			$this.addClass("active");
		})
	})
</script>
</body>
</html>
