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
     * 已售出
     */
    SALEOUT,

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

    private IllegalArea(String provienceShortName) {
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
    REFUEL,
    /**
     * 商品
     */
    PRODUCT;
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
    IDPHOTO,
    /**
     * 广告图片
     */
    ADVIMAGE,
    /**
     * 优惠券说明图片
     */
    COUPONREMARKIMAGE,
    /**
     * 服务描述图片
     */
    SERVICEDESCIMAGE,
    /**
     * 商品品牌图片
     */
    PRODUCTBRANDIMAGE,
    /**
     * 商品展示图片
     */
    PRODUCTIMAGE,
    /**
     * 租户图片
     */
    TENANTIMAGE,
    /**
     * 配送方式图标
     */
    SHIPPINGMETHODICON,
    /**
     * 商品展示图片列表
     */
    PRODUCTIMAGELIST
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

  /**
   * 租户app消息列表类型
   * 
   * @author sujinxuan
   *
   */
  public enum TenantMsgType {
    /** 非法启动 */
    ILLEGALSTARTWARN,
    /** 非法震动 */
    ILLEGALSHOCKWARN,
    /** obd故障报警 */
    OBDWARN,
    /** 水温报警 */
    WATERTEMPWARN,
    /** 超速报警 */
    OVERSPEEDWARN,
    /** 碰撞报警 */
    CRASHWARN,
    /** 侧翻报警 */
    ROLLOVERWARN,
    /** 电瓶拆除报警 */
    BATTERYREMOVEWARN,
    /** 低电压报警 */
    LOWVOLTAGEWARN,
    /** 故障码报警 */
    FAULTCODEWARN

  }

  public enum SendType {
    /** 推送消息 */
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
    WALLET,
    /** 优惠券与阿里，微信，钱包余额支付混合 */
    COUPON,
    /** 洗车券 */
    WASHCOUPON,
    /** 线下钱包混合 */
    OFFLINEBALLANCE,
    /** 线下钱包优惠券混合 */
    MIXCOUPONOFFLINE,
    /** 直接支付（阿里，微信，钱包余额支付）和红包 */
    DIRECTREDPACKAGE,
    /** 优惠券，红包混合 */
    COUPONREDPACKAGE,
    /** 线下,钱包,红包混合 */
    OFFLINEBALLANCEREDPACKAGE,
    /** 线下，钱包，优惠券，红包混合 */
    MIXCOUPONOFFLINEREDPACKAGE
  }

  public enum ChargeStatus {
    /*** 预约中 */
    RESERVATION("预约中"),
    /*** 预约成功 */
    RESERVATION_SUCCESS("预约成功"),
    /*** 预约失败 */
    RESERVATION_FAIL("预约失败"),
    /** 服务完成未支付 */
    UNPAID("未支付"),
    /** 已支付 */
    PAID("已支付"),
    /** 完成 */
    FINISH("完成"),
    /** 过期 */
    OVERDUE("过期"),
    /*** 服务中 */
    IN_SERVICE("服务中");

    private String chargeStatusName;

    private ChargeStatus(String chargeStatusName) {
      this.chargeStatusName = chargeStatusName;
    }

    public String getChargeStatusName() {
      return chargeStatusName;
    }

  }
  /**
   * 结算状态
   * 
   * @author huyong
   *
   */
  public enum ClearingStatus {

    /**
     * 未付款
     */
    UNPAID,
    /**
     * 已付款
     */
    PAID

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

  public enum AreaRegions {
    /**
     * 省
     */
    province,
    /**
     * 市
     */
    municipality,
    /**
     * 区或县
     */
    prefecture
  }

  /**
   * 优惠劵类型
   * 
   * @author sujinxuan
   *
   */
  public enum CouponType {
    /** 全场通用优惠劵 */
    COMMON,
    /** 指定优惠劵 */
    SPECIFY,
    /** 服务通用优惠劵 */
    SERVICES,
    /** 商品通用优惠劵 */
    GOODS
  }

  /**
   * 优惠劵发送方式
   * 
   * @author sujinxuan
   *
   */
  public enum CouponSendType {
    /** 平台注册优惠劵 (用户注册平台即发送) */
    REG,
    /** 租户绑定优惠劵 (用户添加车辆即发送) */
    TENANTBIND,
    /** 设备绑定优惠劵 (用户绑定设备即发送) */
    DEVICEBIND,
    /** 普通优惠劵 (主动发送给用户，需要点击领取) */
    NORMAL

  }
  /**
   * 优惠劵过期方式
   * 
   * @author sujinxuan
   *
   */
  public enum CouponOverDueType {
    /** 按天数计算过期时间 */
    BYDAY,
    /** 指定过期时间 */
    BYDATE
  }

  /**
   * app platform
   * 
   * @author Administrator
   *
   */
  public enum AppPlatform {
    /** android */
    ANDROID,
    /** IOS */
    IOS
  }

  /**
   * 预存转款用途
   * 
   * @author Andrea
   *
   */
  public enum AdvanceUsageType {
    /** 购买设备 */
    DEVICE
  }

  /**
   * systemconfig 配置项
   * 
   * @author Andrea
   *
   */
  public enum SystemConfigKey {
    /** 设备定价 */
    DEVICE_PRICE,
    /**
     * 红包基金--注册
     */
    GROUTHFUND_REG,
    /**
     * 红包基金--添加车辆
     */
    GROUTHFUND_ADDCAR,
    /**
     * 红包基金--绑定车辆
     */
    GROUTHFUND_BIND,
    /**
     * 红包基金--扫二维码关注
     */
    GROUTHFUND_ATTENTION,
    /**
     * 红包基金--绑定后，每行驶一公里红包
     */
    GROUTHFUND_DRIVING,
    /**
     * 红包基金--保险抵扣
     */
    GROUTHFUND_INSURANCE
  }

  public enum AdType {
    /**
     * 普通广告
     */
    NORMAL_AD,
    /**
     * App首页启功广告
     */
    STARTING_AD
  }
  /**
   * 订单状态
   */
  public enum OrderStatus {

    /** 未确认 */
    unconfirmed,

    /** 已确认 */
    confirmed,

    /** 已完成 */
    completed,

    /** 已取消 */
    cancelled,

    /**
     * 已失效
     */
    failure
  }

  /**
   * 支付状态
   */
  public enum PaymentStatus {

    /** 未支付 */
    unpaid,

    /** 部分支付 */
    partialPayment,

    /** 已支付 */
    paid,

    /** 部分退款 */
    partialRefunds,

    /** 已退款 */
    refunded
  }

  /**
   * 配送状态
   */
  public enum ShippingStatus {

    /** 未发货 */
    unshipped,

    /** 部分发货 */
    partialShipment,

    /** 已发货 */
    shipped,

    /** 已收货 */
    received,

    /** 部分退货 */
    partialReturns,

    /** 已退货 */
    returned
  }
  /**
   * 退款售后状态
   *
   */
  public enum AfterSalesStatus {
    /** 申请退款中... */
    applyRefund,

    /** 申请部分退款中... */
    applyPartialRefund,

    /** 已批准退款 */
    approvedRefund,

    /** 已批准部分退款 */
    approvedPartialRefund,
  }
  /**
   * 退款单状态
   */
  public enum RefundsStatus {
    /** 未退款 */
    noRefund,
    /** 退款成功 */
    refund_success,
    /** 退款失败 */
    refund_failed,
    /** 已取消 */
    cancelled
  }
  /**
   * 退货单状态
   */
  public enum ReturnsStatus {
    /** 申请退货中... */
    applyReturn,
    /** 已批准退货 */
    approvedReturn,
    /** 退货成功 */
    return_success,
    /** 退货失败 */
    return_failed,
    /** 已取消 */
    cancelled
  }
  /**
   * 订单日志类型
   */
  public enum OrderLogType {

    /** 订单创建 */
    create,

    /** 订单修改 */
    modify,

    /** 订单确认 */
    confirm,

    /** 订单支付 */
    payment,

    /** 订单退款 */
    refunds,

    /** 订单发货 */
    shipping,

    /** 订单收货 */
    received,

    /** 订单退货 */
    returns,

    /** 订单完成 */
    complete,

    /** 订单取消 */
    cancel,

    /** 订单过期 */
    overdue,

    /** 其它 */
    other
  };
  /**
   * 方式
   */
  public enum Method {

    /** 在线支付 */
    online,

    /** 线下支付 */
    offline,

    /** 预存款支付 */
    deposit
  }
  /**
   * 商品状态
   */
  public enum ProductStatus {

    /** 新建状态 */
    created,

    /** 提交审核 */
    supply,

    /** 审核通过 */
    approvedPass,

    /** 审核退回 */
    approvedFailed,

    /** 上架 */
    marketed,

    /** 下架 */
    unmarketed

  }



  /**
   * 手机接口查询排序类型
   * 
   * @author sujinxuan
   *
   */
  public enum SortType {
    /**
     * 距离由近及远
     */
    DISTANCEASC,
    /**
     * 价格由低到高
     */
    PRICEASC,
    /**
     * 好评分由高到低
     */
    SCOREDESC,
    /**
     * 销量由高到低
     */
    SALESDESC,
    /**
     * 智能推荐
     */
    RECOMMEND,
    /** 其它 */
    OTHER
  }


  public enum GpsSwitch {
    /**
     * 打开
     */
    TURNON,
    /**
     * 关闭
     */
    TURNOFF
  }

  public enum OnlineStatus {
    /**
     * 全部
     */
    ALL,
    /**
     * 在线
     */
    ONLINE,
    /**
     * 离线
     */
    OFFLINE;
  }
  public enum AccStatus{
		/**
		 * 开
		 */
		ON,
		/**
		 * 关
		 */
		OFF
	}
}
