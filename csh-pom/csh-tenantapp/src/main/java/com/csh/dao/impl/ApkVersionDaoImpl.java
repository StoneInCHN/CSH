package com.csh.dao.impl; 

import org.springframework.stereotype.Repository; 

import com.csh.entity.ApkVersion;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.ApkVersionDao;
@Repository("apkVersionDaoImpl")
public class ApkVersionDaoImpl extends  BaseDaoImpl<ApkVersion,Long> implements ApkVersionDao {

}