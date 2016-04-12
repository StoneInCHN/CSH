package com.csh.entity.commonenum;

/**
 * 公共枚举数据
 * 
 * @author shijun
 *
 */
public class CommonEnum {

  /**
   * 配置元属性基本数据类型
   * 
   * @author shijun
   *
   */
  public enum MetaDataType {
    SHORT, INT, LONG, DOUBLE, DECIMAL, STRING
  }

  /**
   * 配置元之间关系
   */
  public enum MetaRelation {
    /**
     * 依赖
     */
    DEPEND,
    /**
     * 包含
     */
    INCLUDE,
    /**
     * 关联
     */
    ASSOCIATION
  }



  /**
   * 短信验证码类型
   * 
   * @author sujinxuan
   *
   */
  public enum SmsTokenType {
    /** 注册 */
    REG,
    /** 找回密码 */
    FINDPWD
  }

  /**
   * 验证码发送方式
   * 
   * @author sujinxuan
   *
   */
  public enum TokenSendType {
    /** 短信 */
    SMS,
    /** 语音 */
    VOICE
  }


  /**
   * 帐号状态
   */
  public enum AccountStatus {

    /** 帐号正常 */
    ACTIVED,

    /** 帐号禁用 */
    LOCKED,

    /** 帐号删除 */
    DELETE
  }

  /**
   * 性别
   */
  public enum Gender {

    /** 男 */
    MALE,

    /** 女 */
    FEMALE
  }

  /**
   * 员工状态
   */
  public enum StaffStatus {

    /** 在职 */
    INSERVICE,

    /** 离职 */
    OUTSERVICE
  }



  /**
   * 通用状态
   * 
   * @author shijun
   *
   */
  public enum Status {
    /**
     * 启用
     */
    ENABLE,
    /**
     * 禁用
     */
    DISABLE
  }
  /**
   * 设备状态
   * 
   * @author huyong
   *
   */
  public enum DeviceStatus {
    /**
     * 初始状态
     */
    INITED,
    /**
     * 下发
     */
    SENDOUT,
    /**
     * 已出库
     */
    STORAGEOUT,
    /**
     * 已绑定
     */
    BINDED,

    /**
     * 退还
     */
    REFUNDED

  }

  /**
   * 绑定状态
   * 
   * @author huyong
   *
   */
  public enum BindStatus {
    /**
     * 已绑定
     */
    BINDED,
    /**
     * 未绑定
     */
    UNBINDED
  }

  /**
   * 省简称
   * 
   * @author shijun
   *
   */
  public enum IllegalArea {
    
    BEIJINGSHI("beijing"),
    
    TIANJIN("tianjin"),
    
    HEBEI("hebei"),
    
    SHANXI1("shanxi"),
    
    NEIMENGGU("neimenggu"),
    
    LIAONING("liaoning"),
    
    JILIN("jilin"),
    
    HEILONGJIANG("heilongjiang"),
    
    SHANGHAI("shanghai"),
    
    JIANGSU("jiangsu"),
    
    ZHEJIANG("zhejiang"),
    
    ANHUI("anhui"),
    
    FUJIAN("fujian"),
    
    JIANGXI("jiangxi"),
    
    SHANDONG("shandong"),
    
    HENAN("henan"),
    
    HUBEI("hubei"),
    
    HUNAN("hunan"),
    
    GUANGDONG("guangdong"),
    
    GUANGXI("guangxi"),
    
    HAINAN("hainan"),
    
    CHONGQING("chongqing"),
    
    SICHUAN("sichuan"),
    
    GUIZHOU("guizhou"),
    
    YUNNAN("yunnan"),
    
    XIZANG("xizang"),
    
    SHANXI("shanxi"),    
    
    GANSU("gansu"),
    
    QINGHAI("qinghai"),
    
    NINGXIA("ningxia"),
    
    XINJIANG("xinjiang"),
    
    XIANGGANG("xianggang"),
    
    AOMEN("aomen"),
    
    TAIWAN("taiwan");

    private IllegalArea(String provienceShortName){
      this.provienceShortName = provienceShortName;
    }
    
    private String provienceShortName;

    public String getProvienceShortName() {
      return provienceShortName;
    }
  }

  /**
   * 逻辑删除标记
   * 
   * @author shijun
   *
   */
  public enum DeleteStatus {
    /**
     * 没有删除
     */
    NOT_DELETED,
    /**
     * 删除
     */
    DELETED
  }


  /**
   * 版本状态
   * 
   * @author huyong
   *
   */
  public enum VersionStatus {
    /**
     * 可用
     */
    ENABLE,
    /**
     * 禁用
     */
    DISABLE
  }

  /**
   * 自增类型
   */
  public enum IdentifierType {
    /**
     * 租户机构代码自增
     */
    ORGCODE
  }

  /**
   * 树节点展开状态
   * 
   * @author tanbiao
   *
   */
  public enum TreeNodeState {
    /**
     * 展开
     */
    open,
    /**
     * 关闭
     */
    closed
  }

  public enum SystemType {
    /**
     * 运营管理系统
     */
    OPERATION,
    /**
     * 多租户系统
     */
    ENTERPRISE,
    /**
     * 接口系统
     */
    INTERFACE
  }

  public enum ServiceType {
    /**
     * 洗车
     */
    WASHING,
    /**
     * 美容
     */
    COSMETOLOGY,
    /**
     * 保养
     */
    UPKEEP,
    /**
     * 加油
     */
    REFUEL;
  }

  public enum ImageType {

    /**
     * 头像
     */
    PHOTO,
    /**
     * 服务照片
     */
    CARSERVICEPICTURE,
    /**
     * 行驶证照片
     */
    DRIVINGLICENSEPHOTO,
    /**
     * 驾驶证照片
     */
    DRIVERLICENSEPHOTO,
    /**
     * 身份证照片
     */
    IDPHOTO
  }


  /**
   * 服务状态
   * 
   * @author sujinxuan
   *
   */
  public enum ServiceStatus {

    /** 可用 */
    ENABLED,

    /** 禁用 */
    DISABLED,

  }
  /**
   * 审核状态
   * 
   * @author tanbiao
   *
   */
  public enum ApplyStatus {
    /** 待审核 */
    AUDIT_WAITING,

    /** 审核通过 */
    AUDIT_PASSED,

    /** 审核退回 */
    AUDIT_FAILED

  }
  /**
   * 预约信息来源
   * 
   * @author huyong
   *
   */
  public enum ReservationInfoFrom {
    /**
     * 来自app
     */
    APP,
    /**
     * 电话预约
     */
    CALL
  }

  public enum MessageType {
    /** 个人消息 */
    PERSONALMSG,
    /** 新闻消息 */
    NEWSMSG,
    /** 活动消息 */
    PROMOTION

  }
  
 public enum SendType{
    /** 推送消息*/
    PUSH,
    /**
     * 短信消息
     */
    SMS
  }

  public enum BalanceType {
    /** 收入 */
    INCOME,
    /** 支出 */
    OUTCOME

  }

  public enum WalletType {
    /** 余额 */
    MONEY,
    /** 红包 */
    REDPACKET,
    /** 积分 */
    SCORE
  }

  public enum PaymentType {
    /** 支付宝 */
    ALIPAY,
    /** 微信 */
    WECHAT,
    /** 钱包余额 */
    WALLET
  }

  public enum ChargeStatus {
    /*** 预约 */
    RESERVATION,
    /** 未支付 */
    UNPAID,
    /** 已支付 */
    PAID
  }
  /**
   * 结算状态
   * 
   * @author huyong
   *
   */
  public enum ClearingStatus {
    /**
     * 待开发票
     */
    WAITING_FOR_INVOICE,
    /**
     * 发票已上传
     */
    INVOICE_ALREADY_PROVIDED,
    /**
     * 已付款
     */
    PAID,
    /**
     * 结算完成
     */
    CLEARING_COMPLETE

  }

  /**
   * 油品类型
   * 
   * @author tanbiao
   *
   */
  public enum OilType {
    /**
     * 0号油
     */
    P0,
    /**
     * 90号油
     */
    P90,
    /**
     * 93号油
     */
    P93,
    /**
     * 97号油
     */
    P97
  }
  
  /**
   * 文件类型
   */
  public enum FileType {

      /** 图片 */
      image,
      /** 文件 */
      file
  }

}
