package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.NewsCommentDao;
import com.csh.entity.NewsComment;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.NewsCommentService;

@Service("newsCommentServiceImpl")
public class NewsCommentServiceImpl extends BaseServiceImpl<NewsComment, Long> implements
    NewsCommentService {

  @Resource(name="newsCommentDaoImpl")
  public void setBaseDao(NewsCommentDao newsCommentDao) {
    super.setBaseDao(newsCommentDao);
  }
  
}
