package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.SnDao;
import com.csh.entity.Sn;
import com.csh.entity.Sn.Type;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.SnService;

@Service("snServiceImpl")
public class SnServiceImpl extends BaseServiceImpl<Sn, Long> implements SnService {

  @Resource(name = "snDaoImpl")
  private SnDao snDao;

  @Resource(name = "snDaoImpl")
  public void setBaseDao(SnDao snDao) {
    super.setBaseDao(snDao);
  }

  @Transactional
  public String generate(Type type) {
    return snDao.generate(type);
  }

}
