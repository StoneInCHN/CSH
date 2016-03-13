package com.csh.json.request;

import org.springframework.web.multipart.MultipartFile;

import com.csh.json.base.BaseRequest;

public class EndUserInfoRequest extends BaseRequest{
    /**
     * 昵称
     */
    private String nickName;
    
    /**
     * 用户头像
     */
    private MultipartFile photo;
    
    /**
     *  签名
     */
    private String sign;
    
    
   
    public MultipartFile getPhoto() {
      return photo;
    }
    public void setPhoto(MultipartFile photo) {
      this.photo = photo;
    }
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
    
    
}
