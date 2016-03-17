package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Distributor;
import com.csh.dao.DistributorDao;
import com.csh.service.DistributorService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("distributorServiceImpl")
public class DistributorServiceImpl extends BaseServiceImpl<Distributor,Long> implements DistributorService {

      @Resource(name="distributorDaoImpl")
      public void setBaseDao(DistributorDao distributorDao) {
         super.setBaseDao(distributorDao);
  }
}