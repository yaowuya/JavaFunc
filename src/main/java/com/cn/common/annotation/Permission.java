package com.cn.common.annotation;

import java.lang.annotation.*;

/**
 * User: zhongrf
 * Date: 2018/3/21 14:26
 * Description:自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface Permission {
}
