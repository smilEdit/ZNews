package com.zzz.myapplication.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @创建者 zlf
 * @创建时间 2016/9/18 17:18
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
