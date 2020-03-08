package com.zyr.common.auth;

import com.zyr.common.util.jwt.JwtOperator;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 认证切面
 * @Author: zhengyongrui
 * @Date: 2020-03-08 11:32
 */
@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationAspect {

    private final JwtOperator jwtOperator;

    @Pointcut("execution(* com.zyr..controller.*Controller.*(..))")
    private void pointcut() {}

    @Around("pointcut()")
    public Object checkLogin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        checkToken(proceedingJoinPoint);
        return proceedingJoinPoint.proceed();
    }

    private void checkToken(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = methodSignature.getMethod();
            if (!method.isAnnotationPresent(AuthenticationNoCheck.class)) {
                HttpServletRequest httpServletRequest = getHttpServletRequest();
                String token = httpServletRequest.getHeader("z-token");
                Boolean isValid = jwtOperator.validateToken(token);
                if (!isValid) {
                    throw new SecurityException("Token不合法！");
                }
                // 如果校验成功，那么就将用户的信息设置到request的attribute里面
                Claims claims = jwtOperator.getClaimsFromToken(token);
                httpServletRequest.setAttribute("userInfo", claims.get("userInfo"));
                httpServletRequest.setAttribute("wechatUser", claims.get("wechatUser"));
            }
        } catch (Throwable throwable) {
            throw new SecurityException("Token不合法！");
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }
}
