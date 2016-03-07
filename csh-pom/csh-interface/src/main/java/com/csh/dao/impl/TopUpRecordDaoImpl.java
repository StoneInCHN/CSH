//package com.lb.dao.impl;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.stereotype.Repository;
//
//import com.lb.dao.TopUpRecordDao;
//import com.lb.entity.EndUser;
//import com.lb.entity.TopUpRecord;
//import com.lb.framework.dao.impl.BaseDaoImpl;
//import com.lb.framework.paging.Page;
//import com.lb.framework.paging.Pageable;
//
//@Repository("topUpRecordDaoImpl")
//public class TopUpRecordDaoImpl extends BaseDaoImpl<TopUpRecord, Long> implements TopUpRecordDao {
//
//  @Override
//  public Page<TopUpRecord> findTopUpRecordByEndUser(Pageable pageable, EndUser endUser) {
//    String jpql = "select topUpRecord from TopUpRecord topUpRecord where topUpRecord.endUser = :endUser";
//    Map<String, Object> paramMap = new HashMap<String, Object>();
//    paramMap.put("endUser", endUser);
//    return findPageCustomized(pageable, jpql, paramMap);
//  }
//
//  
// 
//
//}
