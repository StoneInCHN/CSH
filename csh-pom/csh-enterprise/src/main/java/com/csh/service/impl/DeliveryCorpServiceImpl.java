package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.DeliveryCorp;
import com.csh.dao.DeliveryCorpDao;
import com.csh.service.DeliveryCorpService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("deliveryCorpServiceImpl")
public class DeliveryCorpServiceImpl extends BaseServiceImpl<DeliveryCorp,Long> implements DeliveryCorpService {

      @Resource(name="deliveryCorpDaoImpl")
      public void setBaseDao(DeliveryCorpDao deliveryCorpDao) {
         super.setBaseDao(deliveryCorpDao);
  }
}