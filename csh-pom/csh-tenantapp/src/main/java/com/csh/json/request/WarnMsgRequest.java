package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class WarnMsgRequest extends BaseRequest {

	/**
	 * 设备号
	 */
	private String deviceNo;

	/**
	 * 在线状态
	 */
	private String onlineStatus;

	/**
	 * 故障码
	 */
	private String fautCode;

	/**
	 * 异常信息
	 */
	private String warningMsg;

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(String onlineStatus) {
		this.onlineStatus = onlineStatus;
	}

	public String getFautCode() {
		return fautCode;
	}

	public void setFautCode(String fautCode) {
		this.fautCode = fautCode;
	}

	public String getWarningMsg() {
		return warningMsg;
	}

	public void setWarningMsg(String warningMsg) {
		this.warningMsg = warningMsg;
	}

}
