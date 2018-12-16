package com.steffens.weixin.demo003.config;

/**
 * @创建人 steffens
 * @创建时间 2018/12/16
 * @描述 文件创建
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface XStreamCDATA {
}
