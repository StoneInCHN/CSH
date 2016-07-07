package com.csh.service.impl;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.estore.ShippingMethod;
import com.csh.dao.ShippingMethodDao;
import com.csh.service.ShippingMethodService;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("shippingMethodServiceImpl")
public class ShippingMethodServiceImpl extends BaseServiceImpl<ShippingMethod, Long> implements
    ShippingMethodService {

  @Resource(name = "shippingMethodDaoImpl")
  private ShippingMethodDao shippingMethodDao;

  @Resource(name = "shippingMethodDaoImpl")
  public void setBaseDao(ShippingMethodDao shippingMethodDao) {
    super.setBaseDao(shippingMethodDao);
  }

  @Override
  public Page<ShippingMethod> findPageByFilter(String nameSearch, Pageable pageable,
      boolean isTenant) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery booleanQuery = findPageQuery(analyzer, nameSearch);
      return search(booleanQuery, pageable, null, null, isTenant);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private BooleanQuery findPageQuery(IKAnalyzer analyzer, String nameSearch) {
    BooleanQuery booleanQuery = new BooleanQuery();
    QueryParser queryParser = null;
    Query query = null;
    try {
      if (nameSearch != null) {
        String text = QueryParser.escape(nameSearch);
        queryParser = new QueryParser(Version.LUCENE_35, "name", analyzer);
        query = queryParser.parse(text);
        booleanQuery.add(query, Occur.MUST);
      }
      return booleanQuery;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
