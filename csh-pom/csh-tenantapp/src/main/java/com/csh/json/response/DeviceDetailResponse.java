package com.csh.json.response;

import com.csh.entity.DeviceInfo;
import com.csh.json.base.BaseResponse;

import java.util.Map;

/**
 * 设备详情
 * Created by zhangye on 2016/12/25.
 */
public class DeviceDetailResponse extends BaseResponse {

    /**
     * 设备详情结果
     */
    private Map<String, Object> deviceDetail;

    public Map<String, Object> getDeviceDetail() {
        return deviceDetail;
    }

    public void setDeviceDetail(Map<String, Object> deviceDetail) {
        this.deviceDetail = deviceDetail;
    }
}
