package com.csh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.common.log.LogUtil;
import com.csh.dao.DeviceInfoDao;
import com.csh.entity.DeviceInfo;
import com.csh.entity.commonenum.CommonEnum;
import com.csh.entity.commonenum.CommonEnum.BindStatus;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.json.request.DeviceInfoRequest;
import com.csh.service.DeviceInfoService;

@Service("deviceInfoServiceImpl")
public class DeviceInfoServiceImpl extends BaseServiceImpl<DeviceInfo, Long> implements
    DeviceInfoService {

  @Resource(name = "deviceInfoDaoImpl")
  public void setBaseDao(DeviceInfoDao deviceInfoDao) {
    super.setBaseDao(deviceInfoDao);
  }

  @Override
  public Page<DeviceInfo> findPageByRequest(DeviceInfoRequest request) {
    // 分页信息
    Pageable pageable = new Pageable();
    if (request.getPageNumber() != null) {
      pageable.setPageNumber(request.getPageNumber());
    }
    if (request.getPageSize() != null) {
      pageable.setPageSize(request.getPageSize());
    }

    // 搜索条件(数据库)
    // List<com.csh.framework.filter.Filter> filterList = getFilterList(request);
//    pageable.setFilters(filterList);

    // 搜索(lucene)
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();

    QueryParser nameParser = new QueryParser(Version.LUCENE_35, "deviceNo", analyzer);
    Query nameQuery = null;
    TermQuery statusQuery = null;
    TermQuery tenantIdQuery = null;

    Filter filter = null;
    String deviceNo = request.getDeviceNo();
    BindStatus bindStatus = request.getBindStatus();
    Long tenantId = request.getTenantId();
    
    if (!StringUtils.isEmpty(deviceNo)) {
      String text = QueryParser.escape(deviceNo);
      try {
        nameParser.setAllowLeadingWildcard(true);
        nameQuery = nameParser.parse("*" + text + "*");
        query.add(nameQuery, Occur.MUST);
        if (LogUtil.isDebugEnabled(getClass())) {
          LogUtil.debug(getClass(), "findPageByRequest", "Search device NO: " + deviceNo);
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    if (bindStatus != null) {
      statusQuery = new TermQuery(new Term("bindStatus", bindStatus.toString()));
      query.add(statusQuery, Occur.MUST);
    }
    if (tenantId != null) {
      tenantIdQuery = new TermQuery(new Term("tenantID", tenantId.toString()));
      query.add(tenantIdQuery, Occur.MUST);
    }
    if (nameQuery != null || statusQuery != null || tenantIdQuery != null) {
      return search(query, pageable, analyzer, filter, null);
    }
    return findPage(pageable);

  }

  @Override
  public Integer countByBindStatus(long tenantId, CommonEnum.BindStatus bindStatus) {
    // 搜索条件
    List<com.csh.framework.filter.Filter> filterList = new ArrayList<>();
    filterList.add(new com.csh.framework.filter.Filter("tenantID",
        com.csh.framework.filter.Filter.Operator.eq, tenantId));
    filterList.add(new com.csh.framework.filter.Filter("bindStatus",
        com.csh.framework.filter.Filter.Operator.eq, bindStatus));
    long count = count(filterList.toArray(new com.csh.framework.filter.Filter[filterList.size()]));
    return (int) count;
  }

  private List<com.csh.framework.filter.Filter> getFilterList(DeviceInfoRequest request) {
    List<com.csh.framework.filter.Filter> filterList = new ArrayList<>();
    filterList.add(new com.csh.framework.filter.Filter("tenantID",
        com.csh.framework.filter.Filter.Operator.eq, request.getTenantId()));
    if (!StringUtils.isEmpty(request.getDeviceNo())) {
      filterList.add(new com.csh.framework.filter.Filter("deviceNo",
          com.csh.framework.filter.Filter.Operator.eq, request.getDeviceNo()));
    }
    if (!StringUtils.isEmpty(request.getBindStatus())) {
      filterList.add(new com.csh.framework.filter.Filter("bindStatus",
          com.csh.framework.filter.Filter.Operator.eq, request.getBindStatus()));
    }
    return filterList;
  }

}
