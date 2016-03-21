package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Advertisement;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.AdvertisementDao;
@Repository("advertisementDaoImpl")
public class AdvertisementDaoImpl extends  BaseDaoImpl<Advertisement,Long> implements AdvertisementDao {

}