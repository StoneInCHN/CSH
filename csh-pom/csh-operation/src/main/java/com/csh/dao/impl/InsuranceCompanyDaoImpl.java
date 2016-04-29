package com.csh.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.csh.dao.InsuranceCompanyDao;
import com.csh.entity.InsuranceCompany;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("insuranceCompanyDaoImpl")
public class InsuranceCompanyDaoImpl extends BaseDaoImpl<InsuranceCompany, Long> implements
    InsuranceCompanyDao {

  @Override
  public List<InsuranceCompany> findRoots(Integer count) {
    String jpql =
        "select insuranceCompany from InsuranceCompany insuranceCompany where insuranceCompany.parent is null order by insuranceCompany.id asc";
    TypedQuery<InsuranceCompany> query =
        entityManager.createQuery(jpql, InsuranceCompany.class).setFlushMode(
            FlushModeType.COMMIT);
    if (count != null) {
      query.setMaxResults(count);
    }
    return query.getResultList();
  }

  @Override
  public List<InsuranceCompany> findParents(InsuranceCompany insuranceCompany,
      Integer count) {
    if (insuranceCompany == null || insuranceCompany.getParent() == null) {
      return Collections.<InsuranceCompany>emptyList();
    }
    String jpql =
        "select insuranceCompany from InsuranceCompany insuranceCompany where insuranceCompany.id in (:ids) order by insuranceCompany.grade asc";
    TypedQuery<InsuranceCompany> query =
        entityManager.createQuery(jpql, InsuranceCompany.class)
            .setFlushMode(FlushModeType.COMMIT)
            .setParameter("ids", insuranceCompany.getTreePaths());
    if (count != null) {
      query.setMaxResults(count);
    }
    return query.getResultList();
  }

  @Override
  public List<InsuranceCompany> findChildren(InsuranceCompany insuranceCompany,
      Integer count) {
    TypedQuery<InsuranceCompany> query;
    if (insuranceCompany != null) {
      String jpql =
          "select insuranceCompany from InsuranceCompany insuranceCompany where insuranceCompany.treePath like :treePath order by insuranceCompany.id asc";
      query =
          entityManager
              .createQuery(jpql, InsuranceCompany.class)
              .setFlushMode(FlushModeType.COMMIT)
              .setParameter(
                  "treePath",
                  "%" + InsuranceCompany.TREE_PATH_SEPARATOR + insuranceCompany.getId()
                      + InsuranceCompany.TREE_PATH_SEPARATOR + "%");
    } else {
      String jpql =
          "select insuranceCompany from InsuranceCompany insuranceCompany order by insuranceCompany.id asc";
      query =
          entityManager.createQuery(jpql, InsuranceCompany.class).setFlushMode(
              FlushModeType.COMMIT);
    }
    if (count != null) {
      query.setMaxResults(count);
    }
    return sort(query.getResultList(), insuranceCompany);
  }

  /**
   * 设置treePath、grade并保存
   * 
   * @param lawyerServiceCategory 服务
   */
  @Override
  public void persist(InsuranceCompany insuranceCompany) {
    Assert.notNull(insuranceCompany);
    setValue(insuranceCompany);
    super.persist(insuranceCompany);
  }

  /**
   * 设置treePath、grade并更新
   * 
   * @param lawyerServiceCategory 服务
   * @return 服务
   */
  @Override
  public InsuranceCompany merge(InsuranceCompany insuranceCompany) {
    Assert.notNull(insuranceCompany);
    setValue(insuranceCompany);
    for (InsuranceCompany category : findChildren(insuranceCompany, null)) {
      setValue(category);
    }
    return super.merge(insuranceCompany);
  }

  /**
   * 排序服务
   * 
   * @param lawyerServiceCategory 服务
   * @param parent 上级服务
   * @return 服务
   */
  
  private List<InsuranceCompany> sort(List<InsuranceCompany> insuranceCompanys,
      InsuranceCompany parent) {
    List<InsuranceCompany> result = new ArrayList<InsuranceCompany>();
    if (insuranceCompanys != null) {
      for (InsuranceCompany insuranceCompany : insuranceCompanys) {
        if ((insuranceCompany.getParent() != null && insuranceCompany.getParent().equals(
            parent))
            || (insuranceCompany.getParent() == null && parent == null)) {
          result.add(insuranceCompany);
          result.addAll(sort(insuranceCompanys, insuranceCompany));
        }
      }
    }
    return result;
  }

  /**
   * 设置值
   * 
   * @param lawyerServiceCategory 服务
   */
  private void setValue(InsuranceCompany insuranceCompany) {
    if (insuranceCompany == null) {
      return;
    }
    InsuranceCompany parent = insuranceCompany.getParent();
    if (parent != null) {
      insuranceCompany.setTreePath(parent.getTreePath() + parent.getId()
          + InsuranceCompany.TREE_PATH_SEPARATOR);
    } else {
      insuranceCompany.setTreePath(InsuranceCompany.TREE_PATH_SEPARATOR);
    }
    insuranceCompany.setGrade(insuranceCompany.getTreePaths().size());
  }
  
}
