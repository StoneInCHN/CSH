package com.csh.service;

import com.csh.entity.Identifier;
import com.csh.entity.commonenum.CommonEnum.IdentifierType;
import com.csh.framework.service.BaseService;

public interface IdentifierService extends BaseService<Identifier, Long> {
  /**
   * 根据自增类型获取该数据最新的数字，并将数据库中的值加1
   * @param idType
   * @return
   */
  Long getLatestIdentifier(IdentifierType idType);
  
  String getLatestOrgCode();
  
}
