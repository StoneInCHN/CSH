package com.csh.framework.service.impl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.csh.entity.base.BaseEntity;
import com.csh.framework.dao.BaseDao;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;


@Transactional
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

  /** 更新忽略属性 */
  private static final String[] UPDATE_IGNORE_PROPERTIES = new String[] {
      BaseEntity.ID_PROPERTY_NAME, BaseEntity.CREATE_DATE_PROPERTY_NAME,
      BaseEntity.MODIFY_DATE_PROPERTY_NAME};

  /** baseDao */
  private BaseDao<T, ID> baseDao;

  public void setBaseDao(BaseDao<T, ID> baseDao) {
    this.baseDao = baseDao;
  }

  @Transactional(readOnly = true)
  public T find(ID id) {
    return baseDao.find(id);
  }

  @Transactional(readOnly = true)
  public List<T> findAll() {
    return findList(null, null, null, null);
  }

  @Transactional(readOnly = true)
  public List<T> findList(ID... ids) {
    List<T> result = new ArrayList<T>();
    if (ids != null) {
      for (ID id : ids) {
        T entity = find(id);
        if (entity != null) {
          result.add(entity);
        }
      }
    }
    return result;
  }

  @Transactional(readOnly = true)
  public List<T> findList(Integer count, List<Filter> filters, List<Ordering> orderings) {
    return findList(null, count, filters, orderings);
  }

  @Transactional(readOnly = true)
  public List<T> findList(Integer first, Integer count, List<Filter> filters,
      List<Ordering> orderings) {
    return baseDao.findList(first, count, filters, orderings);
  }

  @Transactional(readOnly = true)
  public Page<T> findPage(Pageable pageable) {
    return baseDao.findPage(pageable);
  }

  @Transactional(readOnly = true)
  public long count() {
    return count(new Filter[] {});
  }

  @Transactional(readOnly = true)
  public long count(Filter... filters) {
    return baseDao.count(filters);
  }

  @Transactional(readOnly = true)
  public boolean exists(ID id) {
    return baseDao.find(id) != null;
  }

  @Transactional(readOnly = true)
  public boolean exists(Filter... filters) {
    return baseDao.count(filters) > 0;
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void save(T entity) {
    baseDao.persist(entity);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void save(List<T> entities) {
    baseDao.persist(entities);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public T update(T entity) {
    return baseDao.merge(entity);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void detach(T entity) {
    baseDao.detach(entity);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void detach(Collection<T> entitys) {
    for (T entity : entitys) {
      baseDao.detach(entity);
    }
  }

  public void update(List<T> entities) {
    baseDao.merge(entities);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public T update(T entity, String... ignoreProperties) {
    Assert.notNull(entity);
    if (baseDao.isManaged(entity)) {
      throw new IllegalArgumentException("Entity must not be managed");
    }
    T persistant = baseDao.find(baseDao.getIdentifier(entity));
    if (persistant != null) {
      copyProperties(entity, persistant,
          (String[]) ArrayUtils.addAll(ignoreProperties, UPDATE_IGNORE_PROPERTIES));
      return update(persistant);
    } else {
      return update(entity);
    }
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void delete(ID id) {
    delete(baseDao.find(id));
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void delete(ID... ids) {
    if (ids != null) {
      for (ID id : ids) {
        delete(baseDao.find(id));
      }
    }
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void delete(T entity) {
    baseDao.remove(entity);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private void copyProperties(Object source, Object target, String[] ignoreProperties)
      throws BeansException {
    Assert.notNull(source, "Source must not be null");
    Assert.notNull(target, "Target must not be null");

    PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());
    List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
    for (PropertyDescriptor targetPd : targetPds) {
      if (targetPd.getWriteMethod() != null
          && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
        PropertyDescriptor sourcePd =
            BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
        if (sourcePd != null && sourcePd.getReadMethod() != null) {
          try {
            Method readMethod = sourcePd.getReadMethod();
            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
              readMethod.setAccessible(true);
            }
            Object sourceValue = readMethod.invoke(source);
            Object targetValue = readMethod.invoke(target);
            if (sourceValue != null && targetValue != null && targetValue instanceof Collection) {
              Collection collection = (Collection) targetValue;
              collection.clear();
              collection.addAll((Collection) sourceValue);
            } else {
              Method writeMethod = targetPd.getWriteMethod();
              if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                writeMethod.setAccessible(true);
              }
              writeMethod.invoke(target, sourceValue);
            }
          } catch (Throwable ex) {
            throw new FatalBeanException("Could not copy properties from source to target", ex);
          }
        }
      }
    }
  }

  @Override
  public Page<T> search(Query query, Pageable pageable, Analyzer analyzer,
      org.apache.lucene.search.Filter filter, SortField sortField) {
    return baseDao.search(query, pageable, analyzer, filter, sortField);
  }
  
  @Override
  public Page<T> search(Query query, Pageable pageable, Analyzer analyzer,
      org.apache.lucene.search.Filter filter, SortField sortField, Long tenantID) {
    if(tenantID !=null){
       TermQuery tenantIdQuery = new TermQuery(new Term("tenantID", tenantID.toString()));
       ((BooleanQuery)query).add (tenantIdQuery,Occur.MUST);
    }
    return baseDao.search(query, pageable, analyzer, filter, sortField);
  }

  @Override
  public void refreshIndex() {
    baseDao.refreshIndex();
  }
  
  @Transactional(readOnly = true)
  public List<T> findAll(Boolean isTenant,Long tenantID) {
    return findList(null, null, null,isTenant, null,tenantID);
  }
  
  @Transactional(readOnly = true)
  public List<T> findList(Integer count, List<Filter> filters, List<Ordering> orderings,
      Boolean isTenant, String flag,Long tenantID) {
    if (isTenant) {
      if(filters == null){
        filters = new ArrayList<Filter>();
      }
      Filter tenantFilter =
          new Filter("tenantID", Operator.eq, tenantID);
      filters.add(tenantFilter);
    }
    return findList(null, count, filters, orderings);
  }

  @Transactional(readOnly = true)
  public Page<T> findPage(Pageable pageable, Boolean isTenant,Long tenantID) {
    if (isTenant) {
      List<Filter> filters = pageable.getFilters();
      Filter tenantFilter =
          new Filter("tenantID", Operator.eq, tenantID);
      filters.add(tenantFilter);
    }
    return baseDao.findPage(pageable);
  }

  @Override
  public long count(Long tenantID, Filter... filters) {
    if(tenantID !=null){
      Filter tenantIdFilter = new Filter();
      tenantIdFilter.setOperator (Operator.eq);
      tenantIdFilter.setProperty ("tenantID");
      tenantIdFilter.setValue (tenantID);
      Filter[]copyArr = Arrays.copyOf(filters,filters.length+1);
      copyArr[filters.length]=tenantIdFilter;
     /* Arrays.sort(copyArr);*/
      return baseDao.count (copyArr);
    }else{
      return baseDao.count(filters);
    }  
  }



}
