package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.DeliveryCorp;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.DeliveryCorpDao;
@Repository("deliveryCorpDaoImpl")
public class DeliveryCorpDaoImpl extends  BaseDaoImpl<DeliveryCorp,Long> implements DeliveryCorpDao {

}