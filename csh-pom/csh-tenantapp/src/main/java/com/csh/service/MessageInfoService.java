package com.csh.service; 

import com.csh.entity.MessageInfo;
import com.csh.framework.paging.Page;
import com.csh.framework.paging.Pageable;
import com.csh.framework.service.BaseService;

public interface MessageInfoService extends BaseService<MessageInfo,Long>{

	Page<MessageInfo> findMsgByTenantUser(Long userId, Pageable pageable);

}