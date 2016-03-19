package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.AppDao;
import com.csh.entity.App;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * app
 * 
 * @author huyong
 *
 */
@Repository("appDaoImpl")
public class AppDaoImpl extends BaseDaoImpl<App, Long> implements AppDao {
  
}
