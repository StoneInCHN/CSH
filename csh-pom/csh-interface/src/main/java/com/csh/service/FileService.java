package com.csh.service;

import org.springframework.web.multipart.MultipartFile;
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


}
