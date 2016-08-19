package com.csh.service.impl; 

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource; 

import org.springframework.stereotype.Service; 

import com.csh.entity.Department;
import com.csh.entity.ItemPart;
import com.csh.entity.VehicleBrand;
import com.csh.entity.VehicleBrandDetail;
import com.csh.entity.VehicleLine;
import com.csh.entity.commonenum.CommonEnum.TreeNodeState;
import com.csh.dao.VehicleBrandDao;
import com.csh.service.ItemPartService;
import com.csh.service.VehicleBrandService;
import com.csh.service.VehicleLineService;
import com.csh.utils.FieldFilterUtils;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.ordering.Ordering;
import com.csh.framework.ordering.Ordering.Direction;
import com.csh.framework.service.impl.BaseServiceImpl;
import com.csh.json.response.TreeNodeResponse;

@Service("vehicleBrandServiceImpl")
public class VehicleBrandServiceImpl extends BaseServiceImpl<VehicleBrand,Long> implements VehicleBrandService {

      @Resource(name="vehicleBrandDaoImpl")
      private VehicleBrandDao vehicleBrandDao;
      @Resource (name = "vehicleLineServiceImpl")
      private VehicleLineService vehicleLineService;
      @Resource (name = "itemPartServiceImpl")
      private ItemPartService itemPartService;
      
      @Resource
      public void setBaseDao(VehicleBrandDao vehicleBrandDao) {
         super.setBaseDao(vehicleBrandDao);
  }

      @Override
      public List<Map<String, Object>> findAllVehicleBrand ()
      {
        
        List<Filter> filters = new ArrayList<Filter>();

        List<VehicleBrand> vehicleBrandList = vehicleBrandDao.findList (null, null, filters, null);
        String[] propertys = {"id", "name"};
        return FieldFilterUtils.filterCollectionMap(propertys, vehicleBrandList);
      }

      @Override
      public List<TreeNodeResponse> listVehicleBrandWithDetail (Long vehicleLineId,
          Long vehicleLineParentId, Long vehicleBrandId, Integer size,
          Integer selectCount, Long itemPartId)
      {
        Set<VehicleBrand> vehicleBrandSet = new HashSet<VehicleBrand> ();
        Set<VehicleLine> vehicleLineSet = new HashSet<VehicleLine> ();
        Set<VehicleBrandDetail> vehicleBrandDetailSet = new HashSet<VehicleBrandDetail> ();
        List<VehicleBrand> vehicleBrandList = null;
        if (itemPartId != null)
        {
          ItemPart itemPart = itemPartService.find (itemPartId);
          vehicleBrandSet = itemPart.getVehicleBrands ();
          vehicleLineSet = itemPart.getVehicleLines ();
          vehicleBrandDetailSet = itemPart.getVehicleBrandDetails ();
        }
        List<TreeNodeResponse> treeNodeResponses = new ArrayList<TreeNodeResponse> ();
//        Map<Long, TreeNodeResponse> parentMap = new HashMap<Long, TreeNodeResponse> ();
        if (vehicleLineId == null && vehicleBrandId == null && vehicleLineParentId == null)
        {
          List<Ordering> orderings = new ArrayList<Ordering> ();
          Ordering ordering = new Ordering();
          ordering.setProperty ("id");
          ordering.setDirection (Direction.asc);
          orderings.add (ordering);
          Integer first = 0;
          if (selectCount == null)
          {
            first = 0;
          }else {
            first = size*selectCount;
          }
          if (size != null)
          {
            vehicleBrandList = this.findList (first, size, null, orderings);
//            size = 10;
          }else {
             vehicleBrandList = this.findList (null,null, orderings);
          }
          
          for (VehicleBrand vehicleBrand : vehicleBrandList)
          {
            TreeNodeResponse treeNodeResponse = new TreeNodeResponse ();
            treeNodeResponse.setId (vehicleBrand.getId ());
            treeNodeResponse.setText (vehicleBrand.getName ());
            if (vehicleBrandSet.contains (vehicleBrand))
            {
              treeNodeResponse.setChecked (true);
            }else {
              treeNodeResponse.setChecked (false);
            }
            
            treeNodeResponse.setIconCls ("ico_blank");
            treeNodeResponse.setState (TreeNodeState.closed);
            treeNodeResponses.add (treeNodeResponse);
          }
        }else if(vehicleLineId == null && vehicleBrandId != null && vehicleLineParentId ==null){
          VehicleBrand vehicleBrand = this.find (vehicleBrandId);
          List<com.csh.framework.filter.Filter> filters = new ArrayList<com.csh.framework.filter.Filter>();
          if (vehicleBrand != null)
          {
            com.csh.framework.filter.Filter vehicleBrandFilter = new com.csh.framework.filter.Filter ();
            vehicleBrandFilter.setOperator (Operator.eq);
            vehicleBrandFilter.setValue (vehicleBrand);
            vehicleBrandFilter.setProperty ("vehicleBrand");
            filters.add (vehicleBrandFilter);
            
            com.csh.framework.filter.Filter vehicleParentFilter = new com.csh.framework.filter.Filter ();
            vehicleParentFilter.setOperator (Operator.isNull);
            vehicleParentFilter.setProperty ("parent");
            filters.add (vehicleParentFilter);
          }
         
          List<Ordering> orderings = new ArrayList<Ordering> ();
          Ordering ordering = new Ordering();
          ordering.setProperty ("id");
          orderings.add (ordering);
          List<VehicleLine> vehicleLineList = vehicleLineService.findList (null, null, filters, orderings);
          for (VehicleLine vehicleLine : vehicleLineList)
          {
            TreeNodeResponse treeNodeResponse = new TreeNodeResponse ();
            treeNodeResponse.setId (vehicleLine.getId ());
            treeNodeResponse.setText (vehicleLine.getName ());
            if (vehicleLineSet.contains (vehicleLine))
            {
              treeNodeResponse.setChecked (true);
            }else {
              treeNodeResponse.setChecked (false);
            }
            treeNodeResponse.setIconCls ("ico_blank");
            treeNodeResponse.setState (TreeNodeState.closed);
            treeNodeResponses.add (treeNodeResponse);
          }
        }else if(vehicleLineId == null && vehicleLineParentId != null && vehicleBrandId == null){
          VehicleLine vehicleLineParent = vehicleLineService.find (vehicleLineParentId);
          Set<VehicleLine> vehicleLineChilSet = vehicleLineParent.getChildren ();
          for (VehicleLine vehicleLine : vehicleLineChilSet)
          {
            TreeNodeResponse treeNodeResponse = new TreeNodeResponse ();
            treeNodeResponse.setId (vehicleLine.getId ());
            treeNodeResponse.setText (vehicleLine.getName ());
            if (vehicleLineSet.contains (vehicleLine))
            {
              treeNodeResponse.setChecked (true);
            }else {
              treeNodeResponse.setChecked (false);
            }
            treeNodeResponse.setIconCls ("ico_blank");
            treeNodeResponse.setState (TreeNodeState.closed);
            treeNodeResponses.add (treeNodeResponse);
          }
        }else if(vehicleLineId != null && vehicleLineParentId == null && vehicleBrandId == null){
          VehicleLine vehicleLineParent = vehicleLineService.find (vehicleLineId);
          Set<VehicleBrandDetail> vehicleBrandDetails = vehicleLineParent.getBrandDetails ();
          for (VehicleBrandDetail vehicleBrandDetail : vehicleBrandDetails)
          {
            TreeNodeResponse treeNodeResponse = new TreeNodeResponse ();
            treeNodeResponse.setId (vehicleBrandDetail.getId ());
            treeNodeResponse.setText (vehicleBrandDetail.getName ());
            if (vehicleBrandDetailSet.contains (vehicleBrandDetail))
            {
              treeNodeResponse.setChecked (true);
            }else {
              treeNodeResponse.setChecked (false);
            }
            treeNodeResponse.setState (TreeNodeState.closed);
            treeNodeResponse.setIconCls ("ico_blank");
            treeNodeResponses.add (treeNodeResponse);
          }
        }
        return treeNodeResponses;
        
      }
}