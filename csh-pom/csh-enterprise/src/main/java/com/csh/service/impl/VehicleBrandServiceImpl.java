package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Department;
import com.csh.entity.VehicleBrand;
import com.csh.dao.VehicleBrandDao;
import com.csh.service.VehicleBrandService;
import com.csh.utils.FieldFilterUtils;
import com.csh.framework.filter.Filter;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vehicleBrandServiceImpl")
public class VehicleBrandServiceImpl extends BaseServiceImpl<VehicleBrand,Long> implements VehicleBrandService {

      @Resource(name="vehicleBrandDaoImpl")
      private VehicleBrandDao vehicleBrandDao;
      @Resource
      public void setBaseDao(VehicleBrandDao vehicleBrandDao) {
         super.setBaseDao(vehicleBrandDao);
  }

      @Override
      public List<Map<String, Object>> findAllVehicleBrand ()
      {
        
        List<Filter> filters = new ArrayList<Filter>();

        List<VehicleBrand> vehicleBrandList = vehicleBrandDao.findList (null, null, filters, null);
        String[] propertys = {"id", "name"};
        return FieldFilterUtils.filterCollectionMap(propertys, vehicleBrandList);
      }
}