package com.csh.service.impl; 

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.VehicleOil;
import com.csh.entity.commonenum.CommonEnum.OilType;
import com.csh.dao.VehicleOilDao;
import com.csh.service.VehicleOilService;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("vehicleOilServiceImpl")
public class VehicleOilServiceImpl extends BaseServiceImpl<VehicleOil,Long> implements VehicleOilService {

      @Resource(name="vehicleOilDaoImpl")
      private VehicleOilDao vehicleOilDao;
      @Resource
      public void setBaseDao(VehicleOilDao vehicleOilDao) {
         super.setBaseDao(vehicleOilDao);
  }

      @Override
      public BigDecimal getOidPrice (OilType oilType, String shortPlate)
      {
        List<Filter> filters = new ArrayList<Filter>();
        Filter oilTypeFilter = new Filter ("oilType", Operator.eq, oilType);
        Filter plateFilter = new Filter ("shortPlate", Operator.eq, shortPlate);
        
        filters.add (oilTypeFilter);
        filters.add (plateFilter);
        
        List<VehicleOil> vehicleOilList =vehicleOilDao.findList (null,null, filters, null);
        if (vehicleOilList != null && vehicleOilList.size () ==1)
        {
          return vehicleOilList.get (0).getPrice ();
        }else {
          return new BigDecimal (0);
        }
      }
}