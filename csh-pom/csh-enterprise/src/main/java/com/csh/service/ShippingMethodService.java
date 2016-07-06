package com.csh.service; 

import com.csh.entity.estore.ShippingMethod;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;

public interface ShippingMethodService extends BaseService<ShippingMethod,Long>{

  Page<ShippingMethod> findPageByFilter(String nameSearch, Pageable pageable, boolean isTenant);

}