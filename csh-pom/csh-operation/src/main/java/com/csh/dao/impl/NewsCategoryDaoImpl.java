package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.NewsCategoryDao;
import com.csh.entity.NewsCategory;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("newsCategoryDaoImpl")
public class NewsCategoryDaoImpl extends BaseDaoImpl<NewsCategory, Long> implements NewsCategoryDao {

}
