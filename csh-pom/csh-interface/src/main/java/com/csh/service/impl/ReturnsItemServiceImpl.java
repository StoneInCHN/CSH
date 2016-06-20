package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.estore.ReturnsItem;
import com.csh.dao.ReturnsItemDao;
import com.csh.service.ReturnsItemService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("returnsItemServiceImpl")
public class ReturnsItemServiceImpl extends BaseServiceImpl<ReturnsItem,Long> implements ReturnsItemService {

      @Resource(name="returnsItemDaoImpl")
      public void setBaseDao(ReturnsItemDao returnsItemDao) {
         super.setBaseDao(returnsItemDao);
  }
}