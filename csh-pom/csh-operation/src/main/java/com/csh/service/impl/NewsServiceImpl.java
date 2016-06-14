package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.NewsDao;
import com.csh.entity.News;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.NewsService;

@Service("newsServiceImpl")
public class NewsServiceImpl extends BaseServiceImpl<News, Long> implements NewsService {

  @Resource(name = "baseDaoImpl")
  public void setBaseDao(NewsDao newsDao) {
    super.setBaseDao(newsDao);
  }

}
