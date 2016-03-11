package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.IdentityCard;
import com.csh.dao.IdentityCardDao;
import com.csh.service.IdentityCardService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("identityCardServiceImpl")
public class IdentityCardServiceImpl extends BaseServiceImpl<IdentityCard,Long> implements IdentityCardService {

      @Resource(name="identityCardDaoImpl")
      public void setBaseDao(IdentityCardDao identityCardDao) {
         super.setBaseDao(identityCardDao);
  }
}