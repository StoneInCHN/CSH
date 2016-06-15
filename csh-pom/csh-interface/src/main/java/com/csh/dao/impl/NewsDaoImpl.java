package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.News;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.NewsDao;
@Repository("newsDaoImpl")
public class NewsDaoImpl extends  BaseDaoImpl<News,Long> implements NewsDao {

}