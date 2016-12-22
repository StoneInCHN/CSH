package com.csh.service.impl; 

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.ItemPart;
import com.csh.dao.ItemPartDao;
import com.csh.service.ItemPartService;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("itemPartServiceImpl")
public class ItemPartServiceImpl extends BaseServiceImpl<ItemPart,Long> implements ItemPartService {

      @Resource(name="itemPartDaoImpl")
      public void setBaseDao(ItemPartDao itemPartDao) {
         super.setBaseDao(itemPartDao);
  }
}