package com.csh.service.impl;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.csh.entity.estore.DeliveryCorp;
import com.csh.dao.DeliveryCorpDao;
import com.csh.service.DeliveryCorpService;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.impl.BaseServiceImpl;

@Service("deliveryCorpServiceImpl")
public class DeliveryCorpServiceImpl extends BaseServiceImpl<DeliveryCorp, Long> implements
    DeliveryCorpService {


  @Resource(name = "deliveryCorpDaoImpl")
  private DeliveryCorpDao deliveryCorpDao;

  @Resource(name = "deliveryCorpDaoImpl")
  public void setBaseDao(DeliveryCorpDao deliveryCorpDao) {
    super.setBaseDao(deliveryCorpDao);
  }

  @Override
  public Page<DeliveryCorp> findPageByFilter(String nameSearch, Pageable pageable) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery booleanQuery = findPageQuery(analyzer, nameSearch);
      return deliveryCorpDao.search(booleanQuery, pageable, null, null);
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
