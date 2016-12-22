package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.NewsCategory;
import com.csh.dao.NewsCategoryDao;
import com.csh.service.NewsCategoryService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("newsCategoryServiceImpl")
public class NewsCategoryServiceImpl extends BaseServiceImpl<NewsCategory,Long> implements NewsCategoryService {

      @Resource(name="newsCategoryDaoImpl")
      public void setBaseDao(NewsCategoryDao newsCategoryDao) {
         super.setBaseDao(newsCategoryDao);
  }
}