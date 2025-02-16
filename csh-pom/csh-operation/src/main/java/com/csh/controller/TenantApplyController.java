package com.csh.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.beans.Setting.ImageType;
import com.csh.controller.base.BaseController;
import com.csh.entity.Distributor;
import com.csh.entity.TenantApply;
import com.csh.entity.commonenum.CommonEnum.ApplyStatus;
import com.csh.framework.filter.Filter;
import com.csh.framework.filter.Filter.Operator;
import com.csh.framework.paging.Pageable;
import com.csh.service.AreaService;
import com.csh.service.DistributorService;
import com.csh.service.FileService;
import com.csh.service.MailService;
import com.csh.service.TenantApplyService;
import com.csh.service.TenantInfoService;
import com.csh.service.VersionConfigService;
import com.csh.utils.SpringUtils;
import com.csh.utils.UcpaasUtil;


@RequestMapping("console/apply")
@Controller("tenantApplyController")
public class TenantApplyController extends BaseController {


  @Resource(name = "tenantApplyServiceImpl")
  private TenantApplyService tenantApplyService;

  @Resource(name = "fileServiceImpl")
  private FileService fileService;

  @Resource(name = "areaServiceImpl")
  private AreaService areaService;

  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;

  @Resource(name = "versionConfigServiceImpl")
  private VersionConfigService versionConfigService;

  @Resource(name = "distributorServiceImpl")
  private DistributorService distributorService;

  @Resource(name = "mailServiceImpl")
  private MailService mailService;

  @RequestMapping(value = "/submit", method = RequestMethod.POST)
  public String submit(TenantApply tenantApply, Long areaId, ModelMap map) {
    if (!isValid(tenantApply)) {
      map.addAttribute("content", false);
    } else {
      String license = "";
      if (tenantApply.getLicenseFile() != null) {
        license = fileService.saveImage(tenantApply.getLicenseFile(), ImageType.LICENSE);
      }
      tenantApply.setLicense(license);
      String photo = "";
      if (tenantApply.getPhotoFile() != null) {
        photo = fileService.saveImage(tenantApply.getPhotoFile(), ImageType.STOREPICTURE);
      }
      tenantApply.setPhoto(photo);
      tenantApply.setApplyStatus(ApplyStatus.AUDIT_WAITING);

      tenantApply.setArea(areaService.find(areaId));
      tenantApplyService.save(tenantApply);
      map.addAttribute("content", true);
    }
    return "/apply/main";
  }

  @RequestMapping(value = "/apply", method = RequestMethod.POST)
  public @ResponseBody Message apply(TenantApply tenantApply, Long areaId) {
    if (!isValid(tenantApply)) {
      return ERROR_MESSAGE;
    } else {
      String license = "";
      if (tenantApply.getLicenseFile() != null) {
        license = fileService.saveImage(tenantApply.getLicenseFile(), ImageType.LICENSE);
      }
      tenantApply.setLicense(license);
      String photo = "";
      if (tenantApply.getPhotoFile() != null) {
        photo = fileService.saveImage(tenantApply.getPhotoFile(), ImageType.STOREPICTURE);
      }
      tenantApply.setPhoto(photo);
      tenantApply.setApplyStatus(ApplyStatus.AUDIT_WAITING);

      tenantApply.setArea(areaService.find(areaId));
      tenantApplyService.save(tenantApply);
      return SUCCESS_MESSAGE;
    }
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(ApplyStatus applyStatus, Pageable pageable, ModelMap model) {
    if (applyStatus != null) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter();
      filter.setProperty("applyStatus");
      filter.setValue(applyStatus);
      filter.setOperator(Operator.eq);
      filters.add(filter);
      pageable.setFilters(filters);
    }
    model.addAttribute("page", tenantApplyService.findPage(pageable));
    model.addAttribute("applyStatus", applyStatus);
    return "/apply/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids.length >= tenantApplyService.count()) {
      return Message.error("csh.common.deleteAllNotAllowed");
    }
    tenantApplyService.delete(ids);
    return SUCCESS_MESSAGE;
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("apply", tenantApplyService.find(id));
    model.addAttribute("versions", versionConfigService.findAll());
    return "/apply/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(TenantApply tenantApply, Long distributorId) {
    TenantApply applyTemp = tenantApplyService.find(tenantApply.getId());
    applyTemp.setApplyStatus(tenantApply.getApplyStatus());
    applyTemp.setNotes(tenantApply.getNotes());
    applyTemp.setVersionConfig(tenantApply.getVersionConfig());
    if (ApplyStatus.AUDIT_PASSED.equals(tenantApply.getApplyStatus())) {
      Distributor distributor = distributorService.find(distributorId);
      tenantApplyService.auditPassed(applyTemp, distributor);
    } else {
      tenantApplyService.update(applyTemp);
      // send email
      String subject = SpringUtils.getMessage("csh.tenantApply.audit.failed.subject");
      String message =
          SpringUtils.getMessage("csh.tenantApply.audit.failed.message", applyTemp.getTenantName(),
              applyTemp.getNotes());
      mailService.send(applyTemp.getEmail(), subject, message);
      // send msg
      UcpaasUtil.SendApplyFailureBySms(applyTemp.getContactPhone(), applyTemp.getTenantName(),
          applyTemp.getNotes());
    }

    return SUCCESS_MESSAGE;
  }

  /**
   * 查看
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(Long id, ModelMap model) {
    model.addAttribute("apply", tenantApplyService.find(id));
    return "/apply/details";
  }

  @RequestMapping(value = "/selectDistributor", method = RequestMethod.GET)
  public String selectDistributor(ModelMap modelMap, Pageable pageable) {
    pageable.setPageSize(5);;
    modelMap.addAttribute("page", distributorService.findPage(pageable));
    return "/apply/selectDistributor";
  }

}
