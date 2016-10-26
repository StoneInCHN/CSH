package com.csh.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.TenantMsgDao;
import com.csh.entity.TenantMsg;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.TenantMsgService;

@Service("tenantMsgServiceImpl")
public class TenantMsgServiceImpl extends BaseServiceImpl<TenantMsg, Long> implements
    TenantMsgService {

  @Resource(name = "tenantMsgDaoImpl")
  private TenantMsgDao tenantMsgDao;

  @Resource(name = "tenantMsgDaoImpl")
  public void setBaseDao(TenantMsgDao tenantMsgDao) {
    super.setBaseDao(tenantMsgDao);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public Set<Integer> getPushMsgCat(Long tenantId) {
    List<Filter> filters = new ArrayList<Filter>();
    Filter statusFilter = new Filter("isPush", Operator.eq, false);
    Filter tenantFilter = new Filter("tenantID", Operator.eq, tenantId);
    filters.add(statusFilter);
    filters.add(tenantFilter);
    List<TenantMsg> msgs = tenantMsgDao.findList(null, null, filters, null);
    Set<Integer> msgSet = new HashSet<Integer>();
    for (TenantMsg tenantMsg : msgs) {
      msgSet.add(tenantMsg.getMsgType().ordinal());
      tenantMsg.setIsPush(true);
      tenantMsgDao.merge(tenantMsg);
    }
    return msgSet;
  }
}
