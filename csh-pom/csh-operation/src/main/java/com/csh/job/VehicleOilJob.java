package com.csh.job;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.csh.beans.OilBean;
import com.csh.common.log.LogUtil;
import com.csh.service.VehicleOilService;
import com.csh.utils.CommonUtils;
import com.csh.utils.JsonUtil;

@Component("vehicleOilJob")
@Lazy(false)
public class VehicleOilJob {

  @Resource(name="vehicleOilServiceImpl")
  private VehicleOilService vehicleOilService;
  
  /**
   * 定时获取油价信息
   */
  @Scheduled(cron = "${job.csh.vehicleOilJob.cron}")
  public void updateOilInfo() {
    if(LogUtil.isDebugEnabled(VehicleOilJob.class)){
      LogUtil.debug(VehicleOilJob.class, "updateOilInfo", "%s", "Starting Scanning........");
    }
    String jsonResult = CommonUtils.getOilInfo4BaiDu();
    Map<String, Object> map = JsonUtil.getMap4Json(jsonResult);
    String[] strings = jsonResult.split("\"list\":");
    String str = null;
    String objString = null;
    for (String string : strings) {
      if (!string.contains("ret_code")) {
        str = string;
      }
    }
    if (str != null) {
          objString = str.substring(0, str.length() - 4);
    }
    if (objString != null) {
      List<OilBean> oilBeans = JsonUtil.getList4Json(objString, OilBean.class);
      for (OilBean oilBean : oilBeans) {
        if(LogUtil.isDebugEnabled(VehicleOilJob.class)){
          LogUtil.debug(VehicleOilJob.class, "updateOilInfo", "%s的油价:p0=%s , p90 = %s, p93 = %s, p97 = %s",oilBean.getProv(),oilBean.getP0(),oilBean.getP90(),oilBean.getP93(),oilBean.getP97());
        }
        vehicleOilService.updateOilInfo(oilBean);
      }

    }
    if(LogUtil.isDebugEnabled(VehicleOilJob.class)){
      LogUtil.debug(VehicleOilJob.class, "updateOilInfo", "%s", "end of scan........");
    }
  }
  
  
  
  
}
