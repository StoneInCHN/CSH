package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.OrderEntity;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.OrderEntityDao;
@Repository("orderEntityDaoImpl")
public class OrderEntityDaoImpl extends  BaseDaoImpl<OrderEntity,Long> implements OrderEntityDao {

}