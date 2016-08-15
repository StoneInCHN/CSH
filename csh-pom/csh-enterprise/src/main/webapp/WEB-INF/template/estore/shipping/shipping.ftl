<script src="${base}/resources/modules/estore/shipping.js"></script>
<div class="easyui-layout" data-options="fit:true">
      		<div data-options="region:'west',split:true" style="width:45%">
             			<div id="order-panel" class="easyui-panel" title="${message("csh.shipping.order")}"    style="width:100%;height:55%;background:#fafafa;"  data-options="collapsible:true">									
									<fieldset style="padding:5px 0px 4px 4px;margin:0px">
								        <form id="shipping-order-search-form" class="search-form">
											<div class="search-item">
											   <label>${message("csh.order.sn")}:</label>
											   <input type="text" class="easyui-textbox"  name="orderSnSearch"  validtype="length[0,20]"/>
											</div>
										</form>
										<div class="search-item">
									  	  <button id="shipping-order-search-btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-search'">${message("csh.search")}</button>
									    </div>
									</fieldset>
									<div id="shipping-order_manager_tool">
										<div class="tool-button">
											<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain=true onclick="shipping_manager_tool.addShipping();">${message("csh.button.shipping")}</a>
										</div>
										<div class="tool-filter"></div>
									</div>
					                <table id="shipping-order-table-list"></table>
			 			</div>
						<div id="orderItem-panel" class="easyui-panel" title="${message("csh.shipping.orderItem")}"  style="width:100%;height:auto;background:#fafafa;"  data-options="collapsible:true">
										<table id="shipping-orderItem-table-list"  fitColumns="true" style="width:100%;height:auto;">
											<thead>
												<tr>
													<th field="orderSn" width="20%"  align="center">${message("csh.order.sn")}</th>
													<th field="productSn" width="15%"  align="center">${message("csh.product.sn")}</th>
													<th field="name"  width="45%"  align="center">${message("csh.order.name")}</th>
													<th field="quantity" width="10%"   align="center">${message("csh.quantity")}</th>
													<th field="price" width="10%"   align="center">${message("csh.price")}</th>
												</tr>
											</thead>
										</table>
						</div>
			</div>
			<div data-options="region:'center'" style="width:55%">
             			<div id="shipping-panel" class="easyui-panel" title="${message("csh.shipping.shipping")}"    style="width:100%;height:55%;background:#fafafa;"  data-options="collapsible:true">	
									<fieldset style="padding:5px 0px 4px 4px;margin:0px">
								        <form id="shipping-search-form" class="search-form">
											<div class="search-item">
											   <label>${message("csh.shipping.sn")}:</label>
											   <input type="text" class="easyui-textbox"  name="shippingSnSearch" validtype="length[0,20]"/>
											</div>
										</form>
										<div class="search-item">
									  	  <button id="shipping-search-btn" class="easyui-linkbutton" style="margin-left:2px;margin-right:2px"data-options="iconCls:'icon-search'">${message("csh.search")}</button>
									    </div>
									</fieldset>
					                <table id="shipping-table-list"></table>
			 			</div>
						<div id="shippingItem-panel" class="easyui-panel" title="${message("csh.shipping.shippingItem")}"  style="width:100%;height:auto;background:#fafafa;"  data-options="collapsible:true">
										<table id="shippingItem-table-list"  fitColumns="true" style="width:100%;height:auto;">
											<thead>
												<tr>
													<th field="shippingSn" width="25%"  align="center">${message("csh.shipping.sn")}</th>
													<th field="productSn" width="25%"  align="center">${message("csh.product.sn")}</th>
													<th field="name"  width="40%"  align="center">${message("csh.order.name")}</th>
													<th field="quantity" width="10%"   align="center">${message("csh.quantity")}</th>
												</tr>
											</thead>
										</table>
						</div>						
			</div>	
</div>
<div id="addShipping"></div>
<div id="editShipping"></div>
<div id="shippingDetail"></div>
<div id="shippingOrderDetail"></div>




