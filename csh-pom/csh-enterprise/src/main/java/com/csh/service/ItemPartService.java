package com.csh.service; 

import java.util.List;

import com.csh.entity.ItemPart;
import com.csh.framework.service.BaseService;

public interface ItemPartService extends BaseService<ItemPart,Long>{

  void saveItemPart (ItemPart itemPart);


  void updateItemParts (ItemPart itemPart, Boolean selectAll,
      Long[] deleteBrandItemIds, Long[] selectBrandItemIds,
      Long[] deleteLineParentItemIds, Long[] selectLineParentItemIds,
      Long[] selectLineChildItemIds, Long[] deleteLineChildItemIds,
      Long[] deleteBrandDetailItemIds, Long[] selectBrandDetailItemIds);

}