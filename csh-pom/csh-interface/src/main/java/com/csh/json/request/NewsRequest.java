package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class NewsRequest extends BaseRequest {

  /**
   * 新闻标题
   */
  private String title;

  /**
   * 新闻URL
   */
  private String contentUrl;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContentUrl() {
    return contentUrl;
  }

  public void setContentUrl(String contentUrl) {
    this.contentUrl = contentUrl;
  }

}
