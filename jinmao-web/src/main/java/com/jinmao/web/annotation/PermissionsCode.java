package com.jinmao.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @描述 : 权限码注解
 * @创建者 : HeZeMin
 * @创建时间 : 2017-1-11 下午4:44:11
 */
@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PermissionsCode {
	/**
	 * @描述 : 权限码参数
	 * @创建者 : HeZeMin
	 * @创建时间 : 2017-1-11 下午4:44:46
	 */
	String code() default "";
}