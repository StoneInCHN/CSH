package com.csh.service.impl;

import javax.annotation.Resource;

import com.csh.entity.commonenum.CommonEnum;
import com.csh.framework.filter.Filter;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.request.DeviceInfoRequest;
import org.springframework.stereotype.Service;

import com.csh.entity.DeviceInfo;
import com.csh.dao.DeviceInfoDao;
import com.csh.service.DeviceInfoService;
import com.csh.framework.service.impl.BaseServiceImpl;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service("deviceInfoServiceImpl")
public class DeviceInfoServiceImpl extends BaseServiceImpl<DeviceInfo, Long> implements DeviceInfoService {

    @Resource(name = "deviceInfoDaoImpl")
    public void setBaseDao(DeviceInfoDao deviceInfoDao) {
        super.setBaseDao(deviceInfoDao);
    }

    @Override
    public Page<DeviceInfo> findPageByRequest(DeviceInfoRequest request) {
        //搜索条件
        List<Filter> filterList = getFilterList(request);
        //分页信息
        Pageable pageable = new Pageable();
        if (request.getPageNumber() != null){
            pageable.setPageNumber(request.getPageNumber());
        }
        if (request.getPageSize() != null){
            pageable.setPageSize(request.getPageSize());
        }
        pageable.setFilters(filterList);
        return findPage(pageable);
    }

    @Override
    public Integer countByBindStatus(long tenantId, CommonEnum.BindStatus bindStatus) {
        //搜索条件
        List<Filter> filterList = new ArrayList<>();
        filterList.add(new Filter("tenantID", Filter.Operator.eq, tenantId));
        filterList.add(new Filter("bindStatus", Filter.Operator.eq, bindStatus));
        long count = count(filterList.toArray(new Filter[filterList.size()]));
        return (int)count;
    }

    private List<Filter> getFilterList(DeviceInfoRequest request){
        List<Filter> filterList = new ArrayList<>();
        filterList.add(new Filter("tenantID", Filter.Operator.eq, request.getTenantId()));
        if (!StringUtils.isEmpty(request.getDeviceNo())) {
            filterList.add(new Filter("deviceNo", Filter.Operator.eq, request.getDeviceNo()));
        }
        if (!StringUtils.isEmpty(request.getBindStatus())) {
            filterList.add(new Filter("bindStatus", Filter.Operator.eq, request.getBindStatus()));
        }
        return filterList;
    }

}
