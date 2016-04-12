package com.csh.aspect;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csh.aspect.URLValidCheck.UrlMethodType;


@Aspect
@Component
public class URLValidCheckAspect {


  // Controller层切点
  @Pointcut("@annotation(com.lb.aspect.URLValidCheck)")
  public void controllerAspect() {}


  /**
   * 获取list方法的请求参数
   * 
   * @param joinPoint 切点
   * @throws Throwable
   */
  @Around(value = "controllerAspect()")
  public Object checkURLValid(ProceedingJoinPoint joinPoint) throws Throwable {
    // 获取目标类（controller）
    String targetName = joinPoint.getTarget().getClass().getName();
    // 获取目标方法（需要切入的方法）
    String methodName = joinPoint.getSignature().getName();
    // 获取目标方法参数
    Object[] arguments = joinPoint.getArgs();
    Class targetClass = Class.forName(targetName);
    Method[] methods = targetClass.getMethods();
    for (Method method : methods) {
      if (method.getName().equals(methodName)) {
        UrlMethodType urlMethodType = method.getAnnotation(URLValidCheck.class).urlMethod();
        Class[] clazzs = method.getParameterTypes();
        if (clazzs.length == arguments.length) {
          HttpSession session = null;
          if (UrlMethodType.LIST.equals(urlMethodType)) {
            if (arguments.length > 0 && arguments[0] != null) {
              if (arguments[0] instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) arguments[0];
                session = request.getSession();
                Map<String, Object> redirectMap = new HashMap<String, Object>();
                Map<String, Object> parameterMap = request.getParameterMap();
                redirectMap.put("controllerName", targetName);
                for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
                  redirectMap.put(entry.getKey(), entry.getValue());
                }
                session.setAttribute("redirectUrlSession", redirectMap);
              }
            }

          } else if (UrlMethodType.UPDATE.equals(urlMethodType)) {
            if (arguments.length > 0 && arguments[0] != null) {
              if (arguments[0] instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) arguments[0];
                session = request.getSession();
              }
            }
            if (arguments.length > 1 && arguments[1] != null) {
              if (arguments[1] instanceof RedirectAttributes) {
                RedirectAttributes redirectAttributes = (RedirectAttributes) arguments[1];
                if (session != null) {
                  Map<String, Object> redirectMap = new HashMap<String, Object>();
                  redirectMap = (Map<String, Object>) session.getAttribute("redirectUrlSession");
                  if(redirectMap !=null){
                    String controllerName = redirectMap.get("controllerName").toString();
                    if (targetName.equals(String.valueOf(controllerName))) {
                      for (Map.Entry<String, Object> entry : redirectMap.entrySet()) {
                        if (!entry.getKey().equals("controllerName")) {
                          redirectAttributes.addAttribute(entry.getKey(), entry.getValue());
                        }
                      }
                    }
                  }
                  
                }
              }
            }
          }
          break;
        }
      }
    }
    return joinPoint.proceed();
  }

}
