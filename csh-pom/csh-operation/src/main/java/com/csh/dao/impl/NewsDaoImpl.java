package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.NewsDao;
import com.csh.entity.News;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("newsDaoImpl")
public class NewsDaoImpl extends BaseDaoImpl<News, Long> implements NewsDao {

}
