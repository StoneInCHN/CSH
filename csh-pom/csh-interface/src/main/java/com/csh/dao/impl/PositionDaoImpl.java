package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Position;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.PositionDao;
@Repository("positionDaoImpl")
public class PositionDaoImpl extends  BaseDaoImpl<Position,Long> implements PositionDao {

}