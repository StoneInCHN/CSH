package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.NewsComment;
import com.csh.dao.NewsCommentDao;
import com.csh.service.NewsCommentService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("newsCommentServiceImpl")
public class NewsCommentServiceImpl extends BaseServiceImpl<NewsComment,Long> implements NewsCommentService {

      @Resource(name="newsCommentDaoImpl")
      public void setBaseDao(NewsCommentDao newsCommentDao) {
         super.setBaseDao(newsCommentDao);
  }
}