package com.zzz.news.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @创建者 zlf
 * @创建时间 2016/9/18 15:46
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface ContextLife {
    String value() default "Application";
}
