package com.csh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csh.aspect.URLValidCheck;
import com.csh.aspect.URLValidCheck.UrlMethodType;
import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.ApkVersion;
import com.csh.entity.commonenum.CommonEnum.FileType;
import com.csh.framework.entity.BaseEntity.Save;
import com.csh.framework.paging.Pageable;
import com.csh.service.APKVersionService;
import com.csh.service.FileService;



@Controller
@RequestMapping("/console/apkVersion")
public class APKVersionController extends BaseController {

  @Resource(name = "apkVersionServiceImpl")
  private APKVersionService apkVersionService;

  @Resource(name = "fileServiceImpl")
  private FileService fileService;

  /**
   * 检查版本号是否存在
   */
  @RequestMapping(value = "/checkVersion", method = RequestMethod.GET)
  public @ResponseBody boolean checkVersion(String versionName, Long id) {
    if (StringUtils.isEmpty(versionName)) {
      return false;
    }
    return !apkVersionService.versionExists(versionName, id);
  }

  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    return "/version/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(ApkVersion apkVersion) {
    if (!isValid(apkVersion, Save.class)) {
      return ERROR_VIEW;
    }
    if (apkVersion.getFile().getSize() < 1) {
      return ERROR_VIEW;
    } else {
      if (!fileService.isValid(FileType.file, apkVersion.getFile())) {
        return ERROR_VIEW;
      } else {
        String apkPath = fileService.uploadApk(apkVersion.getFile());
        apkVersion.setApkPath(apkPath);
        apkVersionService.save(apkVersion);
        return "redirect:list.jhtml";
      }

    }

  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("apkVersion", apkVersionService.find(id));
    return "/version/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @URLValidCheck(urlMethod = UrlMethodType.UPDATE)
  public String update(HttpServletRequest request, RedirectAttributes redirectAttributes,
      ApkVersion apkVersion) {
    if (!isValid(apkVersion)) {
      return ERROR_VIEW;
    }
    if (apkVersion.getFile().getSize() > 0) {
      String apkPath = fileService.upload(FileType.file, apkVersion.getFile(), true);
      apkVersion.setApkPath(apkPath);
    }
    apkVersionService.update(apkVersion);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  @URLValidCheck(urlMethod = UrlMethodType.LIST)
  public String list(HttpServletRequest request, Pageable pageable, ModelMap model) {
    model.addAttribute("page", apkVersionService.findPage(pageable));
    return "/version/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids.length > apkVersionService.count()) {
      return Message.error("admin.common.deleteAllNotAllowed");
    }
    apkVersionService.delete(ids);
    return SUCCESS_MESSAGE;
  }

}
