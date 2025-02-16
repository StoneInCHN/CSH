package com.csh.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.csh.beans.CommonAttributes;
import com.csh.beans.LogConfig;
import com.csh.service.LogConfigService;

/**
 * 日志配置
 * 
 * @author shijun
 *
 */
@Service("logConfigServiceImpl")
public class LogConfigServiceImpl implements LogConfigService {

  @Cacheable("logConfig")
  public List<LogConfig> getAll() {
    try {
      File commonXmlFile = new ClassPathResource(CommonAttributes.COMMON_CONFIG_XML_PATH).getFile();
      Document document = new SAXReader().read(commonXmlFile);
      List<org.dom4j.Element> elements = document.selectNodes("/csh/logConfig");
      List<LogConfig> logConfigs = new ArrayList<LogConfig>();
      for (org.dom4j.Element element : elements) {
        String operation = element.attributeValue("operation");
        String urlPattern = element.attributeValue("urlPattern");
        LogConfig logConfig = new LogConfig();
        logConfig.setOperation(operation);
        logConfig.setUrlPattern(urlPattern);
        logConfigs.add(logConfig);
      }
      return logConfigs;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
