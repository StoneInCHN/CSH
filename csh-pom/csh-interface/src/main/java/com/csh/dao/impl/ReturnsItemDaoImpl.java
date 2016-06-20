package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.estore.ReturnsItem;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ReturnsItemDao;
@Repository("returnsItemDaoImpl")
public class ReturnsItemDaoImpl extends  BaseDaoImpl<ReturnsItem,Long> implements ReturnsItemDao {

}