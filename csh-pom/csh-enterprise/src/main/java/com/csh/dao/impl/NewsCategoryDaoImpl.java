package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.NewsCategory;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.NewsCategoryDao;
@Repository("newsCategoryDaoImpl")
public class NewsCategoryDaoImpl extends  BaseDaoImpl<NewsCategory,Long> implements NewsCategoryDao {

}