package com.csh.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csh.entity.commonenum.CommonEnum.ImageType;
import com.csh.service.FileService;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantUserService;
import com.csh.utils.ImageUtils;

@Service("fileServiceImpl")
public class FileServiceImpl implements FileService {

  private static final String DEST_EXTENSION = "jpg";

  @Value("${ImageUploadPath}")
  private String uploadPath;
  @Value("${ProjectUploadPath}")
  private String projectUploadPath;
  @Resource(name="tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Override
  public String saveImage(MultipartFile[] multipartFile) {
    String webPath = null;
    String projectPath=projectUploadPath;
    if (multipartFile == null || multipartFile.length == 0) {
      return null;
    }
    try {
      for (MultipartFile multiFile : multipartFile) {
        String uuid = UUID.randomUUID().toString();
        String sourcePath =
            uploadPath + File.separator + "src_" + uuid + "."
                + FilenameUtils.getExtension(multiFile.getOriginalFilename());
        webPath = projectPath+
            File.separator + "src_" + uuid + "."
                + FilenameUtils.getExtension(multiFile.getOriginalFilename());
        //String storePath = uploadPath + File.separator + uuid + "." + DEST_EXTENSION;;

        File tempFile =
            new File(System.getProperty("java.io.tmpdir") + File.separator + "upload_"
                + UUID.randomUUID() + ".tmp");
        if (!tempFile.getParentFile().exists()) {
          tempFile.getParentFile().mkdirs();
        }

        multiFile.transferTo(tempFile);
        proccessImage(tempFile, sourcePath);
      }
    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return webPath;
  }

  /**
   * 处理并保存图片
   * @param tempFile
   * @param sourcePath
   * @param resizedPath
   * @param width
   * @param height
   * @param moveSource
   */
  private void proccessImage(File tempFile, String sourcePath, String resizedPath, Integer width,
      Integer height, boolean moveSource) {
    String tempPath = System.getProperty("java.io.tmpdir");
    File resizedFile =
        new File(tempPath + File.pathSeparator + "upload_" + UUID.randomUUID() + "."
            + DEST_EXTENSION);
    ImageUtils.zoom(tempFile, resizedFile, width, height);

    File destFile = new File(resizedPath);
    try {
      if (moveSource) {
        File destSrcFile = new File(sourcePath);
        FileUtils.moveFile(tempFile, destSrcFile);
      }
      FileUtils.moveFile(resizedFile, destFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 直接保存手机端图片，不处理
   * @param tempFile
   * @param sourcePath
   * @param resizedPath
   */
  private void proccessImage(File tempFile, String sourcePath) {
    try {
          File destSrcFile = new File(sourcePath);
          FileUtils.moveFile(tempFile, destSrcFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public String saveImage(MultipartFile multiFile ,ImageType imageType) {
    String webPath = null;
    String imgUploadPath ="";
    String projectPath="/upload/tenant";
    try {

      if (multiFile == null) {
        return null;
      }
      String uuid = UUID.randomUUID().toString();
      if (imageType == ImageType.CARSERVICEPICTURE) {
        imgUploadPath = uploadPath +File.separator+tenantAccountService.getCurrentTenantID ()+ File.separator + "carServicePicture";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "carServicePicture";
      }
      if (imageType == ImageType.DRIVERLICENSEPHOTO) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "driverLicensePhoto";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "driverLicensePhoto";
      }
      if (imageType == ImageType.DRIVINGLICENSEPHOTO) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "drivingLicensePhoto";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "drivingLicensePhoto";
      }
      if (imageType == ImageType.IDPHOTO) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "IDPhoto";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "IDPhoto";
      }
      if (imageType == ImageType.PHOTO) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "photo";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "photo";
      }
      String sourcePath =
          imgUploadPath + File.separator + "src_" + uuid + "."
              + FilenameUtils.getExtension(multiFile.getOriginalFilename());
      webPath =projectPath+File.separator+"src_" + uuid + "."
              + FilenameUtils.getExtension(multiFile.getOriginalFilename());
      //String storePath = imgUploadPath + File.separator + uuid + "." + DEST_EXTENSION;;
      File tempFile =
          new File(System.getProperty("java.io.tmpdir") + File.separator + "upload_"
              + UUID.randomUUID() + ".tmp");
      if (!tempFile.getParentFile().exists()) {
        tempFile.getParentFile().mkdirs();
      }
      multiFile.transferTo(tempFile);
      proccessImage(tempFile, sourcePath);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return webPath;
  }

}

