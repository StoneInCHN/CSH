package com.csh.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.TenantInfoJdbcService;
import com.csh.service.mapper.TenantInfoRowMapper;
import com.csh.utils.LatLonUtil;

@Service("tenantInfoJdbcServiceImpl")
public class TenantInfoJdbcServiceImpl implements TenantInfoJdbcService {

  @Resource(name = "jdbcTemplate")
  private JdbcTemplate jdbcTemplate;

  public Page<Map<String, Object>> getTenantInfos(String lng, String lat, Pageable pageable,
      int radius, Long categoryId) {

    double[] aroundGps = LatLonUtil.getAround(Double.valueOf(lat), Double.valueOf(lng), radius);
    // todo pageable.getPageNumber() <1
    /*
     * Map param = new HashMap(); param.put("minLat", aroundGps[0]); param.put("minLng",
     * aroundGps[1]); param.put("maxLat", aroundGps[2]); param.put("maxLng", aroundGps[3]);
     */

    StringBuffer tenant_sql = new StringBuffer();
    tenant_sql
        .append("SELECT csc.category_name,ccs.price,ccs.promotion_price,cti.id,cti.address,cti.tenant_name,cti.photo,cti.praise_rate,");
    tenant_sql.append("round(6378.138*2*asin(sqrt(pow(sin((" + lat);
    tenant_sql.append("*pi()/180-latitude*pi()/180)/2),2)+cos(" + lat);
    tenant_sql.append("*pi()/180)*cos(latitude*pi()/180)*pow(sin((" + lng);
    tenant_sql.append("*pi()/180-longitude*pi()/180)/2),2)))*1000) as distance");
    tenant_sql.append(" FROM csh_tenant_info cti");
    tenant_sql.append(" LEFT JOIN csh_car_service ccs ON cti.id = ccs.tenant_info");
    tenant_sql.append(" LEFT JOIN csh_service_category csc ON csc.id = ccs.service_category");
    tenant_sql.append(" WHERE cti.account_status = 0 AND ccs.service_status = 0");
    if (categoryId != null) {
      tenant_sql.append(" AND csc.id = " + categoryId);
    }
    tenant_sql.append(" AND longitude > " + aroundGps[1]);
    tenant_sql.append(" AND longitude <" + aroundGps[3]);
    tenant_sql.append(" AND latitude > " + aroundGps[0]);
    tenant_sql.append(" AND latitude < " + aroundGps[2]);
    tenant_sql.append(" ORDER BY distance LIMIT " + (pageable.getPageNumber() - 1)
        * pageable.getPageSize() + "," + pageable.getPageSize() + ";");


    StringBuffer total_count_sql = new StringBuffer();
    total_count_sql.append("SELECT COUNT(*) AS total FROM (SELECT");
    total_count_sql.append(" round(6378.138*2*asin(sqrt(pow(sin((" + lat);
    total_count_sql.append("*pi()/180-latitude*pi()/180)/2),2)+cos(" + lat);
    total_count_sql.append("*pi()/180)*cos(latitude*pi()/180)*pow(sin((" + lng);
    total_count_sql.append("*pi()/180-longitude*pi()/180)/2),2)))*1000) as distance");
    total_count_sql.append(" FROM csh_tenant_info cti");
    total_count_sql.append(" LEFT JOIN csh_car_service ccs ON cti.id = ccs.tenant_info");
    total_count_sql.append(" LEFT JOIN csh_service_category csc ON csc.id = ccs.service_category");
    total_count_sql.append(" WHERE cti.account_status = 0 AND ccs.service_status = 0");
    if (categoryId != null) {
      total_count_sql.append(" AND csc.id = " + categoryId);
    }
    total_count_sql.append(" AND longitude > " + aroundGps[1]);
    total_count_sql.append(" AND longitude <" + aroundGps[3]);
    total_count_sql.append(" AND latitude > " + aroundGps[0]);
    total_count_sql.append(" AND latitude < " + aroundGps[2]);
    total_count_sql.append(" ) AS tenant_total;");



    List<Map<String, Object>> tenantInfoList =
        jdbcTemplate.query(tenant_sql.toString(), new TenantInfoRowMapper());
    Map<String, Object> totalMap = jdbcTemplate.queryForMap(total_count_sql.toString());
    Long total = (Long) totalMap.get("total");

    // int pageTotal =
    // Integer.valueOf(String.valueOf((total + pageable.getPageSize() - 1)
    // / pageable.getPageSize()));

    // pageable.setPageSize(tenantInfoList != null ? tenantInfoList.size() : 0);
    Page<Map<String, Object>> page = new Page<Map<String, Object>>(tenantInfoList, total, pageable);

    return page;
  }
}
