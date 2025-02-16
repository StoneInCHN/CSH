package com.csh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.beans.Setting;
import com.csh.dao.IdentifierDao;
import com.csh.entity.Identifier;
import com.csh.entity.commonenum.CommonEnum.IdentifierType;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.IdentifierService;
import com.csh.utils.SettingUtils;

@Service("identifierServiceImpl")
public class IdentifierServiceImpl extends BaseServiceImpl<Identifier, Long> implements
    IdentifierService {

  @Resource(name = "identifierDaoImpl")
  public void setBaseDao(IdentifierDao identifierDao) {
    super.setBaseDao(identifierDao);
  }

  @Resource(name = "identifierDaoImpl")
  private IdentifierDao identifierDao;
  
  @Override
  public Long getLatestIdentifier(IdentifierType idType) {
    Long lastValue=null;
    Pageable pageable = new Pageable();
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter();
    filter.setProperty("idType");
    filter.setOperator(Operator.eq);
    filter.setValue(idType);
    filters.add(filter);
    List<Ordering> orderings = new ArrayList<Ordering>();
    Ordering ordering = new Ordering();
    ordering.setDirection(Direction.desc);
    ordering.setProperty("lastValue");
    orderings.add(ordering);
    pageable.setPageSize(1);
    pageable.setFilters(filters);
    pageable.setOrders(orderings);
    Page<Identifier> pages = identifierDao.findPage(pageable);
    List<Identifier> identifiers = pages.getContent();
    if (identifiers!=null && identifiers.size() == 1) {
      Identifier identifier = identifiers.get(0);
      lastValue = identifier.getLastValue();
      identifier.setLastValue(++lastValue);
      identifierDao.merge(identifier);
    }else{
      Setting setting = SettingUtils.get();
      lastValue = setting.getIdentifierLastvalue4OrgCode();
      Identifier identifier = new Identifier();
      identifier.setIdType(IdentifierType.ORGCODE);
      identifier.setLastValue(++lastValue);
      identifierDao.persist(identifier);
    }
    
    return lastValue;
  }

  @Override
  public String getLatestOrgCode() {
    String orgCode="";
    Long lastValue = getLatestIdentifier(IdentifierType.ORGCODE);
    Setting setting = SettingUtils.get();
    Integer codeLength = setting.getIdentifierLength4OrgCode();
    int valueLength = lastValue.toString().length();
    orgCode = lastValue.toString();
    if (codeLength - valueLength >0) {
      for (int i = 0; i < codeLength - valueLength; i++) {
        orgCode = "0"+ orgCode;
      }
    }
    return orgCode;
  }
}
