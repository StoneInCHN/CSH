package com.csh.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.csh.dao.ResolutionConfigDao;
import com.csh.entity.ResolutionConfig;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("resolutionConfigDaoImpl")
public class ResolutionConfigDaoImpl extends BaseDaoImpl<ResolutionConfig, Long> implements
    ResolutionConfigDao {

  @Override
  public ResolutionConfig getResolutionConfig(Integer piWidth, Integer piHeight) {
    if (piWidth == null || piHeight == null) {
      return null;
    }
    try {
      String jpql =
          "select * from csh_resolution_config where abs(pi_height-:piHeight)<300 and abs(pi_width-:piWeight)<200 order by abs(pi_height-:piHeight) asc";
      List<ResolutionConfig> configs =
          (List<ResolutionConfig>) entityManager.createNativeQuery(jpql, ResolutionConfig.class)
              .setFlushMode(FlushModeType.COMMIT).setParameter("piHeight", piHeight)
              .setParameter("piWeight", piWidth).getResultList();
      if (configs != null && configs.size() > 0) {
        return configs.get(0);
      }
      return null;
    } catch (Exception e) {
      return null;
    }
  }
}
