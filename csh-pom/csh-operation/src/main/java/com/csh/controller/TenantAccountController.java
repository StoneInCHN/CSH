package com.csh.controller;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csh.entity.TenantAccount;
import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.framework.paging.Pageable;
import com.csh.service.TenantAccountService;
import com.csh.utils.CommonUtils;

@RequestMapping("console/tenantAccount")
@Controller("tenantAccountController")
public class TenantAccountController extends BaseController{
	
	@Resource(name="tenantAccountServiceImpl")
	private TenantAccountService tenantAccountService;
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "/tenantAccount/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(TenantAccount tenantAccount) {
		if (!isValid(tenantAccount)) {
			return ERROR_VIEW;
		}
		String password = CommonUtils.randomPwd();
		tenantAccount.setPassword(DigestUtils.md5Hex(password));
		tenantAccountService.save(tenantAccount);
		//发短信告知账户和密码
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		model.addAttribute("tenantAccount", tenantAccountService.find(id));
		return "/tenantAccount/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(TenantAccount tenantAccount, RedirectAttributes redirectAttributes) {
		if (!isValid(tenantAccount)) {
			return ERROR_VIEW;
		}
		
		TenantAccount temp = tenantAccountService.find(id);
		temp.setAccoutStatus(tenantAccount.getAccoutStatus());
		temp.setRealName(tenantAccount.getRealName());
		tenantAccountService.update(temp);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", tenantAccountService.findPage(pageable));
		return "/tenantAccount/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		if (ids != null) {
			tenantAccountService.delete(ids);
		}
		return SUCCESS_MESSAGE;
	}

	
}
