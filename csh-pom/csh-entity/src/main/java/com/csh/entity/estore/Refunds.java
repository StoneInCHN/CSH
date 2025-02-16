package com.csh.entity.estore;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.Method;
import com.csh.entity.commonenum.CommonEnum.RefundsStatus;

/**
 * Entity - 退款单
 * 
 */
@Entity
@Table(name = "csh_refunds",indexes={@javax.persistence.Index(name="receiverAddress_tenantid",columnList="tenantID")})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_refunds_sequence")
public class Refunds extends BaseEntity {

	private static final long serialVersionUID = 354885216604823632L;

	/** 退款单编号 */
	private String sn;
	
	/**  所属退货单的ID */
	private Long returnsID;

	/** 方式 */
	private Method method;

	/** 支付方式 */
	private String paymentMethod;

	/** 退款银行 */
	private String bank;

	/** 退款账号 */
	private String account;

	/** 退款金额 */
	private BigDecimal amount;

	/** 收款人 */
	private String payee;

	/** 操作员 */
	private String operator;

	/** 备注 */
	private String memo;
	
	/** 退款单状态 */
	private RefundsStatus refundsStatus;

	/** 订单 */
	private Order order;
	/**
    * 租户ID
    */
    private Long tenantID;
  
  @Field(index=org.hibernate.search.annotations.Index.YES,analyze=Analyze.NO,store=Store.NO)
  public Long getTenantID ()
  {
    return tenantID;
  }
  
  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }
	/**
	 * 获取编号
	 * 
	 * @return 编号
	 */
	@Column(nullable = false, updatable = false, unique = true, length = 100)
	public String getSn() {
		return sn;
	}

	/**
	 * 设置编号
	 * 
	 * @param sn
	 *            编号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 获取方式
	 * 
	 * @return 方式
	 */
	@NotNull
	@Column(nullable = false, updatable = false)
	public Method getMethod() {
		return method;
	}

	/**
	 * 设置方式
	 * 
	 * @param method
	 *            方式
	 */
	public void setMethod(Method method) {
		this.method = method;
	}

	/**
	 * 获取支付方式
	 * 
	 * @return 支付方式
	 */
	@Column(updatable = false)
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * 设置支付方式
	 * 
	 * @param paymentMethod
	 *            支付方式
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * 获取退款银行
	 * 
	 * @return 退款银行
	 */
	@Length(max = 200)
	@Column(updatable = false)
	public String getBank() {
		return bank;
	}

	/**
	 * 设置退款银行
	 * 
	 * @param bank
	 *            退款银行
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * 获取退款账号
	 * 
	 * @return 退款账号
	 */
	@Length(max = 200)
	@Column(updatable = false)
	public String getAccount() {
		return account;
	}

	/**
	 * 设置退款账号
	 * 
	 * @param account
	 *            退款账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 获取退款金额
	 * 
	 * @return 退款金额
	 */
	@NotNull
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(nullable = false, updatable = false, precision = 21, scale = 6)
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置退款金额
	 * 
	 * @param amount
	 *            退款金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 获取收款人
	 * 
	 * @return 收款人
	 */
	@Length(max = 200)
	@Column(updatable = false)
	public String getPayee() {
		return payee;
	}

	/**
	 * 设置收款人
	 * 
	 * @param payee
	 *            收款人
	 */
	public void setPayee(String payee) {
		this.payee = payee;
	}

	/**
	 * 获取操作员
	 * 
	 * @return 操作员
	 */
	@Column(nullable = false, updatable = false)
	public String getOperator() {
		return operator;
	}

	/**
	 * 设置操作员
	 * 
	 * @param operator
	 *            操作员
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	@Length(max = 200)
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置备注
	 * 
	 * @param memo
	 *            备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 获取订单
	 * 
	 * @return 订单
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orders", nullable = false, updatable = false)
	public Order getOrder() {
		return order;
	}

	/**
	 * 设置订单
	 * 
	 * @param order
	 *            订单
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	/**
	 * 获取退款单状态
	 * @return
	 */
    public RefundsStatus getRefundsStatus() {
      return refundsStatus;
    }
    /**
     * 设置退款单状态
     * @param refundsStatus
     */
    public void setRefundsStatus(RefundsStatus refundsStatus) {
      this.refundsStatus = refundsStatus;
    }

    public Long getReturnsID() {
      return returnsID;
    }

    public void setReturnsID(Long returnsID) {
      this.returnsID = returnsID;
    }
	

}