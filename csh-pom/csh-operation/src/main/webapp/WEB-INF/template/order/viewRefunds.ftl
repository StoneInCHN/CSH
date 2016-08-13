<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${message("csh.refunds.details")}</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${base}/resources/style/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/font-awesome.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/style.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/style/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/resources/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script type="text/javascript" src="${base}/resources/js/input.js"></script>
</head>
<body>
	<div class="mainbar">
		<div class="page-head">
			<div class="bread-crumb">
				<a ><i class="fa fa-user"></i> ${message("csh.main.order")}</a> 
				<span class="divider">/</span> 
				<a href="#" onclick="history.go(-1)"><i class="fa fa-list"></i>${message("csh.order.list")}</a>
				<span class="divider">/</span>
				<a  class="bread-current"><i class="fa fa-pencil-square-o"></i>${message("csh.refunds.details")}</a>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="matter">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="widget wgreen">
                <div class="widget-head">
                  <div class="pull-left">${message("csh.refunds.details")}</div>
                  <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                  <div class="padd">
							[#list refunds as refund]
							<table class="input tabContent">
					    	<tr>
					    		<th>${message("csh.refunds.sn")}:</th>
					    		<td  width="200">
					    			 ${refund.sn}
					    		</td>	   
					    		<th>${message("csh.common.createDate")}:</th>
					    		<td>
					    			 ${refund.createDate?string("yyyy-MM-dd HH:mm:ss")}
					    		</td> 			    		
					    	</tr>
					    	<tr>
					    		<th>${message("csh.refunds.paymentMethod")}:</th>
					    		<td>
					    			 ${refund.paymentMethod}
					    		</td>	   
					    		<th>${message("csh.refunds.status")}:</th>
					    		<td>
					    			[#if refund.refundsStatus??]
											${message("csh.refunds.status."+refund.refundsStatus)}
									[/#if]
					    		</td> 			    		
					    	</tr>
					    	<tr>
					    		<th>${message("csh.refunds.account")}:</th>
					    		<td>
					    			 ${refund.account}
					    		</td>	   
					    		<th>${message("csh.refunds.amount")}:</th>
					    		<td>
					    			 ${refund.amount}
					    		</td> 			    		
					    	</tr>	    	
					    	<tr>
					    		<th>${message("csh.refunds.payee")}:</th>
					    		<td>
					    			 ${refund.payee}
					    		</td>	   
					    		<th>${message("csh.order.operator")}:</th>
					    		<td>
					    			 ${refund.operator}
					    		</td> 			    		
					    	</tr>	
					    	<tr>
					    		<th>${message("csh.order.memo")}:</th>
					    		<td  colspan="3">
					    			${refund.memo}
					    		</td>	  			    		
					    	</tr>			    	
					    	</table>
					    	<hr>
					    	[/#list]
						<table class="input">
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="button" class="button" value="${message("csh.common.back")}" onclick="history.go(-1)" />
								</td>
							</tr>
						</table>
                  </div>
                </div>
              </div>  
            </div>
          </div>
        </div>
	   </div>
	</div>
	<script type="text/javascript" src="${base}/resources/js/custom.js"></script>
</body>
</html>