package com.csh.json.response;

public class PropertyGridResponse
{
  private String name;
  private String value;
  private String group;
  private String editor;
  private Long id;
  public PropertyGridResponse ()
  {
    this.editor = "text";
  }
  public String getName ()
  {
    return name;
  }
  public void setName (String name)
  {
    this.name = name;
  }
  public String getValue ()
  {
    return value;
  }
  public void setValue (String value)
  {
    this.value = value;
  }
  public String getGroup ()
  {
    return group;
  }
  public void setGroup (String group)
  {
    this.group = group;
  }
  public String getEditor ()
  {
    return editor;
  }
  public void setEditor (String editor)
  {
    this.editor = editor;
  }
  public Long getId ()
  {
    return id;
  }
  public void setId (Long id)
  {
    this.id = id;
  }
  
}
