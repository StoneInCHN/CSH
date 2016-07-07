package com.csh.service;

import org.springframework.web.multipart.MultipartFile;

import com.csh.entity.commonenum.CommonEnum.FileType;
import com.csh.entity.commonenum.CommonEnum.ImageType;


/**
 * 上传图片
 * @author sujinxuan
 *
 */
public interface FileService {
	/**
	 * 批量上传图片
	 * 
	 * @param multipartFile
	 * @return
	 */
	public String saveImage(MultipartFile[] multipartFile);
	/**
	 * 按类型上传图片
	 * @param multipartFile
	 * @param imageType
	 * @return
	 */
	public String saveImage(MultipartFile multipartFile ,ImageType imageType);

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
}
