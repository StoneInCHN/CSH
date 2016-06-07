package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.ItemPartDao;
import com.csh.entity.ItemPart;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CarServiceItemService;
import com.csh.service.ItemPartService;

@Service("itemPartServiceImpl")
public class ItemPartServiceImpl extends BaseServiceImpl<ItemPart,Long> implements ItemPartService {

      @Resource(name = "carServiceItemServiceImpl")
      private CarServiceItemService carServiceItemService;
      @Resource(name="itemPartDaoImpl")
      public void setBaseDao(ItemPartDao itemPartDao) {
         super.setBaseDao(itemPartDao);
  }

      @Override
      @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
      public void saveItemPart (ItemPart itemPart)
      {
        if (itemPart.getIsDefault ())
        {
          List<Filter> filters = new ArrayList<Filter>();
          Filter isDefault = new Filter ("isDefault", Operator.eq, true);
          Filter carServiceItemFilter = new Filter ("carServiceItem", Operator.eq, itemPart.getCarServiceItem ());
          filters.add (isDefault);
          filters.add (carServiceItemFilter);
          
          List<ItemPart> itemParts = this.findList (null, filters, null);
          for (ItemPart itemPartDefault : itemParts)
          {
            itemPartDefault.setIsDefault (false);
            this.save (itemPartDefault);
          }
        }
        this.save(itemPart,true);
      }
}