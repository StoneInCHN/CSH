package com.csh.service.impl; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.SmsTokenDao;
import com.csh.entity.SmsToken;
import com.csh.entity.commonenum.CommonEnum.SmsTokenType;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.SmsTokenService;

@Service("smsTokenServiceImpl")
public class SmsTokenServiceImpl extends BaseServiceImpl<SmsToken,Long> implements SmsTokenService {

	  @Resource(name="smsTokenDaoImpl")
	  private SmsTokenDao smsTokenDao;
	  
      @Resource(name="smsTokenDaoImpl")
      public void setBaseDao(SmsTokenDao smsTokenDao) {
         super.setBaseDao(smsTokenDao);
      }
      
      @Override
      public SmsToken findByUserMobile(String mobile) {
        return smsTokenDao.findByUserMobile(mobile);
      }

      @Override
      public SmsToken findByUserMobile(String mobile, SmsTokenType smsTokenType) {
        return smsTokenDao.findByUserMobile(mobile,smsTokenType);
      }
}