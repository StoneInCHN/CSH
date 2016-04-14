package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.common.log.LogUtil;
import com.csh.controller.EndUserController;
import com.csh.dao.EndUserDao;
import com.csh.entity.EndUser;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.EndUserService;
import com.csh.service.TenantAccountService;
import com.csh.utils.DateTimeUtils;

@Service("endUserServiceImpl")
public class EndUserServiceImpl extends BaseServiceImpl<EndUser,Long> implements EndUserService {

      @Resource(name="endUserDaoImpl")
      private EndUserDao endUserDao;
      @Resource(name="tenantAccountServiceImpl")
      private TenantAccountService tenantAccountService;
      @Resource
      public void setBaseDao(EndUserDao endUserDao) {
         super.setBaseDao(endUserDao);
  }

      @Override
      public Long countUserByTenantID (Long tenantID)
      {
        
        return endUserDao.countUserByTenantID (tenantID);
      }

  @Override
  public Page<EndUser> findEndUser (Pageable pageable, ModelMap model,
      Date beginDate, Date endDate, String userNameSearch,
      AccountStatus accountStatusSearch)
  {
    return endUserDao.findEndUser (pageable, model, beginDate, endDate,
        userNameSearch, accountStatusSearch,
        tenantAccountService.getCurrentTenantID ());

  }
}