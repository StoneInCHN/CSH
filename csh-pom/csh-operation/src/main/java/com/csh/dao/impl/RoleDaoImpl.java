package com.csh.dao.impl;

import javax.persistence.LockModeType;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.RoleDao;
import com.csh.entity.Role;
import com.csh.framework.dao.impl.BaseDaoImpl;

/**
 * Dao - 角色
 * 
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {

  @Override
  @Transactional
  @Cacheable(value ="role",key="'role.id='+#id")
  public Role find(Long id) {
    return super.find(id);
  }

  @Override
  @Transactional
  @Cacheable(value ="role",key="'role.id='+#id+',lockModeType='+#lockModeType")
  public Role find(Long id, LockModeType lockModeType) {
    return super.find(id, lockModeType);
  }

  @Override
  @Transactional
  @CachePut(value ="role",key="'role.id='+#entity.id")
  public Role merge(Role entity) {
    return super.merge(entity);
  }

  @Override
  @Transactional
  @CacheEvict(value ="role",key="'role.id='+#entity.id")
  public void remove(Role entity) {
    super.remove(entity);
  }
  
}