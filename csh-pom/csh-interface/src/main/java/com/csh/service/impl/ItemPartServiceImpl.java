package com.csh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.csh.dao.ItemPartDao;
import com.csh.dao.VehicleBrandDao;
import com.csh.dao.VehicleBrandDetailDao;
import com.csh.dao.VehicleLineDao;
import com.csh.entity.CarService;
import com.csh.entity.CarServiceItem;
import com.csh.entity.ItemPart;
import com.csh.entity.VehicleBrand;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.ItemPartService;
import com.csh.utils.FieldFilterUtils;

@Service("itemPartServiceImpl")
public class ItemPartServiceImpl extends BaseServiceImpl<ItemPart, Long> implements ItemPartService {

  @Resource(name = "itemPartDaoImpl")
  private ItemPartDao itemPartDao;

  @Resource(name = "vehicleBrandDaoImpl")
  private VehicleBrandDao vehicleBrandDao;

  @Resource(name = "vehicleLineDaoImpl")
  private VehicleLineDao vehicleLineDao;

  @Resource(name = "vehicleBrandDetailDaoImpl")
  private VehicleBrandDetailDao vehicleBrandDetailDao;

  @Resource(name = "itemPartDaoImpl")
  public void setBaseDao(ItemPartDao itemPartDao) {
    super.setBaseDao(itemPartDao);
  }

  @Override
  public List<Map<String, Object>> getItemPartMaps(CarService carService, Long brandDetailId) {

    VehicleBrandDetail vehicleBrandDetail = vehicleBrandDetailDao.find(brandDetailId);
    VehicleLine vehicleLine = vehicleBrandDetail.getVehicleLine();
    VehicleBrand vehicleBrand = vehicleLine.getVehicleBrand();

    List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
    for (CarServiceItem item : carService.getCarServiceItems()) {
      Map<String, Object> itemMap = new HashMap<String, Object>();
      List<ItemPart> itemParts =
          itemPartDao.getItemParts(item, vehicleBrandDetail, vehicleLine, vehicleBrand);
      if (itemParts != null && itemParts.size() > 0) {
        itemMap.put("serviceItemName", item.getServiceItemName());
        String[] properties = {"id", "serviceItemPartName", "price", "isDefault"};
        List<Map<String, Object>> map = FieldFilterUtils.filterCollectionMap(properties, itemParts);
        itemMap.put("itemParts", map);
        result.add(itemMap);
      }
    }
    return result;
  }
}
