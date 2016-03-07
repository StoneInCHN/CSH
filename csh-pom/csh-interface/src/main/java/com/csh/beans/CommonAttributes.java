package com.csh.beans;

/**
 * 公共参数
 * 
 */
public final class CommonAttributes {

  /** 日期格式配比 */
  public static final String[] DATE_PATTERNS = new String[] {"yyyy", "yyyy-MM", "yyyyMM",
      "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss",
      "yyyy/MM/dd HH:mm:ss"};

  /** common-config.xml文件路径 */
  public static final String COMMON_CONFIG_XML_PATH = "/common-config.xml";

  /** common-config.properties文件路径 */
  public static final String COMMON_CONFIG_PROPERTIES_PATH = "/common-config.properties";

  public static final String API_TOKEN = "token";
  
  /** 存放paramMap的 DISTINCT_KEY */
  public static final String DISTINCT_KEY="distinct";
  
  /** 存放调用登陆api后返回的token */
  public static final String API_TOKEN_SESSION = "tokenSession";

  public static final String API_USERID = "user_id";

  /** 找回密码时返回的token */
  public static final String PWD_TOKEN = "pwd_token";

  /** 存放调用登陆api后返回的user_id */
  public static final String API_USERID_SESSION = "userIdSession";

  /**请求成功*/
  public static final String SUCCESS = "0000";// 请求成功
  
  /**登录失败*/
  public static final String FAIL_LOGIN = "0001";// 登录失败
  /**注册失败*/
  public static final String FAIL_REG = "0002";// 注册失败
  /**短信验证码相关操作失败*/
  public static final String FAIL_SMSTOKEN = "0003";// 短信验证码相关操作失败
  /**Token 失效*/
  public static final String FAIL_TOKEN_TIMEOUT = "0004";// Token 失效
  /**重置密码失败*/
  public static final String FAIL_RESET_PWD = "0005";// 重置密码失败
  /**缺失必要参数*/
  public static final String MISSING_REQUIRE_PARAM = "0006";// 缺失必要参数
  
  public static final String FAIL_INAVAILABLE_PARAM = "0007";// 请求参数无效
  /**终端用户修改个人信息失败*/
  public static final String FAIL_ENDUSER_INFO = "0008";// 终端用户修改个人信息失败
 
  public static final String FAIL_ORDER_EXPIRED = "0009"; //订单已过期
  
  /**尚未完成填写律师信息*/
  public static final String ACCOUNT_NOT_COMPLETE = "0010";// 尚未完成填写律师信息
 
  /**余额不足*/
  public static final String FAIL_INSUFFICIENT_BALANCE = "0011"; // 余额不足
  /**手机号已存在*/
  public static final String FAIL_MOBILENUM_EXIST = "0012"; // 手机号已存在
  /**手机号格式错误*/
  public static final String FAIL_MOBILENUM_BAD_FORMAT = "0013"; // 手机号格式错误
  /**操作失败*/
  public static final String SEND_EMAIL_SUCCESS = "0014";  //邮件发送成功
  /**操作失败*/
  public static final String SEND_EMAIL_FAILED= "0015";  //邮件发送失败
  /**邮箱格式错误*/
  public static final String FAIL_EMAIL_BAD_FORMAT = "0016"; // 手机号格式错误
  /**有新产生的可结算项*/
  public static final String NOTIFY_NEW_CLEARING_ITEM = "0017"; // 当用律师点击结算的时候同时产生了新结算项
  /**尚未通过管理员审核*/
  public static final String ACCOUNT_NOT_VALID = "0020";// 尚未通过管理员审核
  /**账号审核状态已改变*/
  public static final String ACCOUNT_AUDIT_STATUS_CHANGE = "0018";//账号审核状态已改变
  /**审核被管理员退回*/
  public static final String ACCOUNT_AUDIT_FAILED = "0019";// 审核被管理员退回
  
  public static final String DOMAIN_TIME_OUT_LT = "0021";
  /**服务未开始不能退款*/
  public static final String REFUND_FAIL_SERVICE_NOT_STARTED = "0022";// 服务未开始不能退款
  /**服务超过配置时间不能退款*/
  public static final String REFUND_FAIL_SERVICE_OVER_DATE = "0023";// 服务超过配置时间不能退款
  /**律师未回复但是超过三天之后不能退款 */
  public static final String REFUND_FAIL_SERVICE_OVER_DATE_NO_RESP = "0030";// 律师未回复但是超过三天之后不能退款 
  /**退款成功,审核中*/
  public static final String REFUND_SUCCESS = "0024";// 退款成功,审核中

  /**没有邮箱修改记录，无需取消*/
  public static final String MODIFY_EMAIL_RECORD_NOT_EXIST="0025";//没有邮箱修改记录，无需取消
  /**该订单已经申请退款,请勿再次提交*/
  public static final String REFUND_ALREADY_SUBMITTED = "0026"; //该订单已经申请退款,请勿再次提交
  
  /**申请退款  */
  public static final String REFUND_REQUEST="0027";//申请退款
  
  /**结算金额小于系统设定额度  */
  public static final String CLEARING_AMT_LESSTHAN_LIMITED_AMT="0028";//结算金额小于系统设定额度 
  /**法律援助售已经销售完 */
  public static final String LEGAL_AID_SALE_OUT="0029"; //法律援助销售完
 
  /**律师未设置银行卡和姓名信息 */
  public static final String CLEARING_NO_BANKACCOUNT_INFO="0032";//律师未设置银行卡和姓名信息
  /**操作失败*/
  public static final String FAIL_SIGNIN = "0030";  //签到失败
  
  public static final String NO_PENDING_CONSULATATION_STATUS="0031";//问题不是pending状态
  public static final String FAIL_CONSULATATION_CLOSED="0033";//问题已经关闭
  public static final String FAIL_CONSULATATION_REJECTED_PENDING="0034";//回复初始状态的问题，失败
  public static final String FAIL_CONSULATATION_REJECTED_COMMENTED="0035";//拒绝回答已评论问题，失败
  
  public static final String REFUND_FAIL_SERVICE_CONSULATATION_CLOSED="0036";//申请退款时问题已关闭，失败
  public static final String REFUND_FAIL_SERVICE_NOT_TO_DATE="0037";//申请退款时，距购买服务的时间未达到配置的退款期限，失败
  
  /**操作失败*/
  public static final String FAIL_COMMON = "10000";  //操作失败
  /**没有取得任何信息*/
  public static final String FAIL_NO_INFO = "2001";// 没有取得任何信息
  /**用户被禁用或删除*/
  public static final String USER_INVALID = "2002";// 用户被禁用或删除
  /**只能删除已关闭并且未评论的问题*/
  public static final String CONSULTATIN_CANNOT_DELETE = "0034"; //只能删除已关闭并且未评论的问题
  /**
   * 不可实例化
   */
  private CommonAttributes() {}

}
