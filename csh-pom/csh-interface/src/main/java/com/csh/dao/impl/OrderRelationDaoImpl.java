package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.OrderRelation;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.OrderRelationDao;
@Repository("orderRelationDaoImpl")
public class OrderRelationDaoImpl extends  BaseDaoImpl<OrderRelation,Long> implements OrderRelationDao {

}