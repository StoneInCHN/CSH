package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.VehicleBrand;
import com.csh.entity.VehicleLine;
import com.csh.dao.VehicleLineDao;
import com.csh.service.VehicleLineService;
import com.csh.utils.FieldFilterUtils;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vehicleLineServiceImpl")
public class VehicleLineServiceImpl extends BaseServiceImpl<VehicleLine,Long> implements VehicleLineService {

      @Resource(name="vehicleLineDaoImpl")
      private VehicleLineDao vehicleLineDao;
      @Resource
      public void setBaseDao(VehicleLineDao vehicleLineDao) {
         super.setBaseDao(vehicleLineDao);
  }

      @Override
      public List<Map<String, Object>> findVehicleLineByBrand (VehicleBrand vehicleBrand)
      {
        List<Filter> filters = new ArrayList<Filter>();
        if (vehicleBrand != null)
        {
          Filter vehicleBrandFilter = new Filter ();
          vehicleBrandFilter.setOperator (Operator.eq);
          vehicleBrandFilter.setValue (vehicleBrand);
          vehicleBrandFilter.setProperty ("vehicleBrand");
          
          filters.add (vehicleBrandFilter);
        }
       
        
        List<VehicleLine> vehicleLineList = vehicleLineDao.findList (null, null, filters, null);
        String[] propertys = {"id", "name","grade"};
        return FieldFilterUtils.filterCollectionMap(propertys, vehicleLineList);
      }
}