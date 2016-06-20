package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.Review;
import com.csh.dao.ReviewDao;
import com.csh.service.ReviewService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("reviewServiceImpl")
public class ReviewServiceImpl extends BaseServiceImpl<Review,Long> implements ReviewService {

      @Resource(name="reviewDaoImpl")
      public void setBaseDao(ReviewDao reviewDao) {
         super.setBaseDao(reviewDao);
  }
}