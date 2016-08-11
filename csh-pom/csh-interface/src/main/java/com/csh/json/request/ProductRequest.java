package com.csh.json.request;

import com.csh.entity.commonenum.CommonEnum.SortType;
import com.csh.json.base.BaseRequest;

public class ProductRequest extends BaseRequest {

  /**
   * 商品类别ID
   */
  private Long categoryId;

  /**
   * 商品搜索关键字
   */
  private String searchKeyWord;

  /**
   * 排序类型
   */
  private SortType sortType;

  /**
   * 商品ID
   */
  private Long productId;


  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public String getSearchKeyWord() {
    return searchKeyWord;
  }

  public void setSearchKeyWord(String searchKeyWord) {
    this.searchKeyWord = searchKeyWord;
  }

  public SortType getSortType() {
    return sortType;
  }

  public void setSortType(SortType sortType) {
    this.sortType = sortType;
  }



}
