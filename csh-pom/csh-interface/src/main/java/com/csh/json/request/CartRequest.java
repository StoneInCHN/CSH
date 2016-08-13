package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class CartRequest extends BaseRequest {

  /**
   * 购物车项ID
   */
  private Long itemId;

  /**
   * 购物车项目IDs
   */
  private Long[] itemIds;

  /**
   * 商品数量增减
   */
  private Integer opr;

  /**
   * 商品ID
   */
  private Long productId;

  /**
   * 购买数量
   */
  private Integer quantity;


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long[] getItemIds() {
    return itemIds;
  }

  public void setItemIds(Long[] itemIds) {
    this.itemIds = itemIds;
  }

  public Integer getOpr() {
    return opr;
  }

  public void setOpr(Integer opr) {
    this.opr = opr;
  }

  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }



}
