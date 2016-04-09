package com.csh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.csh.dao.IllegalRecordDao;
import com.csh.entity.IllegalRecord;
import com.csh.framework.dao.impl.BaseDaoImpl;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;

/**
 * 车辆违章
 * 
 * @author shijun
 *
 */
@Repository("illegalRecordDaoImpl")
public class IllegalRecordDaoImpl extends BaseDaoImpl<IllegalRecord, Long> implements
    IllegalRecordDao {

  /**
   * 通过车牌查违章
   * 
   * @param plate
   * @return
   */
  public List<IllegalRecord> getIllegalRecords(String plate) {

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<IllegalRecord> criteriaQuery = criteriaBuilder.createQuery(IllegalRecord.class);
    Root<IllegalRecord> root = criteriaQuery.from(IllegalRecord.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();


    if (plate != null) {
      restrictions =
          criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("plate"), plate));
    }
    criteriaQuery.where(restrictions);

    Ordering ordering = new Ordering();
    ordering.setDirection(Direction.desc);
    ordering.setProperty("createDate");
    List<Ordering> orderings = new ArrayList<Ordering>();
    orderings.add(ordering);

    return super.findList(criteriaQuery, null, null, null, orderings);

  }
}
