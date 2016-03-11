package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.EndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.EndUserDao;
@Repository("endUserDaoImpl")
public class EndUserDaoImpl extends  BaseDaoImpl<EndUser,Long> implements EndUserDao {

}