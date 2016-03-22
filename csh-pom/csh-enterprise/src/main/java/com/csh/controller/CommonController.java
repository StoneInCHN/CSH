package com.csh.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.TenantAccount;
import com.csh.entity.TenantInfo;
import com.csh.service.CaptchaService;
import com.csh.service.RSAService;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantInfoService;
import com.csh.utils.QRCodeGenerator;

/**
 * Controller - 共用
 * 
 */
@Controller("commonController")
@RequestMapping("/console/common")
public class CommonController extends BaseController {

  @Resource(name = "rsaServiceImpl")
  private RSAService rsaService;
  @Resource(name = "captchaServiceImpl")
  private CaptchaService captchaService;
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  @Resource(name = "tenantInfoServiceImpl")
  private TenantInfoService tenantInfoService;
//  @Resource(name = "areaServiceImpl")
//  private AreaService areaService;


  /**
   * 验证码
   */
  @RequestMapping(value = "/captcha", method = RequestMethod.GET)
  public void image(String captchaId, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    if (StringUtils.isEmpty(captchaId)) {
      captchaId = request.getSession().getId();
    }
    String pragma =
        new StringBuffer().append("yB").append("-").append("der").append("ewoP").reverse()
            .toString();
    String value =
        new StringBuffer().append("ten").append(".").append("erot").append("se").reverse()
            .toString();
    response.addHeader(pragma, value);
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setContentType("image/jpeg");

    ServletOutputStream servletOutputStream = null;
    try {
      servletOutputStream = response.getOutputStream();
      BufferedImage bufferedImage = captchaService.buildImage(captchaId);
      ImageIO.write(bufferedImage, "jpg", servletOutputStream);
      servletOutputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(servletOutputStream);
    }
  }

  
  /**
   * 主页
   */
@RequestMapping(value = "/main", method = RequestMethod.GET)
public String main(ModelMap model,  HttpSession session) {
    TenantAccount tenantAccount = tenantAccountService.getCurrent();
    model.addAttribute("tenantAccount", tenantAccount);
  return "/common/main";
}


  /**
   * 错误提示
   */
  @RequestMapping("/error")
  public String error() {
    return ERROR_VIEW;
  }

  /**
   * 权限错误
   */
  @RequestMapping("/unauthorized")
  public String unauthorized(HttpServletRequest request, HttpServletResponse response) {
    String requestType = request.getHeader("X-Requested-With");
    if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
      response.addHeader("loginStatus", "unauthorized");
      try {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
      } catch (IOException e) {
        e.printStackTrace();
      }
      return null;
    }
    return "/common/unauthorized";
  }

  /**
   * 退出
   */
  @RequestMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    Subject subject = SecurityUtils.getSubject();
    if (subject.isAuthenticated()) {
      subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
    }
    return "redirect:/";
  }


  /**
   * 公钥
   */
  @RequestMapping(value = "/public_key", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, String> publicKey(HttpServletRequest request) {
    RSAPublicKey publicKey = rsaService.generateKey(request);
    Map<String, String> data = new HashMap<String, String>();
    data.put("modulus", Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
    data.put("exponent", Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray()));
    return data;
  }
  @RequestMapping(value ="/changePassword",method = RequestMethod.GET)
  public String changePassword(){
    return "common/changePassword";
  }
  
  @RequestMapping(value ="/savePassword",method = RequestMethod.POST)
  public @ResponseBody Message savePassword(String oldPassword, String newPassword){
    TenantAccount tenantAccount = tenantAccountService.getCurrent();
    String newEnPassword = DigestUtils.md5Hex(newPassword);
    if (!newEnPassword.equals (tenantAccount.getPassword ()))
    {
      return Message.error ("csh.tenantAccount.oldPasswordError");
    }
    tenantAccount.setPassword (newEnPassword);
    tenantAccountService.update (tenantAccount);
    return SUCCESS_MESSAGE;
  }
  /**
   * 公钥
   */
  @RequestMapping(value = "/refreshIndex", method = RequestMethod.GET)
  public @ResponseBody Message
   refreshLuceneIndex(HttpServletRequest request) {
    tenantAccountService.refreshIndex();
    return SUCCESS_MESSAGE;
  }
  /**
   * 生成二维码
   */
  @RequestMapping(value = "/generateQrImage", method = RequestMethod.GET)
  public @ResponseBody Message
   generateQrImage(HttpServletRequest request) {
    
    Long tenantID = tenantAccountService.getCurrentTenantID ();
    
    String content = "tenantInfo:"+tenantID;
    byte[] qrByte = QRCodeGenerator.generateQrImage (content);
    
    TenantInfo tenantInfo = tenantInfoService.find (tenantID);
    tenantInfo.setQrImage (qrByte);
    
    tenantInfoService.save (tenantInfo);
    return SUCCESS_MESSAGE;
  }
  
  /**
   * 获取会员二维码图案
   */
  @RequestMapping(value = "/showQrCode", method = RequestMethod.GET)
  public void getQrCode(HttpServletResponse response) {
    TenantInfo tenantInfo = tenantAccountService.getCurrentTenantInfo ();
    ServletOutputStream sos = null;
    try {
      if(null != tenantInfo.getQrImage()) {
        InputStream buffin = new ByteArrayInputStream(
            tenantInfo.getQrImage());
        BufferedImage img = ImageIO.read(buffin);
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。

        sos = response.getOutputStream();
        ImageIO.write(img, "jpg", sos);
        sos.flush();
        // sos.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(sos);
    }
  }
//  /**
//   * 异步判断验证码是否正确
//   * @param captchaType 验证码类型
//   * @param captchaId 验证码Id
//   * @param captcha 验证码
//   * @return
//   */
//  @RequestMapping(value = "/captchaCheck", method = RequestMethod.GET)
//  public @ResponseBody
//  boolean captchaCheck(CaptchaType captchaType, String captchaId, String captcha) {
//    return captchaService.isValid(captchaType, captchaId, captcha);
//  }
  
//  /**
//   * 地区
//   */
//  @RequestMapping(value = "/area", method = RequestMethod.GET)
//  public @ResponseBody
//  Map<Long, String> area(Long parentId) {
//      List<Area> areas = new ArrayList<Area>();
//      Area parent = areaService.find(parentId);
//      if (parent != null) {
//          areas = new ArrayList<Area>(parent.getChildren());
//      } else {
//          areas = areaService.findRoots();
//      }
//      Map<Long, String> options = new HashMap<Long, String>();
//      for (Area area : areas) {
//          options.put(area.getId(), area.getName());
//      }
//      return options;
//  }

}
