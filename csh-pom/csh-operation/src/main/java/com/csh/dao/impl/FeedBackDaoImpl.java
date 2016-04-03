package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.FeedBack;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.FeedBackDao;
@Repository("feedBackDaoImpl")
public class FeedBackDaoImpl extends  BaseDaoImpl<FeedBack,Long> implements FeedBackDao {

}