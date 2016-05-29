package com.csh.service; 

import java.util.List;
import java.util.Map;

import com.csh.entity.CarService;
import com.csh.entity.ItemPart;
import com.csh.framework.service.BaseService;

public interface ItemPartService extends BaseService<ItemPart,Long>{

	/**
	 * 获取当前车辆可用的服务项目
	 * @param carService
	 * @param brandDetailId
	 * @return
	 */
	List<Map<String, Object>> getItemPartMaps(CarService carService,Long brandDetailId);
}