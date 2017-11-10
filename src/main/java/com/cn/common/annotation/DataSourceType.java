package com.cn.common.annotation;

import java.lang.annotation.*;

/**
 * Created by 钟锐锋 on 2017/11/10.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface DataSourceType {
    String value();
}
