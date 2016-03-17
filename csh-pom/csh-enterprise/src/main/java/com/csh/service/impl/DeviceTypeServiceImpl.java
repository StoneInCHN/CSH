package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.DeviceType;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.csh.dao.DeviceTypeDao;
import com.csh.service.DeviceTypeService;
import com.csh.utils.FieldFilterUtils;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("deviceTypeServiceImpl")
public class DeviceTypeServiceImpl extends BaseServiceImpl<DeviceType,Long> implements DeviceTypeService {

      @Resource(name="deviceTypeDaoImpl")
      private DeviceTypeDao deviceTypeDao;
      @Resource
      public void setBaseDao(DeviceTypeDao deviceTypeDao) {
         super.setBaseDao(deviceTypeDao);
  }

      @Override
      public List<Map<String, Object>> findAllDeviceType ()
      {
        List<Filter> filters = new ArrayList<Filter>();
        Filter statusFilter = new Filter ();
        statusFilter.setOperator (Operator.eq);
        statusFilter.setProperty ("status");
        statusFilter.setValue (Status.ENABLE);
        
        filters.add (statusFilter);
        
        List<DeviceType> deviceTypeList= deviceTypeDao.findList (null, null, filters, null);
        String[] propertys = {"id", "name"};
        return FieldFilterUtils.filterCollectionMap(propertys, deviceTypeList);
      }
}