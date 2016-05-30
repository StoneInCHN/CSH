package com.csh.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.csh.dao.ItemPartDao;
import com.csh.entity.CarServiceItem;
import com.csh.entity.ItemPart;
import com.csh.entity.VehicleBrand;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.framework.dao.impl.BaseDaoImpl;

@Repository("itemPartDaoImpl")
public class ItemPartDaoImpl extends BaseDaoImpl<ItemPart, Long> implements ItemPartDao {

  @Override
  public List<ItemPart> getItemParts(CarServiceItem item, VehicleBrandDetail vehicleBrandDetail,
      VehicleLine vehicleLine, VehicleBrand vehicleBrand) {
    List<ItemPart> itemParts = new ArrayList<ItemPart>();
    try {
      String jpqlDetails =
          "select item from ItemPart item join item.vehicleBrandDetails brandDetail where item.carServiceItem = :carServiceItem and brandDetail = :vehicleBrandDetail";
      List<ItemPart> detailItems =
          entityManager.createQuery(jpqlDetails, ItemPart.class).setFlushMode(FlushModeType.COMMIT)
              .setParameter("carServiceItem", item)
              .setParameter("vehicleBrandDetail", vehicleBrandDetail).getResultList();
      itemParts.addAll(detailItems);

      String jpqlLine =
          "select item from ItemPart item join item.vehicleLines line where item.carServiceItem = :carServiceItem and line = :vehicleLine";
      List<ItemPart> lineItems =
          entityManager.createQuery(jpqlLine, ItemPart.class).setFlushMode(FlushModeType.COMMIT)
              .setParameter("carServiceItem", item).setParameter("vehicleLine", vehicleLine)
              .getResultList();
      itemParts.addAll(lineItems);

      String jpqlBrand =
          "select item from ItemPart item join item.vehicleBrands brand where item.carServiceItem = :carServiceItem and brand = :vehicleBrand";
      List<ItemPart> brandItems =
          entityManager.createQuery(jpqlBrand, ItemPart.class).setFlushMode(FlushModeType.COMMIT)
              .setParameter("carServiceItem", item).setParameter("vehicleBrand", vehicleBrand)
              .getResultList();
      itemParts.addAll(brandItems);
      List<ItemPart> result = new ArrayList<ItemPart>();
      List<Long> ids = new ArrayList<Long>();
      for (ItemPart itemPart : itemParts) {
        if (!ids.contains(itemPart.getId())) {
          result.add(itemPart);
        }
        ids.add(itemPart.getId());
      }
      return result;

    } catch (NoResultException e) {
      return null;
    }
  }



}
