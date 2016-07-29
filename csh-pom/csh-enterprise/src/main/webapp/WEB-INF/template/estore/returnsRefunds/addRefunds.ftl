	<form id="addRefunds_form" method="post" >
		<input type="hidden" name="orderId" value="${order.id}">
	    <table class="table table-striped"  border="0">
	    	<tr>
	    		<th>订单编号:</th>
	    		<td  width="200">
	    			 ${order.sn}
	    		</td>	   
	    		<th>创建时间:</th>
	    		<td>
	    			 ${order.createDate?string("yyyy-MM-dd HH:mm:ss")}
	    		</td> 			    		
	    	</tr>
	    	<tr>
	    		<th>订单金额:</th>
	    		<td>
	    			 ${order.amount}
	    		</td>	   
	    		<th>已付金额:</th>
	    		<td>
	    			 ${order.amountPaid}
	    		</td> 			    		
	    	</tr>	 
	    [#if order.isInvoice]
			<tr>
				<th>发票金额:</th>
				<td>${order.invoiceTitle}</td>
				<th>税金:</th>
				<td>${currency(order.tax, true)}</td>
			</tr>
		[/#if]
	    	<tr>
				<th>退款银行:</th>
				<td>
					<input class="easyui-textbox" type="text"  name="bank"  data-options="required:true" validtype="length[0,200]"/>
				</td>
				<th>退款账号:</th>
				<td>
						<input class="easyui-textbox" type="text"  name="account"  data-options="required:true" validtype="length[0,200]"/>
				</td>
			</tr>	
	    	<tr>
				<th>退款金额:</th>
				<td>
					<input class="easyui-textbox" type="text"  name="amount" value="${order.amountPaid}"  readonly="readonly"/>
				</td>
				<th>退款人:</th>
				<td>
						<input class="easyui-textbox" type="text"  name="payee"  data-options="required:true" validtype="length[0,200]"/>
				</td>
			</tr>		    	
	    	   	
	    	<tr>
	    		<th>方式:</th>
	    		<td>
	    			 <select  class="easyui-combobox" name="method" style="width:110px;" data-options="required:true">
	    				[#list refundsMethods as refundsMethod]
	    					<option value="${refundsMethod}" >${message("Refunds.Method." + refundsMethod)}</option>
	    				[/#list]   
				  	</select> 
	    		</td>
				<th>支付方式:</td>
				<td>

	    		</td>
	    	</tr>
	    	<tr>
	    		<th>备注 :</th>
	    		<td  colspan="3">
	    			 <input class="easyui-textbox" type="text"  name="memo"   data-options="multiline:true,height:80width:440" validtype="length[0,200]"/>
	    		</td>	  			    		
	    	</tr>		    	
	    </table>
	</form>
	<table  class="easyui-datagrid" id="addRefunds-orderItem-table-list"  fitColumns="true" style="width:100%;height:auto;">
		<thead>
			<tr>
				<th field="productSn" width="25%"  align="center">${message("csh.product.sn")}</th>
				<th field="name"  width="55%"  align="center">${message("csh.order.name")}</th>
				<th field="quantity" width="10%"   align="center">${message("csh.quantity")}</th>
				<th field="price" width="10%"   align="center">${message("csh.price")}</th>
			</tr>
		</thead>		
		   <tbody>   
		   [#list order.orderItems as orderItem]
		   <tr>   
            	<td>${orderItem.sn}</td>
            	<td>${orderItem.name}</td>
            	<td>${orderItem.quantity}</td>   
            	<td>${orderItem.price}</td>   
          </tr>   
		   [/#list]       
    </tbody>   
   </table>

