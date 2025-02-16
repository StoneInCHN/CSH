package com.csh.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.Admin;
import com.csh.service.AdminService;


/**
 * Controller -- Account
 * @author sujinxuan
 *
 */
@Controller("accountController")
@RequestMapping("/console/account")
public class AccountController extends BaseController {

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	/**
	 * 编辑个人信息页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
	public String edit(ModelMap model) {
		model.addAttribute("admin",adminService.getCurrent());
		return "/account/edit";
	}
	

	/**
	 * 更新个人信息
	 * @param password
	 * @param nickName
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public @ResponseBody Message update(String password,String name,HttpSession session) {
    	  Admin admin = adminService.getCurrent();
    	  if(!StringUtils.isEmpty(password)){
    	    admin.setPassword(DigestUtils.md5Hex(password));
    	  }
    	  admin.setName(name);
    	  adminService.update(admin);
    	  return SUCCESS_MESSAGE;
	}

    
}