package com.csh.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.commonenum.CommonEnum.ImageType;
import com.csh.service.FileService;
import com.csh.utils.JsonUtils;

/**
 * 文件上传
 * 
 * @author shijun
 *
 */
@Controller("fileController")
@RequestMapping("console/file")
public class FileController extends BaseController {

  @Resource(name = "fileServiceImpl")
  private FileService fileService;
  

  @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
   public @ResponseBody Message uploadPhoto(@RequestParam("file") MultipartFile file,ImageType imageType) { 
    String filePath = fileService.saveImage (file,imageType);
    if(filePath ==null){
      filePath ="";
    }
    return Message.success(filePath);
  }
  @RequestMapping(value = "/kindEditorUploadPicutre", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
  public @ResponseBody Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletResponse response,ImageType imageType) {
    Map<String, Object> data = new HashMap<String, Object>();
//    if (!fileService.isValid(fileType, file)) {
//      data.put("error",1);
//      data.put("message", Message.warn("admin.upload.invalid"));
//    } else {
      String url = fileService.saveImage(file,imageType);
      if (url == null) {
        data.put("error",1);
        data.put("width", "100%");
        data.put("message", Message.warn("admin.upload.error"));
      } else {
        data.put("error",0);
        data.put("message", Message.Type.success);
        data.put("url", url);
        data.put("width", "100%");
      }
//    }
      try{
        response.setContentType("text/html; charset=UTF-8");
        JsonUtils.writeValue (response.getWriter (), data);
      }
      catch (IOException e){
        e.printStackTrace();
      }
      return data;
  }
}
