package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.FeedBack;
import com.csh.dao.FeedBackDao;
import com.csh.service.FeedBackService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("feedBackServiceImpl")
public class FeedBackServiceImpl extends BaseServiceImpl<FeedBack,Long> implements FeedBackService {

      @Resource(name="feedBackDaoImpl")
      public void setBaseDao(FeedBackDao feedBackDao) {
         super.setBaseDao(feedBackDao);
  }
}