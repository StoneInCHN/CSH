package com.csh.service; 

import com.csh.entity.MessageInfo;
import com.csh.framework.service.BaseService;

public interface MessageInfoService extends BaseService<MessageInfo,Long>{


  boolean saveMessage (MessageInfo messageInfo, Long[] ids);

}