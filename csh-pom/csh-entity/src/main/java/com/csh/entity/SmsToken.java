package com.csh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.SmsTokenType;
import com.csh.entity.commonenum.CommonEnum.TokenSendType;

/**
 * Entity - 短信验证
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "csh_smstoken")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_smstoken_sequence")
public class SmsToken extends BaseEntity {

  private static final long serialVersionUID = -6055691215834161560L;

  /** 手机号码 */
  private String mobile;

  /** 短信验证码 */
  private String smsToken;

  /** 短信验证码过期时间token */
  private String timeoutToken;
  /** 短信验证码类型 */
  private SmsTokenType smsTokenType;
  
  /**
   * 发送方式
   */
  private TokenSendType sendType;
  
  

  public TokenSendType getSendType() {
	return sendType;
}

public void setSendType(TokenSendType sendType) {
	this.sendType = sendType;
}

public SmsTokenType getSmsTokenType() {
    return smsTokenType;
  }

  public void setSmsTokenType(SmsTokenType smsTokenType) {
    this.smsTokenType = smsTokenType;
  }

  @Column(nullable = false, length = 60)
  public String getTimeoutToken() {
    return timeoutToken;
  }

  public void setTimeoutToken(String timeoutToken) {
    this.timeoutToken = timeoutToken;
  }

  @Column(nullable = false, length = 6)
  public String getSmsToken() {
    return smsToken;
  }

  public void setSmsToken(String smsToken) {
    this.smsToken = smsToken;
  }

  @Column(nullable = false, length = 20)
  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

}
