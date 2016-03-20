package com.csh.json.request;

import java.math.BigDecimal;

import com.csh.json.base.BaseRequest;

public class OrderRequest extends BaseRequest{
    
	/**
	 * 金额
	 */
    private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

    
    
}
