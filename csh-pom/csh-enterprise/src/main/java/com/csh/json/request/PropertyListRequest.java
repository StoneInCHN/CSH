package com.csh.json.request;

import com.csh.json.response.PropertyGridResponse;

public class PropertyListRequest
{

  private PropertyGridResponse[] propertyGridResponses;

  public PropertyGridResponse[] getPropertyGridResponses ()
  {
    return propertyGridResponses;
  }

  public void setPropertyGridResponses (
      PropertyGridResponse[] propertyGridResponses)
  {
    this.propertyGridResponses = propertyGridResponses;
  }

  
}
