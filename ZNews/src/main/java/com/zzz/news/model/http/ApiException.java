package com.zzz.news.model.http;

/**
 * @创建者 zlf
 * @创建时间 2016/9/18 17:24
 */
public class ApiException extends Exception{
    public ApiException(String msg) {
        super(msg);
    }
}
