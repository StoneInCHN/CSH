package com.csh.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csh.service.TenantDeductClearingRecordService;

@RequestMapping("console/TenantDeductClearingRecord")
@Controller("tenantDeductClearingRecordController")
public class TenantDeductClearingRecordController {

  @Resource()
  private TenantDeductClearingRecordService tenantDeductClearingRecordService;
  
}
