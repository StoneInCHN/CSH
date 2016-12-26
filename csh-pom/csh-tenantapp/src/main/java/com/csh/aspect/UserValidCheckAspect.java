package com.csh.aspect;


import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csh.beans.CommonAttributes;
import com.csh.beans.Message;
import com.csh.entity.TenantAccount;
import com.csh.entity.commonenum.CommonEnum.AccountStatus;
import com.csh.service.TenantAccountService;

@Aspect
@Component
public class UserValidCheckAspect {


  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  // Controller层切点
  @Pointcut("@annotation(com.csh.aspect.UserValidCheck)")
  public void controllerAspect() {}


  /**
   * 前置通知 用于拦截Controller层记录用户的操作
   * 
   * @param joinPoint 切点
   * @throws Throwable
   */
  @Around(value = "controllerAspect()")
  public @ResponseBody Object checkUserValid(ProceedingJoinPoint joinPoint) throws Throwable {
    UserParam userParam = getControllerMethodParam(joinPoint);
    Boolean validFlag = true;
    TenantAccount tenantAccount = tenantAccountService.find(userParam.getUserId());
    if (tenantAccount == null || AccountStatus.DELETE.equals(tenantAccount.getAccoutStatus())
        || AccountStatus.LOCKED.equals(tenantAccount.getAccoutStatus())) {
      validFlag = false;
    }
    if (!validFlag) {
      Class returnTypeClass = (Class) userParam.getReturnType();
      Object response = returnTypeClass.newInstance();
      BeanMap beanMap = BeanMap.create(response);
      beanMap.put("code", CommonAttributes.USER_INVALID);
      beanMap.put("desc", Message.warn("csh.user.invalid").getContent());
      return response;
    }

    return joinPoint.proceed();

  }


  /**
   * 获取注解中对方法的参数信息 用于Controller层注解
   * 
   * @param joinPoint 切点
   * @return 方法描述
   * @throws Exception
   */
  public static UserParam getControllerMethodParam(ProceedingJoinPoint joinPoint) throws Exception {
    UserParam params = new UserParam();
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
        Class[] clazzs = method.getParameterTypes();
        if (clazzs.length == arguments.length) {

          Type returnType = method.getGenericReturnType();
          if (returnType instanceof ParameterizedType) {
            params.setReturnType(((ParameterizedType) returnType).getRawType());
          } else {
            params.setReturnType(returnType);
          }
          if (arguments.length > 0 && arguments[0] != null) {

            BeanMap beanMap = BeanMap.create(arguments[0]);
            Long userId = (Long) beanMap.get("userId");
            params.setUserId(userId);
          }
          break;
        }
      }
    }
    return params;
  }
}
