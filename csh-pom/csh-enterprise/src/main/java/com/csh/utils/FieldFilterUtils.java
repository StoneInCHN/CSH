package com.csh.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cglib.beans.BeanMap;

/**
 * Field过滤
 * 
 * @author sujinxuan
 *
 */
public class FieldFilterUtils<T> {



  /**
   * 设置Field Value
   * 
   * @param fieldName
   * @param fieldValue
   * @param entity
   * @return
   */
  public static Object addFieldValue(String fieldName, Object fieldValue, Object entity) {
    BeanMap beanMap = BeanMap.create(entity);
    beanMap.put(fieldName, fieldValue);
    return entity;
  }


  /**
   * 集合 字段过滤(采用beanMap)
   * 
   * @param propertys 需要保留的字段名
   * @param collection 集合
   * @return
   */
  public static <T> List<Map<String, Object>> filterCollectionMap(String[] properties,
      Collection<T> collection) {
    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
    for (T entity : collection) {
      resultList.add(filterEntityMap(properties, entity));
    }
    return resultList;

  }

  /**
   * entity field 过滤 (采用beanMap)
   * 
   * @param propertys 需要保留的字段名
   * @param entity 实体对象
   * @return
   */
  public static Map<String, Object> filterEntityMap(String[] properties, Object entity) {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> childMap = new HashMap<String, Object>();
    BeanMap beanMap = BeanMap.create(entity);
    for (String key : properties) {
      String[] pros = key.split("\\.");
      if (pros != null && pros.length > 1) {// 目前只支持取2级对象
        String[] str = {pros[1]};
        
        Object object = beanMap.get(pros[0]);
        if(object != null){
          childMap.putAll(filterEntityMap(str, object));
          map.put(pros[0], childMap);
        }else{
          map.put(pros[0], null);
        }
      } else {
        map.put(key, beanMap.get(key));
      }
    }
    return map;
  }
}
