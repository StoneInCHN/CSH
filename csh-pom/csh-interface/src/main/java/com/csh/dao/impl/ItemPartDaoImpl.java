package com.csh.dao.impl; 

import java.util.List;
import java.util.Set;

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
public class ItemPartDaoImpl extends  BaseDaoImpl<ItemPart,Long> implements ItemPartDao {

	@Override
	public List<ItemPart> getItemParts(CarServiceItem item,
			Set<VehicleBrandDetail> vehicleBrandDetails,
			Set<VehicleLine> vehicleLines, Set<VehicleBrand> vehicleBrands) {
		    try {
		      String jpql = "select item from ItemPart item where item.carServiceItem = :carServiceItem and (item.vehicleBrandDetails=:vehicleBrandDetails or (item.vehicleLines=:vehicleLines and item.vehicleBrandDetails is null) or (item.vehicleBrands=:vehicleBrands and item.vehicleLines is null and item.vehicleBrandDetails is null))";
		      return entityManager.createQuery(jpql, ItemPart.class).setFlushMode(FlushModeType.COMMIT)
		          .setParameter("carServiceItem", item).setParameter("vehicleBrandDetails", vehicleBrandDetails).setParameter("vehicleLines", vehicleLines).setParameter("vehicleBrands", vehicleBrands).getResultList();
		    } catch (NoResultException e) {
		      return null;
		    }
	}

	

}