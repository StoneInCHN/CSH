package com.csh.json.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 返回entity 结果集
 * @author huyong
 *
 * @param <T>
 */
public class ResponseMultipleMap extends BaseResponse{

  private List<Map<String, Object>> msg= new ArrayList<Map<String, Object>>();
  
  private PageResponse page;
  
  
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
  
  
}
