package com.csh.controller;

import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.Vehicle;
import com.csh.framework.paging.Page;
import com.csh.json.request.VehicleRequest;
import com.csh.json.base.ResponseMultiple;
import com.csh.service.TenantAccountService;
import com.csh.service.VehicleService;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 车辆查询控制器
 * Created by zhangye on 2016/12/25.
 */
@Controller("vehicleController")
@RequestMapping("/vehicle")
public class VehicleController extends MobileBaseController{

    @Autowired
    private TenantAccountService tenantAccountService;

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping("/findUnbindVehiclePage")
    public @ResponseBody
    ResponseMultiple<Map<String, Object>> findUnbindVehiclePage(@RequestBody VehicleRequest request){
        ResponseMultiple<Map<String, Object>> response = new ResponseMultiple<Map<String, Object>>();
        Long userId = request.getUserId();
        String token = request.getToken();
        Long tenantId = request.getTenantId();

        if (LogUtil.isDebugEnabled(getClass())){
            LogUtil.debug(getClass(), "findUnbindVehiclePage", "request param: %s", request.toString());
        }

        //参数有效性验证
        if (userId == null || tenantId == null){
            response.setCode(CommonAttributes.MISSING_REQUIRE_PARAM);
            response.setDesc(Message.error("csh.login.param.invalid").getContent());
            return response;
        }
        String userToken = tenantAccountService.getTenantUserToken(userId);
        // token验证
        if (!TokenGenerator.isValiableToken(token, userToken)) {
            response.setCode(CommonAttributes.FAIL_TOKEN_TIMEOUT);
            response.setDesc(Message.error("csh.user.token.timeout").getContent());
            return response;
        }

        //查询车辆分页数据
        Page<Vehicle> vehiclePage = vehicleService.findPageByRequest(request);
        String[] properties = {"id", "plate", "endUser.realName", "endUser.mobileNum", "vehicleNo", "vehicleBrandDetail", "vehicleFullBrand" ,"createDate"};
        List<Map<String, Object>> resultMaps = FieldFilterUtils.filterCollectionMap(properties, vehiclePage.getContent());
        response.setMsg(resultMaps);

        String newToken = TokenGenerator.generateToken(token);
        response.setToken(newToken);
        response.setCode(CommonAttributes.SUCCESS);
        response.setDesc(SUCCESS_MESSAGE.getContent());

        if (LogUtil.isDebugEnabled(getClass())){
            LogUtil.debug(getClass(), "findUnbindVehiclePage", "response:success");
        }

        return response;
    }



}
