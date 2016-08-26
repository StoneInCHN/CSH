package com.csh.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.SnDao;
import com.csh.entity.Sn.Type;
import com.csh.service.SnService;

@Service("snServiceImpl")
public class SnServiceImpl implements SnService {

  @Resource(name = "snDaoImpl")
  private SnDao snDao;

  @Transactional
  public String generate(Type type) {
    return snDao.generate(type);
  }

}
