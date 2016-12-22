package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Area;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.AreaDao;
@Repository("areaDaoImpl")
public class AreaDaoImpl extends  BaseDaoImpl<Area,Long> implements AreaDao {

}