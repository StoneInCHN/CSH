package com.csh.service; 

import com.csh.entity.SmsToken;
import com.csh.entity.commonenum.CommonEnum.SmsTokenType;
import com.csh.framework.service.BaseService;

public interface SmsTokenService extends BaseService<SmsToken,Long>{

    /**
     * 根据手机号查找短信验证码
     * 
     * @param mobile 手机号
     * 
     */
    SmsToken findByUserMobile(String mobile);
    /**
     * 根据手机号查找短信验证码
     * 
     * @param mobile 手机号
     * @param smsTokenType 短信验证码类型
     */
    SmsToken findByUserMobile(String mobile,SmsTokenType smsTokenType);
}