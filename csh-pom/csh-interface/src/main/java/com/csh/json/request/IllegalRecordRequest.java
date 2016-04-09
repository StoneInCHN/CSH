package com.csh.json.request;

import com.csh.json.base.BaseRequest;

public class IllegalRecordRequest extends BaseRequest {

  /**
   * 车牌
   */
  private String plate;

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

}
