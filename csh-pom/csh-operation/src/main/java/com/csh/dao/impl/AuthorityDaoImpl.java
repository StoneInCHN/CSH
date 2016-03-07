package com.csh.dao.impl;

import org.springframework.stereotype.Repository;

import com.csh.dao.AuthorityDao;
import com.csh.entity.ConfigMeta;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 权限
 * @author pengyanan
 *
 */
@Repository("authorityDaoImpl")
public class AuthorityDaoImpl extends BaseDaoImpl<ConfigMeta, Long> implements AuthorityDao{

}
