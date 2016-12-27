package com.csh.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.common.log.LogUtil;
import com.csh.controller.base.MobileBaseController;
import com.csh.entity.DeviceInfo;
import com.csh.entity.commonenum.CommonEnum;
import com.csh.framework.paging.Page;
import com.csh.json.base.BaseResponse;
import com.csh.json.base.PageResponse;
import com.csh.json.request.DeviceInfoRequest;
import com.csh.json.response.DeviceDetailResponse;
import com.csh.json.response.DevicePageResponse;
import com.csh.service.DeviceInfoService;
import com.csh.service.TenantAccountService;
import com.csh.service.VehicleService;
import com.csh.utils.ApiUtils;
import com.csh.utils.FieldFilterUtils;
import com.csh.utils.LatLonUtil;
import com.csh.utils.TokenGenerator;
import com.csh.utils.ToolsUtils;

/**
 * 设备查询控制器
 * Created by zhangye on 2016/12/23.
 */
@Controller("deviceInfoController")
@RequestMapping("/deviceInfo")
public class DeviceInfoController extends MobileBaseController{

    @Autowired
    private TenantAccountService tenantAccountService;

    @Autowired
    private DeviceInfoService deviceInfoService;

    @Autowired
    private VehicleService vehicleService;

    /**
     * 绑定设备
     * @param request
     * @return
     */
    @RequestMapping(value = "/bindDevice", method = RequestMethod.POST)
    public @ResponseBody BaseResponse bindDevice(@RequestBody DeviceInfoRequest request){
        BaseResponse response = new BaseResponse();
        Long userId = request.getUserId();
        String token = request.getToken();
        Long tenantId = request.getTenantId();
        Long deviceId = request.getDeviceId();
        Long vehicleId = request.getVehicleId();

        if (LogUtil.isDebugEnabled(getClass())){
            LogUtil.debug(getClass(), "bindDevice", "request param: %s", request.toString());
        }

        //参数有效性验证
        if (userId == null || tenantId == null || deviceId == null){
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

        DeviceInfo deviceInfo = deviceInfoService.find(deviceId);
        deviceInfo.setBindStatus(CommonEnum.BindStatus.BINDED);
        deviceInfo.setVehicle(vehicleService.find(vehicleId));
        deviceInfo.setBindTime(new Date());
        deviceInfoService.update(deviceInfo);

        String newToken = TokenGenerator.generateToken(token);
        response.setToken(newToken);
        response.setCode(CommonAttributes.SUCCESS);
        response.setDesc(SUCCESS_MESSAGE.getContent());

        if (LogUtil.isDebugEnabled(getClass())){
            LogUtil.debug(getClass(), "bindDevice", "response:success");
        }

        return response;
    }

    /**
     * 解绑设备
     * @param request
     * @return
     */
    @RequestMapping(value = "/unbindDevice", method = RequestMethod.POST)
    public @ResponseBody BaseResponse unbindDevice(@RequestBody DeviceInfoRequest request){
        BaseResponse response = new BaseResponse();
        Long userId = request.getUserId();
        String token = request.getToken();
        Long tenantId = request.getTenantId();
        Long deviceId = request.getDeviceId();

        if (LogUtil.isDebugEnabled(getClass())){
            LogUtil.debug(getClass(), "unbindDevice", "request param: %s", request.toString());
        }

        //参数有效性验证
        if (userId == null || tenantId == null || deviceId == null){
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

        DeviceInfo deviceInfo = deviceInfoService.find(deviceId);
        deviceInfo.setBindStatus(CommonEnum.BindStatus.UNBINDED);
        deviceInfo.setVehicle(null);
        deviceInfo.setUnBindTime(new Date());
        deviceInfoService.update(deviceInfo);

        String newToken = TokenGenerator.generateToken(token);
        response.setToken(newToken);
        response.setCode(CommonAttributes.SUCCESS);
        response.setDesc(SUCCESS_MESSAGE.getContent());

        if (LogUtil.isDebugEnabled(getClass())){
            LogUtil.debug(getClass(), "unbindDevice", "response:success");
        }

        return response;
    }

    /**
     * 设备详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/getDeviceDetail", method = RequestMethod.POST)
    public @ResponseBody DeviceDetailResponse getDeviceDetail(@RequestBody DeviceInfoRequest request){
        DeviceDetailResponse response = new DeviceDetailResponse();
        Long userId = request.getUserId();
        String token = request.getToken();
        Long tenantId = request.getTenantId();
        Long deviceId = request.getDeviceId();
        String deviceNo = request.getDeviceNo();

        if (LogUtil.isDebugEnabled(getClass())){
            LogUtil.debug(getClass(), "getDeviceDetail", "request param: %s", request.toString());
        }

        //参数有效性验证
        if (userId == null || tenantId == null || deviceId == null || deviceNo == null){
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

        //获取设备详情信息
        DeviceInfo deviceInfo = deviceInfoService.find(deviceId);
        String[] properties = {"id", "deviceNo", "type", "bindStatus", "deviceStatus", "vehicle.plate", "bindTime" ,"createDate"};
        Map<String, Object> resultMap = FieldFilterUtils.filterEntityMap(properties, deviceInfo);

        //通过OBD接口获取：经纬度，方位角
        String url = setting.getObdServerUrl() + "/appVehicleData/vehicleTrends.jhtml?deviceId=" + deviceNo;
        String res = ApiUtils.post(url);
        Map<String, Object> map = ToolsUtils.convertStrToJson(res);
        Map<String, Object> msg = (Map<String, Object>) map.get("msg");
        if (LogUtil.isDebugEnabled(getClass())) {
            LogUtil.debug(getClass(), "getDeviceDetail",
                    "Receive the msg from obd. deviceNo: %s, Msg: %s", deviceNo, msg);
        }
        if (msg != null) {
            //经纬度
            Map<String, Object> xy =
                    LatLonUtil.convertCoordinate(((Double)msg.get("lon")).toString(), ((Double)msg.get("lat")).toString());
            resultMap.putAll(xy);
            //方位角
            Double azimuth = (Double) msg.get("azimuth");
            resultMap.put("azimuth", azimuth);
        }else {
            resultMap.put("azimuth", null);
            resultMap.put("lng", null);
            resultMap.put("lat", null);
        }
        response.setDeviceDetail(resultMap);

        String newToken = TokenGenerator.generateToken(token);
        response.setToken(newToken);
        response.setCode(CommonAttributes.SUCCESS);
        response.setDesc(SUCCESS_MESSAGE.getContent());

        if (LogUtil.isDebugEnabled(getClass())){
            LogUtil.debug(getClass(), "getDeviceDetail", "response:success");
        }

        return response;
    }

    /**
     * 设备分页信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/findPage", method = RequestMethod.POST)
    public @ResponseBody
    DevicePageResponse findPage(@RequestBody DeviceInfoRequest request){
        DevicePageResponse response = new DevicePageResponse();
        Long userId = request.getUserId();
        String token = request.getToken();
        Long tenantId = request.getTenantId();

        if (LogUtil.isDebugEnabled(getClass())){
            LogUtil.debug(getClass(), "findPage", "request param: %s", request.toString());
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

        //设备结果List
        Page<DeviceInfo> pageByRequest = deviceInfoService.findPageByRequest(request);
        String[] properties = {"id", "deviceNo", "type", "bindStatus", "deviceStatus", "vehicle.plate", "bindTime" ,"createDate"};
        List<Map<String, Object>> resultMaps = FieldFilterUtils.filterCollectionMap(properties, pageByRequest.getContent());
        response.setMsg(resultMaps);

        //分页信息
        PageResponse pageResponse = new PageResponse();
        pageResponse.setPageNumber(pageByRequest.getPageNumber());
        pageResponse.setPageSize(pageByRequest.getPageSize());
        pageResponse.setTotal((int)pageByRequest.getTotal());
        response.setPage(pageResponse);

        //设备数量
        Integer bindDeviceCount = deviceInfoService.countByBindStatus(tenantId, CommonEnum.BindStatus.BINDED);
        Integer unbindDeviceCount = deviceInfoService.countByBindStatus(tenantId, CommonEnum.BindStatus.UNBINDED);
        response.setDeviceBindCount(bindDeviceCount);
        response.setDeviceUnbindCount(unbindDeviceCount);
        response.setDeviceAllCount(bindDeviceCount + unbindDeviceCount);

        String newToken = TokenGenerator.generateToken(token);
        response.setToken(newToken);
        response.setCode(CommonAttributes.SUCCESS);
        response.setDesc(SUCCESS_MESSAGE.getContent());

        if (LogUtil.isDebugEnabled(getClass())){
            LogUtil.debug(getClass(), "findPage", "response:success");
        }

        return response;
    }

}

