package com.csh.entity.estore;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import com.csh.entity.EndUser;
import com.csh.entity.base.BaseEntity;

/**
 * Entity - 购物车
 * 
 */
@Entity
@Table(name = "csh_cart", indexes = {@Index(name = "car_tenantid", columnList = "tenantID")})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "csh_cart_sequence")
public class Cart extends BaseEntity {

  private static final long serialVersionUID = -6565967051825794561L;

  /** 超时时间 */
  public static final int TIMEOUT = 604800;

  /** 最大商品数 */
  public static final Integer MAX_PRODUCT_COUNT = 100;


  /** 会员 */
  private EndUser endUser;

  /** 购物车项 */
  private Set<CartItem> cartItems = new HashSet<CartItem>();

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 获取会员
   * 
   * @return 会员
   */
  @OneToOne(fetch = FetchType.LAZY)
  public EndUser getEndUser() {
    return endUser;
  }

  public void setEndUser(EndUser endUser) {
    this.endUser = endUser;
  }

  /**
   * 获取购物车项
   * 
   * @return 购物车项
   */
  @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  public Set<CartItem> getCartItems() {
    return cartItems;
  }

  /**
   * 设置购物车项
   * 
   * @param cartItems 购物车项
   */
  public void setCartItems(Set<CartItem> cartItems) {
    this.cartItems = cartItems;
  }

  /**
   * 获取商品重量
   * 
   * @return 商品重量
   */
  @Transient
  public int getWeight() {
    int weight = 0;
    if (getCartItems() != null) {
      for (CartItem cartItem : getCartItems()) {
        if (cartItem != null) {
          weight += cartItem.getWeight();
        }
      }
    }
    return weight;
  }

  /**
   * 获取商品数量
   * 
   * @return 商品数量
   */
  @Transient
  public int getQuantity() {
    int quantity = 0;
    if (getCartItems() != null) {
      for (CartItem cartItem : getCartItems()) {
        if (cartItem != null && cartItem.getQuantity() != null) {
          quantity += cartItem.getQuantity();
        }
      }
    }
    return quantity;
  }

  /**
   * 获取赠送积分
   * 
   * @return 赠送积分
   */
  @Transient
  public long getPoint() {
    long point = 0L;
    if (getCartItems() != null) {
      for (CartItem cartItem : getCartItems()) {
        if (cartItem != null) {
          point += cartItem.getPoint();
        }
      }
    }
    return point;
  }

  /**
   * 获取赠送积分增加值
   * 
   * @return 赠送积分增加值
   */
  // @Transient
  // public long getAddedPoint() {
  // long originalPoint = 0L;
  // long currentPoint = 0L;
  // for (Promotion promotion : getPromotions()) {
  // if (promotion != null) {
  // int promotionQuantity = getQuantity(promotion);
  // long originalPromotionPoint = getTempPoint(promotion);
  // long currentPromotionPoint = promotion.calculatePoint(promotionQuantity,
  // originalPromotionPoint);
  // originalPoint += originalPromotionPoint;
  // currentPoint += currentPromotionPoint;
  // Set<CartItem> cartItems = getCartItems(promotion);
  // for (CartItem cartItem : cartItems) {
  // if (cartItem != null && cartItem.getTempPoint() != null) {
  // long tempPoint;
  // if (originalPromotionPoint > 0) {
  // tempPoint = (long) (currentPromotionPoint / (double) originalPromotionPoint *
  // cartItem.getTempPoint());
  // } else {
  // tempPoint = (currentPromotionPoint - originalPromotionPoint) / promotionQuantity;
  // }
  // cartItem.setTempPoint(tempPoint > 0 ? tempPoint : 0L);
  // }
  // }
  // }
  // }
  // if (getCartItems() != null) {
  // for (CartItem cartItem : getCartItems()) {
  // if (cartItem != null) {
  // cartItem.setTempPoint(null);
  // }
  // }
  // }
  // long addedPoint = currentPoint - originalPoint;
  // return addedPoint > 0 ? addedPoint : 0L;
  // }

  // /**
  // * 获取有效赠送积分
  // *
  // * @return 有效赠送积分
  // */
  // @Transient
  // public long getEffectivePoint() {
  // long effectivePoint = getPoint() + getAddedPoint();
  // return effectivePoint > 0L ? effectivePoint : 0L;
  // }

  /**
   * 获取商品价格
   * 
   * @return 商品价格
   */
  @Transient
  public BigDecimal getPrice() {
    BigDecimal price = new BigDecimal(0);
    if (getCartItems() != null) {
      for (CartItem cartItem : getCartItems()) {
        if (cartItem != null && cartItem.getSubtotal() != null) {
          price = price.add(cartItem.getSubtotal());
        }
      }
    }
    return price;
  }

  /**
   * 获取折扣
   * 
   * @return 折扣
   */
  // @Transient
  // public BigDecimal getDiscount() {
  // BigDecimal originalPrice = new BigDecimal(0);
  // BigDecimal currentPrice = new BigDecimal(0);
  // for (Promotion promotion : getPromotions()) {
  // if (promotion != null) {
  // int promotionQuantity = getQuantity(promotion);
  // BigDecimal originalPromotionPrice = getTempPrice(promotion);
  // BigDecimal currentPromotionPrice = promotion.calculatePrice(promotionQuantity,
  // originalPromotionPrice);
  // originalPrice = originalPrice.add(originalPromotionPrice);
  // currentPrice = currentPrice.add(currentPromotionPrice);
  // Set<CartItem> cartItems = getCartItems(promotion);
  // for (CartItem cartItem : cartItems) {
  // if (cartItem != null && cartItem.getTempPrice() != null) {
  // BigDecimal tempPrice;
  // if (originalPromotionPrice.compareTo(new BigDecimal(0)) > 0) {
  // tempPrice = currentPromotionPrice.divide(originalPromotionPrice, 50,
  // RoundingMode.DOWN).multiply(cartItem.getTempPrice());
  // } else {
  // tempPrice = new BigDecimal(0);
  // }
  // cartItem.setTempPrice(tempPrice.compareTo(new BigDecimal(0)) > 0 ? tempPrice : new
  // BigDecimal(0));
  // }
  // }
  // }
  // }
  // if (getCartItems() != null) {
  // for (CartItem cartItem : getCartItems()) {
  // if (cartItem != null) {
  // cartItem.setTempPrice(null);
  // }
  // }
  // }
  // Setting setting = SettingUtils.get();
  // BigDecimal discount = setting.setScale(originalPrice.subtract(currentPrice));
  // return discount.compareTo(new BigDecimal(0)) > 0 ? discount : new BigDecimal(0);
  // }

  /**
   * 获取有效商品价格
   * 
   * @return 有效商品价格
   */
  // @Transient
  // public BigDecimal getEffectivePrice() {
  // BigDecimal effectivePrice = getPrice().subtract(getDiscount());
  // return effectivePrice.compareTo(new BigDecimal(0)) > 0 ? effectivePrice : new BigDecimal(0);
  // }

  // /**
  // * 获取促销购物车项
  // *
  // * @param promotion
  // * 促销
  // * @return 促销购物车项
  // */
  // @Transient
  // private Set<CartItem> getCartItems(Promotion promotion) {
  // Set<CartItem> cartItems = new HashSet<CartItem>();
  // if (promotion != null && getCartItems() != null) {
  // for (CartItem cartItem : getCartItems()) {
  // if (cartItem != null && cartItem.getProduct() != null/* &&
  // cartItem.getProduct().isValid(promotion)*/) {
  // cartItems.add(cartItem);
  // }
  // }
  // }
  // return cartItems;
  // }
  //
  // /**
  // * 获取促销商品数量
  // *
  // * @param promotion
  // * 促销
  // * @return 促销商品数量
  // */
  // @Transient
  // private int getQuantity(Promotion promotion) {
  // int quantity = 0;
  // for (CartItem cartItem : getCartItems(promotion)) {
  // if (cartItem != null && cartItem.getQuantity() != null) {
  // quantity += cartItem.getQuantity();
  // }
  // }
  // return quantity;
  // }
  //
  // /**
  // * 获取促销商品赠送积分
  // *
  // * @param promotion
  // * 促销
  // * @return 促销商品赠送积分
  // */
  // @Transient
  // private long getPoint(Promotion promotion) {
  // long point = 0L;
  // for (CartItem cartItem : getCartItems(promotion)) {
  // if (cartItem != null) {
  // point += cartItem.getPoint();
  // }
  // }
  // return point;
  // }
  //
  // /**
  // * 获取促销商品临时赠送积分
  // *
  // * @param promotion
  // * 促销
  // * @return 促销商品临时赠送积分
  // */
  // @Transient
  // private long getTempPoint(Promotion promotion) {
  // long tempPoint = 0L;
  // for (CartItem cartItem : getCartItems(promotion)) {
  // if (cartItem != null && cartItem.getTempPoint() != null) {
  // tempPoint += cartItem.getTempPoint();
  // }
  // }
  // return tempPoint;
  // }
  //
  // /**
  // * 获取促销商品价格
  // *
  // * @param promotion
  // * 促销
  // * @return 促销商品价格
  // */
  // @Transient
  // private BigDecimal getPrice(Promotion promotion) {
  // BigDecimal price = new BigDecimal(0);
  // for (CartItem cartItem : getCartItems(promotion)) {
  // if (cartItem != null && cartItem.getSubtotal() != null) {
  // price = price.add(cartItem.getSubtotal());
  // }
  // }
  // return price;
  // }
  //
  // /**
  // * 获取促销商品临时价格
  // *
  // * @param promotion
  // * 促销
  // * @return 促销商品临时价格
  // */
  // @Transient
  // private BigDecimal getTempPrice(Promotion promotion) {
  // BigDecimal tempPrice = new BigDecimal(0);
  // for (CartItem cartItem : getCartItems(promotion)) {
  // if (cartItem != null && cartItem.getTempPrice() != null) {
  // tempPrice = tempPrice.add(cartItem.getTempPrice());
  // }
  // }
  // return tempPrice;
  // }


  /**
   * 获取购物车项
   * 
   * @param product 商品
   * @return 购物车项
   */
  @Transient
  public CartItem getCartItem(Product product) {
    if (product != null && getCartItems() != null) {
      for (CartItem cartItem : getCartItems()) {
        if (cartItem != null && cartItem.getProduct() != null
            && cartItem.getProduct().equals(product)) {
          return cartItem;
        }
      }
    }
    return null;
  }

  /**
   * 判断是否包含商品
   * 
   * @param product 商品
   * @return 是否包含商品
   */
  @Transient
  public boolean contains(Product product) {
    if (product != null && getCartItems() != null) {
      for (CartItem cartItem : getCartItems()) {
        if (cartItem != null && cartItem.getProduct() != null
            && cartItem.getProduct().equals(product)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 获取令牌
   * 
   * @return 令牌
   */
  // @Transient
  // public String getToken() {
  // HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(17, 37).append(getKey());
  // if (getCartItems() != null) {
  // for (CartItem cartItem : getCartItems()) {
  // hashCodeBuilder.append(cartItem.getProduct()).append(cartItem.getQuantity()).append(cartItem.getPrice());
  // }
  // }
  // return DigestUtils.md5Hex(hashCodeBuilder.toString());
  // }

  /**
   * 获取是否库存不足
   * 
   * @return 是否库存不足
   */
  @Transient
  public boolean getIsLowStock() {
    if (getCartItems() != null) {
      for (CartItem cartItem : getCartItems()) {
        if (cartItem != null && cartItem.getIsLowStock()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 判断是否已过期
   * 
   * @return 是否已过期
   */
  @Transient
  public boolean hasExpired() {
    return new Date().after(DateUtils.addSeconds(getModifyDate(), TIMEOUT));
  }

  /**
   * 判断是否允许使用优惠券
   * 
   * @return 是否允许使用优惠券
   */
  // @Transient
  // public boolean isCouponAllowed() {
  // for (Promotion promotion : getPromotions()) {
  // if (promotion != null && !promotion.getIsCouponAllowed()) {
  // return false;
  // }
  // }
  // return true;
  // }

  /**
   * 判断是否为空
   * 
   * @return 是否为空
   */
  @Transient
  public boolean isEmpty() {
    return getCartItems() == null || getCartItems().isEmpty();
  }

  @Field(index = org.hibernate.search.annotations.Index.YES, store = Store.NO, analyze = Analyze.NO)
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

}
