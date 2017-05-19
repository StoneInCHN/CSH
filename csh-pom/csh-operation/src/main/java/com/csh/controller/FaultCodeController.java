package com.csh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.beans.Message.Type;
import com.csh.controller.base.BaseController;
import com.csh.entity.FaultCode;
import com.csh.entity.commonenum.CommonEnum.MessageType;
import com.csh.framework.paging.Pageable;
import com.csh.service.FaultCodeService;
import com.csh.utils.FaultcodeUtils;

@Controller("faultCodeController")
@RequestMapping("console/faultCode")
public class FaultCodeController extends BaseController {


  @Resource(name = "faultCodeServiceImpl")
  private FaultCodeService faultCodeService;


  /**
   * 添加
   */
  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String add(ModelMap model) {
    return "/faultCode/add";
  }

  /**
   * 保存
   */
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String save(FaultCode faultCode) {
    faultCodeService.save(faultCode);
    return "redirect:list.jhtml";
  }

  /**
   * 编辑
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(Long id, ModelMap model) {
    model.addAttribute("faultCode", faultCodeService.find(id));
    return "/faultCode/edit";
  }

  /**
   * 更新
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public String update(FaultCode faultCode) {
    faultCodeService.update(faultCode);
    return "redirect:list.jhtml";
  }

  /**
   * 列表
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String list(Pageable pageable, ModelMap model) {
    model.addAttribute("page", faultCodeService.findPage(pageable));
    return "/faultCode/list";
  }

  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      faultCodeService.delete(ids);
    }
    return SUCCESS_MESSAGE;
  }
  /**
   * 数据导入
   */ 
  @RequestMapping(value = "/dataImport", method = RequestMethod.GET)
  public @ResponseBody Message dataImport() {
    List<FaultCode> lists = FaultcodeUtils.readCode();
    faultCodeService.save(lists);
    return SUCCESS_MESSAGE;
  }
  /**
   * 数据导出
   */
  @RequestMapping(value = "/dataExport", method = {RequestMethod.GET,RequestMethod.POST})
  public void dataExport(HttpServletResponse response) {
    List<FaultCode> lists = faultCodeService.findAll();
    //List<FaultCode> lists = faultCodeService.findList(10, null, null);
    if (lists != null && lists.size() > 0) {
      String title = "Fault Code List"; // 工作簿标题，同时也是excel文件名前缀
      String[] headers = {"code", "rangeScope", "defineCh", "defineEn", "scope", "info"}; // 需要导出的字段
      String[] headersName = {"故障码", "适用车型", "中文定义", "英文定义", "范畴", "背景知识"}; // 字段对应列的列名
      List<Map<String, String>> mapList = FaultcodeUtils.prepareExportData(lists);
      if (mapList.size() > 0) {
        exportListToExcel(response, mapList, title, headers, headersName);
      }
    }
  }
  
}
