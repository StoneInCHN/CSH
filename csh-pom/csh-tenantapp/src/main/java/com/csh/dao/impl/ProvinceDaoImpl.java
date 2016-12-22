package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.Province;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ProvinceDao;
@Repository("provinceDaoImpl")
public class ProvinceDaoImpl extends  BaseDaoImpl<Province,Long> implements ProvinceDao {

}