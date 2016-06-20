package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.Review;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ReviewDao;
@Repository("reviewDaoImpl")
public class ReviewDaoImpl extends  BaseDaoImpl<Review,Long> implements ReviewDao {

}