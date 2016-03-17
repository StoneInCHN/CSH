package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.EndUser;
import com.csh.entity.Vehicle;
import com.csh.dao.EndUserDao;
import com.csh.dao.VehicleDao;
import com.csh.service.VehicleService;
import com.csh.utils.FieldFilterUtils;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl extends BaseServiceImpl<Vehicle,Long> implements VehicleService {

      @Resource(name="vehicleDaoImpl")
      VehicleDao vehicleDao;
      @Resource(name="endUserDaoImpl")
      EndUserDao endUserDao;
      @Resource
      public void setBaseDao(VehicleDao vehicleDao) {
         super.setBaseDao(vehicleDao);
  }

      @Override
      public List<Map<String, Object>> findVehicleUnderUser (Long userId)
      {
        EndUser user = endUserDao.find (userId);
        
        List<Filter> filters = new ArrayList<Filter>();
        
        Filter userFilter = new Filter ("endUser", Operator.eq, user);
        filters.add (userFilter);
        List<Vehicle> vehicleList=vehicleDao.findList (null, null, filters, null);
        
        String[] propertys = {"id", "plate","dashboardMileage"};
        return FieldFilterUtils.filterCollectionMap(propertys, vehicleList);
      }
}