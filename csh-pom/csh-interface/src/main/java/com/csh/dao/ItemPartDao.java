package com.csh.dao;

import java.util.List;

import com.csh.entity.CarServiceItem;
import com.csh.entity.ItemPart;
import com.csh.entity.VehicleBrand;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.framework.dao.BaseDao;

public interface ItemPartDao extends BaseDao<ItemPart, Long> {

  /**
   * 获取服务详情项目
   * 
   * @param item
   * @param vehicleBrandDetail
   * @param vehicleLine
   * @param vehicleBrand
   * @return
   */
  public List<ItemPart> getItemParts(CarServiceItem item, VehicleBrandDetail vehicleBrandDetail,
      VehicleLine vehicleLine, VehicleBrand vehicleBrand);
}
