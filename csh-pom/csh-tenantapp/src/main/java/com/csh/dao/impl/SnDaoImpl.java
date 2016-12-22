package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Sn;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.SnDao;
@Repository("snDaoImpl")
public class SnDaoImpl extends  BaseDaoImpl<Sn,Long> implements SnDao {

}