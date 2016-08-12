package com.csh.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.BooleanUtils;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.base.BaseEntity;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.entity.commonenum.CommonEnum.Gender;
import com.csh.entity.estore.Cart;
import com.csh.entity.estore.Order;
import com.csh.entity.estore.Product;
import com.csh.entity.estore.ReceiverAddress;
import com.csh.entity.estore.Review;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 终端用户
 * 
 */
@Entity
@Table(name = "csh_end_user")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_end_user_sequence")
public class EndUser extends BaseEntity {

  private static final long serialVersionUID = 1L;

  /**
   * 账号状态
   */
  private AccountStatus accountStatus;

  /**
   * 真实姓名
   */
  private String realName;

  /**
   * 用户名
   */
  private String userName;

  /**
   * 昵称
   */
  private String nickName;
  /**
   * 年龄
   */
  private Integer age;
  /**
   * 性别
   */
  private Gender gender;
  /**
   * 手机号
   */
  private String mobileNum;
  /**
   * 密码
   */
  private String password;

  /**
   * 用户头像
   */
  private String photo;
  /**
   * 出生日期
   */
  private Date birthDay;
  /**
   * 地址
   */
  private String address;

  /**
   * QQ号
   */
  private String qq;
  /**
   * 签名
   */
  private String signature;

  /**
   * VIP等级
   */
  private Integer vipLevel;

  /**
   * 驾照
   */
  private DriverLicense driverLicense;

  /**
   * 车辆
   */
  private Set<Vehicle> vehicles = new HashSet<Vehicle>();

  /**
   * 默认车辆
   */
  private Vehicle defaultVehicle;

  /**
   * login统计
   */
  private Set<LoginStatistics> loginStatistics = new HashSet<LoginStatistics>();

  /**
   * 消息
   */
  private Set<MsgEndUser> msgEndUsers = new HashSet<MsgEndUser>();

  /**
   * 我的钱包
   */
  private Wallet wallet;
  /** 购物车 */
  private Cart cart;
  /**
   * 我的预存专款消费记录
   */
  private Set<AdvanceDeposits> advanceDeposits = new HashSet<AdvanceDeposits>();

  /**
   * 汽车服务购买记录
   */
  private Set<CarServiceRecord> carServiceRecords = new HashSet<CarServiceRecord>();

  /**
   * 用户对商户的评价
   */
  private Set<TenantEvaluate> tenantEvalutes = new HashSet<TenantEvaluate>();

  /**
   * 极光push注册ID
   */
  private String jpushRegId;


  /**
   * 优惠劵
   */
  private Set<CouponEndUser> couponEndUsers = new HashSet<CouponEndUser>();

  /** 订单 */
  private Set<Order> orders = new HashSet<Order>();


  /** 收货地址 */
  private Set<ReceiverAddress> receivers = new HashSet<ReceiverAddress>();

  /** 评论 */
  private Set<Review> reviews = new HashSet<Review>();

  /** 收藏商品 */
  private Set<Product> favoriteProducts = new HashSet<Product>();
  /**
   * 是否获取到红包
   */
  private Boolean isGetCoupon;

  /**
   * 余额
   */
  private BigDecimal accountBalance;

  /**
   * 平台充值余额
   */
  private BigDecimal selfBalance;
  
  @Transient
  public Boolean getIsGetCoupon() {
    return isGetCoupon;
  }

  public void setIsGetCoupon(Boolean isGetCoupon) {
    this.isGetCoupon = isGetCoupon;
  }
  
  @Transient
  public BigDecimal getSelfBalance() {
    return selfBalance;
  }

  public void setSelfBalance(BigDecimal selfBalance) {
    this.selfBalance = selfBalance;
  }

  @OneToMany(mappedBy = "endUser")
  public Set<CouponEndUser> getCouponEndUsers() {
    return couponEndUsers;
  }

  public void setCouponEndUsers(Set<CouponEndUser> couponEndUsers) {
    this.couponEndUsers = couponEndUsers;
  }

  @Column(length = 100)
  public String getjpushRegId() {
    return jpushRegId;
  }

  public void setjpushRegId(String jPushRegId) {
    this.jpushRegId = jPushRegId;
  }

  @OneToMany(mappedBy = "endUser")
  public Set<TenantEvaluate> getTenantEvalutes() {
    return tenantEvalutes;
  }

  public void setTenantEvalutes(Set<TenantEvaluate> tenantEvalutes) {
    this.tenantEvalutes = tenantEvalutes;
  }

  @OneToMany(mappedBy = "endUser")
  public Set<CarServiceRecord> getCarServiceRecords() {
    return carServiceRecords;
  }

  public void setCarServiceRecords(Set<CarServiceRecord> carServiceRecords) {
    this.carServiceRecords = carServiceRecords;
  }

  @OneToMany(mappedBy = "endUser", cascade = CascadeType.ALL)
  public Set<AdvanceDeposits> getAdvanceDeposits() {
    return advanceDeposits;
  }

  public void setAdvanceDeposits(Set<AdvanceDeposits> advanceDeposits) {
    this.advanceDeposits = advanceDeposits;
  }


  @OneToOne(mappedBy = "endUser", cascade = CascadeType.ALL)
  public Wallet getWallet() {
    return wallet;
  }

  public void setWallet(Wallet wallet) {
    this.wallet = wallet;
  }

  @OneToMany(mappedBy = "endUser", cascade = CascadeType.ALL)
  public Set<MsgEndUser> getMsgEndUsers() {
    return msgEndUsers;
  }

  public void setMsgEndUsers(Set<MsgEndUser> msgEndUsers) {
    this.msgEndUsers = msgEndUsers;
  }


  @Transient
  public Vehicle getDefaultVehicle() {
    for (Vehicle v : vehicles) {
      if (BooleanUtils.isTrue(v.getIsDefault())) {
        defaultVehicle = v;
        break;
      }
    }
    return defaultVehicle;
  }

  public void setDefaultVehicle(Vehicle defaultVehicle) {
    this.defaultVehicle = defaultVehicle;
  }


  @OneToMany(mappedBy = "endUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  public Set<LoginStatistics> getLoginStatistics() {
    return loginStatistics;
  }

  public void setLoginStatistics(Set<LoginStatistics> loginStatistics) {
    this.loginStatistics = loginStatistics;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  @JsonProperty
  public AccountStatus getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(AccountStatus accountStatus) {
    this.accountStatus = accountStatus;
  }

  @JsonProperty
  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, analyzer = @Analyzer(
      impl = IKAnalyzer.class))
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @JsonProperty
  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.YES, analyzer = @Analyzer(
      impl = IKAnalyzer.class))
  public String getMobileNum() {
    return mobileNum;
  }

  public void setMobileNum(String mobileNum) {
    this.mobileNum = mobileNum;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public Date getBirthDay() {
    return birthDay;
  }

  public void setBirthDay(Date birthDay) {
    this.birthDay = birthDay;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @JsonProperty
  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public Integer getVipLevel() {
    return vipLevel;
  }

  public void setVipLevel(Integer vipLevel) {
    this.vipLevel = vipLevel;
  }

  @OneToOne(mappedBy = "endUser")
  public DriverLicense getDriverLicense() {
    return driverLicense;
  }

  public void setDriverLicense(DriverLicense driverLicense) {
    this.driverLicense = driverLicense;
  }

  @OneToMany(mappedBy = "endUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  public Set<Vehicle> getVehicles() {
    return vehicles;
  }

  public void setVehicles(Set<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }

  @Transient
  @JsonProperty
  public BigDecimal getAccountBalance() {
    return accountBalance;
  }

  public void setAccountBalance(BigDecimal accountBalance) {
    this.accountBalance = accountBalance;
  }

  /**
   * 获取购物车
   * 
   * @return 购物车
   */
  @OneToOne(mappedBy = "endUser", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  public Cart getCart() {
    return cart;
  }

  /**
   * 设置购物车
   * 
   * @param cart 购物车
   */
  public void setCart(Cart cart) {
    this.cart = cart;
  }

  /**
   * 获取订单
   * 
   * @return 订单
   */
  @OneToMany(mappedBy = "endUser", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  public Set<Order> getOrders() {
    return orders;
  }

  /**
   * 设置订单
   * 
   * @param orders 订单
   */
  public void setOrders(Set<Order> orders) {
    this.orders = orders;
  }



  /**
   * 获取收货地址
   * 
   * @return 收货地址
   */
  @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  @OrderBy("isDefault desc, createDate desc")
  public Set<ReceiverAddress> getReceivers() {
    return receivers;
  }

  public void setReceivers(Set<ReceiverAddress> receivers) {
    this.receivers = receivers;
  }

  /**
   * 获取评论
   * 
   * @return 评论
   */
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @OrderBy("createDate desc")
  public Set<Review> getReviews() {
    return reviews;
  }

  /**
   * 设置评论
   * 
   * @param reviews 评论
   */
  public void setReviews(Set<Review> reviews) {
    this.reviews = reviews;
  }

  /**
   * 获取收藏商品
   * 
   * @return 收藏商品
   */
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "csh_member_favorite_product")
  @OrderBy("createDate desc")
  public Set<Product> getFavoriteProducts() {
    return favoriteProducts;
  }

  /**
   * 设置收藏商品
   * 
   * @param favoriteProducts 收藏商品
   */
  public void setFavoriteProducts(Set<Product> favoriteProducts) {
    this.favoriteProducts = favoriteProducts;
  }
}
