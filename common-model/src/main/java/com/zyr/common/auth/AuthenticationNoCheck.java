package com.zyr.common.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 无需检查到登录验证注解
 * @Author: zhengyongrui
 * @Date: 2020-03-08 11:35
 */
@Target(value= ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthenticationNoCheck {
}
