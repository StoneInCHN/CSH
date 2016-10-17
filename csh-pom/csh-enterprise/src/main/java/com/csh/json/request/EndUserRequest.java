package com.csh.json.request;

public class EndUserRequest
{
  public enum ResultType{
    //插入成功
    Success,
    //插入失败
    Faild,
    //记录已经存在
    Already,
    //缺少参数
    MissingParameters
  }
  private String name;
  private String mobile;
  private String plate;
  private ResultType result;
  private String comments;
  
  
  public String getName ()
  {
    return name;
  }
  public void setName (String name)
  {
    this.name = name;
  }
  public String getMobile ()
  {
    return mobile;
  }
  public void setMobile (String mobile)
  {
    this.mobile = mobile;
  }
  public String getPlate ()
  {
    return plate;
  }
  public void setPlate (String plate)
  {
    this.plate = plate;
  }
  public ResultType getResult ()
  {
    return result;
  }
  public void setResult (ResultType result)
  {
    this.result = result;
  }
  public String getComments ()
  {
    return comments;
  }
  public void setComments (String comments)
  {
    this.comments = comments;
  }
  
  
}
