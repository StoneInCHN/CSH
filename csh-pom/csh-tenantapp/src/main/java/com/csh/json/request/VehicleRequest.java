package com.csh.json.request;

import com.csh.json.base.BaseRequest;

import java.util.Date;

/**
 * 车辆信息查询请求
 * Created by zhangye on 2016/12/25.
 */
public class VehicleRequest extends BaseRequest{

    /**
     * 所有者
     */
    private String endUserName;

    /**
     * 车牌号
     */
    private String plate;

    /**
     * 上牌日期
     */
    private Date plateDateStart;

    /**
     * 上牌日期
     */
    private Date plateDateEnd;

    public String getEndUserName() {
        return endUserName;
    }

    public void setEndUserName(String endUserName) {
        this.endUserName = endUserName;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Date getPlateDateStart() {
        return plateDateStart;
    }

    public void setPlateDateStart(Date plateDateStart) {
        this.plateDateStart = plateDateStart;
    }

    public Date getPlateDateEnd() {
        return plateDateEnd;
    }

    public void setPlateDateEnd(Date plateDateEnd) {
        this.plateDateEnd = plateDateEnd;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[userId=").append(getUserId()).append("]");
        buffer.append("[token=").append(getToken()).append("]");
        buffer.append("[tenantId=").append(getTenantId()).append("]");
        buffer.append("[endUserName=").append(getEndUserName()).append("]");
        buffer.append("[plate=").append(getPlate()).append("]");
        buffer.append("[plateDateStart=").append(getPlateDateStart()).append("]");
        buffer.append("[plateDateEnd=").append(getPlateDateEnd()).append("]");
        return buffer.toString();
    }

}
