package com.csh.json.response;

public class DutyTypeResponse {

  /**
   * ID
   */
  private Long id;

  /**
   * 显示的值
   */
  private String text;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
