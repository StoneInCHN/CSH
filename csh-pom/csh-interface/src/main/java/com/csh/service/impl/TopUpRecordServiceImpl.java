//package com.lb.service.impl;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.lb.dao.TopUpRecordDao;
//import com.lb.entity.EndUser;
//import com.lb.entity.TopUpRecord;
//import com.lb.framework.paging.Page;
//import com.lb.framework.paging.Pageable;
//import com.lb.framework.service.impl.BaseServiceImpl;
//import com.lb.service.TopUpRecordService;
//
//@Service("topUpRecordServiceImpl")
//public class TopUpRecordServiceImpl extends BaseServiceImpl<TopUpRecord, Long> implements
//    TopUpRecordService {
//
//  @Resource(name = "topUpRecordDaoImpl")
//  private TopUpRecordDao topUpRecordDao;
//
//  @Resource(name = "topUpRecordDaoImpl")
//  public void setBaseDao(TopUpRecordDao topUpRecordDao) {
//    super.setBaseDao(topUpRecordDao);
//  }
//
//  @Override
//  public Page<TopUpRecord> findTopUpRecordByEndUser(Pageable pageable, EndUser endUser) {
//    return topUpRecordDao.findTopUpRecordByEndUser(pageable, endUser);
//  }
//
//
//}
