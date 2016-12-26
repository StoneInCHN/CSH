package com.csh.service.impl;

import javax.annotation.Resource;

import com.csh.framework.filter.Filter;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.json.request.VehicleRequest;
import org.springframework.stereotype.Service;

import com.csh.entity.Vehicle;
import com.csh.dao.VehicleDao;
import com.csh.service.VehicleService;
import com.csh.framework.service.impl.BaseServiceImpl;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle, Long> implements VehicleService {

    @Resource(name = "vehicleDaoImpl")
    public void setBaseDao(VehicleDao vehicleDao) {
        super.setBaseDao(vehicleDao);
    }

    @Override
    public Page<Vehicle> findPageByRequest(VehicleRequest request) {
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

    private List<Filter> getFilterList(VehicleRequest request){
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
            filterList.add(new Filter("endUser.realName", Filter.Operator.like, request.getEndUserName()));
        }
        return filterList;
    }

}