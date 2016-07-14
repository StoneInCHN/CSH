package com.csh.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csh.beans.Setting;
import com.csh.entity.commonenum.CommonEnum.FileType;
import com.csh.entity.commonenum.CommonEnum.ImageType;
import com.csh.service.FileService;
import com.csh.service.TenantAccountService;
import com.csh.service.TenantUserService;
import com.csh.utils.ImageUtils;
import com.csh.utils.SettingUtils;

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
//    String projectPath="/upload/tenant";
    String projectPath=File.separator + "upload"+File.separator+"tenant";
    try {

      if (multiFile == null) {
        return null;
      }
      String uuid = UUID.randomUUID().toString();
      if (imageType == ImageType.CARSERVICEPICTURE) {
        imgUploadPath = uploadPath +File.separator+tenantAccountService.getCurrentTenantID ()+ File.separator + "carServicePicture";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "carServicePicture";
      }
      else if (imageType == ImageType.DRIVERLICENSEPHOTO) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "driverLicensePhoto";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "driverLicensePhoto";
      }
      else if (imageType == ImageType.DRIVINGLICENSEPHOTO) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "drivingLicensePhoto";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "drivingLicensePhoto";
      }
      else if (imageType == ImageType.IDPHOTO) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "IDPhoto";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "IDPhoto";
      }
      else if (imageType == ImageType.PHOTO) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "photo";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "photo";
      }
      else if (imageType == ImageType.ADVIMAGE) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "advImage";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "advImage";
      }
      else if (imageType == ImageType.SERVICEDESCIMAGE) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "serviceDescImage";
        projectPath=projectUploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "serviceDescImage";
      }
      else if (imageType == ImageType.COUPONREMARKIMAGE) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "couponRemarkImage";
        projectPath=projectUploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "couponRemarkImage";
      }
      else if (imageType == ImageType.PRODUCTBRANDIMAGE) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "productBrandImage";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "productBrandImage";
      }
      else if (imageType == ImageType.TENANTIMAGE) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "tenantImage";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "tenantImage";
      }
      else if (imageType == ImageType.SHIPPINGMETHODICON) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "shippingMethodIcon";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "shippingMethodIcon";
      }
      else if (imageType == ImageType.PRODUCTIMAGE) {
        imgUploadPath = uploadPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "productImage";
        projectPath=projectPath+File.separator+tenantAccountService.getCurrentTenantID () + File.separator + "productImage";
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
  public boolean isValid(FileType fileType, MultipartFile multipartFile) {
    if (multipartFile == null) {
      return false;
    }
    Setting setting = SettingUtils.get();
    String[] uploadExtensions = null;
    if (fileType == FileType.file) {
      uploadExtensions = setting.getUploadFileExtensions();
    } else if (fileType == FileType.image) {
      uploadExtensions = setting.getUploadImageExtensions();
    }
    if (!ArrayUtils.isEmpty(uploadExtensions)) {
      return FilenameUtils.isExtension(multipartFile.getOriginalFilename().toLowerCase(),
          uploadExtensions);
    }
    return false;
  }
}

