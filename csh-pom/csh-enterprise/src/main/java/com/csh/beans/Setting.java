package com.csh.beans;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 系统设置
 */
public class Setting implements Serializable {

    private static final long serialVersionUID = -1478999889661796840L;

    /**
     * 小数位精确方式
     */
    public enum RoundType {

        /** 四舍五入 */
        roundHalfUp,

        /** 向上取整 */
        roundUp,

        /** 向下取整 */
        roundDown
    }
    
    /**
     * 验证码类型
     */
    public enum CaptchaType {

        /** 会员登录 */
        LOGIN,

        /** 其它 */
        OTHER
    }


    /** 缓存名称 */
    public static final String CACHE_NAME = "setting";

    /** 缓存Key */
    public static final Integer CACHE_KEY = 0;

    /** 分隔符 */
    private static final String SEPARATOR = ",";
    
    /** 验证码类型 */
    private CaptchaType[] captchaTypes;

    /** 网站网址 */
    private String siteUrl;

    /** 安全密匙有效时间 */
    private Integer safeKeyExpiryTime;

    /** 上传文件最大限制 */
    private Integer uploadMaxSize;

    /** 允许上传图片扩展名 */
    private String uploadImageExtension;

    /** 允许上传Flash扩展名 */
    private String uploadFlashExtension;

    /** 允许上传媒体扩展名 */
    private String uploadMediaExtension;

    /** 允许上传文件扩展名 */
    private String uploadFileExtension;

    /** 图片上传路径 */
    private String imageUploadPath;
    
    /** 图片上传路径 */
    private String profilePictureUploadPath;

    /**
     * 通知图片上传路径
     */
    private String notifyPictureUploadPath;
    /** 相册上传路径 */
    private String albumUploadPath;
    
    /**
     * 二维码中小图片
     */
    private String qrImageUrl;
    /**
     * 消息推送接口url 
     */
    private String msgPushUrl;
    
    /** 短信服务平台地址 */
    private String ucpaasUrl;

    /** 短信平台AccountId */
    private String ucpaasSid;

    /** 短信平台Token */
    private String ucpaasToken;

    /** 短信平台软件版本 */
    private String ucpaasVersion;

    /** 短信平台APPID */
    private String ucpaasAppId;

    /** 短信平台软件版本 */
    private String ucpaasTemplate;

    /** 短信平台语音回拨号码 */
    private String ucpaasCallDisplay;
    
    private String ucpaasAccountTemplate;
    /**
     * odb Servier url
     */
    private String obdServiceUrl;
    /**
     * 上传图片大小的最大值
     */
    private Long imageMaxSize;
    
    /**
     * 百度API坐标转换
     */
    private String convertMapUrl;

    /**
     * 百度API地址转坐标
     */
    private String convertAddressUrl;

    private String convertMutipleMapUrl;
    /**
     * 百度地图api key
     */
    private String mapAk;
    /**
     * 获取相册上传路径
     * 
     * @return
     */
    @NotEmpty
    @Length(max = 200)
    public String getAlbumUploadPath() {
      return albumUploadPath;
    }

    public void setAlbumUploadPath(String albumUploadPath) {
      if (albumUploadPath != null) {
        if (!albumUploadPath.startsWith("/")) {
          albumUploadPath = "/" + albumUploadPath;
        }
        if (!albumUploadPath.endsWith("/")) {
          albumUploadPath += "/";
        }
    }
      this.albumUploadPath = albumUploadPath;
    }

    /** Flash上传路径 */
    private String flashUploadPath;

    /** 媒体上传路径 */
    private String mediaUploadPath;

    /** 文件上传路径 */
    private String fileUploadPath;

    /** 发件人邮箱 */
    private String smtpFromMail;

    /** SMTP服务器地址 */
    private String smtpHost;

    /** SMTP服务器端口 */
    private Integer smtpPort;

    /** SMTP用户名 */
    private String smtpUsername;

    /** SMTP密码 */
    private String smtpPassword;

    /** Cookie路径 */
    private String cookiePath;

    /** Cookie作用域 */
    private String cookieDomain;
    
    /**
     * 结算周期
     */
    private Integer clearingRecordCycle;

    /**
     * 获取网站网址
     * 
     * @return 网站网址
     */
    @NotEmpty
    @Length(max = 200)
    public String getSiteUrl() {
        return siteUrl;
    }

    /**
     * 设置网站网址
     * 
     * @param siteUrl
     *            网站网址
     */
    public void setSiteUrl(String siteUrl) {
        this.siteUrl = StringUtils.removeEnd(siteUrl, "/");
    }

    /**
     * 获取安全密匙有效时间
     * 
     * @return 安全密匙有效时间
     */
    @NotNull
    @Min(0)
    public Integer getSafeKeyExpiryTime() {
        return safeKeyExpiryTime;
    }

    /**
     * 设置安全密匙有效时间
     * 
     * @param safeKeyExpiryTime
     *            安全密匙有效时间
     */
    public void setSafeKeyExpiryTime(Integer safeKeyExpiryTime) {
        this.safeKeyExpiryTime = safeKeyExpiryTime;
    }

    /**
     * 获取上传文件最大限制
     * 
     * @return 上传文件最大限制
     */
    @NotNull
    @Min(0)
    public Integer getUploadMaxSize() {
        return uploadMaxSize;
    }

    /**
     * 设置上传文件最大限制
     * 
     * @param uploadMaxSize
     *            上传文件最大限制
     */
    public void setUploadMaxSize(Integer uploadMaxSize) {
        this.uploadMaxSize = uploadMaxSize;
    }

    /**
     * 获取允许上传图片扩展名
     * 
     * @return 允许上传图片扩展名
     */
    @Length(max = 200)
    public String getUploadImageExtension() {
        return uploadImageExtension;
    }

    /**
     * 设置允许上传图片扩展名
     * 
     * @param uploadImageExtension
     *            允许上传图片扩展名
     */
    public void setUploadImageExtension(String uploadImageExtension) {
        if (uploadImageExtension != null) {
            uploadImageExtension = uploadImageExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        }
        this.uploadImageExtension = uploadImageExtension;
    }

    /**
     * 获取允许上传Flash扩展名
     * 
     * @return 允许上传Flash扩展名
     */
    @Length(max = 200)
    public String getUploadFlashExtension() {
        return uploadFlashExtension;
    }

    /**
     * 设置允许上传Flash扩展名
     * 
     * @param uploadFlashExtension
     *            允许上传Flash扩展名
     */
    public void setUploadFlashExtension(String uploadFlashExtension) {
        if (uploadFlashExtension != null) {
            uploadFlashExtension = uploadFlashExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        }
        this.uploadFlashExtension = uploadFlashExtension;
    }

    /**
     * 获取允许上传媒体扩展名
     * 
     * @return 允许上传媒体扩展名
     */
    @Length(max = 200)
    public String getUploadMediaExtension() {
        return uploadMediaExtension;
    }

    /**
     * 设置允许上传媒体扩展名
     * 
     * @param uploadMediaExtension
     *            允许上传媒体扩展名
     */
    public void setUploadMediaExtension(String uploadMediaExtension) {
        if (uploadMediaExtension != null) {
            uploadMediaExtension = uploadMediaExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        }
        this.uploadMediaExtension = uploadMediaExtension;
    }

    /**
     * 获取允许上传文件扩展名
     * 
     * @return 允许上传文件扩展名
     */
    @Length(max = 200)
    public String getUploadFileExtension() {
        return uploadFileExtension;
    }

    /**
     * 设置允许上传文件扩展名
     * 
     * @param uploadFileExtension
     *            允许上传文件扩展名
     */
    public void setUploadFileExtension(String uploadFileExtension) {
        if (uploadFileExtension != null) {
            uploadFileExtension = uploadFileExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        }
        this.uploadFileExtension = uploadFileExtension;
    }

    /**
     * 获取图片上传路径
     * 
     * @return 图片上传路径
     */
    @NotEmpty
    @Length(max = 200)
    public String getImageUploadPath() {
        return imageUploadPath;
    }

    /**
     * 获取通知图片上传路径
     * @return
     */
    @NotEmpty
    @Length(max = 200)
    public String getNotifyPictureUploadPath ()
    {
      return notifyPictureUploadPath;
    }

    public void setNotifyPictureUploadPath (String notifyPictureUploadPath)
    {
      this.notifyPictureUploadPath = notifyPictureUploadPath;
    }

    /**
     * 设置图片上传路径
     * 
     * @param imageUploadPath
     *            图片上传路径
     */
    public void setImageUploadPath(String imageUploadPath) {
        if (imageUploadPath != null) {
            if (!imageUploadPath.startsWith("/")) {
                imageUploadPath = "/" + imageUploadPath;
            }
            if (!imageUploadPath.endsWith("/")) {
                imageUploadPath += "/";
            }
        }
        this.imageUploadPath = imageUploadPath;
    }

    /**
     * 获取基础资料照路径
     * 
     * @return
     */
    @NotEmpty
    @Length(max = 200)
    public String getProfilePictureUploadPath() {
      return profilePictureUploadPath;
    }

    public void setProfilePictureUploadPath(String profilePictureUploadPath) {
      if (profilePictureUploadPath != null) {
        if (!profilePictureUploadPath.startsWith("/")) {
          profilePictureUploadPath = "/" + profilePictureUploadPath;
        }
        if (!profilePictureUploadPath.endsWith("/")) {
          profilePictureUploadPath += "/";
        }
    }
    this.profilePictureUploadPath = profilePictureUploadPath;
    }

    /**
     * 获取Flash上传路径
     * 
     * @return Flash上传路径
     */
    @NotEmpty
    @Length(max = 200)
    public String getFlashUploadPath() {
        return flashUploadPath;
    }

    /**
     * 设置Flash上传路径
     * 
     * @param flashUploadPath
     *            Flash上传路径
     */
    public void setFlashUploadPath(String flashUploadPath) {
        if (flashUploadPath != null) {
            if (!flashUploadPath.startsWith("/")) {
                flashUploadPath = "/" + flashUploadPath;
            }
            if (!flashUploadPath.endsWith("/")) {
                flashUploadPath += "/";
            }
        }
        this.flashUploadPath = flashUploadPath;
    }

    /**
     * 获取媒体上传路径
     * 
     * @return 媒体上传路径
     */
    @NotEmpty
    @Length(max = 200)
    public String getMediaUploadPath() {
        return mediaUploadPath;
    }

    /**
     * 设置媒体上传路径
     * 
     * @param mediaUploadPath
     *            媒体上传路径
     */
    public void setMediaUploadPath(String mediaUploadPath) {
        if (mediaUploadPath != null) {
            if (!mediaUploadPath.startsWith("/")) {
                mediaUploadPath = "/" + mediaUploadPath;
            }
            if (!mediaUploadPath.endsWith("/")) {
                mediaUploadPath += "/";
            }
        }
        this.mediaUploadPath = mediaUploadPath;
    }

    /**
     * 获取文件上传路径
     * 
     * @return 文件上传路径
     */
    @NotEmpty
    @Length(max = 200)
    public String getFileUploadPath() {
        return fileUploadPath;
    }

    /**
     * 设置文件上传路径
     * 
     * @param fileUploadPath
     *            文件上传路径
     */
    public void setFileUploadPath(String fileUploadPath) {
        if (fileUploadPath != null) {
            if (!fileUploadPath.startsWith("/")) {
                fileUploadPath = "/" + fileUploadPath;
            }
            if (!fileUploadPath.endsWith("/")) {
                fileUploadPath += "/";
            }
        }
        this.fileUploadPath = fileUploadPath;
    }

    /**
     * 获取发件人邮箱
     * 
     * @return 发件人邮箱
     */
    @NotEmpty
    @Email
    @Length(max = 200)
    public String getSmtpFromMail() {
        return smtpFromMail;
    }

    /**
     * 设置发件人邮箱
     * 
     * @param smtpFromMail
     *            发件人邮箱
     */
    public void setSmtpFromMail(String smtpFromMail) {
        this.smtpFromMail = smtpFromMail;
    }

    /**
     * 获取SMTP服务器地址
     * 
     * @return SMTP服务器地址
     */
    @NotEmpty
    @Length(max = 200)
    public String getSmtpHost() {
        return smtpHost;
    }

    /**
     * 设置SMTP服务器地址
     * 
     * @param smtpHost
     *            SMTP服务器地址
     */
    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    /**
     * 获取SMTP服务器端口
     * 
     * @return SMTP服务器端口
     */
    @NotNull
    @Min(0)
    public Integer getSmtpPort() {
        return smtpPort;
    }

    /**
     * 设置SMTP服务器端口
     * 
     * @param smtpPort
     *            SMTP服务器端口
     */
    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }

    /**
     * 获取SMTP用户名
     * 
     * @return SMTP用户名
     */
    @NotEmpty
    @Length(max = 200)
    public String getSmtpUsername() {
        return smtpUsername;
    }

    /**
     * 设置SMTP用户名
     * 
     * @param smtpUsername
     *            SMTP用户名
     */
    public void setSmtpUsername(String smtpUsername) {
        this.smtpUsername = smtpUsername;
    }

    /**
     * 获取SMTP密码
     * 
     * @return SMTP密码
     */
    @Length(max = 200)
    public String getSmtpPassword() {
        return smtpPassword;
    }

    /**
     * 设置SMTP密码
     * 
     * @param smtpPassword
     *            SMTP密码
     */
    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

   
    /**
     * 获取Cookie路径
     * 
     * @return Cookie路径
     */
    @NotEmpty
    @Length(max = 200)
    public String getCookiePath() {
        return cookiePath;
    }

    /**
     * 设置Cookie路径
     * 
     * @param cookiePath
     *            Cookie路径
     */
    public void setCookiePath(String cookiePath) {
        if (cookiePath != null && !cookiePath.endsWith("/")) {
            cookiePath += "/";
        }
        this.cookiePath = cookiePath;
    }

    /**
     * 获取Cookie作用域
     * 
     * @return Cookie作用域
     */
    @Length(max = 200)
    public String getCookieDomain() {
        return cookieDomain;
    }

    /**
     * 设置Cookie作用域
     * 
     * @param cookieDomain
     *            Cookie作用域
     */
    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

   

    /**
     * 获取允许上传图片扩展名
     * 
     * @return 允许上传图片扩展名
     */
    public String[] getUploadImageExtensions() {
        return StringUtils.split(uploadImageExtension, SEPARATOR);
    }

    /**
     * 获取允许上传Flash扩展名
     * 
     * @return 允许上传Flash扩展名
     */
    public String[] getUploadFlashExtensions() {
        return StringUtils.split(uploadFlashExtension, SEPARATOR);
    }

    /**
     * 获取允许上传媒体扩展名
     * 
     * @return 允许上传媒体扩展名
     */
    public String[] getUploadMediaExtensions() {
        return StringUtils.split(uploadMediaExtension, SEPARATOR);
    }

    /**
     * 获取允许上传文件扩展名
     * 
     * @return 允许上传文件扩展名
     */
    public String[] getUploadFileExtensions() {
        return StringUtils.split(uploadFileExtension, SEPARATOR);
    }
    
    /**
     * 获取验证码类型
     * 
     * @return 验证码类型
     */
    public CaptchaType[] getCaptchaTypes() {
        return captchaTypes;
    }

    /**
     * 设置验证码类型
     * 
     * @param captchaTypes
     *            验证码类型
     */
    public void setCaptchaTypes(CaptchaType[] captchaTypes) {
        this.captchaTypes = captchaTypes;
    }

    public String getQrImageUrl ()
    {
      return this.qrImageUrl;
    }

    public String getMsgPushUrl ()
    {
      return msgPushUrl;
    }

    public void setMsgPushUrl (String msgPushUrl)
    {
      this.msgPushUrl = msgPushUrl;
    }

    public String getUcpaasUrl ()
    {
      return ucpaasUrl;
    }

    public void setUcpaasUrl (String ucpaasUrl)
    {
      this.ucpaasUrl = ucpaasUrl;
    }

    public String getUcpaasSid ()
    {
      return ucpaasSid;
    }

    public void setUcpaasSid (String ucpaasSid)
    {
      this.ucpaasSid = ucpaasSid;
    }

    public String getUcpaasToken ()
    {
      return ucpaasToken;
    }

    public void setUcpaasToken (String ucpaasToken)
    {
      this.ucpaasToken = ucpaasToken;
    }

    public String getUcpaasVersion ()
    {
      return ucpaasVersion;
    }

    public void setUcpaasVersion (String ucpaasVersion)
    {
      this.ucpaasVersion = ucpaasVersion;
    }

    public String getUcpaasAppId ()
    {
      return ucpaasAppId;
    }

    public void setUcpaasAppId (String ucpaasAppId)
    {
      this.ucpaasAppId = ucpaasAppId;
    }

    public String getUcpaasTemplate ()
    {
      return ucpaasTemplate;
    }

    public void setUcpaasTemplate (String ucpaasTemplate)
    {
      this.ucpaasTemplate = ucpaasTemplate;
    }

    public String getUcpaasCallDisplay ()
    {
      return ucpaasCallDisplay;
    }

    public void setUcpaasCallDisplay (String ucpaasCallDisplay)
    {
      this.ucpaasCallDisplay = ucpaasCallDisplay;
    }

    public String getObdServiceUrl ()
    {
      return obdServiceUrl;
    }

    public void setObdServiceUrl (String obdServiceUrl)
    {
      this.obdServiceUrl = obdServiceUrl;
    }

    public Integer getClearingRecordCycle ()
    {
      return clearingRecordCycle;
    }

    public void setClearingRecordCycle (Integer clearingRecordCycle)
    {
      this.clearingRecordCycle = clearingRecordCycle;
    }

    public Long getImageMaxSize() {
      return imageMaxSize;
  }
  
  public void setImageMaxSize(Long imageMaxSize) {
      this.imageMaxSize = imageMaxSize;
  }

  public String getConvertMapUrl ()
  {
    return convertMapUrl;
  }

  public void setConvertMapUrl (String convertMapUrl)
  {
    this.convertMapUrl = convertMapUrl;
  }

  public String getConvertAddressUrl ()
  {
    return convertAddressUrl;
  }

  public void setConvertAddressUrl (String convertAddressUrl)
  {
    this.convertAddressUrl = convertAddressUrl;
  }

  public String getMapAk ()
  {
    return mapAk;
  }

  public void setMapAk (String mapAk)
  {
    this.mapAk = mapAk;
  }

  public String getConvertMutipleMapUrl ()
  {
    return convertMutipleMapUrl;
  }

  public void setConvertMutipleMapUrl (String convertMutipleMapUrl)
  {
    this.convertMutipleMapUrl = convertMutipleMapUrl;
  }

  public String getUcpaasAccountTemplate ()
  {
    return ucpaasAccountTemplate;
  }

  public void setUcpaasAccountTemplate (String ucpaasAccountTemplate)
  {
    this.ucpaasAccountTemplate = ucpaasAccountTemplate;
  }
    
  
}