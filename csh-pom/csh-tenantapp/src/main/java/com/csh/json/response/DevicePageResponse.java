package com.csh.json.response;

import com.csh.json.base.BaseResponse;
import com.csh.json.base.PageResponse;

import java.util.List;
import java.util.Map;

/**
 * 设备分页查询
 * Created by zhangye on 2016/12/25.
 */
public class DevicePageResponse extends BaseResponse {

    private List<Map<String, Object>> msg;

    private PageResponse page;

    private Integer deviceAllCount;

    private Integer deviceBindCount;

    private Integer deviceUnbindCount;

    public List<Map<String, Object>> getMsg() {
        return msg;
    }

    public void setMsg(List<Map<String, Object>> msg) {
        this.msg = msg;
    }

    public PageResponse getPage() {
        return page;
    }

    public void setPage(PageResponse page) {
        this.page = page;
    }

    public Integer getDeviceAllCount() {
        return deviceAllCount;
    }

    public void setDeviceAllCount(Integer deviceAllCount) {
        this.deviceAllCount = deviceAllCount;
    }

    public Integer getDeviceBindCount() {
        return deviceBindCount;
    }

    public void setDeviceBindCount(Integer deviceBindCount) {
        this.deviceBindCount = deviceBindCount;
    }

    public Integer getDeviceUnbindCount() {
        return deviceUnbindCount;
    }

    public void setDeviceUnbindCount(Integer deviceUnbindCount) {
        this.deviceUnbindCount = deviceUnbindCount;
    }
}
