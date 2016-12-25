package com.csh.json.request;

import com.csh.entity.commonenum.CommonEnum;
import com.csh.json.base.BaseRequest;

/**
 * Created by zhangye on 2016/12/23.
 *
 * 设备信息查询请求参数
 */
public class DeviceInfoRequest extends BaseRequest{

    /**
     * 设备编号
     */
    private String deviceNo;

    /**
     * 绑定状态
     */
    private CommonEnum.BindStatus bindStatus;

    /**
     * 设备ID
     */
    private Long deviceId;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public CommonEnum.BindStatus getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(CommonEnum.BindStatus bindStatus) {
        this.bindStatus = bindStatus;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[userId=").append(getUserId()).append("]");
        buffer.append("[token=").append(getToken()).append("]");
        buffer.append("[tenantId=").append(getTenantId()).append("]");
        buffer.append("[deviceNo=").append(getDeviceNo()).append("]");
        buffer.append("[deviceId=").append(getDeviceId()).append("]");
        buffer.append("[bindStatus=").append(getBindStatus()).append("]");
        return buffer.toString();
    }
}
