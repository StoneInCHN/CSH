package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Advertisement;
import com.csh.dao.AdvertisementDao;
import com.csh.service.AdvertisementService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("advertisementServiceImpl")
public class AdvertisementServiceImpl extends BaseServiceImpl<Advertisement,Long> implements AdvertisementService {

      @Resource(name="advertisementDaoImpl")
      public void setBaseDao(AdvertisementDao advertisementDao) {
         super.setBaseDao(advertisementDao);
  }
}