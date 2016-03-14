package com.csh.dao.impl; 

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository; 

import com.csh.entity.Department;
import com.csh.entity.VehicleBrandDetail;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.dao.VehicleBrandDetailDao;
@Repository("vehicleBrandDetailDaoImpl")
public class VehicleBrandDetailDaoImpl extends  BaseDaoImpl<VehicleBrandDetail,Long> implements VehicleBrandDetailDao {

  @Override
  public List<VehicleBrandDetail> findRoots (Integer count)
  {
    String jpql =
        "select v from VehicleBrandDetail v where v.parent is null order by v.id asc";
    TypedQuery<VehicleBrandDetail> query =
        entityManager.createQuery(jpql, VehicleBrandDetail.class).setFlushMode(FlushModeType.COMMIT);
    if (count != null) {
      query.setMaxResults(count);
    }
    return query.getResultList();
  }

}