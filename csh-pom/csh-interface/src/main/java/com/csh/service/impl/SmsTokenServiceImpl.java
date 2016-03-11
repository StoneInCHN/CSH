package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.SmsToken;
import com.csh.dao.SmsTokenDao;
import com.csh.service.SmsTokenService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("smsTokenServiceImpl")
public class SmsTokenServiceImpl extends BaseServiceImpl<SmsToken,Long> implements SmsTokenService {

      @Resource(name="smsTokenDaoImpl")
      public void setBaseDao(SmsTokenDao smsTokenDao) {
         super.setBaseDao(smsTokenDao);
  }
}