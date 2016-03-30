package com.csh.controller;

import javax.annotation.Resource;

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
  
}
