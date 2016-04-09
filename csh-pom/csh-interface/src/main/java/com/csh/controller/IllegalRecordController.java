package com.csh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.aspect.UserValidCheck;
import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.controller.base.BaseController;
import com.csh.entity.IllegalRecord;
import com.csh.json.base.ResponseMultiple;
import com.csh.json.request.IllegalRecordRequest;
import com.csh.service.EndUserService;
import com.csh.service.IllegalRecordService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;

/**
 * 违章查询
 * 
 * @author shijun
 *
 */
@Controller("illegalRecordController")
public class IllegalRecordController extends BaseController {

  @Resource(name = "endUserServiceImpl")
  private EndUserService endUserService;

  @Resource(name = "illegalRecordServiceImpl")
  private IllegalRecordService illegalRecordService;

  @RequestMapping(value = "/getIllegalRecords", method = RequestMethod.POST)
  @UserValidCheck
  public @ResponseBody ResponseMultiple<Map<String, Object>> getIllegalRecords(
      @RequestBody IllegalRecordRequest illegalRecordRequest) {

    ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
    Long userId = illegalRecordRequest.getUserId();
    String token = illegalRecordRequest.getToken();
    // 验证登录token
    String userToken = endUserService.getEndUserToken(userId);
    if (!TokenGenerator.isValiableToken(token, userToken)) {
      response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
      response.setDesc(Message.error("csh.user.token.timeout").getContent());
      return response;
    }

    List<IllegalRecord> illegalRecords = null;

    if (illegalRecordRequest.getPlate() != null) {
      illegalRecords = illegalRecordService.getIllegalRecords(illegalRecordRequest.getPlate());
    }

    if (illegalRecords != null && illegalRecords.size() > 0) {

      String[] properties =
          {"id", "processingSite", "score", "finesAmount", "illegalContent", "illegalAddress",
              "plate", "illegalDate"};
      List<Map<String, Object>> map =
          FieldFilterUtils.filterCollectionMap(properties, illegalRecords);

      response.setMsg(map);

    }

    String newtoken = TokenGenerator.generateToken(illegalRecordRequest.getToken());
    endUserService.createEndUserToken(newtoken, userId);
    response.setToken(newtoken);
    response.setCode(CommonAttributes.SUCCESS);

    return response;

  }

}
