package com.csh.beans;

import java.io.Serializable;

/**
 * 系统设置
 * 
 */
public class Setting implements Serializable {

  private static final long serialVersionUID = -1478999889661796840L;



  /** 缓存名称 */
  public static final String CACHE_NAME = "setting";

  /** 缓存Key */
  public static final Integer CACHE_KEY = 0;


  /** 密码最大长度 */
  private Integer passwordMaxlength;

  /** 密码最小长度 */
  private Integer passwordMinlength;

  /** 服务器端公钥 */
  private String serverPublicKey;

  /** 客户端公钥 */
  private String serverPrivateKey;

  /** 用户token过期时间 */
  private Integer tokenTimeOut;

  /** 用户名手机号正则表达式 */
  private String mobilePattern;

  /** 短信验证码过期时间 */
  private Integer smsCodeTimeOut;
  
  /** 邮箱验证token过期时间 */
  private Integer emailTokenTimeOut;
  /**修改领域间隔时间*/
  private Long domainTimeOutTime;
  
  /** 短信服务平台地址 */
  private String smsUrl;

  /** 短信平台登录用户名 */
  private String smsUser;

  /** 短信平台登录密码 */
  private String smsPwd;
  
  /** 短信平台apiKey */
  private String apiKey;

  /** 短信内容前缀 */
  private String smsTxtPrefix;

  /** 短信内容后缀 */
  private String smsTxtSuffix;
  
  /** 短信内容后缀(律师版) */
  private String smsTxtSuffixLawyer;

  /** 邮箱正则表达式 */
  private String emailPattern;
  /** 网站名称 */
  private String siteName;

  /** 发件人邮箱 */
  private String smtpFromMail;

  /** SMTP服务器地址 */
  private String smtpHost;

  /** SMTP服务器端口 */
  private Integer smtpPort;

  /** SMTP用户名 */
  private String smtpUsername;

  /** SMTP密码 */
  private String smtpPassword;
  
  /** 退款期限默认配置*/
  private String defaultRefundDayConfig;
  
  /** 结算最低金额默认配置 */
  private String defaultClearingLimitedAmt;
  
  /** 评论提交可仲裁等待时间（天数） */
  private Integer arbitrationOverDays;
  
  /** 问题自动过期时间（小时）*/
  private Integer defaultConsultationExpireation;
  
  /** 默认的模糊查询下拉菜单中返回的记录条数*/
  private Integer defaultPageSize;
  
  /** 个推平台注册应用后生成的appId（公众版）*/
  private String appId;
  
  /** 个推平台注册应用后生成的appKey（公众版）*/
  private String appKey;
  
  /** 个推平台注册应用后生成的masterSecret（公众版）*/
  private String masterSecret;
  
  /** 个推平台注册应用后生成的secret（公众版）*/
  private String appSecret;
  
  /** 个推平台注册应用后生成的appId（律师版）*/
  private String lawyerAppId;
  
  /** 个推平台注册应用后生成的appKey（律师版）*/
  private String lawyerAppKey;
  
  /** 个推平台注册应用后生成的masterSecret（律师版）*/
  private String lawyerMasterSecret;
  
  /** 个推平台注册应用后生成的secret（律师版）*/
  private String lawyerAppSecret;
  
  /** 个推平台消息推送Host*/
  private String pushHost;
  
  /** 消息中心*/
  private String msgSuffix;
  
  /** 免费咨询*/
  private String msgFreeService;
  
  /**
   * 网站域名
   */
  private String siteUrl;
  
  /**
   * 修改邮箱时修改链接提示前缀
   */
  private String editEmailTextPre;
  /**
   * 修改邮箱时修改链接提示后缀
   */
  private String editEmailTextSuffix;
  
  /**
   * 修改email是的链接前缀
   */
  private String editEmailSiteUrl;
  
  /**消息中心问题关闭待评价，拼接字符串1**/
  private String msgConsultation1;
  
  /**消息中心问题关闭待评价，拼接字符串2**/
  private String msgConsultation2;
  
  /**消息中心问题关闭待评价，拼接字符串3**/
  private String msgConsultation3;
  
  public String getMsgConsultation1() {
    return msgConsultation1;
  }

  public void setMsgConsultation1(String msgConsultation1) {
    this.msgConsultation1 = msgConsultation1;
  }

  public String getMsgConsultation2() {
    return msgConsultation2;
  }

  public void setMsgConsultation2(String msgConsultation2) {
    this.msgConsultation2 = msgConsultation2;
  }

  public String getMsgConsultation3() {
    return msgConsultation3;
  }

  public void setMsgConsultation3(String msgConsultation3) {
    this.msgConsultation3 = msgConsultation3;
  }

  public String getLawyerAppId() {
    return lawyerAppId;
  }

  public void setLawyerAppId(String lawyerAppId) {
    this.lawyerAppId = lawyerAppId;
  }

  public String getLawyerAppKey() {
    return lawyerAppKey;
  }

  public void setLawyerAppKey(String lawyerAppKey) {
    this.lawyerAppKey = lawyerAppKey;
  }

  public String getLawyerMasterSecret() {
    return lawyerMasterSecret;
  }

  public void setLawyerMasterSecret(String lawyerMasterSecret) {
    this.lawyerMasterSecret = lawyerMasterSecret;
  }

  public String getMsgFreeService() {
    return msgFreeService;
  }

  public void setMsgFreeService(String msgFreeService) {
    this.msgFreeService = msgFreeService;
  }

  public String getMsgSuffix() {
    return msgSuffix;
  }

  public void setMsgSuffix(String msgSuffix) {
    this.msgSuffix = msgSuffix;
  }

  public enum ImageType {
    /**
     * 营业执照
     */
    LICENSE,
    /**
     * 头像
     */
    PHOTO,
    /**
     * 建议
     */
    SUGGEST,
    /**
     * 音频
     */
    AUDIO
  }
  
  public Integer getArbitrationOverDays() {
    return arbitrationOverDays;
  }

  public void setArbitrationOverDays(Integer arbitrationOverDays) {
    this.arbitrationOverDays = arbitrationOverDays;
  }

  public Integer getPasswordMaxlength() {
    return passwordMaxlength;
  }

  public void setPasswordMaxlength(Integer passwordMaxlength) {
    this.passwordMaxlength = passwordMaxlength;
  }

  public Integer getPasswordMinlength() {
    return passwordMinlength;
  }

  public void setPasswordMinlength(Integer passwordMinlength) {
    this.passwordMinlength = passwordMinlength;
  }

  public String getServerPublicKey() {
    return serverPublicKey;
  }

  public void setServerPublicKey(String serverPublicKey) {
    this.serverPublicKey = serverPublicKey;
  }

  public String getServerPrivateKey() {
    return serverPrivateKey;
  }

  public void setServerPrivateKey(String serverPrivateKey) {
    this.serverPrivateKey = serverPrivateKey;
  }

  public Integer getTokenTimeOut() {
    return tokenTimeOut;
  }

  public void setTokenTimeOut(Integer tokenTimeOut) {
    this.tokenTimeOut = tokenTimeOut;
  }

  public String getMobilePattern() {
    return mobilePattern;
  }

  public void setMobilePattern(String mobilePattern) {
    this.mobilePattern = mobilePattern;
  }


  public Integer getSmsCodeTimeOut() {
    return smsCodeTimeOut;
  }

  public String getAppSecret() {
    return appSecret;
  }

  public void setAppSecret(String appSecret) {
    this.appSecret = appSecret;
  }

  public String getLawyerAppSecret() {
    return lawyerAppSecret;
  }

  public void setLawyerAppSecret(String lawyerAppSecret) {
    this.lawyerAppSecret = lawyerAppSecret;
  }

  public void setSmsCodeTimeOut(Integer smsCodeTimeOut) {
    this.smsCodeTimeOut = smsCodeTimeOut;
  }

  public String getSmsUrl() {
    return smsUrl;
  }

  public void setSmsUrl(String smsUrl) {
    this.smsUrl = smsUrl;
  }

  public String getSmsUser() {
    return smsUser;
  }

  public String getApiKey() {
    return apiKey;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public void setSmsUser(String smsUser) {
    this.smsUser = smsUser;
  }

  public String getSmsPwd() {
    return smsPwd;
  }

  public void setSmsPwd(String smsPwd) {
    this.smsPwd = smsPwd;
  }

  public String getSmsTxtPrefix() {
    return smsTxtPrefix;
  }

  public void setSmsTxtPrefix(String smsTxtPrefix) {
    this.smsTxtPrefix = smsTxtPrefix;
  }

  public String getSmsTxtSuffix() {
    return smsTxtSuffix;
  }

  public void setSmsTxtSuffix(String smsTxtSuffix) {
    this.smsTxtSuffix = smsTxtSuffix;
  }

  public String getSmsTxtSuffixLawyer() {
    return smsTxtSuffixLawyer;
  }

  public void setSmsTxtSuffixLawyer(String smsTxtSuffixLawyer) {
    this.smsTxtSuffixLawyer = smsTxtSuffixLawyer;
  }

  /**
   * 获取发件人邮箱
   * 
   * @return 发件人邮箱
   */
  public String getSmtpFromMail() {
    return smtpFromMail;
  }

  /**
   * 设置发件人邮箱
   * 
   * @param smtpFromMail 发件人邮箱
   */
  public void setSmtpFromMail(String smtpFromMail) {
    this.smtpFromMail = smtpFromMail;
  }

  /**
   * 获取SMTP服务器地址
   * 
   * @return SMTP服务器地址
   */
  public String getSmtpHost() {
    return smtpHost;
  }

  /**
   * 设置SMTP服务器地址
   * 
   * @param smtpHost SMTP服务器地址
   */
  public void setSmtpHost(String smtpHost) {
    this.smtpHost = smtpHost;
  }

  /**
   * 获取SMTP服务器端口
   * 
   * @return SMTP服务器端口
   */
  public Integer getSmtpPort() {
    return smtpPort;
  }

  /**
   * 设置SMTP服务器端口
   * 
   * @param smtpPort SMTP服务器端口
   */
  public void setSmtpPort(Integer smtpPort) {
    this.smtpPort = smtpPort;
  }

  /**
   * 获取SMTP用户名
   * 
   * @return SMTP用户名
   */
  public String getSmtpUsername() {
    return smtpUsername;
  }

  /**
   * 设置SMTP用户名
   * 
   * @param smtpUsername SMTP用户名
   */
  public void setSmtpUsername(String smtpUsername) {
    this.smtpUsername = smtpUsername;
  }

  /**
   * 获取SMTP密码
   * 
   * @return SMTP密码
   */
  public String getSmtpPassword() {
    return smtpPassword;
  }

  /**
   * 设置SMTP密码
   * 
   * @param smtpPassword SMTP密码
   */
  public void setSmtpPassword(String smtpPassword) {
    this.smtpPassword = smtpPassword;
  }

  public String getEmailPattern() {
    return emailPattern;
  }

  public void setEmailPattern(String emailPattern) {
    this.emailPattern = emailPattern;
  }

  public String getSiteName() {
    return siteName;
  }

  public void setSiteName(String siteName) {
    this.siteName = siteName;
  }

  public Integer getEmailTokenTimeOut() {
    return emailTokenTimeOut;
  }

  public void setEmailTokenTimeOut(Integer emailTokenTimeOut) {
    this.emailTokenTimeOut = emailTokenTimeOut;
  }

  public Long getDomainTimeOutTime() {
    return domainTimeOutTime;
  }

  public void setDomainTimeOutTime(Long domainTimeOutTime) {
    this.domainTimeOutTime = domainTimeOutTime;
  }

  public String getDefaultRefundDayConfig() {
    return defaultRefundDayConfig;
  }

  public void setDefaultRefundDayConfig(String defaultRefundDayConfig) {
    this.defaultRefundDayConfig = defaultRefundDayConfig;
  }

  public String getDefaultClearingLimitedAmt() {
    return defaultClearingLimitedAmt;
  }

  public void setDefaultClearingLimitedAmt(String defaultClearingLimitedAmt) {
    this.defaultClearingLimitedAmt = defaultClearingLimitedAmt;
  }

  public Integer getDefaultConsultationExpireation() {
    return defaultConsultationExpireation;
  }

  public void setDefaultConsultationExpireation(Integer defaultConsultationExpireation) {
    this.defaultConsultationExpireation = defaultConsultationExpireation;
  }

  public Integer getDefaultPageSize() {
    return defaultPageSize;
  }

  public void setDefaultPageSize(Integer defaultPageSize) {
    this.defaultPageSize = defaultPageSize;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getMasterSecret() {
    return masterSecret;
  }

  public void setMasterSecret(String masterSecret) {
    this.masterSecret = masterSecret;
  }

  public String getPushHost() {
    return pushHost;
  }

  public void setPushHost(String pushHost) {
    this.pushHost = pushHost;
  }

  public String getSiteUrl() {
    return siteUrl;
  }

  public void setSiteUrl(String siteUrl) {
    this.siteUrl = siteUrl;
  }

  public String getEditEmailTextPre() {
    return editEmailTextPre;
  }

  public void setEditEmailTextPre(String editEmailTextPre) {
    this.editEmailTextPre = editEmailTextPre;
  }

  public String getEditEmailTextSuffix() {
    return editEmailTextSuffix;
  }

  public void setEditEmailTextSuffix(String editEmailTextSuffix) {
    this.editEmailTextSuffix = editEmailTextSuffix;
  }

  public String getEditEmailSiteUrl() {
    return editEmailSiteUrl;
  }

  public void setEditEmailSiteUrl(String editEmailSiteUrl) {
    this.editEmailSiteUrl = editEmailSiteUrl;
  }
  
}
