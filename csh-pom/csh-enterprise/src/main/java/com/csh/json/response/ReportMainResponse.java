package com.csh.json.response;

public class ReportMainResponse
{
  private Long bindedDeviceCount;
  
  private Long unbindedDeviceCount;
  
  private Long endUserCount;
  
  private Long vehicleCount;

  public Long getBindedDeviceCount ()
  {
    return bindedDeviceCount;
  }

  public void setBindedDeviceCount (Long bindedDeviceCount)
  {
    this.bindedDeviceCount = bindedDeviceCount;
  }

  public Long getUnbindedDeviceCount ()
  {
    return unbindedDeviceCount;
  }

  public void setUnbindedDeviceCount (Long unbindedDeviceCount)
  {
    this.unbindedDeviceCount = unbindedDeviceCount;
  }

  public Long getEndUserCount ()
  {
    return endUserCount;
  }

  public void setEndUserCount (Long endUserCount)
  {
    this.endUserCount = endUserCount;
  }

  public Long getVehicleCount ()
  {
    return vehicleCount;
  }

  public void setVehicleCount (Long vehicleCount)
  {
    this.vehicleCount = vehicleCount;
  }

 
  
}
