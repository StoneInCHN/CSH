package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.NewsComment;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.NewsCommentDao;
@Repository("newsCommentDaoImpl")
public class NewsCommentDaoImpl extends  BaseDaoImpl<NewsComment,Long> implements NewsCommentDao {

}