package com.csh.json.request;

public class EndUserRequest
{
  public enum ResultType{
    //插入成功
    Success("插入成功"),
    //插入失败
    Faild("插入失败"),
    //记录已经存在
    Already("记录已经存在"),
    //缺少参数
    MissingParameters("参数缺少");
    private ResultType(String resultTypeName) {
      this.resultTypeName = resultTypeName;
    }
    private String resultTypeName;

    public String getResultTypeName() {
      return resultTypeName;
    }
  }
  private String name;
  private String mobile;
  private String plate;
  private String result;
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
  public String getResult ()
  {
    return result;
  }
  public void setResult (String result)
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
