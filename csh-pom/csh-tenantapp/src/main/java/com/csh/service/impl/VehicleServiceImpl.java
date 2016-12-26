package com.csh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.common.log.LogUtil;
import com.csh.controller.VehicleController;
import com.csh.dao.VehicleDao;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.OnlineStatus;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.json.request.VehicleRequest;
import com.csh.service.VehicleService;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle, Long> implements VehicleService {

  @Resource(name = "vehicleDaoImpl")
  public void setBaseDao(VehicleDao vehicleDao) {
    super.setBaseDao(vehicleDao);
  }

  @Override
  public Page<Vehicle> findPageByRequest(VehicleRequest request) {
    // 搜索条件
    List<Filter> filterList = getFilterList(request);
    // 分页信息
    Pageable pageable = new Pageable();
    if (request.getPageNumber() != null) {
      pageable.setPageNumber(request.getPageNumber());
    }
    if (request.getPageSize() != null) {
      pageable.setPageSize(request.getPageSize());
    }
    pageable.setFilters(filterList);
    return findPage(pageable);
  }

  private List<Filter> getFilterList(VehicleRequest request) {
    List<Filter> filterList = new ArrayList<>();
    filterList.add(new Filter("tenantID", Filter.Operator.eq, request.getTenantId()));
    if (!StringUtils.isEmpty(request.getPlate())) {
      filterList.add(new Filter("plate", Filter.Operator.eq, request.getPlate()));
    }
    if (request.getPlateDateStart() != null && request.getPlateDateEnd() != null) {
      filterList.add(new Filter("plateDate", Filter.Operator.ge, request.getPlateDateStart()));
      filterList.add(new Filter("plateDate", Filter.Operator.le, request.getPlateDateEnd()));
    }
    if (!StringUtils.isEmpty(request.getEndUserName())) {
      // TODO:还不确定是不是模糊查询
      filterList
          .add(new Filter("endUser.realName", Filter.Operator.like, request.getEndUserName()));
    }
    return filterList;
  }

  @Override
  public Page<Vehicle> findPageForList(VehicleRequest request) {
    Page<Vehicle> vehiclePage = null;
    Pageable pageable = new Pageable();
    if (request.getPageNumber() != null) {
      pageable.setPageNumber(request.getPageNumber());
    }
    if (request.getPageSize() != null) {
      pageable.setPageSize(request.getPageSize());
    }
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();
    QueryParser plateParser = new QueryParser(Version.LUCENE_35, "plate", analyzer);
    QueryParser mobileNumParser = new QueryParser(Version.LUCENE_35, "endUser.mobileNum", analyzer);
    QueryParser deviceNoParser = new QueryParser(Version.LUCENE_35, "device.deviceNo", analyzer);
    Query plateQuery = null;
    Query plateNotQuery = null;
    Query mobileNumQuery = null;
    Query deviceNoQuery = null;
    Query isOnlineQuery = null;
    org.apache.lucene.search.Filter filter = null;
    //车牌号
    if (request.getPlate() != null) {
      String text = QueryParser.escape(request.getPlate());
      try {
        // 通配符查询，开启*开头，但影响效率
        plateParser.setAllowLeadingWildcard(true);
        plateQuery = plateParser.parse("*" + text + "*");
        query.add(plateQuery, Occur.MUST);
        if (LogUtil.isDebugEnabled(VehicleController.class)) {
          LogUtil.debug(VehicleController.class, "search", "Search plate: " + request.getPlate());
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    //手机号
    if (request.getMobileNum() != null) {
      String text = QueryParser.escape(request.getMobileNum());
      try {
        mobileNumParser.setAllowLeadingWildcard(true);
        mobileNumQuery = mobileNumParser.parse("*" + text + "*");
        query.add(mobileNumQuery, Occur.MUST);
        if (LogUtil.isDebugEnabled(VehicleServiceImpl.class)) {
          LogUtil.debug(VehicleServiceImpl.class, "search",
              "Search mobile number : " + request.getMobileNum());
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }
    //设备号
    if (request.getDeviceNo() != null) {
      String text = QueryParser.escape(request.getDeviceNo());
      try {
        deviceNoParser.setAllowLeadingWildcard(true);
        deviceNoQuery = deviceNoParser.parse("*" + text + "*");
        query.add(deviceNoQuery, Occur.MUST);
        if (LogUtil.isDebugEnabled(VehicleServiceImpl.class)) {
          LogUtil.debug(VehicleServiceImpl.class, "search",
              "Search device number: " + request.getDeviceNo());
        }
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }

    //是否离线
    if (request.getOnlineStatus() != null) {
      if(request.getOnlineStatus().ordinal() == OnlineStatus.ONLINE.ordinal()){
        isOnlineQuery = new TermQuery(new Term("isOnline","1"));
        query.add(isOnlineQuery, Occur.MUST);
      }else if (request.getOnlineStatus().ordinal() == OnlineStatus.OFFLINE.ordinal()) {
        isOnlineQuery = new TermQuery(new Term("isOnline","0"));
        query.add(isOnlineQuery, Occur.MUST);
      }
    }
    
    plateNotQuery = new TermQuery(new Term("plate", "0000000"));
    query.add(plateNotQuery, Occur.MUST_NOT);

    if (plateQuery != null || deviceNoQuery != null || mobileNumQuery != null) {
      vehiclePage = super.search(query, pageable, analyzer, filter, null);
    } else {
      List<Filter> filters = new ArrayList<Filter>();
      Filter plateFilter = new Filter("plate", Operator.ne, "0000000");
      filters.add(plateFilter);
      pageable.setFilters(filters);
      vehiclePage = this.findPage(pageable);
    }
    return vehiclePage;
  }

}
