package com.zzz.news.base;

/**
 * @创建者 zlf
 * @创建时间 2016/9/18 14:36
 */
public interface BasePresenter<T extends  BaseView>{

    void attachView(T view);

    void detachView();
}
