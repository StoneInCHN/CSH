package com.csh.service;

import com.csh.entity.Sn;
import com.csh.entity.Sn.Type;
import com.csh.framework.service.BaseService;

public interface SnService extends BaseService<Sn, Long> {

  /**
   * 生成序列号
   * 
   * @param type 类型
   * @return 序列号
   */
  String generate(Type type);
}
