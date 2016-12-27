package com.csh.json.response;

import java.util.Map;

import com.csh.json.base.ResponseMultiple;

public class VehiclePageResponse extends ResponseMultiple<Map<String, Object>> {

  /**
   * 全部数量
   */
  private Long allCount;

  /**
   * 在线数量
   */
  private Long onlineCount;

  /**
   * 离线数量
   */
  private Long offlineCount;


  public Long getAllCount() {
    return allCount;
  }

  public void setAllCount(Long allCount) {
    this.allCount = allCount;
  }

  public Long getOnlineCount() {
    return onlineCount;
  }

  public void setOnlineCount(Long onlineCount) {
    this.onlineCount = onlineCount;
  }

  public Long getOfflineCount() {
    return offlineCount;
  }

  public void setOfflineCount(Long offlineCount) {
    this.offlineCount = offlineCount;
  }

}
