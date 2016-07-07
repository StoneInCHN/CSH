package com.csh.service; 

import com.csh.entity.estore.DeliveryCorp;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;

public interface DeliveryCorpService extends BaseService<DeliveryCorp,Long>{

  Page<DeliveryCorp> findPageByFilter(String nameSearch, Pageable pageable, boolean isTenant);

}