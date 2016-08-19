package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.csh.dao.ItemPartDao;
import com.csh.entity.ItemPart;
import com.csh.entity.VehicleBrand;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.service.CarServiceItemService;
import com.csh.service.ItemPartService;
import com.csh.service.VehicleBrandDetailService;
import com.csh.service.VehicleBrandService;
import com.csh.service.VehicleLineService;

@Service("itemPartServiceImpl")
public class ItemPartServiceImpl extends BaseServiceImpl<ItemPart,Long> implements ItemPartService {

      @Resource(name = "carServiceItemServiceImpl")
      private CarServiceItemService carServiceItemService;
      @Resource (name = "vehicleBrandServiceImpl")
      private VehicleBrandService vehicleBrandService;
      
      @Resource (name = "vehicleLineServiceImpl")
      private VehicleLineService vehicleLineService;
      
      @Resource (name = "vehicleBrandDetailServiceImpl")
      private VehicleBrandDetailService vehicleBrandDetailService;
      
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
            this.update (itemPartDefault);
          }
        }
        this.save(itemPart,true);
      }

      @Override
      public void updateItemParts (ItemPart itemPart, Boolean selectAll,
          Long[] deleteBrandItemIds, Long[] selectBrandItemIds,
          Long[] deleteLineParentItemIds,Long[] selectLineParentItemIds,
          Long[] selectLineChildItemIds, Long[] deleteLineChildItemIds,
          Long[] deleteBrandDetailItemIds, Long[] selectBrandDetailItemIds)
  {
    if (selectAll == null || !selectAll)
    {
      ItemPart origItemPart = this.find (itemPart.getId ());

      Set<VehicleBrand> origVehicleBrandSet = origItemPart.getVehicleBrands ();
      Set<VehicleLine> origVehicleLineSet = origItemPart.getVehicleLines ();
      Set<VehicleBrandDetail> origVehicleBrandDetailSet = origItemPart
          .getVehicleBrandDetails ();

      Set<VehicleBrand> newVehicleBrandSet = new HashSet<VehicleBrand> ();
      newVehicleBrandSet.addAll (origVehicleBrandSet);
      Set<VehicleLine> newVehicleLineSet = new HashSet<VehicleLine> ();
      newVehicleLineSet.addAll (origVehicleLineSet);
      Set<VehicleBrandDetail> newVehicleBrandDetailSet = new HashSet<VehicleBrandDetail> ();
      newVehicleBrandDetailSet.addAll (origVehicleBrandDetailSet);

      List<VehicleLine> deleteVehicleLineUnderBrand = new ArrayList<VehicleLine> ();
      List<VehicleBrandDetail> deleteVehicleBrandDetailUnderLine = new ArrayList<VehicleBrandDetail> ();
      //删除的车品牌
      List<VehicleBrand> deleteBrandList = vehicleBrandService
          .findList (deleteBrandItemIds);
      for (VehicleBrand deleteBrand : deleteBrandList)
      {
        if (origVehicleBrandSet.contains (deleteBrand))
        {
          newVehicleBrandSet.remove (deleteBrand);
          deleteVehicleLineUnderBrand.addAll (deleteBrand.getChildren ());
        }
      }
      //添加的车品牌
      List<VehicleBrand> selectBrandList = vehicleBrandService
          .findList (selectBrandItemIds);
      for (VehicleBrand selectBrand : selectBrandList)
      {
        if (!origVehicleBrandSet.contains (selectBrand))
        {
          newVehicleBrandSet.add (selectBrand);
        }
      }
      //删除的车系
      List<VehicleLine> deleteLineParentList = vehicleLineService
          .findList (deleteLineParentItemIds);
      deleteLineParentList.addAll (deleteVehicleLineUnderBrand);//删除品牌的时候，需要同时删除下面的车系，车型数据
      for (VehicleLine deleteLine : deleteLineParentList)
      {
        if (origVehicleLineSet.contains (deleteLine))
        {
          newVehicleLineSet.remove (deleteLine);
        }
      }
      //添加的车系
      List<VehicleLine> selectLineParentList = vehicleLineService
          .findList (selectLineParentItemIds);
      for (VehicleLine selectLine : selectLineParentList)
      {
        if (!origVehicleLineSet.contains (selectLine))
        {
          newVehicleLineSet.add (selectLine);
        }
      }
      //删除的车系
      List<VehicleLine> deleteLineChildList = vehicleLineService
          .findList (deleteLineChildItemIds);
      deleteLineChildList.addAll (deleteVehicleLineUnderBrand);
      for (VehicleLine deleteLine : deleteLineChildList)
      {
        if (origVehicleLineSet.contains (deleteLine))
        {
          newVehicleLineSet.remove (deleteLine);
          deleteVehicleBrandDetailUnderLine.addAll (deleteLine
              .getBrandDetails ());
        }
      }
      //添加的车系
      List<VehicleLine> selectLineChildList = vehicleLineService
          .findList (selectLineChildItemIds);
      for (VehicleLine selectLine : selectLineChildList)
      {
        if (!origVehicleLineSet.contains (selectLine))
        {
          newVehicleLineSet.add (selectLine);
        }
      }
      //删除的车型
      List<VehicleBrandDetail> deleteBrandDetailList = vehicleBrandDetailService
          .findList (deleteBrandDetailItemIds);
      deleteBrandDetailList.addAll (deleteVehicleBrandDetailUnderLine);
      for (VehicleBrandDetail deleteBrandDetail : deleteBrandDetailList)
      {
        if (origVehicleBrandDetailSet.contains (deleteBrandDetail))
        {
          newVehicleBrandDetailSet.remove (deleteBrandDetail);
        }
      }
      //添加的车型
      List<VehicleBrandDetail> selectBrandDetailList = vehicleBrandDetailService
          .findList (selectBrandDetailItemIds);
      for (VehicleBrandDetail selectBrandDetail : selectBrandDetailList)
      {
        if (!origVehicleBrandDetailSet.contains (selectBrandDetail))
        {
          newVehicleBrandDetailSet.add (selectBrandDetail);
        }
      }
      itemPart.setVehicleBrandDetails (newVehicleBrandDetailSet);
      itemPart.setVehicleBrands (newVehicleBrandSet);
      itemPart.setVehicleLines (newVehicleLineSet);

      if (itemPart.getIsDefault ())
      {
        List<Filter> filters = new ArrayList<Filter> ();
        Filter isDefault = new Filter ("isDefault", Operator.eq, true);
        Filter carServiceItemFilter = new Filter ("carServiceItem",
            Operator.eq, origItemPart.getCarServiceItem ());
        filters.add (isDefault);
        filters.add (carServiceItemFilter);

        List<ItemPart> itemParts = this.findList (null, filters, null);
        for (ItemPart itemPartDefault : itemParts)
        {
          itemPartDefault.setIsDefault (false);
          this.update (itemPartDefault);
        }
      }
    }
    else
    {
      List<VehicleBrand> vehicleBrands = vehicleBrandService.findAll ();
      itemPart.setVehicleBrands (new HashSet<VehicleBrand> (vehicleBrands));
    }
    this.update (itemPart, "createDate", "tenantID", "carServiceItem");
  }
}