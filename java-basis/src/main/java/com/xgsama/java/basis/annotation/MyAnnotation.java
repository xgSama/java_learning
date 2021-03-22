package com.xgsama.java.basis.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * MyAnnotation
 *
 * @author xgSama
 * @date 2020/11/23 20:33
 */
@Target({TYPE, FIELD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, METHOD})
@Retention(RUNTIME)
public @interface MyAnnotation {
    String value() default "";
}
