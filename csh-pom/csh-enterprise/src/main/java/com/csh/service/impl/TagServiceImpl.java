package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.Tag;
import com.csh.dao.TagDao;
import com.csh.service.TagService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("tagServiceImpl")
public class TagServiceImpl extends BaseServiceImpl<Tag,Long> implements TagService {

      @Resource(name="tagDaoImpl")
      public void setBaseDao(TagDao tagDao) {
         super.setBaseDao(tagDao);
  }
}