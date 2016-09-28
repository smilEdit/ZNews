package com.zzz.news.model.http;

/**
 * @创建者 zlf
 * @创建时间 2016/9/28 14:08
 */

public class JuheHttpResponse<T> {
    public static final int SUCCESS_CODE = 0;

    private int error_code;
    private T       result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
