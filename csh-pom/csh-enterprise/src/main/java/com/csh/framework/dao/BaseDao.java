package com.csh.framework.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.framework.filter.Filter;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;

public interface BaseDao<T, ID extends Serializable> {

  /**
   * 查找实体对象
   * 
   * @param id ID
   * @return 实体对象，若不存在则返回null
   */
  T find(ID id);

  /**
   * 查找实体对象
   * 
   * @param id ID
   * @param lockModeType 锁定方式
   * @return 实体对象，若不存在则返回null
   */
  T find(ID id, LockModeType lockModeType);

  /**
   * 查找实体对象集合
   * 
   * @param first 起始记录
   * @param count 数量
   * @param filters 筛选
   * @param orders 排序
   * @return 实体对象集合
   */
  List<T> findList(Integer first, Integer count, List<Filter> filters, List<Ordering> orders);

  /**
   * 查找实体对象分页
   * 
   * @param pageable 分页信息
   * @return 实体对象分页
   */
  Page<T> findPage(Pageable pageable);

  /**
   * 查询实体对象数量
   * 
   * @param filters 筛选
   * @return 实体对象数量
   */
  long count(Filter... filters);

  /**
   * 持久化实体对象
   * 
   * @param entity 实体对象
   */
  void persist(T entity);

  /**
   * 合并实体对象
   * 
   * @param entity 实体对象
   * @return 实体对象
   */
  T merge(T entity);

  /**
   * 移除实体对象
   * 
   * @param entity 实体对象
   */
  void remove(T entity);

  /**
   * 刷新实体对象
   * 
   * @param entity 实体对象
   */
  void refresh(T entity);

  /**
   * 刷新实体对象
   * 
   * @param entity 实体对象
   * @param lockModeType 锁定方式
   */
  void refresh(T entity, LockModeType lockModeType);

  /**
   * 获取实体对象ID
   * 
   * @param entity 实体对象
   * @return 实体对象ID
   */
  ID getIdentifier(T entity);

  /**
   * 判断是否为托管状态
   * 
   * @param entity 实体对象
   * @return 是否为托管状态
   */
  boolean isManaged(T entity);

  /**
   * 设置为游离状态
   * 
   * @param entity 实体对象
   */
  void detach(T entity);

  /**
   * 锁定实体对象
   * 
   * @param entity 实体对象
   * @param lockModeType 锁定方式
   */
  void lock(T entity, LockModeType lockModeType);

  /**
   * 清除缓存
   */
  void clear();

  /**
   * 同步数据
   */
  void flush();

  /**
   * 关键字搜索
   */
  Page<T>  search(Query query, Pageable pageable, Analyzer analyzer, org.apache.lucene.search.Filter filter);
  
  /**
   * 重建索引
   */
  void refreshIndex();
  
  /**
   * 查询实体对象数量
   */
  int count(Query query, Analyzer analyzer, org.apache.lucene.search.Filter filter);
  /**
   * 查找实体对象集合
   */
  List<T> searchList(Query query, Analyzer analyzer, org.apache.lucene.search.Filter filter);
   
  /**
   * 调用存储过程
   */
  void callProcedure(String procName,Object...args);

  /**
   * 调用存储过程，带返回值
   * @param procName 存储过程名字
   * @param args 参数列表
   * @return
   */
  List<T> callProcedureWithResult (String procName, Object... args);
}
