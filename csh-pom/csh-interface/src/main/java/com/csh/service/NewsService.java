package com.csh.service;

import com.csh.entity.News;
import com.csh.framework.service.BaseService;

public interface NewsService extends BaseService<News, Long> {

  /**
   * 推送新闻
   * 
   * @param title
   * @param contentUrl
   */
  public void jpushNews(Long newsId, String title, String contentUrl);
}
