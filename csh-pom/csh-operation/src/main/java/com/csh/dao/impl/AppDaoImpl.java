package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.App;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.AppDao;
@Repository("appDaoImpl")
public class AppDaoImpl extends  BaseDaoImpl<App,Long> implements AppDao {

}