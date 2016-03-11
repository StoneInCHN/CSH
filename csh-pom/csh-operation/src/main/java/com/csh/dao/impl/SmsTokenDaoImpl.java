package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.SmsToken;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.SmsTokenDao;
@Repository("smsTokenDaoImpl")
public class SmsTokenDaoImpl extends  BaseDaoImpl<SmsToken,Long> implements SmsTokenDao {

}