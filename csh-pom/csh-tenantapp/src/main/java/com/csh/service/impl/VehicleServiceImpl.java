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
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.common.log.LogUtil;
import com.csh.controller.DeviceInfoController;
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
import com.csh.utils.DateTimeUtils;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle, Long> implements VehicleService {
  
  @Autowired
  private VehicleDao vehicleDao;

  @Resource(name = "vehicleDaoImpl")
  public void setBaseDao(VehicleDao vehicleDao) {
    super.setBaseDao(vehicleDao);
  }

  @Override
  public Page<Vehicle> findPageForList(VehicleRequest request) {
    Page<Vehicle> vehiclePage = null;
    Pageable pageable = new Pageable();
    if (request.getPageNumber() != null) {
      pageable.setPageNumber(request.getPageNumber());
    } else {
      pageable.setPageNumber(1);
    }
    if (request.getPageSize() != null) {
      pageable.setPageSize(request.getPageSize());
    } else {
      pageable.setPageSize(5);
    }
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();
    QueryParser plateParser = new QueryParser(Version.LUCENE_35, "plate", analyzer);
    QueryParser mobileNumParser = new QueryParser(Version.LUCENE_35, "endUser.mobileNum", analyzer);
    QueryParser deviceNoParser = new QueryParser(Version.LUCENE_35, "device.deviceNo", analyzer);
    TermRangeQuery rangeQuery = null;
    Query plateQuery = null;
    Query plateNotQuery = null;
    Query mobileNumQuery = null;
    Query deviceNoQuery = null;
    Query isOnlineQuery = null;
    org.apache.lucene.search.Filter filter = null;
    String startDateStr = null;
    String endDateStr = null;
    if (request.getCreateDateStart() != null) {
      startDateStr = DateTimeUtils.convertDateToString(request.getCreateDateStart(), null);
    }
    if (request.getCreateDateEnd() != null) {
      endDateStr = DateTimeUtils.convertDateToString(request.getCreateDateEnd(), null);
    }
    if (startDateStr != null && endDateStr != null) {
      rangeQuery = new TermRangeQuery("createDate", startDateStr, endDateStr, true, true);
      query.add(rangeQuery, Occur.MUST);
      if (LogUtil.isDebugEnabled(DeviceInfoController.class)) {
        LogUtil.debug(getClass(), "findPageForList", "Search start date: " + startDateStr
            + " end date: " + endDateStr);
      }
    }

    // 车牌号
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
    // 手机号
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
    // 设备号
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

    // 是否离线
    if (request.getOnlineStatus() != null) {
      if (request.getOnlineStatus().ordinal() == OnlineStatus.ONLINE.ordinal()) {
        isOnlineQuery = new TermQuery(new Term("isOnline", "true"));
        query.add(isOnlineQuery, Occur.MUST);
      } else if (request.getOnlineStatus().ordinal() == OnlineStatus.OFFLINE.ordinal()) {
        isOnlineQuery = new TermQuery(new Term("isOnline", "false"));
        query.add(isOnlineQuery, Occur.MUST);
      }
    }

    plateNotQuery = new TermQuery(new Term("plate", "0000000"));
    query.add(plateNotQuery, Occur.MUST_NOT);

    if (plateQuery != null
        || deviceNoQuery != null
        || mobileNumQuery != null
        || (request.getOnlineStatus() != null && request.getOnlineStatus().ordinal() == OnlineStatus.ONLINE
            .ordinal())
        || (request.getOnlineStatus() != null && request.getOnlineStatus().ordinal() == OnlineStatus.OFFLINE
            .ordinal()) || rangeQuery != null) {
      vehiclePage = super.search(query, pageable, analyzer, filter, null, request.getTenantId());
    } else {
      List<Filter> filters = new ArrayList<Filter>();
      Filter plateFilter = new Filter("plate", Operator.ne, "0000000");
      filters.add(plateFilter);
      filters.add(new Filter("tenantID", Operator.eq, request.getTenantId()));
      pageable.setFilters(filters);
      vehiclePage = this.findPage(pageable);
    }
    return vehiclePage;
  }

  @Override
  public Page<Vehicle> findUnbindVehiclePage(VehicleRequest request) {
    Pageable pageable = new Pageable();
    if (request.getPageNumber() != null) {
      pageable.setPageNumber(request.getPageNumber());
    }
    if (request.getPageSize() != null) {
      pageable.setPageSize(request.getPageSize());
    }
    return vehicleDao.listUnbindVehicle(request.getTenantId(), pageable);
  }

  @SuppressWarnings("null")
  @Override
  public Long getCountByOnlineStatus(VehicleRequest request, OnlineStatus onlineStatus) {
    List<Filter> filters = new ArrayList<Filter>();
    long count = 0;
    filters.add(Filter.ne("plate", "0000000"));
    if (request.getPlate() != null) {
      filters.add(Filter.eq("plate", request.getPlate()));
    }
    if (request.getDeviceNo() != null) {
      filters.add(Filter.eq("device.deviceNo", request.getDeviceNo()));
    }
    if (request.getMobileNum() != null) {
      filters.add(Filter.eq("endUser.mobileNum", request.getDeviceNo()));
    }
    if (onlineStatus != null && OnlineStatus.ONLINE.ordinal() == onlineStatus.ordinal()) {
      filters.add(Filter.eq("isOnline", 1));
    } else if (onlineStatus != null && OnlineStatus.OFFLINE.ordinal() == onlineStatus.ordinal()) {
      filters.add(Filter.eq("isOnline", 0));
    }

    Filter[] tempfilters = new Filter[filters.size()];
    for (int i = 0; i < filters.size(); i++) {
      tempfilters[i] = filters.get(i);
    }
    count = super.count(request.getTenantId(), tempfilters);
    return count;
  }



}
