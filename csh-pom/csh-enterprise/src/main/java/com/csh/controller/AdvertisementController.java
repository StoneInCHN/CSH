package com.csh.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.BaseController;
import com.csh.entity.Advertisement;
import com.csh.entity.commonenum.CommonEnum.AdType;
import com.csh.entity.commonenum.CommonEnum.ImageType;
import com.csh.entity.commonenum.CommonEnum.Status;
import com.csh.entity.commonenum.CommonEnum.SystemType;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.service.AdvertisementService;
import com.csh.service.FileService;
import com.csh.utils.DateTimeUtils;

/**
 * 广告
 * @author huyong
 *
 */
@Controller ("advertisementController")
@RequestMapping ("console/advertisement")
public class AdvertisementController extends BaseController
{

  @Resource (name = "advertisementServiceImpl")
  private AdvertisementService advertisementService;
  @Resource (name = "fileServiceImpl")
  private FileService fileService;

  @RequestMapping (value = "/advertisement", method = RequestMethod.GET)
  public String list (ModelMap model)
  {
    return "advertisement/advertisement";
  }

  @RequestMapping (value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Advertisement> list (String searchAdvName,Status searchStatus,Date beginDate, Date endDate, Pageable pageable, ModelMap model)
  {
    String startDateStr = null;
    String endDateStr = null;

    SimpleAnalyzer analyzer = new SimpleAnalyzer (Version.LUCENE_35);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "advName",
        analyzer);
    Query nameQuery = null;
    Query statusQuery = null;
    TermRangeQuery rangeQuery = null;
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (searchAdvName != null)
    {
      String text = QueryParser.escape (searchAdvName);
        try
        {
          nameParser.setAllowLeadingWildcard (true);
          nameQuery = nameParser.parse ("*"+text+"*");
          query.add (nameQuery, Occur.MUST);
          
          if (LogUtil.isDebugEnabled (AdvertisementController.class))
          {
            LogUtil.debug (AdvertisementController.class, "search", "Search advName: "
                + searchAdvName );
          }
        }
        catch (ParseException e)
        {
          e.printStackTrace();
        }
        
    }
//    if (searchAdvName != null)
//    {
//      nameQuery = new FuzzyQuery (new Term ("advName", searchAdvName.toString ()));
//      query.add (nameQuery,Occur.MUST);
//    }
    if (searchStatus != null)
    {
      statusQuery = new TermQuery (new Term ("status", searchStatus.toString ()));
      query.add (statusQuery,Occur.MUST);
    }
    if (startDateStr != null || endDateStr != null)
    {
      rangeQuery = new TermRangeQuery ("createDate", startDateStr, endDateStr, true, true);
      query.add (rangeQuery,Occur.MUST);
      
      if (LogUtil.isDebugEnabled (AdvertisementController.class))
      {
        LogUtil.debug (AdvertisementController.class, "search", "Search start date: "+startDateStr
            +" end date: "+endDateStr);
      }
    }
    if (nameQuery != null || rangeQuery != null || statusQuery != null)
    {
      return advertisementService.search (query, pageable, analyzer,filter,true);
    }
    return advertisementService.findPage (pageable,true);
  }

  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping (value = "/edit", method = RequestMethod.GET)
  public String edit (ModelMap model, Long id)
  {
    model.put ("advertisement", advertisementService.find (id));
    return "advertisement/edit";
  }

  @RequestMapping (value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add (Advertisement advertisement)
  {
    advertisement.setSystemType (SystemType.ENTERPRISE);
    advertisement.setAdType (AdType.NORMAL_AD);
    advertisementService.save (advertisement,true);
    return SUCCESS_MESSAGE;
  }

  @RequestMapping (value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update (Advertisement advertisement)
  {
    advertisementService.update (advertisement,"advImageUrl","createDate","systemType","tenantID","adType");
    return SUCCESS_MESSAGE;
  }

 

  /**
   * 删除
   */
  @RequestMapping (value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete (Long[] ids)
  {
    if (ids != null)
    {
      try
      {
        advertisementService.delete (ids);
      }
      catch (Exception e)
      {
        e.printStackTrace ();
        return Message.error ("csh.delete.fail");
      }
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 获取数据进入详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    Advertisement advertisement = advertisementService.find(id);
    model.addAttribute("advertisement", advertisement);
    return "advertisement/details";
  }
  
  @RequestMapping (value = "/uploadPhoto", method = RequestMethod.POST)
  public @ResponseBody Message uploadPhoto (
      @RequestParam ("file") MultipartFile file, Long advertisementId)
  {
    //    String filePath = fileService.upload(FileType.PROFILE_PICTURE, file, identifier);
    String filePath = fileService.saveImage (file, ImageType.ADVIMAGE);
    if (filePath != null && advertisementId != null)
    {
      Advertisement advertisement = advertisementService.find (advertisementId);
      advertisement.setAdvImageUrl (filePath);
      advertisementService.save (advertisement);
      return Message.success (filePath);
    }
    else
    {
      return ERROR_MESSAGE;
    }
  }
}
