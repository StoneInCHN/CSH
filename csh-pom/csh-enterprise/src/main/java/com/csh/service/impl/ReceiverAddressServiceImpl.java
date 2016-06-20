package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.ReceiverAddress;
import com.csh.dao.ReceiverAddressDao;
import com.csh.service.ReceiverAddressService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("receiverAddressServiceImpl")
public class ReceiverAddressServiceImpl extends BaseServiceImpl<ReceiverAddress,Long> implements ReceiverAddressService {

      @Resource(name="receiverAddressDaoImpl")
      public void setBaseDao(ReceiverAddressDao receiverAddressDao) {
         super.setBaseDao(receiverAddressDao);
  }
}