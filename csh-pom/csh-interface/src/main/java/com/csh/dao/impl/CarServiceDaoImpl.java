package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.CarService;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.CarServiceDao;
@Repository("carServiceDaoImpl")
public class CarServiceDaoImpl extends  BaseDaoImpl<CarService,Long> implements CarServiceDao {

}