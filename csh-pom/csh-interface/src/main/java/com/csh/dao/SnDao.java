package com.csh.dao;

import com.csh.entity.Sn;
import com.csh.entity.Sn.Type;
import com.csh.framework.dao.BaseDao;

public interface SnDao extends BaseDao<Sn, Long> {

  /**
   * 生成序列号
   * 
   * @param type 类型
   * @return 序列号
   */
  String generate(Type type);
}
