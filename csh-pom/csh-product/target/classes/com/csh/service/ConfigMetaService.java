package com.csh.service;

import java.util.List;

import com.csh.entity.ConfigMeta;
import com.csh.entity.MetaRelation;
import com.csh.framework.service.BaseService;

public interface ConfigMetaService extends BaseService<ConfigMeta, Long> {

  /**
   * 根据关系查询元数据功能包或者功能
   * @param relation 关系：DEPEND,INCLUDE,ASSOCIATION
   * @param configType，功能包，功能
   * @return
   */
  List<ConfigMeta> getPackageConfigMeta();
  /**
   * 根据功能包查询功能
   * @param configMeta
   * @return
   */
  List<ConfigMeta> findRelationFunction(ConfigMeta configMeta);
}
