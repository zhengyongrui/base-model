package com.zyr.common.annotation;


import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

/**
 * @author zhengyongrui
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ComponentScan("com.zyr.common.configuration")
public @interface CommonModelConfiguration {

    String ComponentScan() default "com.zyr.common.configuration";

}
