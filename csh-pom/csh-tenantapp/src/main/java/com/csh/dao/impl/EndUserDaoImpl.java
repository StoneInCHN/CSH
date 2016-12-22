package com.csh.dao.impl;


import org.springframework.stereotype.Repository;

import com.csh.dao.EndUserDao;
import com.csh.entity.EndUser;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("endUserDaoImpl")
public class EndUserDaoImpl extends BaseDaoImpl<EndUser, Long> implements EndUserDao {

}
