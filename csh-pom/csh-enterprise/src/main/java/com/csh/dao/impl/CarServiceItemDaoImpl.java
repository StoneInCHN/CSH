package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.CarServiceItem;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CarServiceItemDao;
@Repository("carServiceItemDaoImpl")
public class CarServiceItemDaoImpl extends  BaseDaoImpl<CarServiceItem,Long> implements CarServiceItemDao {

}