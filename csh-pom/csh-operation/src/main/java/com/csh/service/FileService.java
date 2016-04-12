package com.csh.service;

import org.springframework.web.multipart.MultipartFile;

import com.csh.beans.Setting.ImageType;
import com.csh.entity.commonenum.CommonEnum.FileType;

public interface FileService {
  /**
   * 上传头像图片
   * 
   * @param multipartFile
   * @return
   */
  public String saveImage(MultipartFile[] multipartFile);

  public String saveImage(MultipartFile multipartFile, ImageType imageType);

  /**
   * 文件验证
   * 
   * @param fileType
   *            文件类型
   * @param multipartFile
   *            上传文件
   * @return 文件验证是否通过
   */
  boolean isValid(FileType fileType, MultipartFile multipartFile);
  
  /**
   * 文件上传
   * 
   * @param fileType 文件类型
   * @param multipartFile 上传文件
   * @param async 是否异步
   * @return 访问URL
   */
  String upload(FileType fileType, MultipartFile multipartFile, boolean async);

  /**
   * 文件上传(异步)
   * 
   * @param fileType 文件类型
   * @param multipartFile 上传文件
   * @return 访问URL
   */
  String upload(FileType fileType, MultipartFile multipartFile);

}
