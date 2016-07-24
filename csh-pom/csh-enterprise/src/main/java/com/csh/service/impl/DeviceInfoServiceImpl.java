package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.DeviceInfoDao;
import com.csh.dao.VehicleDao;
import com.csh.entity.AdvanceDeposits;
import com.csh.entity.DeviceInfo;
import com.csh.entity.Vehicle;
import com.csh.entity.commonenum.CommonEnum.AdvanceUsageType;
import com.csh.entity.commonenum.CommonEnum.BindStatus;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.AdvanceDepositsService;
import com.csh.service.DeviceInfoService;

@Service("deviceInfoServiceImpl")
public class DeviceInfoServiceImpl extends BaseServiceImpl<DeviceInfo,Long> implements DeviceInfoService {

      @Resource(name="deviceInfoDaoImpl")
      private DeviceInfoDao deviceInfoDao;
      @Resource(name="vehicleDaoImpl")
      private VehicleDao vehicleDao;
      @Resource(name="advanceDepositsServiceImpl")
      private AdvanceDepositsService advanceDepositsService;
      
      @Resource
      public void setBaseDao(DeviceInfoDao deviceInfoDao) {
         super.setBaseDao(deviceInfoDao);
  }

      @Override
      @Transactional(propagation = Propagation.REQUIRED)
      public void unBind (DeviceInfo deviceInfo)
      {
        Vehicle vehicle = deviceInfo.getVehicle ();

        List<Filter> filters = new ArrayList<Filter>();
        
        Filter deviceNoFilter = new Filter("deviceNo", Operator.eq, vehicle.getDeviceNo());
        Filter endUserFilter = new Filter("endUser",Operator.eq,vehicle.getEndUser());
        Filter usageFilter = new Filter("usageType",Operator.eq,AdvanceUsageType.DEVICE);
        Filter isBindFilter = new Filter("isBind",Operator.eq,true);
        
        filters.add(deviceNoFilter);
        filters.add(endUserFilter);
        filters.add(usageFilter);
        filters.add(isBindFilter);
        
        List<AdvanceDeposits> advanceDepositsList = advanceDepositsService.findList(null, filters, null);
        
        if (advanceDepositsList != null && advanceDepositsList.size()== 1) {
			
        	AdvanceDeposits advanceDeposits= advanceDepositsList.get(0);
        	advanceDeposits.setIsBind(false);
        	advanceDepositsService.update(advanceDeposits);
		}
        
        deviceInfo.setBindStatus (BindStatus.UNBINDED);
//        vehicle.setTenantID (null);
        deviceInfo.setVehicle (null);
        deviceInfo.setBindTime (null);
        deviceInfo.setUnBindTime (new Date ());
//        vehicleDao.merge (vehicle);
        
        deviceInfoDao.merge (deviceInfo);
        
      }
}